package dad.loginfxml;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controlador implements Initializable{

	@FXML
	private VBox view;
	
    @FXML
    private CheckBox ldap;
    
	@FXML
	private TextField user;
	
	@FXML
	private PasswordField password;
	
	
	public Controlador() {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/LoginVistas.fxml"));
	        loader.setController(this);
	        loader.load();
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	
	public void mostrarCorrecto() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("AVISO");
        alert.setHeaderText(null);
        alert.setContentText("Contraseña y usuario correctos");
        alert.showAndWait();
    }
    
    public void mostrarIncorrecto() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("ERROR");
    alert.setHeaderText(null);
    alert.setContentText("Contraseña y/o usuario incorrectos");

    alert.showAndWait();
    }
	
	

	
	
	@FXML
    void onActionAcceder(ActionEvent event) {
        Object source = event.getSource();
        
            if (!ldap.isSelected()) {
                    if (checkAuth()) {
                        mostrarCorrecto();
                    } else {
                        mostrarIncorrecto();
                    }
            } else {
                //LDAP
            }
        }
 
	
    @FXML
    void onActionCancelar(ActionEvent event) {
        System.exit(0);
    }

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public VBox getView() {
		return view;
	}
	
	

    private boolean checkAuth() {
        
        return true; 
    }
    
    
}






/*public class Controlador extends Application implements EventHandler {
    private Modelo modelo;
    private Vista vista;

    private boolean ldap_bool = false;

    public void actualizarNombre(String nombre) {
        modelo.setNombre(nombre);
    }

    public void actualizarPassword(String password) {
        modelo.setPassword(password);
    }

    // esta función será la que mande los datos al modelo y el modelo se encargará de comprobarlos
    private boolean checkAuth() throws FileNotFoundException {
        modelo.setNombre(vista.getUsuarioField().getText());
        modelo.setPassword(vista.getPasswordField().getText());

        return modelo.login();
    }

    // aquí se implementa la lógica de interacción del usuario con los botones de la interfaz
    @Override
    public void handle(final Event event) {
        final Object source = event.getSource();

        if(source.equals(vista.getBoton_acceder())) {
            if (!ldap_bool) {
                try {
                    if(checkAuth()) {
                        vista.mostrarCorrecto();
                    }
                    else {
                        vista.mostrarIncorrecto();
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // implementar lógica con LDAP
            }
        }
        if(source.equals(vista.getBoton_cancelar())) {
            System.exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        modelo = new Modelo();
        vista = new Vista(this);

        vista.start(primaryStage);
    }
}*/
