package POJO;
// Generated 02-17-2017 07:23:49 AM by Hibernate Tools 4.3.1



/**
 * ExamenEmbarazo generated by hbm2java
 */
public class ExamenEmbarazo  implements java.io.Serializable {


     private int idExamen;
     private Examen examen;
     private String resultado;
     private Integer semana;
     private String observaciones;
     private String registro;

    public ExamenEmbarazo() {
    }

	
    public ExamenEmbarazo(Examen examen) {
        this.examen = examen;
    }
    public ExamenEmbarazo(Examen examen, String resultado, Integer semana, String observaciones, String registro) {
       this.examen = examen;
       this.resultado = resultado;
       this.semana = semana;
       this.observaciones = observaciones;
       this.registro = registro;
    }
   
    public int getIdExamen() {
        return this.idExamen;
    }
    
    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }
    public Examen getExamen() {
        return this.examen;
    }
    
    public void setExamen(Examen examen) {
        this.examen = examen;
    }
    public String getResultado() {
        return this.resultado;
    }
    
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public Integer getSemana() {
        return this.semana;
    }
    
    public void setSemana(Integer semana) {
        this.semana = semana;
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


