/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Hibernate.NewHibernateUtil;
import POJO.Pacientes;
import POJO.Proveedores;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author Oliverhacker
 */
public class ProveedoresController implements Initializable {
    
            SessionFactory factor;
    Session s = NewHibernateUtil.getSessionFactory().openSession();
    @FXML ContextMenu conmenu;
    @FXML public TableView<Proveedores> Table;
    @FXML public TableColumn<Proveedores, String> cp;
    @FXML public TableColumn<Proveedores, Integer> ct;
    @FXML public TableColumn<Proveedores, String> ce;
    @FXML TextField tn,te,tt;
    @FXML MenuItem m1,m2,m3,m4,m5;
    @FXML Button save;
        int valttip=0;

    /**
     * Initializes the controller class.
     */
    
    public void Stylepred(){
        String stylep="-fx-border-color: black;";
        tn.setStyle(stylep);
        te.setStyle(stylep);
        tt.setStyle(stylep);

    }    
    
         public void TooltipValidation(){
         Stylepred();
 
    String style="-fx-background-color: orange,linear-gradient(to bottom, derive(orange,60%) 5%,derive(orange,90%) 40%);";
    
    String styletxt="-fx-border-color: red;";
   
    
    if(tn.getText().isEmpty() || tn.getText().equals("") || tn.getText().length()<3){            
        tn.setStyle(styletxt);
        
        valttip=1;
    }
    if(te.getText().isEmpty() || te.getText().equals("") || te.getText().length()<3){
        te.setStyle(styletxt);
        valttip=1;
    }
    if(tt.getText().isEmpty() || tt.getText().equals("") || tt.getText().length()!=8){
        tt.setStyle(styletxt);
        valttip=1;   
    }
 
    
     }
        
       
    
    @FXML public void mostrar(){
        
        save.setDisable(true);
        te.setText(String.valueOf(Table.getSelectionModel().getSelectedItem().getEmail()));
        tn.setText(Table.getSelectionModel().getSelectedItem().getNombre());
        tt.setText(String.valueOf(Table.getSelectionModel().getSelectedItem().getTelefono()));
        
    }
    
    @FXML public void agregar(){
     te.setDisable(false);
     tt.setDisable(false);
     tn.setDisable(false);  
     save.setDisable(false);  
     Limpiar();
     
    }
    
    @FXML public void listar(){
             Criteria c = s.createCriteria(Proveedores.class)
        .addOrder( Order.asc("nombre") ).add(Restrictions.like("registro","Activo"));
             if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                
            }else{
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            Proveedores p= (Proveedores) pac;
            
            System.out.println(p.getNombre());
//            System.out.println(p.get);
            
            
        }
       Table.getItems().clear();
       Table.getItems().addAll(c.list()); 
             }   
    }
    
    @FXML public void cancelar(){
    
        Table.getItems().clear();
        Limpiar();
        
        
    }
    @FXML public void eliminar(){
              int id=(Table.getSelectionModel().getSelectedItem().getIdProveedor());
        s.getTransaction().begin();
        Query query = s.createQuery("update Proveedores set registro='Inactivo' where idProveedor= :Id");
        query.setParameter("Id", id);
        int result = query.executeUpdate();
        s.getTransaction().commit();
        Table.getItems().clear();
        cancelar();
       
    }
    
    @FXML public void save(){
        
                TooltipValidation();
  if(valttip==0){

                add();
                Limpiar();
                Table.getItems().clear();       
                     te.setDisable(true);
     tt.setDisable(true);
     tn.setDisable(true);  
     save.setDisable(true);   
          
  } else{
      valttip=0;
  }        
     
   
        
    }
    
    public void add(){
        
               try{
        Transaction t = s.beginTransaction();       
       Proveedores p= new Proveedores();
       
       p.setNombre(tn.getText());
       p.setEmail(te.getText());
       p.setTelefono(Integer.parseInt(tt.getText()));
       p.setRegistro("Activo");
       s.save(p);
       t.commit();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Fallo Conexion: "+e.toString()).show();
        }
        Table.getItems().clear();
        s.close();
        s= NewHibernateUtil.getSessionFactory().openSession();
    }
    
          public void Limpiar(){
        
        te.clear();
        tn.clear();
        tt.clear();

        
    }
          
          public void Inicial(){
                   te.setDisable(true);
     tt.setDisable(true);
     tn.setDisable(true);  
     save.setDisable(true);  
          }
                    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       ce.setCellValueFactory(new PropertyValueFactory<>("email"));
       cp.setCellValueFactory(new PropertyValueFactory<>("nombre"));
       ct.setCellValueFactory(new PropertyValueFactory<>("telefono"));   
     te.setDisable(true);
     tt.setDisable(true);
     tn.setDisable(true);  
     save.setDisable(true);  
     
     
        
            Table.setOnMousePressed(new EventHandler<MouseEvent>(){
        public void handle(MouseEvent me) {

            if(me.getClickCount()==1){
                System.out.println(Table.getSelectionModel().getSelectedIndex());
            if(Table.getSelectionModel().getSelectedIndex()==-1){

              Table.setContextMenu(conmenu);
              
              m1.setDisable(true);
              m2.setDisable(false);
              m3.setDisable(false);
              m4.setDisable(true);
              m5.setDisable(false);
              
              
//              JOptionPane.showMessageDialog(null, "Seleccione una Fila");
              
            }else{
             System.out.println(me.getButton());    
            if(me.getButton().toString()=="SECONDARY"){
                    System.out.println("clic derecho");
                
                    
                    Table.setContextMenu(conmenu);
              m1.setDisable(false);
              m2.setDisable(true);
              m3.setDisable(true);
              m4.setDisable(false);
              m5.setDisable(false);
            }
            }
          
            }
        }
                   });    
        
        
        
        
        // TODO
    }    
    
}
