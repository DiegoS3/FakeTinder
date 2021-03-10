package hilos;

import bbdd.ControladorAfinidad;
import bbdd.ControladorPerfil;
import bbdd.ControladorUser;
import codigos.CodeResponse;
import comunicacion.Comunicacion;
import datos.Afinidad;
import datos.Amigo;
import datos.Perfil;
import datos.Preferencia;
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
public class HiloMain extends Thread {

    private Socket cliente;
    private PublicKey clavePubPropia;
    private PublicKey clavePubAjena;
    private PrivateKey clavePrivPropia;

    public HiloMain(Socket cliente, PublicKey clavePubServidor, PrivateKey clavePrivServidor) {

        this.clavePrivPropia = clavePrivServidor;
        this.clavePubPropia = clavePubServidor;
        this.cliente = cliente;
    }

    public HiloMain(PublicKey clavePubAjena, PrivateKey clavePrivServidor, Socket cliente) {

        this.clavePrivPropia = clavePrivServidor;
        this.clavePubAjena = clavePubAjena;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        System.out.println("ME INICIO HILO MAIN");
        try {

            if (clavePubAjena == null) {
                //recibe la clave publica del cliente actual
                clavePubAjena = (PublicKey) Comunicacion.recibirObjeto(cliente);
                //envia la clave publica del servidor al cliente actual
                Comunicacion.enviarObjeto(cliente, clavePubPropia);
            }

            boolean activo = true;
            SealedObject so = null;
            do {
                System.out.println("ESPERANDO ORDEN DEL CLIENTE");
                so = (SealedObject) Comunicacion.recibirObjeto(cliente);
                short orden = (short) Seguridad.descifrar(clavePrivPropia, so);

                System.out.println("ORDEN DEL CLIENTE: " + orden);

                switch (orden) {

                    case CodeResponse.LOGIN_CODE:
                        System.out.println("MODO LOGIN");
                        HiloLogin hl = new HiloLogin(cliente, clavePubAjena, clavePrivPropia);
                        activo = false;
                        hl.start();
                        break;

                    case CodeResponse.SIGNUP_CODE:
                        System.out.println("MODO SIGN UP");
                        HiloSignUp hs = new HiloSignUp(cliente, clavePubAjena, clavePrivPropia, false);
                        activo = false;
                        hs.start();
                        break;

                    case CodeResponse.PREFS_CODE:
                        System.out.println("MODO PREFERENCIAS");
                        HiloPreferencias hp = new HiloPreferencias(cliente, clavePubAjena, clavePrivPropia);
                        activo = false;
                        hp.start();
                        break;

                    case CodeResponse.ADMIN_CODE:
                        System.out.println("MODO ADMIN");
                        HiloRolAdmin hra = new HiloRolAdmin(cliente, clavePubAjena, clavePrivPropia);
                        activo = false;
                        hra.start();
                        break;

                    case CodeResponse.PERFIL_CODE:
                        System.out.println("MODO PERFIL");
                        HiloPerfil hpr = new HiloPerfil(cliente, clavePubAjena, clavePrivPropia);
                        activo = false;
                        hpr.start();
                        break;

                    case CodeResponse.AFINES_CODE:
                        System.out.println("MODO ENVIAR AFINES");
                        cargarListaAfines();
                        break;

                    case CodeResponse.AMIGUIS_CODE:
                        System.out.println("MODO HILO AMIGOS");
                        HiloAmigos ha = new HiloAmigos(cliente, clavePubAjena, clavePrivPropia);
                        activo = false;
                        ha.start();

                    case CodeResponse.SALIR_CODE:
                        activo = false;
                        break;

                    case CodeResponse.CONTINUAR_CODE:
                        //asi continua sin problemas
                        break;
                }
            } while (activo);
            System.out.println("HE MUERTO HILO MAIN");

        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            ex.printStackTrace();
        }
    }

    private void cargarListaAfines() {

        try {
            //Usuario
            SealedObject so = (SealedObject) Comunicacion.recibirObjeto(cliente);
            Usuario u = (Usuario) Seguridad.descifrar(clavePrivPropia, so);

            //Peferencias
            so = (SealedObject) Comunicacion.recibirObjeto(cliente);
            Preferencia p = (Preferencia) Seguridad.descifrar(clavePrivPropia, so);

            //Perfil
            Perfil pe = ControladorPerfil.selectProfile(u.getId());

            ArrayList<Afinidad> afines = ControladorAfinidad.obtenerUsuariosAfines(u, p, pe);
            ArrayList<SealedObject> afinesCifrados = new ArrayList<>();

            for (int i = 0; i < afines.size(); i++) {
                so = Seguridad.cifrar(clavePubAjena, afines.get(i));
                afinesCifrados.add(so);
            }
            //Enviar lista de afines cifrados
            Comunicacion.enviarObjeto(cliente, afinesCifrados);

            cargarListaAmigos(u.getId());

        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            ex.printStackTrace();
        }
    }

    private void cargarListaAmigos(String id) {
        ArrayList<Amigo> amigos = ControladorAfinidad.selectAmigos(id);
        ArrayList<SealedObject> amigosCifrados = new ArrayList<>();
        String nombre = "";
        try {
            for (int i = 0; i < amigos.size(); i++) {

                if (!amigos.get(i).getIdUser().equals(id)) {
                    nombre = ControladorUser.obtenerNombreUsuarios(amigos.get(i).getIdUser());
                } else {
                    nombre = ControladorUser.obtenerNombreUsuarios(amigos.get(i).getIdUser1());
                }
                SealedObject so = Seguridad.cifrar(clavePubAjena, nombre);
                amigosCifrados.add(so);
            }

            Comunicacion.enviarObjeto(cliente, amigosCifrados);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException | IllegalBlockSizeException ex) {
            Logger.getLogger(HiloMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
