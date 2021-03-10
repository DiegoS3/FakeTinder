/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import static bbdd.ConexionBD.abrirConexion;
import static bbdd.ConexionBD.cerrarConexion;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import constantes.ConstantesBD;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class ControladorRoles {
    
    private static String sentencia;
    private static ResultSet rs;
    
    public synchronized static void addRolUser(String id, Connection conexion) {

        sentencia = "INSERT INTO " + ConstantesBD.TABLAROLES_USERS + " (idRol, idUser) "
                + "values('2','" + id + "')";
        if (conexion != null) {
            try (Statement st = (Statement) conexion.createStatement()) {
                if (st.executeUpdate(sentencia) == 1) {
                    System.out.println("ROL REGISTRADO");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        }
    }
    
    public static boolean modRol(String idUsuario, int idRol){
        boolean exito = false;
        Connection conexion = abrirConexion();

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "UPDATE " + ConstantesBD.TABLAROLES_USERS + " SET idRol = " + idRol + " WHERE idUser = '" + idUsuario + "'";

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
    
    private static String selectIdRol(String id, Connection conexion) {
        String idRol = "";
        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "SELECT idRol FROM " + ConstantesBD.TABLAROLES_USERS + " WHERE idUser LIKE '" + id + "'";
                    rs = st.executeQuery(sentencia);
                    while (rs.next()) {
                        idRol = rs.getString(1);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return idRol;
    }

    /**
     *
     * @param idUser
     * @return
     */
    public synchronized static String selectTypeUser(String idUser) {

        String tipoUser = "";
        Connection conexion = abrirConexion();
        String idRol = selectIdRol(idUser, conexion);

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "SELECT name FROM " + ConstantesBD.TABLAROLES + " WHERE id = '" + idRol + "'";
                    rs = st.executeQuery(sentencia);
                    while (rs.next()) {
                        tipoUser = rs.getString(1);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                cerrarConexion(conexion);
            }
        }
        return tipoUser;
    }
    
    public synchronized static boolean delRolUser(String idUser){
        boolean exito = false;
        Connection conexion = abrirConexion();
        
        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "DELETE FROM " + ConstantesBD.TABLAROLES_USERS + " WHERE idUser = '" + idUser + "'";
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
