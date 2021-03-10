
package bbdd;

import static bbdd.ConexionBD.abrirConexion;
import static bbdd.ConexionBD.cerrarConexion;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import constantes.ConstantesBD;
import datos.Preferencia;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Diego
 */
public class ControladorPrefrences {
    
    private static String sentencia;
    private static ResultSet rs;
    
    /**
     *
     * @param prefs
     */
    public synchronized static boolean insertPreferences(Preferencia prefs) {
        boolean exito = false;
        Connection conexion = abrirConexion();
        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "INSERT INTO " + ConstantesBD.TABLAPREFES + " (idUser, tiporelacion, interes, arte, deporte, politica, thijos, qhijos) "
                            + "values('" + prefs.getIdUser() + "','" + prefs.getTiporelacion()+ "','" + prefs.getInteres() + "','" + prefs.getArte() + "','" + prefs.getDeporte()
                            + "','" + prefs.getPolitica() + "'," + prefs.isThijos()+ "," + prefs.isQhijos()+ ")";
                    if (st.executeUpdate(sentencia) == 1) {
                        exito = true;
                        System.out.println("PREFERENCIA REGISTRADA");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarConexion(conexion);
            }
        }
        return exito;
    }

    public synchronized static boolean updatePreferences(Preferencia prefs) {
        boolean exito = false;

        Connection conexion = abrirConexion();
        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "UPDATE " + ConstantesBD.TABLAPREFES + " SET arte = " + prefs.getArte() + ", deporte = "
                            + prefs.getDeporte() + ", politica = " + prefs.getPolitica() + ", thijos = " + prefs.isThijos()
                            + ", qhijos = " + prefs.isQhijos() + ", tiporelacion = '" + prefs.getTiporelacion() + "', interes = '" + prefs.getInteres() + "'";

                    if (st.executeUpdate(sentencia) == 1) {
                        exito = true;
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarConexion(conexion);
            }

        }
        return exito;
    }

    /**
     *
     * @param idUser
     * @return
     */
    public synchronized static Preferencia obtenerPreferences(String idUser) {
        Preferencia prefs = null;

        Connection conexion = abrirConexion();
        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "SELECT * FROM " + ConstantesBD.TABLAPREFES + " WHERE idUser like '" + idUser + "'";
                    rs = st.executeQuery(sentencia);

                    while (rs.next()) {
                        prefs = new Preferencia();
                        prefs.setIdUser(rs.getString("idUser"));
                        prefs.setArte(rs.getInt("arte"));
                        prefs.setDeporte(rs.getInt("deporte"));
                        prefs.setPolitica(rs.getInt("politica"));
                        prefs.setThijos(rs.getBoolean("thijos"));
                        prefs.setQhijos(rs.getBoolean("qhijos"));
                        prefs.setTiporelacion(rs.getString("tiporelacion"));
                        prefs.setInteres(rs.getString("interes"));
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                cerrarConexion(conexion);
            }
        }
        return prefs;
    }
    
    public synchronized static boolean delPreferencia(String idUser){
        boolean exito = false;
        Connection conexion = abrirConexion();
        
        if (conexion != null) {            
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "DELETE FROM " + ConstantesBD.TABLAPREFES + " WHERE idUser = '" + idUser + "'";
                    if (st.executeUpdate(sentencia) == 1) {
                        exito = true;
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                cerrarConexion(conexion);
            }
        }
        return exito;
    }
}
