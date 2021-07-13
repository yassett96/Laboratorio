
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
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
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
import javafx.scene.DepthTest;
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
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;
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
public class RegExaController implements Initializable {

    SessionFactory factor;
    Session s = NewHibernateUtil.getSessionFactory().openSession();
//    Map<String, String> map = new HashMap<>();
    
    int existe=0;
    
    @FXML
    public ComboBox combocat, comboexa,cita;
    @FXML
    public TextField Noe, apellidos, Nombres, txtb;
    @FXML
    public Button save, cancel, find, list;
    @FXML
    ContextMenu conmenu,conmenu1;
    @FXML
    public RadioButton rNoe, rNom;
    @FXML
    MenuItem meneli, menmos, menmodi,select;
    @FXML
    public TableView<Examen> tableexa;
    @FXML
    public TableColumn columnoe;
//    TableColumn<Map.Entry<String, String>, String> columnom ;
    @FXML
    public TableColumn columnom;
    @FXML
    public TableColumn<Examen, String> columcat;
    @FXML
    public TableColumn<Examen, String> columtipo;
    @FXML
    public TableColumn<Examen, String> columestado;
//    public ObservableList<Examen> Examens;
    
    String hoy;
    @FXML public TableView<Pacientes> tablePac;
    @FXML public TableColumn<Pacientes, Integer> columnoe1;
    @FXML public TableColumn<Pacientes, String> columnom1;
    @FXML public TableColumn<Pacientes, String> columape;
    @FXML public TableColumn<Pacientes, Integer> columnedad;
    @FXML public TableColumn<Pacientes, String> columsala;
    
    String cat;
    int idPac;
    int idMed;
    int valttip=0;
    Alert alertcon = new Alert(Alert.AlertType.CONFIRMATION);
  
    ObservableList<Examen> data;
    ObservableList<String> categoria = FXCollections.observableArrayList("Embarazo", "Orina", "Heces", "Hematologia", "Quimica Sanguinea", "Inmunologia", "Parasitologia", "Bacteriologia", "Banco de Sangre");
    ObservableList<String> embarazo = FXCollections.observableArrayList("Examen de Embarazo en Orina");
    ObservableList<String> orina = FXCollections.observableArrayList("Examen General de Orina");
    ObservableList<String> heces = FXCollections.observableArrayList("Examen General de Heces");
    ObservableList<String> hema = FXCollections.observableArrayList("Biometria Hematica", "Hemoglobina", "Hemoglobina Glicosilada", "Reticulocitos", "Extendido Periferico", "Velocidad Sedimentacion Globular(VSG)", "Constantes Corpusculares", "Cuenta de Plaquetas", "Otros");
    ObservableList<String> qs = FXCollections.observableArrayList("Glucosa", "Creatinina", "Acido Urico", "Nitrogeno Ureico", "Glucosa", "Creatinina", "Acido Urico", "Nitrogeno Ureico", "Bilirrubina Directa", "Bilirrubina Indirecta", "Bilirrubina Total", "AST", "ALT", "Sodio", "Cloro", "Potasio", "Fosforo", "Calcio", "Magnesio", "Amilasa En Sangre", "Proteina Total", "Albumina", "Fosfatasa Alcalina", "Creatinina", "Acido Urico", "Nitrogeno Ureico", "Colesterol Total", "Colesterol HDL", "Colesterol LDL", "Trigliceridos", "LDH");
    ObservableList<String> Inmunologia = FXCollections.observableArrayList("Factor Reumatoide", "Antiestrectolicina", "Proteina C Reactiva", "RPR", "Examen de VIH", "Toxotest");
    ObservableList<String> parasitologia = FXCollections.observableArrayList("Copro Seriado", "Citologia Fecal", "Coproparasitoscopio", "Fresco en Heces");
    ObservableList<String> Bacteriologia = FXCollections.observableArrayList("BAAR", "Exudado Vaginal");
    ObservableList<String> Bancosangre = FXCollections.observableArrayList("COOMBS", "Grupo y RH");
    ObservableList<String> medicoshoy = FXCollections.observableArrayList();
    ObservableList<String> citalist = FXCollections.observableArrayList("Emergencia","Consulta General");
    
//  
//    ObservableList<Map.Entry<String, String>> items = FXCollections.observab(ZleArrayList(map.entrySet());
    		String[] strDays = new String[]{
						"Domingo",
						"Lunes",
						"Martes",
						"Miercoles",
						"Jueves",
						"Viernes",
						"Sabado"};
    
    int valor = 0;
    public String cargomedico;
    public String fechadehoy;
    private String tipodeexamen;
    private Object medicohoy;
    public String sexo;
    public int txtnoe;
    public String txtnom;
    public String txtape;
    public String txtmed;

    public void add() throws IOException {
        //
//        retornar();
        Habilitar();
        find.setDisable(true);
        list.setDisable(true);
        cancel.setDisable(false);
        save.setDisable(false);
//        fechan.setDisable(true);
        cargarpaciente();
        valor = 0;
//        if(  String.valueOf(combocat.getValue())=="Inmunologia"){
//         
//            ListaInmunologia();
//        }
        
        tableexa.getItems().clear();
 
    }

    public void cargarpaciente(){
     
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
    
    public void addcriteria() {

        if(sexo.contains("Masculino") && combocat.getValue().toString()=="Embarazo"){
                System.out.println("Es hombre :v");
                new Alert(Alert.AlertType.ERROR,"Sexo Masculino no puede hacer examen de Embarazo").show();
                cancel();
            }else{
                      
        
            
        try {
            Transaction t = s.beginTransaction();
            
            Medicos m = new Medicos();
            Pacientes p = new Pacientes();
            
            m.setIdMedico(1);
            p.setIdPaciente(idPac);
            
            
            System.out.println(combocat.getValue().toString());

            

            
            Examen e = new Examen();
            

       
            
            
            e.setCategoria((String) combocat.getValue());
            e.setExamenTipo((String) comboexa.getValue());
//            e.setFecha(new Date(fechan.getValue().format(DateTimeFormatter.ISO_DATE).replace('-', '/')));
            e.setRegistro("Activo");
            e.setMedicos(m);
            e.setPacientes(p);
                        
            if(cita.getValue().toString()=="Emergencia"){
                e.setEstado("Emergencia");
            s.save(e);
            t.commit();                
        s.close();
        s= NewHibernateUtil.getSessionFactory().openSession();
        Citaemergencia();
            }else{
                e.setEstado("Sin Cita");
                s.save(e);
                t.commit();  
        s.close();
        s= NewHibernateUtil.getSessionFactory().openSession();
            }
            
            
//       s.update(p);

//            new Alert(Alert.AlertType.INFORMATION,"Examen Agregado").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Fallo Conexion: " + e.toString()).show();
//        }finally
//        {
//            s.close();
        }
        valor = 0;
        tableexa.getItems().clear();
        tablePac.getItems().clear();

        
    }
        
    }
    
    
    public void Citaemergencia() throws ParseException{
        
        Query query = s.createQuery("    select MAX(e.idExamen)\n" +
"    from Examen as e");
        
        int idexamenultimo= Integer.parseInt(query.list().get(0).toString());
        System.out.println("Convertido "+query.list().get(0).toString());
        
                       try{
        Transaction t = s.beginTransaction();
        
            Examen e = new Examen();
            e.setIdExamen(idexamenultimo);
            
            Citas c = new Citas();
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-mm-dd");
            Date date = dateFormat2.parse(hoy);
            c.setFecha(date);
           c.setHora("7am");
           c.setExamen(e);
           
           s.save(c);
       
       t.commit();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Fallo Conexion: "+e.toString());
//        }finally
//        {
//            s.close();
        }
        

    }

    public void suprimir() {
        int id = (tableexa.getSelectionModel().getSelectedItem().getIdExamen());
        cat=tableexa.getSelectionModel().getSelectedItem().getCategoria();
        
        System.out.println(id);
        s.getTransaction().begin();
        
        
            Query query = s.createQuery("update Examen set registro='Inactivo' where idExamen=:Id");
            query.setParameter("Id", id);
            query.executeUpdate();
            
        if(cat.toString().contains("Orina")){
            
            Query query2 = s.createQuery("update ExamenOrina set registro='Inactivo' where idExamen=:Id");
            query2.setParameter("Id", id);
            query2.executeUpdate(); 
            
            Query query3 = s.createQuery("update ExamenOrinaFq set registro='Inactivo' where idExamenOrina=:Id");
            query3.setParameter("Id", id);
            query3.executeUpdate();
            
            Query query4 = s.createQuery("update ExamenOrinaM set registro='Inactivo' where idExamenOrina=:Id");
            query4.setParameter("Id", id);
            query4.executeUpdate();
        }else{           
        if(cat.toString().contains("Heces")){

            Query query5 = s.createQuery("update ExamenHeces set registro='Inactivo' where idExamen=:Id");
            query5.setParameter("Id", id);
            query5.executeUpdate(); 
            
            Query query6 = s.createQuery("update ExamenHecesCm set registro='Inactivo' where idExamenHeces=:Id");
            query6.setParameter("Id", id);
            query6.executeUpdate();
            
            Query query7 = s.createQuery("update ExamenHecesCp set registro='Inactivo' where idExamenHeces=:Id");
            query7.setParameter("Id", id);
            query7.executeUpdate();

            
        }else{
        if(cat.toString().contains("Embarazo")){
      
            Query query8 = s.createQuery("update ExamenEmbarazo set registro='Inactivo' where idExamen=:Id");
            query8.setParameter("Id", id);
            query8.executeUpdate();
            
        }else{
            Query query9 = s.createQuery("update ExamenesVarios set registro='Inactivo' where idExamenV=:Id");
            query9.setParameter("Id", id);
            query9.executeUpdate();            
            
        }      
        }
        }
        
        s.getTransaction().commit();
        new Alert(Alert.AlertType.INFORMATION,"Examen Eliminado").show();
        tableexa.getItems().clear();
        tablePac.getItems().clear();
        cancel();
    }

    public void editcriteria() {
        int id = (tableexa.getSelectionModel().getSelectedItem().getIdExamen());
        System.out.println(tableexa.getSelectionModel().getSelectedItem().getIdExamen());
//        System.out.println("tt"+tableexa.getSelectionModel().getSelectedItem().getEstado());
        s.getTransaction().begin();
        
        String consulta = ("update Examen set categoria =:cat,\n" +
"        examenTipo =:et,\n" +
"        where idExamen =:Id");
//        System.out.println();
        Query query = s.createQuery(consulta);
        query.setParameter("cat", String.valueOf(combocat.getValue()));
        query.setParameter("et", String.valueOf(comboexa.getValue()));
//        System.out.println(combomed.getValue().toString().substring(4,5));
//        query.setParameter("me", combomed.getValue().toString().substring(4,5));
//          query.setParameter("est", estado.getText());
        query.setParameter("Id", id);

        int result = query.executeUpdate();
        s.getTransaction().commit();
        new Alert(Alert.AlertType.INFORMATION,"Examen Modificado").show();
//        config2.dialog(Alert.AlertType.INFORMATION, "Success Delete");
        tableexa.getItems().clear();
        tablePac.getItems().clear();
        s.close();
        s = NewHibernateUtil.getSessionFactory().openSession();
    }

    @FXML
    public void mod() {
        Habilitar();
//        int id = 2;
        find.setDisable(true);
        list.setDisable(true);
        cancel.setDisable(false);
        save.setDisable(false);

        valor = 1;

    }

    @FXML
    public void elim() {
//        Evento();
        suprimir();
        valor = 0;
        tableexa.getItems().clear();
        tablePac.getItems().clear();

    }

    @FXML
    public void listar() {

        Deshabilitar();
        tableexa.setDisable(false);
        tablePac.setDisable(false);
        criterialist();
//            Evento();
        list.setDisable(true);

        cancel.setDisable(false);
        valor = 0;

    }

    @FXML
    public void Evento() {
//        modif.setDisable(false);
//        elim.setDisable(false);
        cancel.setDisable(false);
//        add.setDisable(true);
        find.setDisable(true);

//        String año = tableexa.getSelectionModel().getSelectedItem().getFecha().toString().substring(0,4);
//        String mes=tableexa.getSelectionModel().getSelectedItem().getFecha().toString().substring(5,7);
//        String dia=tableexa.getSelectionModel().getSelectedItem().getFecha().toString().substring(8,10);
//        fechan.setValue(LocalDate.of(Integer.parseInt(año),Integer.parseInt(mes),Integer.parseInt(dia)));
        Noe.setText(String.valueOf(tableexa.getSelectionModel().getSelectedItem().getPacientes().getNoExpediente()));
        Nombres.setText((tableexa.getSelectionModel().getSelectedItem().getPacientes().getNombres()));
        apellidos.setText((tableexa.getSelectionModel().getSelectedItem().getPacientes().getApellidos()));
        combocat.setValue(tableexa.getSelectionModel().getSelectedItem().getCategoria());
        comboexa.setValue(tableexa.getSelectionModel().getSelectedItem().getExamenTipo());
        cita.setValue(tableexa.getSelectionModel().getSelectedItem().getEstado());

    }

    @FXML
    public void MetodoClick() {
        Evento();
        mod();
    }
    
    public void select(){
       Noe.setText(String.valueOf(tablePac.getSelectionModel().getSelectedItem().getNoExpediente()));
       sexo=tablePac.getSelectionModel().getSelectedItem().getSexo();
       idPac=tablePac.getSelectionModel().getSelectedItem().getIdPaciente();
        System.out.println(idPac);
       Nombres.setText((tablePac.getSelectionModel().getSelectedItem().getNombres()));
       apellidos.setText((tablePac.getSelectionModel().getSelectedItem().getApellidos()));
    }

    public void criterialist() {

        Criteria c = s.createCriteria(Examen.class).add(Restrictions.like("registro","Activo"))
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
        valor = 0;
            }
//         retornar();
    }

//        


    @FXML
    public void save() {

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
                tableexa.getItems().clear();                
          
  } else{
      valttip=0;
  }     

    }

    @FXML
    public void cancel() {
        tableexa.getItems().clear();
        Limpiar();
        Inicial();
        valor = 0;

    }
    
    
         public void TooltipValidation(){
         Stylepred();

    String style="-fx-background-color: orange,linear-gradient(to bottom, derive(orange,60%) 5%,derive(orange,90%) 40%);";
    
    String styletxt="-fx-border-color: red;";
   
    
    if(Nombres.getText().isEmpty() || Nombres.getText().equals("") || Nombres.getText().length()<3){            
        Nombres.setStyle(styletxt);

        valttip=1;
    }
    if(apellidos.getText().isEmpty() || apellidos.getText().equals("") || apellidos.getText().length()<3){
        apellidos.setStyle(styletxt);
 
        valttip=1;
    }
    
     }
    
         
       
    
        public void Stylepred(){
        String stylep="-fx-skin: \"Utilidades.MetroTextFieldSkin\";";
        Nombres.setStyle(stylep);
        apellidos.setStyle(stylep);

    }
         

    @FXML
    public void rNoe() {
        rNom.setSelected(false);

    }

    @FXML
    public void rNom() {
        rNoe.setSelected(false);
    }

    


    
    

    


    @FXML
    public void find() {
        if (rNoe.isSelected()) {
            findnoe();
        }
        if (rNom.isSelected()) {
            findnom();
        }

        txtb.setDisable(true);

    }

    public static boolean isNumeric(String str) {
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("") == false);
    }

    public void findnoe() {
        String bus = txtb.getText();
        System.out.println(bus);
        if (bus.isEmpty() || bus.equals("") || bus.length() < 2) {
            new Alert(Alert.AlertType.ERROR,"No deje campos vacios o pocos Caracteres").show();
            cancel.setDisable(false);
        } else {

            Criteria c = s.createCriteria(Examen.class).add(Restrictions.like("registro","Activo"))
                    .add(Restrictions.like("categoria", bus + "%"));

            if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancel();
                Inicial();
                cancel.setDisable(false);
            }else{
            System.out.println("Personas Encontradas:" + c.list().size());
            for (Object pac : c.list()) {
                Examen p = (Examen) pac;
//            System.out.println(" - " +p.getNoExpediente()+" - "+p.getNombres());

            }

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
            Limpiar();
            Inicial();
            tableexa.setDisable(false);
            valor = 0;
                        }
        }
    }

    public void findnom() {
        String bus = txtb.getText();
        System.out.println(bus);
        if (bus.isEmpty() || bus.equals("") || bus.length() < 3) {
            new Alert(Alert.AlertType.ERROR,"No deje campos vacios").show();
            cancel.setDisable(false);
        } else {

            Criteria c = s.createCriteria(Examen.class).add(Restrictions.like("registro","Activo"))
            .add(Restrictions.like("estado", bus + "%"));

            if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancel();
                Inicial();
                cancel.setDisable(false);
            }else{
            System.out.println("Personas Encontradas:" + c.list().size());
            for (Object pac : c.list()) {
                Examen p = (Examen) pac;
//            System.out.println(" - " +p.getNoExpediente()+" - "+p.getNombres());

            }
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
            Limpiar();
            Inicial();
            tableexa.setDisable(false);
            tablePac.setDisable(false);
            valor = 0;
            }
        }
    }

    public void Inicial() {
        tablePac.setDisable(true);
        tableexa.setDisable(true);
        find.setDisable(false);
        list.setDisable(false);
        save.setDisable(true);
        cancel.setDisable(true);
        txtb.setDisable(false);
//        apellidos.setDisable(true);
        Noe.setDisable(true);
        Nombres.setDisable(true);
        apellidos.setDisable(true);
//        Nombres.setDisable(true);
//        estado.setDisable(true);
        combocat.setDisable(true);
        combocat.setItems(categoria);
        cita.setDisable(true);
        cita.setItems(citalist);
        combocat.setValue("");
        cita.setValue("");
        comboexa.setDisable(true);
        comboexa.setValue("");
//        fechan.setDisable(true);
        valor = 0;
//        listPac.add(new Paciente)
    }
    
    public void ListaInmunologia(){
        comboexa.setItems(Inmunologia);
    }
    public void ListaEmbarazo(){
    comboexa.setItems(embarazo);
    }
    

    
    public void ListaOrina(){
    comboexa.setItems(orina);
    }
    
    public void ListaHeces(){
    comboexa.setItems(heces);
    }
    public void ListaHema(){
    comboexa.setItems(hema);
    }
     
    public void Listaqs(){
    comboexa.setItems(qs);
    }
    
    public void Listapara(){
    comboexa.setItems(parasitologia);
   
    }
    public void Listabac(){
    comboexa.setItems(Bacteriologia);
    }
    public void Listabds(){
    comboexa.setItems(Bancosangre);
    }
 

    public void Habilitar() {
        tableexa.setDisable(false);
        tablePac.setDisable(false);
//        Nombres.setDisable(false);
//        estado.setDisable(false);
//        apellidos.setDisable(false);
        Noe.setDisable(false);
        combocat.setDisable(false);
        comboexa.setDisable(false);
        cita.setDisable(false);
        valor = 0;
    }

    public void Deshabilitar() {
        tableexa.setDisable(true);
        tablePac.setDisable(true);
//        Nombres.setDisable(true);
//        estado.setDisable(true);
//        apellidos.setDisable(true);
        Noe.setDisable(true);
        combocat.setDisable(true);
        comboexa.setDisable(true);
        cita.setDisable(true);

        valor = 0;
    }

    public void Limpiar() {

        combocat.setValue("");
        comboexa.setValue("");
        cita.setValue("");
//        fechan.setValue(null);
//        estado.clear();
        apellidos.clear();
        Nombres.clear();
        Noe.clear();
        valor = 0;

    }
    
    public void limpiarcombo(){
        
    }
    

    
    public static int getDayOfTheWeek(Date d){
	GregorianCalendar cal = new GregorianCalendar();
	cal.setTime(d);
	return cal.get(Calendar.DAY_OF_WEEK);		
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

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
        
      
        combocat.valueProperty().addListener((p1, ov, p2) -> {
       medicoshoy.clear();
        cargomedico=(String) p1.getValue();
        System.out.println(cargomedico);
        if(cargomedico=="Inmunologia"){
            limpiarcombo();
            ListaInmunologia();
        }
        if(cargomedico=="Embarazo"){
            
            System.out.println("El sexo del paciente es: "+sexo);
            ListaEmbarazo();   
            

        } 
         if(cargomedico=="Orina"){
             
            ListaOrina();
        } 
         if(cargomedico=="Heces"){
            
            ListaHeces();
        }         
         if(cargomedico=="Hematologia"){
            
            ListaHema();
        }         
         if(cargomedico=="Quimica Sanguinea"){
            cargomedico="Quimica";
            
            Listaqs();
        }         
         if(cargomedico=="Parasitologia"){
            
            Listapara();
        }
         if(cargomedico=="Bacteriologia"){
            
            Listabac();
        }         
         if(cargomedico=="Banco de Sangre"){
             cargomedico="BancoSangre";
             
             Listabds();
        }
        });
//        Nombres.setEditable(false);
//        apellidos.setEditable(false);

        
        
//        globonom.setVisible(false);
        Calendar now = Calendar.getInstance();
        System.out.println("Fecha actual : " + (now.get(Calendar.MONTH) + 1)
						+ "-"
						+ now.get(Calendar.DATE)
						+ "-"
						+ now.get(Calendar.YEAR));
        System.out.println("Hoy es : " + strDays[now.get(Calendar.DAY_OF_WEEK) - 1]);
        fechadehoy=(strDays[now.get(Calendar.DAY_OF_WEEK) - 1]);
        System.out.println(fechadehoy);
//        System.out.println(fechadehoy);
//        tablePac.getColumns().addAll(columnoe,columnom,columape,columnedad,columsala);
//        columid.setCellValueFactory(new PropertyValueFactory<>("idExamen"));
//        columnom.setCellValueFactory(new PropertyValueFactory<>("pacientes.nombres"));
        columcat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        columtipo.setCellValueFactory(new PropertyValueFactory<>("examenTipo"));
        columestado.setCellValueFactory(new PropertyValueFactory<>("estado"));
//        columsala.setCellValueFactory(new PropertyValueFactory<>("sala"));
        rNoe.setSelected(true);
        
        
        
        
        
        
        tableexa.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                find.setDisable(false);
                cancel.setDisable(false);
            if(me.getClickCount()==1){
                System.out.println(tableexa.getSelectionModel().getSelectedIndex());
            if(tableexa.getSelectionModel().getSelectedIndex()==-1){
              
              new Alert(Alert.AlertType.ERROR,"Seleccione Una fila").show();
              
            }else{
             System.out.println(me.getButton());    
            
            if(me.getButton().toString()=="SECONDARY"){
                    System.out.println("clic derecho");
                    find.setDisable(true);
                    tableexa.setContextMenu(conmenu);
            }
            }

            
            }

            }
        });

        Inicial();
        valor = 0;
        // TODO

        //Obteniendo el medico
        idMed=config2.id;
        System.out.println("El id en turno es de:"+idMed);
        System.out.println("ya"+existe);
        
        columnoe1.setCellValueFactory(new PropertyValueFactory<>("noExpediente"));
        columnom1.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        columape.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columnedad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        columsala.setCellValueFactory(new PropertyValueFactory<>("sala"));
        
                tablePac.setOnMousePressed(new EventHandler<MouseEvent>(){
        public void handle(MouseEvent me) {
            if(me.getClickCount()==1){
                System.out.println(tablePac.getSelectionModel().getSelectedIndex());
            if(tablePac.getSelectionModel().getSelectedIndex()==-1){

              tablePac.setContextMenu(null);
              
//              JOptionPane.showMessageDialog(null, "Seleccione una Fila");
              
            }else{
             System.out.println(me.getButton());    
        
            if(me.getButton().toString()=="SECONDARY"){
                    System.out.println("clic derecho");
                
                    tablePac.setContextMenu(conmenu1);
            }
            }
          
            }
        }
                   });

        
      
         hoy=config2.fechahoy;
         System.out.println(hoy+" este es");
                
    }

}
