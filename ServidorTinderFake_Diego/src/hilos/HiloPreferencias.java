package hilos;

import bbdd.ControladorPrefrences;
import codigos.CodeResponse;
import comunicacion.Comunicacion;
import datos.Preferencia;
import java.io.IOException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import seguridad.Seguridad;
import utilities.Utilities;

/**
 *
 * @author Diego
 */
public class HiloPreferencias extends Thread {

    private Socket cliente;
    private PublicKey clavePubAjena;
    private PrivateKey clavePrivPropia;

    public HiloPreferencias(Socket cliente, PublicKey clavePubAjena, PrivateKey clavePrivServidor) {

        this.clavePrivPropia = clavePrivServidor;
        this.clavePubAjena = clavePubAjena;
        this.cliente = cliente;
    }

    @Override
    public void run() {
         System.out.println("ME INICIO HILO PREFS");
        Preferencia prefs = (Preferencia) recibirDato();
        short orden = (short) recibirDato();

        switch (orden) {
            case CodeResponse.PREFS_ACTUALIZAR_CODE:
                if (ControladorPrefrences.updatePreferences(prefs)) {
                    Utilities.enviarOrden(CodeResponse.PREFS_CORRECTO_CODE, clavePubAjena, cliente);
                } else {
                    Utilities.enviarOrden(CodeResponse.ERROR_CODE, clavePubAjena, cliente);
                }
                break;

            case CodeResponse.PREFS_CREAR_CODE:
                if (ControladorPrefrences.insertPreferences(prefs)) {
                    Utilities.enviarOrden(CodeResponse.PREFS_CORRECTO_CODE, clavePubAjena, cliente);
                } else {
                    Utilities.enviarOrden(CodeResponse.ERROR_CODE, clavePubAjena, cliente);
                }
                break;
        }
        System.out.println("HE MUERTO HILO PREFERENCIAS");
        HiloMain hm = new HiloMain(clavePubAjena, clavePrivPropia, cliente);
        hm.start();
    }

    private Object recibirDato() {
        Object o = "";
        try {
            SealedObject so = (SealedObject) Comunicacion.recibirObjeto(cliente);
            o = Seguridad.descifrar(clavePrivPropia, so);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException
                | ClassNotFoundException | IllegalBlockSizeException | BadPaddingException ex) {
        }

        return o;
    }
}
