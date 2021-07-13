/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Hibernate.NewHibernateUtil;
import POJO.Usuario;
import Utilidades.config2;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author Oliverhacker
 */
public class ExamenTipoController implements Initializable {
        SessionFactory factor;
     Session s =  NewHibernateUtil.getSessionFactory().openSession(); 
    public Button btnemb,btnHeces,btnOrina,btnVarios;
    @FXML public Label label;
public ObservableList<String> cargos = FXCollections.observableArrayList();
 int id=0;   
        @FXML public void Embarazo() throws Exception {       
        new Embarazo(); 
        label.getScene().getWindow().hide();
        }
    @FXML public void Orina() throws Exception {       
        new Orina();  
        label.getScene().getWindow().hide();    }

    @FXML public void Varios() throws Exception {       
        new Varios();     
        label.getScene().getWindow().hide();}

    @FXML public void Heces() throws Exception {       
        new Heces(); 
        label.getScene().getWindow().hide();}

    
     class Orina{
  
    Orina() throws IOException
    {

        Stage subStage = new Stage();
        subStage.setTitle("Examenes de Orina");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Orina.fxml"));
        Scene scene = new Scene(root, 891, 700);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        subStage.setX(310);
        subStage.setY(40);
        subStage.initStyle(StageStyle.UTILITY);       
        subStage.show();
                
    }   
}
     
     
    class Varios{
  
    Varios() throws IOException
    {

        Stage subStage = new Stage();
        subStage.setTitle("Examenes Varios");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Varios.fxml"));
        Scene scene = new Scene(root, 727, 561);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        subStage.setX(310);
        subStage.setY(125);
        subStage.initStyle(StageStyle.UTILITY);       
        subStage.show();
                
    }   
}     

    class Embarazo{
  
    Embarazo() throws IOException
    {

        Stage subStage = new Stage();
        subStage.setTitle("Examenes De Embarazo");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Embarazo.fxml"));
        Scene scene = new Scene(root, 769, 507);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        subStage.setX(310);
        subStage.setY(125);
        subStage.initStyle(StageStyle.UTILITY);       
        subStage.show();
        
    }   
}
    
    class Heces{
  
    Heces() throws IOException
    {
 
        Stage subStage = new Stage();
        subStage.setTitle("Examenes de Heces");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Heces.fxml"));
        Scene scene = new Scene(root, 693, 546);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        subStage.setX(310);
        subStage.setY(125);
        subStage.initStyle(StageStyle.UTILITY);       
        subStage.show();
                
    }   
}    

      public void Permisos(){    
        
        System.out.println(id);
                Criteria c = s.createCriteria(Usuario.class).add(Restrictions.like("registro","Activo"))
                .add(Restrictions.eq("idUsuario", id));
                System.out.println("creee");

                        for(Object pac: c.list() )
        {
            Usuario p= (Usuario) pac;
            System.out.println(" - " +p.getNombre());
                System.out.println("clase usuario");
                System.out.println(p.getMedicos().getCargo().toString());
         
                if(p.getMedicos().getCargo().toString().contains("Administrador")){
                    System.out.println("Todos los privilegios"); 
                }          
                if(p.getMedicos().getCargo().toString().contains("Bioanalista")){
                    
        if(cargos.toString().contains("Embarazo")){
             btnemb.setDisable(false);}else{
            btnemb.setDisable(true); }
 
        if(cargos.toString().contains("Heces")){
             btnHeces.setDisable(false);}else{
            btnHeces.setDisable(true);}        

        if(cargos.toString().contains("Orina")){
             btnOrina.setDisable(false);}else{
            btnOrina.setDisable(true); }

        if(cargos.toString().contains("Hematologia")||cargos.toString().contains("Quimica")||
           cargos.toString().contains("Inmunologia")||cargos.toString().contains("Parasitologia")||
           cargos.toString().contains("Bacteriologia")||cargos.toString().contains("BancoSangre")){
             btnVarios.setDisable(false);}else{
            btnVarios.setDisable(true);}  
                    
                }          
          
          
    }
      }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargos=config2.cargos;
        id=config2.id;     
        
        Permisos();



// TODO
    }    
    
}
