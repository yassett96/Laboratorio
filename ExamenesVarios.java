package POJO;
// Generated 02-17-2017 07:23:49 AM by Hibernate Tools 4.3.1



/**
 * ExamenesVarios generated by hbm2java
 */
public class ExamenesVarios  implements java.io.Serializable {


     private int idExamenV;
     private Examen examen;
     private String examenPracticado;
     private String resultado;
     private String observaciones;
     private String registro;

    public ExamenesVarios() {
    }

	
    public ExamenesVarios(Examen examen) {
        this.examen = examen;
    }
    public ExamenesVarios(Examen examen, String examenPracticado, String resultado, String observaciones, String registro) {
       this.examen = examen;
       this.examenPracticado = examenPracticado;
       this.resultado = resultado;
       this.observaciones = observaciones;
       this.registro = registro;
    }
   
    public int getIdExamenV() {
        return this.idExamenV;
    }
    
    public void setIdExamenV(int idExamenV) {
        this.idExamenV = idExamenV;
    }
    public Examen getExamen() {
        return this.examen;
    }
    
    public void setExamen(Examen examen) {
        this.examen = examen;
    }
    public String getExamenPracticado() {
        return this.examenPracticado;
    }
    
    public void setExamenPracticado(String examenPracticado) {
        this.examenPracticado = examenPracticado;
    }
    public String getResultado() {
        return this.resultado;
    }
    
    public void setResultado(String resultado) {
        this.resultado = resultado;
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

