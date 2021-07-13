/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Hibernate.NewHibernateUtil;
import POJO.Medicos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
public class PapeleraMedicosController implements Initializable {

    
    SessionFactory factor;
    Session s = NewHibernateUtil.getSessionFactory().openSession();
    @FXML public TableView<Medicos> tableMed;
    @FXML public TableColumn<Medicos, String> columnoc;
    @FXML public TableColumn<Medicos, String> columnom;
    @FXML public TableColumn<Medicos, String> columape;
    @FXML public TableColumn<Medicos, String> columcargo;
    @FXML public TableColumn<Medicos, String> columdir;
    @FXML ContextMenu conmenu;
    @FXML MenuItem Restaurar;
    /**
     * Initializes the controller class.
     */
    
        @FXML public void Restaurar(){
        int id=(tableMed.getSelectionModel().getSelectedItem().getIdMedico());
        s.getTransaction().begin();
        Query query = s.createQuery("update Medicos set registro='Activo' where idMedico= :Id");
        query.setParameter("Id", id);
        int result = query.executeUpdate();
        
        Query query2 = s.createQuery("update Roles set registro='Activo' where idMedico= :Id");
        query2.setParameter("Id", id);
        int result2 = query2.executeUpdate();
        
        s.getTransaction().commit();
        new Alert(Alert.AlertType.INFORMATION,"Paciente Restaurado").show();
        tableMed.getItems().clear();
        s.close();
        s= NewHibernateUtil.getSessionFactory().openSession();
        listar();
        
    }
    
    
        public void listar(){
                Criteria c = s.createCriteria(Medicos.class)
        .addOrder( Order.asc("nombre") ).add(Restrictions.like("registro","Inactivo"));
             if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.INFORMATION,"Todos los registros han sido Restaurados").show();
                
            }else{
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            Medicos p= (Medicos) pac;
            
            System.out.println(p.getNombre());
            
            
        }
       tableMed.getItems().clear();
       tableMed.getItems().addAll(c.list()); 
             }
        
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        columnoc.setCellValueFactory(new PropertyValueFactory<>("noCedula"));
        columnom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columape.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columcargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        columdir.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        
                listar();
        
        
                tableMed.setOnMousePressed(new EventHandler<MouseEvent>(){
        public void handle(MouseEvent me) {
//        System.out.println(tableMed.getSelectionModel().getSelectedIndex());
        
//        buscar.setDisable(true);
            if(me.getClickCount()==1){
            if(tableMed.getSelectionModel().getSelectedIndex()==-1){
            }
                    
            System.out.println(me.getButton());    
            
            if(me.getButton().toString()=="SECONDARY"){
                    System.out.println("clic derecho");
                
                    tableMed.setContextMenu(conmenu);
            }
            
            }

        }
         });
        
        // TODO
    }    
    
}
