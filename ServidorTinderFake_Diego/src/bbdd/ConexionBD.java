/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import com.mysql.jdbc.Connection;
import constantes.ConstantesBD;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class ConexionBD {

    public static Connection abrirConexion() {
        try {
            String URL_BD = "";
            try {
                String controlador = "com.mysql.jdbc.Driver";
                //String controlador = "org.mariadb.jdbc.Driver";
                Class.forName(controlador);
                URL_BD = "jdbc:mysql://localhost/" + ConstantesBD.BD;

                //URL_BD = "jdbc:mariadb://localhost/" + ConstantesBD.BD;
                //conex = (Connection) java.sql.DriverManager.getConnection(URL_BD, ConstantesBD.USUARIO, ConstantesBD.PASSWD);
                //Sentencia_SQL = (Statement) conex.createStatement();
                //System.out.println("Conexión realizada con éxito");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return (Connection) DriverManager.getConnection(URL_BD, ConstantesBD.USUARIO, ConstantesBD.PASSWD);
        } catch (SQLException ex) {
        }
        return null;
    }

    public static void cerrarConexion(Connection conex) {
        try {
            conex.close();
            conex = null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de Desconexion", JOptionPane.ERROR_MESSAGE);
        }
    }

}
