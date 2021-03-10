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
public class Preferencia  implements Serializable{
    private String idUser;
    private String tiporelacion;
    private String interes;
    private int arte;
    private int deporte;
    private int politica;
    private boolean thijos;
    private boolean qhijos;

    public Preferencia() {
    }

    public Preferencia(String idUser, String tiporelacion, String interes, int arte, int deporte, int politica, boolean thijos, boolean qhijos) {
        this.idUser = idUser;
        this.tiporelacion = tiporelacion;
        this.interes = interes;
        this.arte = arte;
        this.deporte = deporte;
        this.politica = politica;
        this.thijos = thijos;
        this.qhijos = qhijos;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTiporelacion() {
        return tiporelacion;
    }

    public void setTiporelacion(String tiporelacion) {
        this.tiporelacion = tiporelacion;
    }

    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }

    public int getArte() {
        return arte;
    }

    public void setArte(int arte) {
        this.arte = arte;
    }

    public int getDeporte() {
        return deporte;
    }

    public void setDeporte(int deporte) {
        this.deporte = deporte;
    }

    public int getPolitica() {
        return politica;
    }

    public void setPolitica(int politica) {
        this.politica = politica;
    }

    public boolean isThijos() {
        return thijos;
    }

    public void setThijos(boolean thijos) {
        this.thijos = thijos;
    }

    public boolean isQhijos() {
        return qhijos;
    }

    public void setQhijos(boolean qhijos) {
        this.qhijos = qhijos;
    }
}
