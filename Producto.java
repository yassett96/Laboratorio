package POJO;
// Generated 02-17-2017 07:23:49 AM by Hibernate Tools 4.3.1



/**
 * Producto generated by hbm2java
 */
public class Producto  implements java.io.Serializable {


     private Integer idProducto;
     private Proveedores proveedores;
     private String producto;
     private String enero;
     private String febrero;
     private String marzo;
     private String abril;
     private String mayo;
     private String junio;
     private String julio;
     private String agosto;
     private String septiembre;
     private String octubre;
     private String noviembre;
     private String diciembre;
     private String registro;

    public Producto() {
    }

    public Producto(Proveedores proveedores, String producto, String enero, String febrero, String marzo, String abril, String mayo, String junio, String julio, String agosto, String septiembre, String octubre, String noviembre, String diciembre, String registro) {
       this.proveedores = proveedores;
       this.producto = producto;
       this.enero = enero;
       this.febrero = febrero;
       this.marzo = marzo;
       this.abril = abril;
       this.mayo = mayo;
       this.junio = junio;
       this.julio = julio;
       this.agosto = agosto;
       this.septiembre = septiembre;
       this.octubre = octubre;
       this.noviembre = noviembre;
       this.diciembre = diciembre;
       this.registro = registro;
    }
   
    public Integer getIdProducto() {
        return this.idProducto;
    }
    
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    public Proveedores getProveedores() {
        return this.proveedores;
    }
    
    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }
    public String getProducto() {
        return this.producto;
    }
    
    public void setProducto(String producto) {
        this.producto = producto;
    }
    public String getEnero() {
        return this.enero;
    }
    
    public void setEnero(String enero) {
        this.enero = enero;
    }
    public String getFebrero() {
        return this.febrero;
    }
    
    public void setFebrero(String febrero) {
        this.febrero = febrero;
    }
    public String getMarzo() {
        return this.marzo;
    }
    
    public void setMarzo(String marzo) {
        this.marzo = marzo;
    }
    public String getAbril() {
        return this.abril;
    }
    
    public void setAbril(String abril) {
        this.abril = abril;
    }
    public String getMayo() {
        return this.mayo;
    }
    
    public void setMayo(String mayo) {
        this.mayo = mayo;
    }
    public String getJunio() {
        return this.junio;
    }
    
    public void setJunio(String junio) {
        this.junio = junio;
    }
    public String getJulio() {
        return this.julio;
    }
    
    public void setJulio(String julio) {
        this.julio = julio;
    }
    public String getAgosto() {
        return this.agosto;
    }
    
    public void setAgosto(String agosto) {
        this.agosto = agosto;
    }
    public String getSeptiembre() {
        return this.septiembre;
    }
    
    public void setSeptiembre(String septiembre) {
        this.septiembre = septiembre;
    }
    public String getOctubre() {
        return this.octubre;
    }
    
    public void setOctubre(String octubre) {
        this.octubre = octubre;
    }
    public String getNoviembre() {
        return this.noviembre;
    }
    
    public void setNoviembre(String noviembre) {
        this.noviembre = noviembre;
    }
    public String getDiciembre() {
        return this.diciembre;
    }
    
    public void setDiciembre(String diciembre) {
        this.diciembre = diciembre;
    }
    public String getRegistro() {
        return this.registro;
    }
    
    public void setRegistro(String registro) {
        this.registro = registro;
    }




}


