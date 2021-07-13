package POJO;
// Generated 02-17-2017 07:23:49 AM by Hibernate Tools 4.3.1



/**
 * ExamenOrina generated by hbm2java
 */
public class ExamenOrina  implements java.io.Serializable {


     private int idExamen;
     private Examen examen;
     private String resultado;
     private String observaciones;
     private String registro;
     private String diagnostico;
     private ExamenOrinaFq examenOrinaFq;
     private ExamenOrinaM examenOrinaM;

    public ExamenOrina() {
    }

	
    public ExamenOrina(Examen examen) {
        this.examen = examen;
    }
    public ExamenOrina(Examen examen, String resultado, String observaciones, String registro, String diagnostico, ExamenOrinaFq examenOrinaFq, ExamenOrinaM examenOrinaM) {
       this.examen = examen;
       this.resultado = resultado;
       this.observaciones = observaciones;
       this.registro = registro;
       this.diagnostico = diagnostico;
       this.examenOrinaFq = examenOrinaFq;
       this.examenOrinaM = examenOrinaM;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
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
    public ExamenOrinaFq getExamenOrinaFq() {
        return this.examenOrinaFq;
    }
    
    public void setExamenOrinaFq(ExamenOrinaFq examenOrinaFq) {
        this.examenOrinaFq = examenOrinaFq;
    }
    public ExamenOrinaM getExamenOrinaM() {
        return this.examenOrinaM;
    }
    
    public void setExamenOrinaM(ExamenOrinaM examenOrinaM) {
        this.examenOrinaM = examenOrinaM;
    }




}


