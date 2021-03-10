package bbdd;

import static bbdd.ConexionBD.abrirConexion;
import static bbdd.ConexionBD.cerrarConexion;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import constantes.ConstantesBD;
import constantes.ConstantesPreferencias;
import datos.Perfil;
import datos.Preferencia;
import datos.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class ControladorAfinidad {

    private static String sentencia;

    public synchronized static ArrayList<Usuario> obtenerUsuarios(Usuario user, Preferencia p, Perfil pe) {

        ArrayList<Usuario> listaUsers = new ArrayList<Usuario>();
        Connection conexion = abrirConexion();

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = sentencia(p, user, pe);
                    ResultSet rs = st.executeQuery(sentencia);
                    while (rs.next()) {
                        Usuario u = new Usuario();
                        u.setId(rs.getString("id"));
                        u.setNombre(rs.getString("nombre"));
                        u.setEmail(rs.getString("email"));
                        u.setPassword(rs.getString("password"));
                        u.setActivado(rs.getBoolean("activado"));
                        String rol = ControladorRoles.selectTypeUser(u.getId());
                        u.setRol(rol);
                        listaUsers.add(u);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                cerrarConexion(conexion);
            }
        }
        return listaUsers;
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
            sentenciaC = "SELECT * FROM " + ConstantesBD.TABLAUSUARIOS
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
                    + " AND (" + p.getArte() + ")"
                    + " AND (p.deporte BETWEEN (" + p.getDeporte() + ")"
                    + " AND (" + (p.getDeporte() + 15) + ")"
                    + " OR p.deporte BETWEEN (" + (p.getDeporte() - 20) + ")"
                    + " AND (" + p.getDeporte() + "))"
                    + " AND (p.politica BETWEEN (" + p.getPolitica() + ")"
                    + " AND (" + (p.getPolitica() + 5) + ")"
                    + " OR p.politica BETWEEN (" + (p.getPolitica() - 10) + ")"
                    + " AND (" + p.getPolitica() + "))";
        } else {
            sentenciaC = "SELECT * FROM " + ConstantesBD.TABLAUSUARIOS
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
                    + " AND (" + p.getArte() + ")"
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
}
