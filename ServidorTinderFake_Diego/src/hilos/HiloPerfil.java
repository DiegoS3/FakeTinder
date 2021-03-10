
package hilos;

import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;

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
        
    }
    
}
