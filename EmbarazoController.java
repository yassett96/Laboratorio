/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Hibernate.NewHibernateUtil;
import POJO.Examen;
import POJO.ExamenEmbarazo;
import Utilidades.config2;
import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


/**
 * FXML Controller class
 *
 * @author Oliverhacker
 */
public class EmbarazoController implements Initializable {
//    Llamado de los componentes del formulario de Embarazo JAVAFX
        SessionFactory factor;
     Session s =  NewHibernateUtil.getSessionFactory().openSession();
    @FXML public Button mod,save,cancel,find,add,buscar,listar;       
    @FXML public TextField Noe,Nombres,apellidos,res,obs;
    @FXML public ComboBox Combosemana;
//    @FXML public DatePicker fechan;
    @FXML public TableView<ExamenEmbarazo> tableEmb;
    @FXML public TableColumn columnoe;
    @FXML public TableColumn columnom;
    @FXML public TableColumn<ExamenEmbarazo, Integer> columsem;
    @FXML public TableColumn<ExamenEmbarazo, String> columres;
    @FXML public TableColumn colummed;

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

 int idmedicoactual;
    
    int idMed;
    int idexamen=0, valttip = 0;
    String pNombre, sNombre, MedNomApe;
    
    ObservableList<String> semana = FXCollections.observableArrayList("25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41");
    
     int valor=0;    
    
  ObservableList<String> semanalist =FXCollections.observableArrayList("8","12","16","20","24","28","32");
         
//    Método y función para el botón add
        @FXML public void add(){        
        Habilitar();
        add.setDisable(true);   
        buscar.setDisable(true);
        listar.setDisable(true);
        cancel.setDisable(false);
        save.setDisable(false);
        mod.setDisable(true);
        find.setDisable(true);
        ListaExamenes();
        
        valor=0;
        tableEmb.getItems().clear();        
        }
        
    @FXML public void select(){
    
    idexamen=tableexa.getSelectionModel().getSelectedItem().getIdExamen();
    Noe.setText(tableexa.getSelectionModel().getSelectedItem().getPacientes().getNoExpediente().toString());
    Nombres.setText(tableexa.getSelectionModel().getSelectedItem().getPacientes().getNombres());
    apellidos.setText(tableexa.getSelectionModel().getSelectedItem().getPacientes().getApellidos());    
        
    }
        
        
//      Método para añadir un registro  
        public void addcriteria(){
            
            try{
            Transaction t = this.s.beginTransaction();            
            Examen ex = new Examen();
            ex.setIdExamen(idexamen);
            
            ExamenEmbarazo exa = new ExamenEmbarazo();                                                           
            exa.setResultado(res.getText());
//            exa.setFecha(new Date(fechan.getValue().format(DateTimeFormatter.ISO_DATE).replace('-', '/')));
            exa.setObservaciones(obs.getText());
            exa.setSemana(Integer.parseInt(Combosemana.getValue().toString()));
            exa.setRegistro("Activo");
            exa.setExamen(ex);
            
                s.save(exa);
                
            Query query = s.createQuery("update Examen set estado='Realizado',Id_Medico=:me where idExamen=:Id");
            query.setParameter("Id", idexamen);
            query.setParameter("me", idmedicoactual);
            query.executeUpdate();
                
                int result = query.executeUpdate();
                
                if(result == 1){
                    new Alert(Alert.AlertType.INFORMATION, "Examen realizado").show();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Error al realizar examen").show();
                }
                
                t.commit();              
                

                
            }catch(Exception ex){
                new Alert(Alert.AlertType.ERROR,"Sistema de alerta").show();
            }
                                                
        s.close();
        s=NewHibernateUtil.getSessionFactory().openSession();
            valor = 0;
            tableEmb.getItems().clear();
            tableexa.getItems().clear();
            
        }
        
//        Método para borrar registro

//     Método para editar con criteria   
        public void editcriteria(){
          int id = (tableEmb.getSelectionModel().getSelectedItem().getIdExamen());
            System.out.println(id);
          this.s.getTransaction().begin();
          Query query = s.createQuery("update ExamenEmbarazo set resultado = :resultados,fecha = :fechan, semana = :semanas,"
          + "observaciones = :observacion where idExamen = :ID");
          query.setParameter("resultados", (Object)res.getText());            
//          query.setParameter("fechan",new Date(fechan.getValue().format(DateTimeFormatter.ISO_DATE).replace('-', '/')));
          query.setParameter("semanas", Integer.parseInt(Combosemana.getValue().toString()));
          query.setParameter("observacion", String.valueOf((Object)obs.getText()));
          query.setParameter("ID", id);
          
          int result = query.executeUpdate();
          
          if(result == 1)
              JOptionPane.showMessageDialog(null,"Registro modificado correctamente","Sistema de información",JOptionPane.INFORMATION_MESSAGE);
              else
                JOptionPane.showMessageDialog(null,"Error al modificar el registro","Sistema de información",JOptionPane.ERROR_MESSAGE);
          
          s.getTransaction().commit();
          tableEmb.getItems().clear();
          tableexa.getItems().clear();
          s.close();          
          s = NewHibernateUtil.getSessionFactory().openSession();
       }
        
//    Método y función para el botón modificar
        @FXML public void mod(){
        //
            Habilitar();
            save.setDisable(false);
            cancel.setDisable(false);
            find.setDisable(true);
            
            valor = 1;
    }
        
//        Método para eliminar

                      
         public void Evento(){
                                                                                       
             tableEmb.setOnMouseClicked(new EventHandler<MouseEvent>(){                 

                 @Override
                 public void handle(MouseEvent me) {
                     mod.setDisable(false);
                     cancel.setDisable(false);
                     add.setDisable(true);
                     find.setDisable(true);
                     Noe.setText(String.valueOf(tableEmb.getSelectionModel().getSelectedItem().getExamen().getPacientes().getNoExpediente()));
                     Combosemana.setValue(tableEmb.getSelectionModel().getSelectedItem().getSemana());
                     res.setText(String.valueOf(tableEmb.getSelectionModel().getSelectedItem().getResultado()));
                     Nombres.setText(String.valueOf(tableEmb.getSelectionModel().getSelectedItem().getExamen().getPacientes().getNombres()));
                     apellidos.setText(String.valueOf(tableEmb.getSelectionModel().getSelectedItem().getExamen().getPacientes().getApellidos()));
//                     String año=tableEmb.getSelectionModel().getSelectedItem().getFecha().toString().substring(0,4);
//                     String mes=tableEmb.getSelectionModel().getSelectedItem().getFecha().toString().substring(5,7);
//                     String dia=tableEmb.getSelectionModel().getSelectedItem().getFecha().toString().substring(8,10);
////                     fechan.setValue(LocalDate.of(Integer.parseInt(año), Integer.parseInt(mes), Integer.parseInt(dia)));
                     obs.setText(String.valueOf(tableEmb.getSelectionModel().getSelectedItem().getObservaciones()));
                                         //                     
                 }            
             
             });
         }

//         Método y función del botón para el botón buscar
        @FXML public void buscar(){
        
            Deshabilitar();
        add.setDisable(true);
        buscar.setDisable(true);
        listar.setDisable(true);
        cancel.setDisable(false);
        find.setDisable(false);
        Noe.setDisable(false);
        Evento();
              
        }
 
   public void ListaExamenes(){
       
       ObservableList<String> minilist = FXCollections.observableArrayList("Emergencia","Pendiente");
       Criteria c = s.createCriteria(Examen.class).add(Restrictions.like("registro","Activo"))
       .add(Restrictions.in("estado", minilist))
       .addOrder( Order.asc("estado") )
       .add(Restrictions.eq("categoria", "Embarazo"));
       
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
        
        
        
//        Método y función para el botón listar
        @FXML public void listar(){
        
            Deshabilitar();
            tableEmb.setDisable(false);
            criterialist();
            Evento();
            listar.setDisable(true);
            valor = 0;
            cancel.setDisable(false);
            }
        
        //Método para listar Examenes de embarazo
        public void criterialist(){
            
            Criteria c = s.createCriteria(ExamenEmbarazo.class).add(Restrictions.like("registro","Activo"))
            
            .addOrder( Order.asc("resultado") ); 
            if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancel();
                Inicial();                
            }else{
                System.out.println("Personas Encontradas:"+c.list().size());        
            for(Object pac: c.list() )
            {
                ExamenEmbarazo p= (ExamenEmbarazo) pac;
            
                System.out.println(p.getResultado());         
            }
                }
        

        columnoe.setCellValueFactory(new Callback<CellDataFeatures<ExamenEmbarazo,Integer>,ObservableValue<Integer>>(){       
                public ObservableValue<Integer> call(CellDataFeatures<ExamenEmbarazo, Integer> p) {
                   return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNoExpediente());
                }            
        });

        columnom.setCellValueFactory(new Callback<CellDataFeatures<ExamenEmbarazo,String>,ObservableValue<String>>(){       
                public ObservableValue<String> call(CellDataFeatures<ExamenEmbarazo, String> p) {
                   return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNombres()+" "+p.getValue().getExamen().getPacientes().getApellidos());
                }            
        });
        
        colummed.setCellValueFactory(new Callback<CellDataFeatures<ExamenEmbarazo,String>,ObservableValue<String>>(){       
                public ObservableValue<String> call(CellDataFeatures<ExamenEmbarazo, String> p) {
                   return new ReadOnlyObjectWrapper(p.getValue().getExamen().getMedicos().getNombre()+" "+p.getValue().getExamen().getMedicos().getApellido());
                }            
        });
        
         
        
        tableEmb.getItems().clear();
       tableEmb.getItems().addAll(c.list());             
        }
        
        public void Stylepred(){
//            String stylep="-fx-skin: \"Utilidades.MetroTextFieldSkin\";";
            String stylep="-fx-border-color: gray;";
            Noe.setStyle(stylep);
            Nombres.setStyle(stylep);
            apellidos.setStyle(stylep);
//            fechan.setStyle(stylep);
            Combosemana.setStyle(stylep);
            res.setStyle(stylep);
            obs.setStyle(stylep);
        }
        
        public void TooltipValidation(){
            Stylepred();
            
            String styletxt="-fx-border-color: red;";
            
            if(Noe.getText().isEmpty() || Noe.getText().equals("") || Noe.getText().length() != 8){
                Noe.setStyle(styletxt);
                valttip = 1;
            }
            if(Nombres.getText().isEmpty() || Nombres.getText().equals("") || Nombres.getText().length() < 3){
                Nombres.setStyle(styletxt);
                valttip = 1;
            }            
            if(apellidos.getText().isEmpty() || apellidos.getText().equals("") || apellidos.getText().length() < 3){
                apellidos.setStyle(styletxt);
                valttip = 1;
            }
            System.out.println(Combosemana.getValue().toString());
            if(Combosemana.getValue().toString().isEmpty() || Combosemana.getValue().toString() == "Seleccione"){
                Combosemana.setStyle(styletxt);
                valttip = 1;
            }
            if(res.getText().isEmpty() || res.getText().equals("") || res.getText().length() < 4){
                res.setStyle(styletxt);
                valttip = 1;
            }
            if(obs.getText().isEmpty() || obs.getText().equals("") || obs.getText().length() < 4){
                obs.setStyle(styletxt);
                valttip = 1;
            }                                 
        }
        
//        Método y función para el botón agregar
        @FXML public void save(){
        TooltipValidation();
            if(valttip==0){
                if(valor==1){
                editcriteria();
                }else{
                    
                        addcriteria();
                        Limpiar();
                        Inicial();
                        valor=0;
                        tableEmb.getItems().clear();
                        tableexa.getItems().clear();
                    }
                                
                
                                        
            } else{
            valttip=0;
                }
            
            }   
        
//        Método para el botón cancelar
        @FXML public void cancel(){
            Stylepred();
            Limpiar();
            Inicial();
            valor =0;
            tableEmb.getItems().clear(); 
            tableexa.getItems().clear();
        }
        //      Método para el botón find
        @FXML public void find(){
        if( Noe.getText().isEmpty() == false || Noe.getText().length() == 8){
         
        
        Query query = s.createQuery("select new map(ee.idExamen)\n" +
        "    from Pacientes as p\n" +
        "    inner join p.examens as e\n" +
        "    inner join e.examenEmbarazo as ee\n" +
        "    where noExpediente=:noe");
        query.setParameter("noe", Noe.getText() );
        List<Map> listaResultados = query.list();    
        
            System.out.println(listaResultados);
            System.out.println(listaResultados.size());
            
            if(listaResultados.size()==0){
                new Alert(Alert.AlertType.INFORMATION,"No se encontraron resultados.").show();
            }else{
            Object[] mapa = new Object[listaResultados.size()];
            
        for (int i = 0; i < listaResultados.size(); i++) {
            Map mapaconsulta = listaResultados.get(i);
            Set llaves = mapaconsulta.keySet();

            for (Iterator<String> it = llaves.iterator(); it.hasNext();) {
                String llaveActual = it.next();
                System.out.println(mapaconsulta.get(llaveActual));
                mapa[i]=mapaconsulta.get(llaveActual);
            }      
        }
 
        Criteria c = s.createCriteria(ExamenEmbarazo.class).add(Restrictions.like("registro","Activo"))
        .add(Restrictions.in("idExamen", mapa));
 
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            ExamenEmbarazo p= (ExamenEmbarazo) pac;
            
            System.out.println(p.getResultado());
         
        }

        columnoe.setCellValueFactory(new Callback<CellDataFeatures<ExamenEmbarazo,Integer>,ObservableValue<Integer>>(){       
                public ObservableValue<Integer> call(CellDataFeatures<ExamenEmbarazo, Integer> p) {
                   return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNoExpediente());
                }            
        });

        columnom.setCellValueFactory(new Callback<CellDataFeatures<ExamenEmbarazo,String>,ObservableValue<String>>(){       
                public ObservableValue<String> call(CellDataFeatures<ExamenEmbarazo, String> p) {
                   return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNombres()+" "+p.getValue().getExamen().getPacientes().getApellidos());
                }            
        });
        
        colummed.setCellValueFactory(new Callback<CellDataFeatures<ExamenEmbarazo,String>,ObservableValue<String>>(){       
                public ObservableValue<String> call(CellDataFeatures<ExamenEmbarazo, String> p) {
                   return new ReadOnlyObjectWrapper(p.getValue().getExamen().getMedicos().getNombre()+" "+p.getValue().getExamen().getMedicos().getApellido());
                }            
        });
        
         
        
        tableEmb.getItems().clear();
       tableEmb.getItems().addAll(c.list());   
                
            }
        
        }else{
        new Alert(Alert.AlertType.ERROR,"Para buscar necesita el número de expediente del paciente").show();
        
        }
                  
    }  
       
//        Método para volver a la posición inicial del formulario
        
    public void Inicial(){
        add.setDisable(false);
        buscar.setDisable(false);
        listar.setDisable(false);
        mod.setDisable(true);
        save.setDisable(true);
        cancel.setDisable(true);
        find.setDisable(true);
        Nombres.setDisable(true);
        apellidos.setDisable(true);
        Noe.setDisable(true);
        Combosemana.setDisable(true);
        res.setDisable(true);
        obs.setDisable(true);
//        fechan.setDisable(true);
        Combosemana.setValue("Seleccione");
//        fechan.setValue(LocalDate.now());
        Combosemana.setItems(semanalist);
    }    
    
//    Método para habilitar campos
    
    public void Habilitar(){
        Nombres.setDisable(true);
        apellidos.setDisable(true);
        Noe.setDisable(false);
        Combosemana.setDisable(false);
        res.setDisable(false);
        obs.setDisable(false);
//        fechan.setDisable(false);
        valor = 0;
    }
    
//    Método para deshabilitar campos
    
   public void Deshabilitar(){
        Nombres.setDisable(true);
        apellidos.setDisable(true);
        Noe.setDisable(true);
        Combosemana.setDisable(true);
        res.setDisable(true);
        obs.setDisable(true);
//        fechan.setDisable(true);
    }
    
//    Método para limpiar campos
    public void Limpiar(){        
        Nombres.clear();
        apellidos.clear();
        Noe.clear();
        res.clear();
        obs.clear();
//        fechan.setValue(LocalDate.now());           
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idmedicoactual=config2.MedicoId;
        System.out.println("El dia de hoy se encarga"+idmedicoactual);
//        columnoe.setCellValueFactory(new PropertyValueFactory<> ("noExpediente"));
//        columnom.setCellValueFactory(new PropertyValueFactory<> ("nombres"));
        columsem.setCellValueFactory(new PropertyValueFactory<> ("semana"));
        columres.setCellValueFactory(new PropertyValueFactory<> ("resultado"));
//        colummed.setCellValueFactory(new PropertyValueFactory<> ("nombre"));
                

        columcat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        columtipo.setCellValueFactory(new PropertyValueFactory<>("examenTipo"));
        columestado.setCellValueFactory(new PropertyValueFactory<>("estado"));



        Inicial();
        idMed=config2.id;
    }    
    
}
