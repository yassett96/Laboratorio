/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validaciones;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *
 * @author Oliverhacker
 */
public class Burbujas {
 
    public void setMessage(Label l, String message, Color color){
	l.setText(message);
	l.setTextFill(color);
	l.setVisible(true); 
}

    public void removeAllStyle(Node n){
	n.getStyleClass().removeAll("bad","med","good","best"); 
}
    
    
    
}
