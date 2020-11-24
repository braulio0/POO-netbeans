/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author elias
 */
public class Horario {
    private int    NIDHORA;
    private String CDESCHR;
    private String CSTATUS;

    /**
     * @return the NIDHORA
     */
    public int getNIDHORA() {
        return NIDHORA;
    }

    /**
     * @param NIDHORA the NIDHORA to set
     */
    public void setNIDHORA(int NIDHORA) {
        this.NIDHORA = NIDHORA;
    }

    /**
     * @return the CDESCHR
     */
    public String getCDESCHR() {
        return CDESCHR;
    }

    /**
     * @param CDESCHR the CDESCHR to set
     */
    public void setCDESCHR(String CDESCHR) {
        this.CDESCHR = CDESCHR;
    }

    /**
     * @return the CSTATUS
     */
    public String getCSTATUS() {
        return CSTATUS;
    }

    /**
     * @param CSTATUS the CSTATUS to set
     */
    public void setCSTATUS(String CSTATUS) {
        this.CSTATUS = CSTATUS;
    }
}
