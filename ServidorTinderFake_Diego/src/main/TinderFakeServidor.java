
package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import constantes.Constantes;
import hilos.HiloMain;
import seguridad.Seguridad;

/**
 *
 * @author Diego
 */
public class TinderFakeServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        
        ServerSocket servidor;
        servidor = new ServerSocket(Constantes.PORT_SERVER);
        Object[] claves = Seguridad.generarClaves();
        PrivateKey clavePrivPropia = (PrivateKey) claves[Constantes.PRIVATE_KEY];
        PublicKey clavePubPropia = (PublicKey) claves[Constantes.PUBLIC_KEY];
        
        
        while(true){
            Socket cliente = servidor.accept();
            HiloMain hc = new HiloMain(cliente, clavePubPropia, clavePrivPropia);
            hc.start();
        }
    }
    
}
