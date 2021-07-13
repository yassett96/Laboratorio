
package Controladores;

//import Hibernate.NewHibernateUtil;
import Hibernate.NewHibernateUtil;
import POJO.*;
import POJO.Examen;
import POJO.Medicos;
import POJO.Pacientes;
import Utilidades.config2;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import Controladores.*;
import Utilidades.ClaseTemporal;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
public class RegCitas implements Initializable {

    SessionFactory factor;
    Session s = NewHibernateUtil.getSessionFactory().openSession();
//    Map<String, String> map = new HashMap<>();
    
    
    int idex;
    @FXML
    ContextMenu conmenu;
    @FXML
    MenuItem meneli, menmos, menmodi;
    
    @FXML
    public TableView<Examen> tableexa;
    @FXML
    public TableColumn columnoe;
    @FXML
    public TableColumn columnom;

    @FXML
    public TableColumn<Examen, String> columtipo;
    @FXML
    public TableColumn<Examen, String> columestado;

    @FXML TextField txthora;
    @FXML DatePicker fechan;
    
    Alert alertcon = new Alert(Alert.AlertType.CONFIRMATION);
    
    

    public void criterialist() {

        Criteria c = s.createCriteria(Examen.class).add(Restrictions.like("registro","Activo"))
                .add(Restrictions.eq("estado", "Sin Cita"))
                .addOrder(Order.asc("estado"));
//        System.out.println(c.list());
            if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancel();
                Inicial();
                
            }else{
        columnoe.setCellValueFactory(new Callback<CellDataFeatures<Examen, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(CellDataFeatures<Examen, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPacientes().getNoExpediente());
            }
        });

        columnom.setCellValueFactory(new Callback<CellDataFeatures<Examen, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Examen, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPacientes().getNombres());
            }
        });

        tableexa.getItems().clear();
        tableexa.getItems().addAll(c.list());
        System.out.println("aq");
//       tableexa.getColumns().add(columnom);

//       tableexa.getItems().add(columnom.getColumns().iterator().next());

            }
//         retornar();
    }

//        




    @FXML
    public void cancel() {
        tableexa.getItems().clear();
        Limpiar();
        Inicial();
 

    }
    
    
   


    public void Inicial() {
//        tableexa.setDisable(true);
//        find.setDisable(false);
//        list.setDisable(false);
//        save.setDisable(true);
//        cancel.setDisable(true);
//        txtb.setDisable(false);
           fechan.setDisable(true);
           txthora.setDisable(true);
           tableexa.setDisable(false);
////        apellidos.setDisable(true);
//        Noe.setDisable(true);
////        Nombres.setDisable(true);
////        estado.setDisable(true);
//        combocat.setDisable(true);
//        combocat.setItems(categoria);
//        cita.setItems(citalist);
//        combocat.setValue("");
//        comboexa.setDisable(true);
//        comboexa.setValue("");
////        fechan.setDisable(true);
//        valor = 0;
//        listPac.add(new Paciente)
    }
    

@FXML public void add(){
    
           fechan.setDisable(false);
           txthora.setDisable(false);
           tableexa.setDisable(true);

           
           
}

public void addcriteria(){
 
                   try{
        Transaction t = s.beginTransaction();  
               idex=tableexa.getSelectionModel().getSelectedItem().getIdExamen();
          
            Examen e = new Examen();
            e.setIdExamen(idex);    
               
           Citas c = new Citas();
           c.setFecha(new Date(fechan.getValue().format(DateTimeFormatter.ISO_DATE).replace('-', '/')));
           c.setHora(txthora.getText());
           c.setExamen(e);
           
                  s.save(c);
                  t.commit();
           
                   } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Fallo Conexion: "+e.toString()).show();
        }
                   
                   s.close();
                   s=NewHibernateUtil.getSessionFactory().openSession();
                   
                   estado();
    
}

public void estado(){
    
        s.getTransaction().begin();
        Query query = s.createQuery("update Examen set Estado='Pendiente' where idExamen= :Id");
        query.setParameter("Id", idex);
        int result = query.executeUpdate();
        s.getTransaction().commit();
        tableexa.getItems().clear();
        cancel();
        criterialist();
       
    
}

    public void Limpiar() {

        fechan.setValue(null);
        txthora.clear();
//        criterialist();
        
//        combocat.setValue("");
//        comboexa.setValue("");
////        fechan.setValue(null);
////        estado.clear();
//        apellidos.clear();
//        Nombres.clear();
//        Noe.clear();
//        valor = 0;

    }
    
    
    public static int getDayOfTheWeek(Date d){
	GregorianCalendar cal = new GregorianCalendar();
	cal.setTime(d);
	return cal.get(Calendar.DAY_OF_WEEK);		
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        criterialist();
//        cita.valueProperty().addListener((p1, ov, p2) -> {
////       medicoshoy.clear();
////        cargomedico=(String) p1.getValue();
////        System.out.println(cargomedico);
//        if(cita.getValue().toString()=="Emergencia"){
//           Listaemergencia();
//        }else{
//            Listacita();
//        }
//
//        });        
        
      
//        Nombres.setEditable(false);
//        apellidos.setEditable(false);

        
        
//        globonom.setVisible(false);
//        Calendar now = Calendar.getInstance();
//        System.out.println("Fecha actual : " + (now.get(Calendar.MONTH) + 1)
//						+ "-"
//						+ now.get(Calendar.DATE)
//						+ "-"
//						+ now.get(Calendar.YEAR));
//        System.out.println("Hoy es : " + strDays[now.get(Calendar.DAY_OF_WEEK) - 1]);
//        fechadehoy=(strDays[now.get(Calendar.DAY_OF_WEEK) - 1]);
//        System.out.println(fechadehoy);
//        System.out.println(fechadehoy);
//        tablePac.getColumns().addAll(columnoe,columnom,columape,columnedad,columsala);
//        columid.setCellValueFactory(new PropertyValueFactory<>("idExamen"));
//        columnom.setCellValueFactory(new PropertyValueFactory<>("pacientes.nombres"));
        columtipo.setCellValueFactory(new PropertyValueFactory<>("examenTipo"));
        columestado.setCellValueFactory(new PropertyValueFactory<>("estado"));
//        columsala.setCellValueFactory(new PropertyValueFactory<>("sala"));
//        rNoe.setSelected(true);
        
        
        
        
        
        
            tableexa.setOnMousePressed(new EventHandler<MouseEvent>(){
        public void handle(MouseEvent me) {

            if(me.getClickCount()==1){
                System.out.println(tableexa.getSelectionModel().getSelectedIndex());
            if(tableexa.getSelectionModel().getSelectedIndex()==-1){

              tableexa.setContextMenu(conmenu);
              
              menmos.setDisable(true);
              menmodi.setDisable(true);
              meneli.setDisable(false);

              
              
//              JOptionPane.showMessageDialog(null, "Seleccione una Fila");
              
            }else{
             System.out.println(me.getButton());    
            if(me.getButton().toString()=="SECONDARY"){
                    System.out.println("clic derecho");
                
                    
              tableexa.setContextMenu(conmenu);
              menmos.setDisable(false);
              menmodi.setDisable(false);
              meneli.setDisable(true);
            }
            }
          
            }
        }
                   }); 

        Inicial();
   

        
        
    }

}
