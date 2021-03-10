package hilos;

import bbdd.ControladorUser;
import codigos.CodeResponse;
import comunicacion.Comunicacion;
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
public class HiloMain extends Thread {

    private Socket cliente;
    private PublicKey clavePubPropia;
    private PublicKey clavePubAjena;
    private PrivateKey clavePrivPropia;

    public HiloMain(Socket cliente, PublicKey clavePubServidor, PrivateKey clavePrivServidor) {

        this.clavePrivPropia = clavePrivServidor;
        this.clavePubPropia = clavePubServidor;
        this.cliente = cliente;
    }

    public HiloMain(PublicKey clavePubAjena, PrivateKey clavePrivServidor, Socket cliente) {

        this.clavePrivPropia = clavePrivServidor;
        this.clavePubAjena = clavePubAjena;
        this.cliente = cliente;
    }

    @Override
    public void run() {

        try {

            if (clavePubAjena == null) {
                //recibe la clave publica del cliente actual
                clavePubAjena = (PublicKey) Comunicacion.recibirObjeto(cliente);
                //envia la clave publica del servidor al cliente actual
                Comunicacion.enviarObjeto(cliente, clavePubPropia);
            }
            
            boolean activo = true;
            SealedObject so = null;
            do {
                System.out.println("ESPERANDO ORDEN DEL CLIENTE");
                so = (SealedObject) Comunicacion.recibirObjeto(cliente);
                short orden = (short) Seguridad.descifrar(clavePrivPropia, so);

                System.out.println("ORDEN DEL CLIENTE: " + orden);

                switch (orden) {

                    case CodeResponse.LOGIN_CODE:
                        System.out.println("MODO LOGIN");
                        HiloLogin hl = new HiloLogin(cliente, clavePubAjena, clavePrivPropia);
                        activo = false;
                        hl.start();
                        break;

                    case CodeResponse.SIGNUP_CODE:
                        System.out.println("MODO SIGN UP");
                        HiloSignUp hs = new HiloSignUp(cliente, clavePubAjena, clavePrivPropia, false);
                        activo = false;
                        hs.start();
                        break;
                        
                    case CodeResponse.PREFS_CODE:
                        System.out.println("MODO PREFERENCIAS");
                        HiloPreferencias hp = new HiloPreferencias(cliente, clavePubAjena, clavePrivPropia);
                        activo = false;
                        hp.start();
                        break;
                        
                    case CodeResponse.ADMIN_CODE:
                        System.out.println("MODO ADMIN");
                        HiloRolAdmin hra = new HiloRolAdmin(cliente, clavePubAjena, clavePrivPropia);
                        activo = false;
                        hra.start();
                        break;
                }
            } while (activo);

        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
        }

    }

}
