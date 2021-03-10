
package hilos;

import bbdd.ControladorPerfil;
import codigos.CodeResponse;
import comunicacion.Comunicacion;
import datos.Perfil;
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
public class HiloPerfil extends Thread{
    
    private Socket cliente;
    private PublicKey clavePubAjena;
    private PrivateKey clavePrivPropia;

    public HiloPerfil(Socket cliente, PublicKey clavePubAjena, PrivateKey clavePrivServidor) {

        this.clavePrivPropia = clavePrivServidor;
        this.clavePubAjena = clavePubAjena;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        
        System.out.println("ME INICIO HILO PERFIL");
        try {
            //Recibo el id del usuario
            SealedObject so = (SealedObject) Comunicacion.recibirObjeto(cliente);
            String idUser = (String) Seguridad.descifrar(clavePrivPropia, so);
            //Obtengo de la bd el perfil del usuario 
            Perfil p = ControladorPerfil.selectProfile(idUser);
            //Cifro y envio
            so = Seguridad.cifrar(clavePubAjena, p);
            Comunicacion.enviarObjeto(cliente, so);
            
            so = (SealedObject) Comunicacion.recibirObjeto(cliente);
            p = (Perfil) Seguridad.descifrar(clavePrivPropia, so);
            
            if (ControladorPerfil.updatePerfil(p)) {
                Utilities.enviarOrden(CodeResponse.PERFIL_ACTUALIZAR_CODE, clavePubAjena, cliente);
            }else{
                Utilities.enviarOrden(CodeResponse.ERROR_CODE, clavePubAjena, cliente);
            }
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | 
                IOException | ClassNotFoundException | IllegalBlockSizeException | BadPaddingException ex) {
            ex.printStackTrace();
        }
        System.out.println("HE MUERTO HILO PERFIL");
    }
}
