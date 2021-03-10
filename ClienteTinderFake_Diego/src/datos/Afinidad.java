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
public class Afinidad implements Serializable {
    
    private String idUser;
    private String nombre;
    private String email;
    private String sexo;
    private int edad;

    public Afinidad(String idUser, String nombre, String email, String sexo, int edad) {
        this.idUser = idUser;
        this.nombre = nombre;
        this.email = email;
        this.sexo = sexo;
        this.edad = edad;
    }

    public Afinidad() {
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
