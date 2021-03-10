package hilos;

import bbdd.ControladorPrefrences;
import bbdd.ControladorUser;
import codigos.CodeResponse;
import comunicacion.Comunicacion;
import datos.Preferencia;
import datos.Usuario;
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
public class HiloLogin extends Thread {

    private Socket cliente;
    private PublicKey clavePubAjena;
    private PrivateKey clavePrivPropia;

    public HiloLogin(Socket cliente, PublicKey clavePubAjena, PrivateKey clavePrivServidor) {

        this.clavePrivPropia = clavePrivServidor;
        this.clavePubAjena = clavePubAjena;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        System.out.println("ME INICIO HILO LOGIN");
        comprobarLogin();
        HiloMain hm = new HiloMain(clavePubAjena, clavePrivPropia, cliente);
        hm.start();
        System.out.println("HE MUERTO HILO LOGIN");
    }

    private Usuario recibirUsuario() {
        Usuario u = null;

        try {
            SealedObject so = (SealedObject) Comunicacion.recibirObjeto(cliente);
            u = (Usuario) Seguridad.descifrar(clavePrivPropia, so);
            System.out.println("RECIBIDO USUARIO CLIENTE " + u.getEmail());

        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException
                | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(HiloMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;
    }

    private void comprobarLogin() {
        Usuario userLogin = recibirUsuario();
        Usuario logeado = ControladorUser.comprobarLogin(userLogin.getEmail(), userLogin.getPassword());

        if (logeado != null) {
            if (logeado.isActivado()) {
                Utilities.enviarOrden(CodeResponse.LOGIN_CORRECTO_CODE, clavePubAjena, cliente);
                enviar(logeado);
                comprobarPrefs(logeado.getId());
            } else {
                Utilities.enviarOrden(CodeResponse.LOGIN_NO_ACTIVADO_CODE, clavePubAjena, cliente);
            }

        } else {
            Utilities.enviarOrden(CodeResponse.ERROR_CODE, clavePubAjena, cliente);
        }
    }

    private void enviar(Object o) {
        try {
            SealedObject so = Seguridad.cifrar(clavePubAjena, o);
            Comunicacion.enviarObjeto(cliente, so);
            System.out.println("ENVIANDO USUARIO LOGEADO AL CLIENTE");

        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException ex) {
        }
    }

    private void comprobarPrefs(String id) {
        Preferencia p = ControladorPrefrences.obtenerPreferences(id);

        if (p != null) {
            Utilities.enviarOrden(CodeResponse.PREFS_EXSTEN_CODE, clavePubAjena, cliente);
            enviar(p);
        } else {
            Utilities.enviarOrden(CodeResponse.ERROR_CODE, clavePubAjena, cliente);
            enviar(p);
        }
    }
}
