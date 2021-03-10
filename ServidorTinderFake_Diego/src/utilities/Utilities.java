/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import comunicacion.Comunicacion;
import java.io.IOException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import seguridad.Seguridad;

/**
 *
 * @author Diego
 */
public class Utilities {

    public static void enviarOrden(short orden, PublicKey claveAjena, Socket cliente) {
        try {
            SealedObject so = Seguridad.cifrar(claveAjena, orden);
            Comunicacion.enviarObjeto(cliente, so);
            System.out.println("Envio Orden " + orden);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException | IllegalBlockSizeException ex) {
            ex.printStackTrace();
        }
    }

    public static short recibirOrden(Socket cliente, PrivateKey clavePropia) {
        short orden = 0;
        try {

            SealedObject so = (SealedObject) Comunicacion.recibirObjeto(cliente);
            orden = (short) Seguridad.descifrar(clavePropia, so);
        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            ex.printStackTrace();
        }

        return orden;
    }

}
