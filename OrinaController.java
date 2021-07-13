/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import static Controladores.RegExaController.isNumeric;
import Hibernate.NewHibernateUtil;
import POJO.Examen;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.zone;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.imageio.ImageIO;
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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author Wilmer Lopez
 */
public class OrinaController implements Initializable {

    SessionFactory factor;
    Session s = NewHibernateUtil.getSessionFactory().openSession();

    //    Map<String, String> map = new HashMap<>();
    @FXML
    public Button NuevoExa, BuscarExa, listO, guardarO, cancelarO, BuscarRe, idclean;
    
    @FXML
    public TextField SexoO, EdadO,resultado,NoEx,NombrePaciente,buscarR;
    //Examen Quimico
@FXML
    public RadioButton Nombre, Expediente;

    @FXML
    TextArea idres;
        
    @FXML
    public TextField color, aspecto, densidad, ph, proteina, glucosa,
            sangreoculta, cuerposcet, Uribilinogeno, bilirrubina, nitritos, hemoglobinas, esterasa;
 int idmedicoactual;
    //Examen Microscopico
    @FXML
    public TextField cilindros, granulosos, leucocitarios, hematicos, hialinos, hematies,
            leucocitos, celulasepi, cristales, parasito, otros, observaciones;
    @FXML
    ContextMenu conmenu;
   @FXML
    MenuItem meneli, menmos, menmodi;
//    @FXML
//    public DatePicker FechaO;
//    @FXML
    public TableView<ExamenOrina> TablaOrina;
    @FXML
    public TableColumn<ExamenOrina, String> columresultado;
    @FXML
    public TableColumn<ExamenOrina, String> columobservaciones;

    @FXML
    public TableColumn columnoe;
    int valttip=0;
//    TableColumn<Map.Entry<String, String>, String> columnom ;
    @FXML
    public TableColumn columpaciente;

    @FXML
    public TableColumn colummedico;
    int valor = 0;
    int cargar = 0;
    int idexamen;
    String variable,variable1;

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
    
    
//    ObservableList<String> pacientesO = FXCollections.observableArrayList();
//    ObservableList<String> medicos = FXCollections.observableArrayList();
    
    @FXML public void idclean(){
        this.idres.setText("");
        clean();
    }
    
   
    
     @FXML public void GuardarO() throws IOException, JRException, SQLException{
               
         
         
               TooltipValidation();
  if(valttip==0){
                 if(valor==1){                       
                AddResultado();                 
                EditarOrina();
                openReportPac();                                                
                }else{                     
                AddResultado();                
                AddCriteria();
                openReportPac();                
            }
                 
                Limpiar();
                Inicial();
                valor=0;
                TablaOrina.getItems().clear();  
                tableexa.getItems().clear();
          
  } else{
       
      valttip=0;
  }   

                            


        }
     
     public void openReportPac() throws JRException, SQLException{
         String path = "src\\Reportes\\ReporteExamenIndividual.jasper";
            Connection a = DriverManager.getConnection("jdbc:mysql://localhost:3306/Laboratorio","root","1996set");

      JasperPrint jasperPrint; 
       
        Map parameters = new HashMap();
            System.out.println(idexamen);           
            parameters.put("IdExamen", idexamen);            
      
      jasperPrint = JasperFillManager.fillReport(path, parameters,a);
      JasperViewer jv = new JasperViewer(jasperPrint, false);
      jv.setTitle("Reporte de resultados del paciente");
      jv.setVisible(true);                    
     }
     
     public void AddResultado(){
         
         //ph
                int minPh = 4, maxPh = 8 ;
                //Densidad
                int minDen = 1005, maxDen = 1035;
                //Glucosa
                int minGlu = 0, maxGlu = 15;
                //Proteina
                int minPro = 0, maxPro = 20;
                //Cuerpos Cet
                int minCCet = 30, maxCCet = 70;
                //Hematies
                int minHem = 3, maxHem = 5;
                //Leucocitos
                int minLeu = 0, maxLeu = 5;
                //Bilirrubina
                int minBili = 0, maxBili = 2;               
                //urobilinógeno puede estar presente sin importancia clínica
                //Nitritos
                boolean posNitri = true, negNitri = false;
                //Celulas epiteliales
                int minCEp = 2, maxCEp = 100;
                
                String texto = "";
                
                //PH
                if(Integer.parseInt(this.ph.getText()) < minPh){
                texto = texto + "\n PH por debajo de parámetro, puede significar alto consumo de carne, "
                             + "se recomienda alimentación más balanceado \n";
                 }
                 
                if(Integer.parseInt(this.ph.getText()) > maxPh){                  
                texto = texto + "\n PH por encima de parámetro, puede significar alto consumo de vegetales, "
                            + "se recomienda alimentación más balanceado \n";                        
                }
                   
                //Densidad
                if(Integer.parseInt(this.densidad.getText()) < minDen){
                    texto = texto + "\n Densidad por debajo parámetro, puede significar daño a la célula de riñon,"
                            + " Consumo de mucho líquido o insuficiencia renal,"
                            + " se recomienda toma de líquidos en cantidades apropiadas o para clamar la sed. \n";
                    }
                
                if(Integer.parseInt(this.densidad.getText()) > maxDen){
                   texto = texto + "\n Densidad por encima de parámetro, puede significar deshidratación"
                           + "Diarrea a causa de deshidratación, azúcar en la sangre. Se recomienda la toma"
                           + "de líquidos moderado \n";                                   
                    }
                
                //Glucosa
                if(Integer.parseInt(this.glucosa.getText()) > maxGlu){
                    texto = texto + "\n Glucosa por encima de parámetros, puede significar Diabetes, Embarazo, infección renal."
                            + "Se recomienda evitar el consumo de azúcar \n";
                }
                
                //Proteina
                if(Integer.parseInt(this.proteina.getText()) > maxPro){
                    texto = texto + "\n Proteina por encima de parámetros, puede significar insuficiencia cardiaca,"
                            + "problemas renales, deshidratación, problema en las vías urinarias, problemas en"
                            + "el embarazo. Se recomienda el consumo adecuado de esta en las carnes y vegetales"
                            + "que lo poseen \n";
                }
                      
                //Cuerpos Cet
                if(Integer.parseInt(this.cuerposcet.getText()) < minCCet){
                    texto = texto + "\n Cetonas por debajo de parámetros, puede deberse a anorexia, vómitos durante"
                            + "largo tiempo, Fiebres altas, amamantar a un bebé (Cuando la madre no se alimenta bien)"
                            + ". Se recomienda un consumo moderado y balanceado de alimentos. \n";
                }
                
                if(Integer.parseInt(this.cuerposcet.getText()) > maxCCet){
                    texto = texto + "\n Cetonas por encima de parámetros, puede deberse a Dieta con contenido alto"
                            + "en proteínas, hipertiroidismo. Se recomienda el consumo adecuado de esta en las carnes"
                            + "y vegetales que lo poseen. \n";
                }
                
                //Hematies
                if(Integer.parseInt(this.hematies.getText()) < minHem){
                    texto = texto + "\n Hematies por debajo de parámetros, puede deberse a hemorragia, mala alimentación, anemia. "
                            + "Se recomienda una alimentación balanceada y consumo de líquidos para la defensa contra cualquier"
                            + "hemorragia \n";
                }
                
                if(Integer.parseInt(this.hematies.getText()) > maxHem){
                    texto = texto + "\n Hematies por encima de parámetros, puede deberse a contaminación de muestra, cálculo renal, mucho ejercicio físico, algún tipo de anemia. "
                            + "Se recomienda seguir las recomendaciones del doctor para el trato de esto. \n";
                }
                
                //Leucocitos
                if(Integer.parseInt(this.leucocitos.getText()) < minLeu){
                    texto = texto + "\n Leucocitos por debajo de parámetros, puede deberse a inflamación en las vías urinarias, infección urinaria"
                            + "uso de sustancias irritantes. \n";                            
                }
                
                if(Integer.parseInt(this.leucocitos.getText()) > maxLeu){
                    texto = texto + "\n Leucocitos por debajo de parámetros, puede deberse a inflamación en las vías urinarias, infección urinaria"
                            + "uso de sustancias irritantes. \n";                            
                }
                
                //Bilirrubina
                if(Integer.parseInt(this.bilirrubina.getText()) < minBili){
                    texto = texto + "\n Bilirrubina por debajo de parámetros, puede deberse a enfermedad hepática o destrucción de hematíes."
                            + "Se recomienda el reposo para evitar el esfuerzo al hígado, hasta que el doctor le de"
                            + "indicaciones \n";                            
                }
                
                if(Integer.parseInt(this.bilirrubina.getText()) > maxBili){
                    texto = texto + "\n Bilirrubina por encima de parámetros, puede deberse a el nivel sanguíneo alto."
                            + "Se recomienda seguir las indicaciones del doctor. \n";                            
                }
                
                //Nitritos
                if(Integer.parseInt(this.nitritos.getText()) > 0){
                    texto = texto + "\n Existencia de nitritos. Puede significar infección urinaria. Se recomienda tomar los debidos antibióticos guiados por el doctor. \n";
                }
                
                //Célula epiteliales
                if(Integer.parseInt(this.celulasepi.getText()) >= maxCEp){
                    texto = texto + "\n Celulas Epiteliales por encima de parámetros. Puede significar vaginitis,"
                            + "uretritis, infecciones renales, daños en el riñon. Se recomienda realizar el examen profundo"
                            + "para determinar el tipo de célula y su infección específica. \n";
                }
                                
                this.idres.setText(texto);
     }
     
     public void AddCriteria(){
    try {
            Transaction t = this.s.beginTransaction();           
            Examen ex = new Examen();
            ExamenOrina exo = new ExamenOrina();
            ExamenOrinaFq fq = new ExamenOrinaFq();
            ExamenOrinaM om = new ExamenOrinaM();
            
            ex.setIdExamen(idexamen);
            
//            exo.setFecha(new Date(FechaO.getValue().format(DateTimeFormatter.ISO_DATE).replace('-', '/')));
            exo.setResultado(this.resultado.getText());
            System.out.println(this.idres.getText());
            exo.setDiagnostico(this.idres.getText());
            exo.setObservaciones(this.observaciones.getText());
            exo.setExamen(ex);
            exo.setRegistro("Activo");
            s.save(exo);
           

            exo.setIdExamen(idexamen);

            fq.setColor(this.color.getText());
            fq.setAspecto(this.aspecto.getText());
            fq.setDensidad(Integer.parseInt(densidad.getText()));
            fq.setPh(Integer.parseInt(ph.getText()));
            fq.setProteinas(Integer.parseInt(proteina.getText()));
            fq.setGlucosa(Integer.parseInt(glucosa.getText()));
            fq.setSangreOculta(Integer.parseInt(sangreoculta.getText()));
            fq.setCuerposCet(Integer.parseInt(cuerposcet.getText()));
            fq.setUrobilinogeno(Integer.parseInt(Uribilinogeno.getText()));
            fq.setBilirrubina(Integer.parseInt(bilirrubina.getText()));
            fq.setNitritos(Integer.parseInt(nitritos.getText()));
            fq.setHemoglobina(Integer.parseInt(hemoglobinas.getText()));
            fq.setEsterasa(Integer.parseInt(esterasa.getText()));
            fq.setExamenOrina(exo);
            fq.setRegistro("Activo");
            s.save(fq);
        
         
         
          

            exo.setIdExamen(idexamen);

            om.setCilindros(Integer.parseInt(cilindros.getText()));
            om.setGranulosos(Integer.parseInt(granulosos.getText()));
            om.setLeucocitarios(Integer.parseInt(leucocitarios.getText()));
            om.setHematicos(Integer.parseInt(hematicos.getText()));
            om.setHialinos(Integer.parseInt(hialinos.getText()));
            om.setHematies(Integer.parseInt(hematies.getText()));
            om.setLeucocitos(Integer.parseInt(leucocitos.getText()));
            om.setCelulasEpiteliales(Integer.parseInt(celulasepi.getText()));
            om.setCristales(Integer.parseInt(cristales.getText()));
            om.setParasitologico(Integer.parseInt(parasito.getText()));
            om.setOtros(Integer.parseInt(otros.getText()));
            om.setExamenOrina(exo);
            om.setRegistro("Activo");
            s.save(om);
            t.commit();
                    s.close();
        s=NewHibernateUtil.getSessionFactory().openSession();
            
            t=s.beginTransaction();
            Query query4 = s.createQuery("update Examen set estado='Realizado',Id_Medico=:me where idExamen=:Id");
            query4.setParameter("Id", idexamen);
            query4.setParameter("me", idmedicoactual);
            query4.executeUpdate();
            
            Query queryUpd = s.createQuery("update ExamenOrina set diagnostico = :diag where Id_Examen = :idexamen" );
            queryUpd.setParameter("diag", this.idres.getText());
            queryUpd.setParameter("idexamen", idexamen);
            queryUpd.executeUpdate();
//
            t.commit(); 
            new Alert(Alert.AlertType.INFORMATION,"Examen Agregado").show();
            
            s.close();
            
            s = NewHibernateUtil.getSessionFactory().openSession();
            
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallo Conexion: " + e.toString());
        }
        
        
        this.valor = 0;
        this.TablaOrina.getItems().clear();
        tableexa.getItems().clear();
    
    }
     
     
     public void EditarO(){
         this.Habilitar();
        resultado.setDisable(false);
        this.NuevoExa.setDisable(true);
//        this.camera.setDisable(false);
        this.listO.setDisable(true);
//        this.EditarO.setDisable(true);
//        this.EliminarO.setDisable(true);
        this.cancelarO.setDisable(false);
        this.guardarO.setDisable(false);
        this.TablaOrina.setDisable(true);
        tableexa.setDisable(true);
//        validarcargo=1;
        this.valor = 1;
     
     }
     
      @FXML
    public void MetodoClick() throws IOException {
        
        Evento();
        EditarO();
        
    }
    
    
    @FXML public void select(){
    
    idexamen=tableexa.getSelectionModel().getSelectedItem().getIdExamen();
    NoEx.setText(tableexa.getSelectionModel().getSelectedItem().getPacientes().getNoExpediente().toString());
    NombrePaciente.setText(tableexa.getSelectionModel().getSelectedItem().getPacientes().getNombres()+" "+
            tableexa.getSelectionModel().getSelectedItem().getPacientes().getApellidos());
    EdadO.setText(tableexa.getSelectionModel().getSelectedItem().getPacientes().getEdad().toString());
    SexoO.setText(tableexa.getSelectionModel().getSelectedItem().getPacientes().getSexo());    
        
    }
     
    public void EditarOrina() throws IOException{

        int id= TablaOrina.getSelectionModel().getSelectedItem().getIdExamen();
        System.out.println(id);
        this.s.getTransaction().begin();
        System.out.println("abrio");
        Query query1 = this.s.createQuery("update ExamenOrina set resultado=:resul,observaciones=:Obser\n" +
"                where idExamen=:Id");
        query1.setParameter("resul", (Object)this.resultado.getText());
        query1.setParameter("Obser", (Object)this.observaciones.getText());
        query1.setParameter("Id", id);
        int result1 = query1.executeUpdate();
       
 Query query2 = this.s.createQuery("update ExamenOrinaFq set color =:col , aspecto=:aspec,densidad=:den, ph=:ph , proteinas =:prot,\n" +
"glucosa=:gluc,sangreOculta =:socul,cuerposCet=:cuer,urobilinogeno=:uri,bilirrubina=:bili,nitritos=:nitri,\n" +
"hemoglobina=:hemo,esterasa=:este where idExamenOrina=:Id");
        query2.setParameter("col", (Object)this.color.getText());
        query2.setParameter("aspec",(Object)this.aspecto.getText());
        query2.setParameter("den", (Object)Integer.parseInt(this.densidad.getText()));
        query2.setParameter("ph", (Object)Integer.parseInt(this.ph.getText()));
        query2.setParameter("prot", (Object)Integer.parseInt(this.proteina.getText()));
        query2.setParameter("gluc", (Object)Integer.parseInt(this.glucosa.getText()));
        query2.setParameter("socul", (Object)Integer.parseInt(this.sangreoculta.getText()));
        query2.setParameter("cuer", (Object)Integer.parseInt(this.cuerposcet.getText()));
        query2.setParameter("uri", (Object)Integer.parseInt(this.Uribilinogeno.getText()));
        query2.setParameter("bili",(Object)Integer.parseInt(this.bilirrubina.getText()));
        query2.setParameter("nitri", (Object)Integer.parseInt(this.nitritos.getText()));
        query2.setParameter("hemo", (Object)Integer.parseInt(this.hemoglobinas.getText()));
        query2.setParameter("este", (Object)Integer.parseInt(this.esterasa.getText()));
            System.out.println("Parameter");
        query2.setParameter("Id",id);
        
        int result = query2.executeUpdate();
        
        
        
          Query query3 = this.s.createQuery("update ExamenOrinaM set cilindros=:cilin,granulosos=:granu,leucocitarios=:leuco ,hematicos=:hema,\n" +
"hialinos=:hiali ,hematies=:hemati ,leucocitos=:leu,\n" +
"celulasEpiteliales=:cel,cristales=:cris,parasitologico=:para,otros=:otro\n" +
"where idExamenOrina=:Id");
        query3.setParameter("cilin", (Object)Integer.parseInt(this.cilindros.getText()));
        query3.setParameter("granu",(Object)Integer.parseInt(this.granulosos.getText()));
        query3.setParameter("leuco", (Object)Integer.parseInt(this.leucocitarios.getText()));
        query3.setParameter("hema", (Object)Integer.parseInt(this.hematicos.getText()));
        query3.setParameter("hiali", (Object)Integer.parseInt(this.hialinos.getText()));
        query3.setParameter("hemati", (Object)Integer.parseInt(this.hematies.getText()));
        query3.setParameter("leu", (Object)Integer.parseInt(this.leucocitos.getText()));
        query3.setParameter("cel", (Object)Integer.parseInt(this.celulasepi.getText()));
        query3.setParameter("cris", (Object)Integer.parseInt(this.cristales.getText()));
        query3.setParameter("para",(Object)Integer.parseInt(this.parasito.getText()));
        query3.setParameter("otro", (Object)Integer.parseInt(this.otros.getText()));
        System.out.println("Parameter");
        query3.setParameter("Id", (Object)id);
//        
        int result2 = query3.executeUpdate();
        
        
        
        
        System.out.println("actualizado");                
        this.s.getTransaction().commit();
        new Alert(Alert.AlertType.INFORMATION,"Examen Modificado").show();
        this.TablaOrina.getItems().clear();
        System.out.println("Limpio");
        s.close();
        s = NewHibernateUtil.getSessionFactory().openSession();
        System.out.println("Reinicio sesion");
        this.cargar = 0;
        System.out.println("cargar=0");
       
    }
    
      @FXML
    public void Evento() {
//        EditarO.setDisable(false);
//        EliminarO.setDisable(false);
        cancelarO.setDisable(false);
        NuevoExa.setDisable(true);
        BuscarExa.setDisable(true);
//  int noexp = TablaOrina.getSelectionModel().getSelectedItem().getExamen().getPacientes().getNoExpediente();
        NoEx.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getPacientes().getNoExpediente()));
        NombrePaciente.setText((TablaOrina.getSelectionModel().getSelectedItem().getExamen().getPacientes().getNombres()));
        SexoO.setText(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getPacientes().getSexo());
        EdadO.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getPacientes().getEdad()));
        resultado.setText(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getResultado());
//        String año = TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getFecha().toString().substring(0,4);
//        String mes=TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getFecha().toString().substring(5,7);
//        String dia=TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getFecha().toString().substring(8,10);
//        FechaO.setValue(LocalDate.of(Integer.parseInt(año),Integer.parseInt(mes),Integer.parseInt(dia)));
//        Criteria c = s.createCriteria(ExamenOrinaFq.class).add(Restrictions.eq("idMedico", noexp));
//          System.out.println(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getObservaciones());
        color.setText(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaFq().getColor());
        aspecto.setText(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaFq().getAspecto());
        densidad.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaFq().getDensidad()));
        ph.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaFq().getPh()));
        proteina.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaFq().getProteinas()));
        glucosa.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaFq().getGlucosa()));
        sangreoculta.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaFq().getSangreOculta()));
        cuerposcet.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaFq().getCuerposCet()));
        Uribilinogeno.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaFq().getUrobilinogeno()));
        bilirrubina.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaFq().getBilirrubina()));
        nitritos.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaFq().getNitritos()));
        hemoglobinas.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaFq().getHemoglobina()));
        esterasa.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaFq().getEsterasa()));
        cilindros.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaM().getCilindros()));
        granulosos.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaM().getGranulosos()));
        leucocitarios.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaM().getLeucocitarios()));
        hematicos.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaM().getHematicos()));
        hialinos.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaM().getHialinos()));
        hematies.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaM().getHematies()));
        leucocitos.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaM().getLeucocitos()));
        celulasepi.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaM().getCelulasEpiteliales()));
        cristales.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaM().getCristales()));
        parasito.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaM().getParasitologico()));
        otros.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getExamenOrinaM().getOtros()));
        observaciones.setText(String.valueOf(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getObservaciones()));
//        FechaO.setValue(new Date(TablaOrina.getSelectionModel().getSelectedItem().getExamen().getExamenOrina().getFecha()));
//        combocat.setValue(tableexa.getSelectionModel().getSelectedItem().getCategoria());
//        comboexa.setValue(tableexa.getSelectionModel().getSelectedItem().getExamenTipo());
//        combomed.setValue(tableexa.getSelectionModel().getSelectedItem().getMedicos().getNombre());

    }
    
    
    
    @FXML
    public void NuevoExa() {
        //
        
        NoEx.setDisable(false);
        
        NuevoExa.setDisable(true);
        BuscarExa.setDisable(false);
        listO.setDisable(true);
//        EditarO.setDisable(true);
//        EliminarO.setDisable(true);
        cancelarO.setDisable(false);
        guardarO.setDisable(false);
        resultado.setDisable(true);
        valor = 0;
        TablaOrina.getItems().clear();
        
        ListaExamenes();
        Habilitar();
    }

    @FXML
    public void listarO() {

        Deshabilitar();
        TablaOrina.setDisable(false);
        criterialist();
//            Evento();
        listO.setDisable(true);

        cancelarO.setDisable(false);
        valor = 0;

    }
    
    @FXML
    public void clean0(){
        idres.setText(" ");
    }

   public void ExamenSeleccionar(){
      
        tableexa.getSelectionModel().getSelectedItem().getIdExamen();
       
   }
    
   public void ListaExamenes(){
       ObservableList<String> minilist = FXCollections.observableArrayList("Emergencia","Pendiente");
       Criteria c = s.createCriteria(Examen.class).add(Restrictions.like("registro","Activo"))
       .add(Restrictions.in("estado", minilist))
       .addOrder( Order.asc("estado") )
       .add(Restrictions.eq("categoria", "Orina"));
       
                   if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancelarO();
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
              
        
    public void criterialist() {

        Criteria c = s.createCriteria(ExamenOrina.class).add(Restrictions.like("registro","Activo"))       
                .addOrder(Order.asc("resultado"));

        columnoe.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenOrina, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<ExamenOrina, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNoExpediente());
            }
        });

        columpaciente.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenOrina, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenOrina, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNombres());

            }
        });

        colummedico.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenOrina, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenOrina, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getMedicos().getNombre());

            }
        });

        TablaOrina.getItems().clear();
        TablaOrina.getItems().addAll(c.list());
        System.out.println("aq");
//       tableexa.getColumns().add(columnom);

//       tableexa.getItems().add(columnom.getColumns().iterator().next());
        valor = 0;
//         retornar();
    }
    
//         
   @FXML
    public void rNoe() {
        Nombre.setSelected(false);
        buscarR.setDisable(false);
        cancelarO.setDisable(false);

    }
    
     public void clean(){
        this.idres.setText(" ");
    }

    @FXML
    public void rNom() {
        Expediente.setSelected(false);
        buscarR.setDisable(false);
        cancelarO.setDisable(false);
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
     cancelarO.setDisable(false);

    }
        
//        
//        
        public void NumeroExpediente(){
        if( buscarR.getText().isEmpty() == false || buscarR.getText().length() <2||buscarR.getText().length()>8){
         
        
        Query query = s.createQuery("select new map(eo.idExamen)\n" +
"from Pacientes as p\n" +
"inner join p.examens as e\n" +
"inner join e.examenOrina as eo\n" +
" where noExpediente=:expediente");
        query.setParameter("expediente", buscarR.getText());
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
 
        Criteria c = s.createCriteria(ExamenOrina.class).add(Restrictions.like("registro","Activo"))
        .add(Restrictions.in("idExamen", mapa));
 
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            ExamenOrina p= (ExamenOrina) pac;
            
            System.out.println(p.getResultado());
         
        }

        
          columnoe.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenOrina, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<ExamenOrina, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNoExpediente());
            }
        });

        columpaciente.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenOrina, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenOrina, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNombres());

            }
        });

        colummedico.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenOrina, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenOrina, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getMedicos().getNombre());

            }
        });
        
        TablaOrina.getItems().clear();
       TablaOrina.getItems().addAll(c.list());   
                
            }
        
        }else{
        JOptionPane.showMessageDialog(null, "Para buscar necesita el número de expediente del paciente", "Sistema de infromación", JOptionPane.INFORMATION_MESSAGE);
        
        }
            
      
    }  
        
        public void NombrePaciente(){
        if( buscarR.getText().isEmpty() == false || buscarR.getText().length() <2){
         
        
        Query query = s.createQuery("select new map(eo.idExamen)\n" +
"from Pacientes as p\n" +
"inner join p.examens as e\n" +
"inner join e.examenOrina as eo\n" +
" where nombres=:nombre");
        query.setParameter("nombre", buscarR.getText());
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
 
        Criteria c = s.createCriteria(ExamenOrina.class).add(Restrictions.like("registro","Activo"))
        .add(Restrictions.in("idExamen", mapa));
 
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            ExamenOrina p= (ExamenOrina) pac;
            
            System.out.println(p.getResultado());
         
        }

       
        columnoe.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenOrina, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<ExamenOrina, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNoExpediente());
            }
        });

        columpaciente.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenOrina, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenOrina, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getPacientes().getNombres());

            }
        });

        colummedico.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenOrina, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExamenOrina, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getExamen().getMedicos().getNombre());

            }
        });
         
        
        TablaOrina.getItems().clear();
       TablaOrina.getItems().addAll(c.list());   
                
            }
        
        }else{
        JOptionPane.showMessageDialog(null, "Para buscar necesita el número de expediente del paciente", "Sistema de infromación", JOptionPane.INFORMATION_MESSAGE);
        
        }
            
      
    }
     
     
  @FXML
    public void cancelarO() {
        TablaOrina.getItems().clear();
        Limpiar();
        Inicial();
        
        valor = 0;

    }
     
       
       
   
       
       
    
//    public void suprimir() {
//       int id= TablaOrina.getSelectionModel().getSelectedItem().getIdExamen();
//        s.getTransaction().begin();
//        Query query = s.createQuery("delete from ExamenOrina where idExamen= :Id");
//        query.setParameter("Id", id);
//        int result = query.executeUpdate();
//        
//        Query query1 = s.createQuery("delete from ExamenOrinaFq where idExamenOrina= :Id");
//        query1.setParameter("Id", id);
//        int result1= query1.executeUpdate();
//        
//          Query query2 = s.createQuery("delete from ExamenOrinaM where idExamenOrina= :Id");
//        query1.setParameter("Id", id);
//        int result2= query2.executeUpdate();
//       if(result == 1)
//              JOptionPane.showMessageDialog(null,"Registro eliminado correctamente","Sistema de información",JOptionPane.INFORMATION_MESSAGE);
//              else
//                JOptionPane.showMessageDialog(null,"Error al eliminar el registro","Sistema de información",JOptionPane.ERROR_MESSAGE);
//            
//            s.getTransaction().commit();
//            TablaOrina.getItems().clear();
//            Inicial();
//            valor=0;
//    }
    public void Inicial() {

        TablaOrina.setDisable(false);
        tableexa.setDisable(false);
        NuevoExa.setDisable(false);
        BuscarExa.setDisable(true);
        listO.setDisable(false);
//        EditarO.setDisable(true);
//        EliminarO.setDisable(true);
        guardarO.setDisable(true);
        cancelarO.setDisable(true);

//        find.setDisable(true);
//        PacienteO.setDisable(true);
//        MedicoO.setDisable(true);

buscarR.setDisable(true);
NoEx.setDisable(true);
NombrePaciente.setDisable(true);
resultado.setDisable(true);
        SexoO.setDisable(true);
        EdadO.setDisable(true);
//        FechaO.setDisable(true);
        color.setDisable(true);
        aspecto.setDisable(true);
        densidad.setDisable(true);
        ph.setDisable(true);
        proteina.setDisable(true);
        glucosa.setDisable(true);
        sangreoculta.setDisable(true);
        cuerposcet.setDisable(true);
        Uribilinogeno.setDisable(true);
        bilirrubina.setDisable(true);
        nitritos.setDisable(true);
        hemoglobinas.setDisable(true);
        esterasa.setDisable(true);
        
        cilindros.setDisable(true);
        granulosos.setDisable(true);
        leucocitarios.setDisable(true);
        hematicos.setDisable(true);
        hialinos.setDisable(true);
        hematies.setDisable(true);
        leucocitos.setDisable(true);
        celulasepi.setDisable(true);
        cristales.setDisable(true);
        parasito.setDisable(true);
        otros.setDisable(true);
        observaciones.setDisable(true);
        
        valor = 0;
//        listPac.add(new Paciente)
    }

    public void Habilitar() {

//        PacienteO.setDisable(false);
//        MedicoO.setDisable(false);
        
        NombrePaciente.setDisable(true);
        SexoO.setDisable(true);
        EdadO.setDisable(true);
//        FechaO.setDisable(true);
        resultado.setDisable(false);
        color.setDisable(false);
        aspecto.setDisable(false);
        densidad.setDisable(false);
        ph.setDisable(false);
        proteina.setDisable(false);
        glucosa.setDisable(false);
        sangreoculta.setDisable(false);
        cuerposcet.setDisable(false);
        Uribilinogeno.setDisable(false);
        bilirrubina.setDisable(false);
        nitritos.setDisable(false);
        hemoglobinas.setDisable(false);
        esterasa.setDisable(false);
        
         cilindros.setDisable(false);
        granulosos.setDisable(false);
        leucocitarios.setDisable(false);
        hematicos.setDisable(false);
        hialinos.setDisable(false);
        hematies.setDisable(false);
        leucocitos.setDisable(false);
        celulasepi.setDisable(false);
        cristales.setDisable(false);
        parasito.setDisable(false);
        otros.setDisable(false);
        observaciones.setDisable(false);

        valor = 0;
    }
//    

    public void Deshabilitar() {
        TablaOrina.setDisable(true);
        tableexa.setDisable(true);
//        PacienteO.setDisable(true);
//        MedicoO.setDisable(true);
        SexoO.setDisable(true);
        EdadO.setDisable(true);
//        FechaO.setDisable(true);
        color.setDisable(true);
        aspecto.setDisable(true);
        densidad.setDisable(true);
        ph.setDisable(true);
        proteina.setDisable(true);
        glucosa.setDisable(true);
        sangreoculta.setDisable(true);
        cuerposcet.setDisable(true);
        Uribilinogeno.setDisable(true);
        bilirrubina.setDisable(true);
        nitritos.setDisable(true);
        hemoglobinas.setDisable(true);
        esterasa.setDisable(true);
        valor = 0;
    }

    public void Limpiar() {
//PacienteO.setValue("");
//MedicoO.setValue("");
NoEx.clear();
NombrePaciente.clear();
resultado.clear();
buscarR.clear();
        SexoO.clear();
        EdadO.clear();
//        FechaO.setValue(null);
        color.clear();
        aspecto.clear();
        densidad.clear();
        ph.clear();
        proteina.clear();
        glucosa.clear();
        sangreoculta.clear();
        cuerposcet.clear();
        Uribilinogeno.clear();
        bilirrubina.clear();
        nitritos.clear();
        hemoglobinas.clear();
        esterasa.clear();
        
        cilindros.clear();
        granulosos.clear();
        leucocitarios.clear();
        hematicos.clear();
        hialinos.clear();
        hematies.clear();
        leucocitos.clear();
        celulasepi.clear();
        cristales.clear();
        parasito.clear();
        otros.clear();
        observaciones.clear();
        
        valor = 0;

    }

   public void Stylepred(){
        String stylep="-fx-border-color: black;";
//        noExph.setStyle(stylep);
        resultado.setStyle(stylep);
        color.setStyle(stylep);
         aspecto.setStyle(stylep);
         densidad.setStyle(stylep);
         ph.setStyle(stylep);
         proteina.setStyle(stylep);
         glucosa.setStyle(stylep);
         sangreoculta.setStyle(stylep);
         cuerposcet.setStyle(stylep);
         Uribilinogeno.setStyle(stylep);
         bilirrubina.setStyle(stylep);
         nitritos.setStyle(stylep);
         hemoglobinas.setStyle(stylep);
         esterasa.setStyle(stylep);
         
         cilindros.setStyle(stylep);
         granulosos.setStyle(stylep);
         leucocitarios.setStyle(stylep);
         hematicos.setStyle(stylep);
         hialinos.setStyle(stylep);
         hematies.setStyle(stylep);
         leucocitos.setStyle(stylep);
         celulasepi.setStyle(stylep);
         cristales.setStyle(stylep);
         parasito.setStyle(stylep);
         otros.setStyle(stylep);
         observaciones.setStyle(stylep);
        
        
    }
      
       public static boolean isNumeric(String str) {
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("") == false);
    }
        public void TooltipValidation(){
         Stylepred();
           
    String style="-fx-background-color: orange,linear-gradient(to bottom, derive(orange,60%) 5%,derive(orange,90%) 40%);";
    
    String styletxt="-fx-border-color: red;";
   
    
    if(resultado.getText().isEmpty() || resultado.getText().equals("") || resultado.getText().length()<3||isNumeric(resultado.getText())==true){            
        resultado.setStyle(styletxt);
        
           valttip = 1;
    }
           
    if(color.getText().isEmpty() || color.getText().equals("") || color.getText().length()<3||isNumeric(color.getText())==true){
        color.setStyle(styletxt);
        valttip=1;
        
    }
    if(aspecto.getText().isEmpty() || aspecto.getText().equals("") || aspecto.getText().length()<2||isNumeric(aspecto.getText())==true){
        aspecto.setStyle(styletxt);
        valttip=1;
        
    }
    if(densidad.getText().isEmpty() || densidad.getText().equals("") || densidad.getText().length()<1||isNumeric(densidad.getText())==false){
        densidad.setStyle(styletxt);
        valttip=1;   
    } 
    if(ph.getText().isEmpty() || ph.getText().equals("") || ph.getText().length()<1||isNumeric(ph.getText())==false){
        ph.setStyle(styletxt);
        valttip=1;
    }
    if(proteina.getText().isEmpty() || proteina.getText().equals("") || proteina.getText().length()<1||isNumeric(proteina.getText())==false){
        proteina.setStyle(styletxt);
        valttip=1;   
    }
    if(glucosa.getText().isEmpty() || glucosa.getText().equals("") || glucosa.getText().length()<1||isNumeric(glucosa.getText())==false){
        glucosa.setStyle(styletxt);
        valttip=1;   
    }
    if(sangreoculta.getText().isEmpty() || sangreoculta.getText().equals("") || sangreoculta.getText().length()<1||isNumeric(sangreoculta.getText())==false){
        sangreoculta.setStyle(styletxt);
        valttip=1;    
    }
    if(cuerposcet.getText().isEmpty() || cuerposcet.getText().equals("") || cuerposcet.getText().length()<1||isNumeric(cuerposcet.getText())==false){
        cuerposcet.setStyle(styletxt);
        valttip=1;   
    } 
    if(Uribilinogeno.getText().isEmpty() || Uribilinogeno.getText().equals("") || Uribilinogeno.getText().length()<1||isNumeric(Uribilinogeno.getText())==false){
        Uribilinogeno.setStyle(styletxt);
        valttip=1;   
    } 
    if(bilirrubina.getText().isEmpty() || bilirrubina.getText().equals("") || bilirrubina.getText().length()<1||isNumeric(bilirrubina.getText())==false){
        bilirrubina.setStyle(styletxt);
        valttip=1;   
    } 
    if(nitritos.getText().isEmpty() || nitritos.getText().equals("") || nitritos.getText().length()<1||isNumeric(nitritos.getText())==false){
        nitritos.setStyle(styletxt);
        valttip=1;   
    }
    if(hemoglobinas.getText().isEmpty() || hemoglobinas.getText().equals("") || hemoglobinas.getText().length()<1||isNumeric(hemoglobinas.getText())==false){
        hemoglobinas.setStyle(styletxt);
        valttip=1;   
    }
    if(esterasa.getText().isEmpty() || esterasa.getText().equals("") || esterasa.getText().length()<1||isNumeric(esterasa.getText())==false){
        esterasa.setStyle(styletxt);
        valttip=1;   
    }
    if(cilindros.getText().isEmpty() || cilindros.getText().equals("") || cilindros.getText().length()<1||isNumeric(cilindros.getText())==false){
        cilindros.setStyle(styletxt);
        valttip=1;   
    }
    if(granulosos.getText().isEmpty() || granulosos.getText().equals("") || granulosos.getText().length()<1||isNumeric(granulosos.getText())==false){
        granulosos.setStyle(styletxt);
        valttip=1;   
    }
    if(leucocitarios.getText().isEmpty() || leucocitarios.getText().equals("") || leucocitarios.getText().length()<1||isNumeric(leucocitarios.getText())==false){
        leucocitarios.setStyle(styletxt);
        valttip=1;   
    }
    if(hematicos.getText().isEmpty() || hematicos.getText().equals("") || hematicos.getText().length()<1||isNumeric(hematicos.getText())==false){
        hematicos.setStyle(styletxt);
        valttip=1;   
    }
    if(hialinos.getText().isEmpty() || hialinos.getText().equals("") || hialinos.getText().length()<1||isNumeric(hialinos.getText())==false){
        hialinos.setStyle(styletxt);
        valttip=1;   
    }
    if(hematies.getText().isEmpty() || hematies.getText().equals("") || hematies.getText().length()<1||isNumeric(hematies.getText())==false){
        hematies.setStyle(styletxt);
        valttip=1;   
    }
    if(leucocitos.getText().isEmpty() || leucocitos.getText().equals("") || leucocitos.getText().length()<1||isNumeric(leucocitos.getText())==false){
        leucocitos.setStyle(styletxt);
        valttip=1;   
    }
    if(celulasepi.getText().isEmpty() || celulasepi.getText().equals("") || celulasepi.getText().length()<1||isNumeric(celulasepi.getText())==false){
        celulasepi.setStyle(styletxt);
        valttip=1;   
    }
    if(cristales.getText().isEmpty() || cristales.getText().equals("") || cristales.getText().length()<1||isNumeric(cristales.getText())==false){
        cristales.setStyle(styletxt);
        valttip=1;   
    }
    if(parasito.getText().isEmpty() || parasito.getText().equals("") || parasito.getText().length()<1||isNumeric(parasito.getText())==false){
        parasito.setStyle(styletxt);
        valttip=1;   
    }if(otros.getText().isEmpty() || otros.getText().equals("") || otros.getText().length()<1||isNumeric(otros.getText())==false){
        otros.setStyle(styletxt);
        valttip=1;   
    }
    if(observaciones.getText().isEmpty() || observaciones.getText().equals("") || observaciones.getText().length()<3||isNumeric(observaciones.getText())==true){
        observaciones.setStyle(styletxt);
        valttip=1;   
    }
     }
     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idmedicoactual=config2.MedicoId;
        System.out.println("El dia de hoy se encarga"+idmedicoactual);        
                columcat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        columtipo.setCellValueFactory(new PropertyValueFactory<>("examenTipo"));
        columestado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        columresultado.setCellValueFactory(new PropertyValueFactory<> ("resultado"));
        columobservaciones.setCellValueFactory(new PropertyValueFactory<> ("observaciones"));
        Inicial();
         ExamenOrina or= new ExamenOrina();
        
         
         TablaOrina.setOnMousePressed(new EventHandler<MouseEvent>(){
        public void handle(MouseEvent me) {
    System.out.println(TablaOrina.getSelectionModel().getSelectedIndex());
//        EditarO.setDisable(false);
//        EliminarO.setDisable(false);
        cancelarO.setDisable(false);
        NuevoExa.setDisable(true);
        BuscarExa.setDisable(true);
            if(me.getClickCount()==1){
            if(TablaOrina.getSelectionModel().getSelectedIndex()==-1){
//              EditarO.setDisable(true);
//              EliminarO.setDisable(true);
            }
                    
            System.out.println(me.getButton());    
            
            if(me.getButton().toString()=="SECONDARY"){
                    System.out.println("clic derecho");
                
                    TablaOrina.setContextMenu(conmenu);
            }
            
            }

        }
         });
                   
            

              
              
           
    }

}
