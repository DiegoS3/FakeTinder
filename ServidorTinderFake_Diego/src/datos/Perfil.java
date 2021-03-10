/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;

/**
 *
 * @author Diego
 */
public class Perfil  implements Serializable{

    private String iduser;
    private String username;
    private String sexo;
    private int edad;

    public Perfil() {
    }

    public Perfil(String iduser, String username, String sexo, int edad) {
        this.iduser = iduser;
        this.username = username;
        this.sexo = sexo;
        this.edad = edad;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
