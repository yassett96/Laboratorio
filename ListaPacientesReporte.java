/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Hibernate.NewHibernateUtil;
import POJO.Pacientes;
import Utilidades.ClaseTemporal;
//import static com.mchange.v2.c3p0.impl.C3P0Defaults.user;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author Oliverhacker
 */
public class ListaPacientesReporte implements Initializable {

    
            SessionFactory factor;
    Session s = NewHibernateUtil.getSessionFactory().openSession();
    @FXML public TableView<Pacientes> tablePac;
    @FXML public TableColumn<Pacientes, Integer> columnoe;
    @FXML public TableColumn<Pacientes, String> columnom;
    @FXML public TableColumn<Pacientes, String> columape;
    @FXML public TableColumn<Pacientes, Integer> columnedad;
    @FXML public TableColumn<Pacientes, String> columsala;
    @FXML Label user;
    public static int  Noe, idPac;
    public static String Nombre,Apellidos,sexo;
    
    /**
     * Initializes the controller class.
     */
    
//    @FXML public void Restaurar(){
//        int id=(tablePac.getSelectionModel().getSelectedItem().getIdPaciente());
//        s.getTransaction().begin();
//        Query query = s.createQuery("update Pacientes set registro='Activo' where idPaciente= :Id");
//        query.setParameter("Id", id);
//        int result = query.executeUpdate();
//        s.getTransaction().commit();
//        new Alert(Alert.AlertType.INFORMATION,"Paciente Restaurado").show();
//        tablePac.getItems().clear();
//        s.close();
//        s= NewHibernateUtil.getSessionFactory().openSession();
//        listar();
//        
//    }
    
    public void listar(){
                Criteria c = s.createCriteria(Pacientes.class)
        .addOrder( Order.asc("nombres") ).add(Restrictions.like("registro","Activo"));
             if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.INFORMATION,"Todos los registros han sido Restaurados").show();
                
            }else{
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            Pacientes p= (Pacientes) pac;
            
            System.out.println(p.getNombres());
            
            
        }
       tablePac.getItems().clear();
       tablePac.getItems().addAll(c.list()); 
             }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        

        
        columnoe.setCellValueFactory(new PropertyValueFactory<>("noExpediente"));
        columnom.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        columape.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columnedad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        columsala.setCellValueFactory(new PropertyValueFactory<>("sala"));
        
        listar();
        
        
        
tablePac.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
    public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            System.out.println(tablePac.getSelectionModel().getSelectedItem());  
            System.out.println("Doble Clic");
            Noe=tablePac.getSelectionModel().getSelectedItem().getNoExpediente();
            idPac=tablePac.getSelectionModel().getSelectedItem().getIdPaciente();
            Nombre=tablePac.getSelectionModel().getSelectedItem().getNombres();
            Apellidos=tablePac.getSelectionModel().getSelectedItem().getApellidos();
            sexo=tablePac.getSelectionModel().getSelectedItem().getSexo();
            System.out.println(idPac);
            
            ClaseTemporal ct = new ClaseTemporal();
            ct.Enviardatos(sexo,Noe,idPac,Nombre,Apellidos);

            PrincipalController.subStage.hide();
//            RegExaController.subStage.close();
            
            
            
        }

    }
});
        
        
        
        // TODO
    }    
    
}
