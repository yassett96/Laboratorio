package POJO;
// Generated 02-17-2017 07:23:49 AM by Hibernate Tools 4.3.1



/**
 * Roles generated by hbm2java
 */
public class Roles  implements java.io.Serializable {


     private int idMedico;
     private Medicos medicos;
     private String embarazo;
     private String heces;
     private String orina;
     private String hematologia;
     private String quimica;
     private String inmunologia;
     private String parasitologia;
     private String bacteriologia;
     private String bancoSangre;
     private String registro;

    public Roles() {
    }

	
    public Roles(Medicos medicos) {
        this.medicos = medicos;
    }
    public Roles(Medicos medicos, String embarazo, String heces, String orina, String hematologia, String quimica, String inmunologia, String parasitologia, String bacteriologia, String bancoSangre, String registro) {
       this.medicos = medicos;
       this.embarazo = embarazo;
       this.heces = heces;
       this.orina = orina;
       this.hematologia = hematologia;
       this.quimica = quimica;
       this.inmunologia = inmunologia;
       this.parasitologia = parasitologia;
       this.bacteriologia = bacteriologia;
       this.bancoSangre = bancoSangre;
       this.registro = registro;
    }
   
    public int getIdMedico() {
        return this.idMedico;
    }
    
    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }
    public Medicos getMedicos() {
        return this.medicos;
    }
    
    public void setMedicos(Medicos medicos) {
        this.medicos = medicos;
    }
    public String getEmbarazo() {
        return this.embarazo;
    }
    
    public void setEmbarazo(String embarazo) {
        this.embarazo = embarazo;
    }
    public String getHeces() {
        return this.heces;
    }
    
    public void setHeces(String heces) {
        this.heces = heces;
    }
    public String getOrina() {
        return this.orina;
    }
    
    public void setOrina(String orina) {
        this.orina = orina;
    }
    public String getHematologia() {
        return this.hematologia;
    }
    
    public void setHematologia(String hematologia) {
        this.hematologia = hematologia;
    }
    public String getQuimica() {
        return this.quimica;
    }
    
    public void setQuimica(String quimica) {
        this.quimica = quimica;
    }
    public String getInmunologia() {
        return this.inmunologia;
    }
    
    public void setInmunologia(String inmunologia) {
        this.inmunologia = inmunologia;
    }
    public String getParasitologia() {
        return this.parasitologia;
    }
    
    public void setParasitologia(String parasitologia) {
        this.parasitologia = parasitologia;
    }
    public String getBacteriologia() {
        return this.bacteriologia;
    }
    
    public void setBacteriologia(String bacteriologia) {
        this.bacteriologia = bacteriologia;
    }
    public String getBancoSangre() {
        return this.bancoSangre;
    }
    
    public void setBancoSangre(String bancoSangre) {
        this.bancoSangre = bancoSangre;
    }
    public String getRegistro() {
        return this.registro;
    }
    
    public void setRegistro(String registro) {
        this.registro = registro;
    }




}


