/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import static Controladores.RegExaController.isNumeric;

import Hibernate.NewHibernateUtil;
import POJO.Examen;
import POJO.ExamenesVarios;
import POJO.Medicos;
import POJO.Pacientes;
import Utilidades.ClaseTemporal;
import Utilidades.config2;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
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
public class VariosController implements Initializable {
    
        SessionFactory factor;
    Session s = NewHibernateUtil.getSessionFactory().openSession();
    
    
        @FXML
    public RadioButton rNoe, rNom;
    @FXML TextField Noe,Nombres,apellidos,res,obs,cc,ct,txtb;
    @FXML Button save,cancel,find,add,listar;
     @FXML
    ContextMenu conmenu;
   @FXML
    MenuItem menmos, menmodi;
    public static Stage subStage1 = new Stage();; 
   
//    @FXML DatePicker fechan;
    private int valor;
    private Integer idPac;
    int valttip=0;
    int idmedicoactual;
        @FXML
    public TableView<ExamenesVarios> tablev;
    @FXML
    public TableColumn cln;
//    TableColumn<Map.Entry<String, String>, String> columnom ;
    @FXML
    public TableColumn cle;
    @FXML
    public TableColumn<ExamenesVarios, Date> clf;
    @FXML
    public TableColumn<ExamenesVarios, String> clr;
    private int idexamen;
    int existe=0;
 
        @FXML
    public TableView<Examen> tableexa;
    @FXML
    public TableColumn columnoe1;
//    TableColumn<Map.Entry<String, String>, String> columnom ;
    @FXML
    public TableColumn columnom1;
    @FXML
    public TableColumn<Examen, String> columcat;
    @FXML
    public TableColumn<Examen, String> columtipo;
    @FXML
    public TableColumn<Examen, String> columestado;
    
    
    public void addcriteria() {

        try {
            Transaction t = s.beginTransaction();
    
            ExamenesVarios v = new ExamenesVarios();
            Examen e = new Examen();
           
            e.setIdExamen(idexamen);
//            e.setEstado("Realizado");
            

            v.setExamenPracticado(ct.getText());
//            v.setFecha(new Date(fechan.getValue().format(DateTimeFormatter.ISO_DATE).replace('-', '/')));
            v.setObservaciones(obs.getText());
//            v.setRegistro("Activo");
            v.setResultado(res.getText());
            v.setExamen(e);
            v.setRegistro("Activo");
            s.save(v);
            
            Query query = s.createQuery("update Examen set estado='Realizado',Id_Medico=:me where idExamen=:Id");
            query.setParameter("Id", idexamen);
            query.setParameter("me", idmedicoactual);
            query.executeUpdate();
            
            
//       s.update(p);
            new Alert(Alert.AlertType.INFORMATION,"Examen Agregado").show();
            t.commit();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Fallo Conexion: "+e.toString()).show();

//        }finally
//        {
//            s.close();
        }
        valor = 0;
        tablev.getItems().clear();
        
        s.close();
        s=NewHibernateUtil.getSessionFactory().openSession();
        
    }

    
    
    @FXML public void save(){
   
               TooltipValidation();
  if(valttip==0){
                                
           if (valor == 1) {
                editcriteria();
            } else {
                addcriteria();
            }
            Limpiar();
            Inicial();
            valor = 0;
            tablev.getItems().clear();
  } else{
      valttip=0;
  }   
   
    }
    
    @FXML public void report() throws SQLException{
                    String path = "src\\Reportes\\ReporteExamenIndividualV.jasper";
            Connection a = DriverManager.getConnection("jdbc:mysql://localhost:3306/Laboratorio","root","Sistemas");

                    Map parameters = new HashMap();
                    parameters.put("IdExamen", tablev.getSelectionModel().getSelectedItem().getIdExamenV());
//      InputStream reporte = this.getClass().getResourceAsStream("/Reportes/Medicos.jasper");
      JasperPrint jasperPrint; 
        try {
            jasperPrint = JasperFillManager.fillReport(path, parameters,a);
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("Reporte de Medicos");
            jv.setVisible(true); 
        } catch (JRException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void editcriteria() {
        int id = (tablev.getSelectionModel().getSelectedItem().getIdExamenV());
        System.out.println(tablev.getSelectionModel().getSelectedItem().getIdExamenV());
//        System.out.println("tt"+tableexa.getSelectionModel().getSelectedItem().getEstado());
        this.s.getTransaction().begin();
        

//        System.out.println();
        Query query= s.createQuery("    update ExamenesVarios set resultado=:res,\n" +
        "    observaciones=:obs \n" +
        "    where idExamenV=:Id");
        query.setParameter("res", res.getText());
        query.setParameter("obs", obs.getText());
//        query.setParameter("fe",(new Date(fechan.getValue().format(DateTimeFormatter.ISO_DATE).replace('-', '/'))));
        query.setParameter("Id", id);

        int result = query.executeUpdate();

        s.getTransaction().commit();
            new Alert(Alert.AlertType.INFORMATION,"Examen Modificado").show();
//        config2.dialog(Alert.AlertType.INFORMATION, "Success Delete");
        tablev.getItems().clear();
        s.close();
        s = NewHibernateUtil.getSessionFactory().openSession();
    }    
    
    
    @FXML public void cancel(){
        tablev.getItems().clear();
        Limpiar();
        Inicial();
        valor = 0;
    }
    
    @FXML public void find(){
               if (rNoe.isSelected()) {
            findnoe();
        }
        if (rNom.isSelected()) {
            findnom();
        }

        txtb.setDisable(true);
        
        
    }

    public void findnoe() {
        String bus = txtb.getText();
        System.out.println(bus);
        if (bus.isEmpty() || bus.equals("") || bus.length() < 2) {
            new Alert(Alert.AlertType.WARNING,"No deje Campos Vacios o Nulos").show();
            cancel.setDisable(false);
        } else {

                    Query query = s.createQuery("    select new map (v.idExamenV)\n" +
"   from Examen as e\n" +
"   inner join e.pacientes as p\n" +
"   inner join e.examenesVarios as v\n" +
"    where p.noExpediente=:bus");
        query.setParameter("bus", Integer.parseInt(bus));
        List <Map> lista = query.list();
        Iterator<String> it=lista.get(0).keySet().iterator();
        idexamen=Integer.parseInt(lista.get(0).get(it.next()).toString()); 
            System.out.println(idexamen);
            Criteria c = s.createCriteria(ExamenesVarios.class).add(Restrictions.like("registro","Activo"))
                    .add(Restrictions.eq("idExamenV", idexamen));

            if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancel();
                Inicial();
            }else{
            System.out.println("Personas Encontradas:" + c.list().size());
            for (Object pac : c.list()) {
                ExamenesVarios p = (ExamenesVarios) pac;
//            System.out.println(" - " +p.getNoExpediente()+" - "+p.getNombres());

            }

        cle.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenesVarios, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenesVarios, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getExamenTipo());
            }
        });

        cln.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenesVarios, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenesVarios, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNombres()+
                        " "+p.getValue().getExamen().getPacientes().getApellidos());
            }
        });
            tablev.getItems().clear();
            tablev.getItems().addAll(c.list());
            Limpiar();
            Inicial();
            valor = 0;
                        }
        }
    }


    public void findnom() {
        String bus = txtb.getText();
        System.out.println(bus);
        if (bus.isEmpty() || bus.equals("") || bus.length() < 3) {
            new Alert(Alert.AlertType.ERROR,"No deje campos vacios o pocos Caracteres");
            cancel.setDisable(false);
        } else {
            
            
        Query query = s.createQuery("    select new map (v.idExamenV)\n" +
"   from Examen as e\n" +
"   inner join e.pacientes as p\n" +
"   inner join e.examenesVarios as v\n" +
"    where p.nombres like :no");
        query.setParameter("no", bus+"%");
        List <Map> lista = query.list();
        Iterator<String> it=lista.get(0).keySet().iterator();
        idexamen=Integer.parseInt(lista.get(0).get(it.next()).toString());            

        Criteria c = s.createCriteria(ExamenesVarios.class).add(Restrictions.like("registro","Activo"))
         .add(Restrictions.eq("idExamenV", idexamen));
//        System.out.println(c.list());
            if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancel();
                Inicial();
                
            }else{
        cle.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenesVarios, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenesVarios, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getExamenTipo());
            }
        });

        cln.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenesVarios, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenesVarios, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNombres()+
                        " "+p.getValue().getExamen().getPacientes().getApellidos());
            }
        });
        
            tablev.getItems().clear();
            tablev.getItems().addAll(c.list());
            Limpiar();
            Inicial();
            valor = 0;
        
    }    

        }
        
    }

    
      
        
        @FXML
    public void rNoe() {
        rNom.setSelected(false);

    }

    @FXML
    public void rNom() {
        rNoe.setSelected(false);
    }    
        
        
        

 
    
    
    
    @FXML public void add(){
             
        Noe.setDisable(false);
        find.setDisable(true);
        listar.setDisable(true);
        cancel.setDisable(false);
        save.setDisable(false);
        
        valor = 0;

        ListaExamenes();
        tablev.getItems().clear();
   
    } 

    @FXML public void select(){
    
    idexamen=tableexa.getSelectionModel().getSelectedItem().getIdExamen();
    Noe.setText(tableexa.getSelectionModel().getSelectedItem().getPacientes().getNoExpediente().toString());
    Nombres.setText(tableexa.getSelectionModel().getSelectedItem().getPacientes().getNombres());
    apellidos.setText(tableexa.getSelectionModel().getSelectedItem().getPacientes().getApellidos());
    cc.setText(tableexa.getSelectionModel().getSelectedItem().getCategoria());
    ct.setText(tableexa.getSelectionModel().getSelectedItem().getExamenTipo());    
    }

    
    @FXML public void mod(){
        Habilitar();
//        int id = 2;
        find.setDisable(true);
        listar.setDisable(true);
        cancel.setDisable(false);
        save.setDisable(false);
        Nombres.setDisable(true);

        valor = 1;
    }     

    @FXML public void elim(){
        //        Evento();
//        suprimir();
        valor = 0;
        tablev.getItems().clear();
    }     


    
    
    @FXML public void listar(){
        Deshabilitar();
        tablev.setDisable(false);
        criterialist();
//            Evento();
        listar.setDisable(true);

        cancel.setDisable(false);
        valor = 0;  
    }     
  
        public void criterialist() {

        Criteria c = s.createCriteria(ExamenesVarios.class).add(Restrictions.like("registro","Activo"))
                .addOrder(Order.asc("observaciones"));
//        System.out.println(c.list());
            if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancel();
                Inicial();
                
            }else{
        cle.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenesVarios, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenesVarios, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getExamenTipo());
            }
        });

        cln.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenesVarios, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenesVarios, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNombres()+
                        " "+p.getValue().getExamen().getPacientes().getApellidos());
            }
        });


        tablev.getItems().clear();
        tablev.getItems().addAll(c.list());
        System.out.println("aq");
//       tableexa.getColumns().add(columnom);

//       tableexa.getItems().add(columnom.getColumns().iterator().next());
        valor = 0;
            }
//         retornar();
    }
    
    
    /**
     * Initializes the controller class.
     */
    
        public static boolean isNumeric(String str) {
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("") == false);
    }
    
        
        public void ya(){
    
    this.idexamen=ClaseTemporal.idexamen;

    
        System.out.println("Este id es actual: "+this.idexamen); 
    
        Criteria c = s.createCriteria(Examen.class)
        .add(Restrictions.eq("idExamen", idexamen));
        
        for (Object pac : c.list()) {
        Examen p = (Examen) pac;
            Nombres.setText(p.getPacientes().getNombres());
            apellidos.setText(p.getPacientes().getApellidos());
            cc.setText(p.getCategoria());
            ct.setText(p.getExamenTipo());

            }
        
        obs.setDisable(false);
        res.setDisable(false);
}    
        
        
    
        public void Deshabilitar() {
        tablev.setDisable(true);
        Nombres.setDisable(true);
//        estado.setDisable(true);
        apellidos.setDisable(true);
        Noe.setDisable(true);
        cc.setDisable(true);
        ct.setDisable(true);


        valor = 0;
    }
        
        public void Habilitar() {
        tablev.setDisable(false);
        Nombres.setDisable(true);
//        estado.setDisable(false);
        apellidos.setDisable(true);
        Noe.setDisable(false);
        res.setDisable(false);
        obs.setDisable(false);
        cc.setDisable(true);
        ct.setDisable(true);
        
        valor = 0;
    }    
    
        public void Inicial(){
        add.setDisable(false);
//        buscar.setDisable(false);
        listar.setDisable(false);
//        modif.setDisable(true);
//        elim.setDisable(true);
        save.setDisable(true);
        cancel.setDisable(true);
        find.setDisable(false);
        Nombres.setDisable(true);
        apellidos.setDisable(true);
        Noe.setDisable(true);
        cc.setDisable(true);
        ct.setDisable(true);
        res.setDisable(true);
        obs.setDisable(true);
//        fechan.setDisable(true);
 
//        Combosemana.setItems(semanalist);
    }  
        
        public void Limpiar() {

        cc.clear();
        ct.clear();
//        fechan.setValue(null);
//        estado.clear();
        apellidos.clear();
        Nombres.clear();
        Noe.clear();
        res.clear();
        obs.clear();
        valor = 0;

    }    
        
        
   public void ListaExamenes(){
       
       ObservableList<String> minilist = FXCollections.observableArrayList("Emergencia","Pendiente");
       Criteria c = s.createCriteria(Examen.class).add(Restrictions.like("registro","Activo"))
       .add(Restrictions.in("estado", minilist))
       .addOrder( Order.asc("estado") )
       .add(Restrictions.not(Restrictions.eq("categoria", "Heces")))
       .add(Restrictions.not(Restrictions.eq("categoria", "Embarazo")))
       .add(Restrictions.not(Restrictions.eq("categoria", "Orina")));
                   if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancel();
                Inicial();
                
            }else{
        columnoe1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Examen, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Examen, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPacientes().getNoExpediente());
            }
        });

        columnom1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Examen, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Examen, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPacientes().getNombres());
            }
        });

        tableexa.getItems().clear();

        tableexa.getItems().addAll(c.list());
        System.out.println("aq");
//       tableexa.getColumns().add(columnom);

//       tableexa.getItems().add(columnom.getColumns().iterator().next());
        valor = 0;
            } 
       
   }        
        
        
          
    @FXML public void Evento() {
//        EditarO.setDisable(false);
//        EliminarO.setDisable(false);
        cancel.setDisable(false);
        add.setDisable(true);
//  int noexp = TablaOrina.getSelectionModel().getSelectedItem().getExamen().getPacientes().getNoExpediente();
      Noe.setText(String.valueOf(tablev.getSelectionModel().getSelectedItem().getExamen().getPacientes().getNoExpediente()));
      Nombres.setText(tablev.getSelectionModel().getSelectedItem().getExamen().getPacientes().getNombres());
      apellidos.setText(tablev.getSelectionModel().getSelectedItem().getExamen().getPacientes().getApellidos());
      cc.setText(tablev.getSelectionModel().getSelectedItem().getExamen().getExamenTipo());
      ct.setText(tablev.getSelectionModel().getSelectedItem().getExamen().getCategoria());
//      String año = tablev.getSelectionModel().getSelectedItem().getExamen().getExamenesVarios().getFecha().toString().substring(0,4);
//        String mes=tablev.getSelectionModel().getSelectedItem().getExamen().getExamenesVarios().getFecha().toString().substring(5,7);
//        String dia=tablev.getSelectionModel().getSelectedItem().getExamen().getExamenesVarios().getFecha().toString().substring(8,10);
//        fechan.setValue(LocalDate.of(Integer.parseInt(año),Integer.parseInt(mes),Integer.parseInt(dia)));
//        
      res.setText(tablev.getSelectionModel().getSelectedItem().getExamen().getExamenesVarios().getResultado());
      obs.setText(tablev.getSelectionModel().getSelectedItem().getExamen().getExamenesVarios().getObservaciones());


    }
    
     @FXML
    public void MetodoClick() throws IOException {
        
        Evento();
        EditarO();
        
    }
      public void EditarO(){
         this.Habilitar();
//        res.setDisable(false);
        this.add.setDisable(true);
//        this.camera.setDisable(false);
        this.listar.setDisable(true);
//        this.EditarO.setDisable(true);
//        this.EliminarO.setDisable(true);
        this.cancel.setDisable(false);
        this.save.setDisable(false);
        this.tablev.setDisable(true);
//        validarcargo=1;
        this.valor = 1;
     
     }
      
      
       public void Stylepred(){
        String stylep="-fx-border-color: black;";
//        noExph.setStyle(stylep);
      res.setStyle(stylep);
      obs.setStyle(stylep);
      }
    
        public void TooltipValidation(){
         Stylepred();
           
    String style="-fx-background-color: orange,linear-gradient(to bottom, derive(orange,60%) 5%,derive(orange,90%) 40%);";
    
    String styletxt="-fx-border-color: red;";
   
    
    if(res.getText().isEmpty() || res.getText().equals("") || res.getText().length()<3){            
        res.setStyle(styletxt);
        
           valttip = 1;
    }
           
    if(obs.getText().isEmpty() || obs.getText().equals("") || obs.getText().length()<3){
        obs.setStyle(styletxt);
        valttip=1;
        
    }}
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        columcat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        columtipo.setCellValueFactory(new PropertyValueFactory<>("examenTipo"));
        columestado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        idmedicoactual=config2.MedicoId;
        System.out.println("El dia de hoy se encarga"+idmedicoactual);
         Inicial();

        
        //        columnom.setCellValueFactory(new PropertyValueFactory<>("pacientes.nombres"));
        clf.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        clr.setCellValueFactory(new PropertyValueFactory<>("resultado"));
        
          tablev.setOnMousePressed(new EventHandler<MouseEvent>(){
        public void handle(MouseEvent me) {
    System.out.println(tablev.getSelectionModel().getSelectedIndex());
//        EditarO.setDisable(false);
//        EliminarO.setDisable(false);
        cancel.setDisable(false);
        add.setDisable(true);
        find.setDisable(true);
            if(me.getClickCount()==1){
            if(tablev.getSelectionModel().getSelectedIndex()==-1){
//              EditarO.setDisable(true);
//              EliminarO.setDisable(true);
            }
                    
            System.out.println(me.getButton());    
            
            if(me.getButton().toString()=="SECONDARY"){
                    System.out.println("clic derecho");
                
                    tablev.setContextMenu(conmenu);
            }
            
            }

        }
         });
        

        
             System.out.println("ya"+existe);
        
        if(subStage1.getTitle()==null){
            System.out.println("sin titulo");
            existe=0;
        }else{
            existe=1;
        }
    }    
    
}
