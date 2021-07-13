/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Animations.FadeInLeftTransition;
import Animations.FadeInRightTransition;
import Animations.FadeInTransition;
import Hibernate.NewHibernateUtil;
import Utilidades.config2;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author Herudi
 */
public class controllSplash implements Initializable {
            SessionFactory factor;
     Session s =  NewHibernateUtil.getSessionFactory().openSession();
     
     private Integer i=0;
    
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblRudy;
    @FXML
    private VBox vboxBottom;
    @FXML
    private Label lblClose;
    Stage stage;
    @FXML
    private ImageView imgLoading;
    private Timeline timeline;
    private AnimationTimer timer;
    final Text text = new Text (i.toString());
    
    
    class Login{
  
    Login() throws IOException
    {
        
//        if(subStage.isShowing()){
//            JOptionPane.showMessageDialog(null,"Cierre Primero antes de Abrir otra");
//        }else{
        Stage subStage = new Stage();
            subStage.setTitle("Inicio de Sesion");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Principal.fxml"));
        Scene scene = new Scene(root, 493, 327);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        //subStage.initOwner(LaboratorioMain.primaryStage);
        subStage.setX(150);
        subStage.setY(70);
        subStage.initStyle(StageStyle.UTILITY);
        subStage.show();        
    }   
}  

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        longStart();
       
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
  

            FadeInLeftTransition fadeIn = new FadeInLeftTransition(lblWelcome);
            fadeIn.play();
            FadeInRightTransition fadeOut = new FadeInRightTransition(lblRudy);
            fadeOut.setDelay(Duration.seconds(2));
            fadeOut.play();            
            FadeInTransition fadeIn2 = new FadeInTransition(vboxBottom);
            fadeIn2.play();
            

                    fadeOut.setOnFinished((e) -> {
                        System.out.println("Iniciando...");
                        fadeIn.stop();
                        config2 config = new config2();
                        config.newStage3(stage, lblClose, "/FXML/login.fxml", "Inicio de Sesion", true, StageStyle.TRANSPARENT, false);
            });
                    
            
        lblClose.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
            System.exit(0);
        });
        // TODO
    

 
        };
    
    
    

    }   
    
//    private void longStart() {
//        Service<ApplicationContext> service = new Service<ApplicationContext>() {
//            @Override
//            protected Task<ApplicationContext> createTask() {
//                return new Task<ApplicationContext>() {           
//                    @Override
//                    protected ApplicationContext call() throws Exception {
//                        ApplicationContext appContex = config.getInstance().getApplicationContext();
//                        int max = appContex.getBeanDefinitionCount();
//                        updateProgress(0, max);
//                        for (int k = 0; k < max; k++) {
//                            Thread.sleep(50);
//                            updateProgress(k+1, max);
//                        }
//                        return appContex;
//                    }
//                };
//            }
//        };
//        service.start();
//        service.setOnRunning((WorkerStateEvent event) -> {
//
//        });
//        service.setOnSucceeded((WorkerStateEvent event) -> {
//            config2 config = new config2();
//            config.newStage(stage, lblClose, "/FXML/login.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
//        });
//    } 

