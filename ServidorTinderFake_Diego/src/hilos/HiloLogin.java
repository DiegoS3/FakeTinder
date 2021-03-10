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
        comprobarLogin();
        HiloMain hm = new HiloMain(clavePubAjena, clavePrivPropia, cliente);
        hm.start();
    }
    
    private Usuario recibirUsuario(){
        Usuario u = null;
        
        try {
            SealedObject so = (SealedObject) Comunicacion.recibirObjeto(cliente);
            u = (Usuario) Seguridad.descifrar(clavePrivPropia, so);
            System.out.println("RECIBIDO USUARIO CLIENTE " + u.getEmail());
            
        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | 
                NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(HiloMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return u;
    }
    
    private void comprobarLogin() {
        Usuario userLogin = recibirUsuario();
        Usuario logeado = ControladorUser.comprobarLogin(userLogin.getEmail(), userLogin.getPassword());
        
        if (logeado != null) {
            Utilities.enviarOrden(CodeResponse.LOGIN_CORRECTO_CODE, clavePubAjena, cliente);
            enviarUsuario(logeado);
        }else{
            Utilities.enviarOrden(CodeResponse.ERROR_CODE, clavePubAjena, cliente);
        }
    }
    
    private void enviarUsuario(Usuario user) {
        try {
            SealedObject so = Seguridad.cifrar(clavePubAjena, user);
            Comunicacion.enviarObjeto(cliente, so);
            System.out.println("ENVIANDO USUARIO LOGEADO AL CLIENTE");

        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException ex) {
        }
    }
    
}
