package dad.loginfxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.login.auth.AuthService;
import dad.login.auth.FileAuthService;
import dad.login.auth.LdapAuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controlador implements Initializable {

	// model

	private Modelo modelo = new Modelo();

	// view

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		modelo.ldapProperty().bind(ldap.selectedProperty());
		modelo.nombreProperty().bind(user.textProperty());
		modelo.passwordProperty().bind(password.textProperty());
		
	}

	public void mostrarCorrecto() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("AVISO");
		alert.setHeaderText("Acceso permitido");
		alert.setContentText("Contraseña y usuario correctos");
		alert.showAndWait();
	}

	public void mostrarIncorrecto() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText("Acceso denegado");
		alert.setContentText("Contraseña y/o usuario incorrectos");
		alert.showAndWait();
	}

	@FXML
	void onActionAcceder(ActionEvent event) {
		
		try { 
		
			AuthService service;
			
			if (modelo.isLdap()) {
				
				service = new LdapAuthService();
				
			} else {
				
				service = new FileAuthService();
				
			}
			
			if (service.login(modelo.getNombre(), modelo.getPassword())) {
			
				mostrarCorrecto();
			
			} else {
	
				mostrarIncorrecto();
				
			}
			
		} catch (Exception e) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error validando usuario");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			
		}

	}

	@FXML
	void onActionCancelar(ActionEvent event) {

		LoginApp.primaryStage.close();

	}

	public VBox getView() {
		return view;
	}


}
