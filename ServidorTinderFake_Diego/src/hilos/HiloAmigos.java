
package hilos;

import bbdd.ControladorAfinidad;
import codigos.CodeResponse;
import comunicacion.Comunicacion;
import datos.Amigo;
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
public class HiloAmigos extends Thread{
    
    private Socket cliente;
    private PublicKey clavePubAjena;
    private PrivateKey clavePrivPropia;

    public HiloAmigos(Socket cliente, PublicKey clavePubAjena, PrivateKey clavePrivServidor) {

        this.clavePrivPropia = clavePrivServidor;
        this.clavePubAjena = clavePubAjena;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        
        try {
            SealedObject sos = (SealedObject) Comunicacion.recibirObjeto(cliente);
            String[] ids = (String[]) Seguridad.descifrar(clavePrivPropia, sos);
            
            Amigo a = ControladorAfinidad.selectAmigo(ids[0], ids[1]);
            
            if (a != null && a.isAmbos()) {
                // ya son amigos
                Utilities.enviarOrden(CodeResponse.YA_SON_CODE, clavePubAjena, cliente);
            }else if(a != null && !a.isAmbos()){
                if (a.getIdUser().equals(ids[0])) {
                    //espera a que te de amistad
                    Utilities.enviarOrden(CodeResponse.ESPERA_AC_CODE, clavePubAjena, cliente);
                }else{
                    ControladorAfinidad.updateAmigo(ids[0], ids[1]);
                    Utilities.enviarOrden(CodeResponse.ACEPTADA_CODE, clavePubAjena, cliente);
                }
            }
            else{
                Utilities.enviarOrden(CodeResponse.CREADA_CODE, clavePubAjena, cliente);
                ControladorAfinidad.insertAmigo(ids[0], ids[1]);
            }
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException 
                | ClassNotFoundException | IllegalBlockSizeException | BadPaddingException ex) {
            ex.printStackTrace();
        }
        
        HiloMain hm = new HiloMain(clavePubAjena, clavePrivPropia, cliente);
        hm.start();
    }
}
