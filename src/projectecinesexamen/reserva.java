/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectecinesexamen;

import java.sql.Time;

/**
 *
 * @author dani_
 */
public class reserva {
    private int idReserva;
    private int idCine;
    private int idSala;
    private int entrades;
    private Time horainici;

    /**
     * @return the idReserva
     */
    public int getIdReserva() {
        return idReserva;
    }

    /**
     * @param idReserva the idReserva to set
     */
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * @return the idCine
     */
    public int getIdCine() {
        return idCine;
    }

    /**
     * @param idCine the idCine to set
     */
    public void setIdCine(int idCine) {
        this.idCine = idCine;
    }

    /**
     * @return the idSala
     */
    public int getIdSala() {
        return idSala;
    }

    /**
     * @param idSala the idSala to set
     */
    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    /**
     * @return the entrades
     */
    public int getEntrades() {
        return entrades;
    }

    /**
     * @param entrades the entrades to set
     */
    public void setEntrades(int entrades) {
        this.entrades = entrades;
    }

    /**
     * @return the horainici
     */
    public Time getHorainici() {
        return horainici;
    }

    /**
     * @param horainici the horainici to set
     */
    public void setHorainici(Time horainici) {
        this.horainici = horainici;
    }
}
