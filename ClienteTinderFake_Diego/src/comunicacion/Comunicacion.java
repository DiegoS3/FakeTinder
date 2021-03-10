
package comunicacion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Diego
 */
public class Comunicacion {
    
    public static void enviarObjeto(Socket cliente, Object o) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
        oos.writeObject(o);
    }

    public static Object recibirObjeto(Socket cliente) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

        return ois.readObject();
    }

    public static void enviarString(Socket cliente, String s) throws IOException {
        DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
        dos.writeUTF(s);
    }

    public static String recibirString(Socket cliente) throws IOException {
        DataInputStream dis = new DataInputStream(cliente.getInputStream());

        return dis.readUTF();
    }
    
}
