package POJO;
// Generated 02-17-2017 07:23:49 AM by Hibernate Tools 4.3.1



/**
 * ExamenHecesCm generated by hbm2java
 */
public class ExamenHecesCm  implements java.io.Serializable {


     private int idExamenHeces;
     private ExamenHeces examenHeces;
     private String ascaris;
     private String trichuris;
     private String uncinaria;
     private String strong;
     private String enterobius;
     private String taeniassp;
     private String otros;
     private String observaciones;
     private String registro;

    public ExamenHecesCm() {
    }

	
    public ExamenHecesCm(ExamenHeces examenHeces) {
        this.examenHeces = examenHeces;
    }
    public ExamenHecesCm(ExamenHeces examenHeces, String ascaris, String trichuris, String uncinaria, String strong, String enterobius, String taeniassp, String otros, String observaciones, String registro) {
       this.examenHeces = examenHeces;
       this.ascaris = ascaris;
       this.trichuris = trichuris;
       this.uncinaria = uncinaria;
       this.strong = strong;
       this.enterobius = enterobius;
       this.taeniassp = taeniassp;
       this.otros = otros;
       this.observaciones = observaciones;
       this.registro = registro;
    }
   
    public int getIdExamenHeces() {
        return this.idExamenHeces;
    }
    
    public void setIdExamenHeces(int idExamenHeces) {
        this.idExamenHeces = idExamenHeces;
    }
    public ExamenHeces getExamenHeces() {
        return this.examenHeces;
    }
    
    public void setExamenHeces(ExamenHeces examenHeces) {
        this.examenHeces = examenHeces;
    }
    public String getAscaris() {
        return this.ascaris;
    }
    
    public void setAscaris(String ascaris) {
        this.ascaris = ascaris;
    }
    public String getTrichuris() {
        return this.trichuris;
    }
    
    public void setTrichuris(String trichuris) {
        this.trichuris = trichuris;
    }
    public String getUncinaria() {
        return this.uncinaria;
    }
    
    public void setUncinaria(String uncinaria) {
        this.uncinaria = uncinaria;
    }
    public String getStrong() {
        return this.strong;
    }
    
    public void setStrong(String strong) {
        this.strong = strong;
    }
    public String getEnterobius() {
        return this.enterobius;
    }
    
    public void setEnterobius(String enterobius) {
        this.enterobius = enterobius;
    }
    public String getTaeniassp() {
        return this.taeniassp;
    }
    
    public void setTaeniassp(String taeniassp) {
        this.taeniassp = taeniassp;
    }
    public String getOtros() {
        return this.otros;
    }
    
    public void setOtros(String otros) {
        this.otros = otros;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getRegistro() {
        return this.registro;
    }
    
    public void setRegistro(String registro) {
        this.registro = registro;
    }




}


