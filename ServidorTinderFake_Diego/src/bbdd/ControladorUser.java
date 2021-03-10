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
import datos.Usuario;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class ControladorUser {

    private static String sentencia;
    private static ResultSet rs;

    private static boolean usuarioExists(String email) {
        boolean exist = false;
        Connection conexion = abrirConexion();

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "SELECT * FROM " + ConstantesBD.TABLAUSUARIOS + " WHERE email = '" + email + "'";

                    rs = st.executeQuery(sentencia);
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

    public synchronized static boolean registrarUsuario(Usuario u) {
        boolean exito = false;
        Connection conexion = abrirConexion();

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    if (!usuarioExists(u.getEmail())) {
                        sentencia = "INSERT INTO " + ConstantesBD.TABLAUSUARIOS + " (id, nombre, email, password) "
                                + "values('" + u.getId() + "','" + u.getNombre() + "','" + u.getEmail()
                                + "','" + u.getPassword() + "')";

                        if (st.executeUpdate(sentencia) == 1) {
                            System.out.println("USUARIO REGISTRADO");
                            exito = true;
                            ControladorRoles.addRolUser(u.getId(), conexion);
                        }
                    }
                }

            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                cerrarConexion(conexion);
            }
        }
        return exito;
    }

    public synchronized static Usuario comprobarLogin(String email, String pwd) {
        Usuario u = null;
        Connection conexion = abrirConexion();

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "SELECT * FROM " + ConstantesBD.TABLAUSUARIOS + " WHERE email LIKE '" + email
                            + "' and password LIKE '" + pwd + "'";

                    rs = st.executeQuery(sentencia);

                    while (rs.next()) {
                        u = new Usuario();
                        u.setId(rs.getString("id"));
                        u.setNombre(rs.getString("nombre"));
                        u.setEmail(rs.getString("email"));
                        u.setPassword(rs.getString("password"));
                        u.setActivado(rs.getBoolean("activado"));
                    }
                    if (u != null) {
                        String rol = ControladorRoles.selectTypeUser(u.getId());
                        u.setRol(rol);
                    }

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                cerrarConexion(conexion);
            }
        }
        return u;
    }

    public static boolean activarUser(String id) {
        boolean exito = false;
        Connection conexion = abrirConexion();

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "UPDATE " + ConstantesBD.TABLAUSUARIOS + " SET activado = true WHERE id = '" + id + "'";

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

    public static boolean deleteUser(String id) {
        boolean exito = false;
        Connection conexion = abrirConexion();

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "DELETE FROM " + ConstantesBD.TABLAUSUARIOS + " WHERE id = '" + id + "'";
                    //Eliminamos todos los registros del usuario en la BD
                    ControladorPerfil.delPerfil(id);
                    ControladorPrefrences.delPreferencia(id);
                    ControladorRoles.delRolUser(id);
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

    public synchronized static ArrayList<Usuario> obtenerUsuarios() {

        ArrayList<Usuario> listaUsers = new ArrayList<Usuario>();
        Connection conexion = abrirConexion();

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "SELECT * FROM " + ConstantesBD.TABLAUSUARIOS ;
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
    
    public synchronized static String obtenerNombreUsuarios(String id) {

        Connection conexion = abrirConexion();
        String nombre = "";

        if (conexion != null) {
            try {
                try (Statement st = (Statement) conexion.createStatement()) {
                    sentencia = "SELECT * FROM " + ConstantesBD.TABLAUSUARIOS + " WHERE id like '" + id + "'";
                    ResultSet rs = st.executeQuery(sentencia);
                    while (rs.next()) {
                        nombre = rs.getString("nombre");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                cerrarConexion(conexion);
            }
        }
        return nombre;
    }

}
