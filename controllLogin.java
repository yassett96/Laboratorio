/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;
import Hibernate.NewHibernateUtil;
import POJO.*;
import Animations.FadeInLeftTransition;
import Animations.FadeInLeftTransition1;
import Animations.FadeInRightTransition;
//import Hibernate.NewHibernateUtil;
import POJO.Medicos;
import POJO.Usuario;
import Utilidades.RolesxDia;
import Utilidades.config2;
import Validaciones.Cifrado;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author Herudi
 */
public class controllLogin implements Initializable {

                SessionFactory factor;
     Session s =  NewHibernateUtil.getSessionFactory().openSession();

    @FXML
    private PasswordField txtPassword;
    @FXML
    private Text lblUserLogin;
    @FXML
    private Text lblUsername;
    @FXML ComboBox combomed;
    @FXML
    private Text lblPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Text lblRudyCom;
    @FXML 
    private Label lblClose;    
    @FXML public TextField med;
    @FXML public ImageView ivImagen;
    Stage stage;

    public String nombre;
    public String apellido;

    ObservableList<String> choices = FXCollections.observableArrayList();
    public ObservableList<String> cargos = FXCollections.observableArrayList();
    public String user;
    public String pass;
            		String[] strDays = new String[]{
						"Domingo",
						"Lunes",
						"Martes",
						"Miercoles",
						"Jueves",
						"Viernes",
						"Sabado"};
    public int id;
    private Image image;
    private String fechadehoy;
    public String nuser;
    
    
    
    
    
    public void cargar() throws Exception, IOException{

        System.out.println("adentro");
        System.out.println(nuser);
        Criteria c = s.createCriteria(Usuario.class)
        .add(Restrictions.like("nombre", nuser,MatchMode.ANYWHERE));
        
        System.out.println("tamaño:"+c.list().size());
        for(Object pac: c.list() )
        {
            Usuario p= (Usuario) pac;
            
//            System.out.println(p.getMedicos().getIdMedico()+" "+p.getMedicos().getUsuario().getNombre());
            id=p.getIdUsuario();
            System.out.println("me genero "+id);
        }
                
        Query query = s.createQuery("    select new map (m.nombre,m.apellido,m.foto,u.nombre,u.password)\n" +
        "    from Medicos as m\n" +
        "    inner join m.roles as r\n" +
        "    inner join m.usuario as u\n" +
        "    where u.idUsuario=:Id");
        query.setParameter("Id", id);
        List <Map> lista = query.list();
        
        System.out.println(lista.get(0).size());
        Iterator<String> it=lista.get(0).keySet().iterator();
        
        nombre=lista.get(0).get(it.next()).toString();
        apellido=lista.get(0).get(it.next()).toString();
        byte[] imagen= (byte[]) lista.get(0).get(it.next());
        System.out.println(nombre);
        System.out.println(apellido);
        System.out.println(imagen);
        System.out.println("Aqui llego");
        InputStream in = new ByteArrayInputStream(imagen);
        System.out.println("aqui tambien llego");
        System.out.println(ImageIO.read(in));
        BufferedImage bImageFromConvert = ImageIO.read(in);
        File imagensalida= new File("src/Imagenes/Medico.png");
        System.out.println(imagensalida);
        System.out.println(bImageFromConvert);
        ImageIO.write(ImageIO.read(imagensalida), "png", imagensalida);  
        image = new Image("file:" + imagensalida.getAbsolutePath());
        ivImagen.setImage(image);
        med.setText(nombre+" "+apellido);
        
        user=lista.get(0).get(it.next()).toString();
        
        //byte[] cifrado= Base64.getDecoder().decode(lista.get(0).get(it.next()).toString());
         byte[] cifrado= (byte[]) lista.get(0).get(it.next());
        //String cifrado=  lista.get(0).get(it.next()).toString().trim();
        
        //String cif = new String(lista.get(0).get(it.next()).toString().trim());
        
        //byte[] cifrado= Base64.getDecoder().decode(cif);
        
        System.out.println(cifrado);
        pass=Cifrado.descifra(cifrado);
        System.out.println(pass);        
    }
    
    public void roles(){
        Query query1 = s.createQuery("from Medicos as m inner join m.roles as r inner join m.usuario as u\n" +
"    where r.embarazo=:hoy and u.registro='Activo' and idUsuario=:Id");
    query1.setParameter("hoy", fechadehoy);
    query1.setParameter("Id", id);
        if(query1.list().size()==1){cargos.add("Embarazo");}
        
        Query query2 = s.createQuery("from Medicos as m inner join m.roles as r inner join m.usuario as u\n" +
"    where r.heces=:hoy and u.registro='Activo' and idUsuario=:Id");
    query2.setParameter("hoy", fechadehoy);
    query2.setParameter("Id", id);
        if(query2.list().size()==1){cargos.add("Heces");}

        Query query3 = s.createQuery("from Medicos as m inner join m.roles as r inner join m.usuario as u\n" +
"    where r.orina=:hoy and u.registro='Activo' and idUsuario=:Id");
    query3.setParameter("hoy", fechadehoy);
    query3.setParameter("Id", id);
        if(query3.list().size()==1){cargos.add("Orina");}        
 
        Query query4 = s.createQuery("from Medicos as m inner join m.roles as r inner join m.usuario as u\n" +
"    where r.hematologia=:hoy and u.registro='Activo' and idUsuario=:Id");
    query4.setParameter("hoy", fechadehoy);
    query4.setParameter("Id", id);
        if(query4.list().size()==1){cargos.add("Hematologia");}  
        
        Query query5 = s.createQuery("from Medicos as m inner join m.roles as r inner join m.usuario as u\n" +
"    where r.quimica=:hoy and u.registro='Activo' and idUsuario=:Id");
    query5.setParameter("hoy", fechadehoy);
    query5.setParameter("Id", id);
        if(query5.list().size()==1){cargos.add("Quimica");}

        Query query6 = s.createQuery("from Medicos as m inner join m.roles as r inner join m.usuario as u\n" +
"    where r.inmunologia=:hoy and u.registro='Activo' and idUsuario=:Id");
    query6.setParameter("hoy", fechadehoy);
    query6.setParameter("Id", id);
        if(query6.list().size()==1){cargos.add("Inmunologia");}

        Query query7 = s.createQuery("from Medicos as m inner join m.roles as r inner join m.usuario as u\n" +
"    where r.parasitologia=:hoy and u.registro='Activo' and idUsuario=:Id");
    query7.setParameter("hoy", fechadehoy);
    query7.setParameter("Id", id);
        if(query7.list().size()==1){cargos.add("Parasitologia");}

        Query query8 = s.createQuery("from Medicos as m inner join m.roles as r inner join m.usuario as u\n" +
"    where r.bacteriologia=:hoy and u.registro='Activo' and idUsuario=:Id");
    query8.setParameter("hoy", fechadehoy);
    query8.setParameter("Id", id);
        if(query8.list().size()==1){cargos.add("Bacteriologia");}

        Query query9 = s.createQuery("from Medicos as m inner join m.roles as r inner join m.usuario as u\n" +
"    where r.bancoSangre=:hoy and u.registro='Activo' and idUsuario=:Id");
    query9.setParameter("hoy", fechadehoy);
    query9.setParameter("Id", id);
        if(query9.list().size()==1){cargos.add("BancoSangre");}   
        
//        System.out.println(cargos);

        
    }
    
    
    public void fecha(){
                Calendar now = Calendar.getInstance();
        System.out.println("Fecha actual : " + (now.get(Calendar.MONTH) + 1)
						+ "-"
						+ now.get(Calendar.DATE)
						+ "-"
						+ now.get(Calendar.YEAR));
        System.out.println("Hoy es : " + strDays[now.get(Calendar.DAY_OF_WEEK) - 1]);
        fechadehoy=(strDays[now.get(Calendar.DAY_OF_WEEK) - 1]);
        System.out.println(fechadehoy);
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        med.setEditable(false);
        fecha();
        Platform.runLater(() -> {
            new FadeInRightTransition(lblUserLogin).play();
//            new FadeInLeftTransition(lblWelcome).play();
//            lblWelcome.setText("Welcome");
            new FadeInLeftTransition1(lblPassword).play();
            new FadeInLeftTransition1(lblUsername).play();
            new FadeInLeftTransition1(txtPassword).play();
            new FadeInRightTransition(btnLogin).play();
//            txtUsername.setText("Laboratorio");
            
    



                 Criteria c = s.createCriteria(Usuario.class)
                 .add(Restrictions.eq("registro", "Activo"))
     .addOrder( Order.asc("idUsuario") );
     for(Object p: c.list()){
         Usuario m = (Usuario) p;
         choices.add(m.getNombre());
     }
     

     
     
     combomed.setItems(choices);
     
       combomed.valueProperty().addListener((p1, ov, p2) -> {
//        System.out.println(p1.getValue().toString());
        
        nuser=String.valueOf(p1.getValue().toString());
        System.out.println(nuser);

                try {
                    cargar();
                } catch (Exception ex) {
                    Logger.getLogger(controllLogin.class.getName()).log(Level.SEVERE, null, ex);
                }

        });
    
     
     
     
     
     
            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Platform.exit();
                System.exit(0);
            });
        });
        // TODO
    }    

    @FXML
    private void aksiLogin(ActionEvent event) throws IOException {
        
        if (combomed.getValue().toString().equals(user) && txtPassword.getText().equals(pass)) {
            roles();
            PrincipalController r = new PrincipalController();
//            System.out.println(cargos);
//            r.setCargos(cargos);
            config2 c = new config2();
            System.out.println("nnn");
//            s.close();
            c.newStage(id,cargos,stage, lblClose, "/FXML/Principal.fxml", "Laboratorio Clinico", true, StageStyle.DECORATED, true);
            
        }else{
            config2.dialog(Alert.AlertType.ERROR, "Error al Ingresar, Revise Usuario y Contraseña");
        }
    }
    
 
    
}
