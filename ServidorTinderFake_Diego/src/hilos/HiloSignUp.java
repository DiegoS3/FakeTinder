/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import bbdd.ControladorPerfil;
import bbdd.ControladorUser;
import codigos.CodeResponse;
import comunicacion.Comunicacion;
import datos.Perfil;
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
public class HiloSignUp extends Thread {

    private Socket cliente;
    private PublicKey clavePubAjena;
    private PrivateKey clavePrivPropia;
    private boolean modoAdmin;

    public HiloSignUp(Socket cliente, PublicKey clavePubAjena, PrivateKey clavePrivServidor, boolean modoAdmin) {

        this.clavePrivPropia = clavePrivServidor;
        this.clavePubAjena = clavePubAjena;
        this.cliente = cliente;
        this.modoAdmin = modoAdmin;
    }

    @Override
    public void run() {
        System.out.println("ME INICIO HILO SIGN UP");
        short orden = Utilities.recibirOrden(cliente, clavePrivPropia);
        if (orden != CodeResponse.CONTINUAR_CODE) {
            comprobarSignUp();
            if (modoAdmin) {
                HiloRolAdmin hra = new HiloRolAdmin(cliente, clavePubAjena, clavePrivPropia);
                hra.start();

            } else {
                HiloMain hm = new HiloMain(clavePubAjena, clavePrivPropia, cliente);
                hm.start();
            }
        }

        System.out.println("HE MUERTO HILO SIGN UP");

    }

    private void comprobarSignUp() {
        System.out.println("ESPERANDO USUARIO PARA REGISTRAR");
        Usuario u = (Usuario) recibirObjeto();

        System.out.println("REGISTRANDO USUARIO");
        if (ControladorUser.registrarUsuario(u)) {
            Utilities.enviarOrden(CodeResponse.SIGNUP_CORRECTO_CODE, clavePubAjena, cliente);

            System.out.println("CREANDO PERFIL EN BD");
            Perfil p = (Perfil) recibirObjeto();
            if (ControladorPerfil.crearPerfil(p)) {
                Utilities.enviarOrden(CodeResponse.SIGNUP_CORRECTO_CODE, clavePubAjena, cliente);
            } else {
                Utilities.enviarOrden(CodeResponse.ERROR_CODE, clavePubAjena, cliente);
            }
        } else {
            Utilities.enviarOrden(CodeResponse.ERROR_CODE, clavePubAjena, cliente);
        }

    }

    private Object recibirObjeto() {
        Object u = null;

        try {
            System.out.println("ENTRANDO EN RECIBIR OBJETO");
            SealedObject so = (SealedObject) Comunicacion.recibirObjeto(cliente);
            u = Seguridad.descifrar(clavePrivPropia, so);
            System.out.println("RECIBIDO OBJETO DEL CLIENTE SIGN UP");

        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException
                | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            ex.printStackTrace();
        }

        return u;
    }

}
