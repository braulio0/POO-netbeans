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
    private int     NIDHORA;
    private String  CDESCHR;
    private String  CSTATUS;
    private int     NDIASEM; 
    private String  CHORENT;
    private String  CHORSAL;
    private String  CMOTIVO;
    private String  DFECHNL;
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
    public String getCMOTIVO() {
        return CMOTIVO;
    }

    /**
     * @param CMOTIVO the CMOTIVO to set
     */
    public void setCMOTIVO(String CMOTIVO) {
        this.CMOTIVO = CMOTIVO;
    }
    public void setDFECHNL(String DFECHNL) {
        this.DFECHNL = DFECHNL;
    }
    /**
     * @return the CDESCHR
     */
    public String getDFECHNL() {
        return DFECHNL;
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
    
    public int getNDIASEM() {
        return NDIASEM;
    }

    /**
     * @param NDIASEM the NIDHORA to set
     */
    public void setNDIASEM(int NDIASEM) {
        this.NDIASEM = NDIASEM;
    }

    /**
     * @return the CHORENT
     */
    public String getCHORENT() {
        return CHORENT;
    }

    /**
     * @param CDESCHR the CDESCHR to set
     */
    public void setCHORENT(String CHORENT) {
        this.CHORENT = CHORENT;
    }

    /**
     * @return the CHORSAL
     */
    public String getCHORSAL() {
        return CHORSAL;
    }

    /**
     * @param CHORSAL the CSTATUS to set
     */
    public void setCHORSAL(String CHORSAL) {
        this.CHORSAL = CHORSAL;
    }
}
