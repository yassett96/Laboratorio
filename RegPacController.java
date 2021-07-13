/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import com.sun.javafx.scene.control.skin.TextFieldSkin;
import Hibernate.NewHibernateUtil;
import POJO.*;
import Validaciones.Burbujas;
//import Hibernate.NewHibernateUtil;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import FXML.*;
//import Hibernate.NewHibernateUtil;
import POJO.Pacientes;
import Utilidades.config2;
import Validaciones.LetterTextField;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javax.swing.JOptionPane;
import javax.swing.plaf.ToolTipUI;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.aop.AopInvocationException;
import org.springframework.core.style.StylerUtils;

/**
 *
 * @author Oliverhacker
 */
public class RegPacController implements Initializable {
    
//    @FXML public TextField txt;
        SessionFactory factor;
    Session s = NewHibernateUtil.getSessionFactory().openSession();
    
    @FXML public ComboBox Combosexo;
    @FXML public ComboBox Combosala;
    @FXML public TextField Nombre,cel,apellidos,ciudad,dir,Noe,depar,NoCed,Edad,txtb;
    @FXML public Button modif, elim, save, cancel, add,buscar,list;
    @FXML public RadioButton rNoe, rNom;
    @FXML ContextMenu conmenu;
    @FXML MenuItem meneli,menmos,menmodi;
    @FXML public TableView<Pacientes> tablePac;
    @FXML public TableColumn<Pacientes, Integer> columnoe;
    @FXML public TableColumn<Pacientes, String> columnom;
    @FXML public TableColumn<Pacientes, String> columape;
    @FXML public TableColumn<Pacientes, Integer> columnedad;
    @FXML public TableColumn<Pacientes, String> columsala;

    Alert alertcon = new Alert(AlertType.CONFIRMATION);
//    Tooltip tp= new Tooltip("Hello this is only the test");
    @FXML Label lblerror;
    int valttip=0;
//    @FXML Label message;
    int valor=0;
//    final ToggleGroup group = new ToggsleGroup();
    
    ObservableList<Pacientes> listPac = FXCollections.observableArrayList();
    ObservableList<String> sexo =FXCollections.observableArrayList("Masculino","Femenino");
    ObservableList<String> sala =FXCollections.observableArrayList("Casa Materna","Hospitalizados",
"Emergencia de Adultos","Emergencia de Niños","UAF","Consulta Externa","Consulta Externa Especialidades");
    
//    @FXML public Button BtAnalisis;
    


  
    

    @FXML public void add(){
        //
        Habilitar();
        add.setDisable(true);
        buscar.setDisable(true);
        list.setDisable(true);
        modif.setDisable(true);
        elim.setDisable(true);
        cancel.setDisable(false);
        save.setDisable(false);
        txtb.setDisable(true);
        valor=0;
        tablePac.getItems().clear();

    }
    
    public void addcriteria(){

               try{
        Transaction t = s.beginTransaction();
        
       Pacientes p= new Pacientes();
       
       p.setNombres(Nombre.getText());
       p.setApellidos(apellidos.getText());
       p.setNoExpediente(Integer.parseInt(Noe.getText()));
       p.setSexo(String.valueOf(Combosexo.getValue()));
       p.setSala(String.valueOf(Combosala.getValue()));
       p.setTelefono(Integer.parseInt(cel.getText()));
       p.setEdad(Integer.parseInt(Edad.getText()));
       p.setNoCedula(NoCed.getText());
       p.setTipoSangre(ciudad.getText());
       p.setDepartamento(depar.getText());
       p.setDireccion(dir.getText());
       p.setRegistro("Activo");
       s.save(p);
       new Alert(AlertType.INFORMATION,"Paciente Agregado").show();
//       s.update(p);
       
       t.commit();
        } catch (Exception e){
            new Alert(AlertType.ERROR,"Fallo Conexion: "+e.toString());
//        }finally
//        {
//            s.close();
        }
        valor=0;
        tablePac.getItems().clear();
    }


    public void suprimir(){
        int id=(tablePac.getSelectionModel().getSelectedItem().getIdPaciente());
        s.getTransaction().begin();
        Query query = s.createQuery("update Pacientes set registro='Inactivo' where idPaciente= :Id");
        query.setParameter("Id", id);
        int result = query.executeUpdate();
        s.getTransaction().commit();
        new Alert(AlertType.INFORMATION,"Paciente Eliminado").show();
        tablePac.getItems().clear();
        cancel();
    }

    
    public void editcriteria(){
        int id=(tablePac.getSelectionModel().getSelectedItem().getIdPaciente());
        s.getTransaction().begin();
        Query query = s.createQuery("update Pacientes set nombres = :nombres ,"
                + "apellidos = :apellidos , noExpediente = :noe , sexo = :sexo , sala= :sala ,"
                + "telefono= :tel, edad= :edad , noCedula= :noc ,"
                + "ciudad= :ciudad, departamento= :dep, direccion= :dir"                
                + " where idPaciente = :Id");
        query.setParameter("nombres", Nombre.getText());
        query.setParameter("apellidos", apellidos.getText());
        query.setParameter("noe", Integer.parseInt(Noe.getText()));
        query.setParameter("sexo", String.valueOf(Combosexo.getValue()));
        query.setParameter("sala", String.valueOf(Combosala.getValue()));
        query.setParameter("tel", Integer.parseInt(cel.getText()));
        query.setParameter("edad", Integer.parseInt(Edad.getText()));
        query.setParameter("noc", NoCed.getText());
        query.setParameter("ciudad", ciudad.getText());
        query.setParameter("dep", depar.getText());
        query.setParameter("dir", dir.getText());
        query.setParameter("Id", id);

        alertcon.setTitle("Actualizar Paciente");
        alertcon.setHeaderText("Al actualizar un Paciente, se reemplazaran los registros anteriores por"
                + " los nuevos");
        alertcon.setContentText("Realmente Desea Continuar?");
        Optional<ButtonType> result = alertcon.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
        query.executeUpdate();
        s.getTransaction().commit();
        new Alert(AlertType.INFORMATION,"Paciente Modificado").show();
//        config2.dialog(Alert.AlertType.INFORMATION,"Actualizado Correctamente");
        tablePac.getItems().clear();
        s.close();
        s = NewHibernateUtil.getSessionFactory().openSession();    
 }else{
            alertcon.close();
            s.getTransaction().commit();
            s.close();
            s = NewHibernateUtil.getSessionFactory().openSession();
        }


    }    
    
        @FXML public void mod(){
        Habilitar();
//        int id = 2;
        add.setDisable(true);
        buscar.setDisable(true);
        list.setDisable(true);
        modif.setDisable(true);
        elim.setDisable(true);
        cancel.setDisable(false);
        save.setDisable(false);
        txtb.setDisable(true);
        
        valor=1;
        
    }
        
        
         @FXML public void elim(){
        Evento();
        suprimir(); 
        valor=0;
        tablePac.getItems().clear();
     
    }
         


        @FXML public void buscar(){
        
            Deshabilitar();
        
        add.setDisable(true);
        buscar.setDisable(true);
        list.setDisable(true);
        cancel.setDisable(false);
//        find.setDisable(false);
        Noe.setDisable(false);
//        Evento();
        valor=0;
        tablePac.getItems().clear();
      
    }
        @FXML public void listar(){
                    
            txtb.setDisable(true);
            Deshabilitar();
            tablePac.setDisable(false);
            criterialist();
//            Evento();
            list.setDisable(true);

            
           valor=0; 

      
    }
        
        @FXML public void Evento(){
        modif.setDisable(false);
        elim.setDisable(false);
        cancel.setDisable(false);
        add.setDisable(true);
        buscar.setDisable(true);
        txtb.setDisable(true);
    
//           System.out.println(tablePac.getSelectionModel().selectedItemProperty().getValue().getNombres());
        Noe.setText(String.valueOf(tablePac.getSelectionModel().getSelectedItem().getNoExpediente()));
        Nombre.setText((tablePac.getSelectionModel().getSelectedItem().getNombres()));
        apellidos.setText((tablePac.getSelectionModel().getSelectedItem().getApellidos()));
        Combosexo.setValue(tablePac.getSelectionModel().getSelectedItem().getSexo());
        Combosala.setValue(tablePac.getSelectionModel().getSelectedItem().getSala());
        cel.setText(String.valueOf(tablePac.getSelectionModel().getSelectedItem().getTelefono()));
        Edad.setText(String.valueOf(tablePac.getSelectionModel().getSelectedItem().getEdad()));
        NoCed.setText((tablePac.getSelectionModel().getSelectedItem().getNoCedula()));
        ciudad.setText((tablePac.getSelectionModel().getSelectedItem().getTipoSangre()));
        depar.setText((tablePac.getSelectionModel().getSelectedItem().getDepartamento()));
        dir.setText((tablePac.getSelectionModel().getSelectedItem().getDireccion()));
        }
            
        @FXML public void MetodoClick(){
            Evento();
            mod();
        }
      

    public void Stylepred(){
        String stylep="-fx-skin: \"Utilidades.MetroTextFieldSkin\";";
        Nombre.setStyle(stylep);
        apellidos.setStyle(stylep);
        cel.setStyle(stylep);
        dir.setStyle(stylep);
        Noe.setStyle(stylep);
        depar.setStyle(stylep);
        NoCed.setStyle(stylep);
        Edad.setStyle(stylep);
        ciudad.setStyle(stylep);
    }
     public void TooltipValidation(){
         Stylepred();
 
    String style="-fx-background-color: orange,linear-gradient(to bottom, derive(orange,60%) 5%,derive(orange,90%) 40%);";
    
    String styletxt="-fx-border-color: red;";
   
    
    if(Nombre.getText().isEmpty() || Nombre.getText().equals("") || Nombre.getText().length()<3){            
        Nombre.setStyle(styletxt);
        
        valttip=1;
    }
    if(apellidos.getText().isEmpty() || apellidos.getText().equals("") || apellidos.getText().length()<3){
        apellidos.setStyle(styletxt);
        valttip=1;
    }
    if(cel.getText().isEmpty() || cel.getText().equals("") || cel.getText().length()!=8){
        cel.setStyle(styletxt);
        valttip=1;   
    }
    if(dir.getText().isEmpty() || dir.getText().equals("") || dir.getText().length()<8){
        dir.setStyle(styletxt);
        valttip=1;   
    } 
    if(Noe.getText().isEmpty() || Noe.getText().equals("") || Noe.getText().length()!=8){
        Noe.setStyle(styletxt);
        valttip=1;
    }
    if(depar.getText().isEmpty() || depar.getText().equals("") || depar.getText().length()<4){
        depar.setStyle(styletxt);
        valttip=1;   
    }
    if(NoCed.getText().isEmpty() || NoCed.getText().equals("") || NoCed.getText().length()<10){
        NoCed.setStyle(styletxt);
        valttip=1;   
    }
    if(Edad.getText().isEmpty() || Edad.getText().equals("") || Edad.getText().length()>2){
        Edad.setStyle(styletxt);
        valttip=1;    
    }
    if(ciudad.getText().isEmpty() || ciudad.getText().equals("") || ciudad.getText().length()>3){
        ciudad.setStyle(styletxt);
        valttip=1;   
    } 
    
     }
        
       
        
    public void criterialist(){
       
        Criteria c = s.createCriteria(Pacientes.class)
        .addOrder( Order.asc("nombres") ).add(Restrictions.like("registro","Activo"));
             if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancel();
                Inicial();
                
            }else{
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            Pacientes p= (Pacientes) pac;
            
            System.out.println(p.getNombres());
//            System.out.println(p.get);
            
            
        }
       tablePac.getItems().clear();
       tablePac.getItems().addAll(c.list()); 
       valor=0;
             }
    }
    
    @FXML public void rNoe(){
        rNom.setSelected(false);
        
    }
    @FXML public void rNom(){
        rNoe.setSelected(false);
    }    
    
        @FXML public void save(){

            
            TooltipValidation();
  if(valttip==0){
                if(valor==1){
                editcriteria();
                }else{
                addcriteria();
            }
                Limpiar();
                Inicial();
                valor=0;
                tablePac.getItems().clear();                
          
  } else{
      valttip=0;
  }        

    
    


        }   
        
        @FXML public void cancel(){
            Stylepred();
            tablePac.getItems().clear();
            Limpiar();
            Inicial();
            valor=0;
      
    }
        @FXML public void find(){
            if(rNoe.isSelected()){
              findnoe();   
            }
            if(rNom.isSelected()){
              findnom();   
            }  
            
            txtb.setDisable(true);
             
    }  
        
      public static boolean isNumeric(String str) {
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("")==false);
    }

       
    public void findnoe(){
            String bus=txtb.getText();
            if(isNumeric(bus)==true){
            System.out.println(bus);
            if(bus.isEmpty() || bus.equals("") || bus.length()<4){new Alert(AlertType.ERROR,"No deje campos vacios o pocos Caracteres").show();}else{


        Criteria c = s.createCriteria(Pacientes.class).add(Restrictions.like("registro","Activo"))
        .add(Restrictions.like("noExpediente", Integer.parseInt(bus)));
 
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            Pacientes p= (Pacientes) pac;
            System.out.println(" - " +p.getNoExpediente()+" - "+p.getNombres());
            
        }
            tablePac.getItems().clear();
            tablePac.getItems().addAll(c.list());
            
            Limpiar();
            Inicial();
            tablePac.setDisable(false);
            txtb.setText("");
            txtb.clear();
            valor=0;

}        
                
            } else {
            new Alert(AlertType.ERROR,"Ingrese Numeros").show();
            }
    }

    public void findnom(){
            String bus=txtb.getText();
            System.out.println(bus);
            if(bus.isEmpty() || bus.equals("") || bus.length()<3){new Alert(AlertType.ERROR,"Revisar").show();}else{

              
        Criteria c = s.createCriteria(Pacientes.class).add(Restrictions.like("registro","Activo"))
        .add(Restrictions.like("nombres", bus+"%"));
             if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancel();
                Inicial();
                
            }else{
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            Pacientes p= (Pacientes) pac;
            System.out.println(" - " +p.getNoExpediente()+" - "+p.getNombres());
            
        }
            tablePac.getItems().clear();
            tablePac.getItems().addAll(c.list());           
            Limpiar();
            Inicial();
            tablePac.setDisable(false);
            txtb.setText("");
            txtb.clear();
            
            valor=0;
             }
}        
    }

    public void Inicial(){
        lblerror.setVisible(false);
        tablePac.setDisable(true);
        add.setDisable(false);
        buscar.setDisable(false);
        list.setDisable(false);
        modif.setDisable(true);
        elim.setDisable(true);
        save.setDisable(true);
        cancel.setDisable(true);
        txtb.setDisable(false);
//        find.setDisable(true);
        Nombre.setDisable(true);
        cel.setDisable(true);
        apellidos.setDisable(true);
        ciudad.setDisable(true);
        dir.setDisable(true);
        Noe.setDisable(true);
        depar.setDisable(true);
        NoCed.setDisable(true);
        Edad.setDisable(true);
        Combosexo.setDisable(true);
        Combosala.setDisable(true);
        Combosexo.setValue("Masculino");
        Combosexo.setItems(sexo);
        Combosala.setValue("Casa Materna");
        Combosala.setItems(sala);
        valor=0;
//        listPac.add(new Paciente)
    }    
    
    public void Habilitar(){
        Nombre.setDisable(false);
        cel.setDisable(false);
        apellidos.setDisable(false);
        ciudad.setDisable(false);
        dir.setDisable(false);
        Noe.setDisable(false);
        depar.setDisable(false);
        NoCed.setDisable(false);
        Edad.setDisable(false);
        Combosexo.setDisable(false);
        Combosala.setDisable(false);
        valor=0;
    }
    

    
    public void Deshabilitar(){
        tablePac.setDisable(true);
        Nombre.setDisable(true);
        cel.setDisable(true);
        apellidos.setDisable(true);
        ciudad.setDisable(true);
        dir.setDisable(true);
        Noe.setDisable(true);
        depar.setDisable(true);
        NoCed.setDisable(true);
        Edad.setDisable(true);
        Combosexo.setDisable(true);
        Combosala.setDisable(true);
        valor=0;
    }
    public void Limpiar(){
        
        Nombre.clear();
        cel.clear();
        apellidos.clear();
        ciudad.clear();
        dir.clear();
        Noe.clear();
        depar.clear();
        NoCed.clear();
        Edad.clear();
        valor=0;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblerror.setVisible(false);
//        message.setVisible(false);
//        tablePac.getColumns().addAll(columnoe,columnom,columape,columnedad,columsala);
        columnoe.setCellValueFactory(new PropertyValueFactory<>("noExpediente"));
        columnom.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        columape.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columnedad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        columsala.setCellValueFactory(new PropertyValueFactory<>("sala"));
        rNoe.setSelected(true);
        

            

//          Nombre.setOnMouseMoved(new EventHandler<MouseEvent>() {
//
//        @Override
//        public void handle(MouseEvent event) {
////            String msg = "(x: " + event.getX() + ", y: " + event.getY() + ")\n(sceneX: "
////                    + event.getSceneX() + ", sceneY: " + event.getSceneY() + ")\n(screenX: "
////                    + event.getScreenX() + ", screenY: " + event.getScreenY() + ")";
//            tooltip.setText("Aqui");
//
//            Node node = (Node) event.getSource();
//            System.out.println(event.getSource());
//            tooltip.show(node, event.getScreenX() + 50, event.getScreenY());
//        }
//
//    });
        
        tablePac.setOnMousePressed(new EventHandler<MouseEvent>(){
        public void handle(MouseEvent me) {
        modif.setDisable(false);
        elim.setDisable(false);
        cancel.setDisable(false);
        add.setDisable(true);
        buscar.setDisable(true);
            if(me.getClickCount()==1){
                System.out.println(tablePac.getSelectionModel().getSelectedIndex());
            if(tablePac.getSelectionModel().getSelectedIndex()==-1){
              modif.setDisable(true);
              elim.setDisable(true);
              lblerror.setVisible(true);
              tablePac.setContextMenu(null);
              
//              JOptionPane.showMessageDialog(null, "Seleccione una Fila");
              
            }else{
             System.out.println(me.getButton());    
             lblerror.setVisible(false);
            if(me.getButton().toString()=="SECONDARY"){
                    System.out.println("clic derecho");
                
                    tablePac.setContextMenu(conmenu);
            }
            }
          
            }
        }
                   });
        
//                Validaciones.Burbujas b = new Burbujas();

        
        Inicial();
        valor=0;
        // TODO
        
  


    
}

}