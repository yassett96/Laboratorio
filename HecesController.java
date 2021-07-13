/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import static Controladores.RegExaController.isNumeric;
import Hibernate.NewHibernateUtil;
import POJO.Examen;
import POJO.ExamenEmbarazo;
import POJO.ExamenHeces;
import POJO.ExamenHecesCm;
import POJO.ExamenHecesCp;
import POJO.ExamenOrina;
import POJO.ExamenOrinaM;
import POJO.ExamenOrinaFq;
import POJO.Medicos;
import POJO.Pacientes;
import POJO.Roles;
import Utilidades.config2;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.zone;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
public class HecesController implements Initializable {
    
    SessionFactory factor;
    Session s = NewHibernateUtil.getSessionFactory().openSession();
    
    @FXML
    public Button AgregarH,CancelarH,GuardarH,ListarH,BuscarResultado;
    
  

   @FXML 
   public TextField nombreP,sexoP,edadP,noExph,color,resultadoP,macro,consistencia,restosA,mucus,
           macrosco,hemati,micros,leuc,buscarR;
   
//   @FXML 
//   public DatePicker fechaH;
//   ///Tabla ExamenHecesCP
   
   @FXML 
   public TextField entahistro,entahisqui,entacolitro,entacoliqui,endonatro,endonaqui,giartro,giarqui,
           tritro,triqui,chitro,chiqui,lotro,loqui,otro1,otro2;
   
   
   /// Tabla ExamenhecesCm
   
   @FXML
   public TextField ascaris,trichu,unci,otroscm,observ,stron,entero,tae;
   
   
    @FXML
    ContextMenu conmenu;
   @FXML
    MenuItem menueli, menumos, menumodi;

           
   int valttip=0;
    @FXML
    public TableView<ExamenHeces> TablaHeces;
    @FXML
    public TableColumn<ExamenHeces, String> columresultado;
    @FXML
    public TableColumn columobservaciones;

    @FXML
    public TableColumn columnoe;
//    TableColumn<Map.Entry<String, String>, String> columnom ;
    @FXML
    public TableColumn columpaciente;

    @FXML
    public TableColumn colummedico;
 
    @FXML
    public TableView<Examen> tableexa;
    @FXML
    public TableColumn columnoe1;
//    TableColumn<Map.Entry<String, String>, String> columnom ;
    @FXML
    public TableColumn columnom;
    @FXML
    public TableColumn<Examen, String> columcat;
    @FXML
    public TableColumn<Examen, String> columtipo;
    @FXML
    public TableColumn<Examen, String> columestado;    
    
    int valor = 0;
    int cargar = 0;
    int idexamen;
    String variable,variable1;
    int idmedicoactual;
    @FXML 
    public RadioButton Expediente,Nombre;
    
    @FXML
    public void NuevoExaH() {
        //
        
        noExph.setDisable(false);
        
        AgregarH.setDisable(true);
        ListarH.setDisable(true);
//        ModificarH.setDisable(true);
//        EliminarH.setDisable(true);
        CancelarH.setDisable(false);
        GuardarH.setDisable(false);
        resultadoP.setDisable(true);
        valor = 0;
        TablaHeces.getItems().clear();
        
        Habilitar();
        ListaExamenes();

    }
    

     
       @FXML
    public void listarH() {

        Deshabilitar();
        TablaHeces.setDisable(false);
        criterialist();
//            Evento();
        ListarH.setDisable(true);

        CancelarH.setDisable(false);
        BuscarResultado.setDisable(false);
        AgregarH.setDisable(true);
//        ModificarH.setDisable(false);
        valor = 0;

    }
    public void criterialist() {

        Criteria c = s.createCriteria(ExamenHeces.class).add(Restrictions.like("registro","Activo"))
                .addOrder(Order.asc("resultado"));

        columnoe.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenHeces, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<ExamenHeces, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNoExpediente());
            }
        });

        columpaciente.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenHeces, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenHeces, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNombres());

            }
        });

        colummedico.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenHeces, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenHeces, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getMedicos().getNombre());

            }
        });
        
        columobservaciones.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenHeces, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenHeces, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getExamenHeces().getExamenHecesCm().getObservaciones());

            }
        });

        TablaHeces.getItems().clear();
        TablaHeces.getItems().addAll(c.list());
        System.out.println("aq");
//       tableexa.getColumns().add(columnom);

//       tableexa.getItems().add(columnom.getColumns().iterator().next());
        valor = 0;
//         retornar();
    }
    
    
   @FXML
    public void cancelarH() {
        TablaHeces.getItems().clear();
        tableexa.getItems().clear();
        Limpiar();
        Inicial();
        valor = 0;

    }
    
    
     @FXML public void GuardarH() throws IOException{
       TooltipValidation();
  if(valttip==0){
                if(valor==1){
                EditarHeces();
                }else{
                AddCriteria();
            }
                Limpiar();
                Inicial();
                valor=0;
                TablaHeces.getItems().clear();                
          
  } else{
      valttip=0;
  }   

        }
     
      public void AddCriteria(){
       try {
            Transaction t = this.s.beginTransaction();           
            Examen ex = new Examen();
            ExamenHeces eh = new ExamenHeces();
            ExamenHecesCp ehp = new ExamenHecesCp();
            ExamenHecesCm ehm = new ExamenHecesCm();
            
            
            ex.setIdExamen(idexamen);
            
//            eh.setFecha(new Date(fechaH.getValue().format(DateTimeFormatter.ISO_DATE).replace('-', '/')));
            eh.setResultado(this.resultadoP.getText());
            eh.setColor(this.color.getText());
            eh.setMacrofagos(Integer.parseInt(this.macro.getText()));
            eh.setConsistencia(this.consistencia.getText());
            eh.setRestosAlimenticios(this.restosA.getText());
            eh.setMucus(this.mucus.getText());
            eh.setMacroscopicos(this.macrosco.getText());
            eh.setHematies(Integer.parseInt(this.hemati.getText()));
            eh.setMicroscopicos(this.micros.getText());
            eh.setLeucocitos(Integer.parseInt(this.leuc.getText()));
            eh.setRegistro("Activo");
            eh.setExamen(ex);
            s.save(eh);
           
//

            
            ehp.setEntamoebaHA(this.entahistro.getText());
            ehp.setEntamoebaHQ(this.entahisqui.getText());
            ehp.setEntamoebaColiA(this.entacolitro.getText());
            ehp.setEntamoebaColiQ(this.entacoliqui.getText());
            ehp.setEndolimaxA(this.endonatro.getText());
            ehp.setEndolimaxQ(this.endonaqui.getText());
            ehp.setGiardiaA(this.giartro.getText());
            ehp.setGiardiaQ(this.giarqui.getText());
            ehp.setTrichomonasA(this.tritro.getText());
            ehp.setTrichomonasQ(this.triqui.getText());
            ehp.setChilomastixA(this.chitro.getText());
            ehp.setChilomastixQ(this.chiqui.getText());
            ehp.setIodamoebaA(this.lotro.getText());
            ehp.setIodamoebaQ(this.loqui.getText());
            ehp.setOtrosA(this.otro1.getText());
            ehp.setOtrosQ(this.otro2.getText());
            ehp.setRegistro("Activo");   
            ehp.setExamenHeces(eh);

            s.save(ehp);
//        
//         
//         
//          
//
            
            ehm.setAscaris(this.ascaris.getText());
            ehm.setTrichuris(this.trichu.getText());
            ehm.setUncinaria(this.unci.getText());
            ehm.setOtros(this.otroscm.getText());
            ehm.setObservaciones(this.observ.getText());
            ehm.setStrong(this.stron.getText());
            ehm.setEnterobius(this.entero.getText());
            ehm.setTaeniassp(this.tae.getText());
            ehm.setExamenHeces(eh);
            ehm.setRegistro("Activo");
            s.save(ehm);
            t.commit();
        s.close();
        s=NewHibernateUtil.getSessionFactory().openSession();

        t=s.beginTransaction();
            System.out.println("Luego de agregar el nuevo Id es"+idexamen);
            Query query4 = s.createQuery("update Examen set estado='Realizado',Id_Medico=:me where idExamen=:Id");
            query4.setParameter("Id", idexamen);
            query4.setParameter("me", idmedicoactual);
            query4.executeUpdate();
            t.commit();
//
 
            new Alert(Alert.AlertType.INFORMATION,"Examen Agregado").show();
            s.close();
            
            s = NewHibernateUtil.getSessionFactory().openSession();
            
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallo Conexion: " + e.toString());
        }
        
        
        this.valor = 0;
        this.TablaHeces.getItems().clear();
        tableexa.getItems().clear();
    
    }
      
      
      
      
      
         public void EditarH(){
       
       this.Habilitar();
//        resultado.setDisable(false);
        this.AgregarH.setDisable(true);
//        this.camera.setDisable(false);
        this.ListarH.setDisable(true);
//        this.ModificarH.setDisable(true);
//        this.EliminarH.setDisable(true);
        this.CancelarH.setDisable(false);
        this.GuardarH.setDisable(false);
        this.TablaHeces.setDisable(true);
        tableexa.setDisable(true);
//        validarcargo=1;
        this.valor = 1;
     }
     
           
           
            @FXML
    public void Evento() {
//        ModificarH.setDisable(false);
//        EliminarH.setDisable(false);
        CancelarH.setDisable(false);
        AgregarH.setDisable(true);
//  int noexp = TablaOrina.getSelectionModel().getSelectedItem().getExamen().getPacientes().getNoExpediente();
        noExph.setText(String.valueOf(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getPacientes().getNoExpediente()));
        nombreP.setText((TablaHeces.getSelectionModel().getSelectedItem().getExamen().getPacientes().getNombres()));
        sexoP.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getPacientes().getSexo());
        edadP.setText(String.valueOf(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getPacientes().getEdad()));
        resultadoP.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getResultado());
//        String año = TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getFecha().toString().substring(0,4);
//        String mes=TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getFecha().toString().substring(5,7);
//        String dia=TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getFecha().toString().substring(8,10);
//        fechaH.setValue(LocalDate.of(Integer.parseInt(año),Integer.parseInt(mes),Integer.parseInt(dia)));
        color.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getColor());
        macro.setText(String.valueOf(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getMacrofagos()));
        consistencia.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getConsistencia());
        restosA.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getRestosAlimenticios());
        mucus.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getMucus());
        macrosco.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getMacroscopicos());
        hemati.setText(String.valueOf(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getHematies()));
        micros.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getMicroscopicos());
        leuc.setText(String.valueOf(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getLeucocitos()));
        entahistro.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getEntamoebaHA());
        entahisqui.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getEntamoebaHQ());
        entacolitro.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getEntamoebaColiA());
        entacoliqui.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getEntamoebaColiQ());
        endonatro.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getEndolimaxA());
        endonaqui.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getEndolimaxQ());
        giartro.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getGiardiaA());
        giarqui.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getGiardiaQ());
        tritro.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getTrichomonasA());
        triqui.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getTrichomonasQ());
        chitro.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getChilomastixA());
        chiqui.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getChilomastixQ());
        lotro.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getIodamoebaA());
        loqui.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getIodamoebaQ());
        otro1.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getOtrosA());
        otro2.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCp().getOtrosQ());
        
        ascaris.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCm().getAscaris());
        trichu.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCm().getTrichuris());
        unci.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCm().getUncinaria());
        otroscm.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCm().getOtros());
        observ.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCm().getObservaciones());
        stron.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCm().getStrong());
        entero.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCm().getEnterobius());
        tae.setText(TablaHeces.getSelectionModel().getSelectedItem().getExamen().getExamenHeces().getExamenHecesCm().getTaeniassp());
        
    }
    
    @FXML
    public void MetodoClickH() throws IOException {
        
        Evento();
        EditarH();
        
        buscarR.setDisable(true);
        BuscarResultado.setDisable(true);
        buscarR.clear();
        
         this.valor = 1;
        
    }
    
    
    
     public void EditarHeces() throws IOException{

        int id= TablaHeces.getSelectionModel().getSelectedItem().getIdExamen();
        System.out.println(id);
        this.s.getTransaction().begin();
        System.out.println("abrio");
        Query query1 = this.s.createQuery("update ExamenHeces set color=:color,macrofagos=:macro,\n" +
"consistencia=:consistencia,restosAlimenticios=:restos,\n" +
"mucus=:muc,macroscopicos=:ma,\n" +
"hematies=:hema,microscopicos=:micro,leucocitos=:leu,\n" +
"resultado=:resul where idExamen=:Id");
        
        query1.setParameter("color",(Object)this.color.getText());
        query1.setParameter("macro", (Object)Integer.parseInt(this.macro.getText()));
        query1.setParameter("consistencia", (Object)this.consistencia.getText());
        query1.setParameter("restos", (Object)this.restosA.getText());
        query1.setParameter("muc", (Object)this.mucus.getText());
        query1.setParameter("ma", (Object)this.macrosco.getText());
        query1.setParameter("hema", (Object)Integer.parseInt(this.hemati.getText()));
        query1.setParameter("micro", (Object)this.micros.getText());
        query1.setParameter("leu", (Object)Integer.parseInt(this.leuc.getText()));
        query1.setParameter("resul", (Object)this.resultadoP.getText());
        query1.setParameter("Id", id);
        int result1 = query1.executeUpdate();
//       
 Query query2 = this.s.createQuery("update ExamenHecesCp set entamoebaHA=:eht,entamoebaHQ=:ehq,\n" +
"entamoebaColiA=:entat,entamoebaColiQ=:entaq,endolimaxA=:endot,endolimaxQ=:endoq,\n" +
"giardiaA=:giart,giardiaQ=:giarq,trichomonasA=:trit,trichomonasQ=:triq,\n" +
"chilomastixA=:chit,chilomastixQ=:chiq,iodamoebaA=:lot,iodamoebaQ=:loq,\n" +
" otrosA=:otrot,otrosQ=:otrosq where idExamenHeces=:Id");
        query2.setParameter("eht", (Object)this.entahistro.getText());
        query2.setParameter("ehq",(Object)this.entahisqui.getText());
        query2.setParameter("entat", (Object)this.entacolitro.getText());
        query2.setParameter("entaq", (Object)this.entacoliqui.getText());
        query2.setParameter("endot", (Object)this.endonatro.getText());
        query2.setParameter("endoq", (Object)this.endonaqui.getText());
        query2.setParameter("giart",(Object)this.giartro.getText());
        query2.setParameter("giarq", (Object)this.giarqui.getText());
        query2.setParameter("trit", (Object)this.tritro.getText());
        query2.setParameter("triq",(Object)this.triqui.getText());
        query2.setParameter("chit", (Object)this.chitro.getText());
        query2.setParameter("chiq", (Object)this.chiqui.getText());
        query2.setParameter("lot", (Object)this.lotro.getText());
        query2.setParameter("loq", (Object)this.loqui.getText());
        query2.setParameter("otrot", (Object)this.otro1.getText());
        query2.setParameter("otrosq", (Object)this.otro2.getText());
            System.out.println("Parameter");
        query2.setParameter("Id",id);
        
        int result = query2.executeUpdate();
//        
//        
//        
          Query query3 = this.s.createQuery("update ExamenHecesCm set ascaris=:ascar,\n" +
"trichuris=:trichu,uncinaria=:unci,\n" +
"strong=:stro,enterobius=:enter,taeniassp=:tae,\n" +
"otros=:otros,observaciones=:observe where idExamenHeces=:Id");
          
        query3.setParameter("ascar", (Object)this.ascaris.getText());
        query3.setParameter("trichu",(Object)this.trichu.getText());
        query3.setParameter("unci", (Object)this.unci.getText());
        query3.setParameter("stro", (Object)this.stron.getText());
        query3.setParameter("enter", (Object)this.entero.getText());
        query3.setParameter("tae", (Object)this.tae.getText());
        query3.setParameter("otros", (Object)this.otroscm.getText());
        query3.setParameter("observe",(Object)this.observ.getText());
        System.out.println("Parameter");
        query3.setParameter("Id", (Object)id);
//        
        int result2 = query3.executeUpdate();
    System.out.println("actualizado");                
        this.s.getTransaction().commit();
        new Alert(Alert.AlertType.INFORMATION,"Examen Modificado").show();
        this.TablaHeces.getItems().clear();
        tableexa.getItems().clear();
        System.out.println("Limpio");
        s.close();
        s = NewHibernateUtil.getSessionFactory().openSession();
        System.out.println("Reinicio sesion");
        this.cargar = 0;
        System.out.println("cargar=0");
       
    }
    
    public void Inicial() {

        TablaHeces.setDisable(false);
        tableexa.setDisable(false);
        AgregarH.setDisable(false);
        ListarH.setDisable(false);
//        ModificarH.setDisable(true);
//        EliminarH.setDisable(true);
        GuardarH.setDisable(true);
        CancelarH.setDisable(true);

//        find.setDisable(true);
//        PacienteO.setDisable(true);
//        MedicoO.setDisable(true);
buscarR.setDisable(true);
       noExph.setDisable(true);
        nombreP.setDisable(true);
       sexoP.setDisable(true);
        edadP.setDisable(true);
//        fechaH.setDisable(true);
        resultadoP.setDisable(true);
       
        color.setDisable(true);
        macro.setDisable(true);
        consistencia.setDisable(true);
        restosA.setDisable(true);
        mucus.setDisable(true);
        macrosco.setDisable(true);
        hemati.setDisable(true);
        micros.setDisable(true);
        leuc.setDisable(true);
        
        entahistro.setDisable(true);
        entahisqui.setDisable(true);
        entacolitro.setDisable(true);
        entacoliqui.setDisable(true);
        endonatro.setDisable(true);
        endonaqui.setDisable(true);
        giartro.setDisable(true);
        giarqui.setDisable(true);
        tritro.setDisable(true);
        triqui.setDisable(true);
        chitro.setDisable(true);
        chiqui.setDisable(true);
        lotro.setDisable(true);
        loqui.setDisable(true);
        otro1.setDisable(true);
        otro2.setDisable(true);
        
        ascaris.setDisable(true);
        trichu.setDisable(true);
        unci.setDisable(true);
        otroscm.setDisable(true);
        observ.setDisable(true);
        stron.setDisable(true);
        entero.setDisable(true);
        tae.setDisable(true);
        
        
        
        
        
        valor = 0;
//        listPac.add(new Paciente)
    }
    
     public void Habilitar() {

//        PacienteO.setDisable(false);
//        MedicoO.setDisable(false);
        
  noExph.setDisable(true); 
resultadoP.setDisable(false);
    color.setDisable(false);
        macro.setDisable(false);
        consistencia.setDisable(false);
        restosA.setDisable(false);
        mucus.setDisable(false);
        macrosco.setDisable(false);
        hemati.setDisable(false);
        micros.setDisable(false);
        leuc.setDisable(false);
        nombreP.setDisable(true);
        sexoP.setDisable(true);
        edadP.setDisable(true);
//        fechaH.setDisable(true);
//        
        entahistro.setDisable(false);
        entahisqui.setDisable(false);
        entacolitro.setDisable(false);
        entacoliqui.setDisable(false);
        endonatro.setDisable(false);
        endonaqui.setDisable(false);
        giartro.setDisable(false);
        giarqui.setDisable(false);
        tritro.setDisable(false);
        triqui.setDisable(false);
        chitro.setDisable(false);
        chiqui.setDisable(false);
        lotro.setDisable(false);
        loqui.setDisable(false);
        otro1.setDisable(false);
        otro2.setDisable(false);
        
        ascaris.setDisable(false);
        trichu.setDisable(false);
        unci.setDisable(false);
        otroscm.setDisable(false);
        observ.setDisable(false);
        stron.setDisable(false);
        entero.setDisable(false);
        tae.setDisable(false);
        
        

        valor = 0;
    }
     
     
    public void Deshabilitar() {
        TablaHeces.setDisable(true);
        tableexa.setDisable(true);
//        PacienteO.setDisable(true);
//        MedicoO.setDisable(true);
       sexoP.setDisable(true);
        edadP.setDisable(true);
//        fechaH.setDisable(true);
   resultadoP.setDisable(true);
       
        color.setDisable(true);
        macro.setDisable(true);
        consistencia.setDisable(true);
        restosA.setDisable(true);
        mucus.setDisable(true);
        macrosco.setDisable(true);
        hemati.setDisable(true);
        micros.setDisable(true);
        leuc.setDisable(true);
        
        entahistro.setDisable(true);
        entahisqui.setDisable(true);
        entacolitro.setDisable(true);
        entacoliqui.setDisable(true);
        endonatro.setDisable(true);
        endonaqui.setDisable(true);
        giartro.setDisable(true);
        giarqui.setDisable(true);
        tritro.setDisable(true);
        triqui.setDisable(true);
        chitro.setDisable(true);
        chiqui.setDisable(true);
        lotro.setDisable(true);
        loqui.setDisable(true);
        otro1.setDisable(true);
        otro2.setDisable(true);
        
        ascaris.setDisable(true);
        trichu.setDisable(true);
        unci.setDisable(true);
        otroscm.setDisable(true);
        observ.setDisable(true);
        stron.setDisable(true);
        entero.setDisable(true);
        tae.setDisable(true);
        
        
        
        
        
        valor = 0;
        
    }
     
      public void Limpiar() {
//PacienteO.setValue("");
//MedicoO.setValue("");
buscarR.clear();
noExph.clear();
        nombreP.clear();
        resultadoP.clear();
        sexoP.clear();
        edadP.clear();
//        fechaH.setValue(null);
        color.clear();
        macro.clear();
        consistencia.clear();
         restosA.clear();
        mucus.clear();
        macrosco.clear();
        hemati.clear();
        micros.clear();
        leuc.clear();
        entahistro.clear();
        entahisqui.clear();
        entacolitro.clear();
        entacoliqui.clear();
        
        endonatro.clear();
        endonaqui.clear();
        giartro.clear();
        giarqui.clear();
        tritro.clear();
        triqui.clear();
        chitro.clear();
        chiqui.clear();
        lotro.clear();
        loqui.clear();
        otro1.clear();
        otro2.clear();
        
        ascaris.clear();
        trichu.clear();
        unci.clear();
        otroscm.clear();
        observ.clear();
        stron.clear();
        entero.clear();
        tae.clear();
        
        valor = 0;

    }
      public void Stylepred(){
        String stylep="-fx-border-color: black;";
//        noExph.setStyle(stylep);
        resultadoP.setStyle(stylep);
        color.setStyle(stylep);
        macro.setStyle(stylep);
        consistencia.setStyle(stylep);
        restosA.setStyle(stylep);
        mucus.setStyle(stylep);
        macrosco.setStyle(stylep);
        hemati.setStyle(stylep);
        micros.setStyle(stylep);
        leuc.setStyle(stylep);
        
        entahistro.setStyle(stylep);
        entahisqui.setStyle(stylep);
        entacolitro.setStyle(stylep);
        entacoliqui.setStyle(stylep);
        endonatro.setStyle(stylep);
        endonaqui.setStyle(stylep);
        giartro.setStyle(stylep);
        giarqui.setStyle(stylep);
        tritro.setStyle(stylep);
        triqui.setStyle(stylep);
        chitro.setStyle(stylep);
        chiqui.setStyle(stylep);
        lotro.setStyle(stylep);
        loqui.setStyle(stylep);
        otro1.setStyle(stylep);
        otro2.setStyle(stylep);
        
        ascaris.setStyle(stylep);
        trichu.setStyle(stylep);
        unci.setStyle(stylep);
        otroscm.setStyle(stylep);
        observ.setStyle(stylep);
        stron.setStyle(stylep);
        entero.setStyle(stylep);
        tae.setStyle(stylep);
        
        
    }
      
       public static boolean isNumeric(String str) {
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("") == false);
    }
        public void TooltipValidation(){
         Stylepred();
           
    String style="-fx-background-color: orange,linear-gradient(to bottom, derive(orange,60%) 5%,derive(orange,90%) 40%);";
    
    String styletxt="-fx-border-color: red;";
   
    
    if(resultadoP.getText().isEmpty() || resultadoP.getText().equals("") || resultadoP.getText().length()<3){            
        resultadoP.setStyle(styletxt);
        
           valttip = 1;
    }
           
    if(color.getText().isEmpty() || color.getText().equals("") || color.getText().length()<3){
        color.setStyle(styletxt);
        valttip=1;
    }
    if(macro.getText().isEmpty() || macro.getText().equals("") || macro.getText().length()<2){
        macro.setStyle(styletxt);
        valttip=1;
        
    }
    if(consistencia.getText().isEmpty() || consistencia.getText().equals("") || consistencia.getText().length()<3){
        consistencia.setStyle(styletxt);
        valttip=1;   
    } 
    if(restosA.getText().isEmpty() || restosA.getText().equals("") || restosA.getText().length()<4){
        restosA.setStyle(styletxt);
        valttip=1;
    }
    if(mucus.getText().isEmpty() || mucus.getText().equals("") || mucus.getText().length()<4){
        mucus.setStyle(styletxt);
        valttip=1;   
    }
    if(macrosco.getText().isEmpty() || macrosco.getText().equals("") || macrosco.getText().length()<3){
        macrosco.setStyle(styletxt);
        valttip=1;   
    }
    if(hemati.getText().isEmpty() || hemati.getText().equals("") || hemati.getText().length()<2){
        hemati.setStyle(styletxt);
        valttip=1;    
    }
    if(micros.getText().isEmpty() || micros.getText().equals("") || micros.getText().length()<3){
        micros.setStyle(styletxt);
        valttip=1;   
    } 
    if(leuc.getText().isEmpty() || leuc.getText().equals("") || leuc.getText().length()<2){
        leuc.setStyle(styletxt);
        valttip=1;   
    } 
    if(entahistro.getText().isEmpty() || entahistro.getText().equals("") || entahistro.getText().length()<3){
        entahistro.setStyle(styletxt);
        valttip=1;   
    } 
    if(entahisqui.getText().isEmpty() || entahisqui.getText().equals("") || entahisqui.getText().length()<3){
        entahisqui.setStyle(styletxt);
        valttip=1;   
    }
    if(entacolitro.getText().isEmpty() || entacolitro.getText().equals("") || entacolitro.getText().length()<3){
        entacolitro.setStyle(styletxt);
        valttip=1;   
    }
    if(entacoliqui.getText().isEmpty() || entacoliqui.getText().equals("") || entacoliqui.getText().length()<3){
        entacoliqui.setStyle(styletxt);
        valttip=1;   
    }
    if(endonatro.getText().isEmpty() || endonatro.getText().equals("") || endonatro.getText().length()<3){
        endonatro.setStyle(styletxt);
        valttip=1;   
    }
    if(endonaqui.getText().isEmpty() || endonaqui.getText().equals("") || endonaqui.getText().length()<3){
        endonaqui.setStyle(styletxt);
        valttip=1;   
    }
    if(giartro.getText().isEmpty() || giartro.getText().equals("") || giartro.getText().length()<3){
        giartro.setStyle(styletxt);
        valttip=1;   
    }
    if(giarqui.getText().isEmpty() || giarqui.getText().equals("") || giarqui.getText().length()<3){
        giarqui.setStyle(styletxt);
        valttip=1;   
    }
    if(tritro.getText().isEmpty() || tritro.getText().equals("") || tritro.getText().length()<3){
        tritro.setStyle(styletxt);
        valttip=1;   
    }
    if(triqui.getText().isEmpty() || triqui.getText().equals("") || triqui.getText().length()<3){
        triqui.setStyle(styletxt);
        valttip=1;   
    }
    if(chitro.getText().isEmpty() || chitro.getText().equals("") || chitro.getText().length()<3){
        chitro.setStyle(styletxt);
        valttip=1;   
    }
    if(chiqui.getText().isEmpty() || chiqui.getText().equals("") || chiqui.getText().length()<3){
        chiqui.setStyle(styletxt);
        valttip=1;   
    }
    if(lotro.getText().isEmpty() || lotro.getText().equals("") || lotro.getText().length()<3){
        lotro.setStyle(styletxt);
        valttip=1;   
    }
    if(loqui.getText().isEmpty() || loqui.getText().equals("") || loqui.getText().length()<3){
        loqui.setStyle(styletxt);
        valttip=1;   
    }if(otro1.getText().isEmpty() || otro1.getText().equals("") || otro1.getText().length()<3){
        otro1.setStyle(styletxt);
        valttip=1;   
    }
    if(otro2.getText().isEmpty() || otro2.getText().equals("") || otro2.getText().length()<3){
        otro2.setStyle(styletxt);
        valttip=1;   
    }
     if(ascaris.getText().isEmpty() || ascaris.getText().equals("") || ascaris.getText().length()<3){
        ascaris.setStyle(styletxt);
        valttip=1;   
    }
      if(trichu.getText().isEmpty() || trichu.getText().equals("") || trichu.getText().length()<3){
        trichu.setStyle(styletxt);
        valttip=1;   
    }
       if(unci.getText().isEmpty() || unci.getText().equals("") || unci.getText().length()<3){
        unci.setStyle(styletxt);
        valttip=1;   
    }
        if(otroscm.getText().isEmpty() || otroscm.getText().equals("") || otroscm.getText().length()<3){
        otroscm.setStyle(styletxt);
        valttip=1;   
    }
         if(observ.getText().isEmpty() || observ.getText().equals("") || observ.getText().length()<3){
        observ.setStyle(styletxt);
        valttip=1;   
    }
          if(stron.getText().isEmpty() || stron.getText().equals("") || stron.getText().length()<3){
        stron.setStyle(styletxt);
        valttip=1;   
    }
           if(entero.getText().isEmpty() || entero.getText().equals("") || entero.getText().length()<3){
        entero.setStyle(styletxt);
        valttip=1;   
    }
            if(tae.getText().isEmpty() || tae.getText().equals("") || tae.getText().length()<3){
        tae.setStyle(styletxt);
        valttip=1;   
    }
    
    
    
    
     }
        
        
   @FXML
    public void rNoe() {
        Nombre.setSelected(false);
        buscarR.setDisable(false);

    }

    @FXML
    public void rNom() {
        Expediente.setSelected(false);
        buscarR.setDisable(false);
    }
    
    
      @FXML
    public void find() {
        if (Expediente.isSelected()) {
            NumeroExpediente();
            
        }
        if (Nombre.isSelected()) {
           
            NombrePaciente();
        }

        
         buscarR.setDisable(false);
     CancelarH.setDisable(false);

    }
        
//        
//        
        public void NumeroExpediente(){
        if( buscarR.getText().isEmpty() == false || buscarR.getText().length() == 8){
         
        
        Query query = s.createQuery("select new map (eh.idExamen)\n" +
"from Pacientes as p\n" +
"inner join p.examens as e\n" +
"inner join e.examenHeces as eh\n" +
" where noExpediente=:Exp");
        query.setParameter("Exp", buscarR.getText());
        List<Map> listaResultados = query.list();    
        
            System.out.println(listaResultados);
            System.out.println(listaResultados.size());
            
            if(listaResultados.size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Resultados").show();
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
 
        Criteria c = s.createCriteria(ExamenHeces.class).add(Restrictions.like("registro","Activo"))
        .add(Restrictions.in("idExamen", mapa));
 
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            ExamenHeces p= (ExamenHeces) pac;
            
            System.out.println(p.getResultado());
         
        }

        columnoe.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenHeces, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<ExamenHeces, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNoExpediente());
            }
        });

        columpaciente.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenHeces, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenHeces, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNombres());

            }
        });

        colummedico.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenHeces, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenHeces, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getMedicos().getNombre());

            }
        });
        
        columobservaciones.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenHeces, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenHeces, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getExamenHeces().getExamenHecesCm().getObservaciones());

            }
        });
        
         
        
        TablaHeces.getItems().clear();
       TablaHeces.getItems().addAll(c.list());   
                
            }
        
        }else{
        new Alert(Alert.AlertType.ERROR,"Para buscar necesita el número de expediente del paciente").show();
        }
            
      
    }  
        
        public void NombrePaciente(){
        if( buscarR.getText().isEmpty() == false || buscarR.getText().length() == 8){
         
        
        Query query = s.createQuery("select new Map(eh.idExamen)\n" +
"from Pacientes as p\n" +
"inner join p.examens as e\n" +
"inner join e.examenHeces as eh\n" +
" where nombres=:Nombre");
        query.setParameter("Nombre", buscarR.getText());
        List<Map> listaResultados = query.list();    
        
            System.out.println(listaResultados);
            System.out.println(listaResultados.size());
            
            if(listaResultados.size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Resultados").show();
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
 
        Criteria c = s.createCriteria(ExamenHeces.class).add(Restrictions.like("registro","Activo"))
        .add(Restrictions.in("idExamen", mapa));
 
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            ExamenHeces p= (ExamenHeces) pac;
            
            System.out.println(p.getResultado());
         
        }

        columnoe.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenHeces, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<ExamenHeces, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNoExpediente());
            }
        });

        columpaciente.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenHeces, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenHeces, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNombres());

            }
        });

        colummedico.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenHeces, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenHeces, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getMedicos().getNombre());

            }
        });
        
        columobservaciones.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenHeces, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenHeces, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getExamenHeces().getExamenHecesCm().getObservaciones());

            }
        });
        
         
        
        TablaHeces.getItems().clear();
       TablaHeces.getItems().addAll(c.list());   
                
            }
        
        }else{
        JOptionPane.showMessageDialog(null, "Para buscar necesita el número de expediente del paciente", "Sistema de infromación", JOptionPane.INFORMATION_MESSAGE);
        
        }
            
      
    }
        
    @FXML public void select(){
    
    idexamen=tableexa.getSelectionModel().getSelectedItem().getIdExamen();
    noExph.setText(tableexa.getSelectionModel().getSelectedItem().getPacientes().getNoExpediente().toString());
    nombreP.setText(tableexa.getSelectionModel().getSelectedItem().getPacientes().getNombres()+" "+
            tableexa.getSelectionModel().getSelectedItem().getPacientes().getApellidos());
    edadP.setText(tableexa.getSelectionModel().getSelectedItem().getPacientes().getEdad().toString());
    sexoP.setText(tableexa.getSelectionModel().getSelectedItem().getPacientes().getSexo());    
        
    }        
        
   public void ListaExamenes(){
       ObservableList<String> minilist = FXCollections.observableArrayList("Emergencia","Pendiente");
       Criteria c = s.createCriteria(Examen.class).add(Restrictions.like("registro","Activo"))
       .add(Restrictions.in("estado", minilist))
       .addOrder( Order.asc("estado") )
       .add(Restrictions.eq("categoria", "Heces"));
       
                   if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancelarH();
                Inicial();
                
            }else{
        columnoe1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Examen, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Examen, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPacientes().getNoExpediente());
            }
        });

        columnom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Examen, String>, ObservableValue<String>>() {
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
        
//    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idmedicoactual=config2.MedicoId;
        System.out.println("El dia de hoy se encarga"+idmedicoactual);
        columresultado.setCellValueFactory(new PropertyValueFactory<> ("resultado"));
Inicial();

        columcat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        columtipo.setCellValueFactory(new PropertyValueFactory<>("examenTipo"));
        columestado.setCellValueFactory(new PropertyValueFactory<>("estado"));


TablaHeces.setOnMousePressed(new EventHandler<MouseEvent>(){
        public void handle(MouseEvent me) {
    System.out.println(TablaHeces.getSelectionModel().getSelectedIndex());
//        ModificarH.setDisable(false);
//        EliminarH.setDisable(false);
        CancelarH.setDisable(false);
        AgregarH.setDisable(true);
            if(me.getClickCount()==1){
            if(TablaHeces.getSelectionModel().getSelectedIndex()==-1){
//              ModificarH.setDisable(true);
//              EliminarH.setDisable(true);
            }
                    
            System.out.println(me.getButton());    
            
            if(me.getButton().toString()=="SECONDARY"){
                    System.out.println("clic derecho");
                
                    TablaHeces.setContextMenu(conmenu);
            }
            
            }

        }
         });
    }    
    
}
