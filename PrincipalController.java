/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Hibernate.NewHibernateUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
//import Hibernate.NewHibernateUtil;
import POJO.Usuario;
import Utilidades.ClaseTemporal;
import Utilidades.config2;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Oliverhacker
 */
public class PrincipalController implements Initializable {
    
        SessionFactory factor;
     Session s =  NewHibernateUtil.getSessionFactory().openSession();    
public ObservableList<String> cargos = FXCollections.observableArrayList();
public Button btnControl,BtRegPac,BtRegExa,btnMed,btnUser,btnPro,btnProv,btnVIH,btnCita;
public MenuItem catvarios,catmed,catpac,catuser,catpro,catprod;
int id=0;
int idm=0;
int noe=0;
    static Stage subStage = new Stage();
@FXML public ImageView ivima;
@FXML public Label user,ldia,lmes,laño,ldiaw, horalabel;
Image image;

    int existe=0;
    

    
    @FXML public void RegPac() throws Exception {
        new RegPac();      }

    @FXML public void RegExa() throws Exception {       
        new RegExa();      }

    @FXML public void VIH() throws Exception {       
        new VIH();      }    


        @FXML public void Cita() throws Exception {       
        new Cita();      }
 

    @FXML public void Proveedores() throws Exception {       
        new Proveedor();      }

    @FXML public void Control() throws Exception {       
        new Control();      }    

    @FXML public void Productos() throws Exception {       
        new Productos();      }
    
    
    @FXML public void Medicos() throws Exception {       
        new Medicos();      } 
    
    @FXML public void Usuarios() throws Exception {       
        new Usuarios();      }    

    @FXML public void papelPac() throws Exception {       
        new PapelPac();      }  
    
        @FXML public void papelMed() throws Exception {       
        new PapelMed();      } 

//    public void set(ObservableList<String> car) {
//
//        
//    }
    
    @FXML public void Calculadora(){
            try        
    {
        Runtime rt = Runtime.getRuntime();           
        Process p = rt.exec("calc");            
        p.waitFor();        
    }        
    catch ( IOException ioe )       
    {            
        ioe.printStackTrace();
    }         
    catch ( InterruptedException ie )
    {            
        ie.printStackTrace();     
    }
    }

    @FXML public void Word()  {
try {
    Process proceso = Runtime.getRuntime().exec("cmd.exe /K start winword");

} catch (IOException e) {
        e.printStackTrace();
}
    }
    
    
        @FXML public void Excel(){
                  
try {
    Process proceso = Runtime.getRuntime().exec("cmd.exe /K start Excel");

} catch (IOException e) {
        e.printStackTrace();
}
    }
        
        
            @FXML public void Point(){
try {
    Process proceso = Runtime.getRuntime().exec("cmd.exe /K start powerpnt");

} catch (IOException e) {
        e.printStackTrace();
}
    }
            
   @FXML public void ses() throws IOException{
            Stage st = new Stage();
            Stage stage = (Stage) user.getScene().getWindow();            
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
            Scene scene = new Scene(root);
            st.initStyle(StageStyle.TRANSPARENT);
            st.setResizable(true);
            st.setMaximized(false);
            st.setTitle("Inicio de Sesion");
            st.setScene(scene);
            st.show();
            stage.close();     
   }         

   @FXML public void Conceptos(){
       Alert alert1=new Alert(AlertType.INFORMATION,"Este Sistema permite el control de Resultados de Laboratorio");
       alert1.setTitle("Informacion General");
       alert1.show();
   }

   @FXML public void Acerca(){
       Alert alert2=new Alert(AlertType.INFORMATION,"Copyrigth BD2 UNI");
       alert2.setTitle("Informacion General");
       alert2.show();
   }
   
   
   @FXML public void cerrar() throws IOException{
            Stage stage = (Stage) user.getScene().getWindow();            
            stage.close();
            System.exit(0);
   }    
   
   
   
    @FXML public void ReportePac() throws SQLException, JRException{
        
            String path = "src\\Reportes\\Pacientes.jasper";
            Connection a = DriverManager.getConnection("jdbc:mysql://localhost:3306/Laboratorio","root","12345678");
//            InputStream reporte = this.getClass().getResourceAsStream("/Reportes/Medicos.jasper");
      JasperPrint jasperPrint; 
      jasperPrint = JasperFillManager.fillReport(path, null,a);
      JasperViewer jv = new JasperViewer(jasperPrint, false);
      jv.setTitle("Reporte de Pacientes");
      jv.setVisible(true);
      
    }

    @FXML public void ReporteKardex() throws SQLException, JRException{
        
            String path = "src\\Reportes\\ReporteVIH.jasper";
            Connection a = DriverManager.getConnection("jdbc:mysql://localhost:3306/Laboratorio","root","12345678");
//            InputStream reporte = this.getClass().getResourceAsStream("/Reportes/Medicos.jasper");
      JasperPrint jasperPrint; 
      jasperPrint = JasperFillManager.fillReport(path, null,a);
      JasperViewer jv = new JasperViewer(jasperPrint, false);
      jv.setTitle("Kardex - Consolidado de VIH");
      jv.setVisible(true);

    }    

    @FXML public void ReporteMX() throws SQLException, JRException{
        
      String path = "src\\Reportes\\ReporteMX.jasper";
      Connection a = DriverManager.getConnection("jdbc:mysql://localhost:3306/Laboratorio","root","12345678");
      JasperPrint jasperPrint = JasperFillManager.fillReport(path, null,a);
      JasperViewer jv = new JasperViewer(jasperPrint, false);
      jv.setTitle("Informe Mensual de Consumos RX de Lab");
      jv.setVisible(true);

    }  

    @FXML public void HistorialPac() throws SQLException, JRException, IOException{
        
        Comprobar();

            find2();
                    

    }    
    
    public void Generarya() throws SQLException, JRException{
    
        
            Map parameters = new HashMap();
            parameters.put("NoExpediente", noe);
                        

            String path = "src\\Reportes\\ExamenesHistorialPaciente.jasper";
            Connection a = DriverManager.getConnection("jdbc:mysql://localhost:3306/Laboratorio","root","12345678");

      JasperPrint jasperPrint; 
      
      jasperPrint = JasperFillManager.fillReport(path, parameters,a);
      JasperViewer jv = new JasperViewer(jasperPrint, false);
      jv.setTitle("Reporte de Historial del Paciente");
      jv.setVisible(true);
        

                
            }

    
    @FXML public void ReporteEmb() throws SQLException, JRException{
        
            String path = "src\\Reportes\\ExamenesEmb.jasper";
            Connection a = DriverManager.getConnection("jdbc:mysql://localhost:3306/Laboratorio","root","12345678");

      JasperPrint jasperPrint; 

        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fin = (año + "-" + (mes) + "-" + dia);
        System.out.println(fin);
        
        if(mes==1){
        
                fecha.add(Calendar.MONTH, -1);
                fecha.add(Calendar.YEAR, -1);
        int naño = fecha.get(Calendar.YEAR) + 1; 
        int nmes = fecha.get(Calendar.MONTH) + 1;       
        String inicio = (naño + "-" + (nmes) + "-" + dia);
        System.out.println(inicio);
        

        Map parameters = new HashMap();
  
            parameters.put("inicio", inicio);
            parameters.put("fin", fin);
      
      jasperPrint = JasperFillManager.fillReport(path, parameters,a);
      JasperViewer jv = new JasperViewer(jasperPrint, false);
      jv.setTitle("Reporte de Examenes de Embarazo");
      jv.setVisible(true);
            
        }else{
        fecha.add(Calendar.MONTH, -1); 
        int nmes = fecha.get(Calendar.MONTH) + 1;       
        String inicio = (año + "-" + (nmes) + "-" + dia);
        System.out.println(inicio);
        

        Map parameters = new HashMap();
  
            parameters.put("inicio", inicio);
            parameters.put("fin", fin);
      
      jasperPrint = JasperFillManager.fillReport(path, parameters,a);
      JasperViewer jv = new JasperViewer(jasperPrint, false);
      jv.setTitle("Reporte de Examenes de Embarazo");
      jv.setVisible(true);
        }


    }    
    
    @FXML public void ReporteOrina() throws SQLException, JRException{
        
            String path = "src\\Reportes\\ExamenesOrina.jasper";
            Connection a = DriverManager.getConnection("jdbc:mysql://localhost:3306/Laboratorio","root","12345678");

      JasperPrint jasperPrint; 

        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fin = (año + "-" + (mes) + "-" + dia);
        System.out.println(fin);
        
        if(mes==1){
                    fecha.add(Calendar.MONTH, -1);
                    fecha.add(Calendar.YEAR, -1);
                    int naño=fecha.get(Calendar.YEAR) + 1;
        int nmes = fecha.get(Calendar.MONTH) + 1;       
        String inicio = (naño + "-" + (nmes) + "-" + dia);
        System.out.println(inicio);
        

        Map parameters = new HashMap();
  
            parameters.put("inicio", inicio);
            parameters.put("fin", fin);
      
      jasperPrint = JasperFillManager.fillReport(path, parameters,a);
      JasperViewer jv = new JasperViewer(jasperPrint, false);
      jv.setTitle("Reporte de Examenes de Orina");
      jv.setVisible(true);
            
        }else{
                    fecha.add(Calendar.MONTH, -1); 
        int nmes = fecha.get(Calendar.MONTH) + 1;       
        String inicio = (año + "-" + (nmes) + "-" + dia);
        System.out.println(inicio);
        

        Map parameters = new HashMap();
  
            parameters.put("inicio", inicio);
            parameters.put("fin", fin);
      
      jasperPrint = JasperFillManager.fillReport(path, parameters,a);
      JasperViewer jv = new JasperViewer(jasperPrint, false);
      jv.setTitle("Reporte de Examenes de Orina");
      jv.setVisible(true);
        }

    }   
    
    
    
        @FXML public void ReporteHeces() throws SQLException, JRException{
        
            String path = "src\\Reportes\\ExamenesHeces.jasper";
            Connection a = DriverManager.getConnection("jdbc:mysql://localhost:3306/Laboratorio","root","12345678");

      JasperPrint jasperPrint; 

        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fin = (año + "-" + (mes) + "-" + dia);
        System.out.println(fin);

        if(mes==1){

        fecha.add(Calendar.MONTH, -1); 
        fecha.add(Calendar.YEAR, -1);
        int naño = fecha.get(Calendar.YEAR)+1;
        int nmes = fecha.get(Calendar.MONTH) + 1;       
        String inicio = (naño + "-" + (nmes) + "-" + dia);
        System.out.println(inicio);
        

        Map parameters = new HashMap();
  
            parameters.put("inicio", inicio);
            parameters.put("fin", fin);
      
      jasperPrint = JasperFillManager.fillReport(path, parameters,a);
      JasperViewer jv = new JasperViewer(jasperPrint, false);
      jv.setTitle("Reporte de Examenes de Heces");
      jv.setVisible(true);
            
        }else{
        fecha.add(Calendar.MONTH, -1); 
        int nmes = fecha.get(Calendar.MONTH) + 1;       
        String inicio = (año + "-" + (nmes) + "-" + dia);
        System.out.println(inicio);
        

        Map parameters = new HashMap();
  
            parameters.put("inicio", inicio);
            parameters.put("fin", fin);
      
      jasperPrint = JasperFillManager.fillReport(path, parameters,a);
      JasperViewer jv = new JasperViewer(jasperPrint, false);
      jv.setTitle("Reporte de Examenes de Heces");
      jv.setVisible(true);
            
        }
        
        
    }   
        
        
        
            @FXML public void ReporteVarios() throws SQLException, JRException{
        
            String path = "src\\Reportes\\ExamenesVarios.jasper";
            Connection a = DriverManager.getConnection("jdbc:mysql://localhost:3306/Laboratorio","root","12345678");

      JasperPrint jasperPrint; 

        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fin = (año + "-" + (mes) + "-" + dia);
        System.out.println(fin);
        
        if(mes==1){
        fecha.add(Calendar.MONTH, -1);
        fecha.add(Calendar.YEAR, -1);
        int nmes = fecha.get(Calendar.MONTH) + 1; 
        int naño = fecha.get(Calendar.YEAR);
        String inicio = (naño + "-" + (nmes) + "-" + dia);
        System.out.println(inicio); 

        

        Map parameters = new HashMap();
  
            parameters.put("inicio", inicio);
            parameters.put("fin", fin);
      
      jasperPrint = JasperFillManager.fillReport(path, parameters,a);
      JasperViewer jv = new JasperViewer(jasperPrint, false);
      jv.setTitle("Reporte de Examenes Varios");
      jv.setVisible(true);

        
        }else{
        fecha.add(Calendar.MONTH, -1); 
        int nmes = fecha.get(Calendar.MONTH) + 1; 
        String inicio = (año + "-" + (nmes) + "-" + dia);
        System.out.println(inicio); 
        
        Map parameters = new HashMap();
  
            parameters.put("inicio", inicio);
            parameters.put("fin", fin);
      
      jasperPrint = JasperFillManager.fillReport(path, parameters,a);
      JasperViewer jv = new JasperViewer(jasperPrint, false);
      jv.setTitle("Reporte de Examenes Varios");
      jv.setVisible(true);
        }
        


    }   

    
    @FXML public void ReporteMed() throws SQLException{
        
            String path = "src\\Reportes\\Medicos.jasper";
            Connection a = DriverManager.getConnection("jdbc:mysql://localhost:3306/Laboratorio","root","12345678");

//      InputStream reporte = this.getClass().getResourceAsStream("/Reportes/Medicos.jasper");
      JasperPrint jasperPrint; 
        try {
            jasperPrint = JasperFillManager.fillReport(path, null,a);
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("Reporte de Medicos");
            jv.setVisible(true); 
        } catch (JRException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML public void ReporteUser() throws SQLException{
        
            String path = "src\\Reportes\\Usuarios.jasper";
            Connection a = DriverManager.getConnection("jdbc:mysql://localhost:3306/Laboratorio","root","12345678");

//      InputStream reporte = this.getClass().getResourceAsStream("/Reportes/Medicos.jasper");
      JasperPrint jasperPrint; 
        try {
            jasperPrint = JasperFillManager.fillReport(path, null,a);
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("Reporte de Usuarios del Sistema");
            jv.setVisible(true); 
        } catch (JRException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
  

 
    public void Medicoencargado(){

    
    int idmedicoactual= idm;
        System.out.println("sisi "+idmedicoactual);
    config2 cd = new config2();
    cd.setMedicoId(idmedicoactual);
    
    }

    

    public void Rol() throws IOException{
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
                System.out.println(p.getMedicos().getFoto());
                idm=p.getMedicos().getIdMedico();
                user.setText("Usuario: "+p.getMedicos().getNombre()+" "+p.getMedicos().getApellido());
                InputStream in = new ByteArrayInputStream(p.getMedicos().getFoto());
            BufferedImage bImageFromConvert = ImageIO.read(in);
            File imagensalida= new File("src/Imagenes/Medicoinicial.png");
            ImageIO.write(ImageIO.read(imagensalida), "png", imagensalida);  
            image = new Image("file:" + imagensalida.getAbsolutePath());
//            ivima.setImage(image);
                
                
                if(p.getMedicos().getCargo().toString().contains("Administrador")){
                    System.out.println("Todos los privilegios"); 
                }                    
                if(p.getMedicos().getCargo().toString().contains("Recepcionista")){
                    btnControl.setDisable(true);
                    btnMed.setDisable(true);
                    btnUser.setDisable(true);
                    catvarios.setDisable(true);
                    catmed.setDisable(true);                    
                    catpac.setDisable(false);
                    catuser.setDisable(true);
                    btnVIH.setDisable(true);
                    catpro.setDisable(true);
                    catprod.setDisable(true);
                    btnProv.setDisable(true);
                    btnPro.setDisable(true);
                     System.out.println("Ninguno");
                 }                
                if(p.getMedicos().getCargo().toString().contains("Bioanalista")){
//                     Permisos();
                     btnControl.setDisable(false);
                     BtRegExa.setDisable(true);
                     BtRegPac.setDisable(true);
                    btnCita.setDisable(true);
                    btnMed.setDisable(true);
                    btnUser.setDisable(true);
                    catvarios.setDisable(true);
                    catmed.setDisable(true);                    
                    catpac.setDisable(true);
                    catuser.setDisable(true);
                    btnVIH.setDisable(true);
                    catpro.setDisable(true);
                    catprod.setDisable(true);
                    btnProv.setDisable(true);
                    btnPro.setDisable(true);                    
                 }
            
        }
                
                
                
                }        
    
    class Cita{
  
    Cita() throws IOException
    {
        
//        if(subStage.isShowing()){
//            JOptionPane.showMessageDialog(null,"Cierre Primero antes de Abrir otra");
//        }else{
        Stage subStage = new Stage();
            subStage.setTitle("Registro de Citas");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/RegCitas.fxml"));
        Scene scene = new Scene(root, 604, 289);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        //subStage.initOwner(LaboratorioMain.primaryStage);
//        subStage.setX(150);
//        subStage.setY(70);
        subStage.centerOnScreen();
        subStage.initStyle(StageStyle.UTILITY);
//        scene.getStylesheets().add(getClass().getResource("/Estilos/Tooltip.css").toExternalForm());
        subStage.show();        
    }   
}

    class Control{
  
    Control() throws IOException
    {
        
//        if(subStage.isShowing()){
//            JOptionPane.showMessageDialog(null,"Cierre Primero antes de Abrir otra");
//        }else{
        Stage subStage = new Stage();
            subStage.setTitle("Control de Examenes");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ExamenTipo.fxml"));
        Scene scene = new Scene(root, 721, 486);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        //subStage.initOwner(LaboratorioMain.primaryStage);
//        subStage.setX(150);
//        subStage.setY(70);
        subStage.centerOnScreen();
        subStage.initStyle(StageStyle.UTILITY);
//        scene.getStylesheets().add(getClass().getResource("/Estilos/Tooltip.css").toExternalForm());
        subStage.show();        
    }   
}
    
    
    class RegPac{
  
    RegPac() throws IOException
    {
        
//        if(subStage.isShowing()){
//            JOptionPane.showMessageDialog(null,"Cierre Primero antes de Abrir otra");
//        }else{
        Stage subStage = new Stage();
            subStage.setTitle("Registro de Pacientes");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/RegPac.fxml"));
        Scene scene = new Scene(root, 1054, 619);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        //subStage.initOwner(LaboratorioMain.primaryStage);
//        subStage.setX(150);
//        subStage.setY(70);
        subStage.centerOnScreen();
        subStage.initStyle(StageStyle.UTILITY);
//        scene.getStylesheets().add(getClass().getResource("/Estilos/Tooltip.css").toExternalForm());
        subStage.show();        
    }   
}

    class RegExa{
  
    RegExa() throws IOException
    {
        
//        if(subStage.isShowing()){
//            JOptionPane.showMessageDialog(null,"Cierre Primero antes de Abrir otra");
//        }else{
        Stage subStage = new Stage();
            subStage.setTitle("Registro de Pacientes");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/RegExa.fxml"));
        Scene scene = new Scene(root);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        //subStage.initOwner(LaboratorioMain.primaryStage);
        subStage.centerOnScreen();
        subStage.initStyle(StageStyle.UTILITY);
        subStage.show();        
    }   
}
    
    class Proveedor{
  
    Proveedor() throws IOException
    {
        
//        if(subStage.isShowing()){
//            JOptionPane.showMessageDialog(null,"Cierre Primero antes de Abrir otra");
//        }else{
        Stage subStage = new Stage();
            subStage.setTitle("Registro de Proveedores");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Proveedores.fxml"));
        Scene scene = new Scene(root, 654, 300);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        //subStage.initOwner(LaboratorioMain.primaryStage);
        subStage.setX(300);
        subStage.setY(220);
        subStage.initStyle(StageStyle.UTILITY);
        subStage.show();        
    }   
}


    class Productos{
  
    Productos() throws IOException
    {
        
//        if(subStage.isShowing()){
//            JOptionPane.showMessageDialog(null,"Cierre Primero antes de Abrir otra");
//        }else{
        Stage subStage = new Stage();
            subStage.setTitle("Registro de Productos");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Producto.fxml"));
        Scene scene = new Scene(root, 704, 474);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        //subStage.initOwner(LaboratorioMain.primaryStage);
        subStage.setX(300);
        subStage.setY(170);
        subStage.initStyle(StageStyle.UTILITY);
        subStage.show();        
    }   
}
    

    class PapelPac{
  
    PapelPac() throws IOException
    {

        Stage subStage = new Stage();
        subStage.setTitle("Papelera de Pacientes");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/PapeleraPacientes.fxml"));
        Scene scene = new Scene(root, 973, 315);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        subStage.setX(210);
        subStage.setY(125);
        subStage.initStyle(StageStyle.UTILITY);       
        subStage.show();
                
    }   
}    
 

    
    class PapelMed{
  
    PapelMed() throws IOException
    {

        Stage subStage = new Stage();
        subStage.setTitle("Papelera de Medicos");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/PapeleraMedicos.fxml"));
        Scene scene = new Scene(root, 1098, 326);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        subStage.setX(210);
        subStage.setY(125);
        subStage.initStyle(StageStyle.UTILITY);       
        subStage.show();
                
    }   
} 

    class VIH{
  
    VIH() throws IOException
    {

        Stage subStage = new Stage();
        subStage.setTitle("Reporte Consolidado VIH");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Reporte_VIH.fxml"));
        Scene scene = new Scene(root, 1177, 443);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        subStage.setX(90);
        subStage.setY(125);
        subStage.initStyle(StageStyle.UTILITY);       
        subStage.show();
                
    }   
}    
    

    
    class Medicos{
  
    Medicos() throws IOException
    {
        Stage subStage = new Stage();
        subStage.setTitle("Registro de Medicos");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Medicos.fxml"));
        Scene scene = new Scene(root, 1079, 563);
        subStage.setX(140);
        subStage.setY(110);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        subStage.initStyle(StageStyle.UTILITY);       
        subStage.show();
       
    }   
}


    class Usuarios{
  
    Usuarios() throws IOException
    {

        Stage subStage = new Stage();
        subStage.setTitle("Registro de Usuarios");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Usuarios.fxml"));
        Scene scene = new Scene(root, 643, 299);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        subStage.setX(310);
        subStage.setY(225);
        subStage.initStyle(StageStyle.UTILITY);       
        subStage.show();
                
    }   
}

//

    
    public void Fechas(){

        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
            int diax= fecha.get(Calendar.DAY_OF_WEEK);
        String fin = (año + "-" + (mes) + "-" + dia);
        System.out.println(fin);
        laño.setText(String.valueOf(año));
        System.out.println(String.valueOf(dia).length());
        if(String.valueOf(dia).length()==1){
            ldiaw.setText("0"+String.valueOf(dia));
        }else{
                   ldiaw.setText(String.valueOf(dia)); 
        }
        
        if(diax==1){
            ldia.setText("Domingo");
        }
        if(diax==2){
            ldia.setText("Lunes");
        }
        if(diax==3){
            ldia.setText("Martes");
        }
        if(diax==4){
            ldia.setText("Miercoles");
        }
        if(diax==5){
            ldia.setText("Jueves");
        }
        if(diax==6){
            ldia.setText("Viernes");
        }
        if(diax==7){
            ldia.setText("Sabado");
        }        
        laño.setStyle("-fx-text-fill: black;");
        lmes.setStyle("-fx-text-fill: black;");
       if(mes==1){
           lmes.setText("Enero");
           
       }
       if(mes==2){
           lmes.setText("Febrero");
       }
       if(mes==3){
           lmes.setText("Marzo");
       } 
       if(mes==4){
           lmes.setText("Abril");
       }
       if(mes==5){
           lmes.setText("Mayo");
       }
       if(mes==6){
           lmes.setText("Junio");
       } 
       if(mes==7){
           lmes.setText("Julio");
       }        
       if(mes==8){
           lmes.setText("Agosto");
       }
       if(mes==9){
           lmes.setText("Septiembre");
       }
       if(mes==10){
           lmes.setText("Octubre");
       }
       if(mes==11){
           lmes.setText("Noviembre");
       }
       if(mes==12){
           lmes.setText("Diciembre");
       }
    }
    
    public void find2() throws IOException, SQLException, JRException {
        
        
        if(existe==0){
        subStage.setTitle("Seleccionar Paciente");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ListaPacientesReporte.fxml"));
        Scene scene = new Scene(root, 870, 476);
        //root.setLayoutX(300);
        subStage.initModality(Modality.APPLICATION_MODAL);
        subStage.setScene(scene);
        subStage.centerOnScreen();
        //subStage.initOwner(LaboratorioMain.primaryStage);
//        subStage.setX(200);
//        subStage.setY(150);
        subStage.initStyle(StageStyle.TRANSPARENT);
        subStage.show(); 
        existe=1;
        subStage.setOnHidden(event ->{
        
            try {
                ya();
            } catch (SQLException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

     
        }else{
            System.out.println("Ya abierto, volviendo a abrir");
            subStage.show();
            existe=1;
            ya();
        }
        
            
        
    }
    
    
    public void ya() throws SQLException, JRException{
    
    this.noe=ClaseTemporal.noe;
        System.out.println(noe);
        Generarya();
}
 
    
public void Comprobar(){
                    if(subStage.getTitle()==null){
            System.out.println("sin titulo");
            existe=0;
        }else{
            existe=1;
        }
}    

public void Hoy(){
            Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fin = (año + "-" + (mes) + "-" + dia);
        config2 c2 = new config2();
        c2.setFecha(fin);
        System.out.println("Para examen "+fin);
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        Comprobar();
        
        Fechas();
//        
//        

        Hoy();









        System.out.println("Scene abierto");


        System.out.println("Imagen Cargada");
        cargos=config2.cargos;
        id=config2.id;
            try {
                Rol();
            } catch (IOException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
//        System.out.println("zzz");
        ivima.setImage(image);
        Medicoencargado();
        
//        System.out.println("ya");

        
        // TODO
    }    
    
}
