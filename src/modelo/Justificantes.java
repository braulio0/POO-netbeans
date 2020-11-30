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
public class Justificantes {
    private int NIDTPJU;
    private String CDESJUS;
    private String CSTATUS;
    
    public Justificantes(){
        
    }
    /**
     * @return the NIDTPJU
     */
    public int getNIDTPJU() {
        return NIDTPJU;
    }

    /**
     * @param NIDTPJU the NIDTPJU to set
     */
    public void setNIDTPJU(int NIDTPJU) {
        this.NIDTPJU = NIDTPJU;
    }

    /**
     * @return the CDESJUS
     */
    public String getCDESJUS() {
        return CDESJUS;
    }

    /**
     * @param CDESJUS the CDESJUS to set
     */
    public void setCDESJUS(String CDESJUS) {
        this.CDESJUS = CDESJUS;
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
