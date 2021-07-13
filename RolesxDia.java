/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Controladores.PrincipalController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Oliverhacker
 */
public class RolesxDia {
 
    ObservableList<String> cargos = FXCollections.observableArrayList();
    






    public void setCargos(ObservableList<String> car) {
        this.cargos = car;
        System.out.println(cargos);
        PrincipalController p = new PrincipalController();
        
    }
    
    public ObservableList<String> getCargos() {
        return cargos;
    }
    
    
    
    
}
