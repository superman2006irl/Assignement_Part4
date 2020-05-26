package application;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	public Stage window;
	
	public void start(Stage primaryStage) {
		
		Controller control = new Controller();
		
		Scene scene = new Scene(control,1000,600);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("");
		primaryStage.show();
		

	}

	public static void main(String[] args) {
		launch(args);
	}

}
