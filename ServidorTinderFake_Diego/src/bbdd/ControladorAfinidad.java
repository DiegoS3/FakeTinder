package bbdd;

import static bbdd.ConexionBD.abrirConexion;
import static bbdd.ConexionBD.cerrarConexion;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import constantes.ConstantesBD;
import constantes.ConstantesPreferencias;
import datos.Afinidad;
import datos.Amigo;
import datos.Perfil;
import datos.Preferencia;
import datos.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Diego
 */
public class ControladorAfinidad {

    private static String sentencia;

    public synchronized static ArrayList<Afinidad> obtenerUsuariosAfines(Usuario user, Preferencia p, Perfil pe) {

        ArrayList<Afinidad> listaUsersAfines = new ArrayList<Afinidad>();
        Connection conexion = abrirConexion();

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = sentencia(p, user, pe);
                    ResultSet rs = st.executeQuery(sentencia);
                    while (rs.next()) {
                        Afinidad u = new Afinidad();
                        u.setIdUser(rs.getString(1));
                        u.setNombre(rs.getString(2));
                        u.setEmail(rs.getString(3));
                        u.setSexo(rs.getString(4));
                        u.setEdad(rs.getInt(5));
                        listaUsersAfines.add(u);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                cerrarConexion(conexion);
            }
        }
        return listaUsersAfines;
    }

    private static String sentencia(Preferencia p, Usuario user, Perfil pe) {
        String sexo = "";
        String sentenciaC = "";
        if (p.getInteres().equals(ConstantesPreferencias.INTERES_HOM)) {
            sexo = ConstantesPreferencias.SEXO_M;
        } else {
            sexo = ConstantesPreferencias.SEXO_F;
        }
        if (p.getInteres().equals(ConstantesPreferencias.INTERES_AMB)) {
            sentenciaC = "SELECT u.id, u.nombre, u.email, pe.sexo, pe.edad FROM " + ConstantesBD.TABLAUSUARIOS
                    + " u JOIN " + ConstantesBD.TABLAPREFES
                    + " p ON u.id = p.idUser"
                    + " JOIN " + ConstantesBD.TABLAPERFIL
                    + " pe ON u.id = pe.idUser"
                    + " WHERE u.id NOT LIKE '" + user.getId()
                    + "' AND (pe.edad BETWEEN (" + pe.getEdad() + ")"
                    + " AND (" + (pe.getEdad() + 10) + ")"
                    + " OR pe.edad BETWEEN (" + (pe.getEdad() - 10) + ")"
                    + " AND (" + pe.getEdad() + "))"
                    + " AND p.tiporelacion LIKE '" + p.getTiporelacion()
                    + "' AND (p.thijos = " + p.isQhijos()
                    + " OR p.qhijos = " + p.isQhijos() + ")"
                    + " AND (p.arte BETWEEN (" + p.getArte() + ")"
                    + " AND (" + (p.getArte() + 10) + ")"
                    + " OR p.arte BETWEEN (" + (p.getArte() - 20) + ")"
                    + " AND (" + p.getArte() + "))"
                    + " AND (p.deporte BETWEEN (" + p.getDeporte() + ")"
                    + " AND (" + (p.getDeporte() + 15) + ")"
                    + " OR p.deporte BETWEEN (" + (p.getDeporte() - 20) + ")"
                    + " AND (" + p.getDeporte() + "))"
                    + " AND (p.politica BETWEEN (" + p.getPolitica() + ")"
                    + " AND (" + (p.getPolitica() + 5) + ")"
                    + " OR p.politica BETWEEN (" + (p.getPolitica() - 10) + ")"
                    + " AND (" + p.getPolitica() + "))";
        } else {
            sentenciaC = "SELECT u.id, u.nombre, u.email, pe.sexo, pe.edad FROM " + ConstantesBD.TABLAUSUARIOS
                    + " u JOIN " + ConstantesBD.TABLAPREFES
                    + " p ON u.id = p.idUser"
                    + " JOIN " + ConstantesBD.TABLAPERFIL
                    + " pe ON u.id = pe.idUser"
                    + " WHERE u.id NOT LIKE '" + user.getId()
                    + "' AND pe.sexo LIKE '" + sexo
                    + "' AND (pe.edad BETWEEN (" + pe.getEdad() + ")"
                    + " AND (" + (pe.getEdad() + 10) + ")"
                    + " OR pe.edad BETWEEN (" + (pe.getEdad() - 10) + ")"
                    + " AND (" + pe.getEdad() + "))"
                    + " AND p.tiporelacion LIKE '" + p.getTiporelacion()
                    + "' AND (p.thijos = " + p.isQhijos()
                    + " OR p.qhijos = " + p.isQhijos() + ")"
                    + " AND (p.arte BETWEEN (" + p.getArte() + ")"
                    + " AND (" + (p.getArte() + 10) + ")"
                    + " OR p.arte BETWEEN (" + (p.getArte() - 20) + ")"
                    + " AND (" + p.getArte() + "))"
                    + " AND (p.deporte BETWEEN (" + p.getDeporte() + ")"
                    + " AND (" + (p.getDeporte() + 15) + ")"
                    + " OR p.deporte BETWEEN (" + (p.getDeporte() - 20) + ")"
                    + " AND (" + p.getDeporte() + "))"
                    + " AND (p.politica BETWEEN (" + p.getPolitica() + ")"
                    + " AND (" + (p.getPolitica() + 5) + ")"
                    + " OR p.politica BETWEEN (" + (p.getPolitica() - 10) + ")"
                    + " AND (" + p.getPolitica() + "))";
        }
        return sentenciaC;
    }

    public static void insertAmigo(String id, String idU) {
        Connection conexion = abrirConexion();

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {

                    sentencia = "INSERT INTO " + ConstantesBD.TABLAAMIGOS + " (idUser, idUser1)"
                            + "values('" + id + "','" + idU + "')";
                    if (st.executeUpdate(sentencia) == 1) {
                        System.out.println("AMISTAD REGISTRADA");
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                cerrarConexion(conexion);
            }
        }
    }
    
    public static Amigo selectAmigo(String id, String idU) {
        Connection conexion = abrirConexion();
        Amigo a = null;

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {

                    sentencia = "SELECT idUser, idUser1 FROM " + ConstantesBD.TABLAAMIGOS + " WHERE idUser like '" + id
                            + "' AND idUser1 like '" + idU + "'" + " or idUser like '" + idU
                            + "' AND idUser1 like '" + id + "'";
                    ResultSet rs = st.executeQuery(sentencia);

                    while (rs.next()) {
                        a = new Amigo();
                        a.setIdUser(rs.getString(1));
                        a.setIdUser1(rs.getString(2));
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                cerrarConexion(conexion);
            }
        }
        return a;
    }
    
    public static ArrayList<Amigo> selectAmigos(String id) {
        Connection conexion = abrirConexion();
        ArrayList<Amigo> amigos = new ArrayList<>();

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {

                    sentencia = "SELECT idUser, idUser1 FROM " + ConstantesBD.TABLAAMIGOS + " WHERE idUser like '" + id
                            + "' OR idUser1 like '" + id + "'";
                    ResultSet rs = st.executeQuery(sentencia);

                    while (rs.next()) {
                        Amigo a = new Amigo();
                        a.setIdUser(rs.getString(1));
                        a.setIdUser1(rs.getString(2));
                        amigos.add(a);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                cerrarConexion(conexion);
            }
        }
        return amigos;
    }

    public static void updateAmigo(String id, String idU) {
        Connection conexion = abrirConexion();

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {

                    sentencia = "UPDATE " + ConstantesBD.TABLAAMIGOS + " SET ambos = true";
                    if (st.executeUpdate(sentencia) == 1) {
                        System.out.println("AMISTAD ACTUALIZADA");
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                cerrarConexion(conexion);
            }
        }
    }
}
