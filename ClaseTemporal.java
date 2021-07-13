/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

/**
 *
 * @author Oliverhacker
 */
public class ClaseTemporal {
    
    public static String txtnom,txtape,txtidmed,txt,txtmed,sexo;
    public static int noe,idmed,idpac;
    public static int idexamen;

    
    
    public void Enviardatos3(int idex) {

        this.idexamen=idex;
        System.out.println("Recibo a clase temporal "+this.idexamen);
    }
    

    public void Enviardatos(String sex,int Noe, int idPac, String Nombre, String Apellidos) {

        this.sexo=sexo;
        this.noe=Noe;
        this.txtnom=Nombre;
        this.txtape=Apellidos;
        this.idpac=idPac;
        
        System.out.println("Recibo a clase temporal "+this.noe);
    }

    public void Enviardatos2(int idMed, String Nombre) {

        this.idmed=idMed;
        this.txtmed=Nombre;
        System.out.println("Recibo a clase temporal "+this.idmed);
    }
    
    
}
