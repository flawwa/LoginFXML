package dad.loginfxml;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginApp extends Application {

	public static Stage primaryStage; 
	
	private Controlador controlador;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		LoginApp.primaryStage = primaryStage;

		controlador = new Controlador();
		
		primaryStage.setTitle("Iniciar sesión");
		primaryStage.setScene(new Scene(controlador.getView(), 320, 200));
		primaryStage.show();

	}

}
