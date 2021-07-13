/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validaciones;

import javafx.scene.control.TextField;

/**
 *
 * @author Oliverhacker
 */
public class CedulaTextField extends TextField{
    
        public CedulaTextField(){
        //this.setPromptText("Ingresa solo Letras");
    }
    
    @Override
    public void replaceText(int i, int il, String string){
        if(string.matches("[a-zA-Z ñÑ0-9--]")||string.isEmpty()){
            super.replaceText(i,il, string);
        }
    }
    
    @Override
    public void replaceSelection(String string){
        super.replaceSelection(string);
    }
}