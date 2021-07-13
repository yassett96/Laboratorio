/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Hibernate.NewHibernateUtil;
import POJO.Examen;
import POJO.Producto;
import POJO.Proveedores;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
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
public class ProductoController implements Initializable {

                SessionFactory factor;
    Session s = NewHibernateUtil.getSessionFactory().openSession();
    @FXML ContextMenu conmenu;
    @FXML public TableView<Producto> Table;
    @FXML public TableColumn<Producto, String> cp;
    @FXML public TableColumn ct;
    @FXML public TableColumn ce;
    @FXML TextField tp,te,tf,tm,ta,tmm,tj,tjj,tag,ts,to,tn,td;
    @FXML ComboBox cbox;
    @FXML MenuItem m1,m2,m3,m4,m5;
    @FXML Button save;
        int valttip=0;
        
    ObservableList<String> proveedores = FXCollections.observableArrayList();    
        

    public void Stylepred(){
        String stylep="-fx-border-color: black;";
        tp.setStyle(stylep);
        te.setStyle(stylep);
        tf.setStyle(stylep);
        tm.setStyle(stylep);
        ta.setStyle(stylep);
        tmm.setStyle(stylep);
        tj.setStyle(stylep);
        tjj.setStyle(stylep);
        tag.setStyle(stylep);
        ts.setStyle(stylep);
        to.setStyle(stylep);
        tn.setStyle(stylep);
        td.setStyle(stylep);

    }    
    
         public void TooltipValidation(){
         Stylepred();
 
    String style="-fx-background-color: orange,linear-gradient(to bottom, derive(orange,60%) 5%,derive(orange,90%) 40%);";
    
    String styletxt="-fx-border-color: red;";
   
    
    if(tp.getText().isEmpty() || tp.getText().equals("") ){            
        tp.setStyle(styletxt);
        
        valttip=1;
    }
    if(te.getText().isEmpty() || te.getText().equals("") ){            
        te.setStyle(styletxt);
        
        valttip=1;
    }
        if(tf.getText().isEmpty() || tf.getText().equals("") ){            
        tf.setStyle(styletxt);
        
        valttip=1;
    }
        if(tm.getText().isEmpty() || tm.getText().equals("") ){            
        tm.setStyle(styletxt);
        
        valttip=1;
    }
        if(tmm.getText().isEmpty() || tmm.getText().equals("")){            
        tmm.setStyle(styletxt);
        
        valttip=1;
    }
        if(ta.getText().isEmpty() || ta.getText().equals("") ){            
        ta.setStyle(styletxt);
        
        valttip=1;
    }
        if(tj.getText().isEmpty() || tj.getText().equals("") ){            
        tj.setStyle(styletxt);
        
        valttip=1;
    }
        if(tjj.getText().isEmpty() || tjj.getText().equals("")){            
        tjj.setStyle(styletxt);
        
        valttip=1;
    }
        if(tag.getText().isEmpty() || tag.getText().equals("")){            
        tag.setStyle(styletxt);
        
        valttip=1;
    }
        if(ts.getText().isEmpty() || ts.getText().equals("")){            
        ts.setStyle(styletxt);
        
        valttip=1;
    }
        if(to.getText().isEmpty() || to.getText().equals("")){            
        to.setStyle(styletxt);
        
        valttip=1;
    }
        if(tn.getText().isEmpty() || tn.getText().equals("")){            
        tn.setStyle(styletxt);
        
        valttip=1;
    }
        if(td.getText().isEmpty() || td.getText().equals("")){            
        td.setStyle(styletxt);
        
        valttip=1;
    }
    
 
    
     }
        
       
    
    @FXML public void mostrar(){
        
        cbox.setDisable(true);
        save.setDisable(true);
        tp.setText(Table.getSelectionModel().getSelectedItem().getProducto());
        te.setText(Table.getSelectionModel().getSelectedItem().getEnero());
        tf.setText(Table.getSelectionModel().getSelectedItem().getFebrero());
        tm.setText(Table.getSelectionModel().getSelectedItem().getMarzo());
        ta.setText(Table.getSelectionModel().getSelectedItem().getAbril());
        tmm.setText(Table.getSelectionModel().getSelectedItem().getMayo());
        tj.setText(Table.getSelectionModel().getSelectedItem().getJunio());
        tjj.setText(Table.getSelectionModel().getSelectedItem().getJulio());
        tag.setText(Table.getSelectionModel().getSelectedItem().getAgosto());
        ts.setText(Table.getSelectionModel().getSelectedItem().getSeptiembre());
        to.setText(Table.getSelectionModel().getSelectedItem().getOctubre());
        tn.setText(Table.getSelectionModel().getSelectedItem().getNoviembre());
        td.setText(Table.getSelectionModel().getSelectedItem().getDiciembre());
        
    }
    
    @FXML public void agregar(){
     tp.setDisable(false);
     te.setDisable(false);
     tf.setDisable(false);
     tm.setDisable(false);
     ta.setDisable(false);
     tmm.setDisable(false);
     tj.setDisable(false);
     tjj.setDisable(false);
     tag.setDisable(false);
     ts.setDisable(false);
     to.setDisable(false);
     tn.setDisable(false);
     td.setDisable(false);
     cbox.setDisable(false);
  
     save.setDisable(false);  
     Limpiar();
     
    }
    
    @FXML public void listar(){
             Criteria c = s.createCriteria(Producto.class)
        .addOrder( Order.asc("producto") ).add(Restrictions.like("registro","Activo"));
             if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                
            }else{
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            Producto p= (Producto) pac;
            
            System.out.println(p.getProducto());
//            System.out.println(p.get);
            
            
        }
               ct.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producto, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Producto, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getProveedores().getTelefono());
            }
        });

        ce.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Producto, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getProveedores().getNombre());
            }
        }); 
       Table.getItems().clear();
       Table.getItems().addAll(c.list()); 
             }   
    }
    
    @FXML public void cancelar(){
    
        Table.getItems().clear();
        Limpiar();
        
        
    }
    @FXML public void eliminar(){
              int id=(Table.getSelectionModel().getSelectedItem().getIdProducto());
        s.getTransaction().begin();
        Query query = s.createQuery("update Producto set registro='Inactivo' where idProducto= :Id");
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
     tp.setDisable(true);
     te.setDisable(true);
     tf.setDisable(true);
     tm.setDisable(true);
     ta.setDisable(true);
     tmm.setDisable(true);
     tj.setDisable(true);
     tjj.setDisable(true);
     tag.setDisable(true);
     ts.setDisable(true);
     to.setDisable(true);
     tn.setDisable(true);
     td.setDisable(true);
     cbox.setDisable(true);
     
     save.setDisable(true);   
          
  } else{
      valttip=0;
  }        
     
   
        
    }
    
    public void add(){
        
        
               try{
        Transaction t = s.beginTransaction();       
       Proveedores p= new Proveedores();
       Producto pro = new Producto();
       p.setIdProveedor(Integer.parseInt(cbox.getValue().toString().substring(0,1)));
       System.out.println("to "+cbox.getValue().toString().substring(0,1));
       
       pro.setProducto(tp.getText());
       pro.setEnero(te.getText());
       pro.setFebrero(tf.getText());
       pro.setMarzo(tm.getText());
       pro.setAbril(ta.getText());
       pro.setMayo(tmm.getText());
       pro.setJunio(tj.getText());
       pro.setJulio(tjj.getText());
       pro.setAgosto(tag.getText());
       pro.setSeptiembre(ts.getText());
       pro.setOctubre(to.getText());
       pro.setNoviembre(tn.getText());
       pro.setDiciembre(td.getText());
       pro.setRegistro("Activo");
       pro.setProveedores(p);
       s.save(pro);
       t.commit();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Fallo Conexion: "+e.toString()).show();
        }
        Table.getItems().clear();
        s.close();
        s= NewHibernateUtil.getSessionFactory().openSession();
    }
    
          public void Limpiar(){
        
        tp.clear();
        te.clear();
        tf.clear();
        tm.clear();
        ta.clear();
        tmm.clear();
        tj.clear();
        tjj.clear();
        tag.clear();
        ts.clear();
        to.clear();
        tn.clear();
        td.clear();
        
    }
          
          public void Inicial(){
        tp.setDisable(true);
        te.setDisable(true);
        tf.setDisable(true);
        tm.setDisable(true);
        ta.setDisable(true);
        tmm.setDisable(true);
        tj.setDisable(true);
        tjj.setDisable(true);
        tag.setDisable(true);
        ts.setDisable(true);
        to.setDisable(true);
        tn.setDisable(true);
        td.setDisable(true);
        cbox.setDisable(true);
        
        
     save.setDisable(true);  
          }



        
    public void Consulta(){
        Query query = s.createQuery("            select CONCAT(pr.idProveedor,': ',pr.nombre)\n" +
                "    from Proveedores as pr\n" +
                "    where pr.registro='Activo'");
        query.list();
        
        for(int i=0;i<query.list().size();i++){
            System.out.println(query.list().get(i));
            proveedores.add(query.list().get(i).toString());
        }
        
        cbox.setItems(proveedores);
        
            }    
        
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Consulta();  
       cp.setCellValueFactory(new PropertyValueFactory<>("producto"));  
       tp.setDisable(true);
       te.setDisable(true);
       tf.setDisable(true);
       tm.setDisable(true);
       ta.setDisable(true);
       tmm.setDisable(true);
       tj.setDisable(true);
       tjj.setDisable(true);
       tag.setDisable(true);
       ts.setDisable(true);
       to.setDisable(true);
       tn.setDisable(true);
       td.setDisable(true);
       save.setDisable(true);
     cbox.setDisable(true);
        
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
