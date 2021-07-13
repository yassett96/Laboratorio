package POJO;
// Generated 02-17-2017 07:23:49 AM by Hibernate Tools 4.3.1



/**
 * ExamenHeces generated by hbm2java
 */
public class ExamenHeces  implements java.io.Serializable {


     private int idExamen;
     private Examen examen;
     private String color;
     private Integer macrofagos;
     private String consistencia;
     private String restosAlimenticios;
     private String mucus;
     private String macroscopicos;
     private Integer hematies;
     private String microscopicos;
     private Integer leucocitos;
     private String resultado;
     private String registro;
     private ExamenHecesCm examenHecesCm;
     private ExamenHecesCp examenHecesCp;

    public ExamenHeces() {
    }

	
    public ExamenHeces(Examen examen) {
        this.examen = examen;
    }
    public ExamenHeces(Examen examen, String color, Integer macrofagos, String consistencia, String restosAlimenticios, String mucus, String macroscopicos, Integer hematies, String microscopicos, Integer leucocitos, String resultado, String registro, ExamenHecesCm examenHecesCm, ExamenHecesCp examenHecesCp) {
       this.examen = examen;
       this.color = color;
       this.macrofagos = macrofagos;
       this.consistencia = consistencia;
       this.restosAlimenticios = restosAlimenticios;
       this.mucus = mucus;
       this.macroscopicos = macroscopicos;
       this.hematies = hematies;
       this.microscopicos = microscopicos;
       this.leucocitos = leucocitos;
       this.resultado = resultado;
       this.registro = registro;
       this.examenHecesCm = examenHecesCm;
       this.examenHecesCp = examenHecesCp;
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
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    public Integer getMacrofagos() {
        return this.macrofagos;
    }
    
    public void setMacrofagos(Integer macrofagos) {
        this.macrofagos = macrofagos;
    }
    public String getConsistencia() {
        return this.consistencia;
    }
    
    public void setConsistencia(String consistencia) {
        this.consistencia = consistencia;
    }
    public String getRestosAlimenticios() {
        return this.restosAlimenticios;
    }
    
    public void setRestosAlimenticios(String restosAlimenticios) {
        this.restosAlimenticios = restosAlimenticios;
    }
    public String getMucus() {
        return this.mucus;
    }
    
    public void setMucus(String mucus) {
        this.mucus = mucus;
    }
    public String getMacroscopicos() {
        return this.macroscopicos;
    }
    
    public void setMacroscopicos(String macroscopicos) {
        this.macroscopicos = macroscopicos;
    }
    public Integer getHematies() {
        return this.hematies;
    }
    
    public void setHematies(Integer hematies) {
        this.hematies = hematies;
    }
    public String getMicroscopicos() {
        return this.microscopicos;
    }
    
    public void setMicroscopicos(String microscopicos) {
        this.microscopicos = microscopicos;
    }
    public Integer getLeucocitos() {
        return this.leucocitos;
    }
    
    public void setLeucocitos(Integer leucocitos) {
        this.leucocitos = leucocitos;
    }
    public String getResultado() {
        return this.resultado;
    }
    
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public String getRegistro() {
        return this.registro;
    }
    
    public void setRegistro(String registro) {
        this.registro = registro;
    }
    public ExamenHecesCm getExamenHecesCm() {
        return this.examenHecesCm;
    }
    
    public void setExamenHecesCm(ExamenHecesCm examenHecesCm) {
        this.examenHecesCm = examenHecesCm;
    }
    public ExamenHecesCp getExamenHecesCp() {
        return this.examenHecesCp;
    }
    
    public void setExamenHecesCp(ExamenHecesCp examenHecesCp) {
        this.examenHecesCp = examenHecesCp;
    }




}


