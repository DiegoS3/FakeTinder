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
public class Amigo implements Serializable{
    
    private String idUser;
    private String idUser1;
    private boolean ambos;

    public Amigo() {
    }

    public Amigo(String idUser, String idUser1, boolean ambos) {
        this.idUser = idUser;
        this.idUser1 = idUser1;
        this.ambos = ambos;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUser1() {
        return idUser1;
    }

    public void setIdUser1(String idUser1) {
        this.idUser1 = idUser1;
    }

    public boolean isAmbos() {
        return ambos;
    }

    public void setAmbos(boolean ambos) {
        this.ambos = ambos;
    }
}
