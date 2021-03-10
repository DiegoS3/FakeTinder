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
import datos.Perfil;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class ControladorPerfil {

    private static String sentencia;

    /**
     *
     * @param id
     * @return
     */
    public static boolean profileExists(String id) {
        boolean exist = false;
        Connection conexion = abrirConexion();

        if (conexion != null) {

            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "SELECT * FROM " + ConstantesBD.TABLAPERFIL + " WHERE idUser = '" + id + "'";

                    ResultSet rs = st.executeQuery(sentencia);
                    if (rs.next()) {
                        exist = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarConexion(conexion);
            }
        }
        return exist;
    }

    public static boolean crearPerfil(Perfil p) {
        boolean exist = false;
        Connection conexion = abrirConexion();

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "INSERT INTO " + ConstantesBD.TABLAPERFIL + " (idUser, username, sexo, edad) "
                                + "values('" + p.getIduser() + "','" + p.getUsername() + "','" + p.getSexo()
                                + "'," + p.getEdad() + ")";

                    if (st.executeUpdate(sentencia) == 1) {
                        exist = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarConexion(conexion);
            }
        }
        return exist;
    }
}
