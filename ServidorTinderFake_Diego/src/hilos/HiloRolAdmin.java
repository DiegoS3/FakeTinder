package hilos;

import bbdd.ControladorUser;
import comunicacion.Comunicacion;
import datos.Usuario;
import java.io.IOException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import seguridad.Seguridad;

/**
 *
 * @author Diego
 */
public class HiloRolAdmin extends Thread {

    private Socket cliente;
    private PublicKey clavePubAjena;
    private PrivateKey clavePrivPropia;

    public HiloRolAdmin(Socket cliente, PublicKey clavePubAjena, PrivateKey clavePrivServidor) {

        this.clavePrivPropia = clavePrivServidor;
        this.clavePubAjena = clavePubAjena;
        this.cliente = cliente;
    }

    @Override
    public void run() {

        String idUser = (String) recibirDato();
        enviarListaUsers(idUser);

        HiloMain hm = new HiloMain(clavePubAjena, clavePrivPropia, cliente);
        hm.start();
    }

    private void enviarListaUsers(String id) {
        try {
            ArrayList<Usuario> listaUsers = ControladorUser.obtenerUsuarios(id);
            ArrayList<SealedObject> listaObjCifrados = new ArrayList<>();
            //Cifro cada objeto y lo meto en una lista para intentar que no de problemas
            //al cifrar un objeto mas pesado
            for (int i = 0; i < listaUsers.size(); i++) {
                SealedObject so = Seguridad.cifrar(clavePubAjena, listaUsers.get(i));
                listaObjCifrados.add(so);
            }
            
            Comunicacion.enviarObjeto(cliente, listaObjCifrados);
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException 
                | IOException | IllegalBlockSizeException ex) {
            ex.printStackTrace();
        }
    }

    private Object recibirDato() {
        Object o = null;
        try {
            SealedObject so = (SealedObject) Comunicacion.recibirObjeto(cliente);
            o = Seguridad.descifrar(clavePrivPropia, so);
        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            ex.printStackTrace();
        }
        return o;
    }

}
