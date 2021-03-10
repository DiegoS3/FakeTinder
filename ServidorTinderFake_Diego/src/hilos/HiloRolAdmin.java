package hilos;

import bbdd.ControladorRoles;
import bbdd.ControladorUser;
import codigos.CodeResponse;
import comunicacion.Comunicacion;
import constantes.ConstantesRoles;
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
import utilities.Utilities;

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
        boolean activo = true;
        String idUser = (String) recibirDato();
        do {
            try {
                enviarListaUsers(idUser);
                short orden = Utilities.recibirOrden(cliente, clavePrivPropia);
                SealedObject so = (SealedObject) Comunicacion.recibirObjeto(cliente);
                String idUsuario = "";
                if (orden != CodeResponse.SIGNUP_CODE && orden != CodeResponse.ADMIN_FIN_CODE) {
                    idUsuario = (String) Seguridad.descifrar(clavePrivPropia, so);
                }

                switch (orden) {

                    case CodeResponse.SIGNUP_CODE:
                        //addUser();
                        System.out.println("MODO ADD USER POR ADMIN");
                        HiloSignUp hsu = new HiloSignUp(cliente, clavePubAjena, clavePrivPropia, true);
                        activo = false;
                        hsu.start();
                        break;

                    case CodeResponse.ACTIVAR_CODE:
                        activarUser(idUsuario);
                        break;

                    case CodeResponse.DELETE_CODE:
                        deleteUser(idUsuario);
                        break;

                    case CodeResponse.ASC_CODE:
                        ascenderRol(idUsuario);
                        break;

                    case CodeResponse.DESC_CODE:
                        descenderRol(idUsuario);
                        break;

                    case CodeResponse.ADMIN_FIN_CODE:
                        HiloMain hm = new HiloMain(clavePubAjena, clavePrivPropia, cliente);
                        activo = false;
                        hm.start();
                        break;

                }
            } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException
                    | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
                ex.printStackTrace();
            }
        } while (activo);
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

    private void addUser() {
    }

    private void activarUser(String idUsuario) {
        if (ControladorUser.activarUser(idUsuario)) {
            Utilities.enviarOrden(CodeResponse.ADMIN_EXITO_CODE, clavePubAjena, cliente);
        } else {
            Utilities.enviarOrden(CodeResponse.ERROR_CODE, clavePubAjena, cliente);
        }
    }

    private void deleteUser(String idUsuario) {
        if (ControladorUser.deleteUser(idUsuario)) {
            Utilities.enviarOrden(CodeResponse.ADMIN_EXITO_CODE, clavePubAjena, cliente);
        } else {
            Utilities.enviarOrden(CodeResponse.ERROR_CODE, clavePubAjena, cliente);
        }
    }

    private void ascenderRol(String idUsuario) {
        if (ControladorRoles.modRol(idUsuario, ConstantesRoles.ROL_ID_ADMIN)) {
            Utilities.enviarOrden(CodeResponse.ADMIN_EXITO_CODE, clavePubAjena, cliente);
        } else {
            Utilities.enviarOrden(CodeResponse.ERROR_CODE, clavePubAjena, cliente);
        }

    }

    private void descenderRol(String idUsuario) {
        if (ControladorRoles.modRol(idUsuario, ConstantesRoles.ROL_ID_USER)) {
            Utilities.enviarOrden(CodeResponse.ADMIN_EXITO_CODE, clavePubAjena, cliente);
        } else {
            Utilities.enviarOrden(CodeResponse.ERROR_CODE, clavePubAjena, cliente);
        }
    }

}
