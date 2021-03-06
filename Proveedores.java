package POJO;
// Generated 02-17-2017 07:23:49 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Proveedores generated by hbm2java
 */
public class Proveedores  implements java.io.Serializable {


     private Integer idProveedor;
     private String nombre;
     private String email;
     private Integer telefono;
     private String registro;
     private Set productos = new HashSet(0);

    public Proveedores() {
    }

    public Proveedores(String nombre, String email, Integer telefono, String registro, Set productos) {
       this.nombre = nombre;
       this.email = email;
       this.telefono = telefono;
       this.registro = registro;
       this.productos = productos;
    }
   
    public Integer getIdProveedor() {
        return this.idProveedor;
    }
    
    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    public String getRegistro() {
        return this.registro;
    }
    
    public void setRegistro(String registro) {
        this.registro = registro;
    }
    public Set getProductos() {
        return this.productos;
    }
    
    public void setProductos(Set productos) {
        this.productos = productos;
    }




}


