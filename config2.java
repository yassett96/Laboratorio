/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Oliverhacker
 */
public class config2 {

    public static ObservableList<String> cargos = FXCollections.observableArrayList();
    public static int id;
    public static String fechahoy;
    public static int MedicoId;
    
    public config2() {
        
        
    }
    
    public static void dialog(Alert.AlertType alertType,String s){
        Alert alert = new Alert(alertType,s);
        alert.initStyle(StageStyle.UTILITY);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Info");
        alert.showAndWait();
    }

    public void newStage3(Stage stage, Label lb, String load, String judul, boolean resize, StageStyle style, boolean maximized){
       try {
            
//            System.out.println(cargos);
            Stage st = new Stage();
            stage = (Stage) lb.getScene().getWindow();            
            Parent root = FXMLLoader.load(getClass().getResource(load));
            Scene scene = new Scene(root);
            st.initStyle(style);
            st.setResizable(resize);
            st.setMaximized(maximized);
            st.setTitle(judul);
            st.setScene(scene);
            st.show();
            stage.close();
        } catch (Exception e) {
        } 
    }
    
    public void newStage(int i,ObservableList<String> c, Stage stage, Label lb, String load, String judul, boolean resize, StageStyle style, boolean maximized){
       try {
            id=i;
            System.out.println(id);
            cargos=c;
            System.out.println(cargos);
            Stage st = new Stage();
            System.out.println("Llegada 1 wee");
            stage = (Stage) lb.getScene().getWindow(); 
            System.out.println("Llegada 2 wee");
            System.out.println("Abriendo nuevo scene");
            System.out.println("Llegada 3 wee");
            Parent root = FXMLLoader.load(getClass().getResource(load));
            System.out.println("Llegada 4 wee");
            Scene scene = new Scene(root);
            System.out.println("Llegada 5 wee");
            st.initStyle(style);
            System.out.println("Llegada 6 wee");
            st.setResizable(resize);
            System.out.println("Llegada 7 wee");
            st.setMaximized(maximized);
            System.out.println("Llegada 8 wee");
            st.setTitle(judul);
            System.out.println("Llegada 9 wee");
            st.setScene(scene);
            System.out.println("Llegada 10 wee");
            st.show();
            System.out.println("Llegada 11 wee");
            stage.close();
            System.out.println("Llegada Final wee");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "El error mahe");
        } 
    }
    
    public void newStage2(Stage stage, Button lb, String load, String judul, boolean resize, StageStyle style, boolean maximized){
       try {
            Stage st = new Stage();
            stage = (Stage) lb.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(load));
            Scene scene = new Scene(root);
            st.initStyle(style);
            st.setResizable(resize);
            st.setMaximized(maximized);
            st.setTitle(judul);
            st.setScene(scene);
            st.show();
            stage.close();
        } catch (Exception e) {
        } 
    }
    
//    public void loadAnchorPane(AnchorPane ap, String a){
//        try {
//            AnchorPane p = FXMLLoader.load(getClass().getResource("/herudi/view/"+a));
//            ap.getChildren().setAll(p);
//        } catch (IOException e) {
//        }   
//    }
    
    public static void setModelColumn(TableColumn tb,String a){
        tb.setCellValueFactory(new PropertyValueFactory(a));
    }



    public void setFecha(String fin) {
        
        this.fechahoy=fin;
//To change body of generated methods, choose Tools | Templates.
    }

    public void setMedicoId(int idmedicoactual) {
        this.MedicoId=idmedicoactual;
    }


}
