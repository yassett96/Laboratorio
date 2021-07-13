/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import Hibernate.NewHibernateUtil;
import Hibernate.NewHibernateUtil;
import POJO.Medicos;
import POJO.Usuario;
import Validaciones.Cifrado;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;
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
public class UsuariosController implements Initializable {
//    Llamado de los componentes del formulario de Embarazo JAVAFX
        SessionFactory factor;
     Session s =  NewHibernateUtil.getSessionFactory().openSession();
     @FXML TableView<Usuario> tableuser;
     @FXML TableColumn<Usuario,String> columuser;
     @FXML public TableColumn columnoc,columnom,columc;
     @FXML ContextMenu c1,c2;
     @FXML MenuItem add,delete,list,cancelar;
     @FXML Button add2;
     @FXML TextField u;
     @FXML PasswordField con;
     @FXML ComboBox c;
     
    ObservableList<String> choices = FXCollections.observableArrayList();
    public String name;
    public String pass;
    public int id;
    byte[] encrypt; 
     
     @FXML public void cancel(){
         tableuser.getItems().clear();
     }
     
     @FXML public void agregar() throws Exception{
     Criteria h = s.createCriteria(Medicos.class).add(Restrictions.like("registro","Activo"))
     .addOrder( Order.asc("idMedico") );
     for(Object p: h.list()){
         Medicos m = (Medicos) p;
         choices.add("No: "+m.getIdMedico()+" Nombre: "+m.getNombre()+" "+m.getApellido());
     }
     
     
     c.setItems(choices);
                   
     }
     
     @FXML public void nuevo() throws Exception{
       
      Retornar();
      
      if(Retornar()==0){
          add();
      }else{
          Reactivar();
      }
     }
     
     public int Retornar(){
     id= Integer.parseInt(c.getValue().toString().substring(4,5));
      
     Query query = s.createQuery("from Usuario where idUsuario=:Id");
     query.setParameter("Id", id);
    
     System.out.println(query.list().size());
     
     return query.list().size();
     }
     
     
     
     @FXML public void Reactivar() throws Exception{


         name=u.getText();
         pass=con.getText();

            encrypt= Cifrado.cifra(pass);
            System.out.println(encrypt);
         
       s.getTransaction().begin();
        Query query = this.s.createQuery("update Usuario set nombre=:name,"
                + "password=:enc,registro='Activo' where idUsuario= :Id");
        query.setParameter("name", name);
        query.setParameter("enc", encrypt);
        query.setParameter("Id",id);
        
        int result = query.executeUpdate();
        s.getTransaction().commit();
        new Alert(Alert.AlertType.INFORMATION,"Usuario Agregado").show();
        s.close();
        s = NewHibernateUtil.getSessionFactory().openSession();
        listar();         
         
     }
     @FXML public void add() throws Exception{

         id= Integer.parseInt(c.getValue().toString().substring(4,5));
         name=u.getText();
         pass=con.getText();
         
         System.out.println(pass);
         
         
            encrypt= Cifrado.cifra(pass);
            System.out.println(encrypt);
                   try{
        Transaction t = s.beginTransaction();
        
        Medicos m = new Medicos();
        m.setIdMedico(id);
        
        
        Usuario u = new Usuario();
       System.out.println(name);
       
       u.setNombre(name);
       u.setPassword(encrypt);
       u.setRegistro("Activo");
       u.setMedicos(m);
       
       s.save(u);
       
       t.commit();
       new Alert(Alert.AlertType.INFORMATION,"Usuario Agregado").show();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Fallo Conexion: "+e.toString());    
        }
       s.close();
       s = NewHibernateUtil.getSessionFactory().openSession();
       listar();
                   
     
     }
     
     @FXML public void eliminar(){
       int idd=tableuser.getSelectionModel().getSelectedItem().getIdUsuario();
         System.out.println(idd);
       s.getTransaction().begin();
        Query query = this.s.createQuery("update Usuario set registro='Inactivo' where idUsuario= :Id");
        query.setParameter("Id",idd);
        int result = query.executeUpdate();
        s.getTransaction().commit();
        new Alert(Alert.AlertType.INFORMATION,"Usuario Eliminado").show();
        s.close();
        s = NewHibernateUtil.getSessionFactory().openSession();
        listar();
        
     }
     
     @FXML public void listar(){
            Criteria c = s.createCriteria(Usuario.class).add(Restrictions.like("registro","Activo"))
        .addOrder( Order.asc("nombre") ); 
 
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            Usuario p= (Usuario) pac;
        }

        columnoc.setCellValueFactory(new Callback<CellDataFeatures<Usuario,Integer>,ObservableValue<Integer>>(){       
                public ObservableValue<Integer> call(CellDataFeatures<Usuario, Integer> p) {
                   return new ReadOnlyObjectWrapper(p.getValue().getMedicos().getNoCedula());
                }            
        });

        columnom.setCellValueFactory(new Callback<CellDataFeatures<Usuario,String>,ObservableValue<String>>(){       
                public ObservableValue<String> call(CellDataFeatures<Usuario, String> p) {
                   return new ReadOnlyObjectWrapper(p.getValue().getMedicos().getNombre()+" "+p.getValue().getMedicos().getApellido());
                }            
        });
        
        columc.setCellValueFactory(new Callback<CellDataFeatures<Usuario,String>,ObservableValue<String>>(){       
                public ObservableValue<String> call(CellDataFeatures<Usuario, String> p) {
                   return new ReadOnlyObjectWrapper(p.getValue().getMedicos().getCargo());
                }            
        });   
        
     tableuser.getItems().clear();
     tableuser.getItems().addAll(c.list());
     
     }
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    columuser.setCellValueFactory(new PropertyValueFactory<> ("nombre"));
    
            tableuser.setOnMousePressed(new EventHandler<MouseEvent>(){
        public void handle(MouseEvent me) {
            if(me.getClickCount()==1){
            if(me.getButton().toString()=="SECONDARY"){
                    System.out.println("clic derecho");
            if(tableuser.getSelectionModel().getSelectedIndex()==-1){

              tableuser.setContextMenu(c1);
             add.setVisible(true);
             list.setVisible(true);
             delete.setVisible(false);
             cancelar.setVisible(true);
              
            }else{
             tableuser.setContextMenu(c1); 
             add.setVisible(false);
             list.setVisible(false);
             delete.setVisible(true);
             cancelar.setVisible(true);
             
            }
            }

          
            }
        }
                   });
            
           
        
    }    
    
}
