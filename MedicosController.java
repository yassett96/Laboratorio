/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;



//import Hibernate.NewHibernateUtil;
import Hibernate.NewHibernateUtil;
import POJO.*;
import POJO.Medicos;
import POJO.Roles;
import com.github.sarxos.webcam.Webcam;
//import Utilidades.WebCamAppLauncher;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.in;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
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
public class MedicosController implements Initializable {
    SessionFactory factor;
    Session s = NewHibernateUtil.getSessionFactory().openSession();
    Image image;
    byte[] Imagebyte;
    @FXML public ImageView ivImagen, webimg;
    @FXML public Button btnBuscar;
    @FXML public Button remove;
    @FXML public ComboBox combocargo,cemb,checes,cori,chema,cqc,cinm,cpara,cbac,cbds;
    @FXML public TextField Nombre, cel, apellidos, dir,NoCed,Edad;
    @FXML public Button mod, elim,save,cancel,camera,add,listar;
    @FXML Label labelfoto;
    @FXML public TableView<Medicos> tableMed;
    @FXML public TableColumn<Medicos, String> columnoc;
    @FXML public TableColumn<Medicos, String> columnom;
    @FXML public TableColumn<Medicos, String> columape;
    @FXML public TableColumn<Medicos, String> columcargo;
    @FXML public TableColumn<Medicos, String> columdir;
    @FXML ContextMenu conmenu;
    @FXML MenuItem meneli,menmos,menmodi;
    public String cameraListPromptText = "Seleccionar Camara";
    public Webcam webCam = null;
    public boolean stopCamera = false;
    public BufferedImage grabbedImage;
    String cargomedico;
    ObjectProperty<Image> imageProperty = new SimpleObjectProperty();
    @FXML Button btnCamreaStop;
    @FXML Button btnCamreaStart;
    @FXML Button btnCameraDispose;
    @FXML ComboBox<WebCamInfo> cameraOptions;
//    @FXML    BorderPane webCamPane;
    ObservableList<WebCamInfo> options = FXCollections.observableArrayList();
    int valor = 0;
    int cargar = 0;
    int validarcargo=0;
//    int event=0;
    ObservableList<String> cargo =FXCollections.observableArrayList("Administrador","Bioanalista","Recepcionista");
    ObservableList<String> lista   =FXCollections.observableArrayList("Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo");
    public int valttip;

	public void createTopPanel() {

		int webCamCounter                  = 0;
//		Label lbInfoLabel                  = new Label("Select Your WebCam Camera");
		
//		topPane.getChildren().add(lbInfoLabel);
		for(Webcam webcam:Webcam.getWebcams())
		{
			WebCamInfo webCamInfo = new WebCamInfo();
			webCamInfo.setWebCamIndex(webCamCounter);
			webCamInfo.setWebCamName(webcam.getName());
			options.add(webCamInfo);
			webCamCounter ++;
		}
		
		cameraOptions.setItems(options);
                btnCameraDispose.setDisable(true);
		cameraOptions.setPromptText(cameraListPromptText);
		cameraOptions.getSelectionModel().selectedItemProperty().addListener(new  ChangeListener<WebCamInfo>() {

	        @Override
	        public void changed(ObservableValue<? extends WebCamInfo> arg0, WebCamInfo arg1, WebCamInfo arg2) {
	            if (arg2 != null) {
	               
	            	System.out.println("WebCam Index: " + arg2.getWebCamIndex()+": WebCam Name:"+ arg2.getWebCamName());
	            	initializeWebCam(arg2.getWebCamIndex());
	            }
	        }
	    });
//		topPane.getChildren().add(cameraOptions);
	}

	public void initializeWebCam(final int webCamIndex) {
		
		Task<Void> webCamTask = new Task<Void>() {
			
			@Override
			protected Void call() throws Exception {
				
				if(webCam != null)
				{
					disposeWebCamCamera();
					webCam = Webcam.getWebcams().get(webCamIndex);
					webCam.open();
				}else
				{
					webCam = Webcam.getWebcams().get(webCamIndex);
					webCam.open();
				}
				
				startWebCamStream();
				return null;
			}
		};
		
		Thread webCamThread = new Thread(webCamTask);
		webCamThread.setDaemon(true);
		webCamThread.start();
//		bottomCameraControlPane.setDisable(false);
		btnCamreaStart.setDisable(true);
	}

	public void startWebCamStream() {
		
		stopCamera  = false;
		Task<Void> task = new Task<Void>() {

		
			@Override
			protected Void call() throws Exception {

				while (!stopCamera) {
                                    btnCamreaStop.setDisable(false);
					try {
						if ((grabbedImage = webCam.getImage()) != null) {
							
//							System.out.println("Captured Image height*width:"+grabbedImage.getWidth()+"*"+grabbedImage.getHeight());
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									final Image mainiamge = SwingFXUtils
											.toFXImage(grabbedImage, null);
									imageProperty.set(mainiamge);
                                                                        
                                    try {
                                        ImageIO.write(webCam.getImage(), "PNG", new File("src/Imagenes/Medico.png"));
                                   }catch (IOException ex){
                                        Logger.getLogger(MedicosController.class.getName()).log(Level.SEVERE, null, ex);
                                   }
//                                    
                                                                        
								}
							});

							grabbedImage.flush();

						}
					} catch (Exception e) {
					} finally {
                                            
					}

				}
                                
				return null;

			}

		};
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
                
                webimg.setVisible(true);
                ivImagen.setVisible(false);
		webimg.imageProperty().bind(imageProperty);
                
		
	}
	
        public void dispose() throws IOException{
            disposeWebCamCamera();
            Reactivar();
            options.clear();
            
        }
	public void disposeWebCamCamera() throws IOException {
		
		stopCamera = true;
		webCam.close();

        
	}
        
        public void Reactivar() throws IOException{
                            
//		Webcam.shutdown();
//                System.exit(0);
//                ivImagen.imageProperty().setValue(image);
               
//                s.close();
//                startWebCamStream();

		btnCamreaStart.setDisable(true);
		btnCamreaStop.setDisable(true);
                File imgFile = new File("src/Imagenes/Medico.png");
                BufferedImage originalImage = ImageIO.read(imgFile);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write((RenderedImage)originalImage, "png", baos);
                baos.flush();
                this.Imagebyte = baos.toByteArray();
                baos.close();
                labelfoto.setVisible(true);
                save.setDisable(false);
                cancel.setDisable(false);
                remove.setDisable(true);
                btnBuscar.setDisable(true);
                camera.setDisable(false);               
//                event=1;
//                Image inicial = new Image("/Imagenes/Medico.png");
//                this.ivImagen.setImage(inicial);                
//                webCamPane.setVisible(false);
//                webimg.setVisible(false);
//                ivImagen.setVisible(true);

////                ivImagen.setVisible(false);
//                imageProperty.set(inicial);
                
                this.btnBuscar.setVisible(true);
        this.camera.setVisible(true);
        this.remove.setVisible(true);
        this.btnCamreaStart.setVisible(false);
//        this.btnCamreaStart.setLayoutY(0.0);
        this.btnCamreaStop.setVisible(false);
//        this.btnCamreaStop.setLayoutY(0.0);
        this.btnCameraDispose.setVisible(false);
//        this.btnCameraDispose.setLayoutY(0.0);
        this.cameraOptions.setVisible(false);
        this.cameraOptions.setDisable(true);
        }

	public void startWebCamCamera() {
		
		stopCamera = false;
		startWebCamStream();
		btnCamreaStop.setDisable(false);
		btnCamreaStart.setDisable(true);
	}

	public void stopWebCamCamera() {
		
		stopCamera = true;
		btnCamreaStart.setDisable(false);
		btnCamreaStop.setDisable(true);
                btnCameraDispose.setDisable(false);
	}

	class WebCamInfo
	{
            
		public String webCamName ;
		public int webCamIndex ;
		
		public String getWebCamName() {
			return webCamName;
		}
		public void setWebCamName(String webCamName) {
			this.webCamName = webCamName;
		}
		public int getWebCamIndex() {
			return webCamIndex;
		}
		public void setWebCamIndex(int webCamIndex) {
			this.webCamIndex = webCamIndex;
		}
		
		@Override
		public String toString() {
		        return webCamName;
	     }
		
	}
    
        @FXML public void Image() throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")        );
        File imgFile = fileChooser.showOpenDialog(null);
        
        BufferedImage originalImage=ImageIO.read(imgFile);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();       
        ImageIO.write(originalImage, "png", baos);
        baos.flush();
        Imagebyte=baos.toByteArray();
        baos.close();        
        
            System.out.println("1");
        if (imgFile != null) {
            image = new Image("file:" + imgFile.getAbsolutePath());
            ivImagen.setImage(image);
            System.out.println("2");
        }       
        cargar=1;
        camera.setDisable(true);
    }
        
 
            
    @FXML public void add() {
        this.Habilitar();
//        HabilitarCombos();
        this.add.setDisable(true);
        this.listar.setDisable(true);
        this.mod.setDisable(true);
        this.camera.setDisable(false);
        this.elim.setDisable(true);
        this.cancel.setDisable(false);
        this.save.setDisable(false);
        this.valor = 0;
        this.tableMed.getItems().clear();
        DeshabilitarCombos();
        this.tableMed.setDisable(true);
        validarcargo=1;
        System.out.println(String.valueOf(combocargo.getValue()));
        if(  String.valueOf(combocargo.getValue())=="Bioanalista"){
         
            HabilitarCombos();
        }else{
            ListaADM();
        }
    }

    public void addcriteria() {
        try {
            Transaction t = this.s.beginTransaction();           
            
            Medicos p = new Medicos();
            p.setNombre(this.Nombre.getText());
            p.setApellido(this.apellidos.getText());
            p.setCargo(String.valueOf(this.combocargo.getValue()));
            p.setNoCedula(this.NoCed.getText());
            p.setTelefono(Integer.valueOf(Integer.parseInt(this.cel.getText())));
            p.setEdad(Integer.valueOf(Integer.parseInt(this.Edad.getText())));
            p.setDireccion(this.dir.getText());
            p.setFoto(this.Imagebyte);      
            p.setRegistro("Activo");
            s.save(p); 

            Roles r = new Roles();
            r.setEmbarazo(String.valueOf(cemb.getValue()));
            r.setHeces(String.valueOf(checes.getValue()));
            r.setOrina(String.valueOf(cori.getValue()));
            r.setHematologia(String.valueOf(chema.getValue()));
            r.setQuimica(String.valueOf(cqc.getValue()));
            r.setInmunologia(String.valueOf(cinm.getValue()));
            r.setParasitologia(String.valueOf(cpara.getValue()));
            r.setBacteriologia(String.valueOf(cbac.getValue()));
            r.setBancoSangre(String.valueOf(cbds.getValue()));
            r.setRegistro("Activo");
            r.setMedicos(p);
            s.save(r);

            t.commit();
            new Alert(Alert.AlertType.INFORMATION,"Medico Agregado").show();
            
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallo Conexion: " + e.toString());
        }
        
        s.close();
        s=NewHibernateUtil.getSessionFactory().openSession();        
        this.valor = 0;
        this.tableMed.getItems().clear();
    }
    

    
    @FXML
    public void web() throws IOException {
        this.btnBuscar.setVisible(false);
        this.camera.setVisible(false);
        this.remove.setVisible(false);
        this.btnCamreaStart.setVisible(true);
        this.btnCamreaStart.setLayoutY(-30.0);
        this.btnCamreaStop.setVisible(true);
        this.btnCamreaStop.setLayoutY(-30.0);
        this.btnCameraDispose.setVisible(true);
        this.btnCameraDispose.setLayoutY(-30.0);
        this.cameraOptions.setVisible(true);
        this.cameraOptions.setDisable(false);
        this.createTopPanel();
        btnCamreaStop.setDisable(true);
        save.setDisable(true);
        cancel.setDisable(true);
        labelfoto.setVisible(false);
        
    }

    public void suprimir() throws IOException {
        int id = ((Medicos)this.tableMed.getSelectionModel().getSelectedItem()).getIdMedico();
        this.s.getTransaction().begin();
        Query query1 = this.s.createQuery("update Medicos set registro='Inactivo' where idMedico= :Id");
        query1.setParameter("Id", (Object)id);
        int result1 = query1.executeUpdate();
        Query query2 = this.s.createQuery("update Roles set registro='Inactivo' where idMedico= :Id");
        query2.setParameter("Id", (Object)id);
        int result2 = query2.executeUpdate();        
        this.s.getTransaction().commit();
        new Alert(Alert.AlertType.INFORMATION,"Medico Eliminado").show();
        this.tableMed.getItems().clear();
        this.cancel();
    }

    public void editcriteria() throws IOException {
        editmedico();
//        editroles();

    }
    
    public void editmedico() throws IOException{
        int id = ((Medicos)this.tableMed.getSelectionModel().getSelectedItem()).getIdMedico();
        this.s.getTransaction().begin();
        Query query1 = this.s.createQuery("update Medicos set nombre = :nombres ,apellido = :apellidos , cargo= :cargo ,noCedula= :noc , telefono= :tel, edad= :edad ,direccion= :dir , foto= :foto where idMedico = :Id");
        query1.setParameter("nombres", (Object)this.Nombre.getText());
        query1.setParameter("apellidos", (Object)this.apellidos.getText());
        query1.setParameter("cargo", (Object)String.valueOf(this.combocargo.getValue()));
        query1.setParameter("noc", (Object)this.NoCed.getText());
        query1.setParameter("tel", (Object)Integer.parseInt(this.cel.getText()));
        query1.setParameter("edad", (Object)Integer.parseInt(this.Edad.getText()));
        query1.setParameter("dir", (Object)this.dir.getText());
        System.out.println("Guardo anterior");
        query1.setParameter("foto", (Object)this.Imagebyte);
        if (this.cargar == 1) {
            query1.setParameter("foto", (Object)this.Imagebyte);
        } else {
            File imgFile = new File("src/Imagenes/Medico.png");
            BufferedImage originalImage = ImageIO.read(imgFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write((RenderedImage)originalImage, "png", baos);
            baos.flush();
            this.Imagebyte = baos.toByteArray();
            baos.close();
            query1.setParameter("foto", (Object)this.Imagebyte);
        }
        query1.setParameter("Id", (Object)id);
        int result1 = query1.executeUpdate();
        
        Query query2 = this.s.createQuery("update Roles set embarazo = :emb ,heces = :heces , orina= :orina ,hematologia= :hema , quimica= :qc, inmunologia= :inm ,parasitologia= :para , bacteriologia= :bac , bancoSangre= :bds  where idMedico = :Id");
        query2.setParameter("emb", (Object)String.valueOf(this.cemb.getValue()));
        query2.setParameter("heces", String.valueOf(checes.getValue()));
        query2.setParameter("orina", String.valueOf(cori.getValue()));
        query2.setParameter("hema", String.valueOf(chema.getValue()));
        query2.setParameter("qc", String.valueOf(cqc.getValue()));
        query2.setParameter("inm", String.valueOf(cinm.getValue()));
        query2.setParameter("para", String.valueOf(cpara.getValue()));
        query2.setParameter("bac", String.valueOf(cbac.getValue()));
        query2.setParameter("bds", String.valueOf(cbds.getValue()));
            System.out.println("Parameter");
        query2.setParameter("Id", (Object)id);
        
        int result = query2.executeUpdate();
        System.out.println("actualizado");                
        this.s.getTransaction().commit();
        new Alert(Alert.AlertType.INFORMATION,"Medico Modificado").show();
        this.tableMed.getItems().clear();
        System.out.println("Limpio");
        s.close();
        s = NewHibernateUtil.getSessionFactory().openSession();
        System.out.println("Reinicio sesion");
        this.cargar = 0;
        System.out.println("cargar=0");
       
    }
    
   @FXML
    public void mod() {
        this.Habilitar();
        this.add.setDisable(true);
        this.camera.setDisable(false);
        this.listar.setDisable(true);
        this.mod.setDisable(true);
        this.elim.setDisable(true);
        this.cancel.setDisable(false);
        this.save.setDisable(false);
        this.tableMed.setDisable(true);
        validarcargo=1;
        this.valor = 1;
        System.out.println(String.valueOf(combocargo.getValue()));
        if(  String.valueOf(combocargo.getValue())=="Bioanalista"){
         
            HabilitarCombos();
            
        } else{
            DeshabilitarCombos();
        }
    }

    @FXML
    public void elim() throws IOException {
        this.Evento();
        this.suprimir();
        this.valor = 0;
        this.tableMed.getItems().clear();
    }

    @FXML
    public void listar() throws IOException {
        Deshabilitar();
        System.out.println("deshabilitado");
        tableMed.setDisable(false);
        listar.setDisable(false);
        System.out.println("tabla");
        criterialist();
        System.out.println("listo");
        
        System.out.println("ya");
        valor = 0;
    }
        
 @FXML       public void Evento() throws IOException{

        mod.setDisable(false);
        elim.setDisable(false);
        cancel.setDisable(false);
        add.setDisable(true);
//        buscar.setDisable(true);        
        int id=tableMed.getSelectionModel().getSelectedItem().getIdMedico();
        Nombre.setText((tableMed.getSelectionModel().getSelectedItem().getNombre()));
        apellidos.setText((tableMed.getSelectionModel().getSelectedItem().getApellido()));
        combocargo.setValue(tableMed.getSelectionModel().getSelectedItem().getCargo());
        NoCed.setText((tableMed.getSelectionModel().getSelectedItem().getNoCedula()));
        cel.setText(String.valueOf(tableMed.getSelectionModel().getSelectedItem().getTelefono()));
        Edad.setText(String.valueOf(tableMed.getSelectionModel().getSelectedItem().getEdad()));
        dir.setText((tableMed.getSelectionModel().getSelectedItem().getDireccion()));

        Criteria c = s.createCriteria(Roles.class).add(Restrictions.like("registro","Activo"))
        .add(Restrictions.eq("idMedico", id));
                    if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancel();
                Inicial();
                
            }else{
        for(Object pac: c.list() ) {
            Roles r= (Roles) pac;
            System.out.println(" - " +r.getBacteriologia()+" - "+r.getBancoSangre()); 
            cemb.setValue(r.getEmbarazo());
            checes.setValue(r.getHeces());
            cori.setValue(r.getOrina());
            chema.setValue(r.getHematologia());
            cqc.setValue(r.getQuimica());
            cinm.setValue(r.getInmunologia());
            cpara.setValue(r.getParasitologia());
            cbac.setValue(r.getBacteriologia());
            cbds.setValue(r.getBancoSangre());            
        }        
           try {
               leerimagen();
           } catch (IOException ex) {
               Logger.getLogger(MedicosController.class.getName()).log(Level.SEVERE, null, ex);
           }
       
                    }
        }
        
        @FXML public void MetodoClick() throws IOException{
            Evento();
            mod();
        }
     
        public void leerimagen() throws IOException{

        int id=0;
        id=tableMed.getSelectionModel().getSelectedItem().getIdMedico();
            Criteria c = s.createCriteria(Medicos.class).add(Restrictions.like("registro","Activo"))
        .add(Restrictions.eq("idMedico", id));
             if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancel();
                Inicial();
                
            }else{
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            Medicos p= (Medicos) pac;
            System.out.println(" - " +p.getIdMedico()+" - "+p.getFoto());           
           
            InputStream in = new ByteArrayInputStream(p.getFoto());
            BufferedImage bImageFromConvert = ImageIO.read(in);
            File imagensalida= new File("src/Imagenes/Medico.png");
            ImageIO.write(bImageFromConvert, "png", imagensalida);  
            image = new Image("file:" + imagensalida.getAbsolutePath());
            ivImagen.setImage(image);
        }
        
             }
        }
        
    public void criterialist() throws IOException{
       
        Criteria c = s.createCriteria(Medicos.class).add(Restrictions.like("registro","Activo"))
        .addOrder( Order.asc("nombre") ); 
             if(c.list().isEmpty()||c.list().size()==0){
                new Alert(Alert.AlertType.ERROR,"No se encontraron Registros").show();
                cancel();
                Inicial();
                
            }else{
        System.out.println("Personas Encontradas:"+c.list().size());
        for(Object pac: c.list() )
        {
            Medicos p= (Medicos) pac;
            
            System.out.println(p.getNombre());

            
            
        }
       tableMed.getItems().clear();
       tableMed.getItems().addAll(c.list()); 
       valor=0;
             }
    }
    
    
    public void Stylepred(){
        String stylep="-fx-border-color: black;";
        Nombre.setStyle(stylep);
        apellidos.setStyle(stylep);
        cel.setStyle(stylep);
        dir.setStyle(stylep);
        NoCed.setStyle(stylep);
        Edad.setStyle(stylep);
    }
     public void TooltipValidation(){
         Stylepred();
 
    String style="-fx-background-color: orange,linear-gradient(to bottom, derive(orange,60%) 5%,derive(orange,90%) 40%);";
    
    String styletxt="-fx-border-color: red;";
   
    
    if(Nombre.getText().isEmpty() || Nombre.getText().equals("") || Nombre.getText().length()<3){            
        Nombre.setStyle(styletxt);
        
        valttip=1;
    }
    if(apellidos.getText().isEmpty() || apellidos.getText().equals("") || apellidos.getText().length()<3){
        apellidos.setStyle(styletxt);
        valttip=1;
    }
    if(cel.getText().isEmpty() || cel.getText().equals("") || cel.getText().length()!=8){
        cel.setStyle(styletxt);
        valttip=1;   
    }
    if(dir.getText().isEmpty() || dir.getText().equals("") || dir.getText().length()<8){
        dir.setStyle(styletxt);
        valttip=1;   
    } 

    if(NoCed.getText().isEmpty() || NoCed.getText().equals("") || NoCed.getText().length()<10){
        NoCed.setStyle(styletxt);
        valttip=1;   
    }
    if(Edad.getText().isEmpty() || Edad.getText().equals("") || Edad.getText().length()>2){
        Edad.setStyle(styletxt);
        valttip=1;    
    }

    
     }
    
    
    
        @FXML public void save() throws IOException{
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
                tableMed.getItems().clear();                
          
  } else{
      valttip=0;
  }        

    

        }   
        
    @FXML
    public void cancel() throws IOException {
        tableMed.getItems().clear();
        Limpiar();
        Inicial();
        valor = 0;
        validarcargo=0;
    }

    public void Inicial() throws IOException {
//        ivImagen.setFitHeight(201.0);
//        ivImagen.setFitWidth(223.0);
        DeshabilitarCombos();
        validarcargo=0;
        btnCamreaStart.setVisible(false);
        btnCamreaStart.setDisable(true);
        btnCamreaStop.setVisible(false);
        btnCameraDispose.setVisible(false);
        cameraOptions.setVisible(false);
        tableMed.setDisable(false);
        add.setDisable(false);
        btnBuscar.setDisable(true);
        remove.setDisable(true);
        listar.setDisable(false);
        mod.setDisable(true);
        this.elim.setDisable(true);
        this.save.setDisable(true);
        this.cancel.setDisable(true);
        this.camera.setDisable(true);
        this.Nombre.setDisable(true);
        this.cel.setDisable(true);
        this.apellidos.setDisable(true);
        this.dir.setDisable(true);
        this.NoCed.setDisable(true);
        this.Edad.setDisable(true);
        this.combocargo.setDisable(true);
        this.combocargo.setValue((Object)"Administrador");

        ListaVacia();
//        ListaADM();
        this.combocargo.setItems(this.cargo);
        this.valor = 0;
//        this.ivImagen.setLayoutY((double)this.valor);       
        webimg.setVisible(false);
        ivImagen.setVisible(true);
        ImagenPredeterminada();
    }
    
    public void ListaVacia(){
        this.cbac.setValue((Object)"");
        this.cbds.setValue((Object)"");
        this.cemb.setValue((Object)"");
        this.chema.setValue((Object)"");
        this.checes.setValue((Object)"");
        this.cori.setValue((Object)"");
        this.cpara.setValue((Object)"");
        this.cqc.setValue((Object)"");
        this.cinm.setValue((Object)"");        
    }
    
    public void Lista(){
        this.cbac.setItems(lista);
        this.cbds.setItems(lista);
        this.cemb.setItems(lista);
        this.chema.setItems(lista);
        this.checes.setItems(lista);
        this.cori.setItems(lista);
        this.cqc.setItems(lista);
        this.cpara.setItems(lista);
        this.cinm.setItems(lista); 
        this.cbac.setValue((Object)"Lunes");
        this.cbds.setValue((Object)"Lunes");
        this.cemb.setValue((Object)"Lunes");
        this.chema.setValue((Object)"Lunes");
        this.checes.setValue((Object)"Lunes");
        this.cori.setValue((Object)"Lunes");
        this.cpara.setValue((Object)"Lunes");
        this.cqc.setValue((Object)"Lunes");
        this.cinm.setValue((Object)"Lunes");
    }
    
        public void ListaREC(){
        this.cbac.setValue((Object)"Recepcionista");
        this.cbds.setValue((Object)"Recepcionista");
        this.cemb.setValue((Object)"Recepcionista");
        this.chema.setValue((Object)"Recepcionista");
        this.checes.setValue((Object)"Recepcionista");
        this.cori.setValue((Object)"Recepcionista");
        this.cpara.setValue((Object)"Recepcionista");
        this.cqc.setValue((Object)"Recepcionista");
        this.cinm.setValue((Object)"Recepcionista");
    }
        
        public void ListaADM(){
        this.cbac.setValue((Object)"Administrador");
        this.cbds.setValue((Object)"Administrador");
        this.cemb.setValue((Object)"Administrador");
        this.chema.setValue((Object)"Administrador");
        this.checes.setValue((Object)"Administrador");
        this.cori.setValue((Object)"Administrador");
        this.cpara.setValue((Object)"Administrador");
        this.cqc.setValue((Object)"Administrador");
        this.cinm.setValue((Object)"Administrador");
    }

    public void Habilitar() {
        this.Nombre.setDisable(false);
        this.cel.setDisable(false);
        this.apellidos.setDisable(false);
        this.dir.setDisable(false);
        this.NoCed.setDisable(false);
        this.Edad.setDisable(false);
        this.combocargo.setDisable(false);
        this.btnBuscar.setDisable(false);
        this.remove.setDisable(false);
        this.valor = 0;
    }
    
    public void DeshabilitarCombos(){
        cbac.setDisable(true);
        cbds.setDisable(true);
        cemb.setDisable(true);
        checes.setDisable(true);
        chema.setDisable(true);
        cinm.setDisable(true);
        cori.setDisable(true);
        cpara.setDisable(true);
        cqc.setDisable(true);
    }
    
    public void HabilitarCombos(){
         cbac.setDisable(false);
        cbds.setDisable(false);
        cemb.setDisable(false);
        checes.setDisable(false);
        chema.setDisable(false);
        cinm.setDisable(false);
        cori.setDisable(false);
        cpara.setDisable(false);
        cqc.setDisable(false);       
    }

    public void Deshabilitar() {
        this.tableMed.setDisable(true);
        this.Nombre.setDisable(true);
        this.cel.setDisable(true);
        this.apellidos.setDisable(true);
        this.dir.setDisable(true);
        this.NoCed.setDisable(true);
        this.Edad.setDisable(true);
        this.combocargo.setDisable(true);
        this.btnBuscar.setDisable(true);
        this.remove.setDisable(true);
        this.valor = 0;
    }

    public void Limpiar() {
        this.Nombre.clear();
        this.cel.clear();
        this.apellidos.clear();
        this.dir.clear();
        this.NoCed.clear();
        this.Edad.clear();
        this.valor = 0;
    }

    @FXML
    public void remove() throws IOException {
        this.ImagenPredeterminada();
    }

    public void ImagenPredeterminada() throws IOException {
        File imgFile = new File("src/Imagenes/noimage.png");
        BufferedImage originalImage = ImageIO.read(imgFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage)originalImage, "png", baos);
        baos.flush();
        this.Imagebyte = baos.toByteArray();
        baos.close();
        ByteArrayInputStream in = new ByteArrayInputStream(this.Imagebyte);
        BufferedImage bImageFromConvert = ImageIO.read(in);
        File imagensalida = new File("src/Imagenes/Medico.png");
        ImageIO.write((RenderedImage)bImageFromConvert, "png", imagensalida);
        Image inicial = new Image("/Imagenes/NoPhoto.png");
        this.ivImagen.setImage(inicial);
    }
            
            
            
            
            
        
@Override
public void initialize(URL url, ResourceBundle rb) {
         Image inicial = new Image("/Imagenes/NoPhoto.png");
         ivImagen.setImage(inicial);  
            try {
                ImagenPredeterminada();
            } catch (IOException ex) {
                Logger.getLogger(MedicosController.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        tableMed.setOnMousePressed(new EventHandler<MouseEvent>(){
        public void handle(MouseEvent me) {
//        System.out.println(tableMed.getSelectionModel().getSelectedIndex());
        mod.setDisable(false);
        elim.setDisable(false);
        cancel.setDisable(false);
        add.setDisable(true);
//        buscar.setDisable(true);
            if(me.getClickCount()==1){
            if(tableMed.getSelectionModel().getSelectedIndex()==-1){
              mod.setDisable(true);
              elim.setDisable(true);
            }
                    
            System.out.println(me.getButton());    
            
            if(me.getButton().toString()=="SECONDARY"){
                    System.out.println("clic derecho");
                
                    tableMed.setContextMenu(conmenu);
            }
            
            }

        }
         });
            
        columnoc.setCellValueFactory(new PropertyValueFactory<>("noCedula"));
        columnom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columape.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columcargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        columdir.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        combocargo.valueProperty().addListener((p1, ov, p2) -> {
       
        cargomedico=(String) p1.getValue();
        System.out.println(cargomedico);
        if(cargomedico=="Administrador"){
            DeshabilitarCombos();
            ListaADM();
            
        }else{
         if(cargomedico=="Bioanalista" && validarcargo==1){
            HabilitarCombos();
            Lista();
         }else{
            DeshabilitarCombos();
            ListaREC();
         }   
        }
        
        
        });
        
        try {
            Inicial();
        } catch (IOException ex) {
            Logger.getLogger(MedicosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        valor=0; 
}

}
