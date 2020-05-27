package application;



import Dialogs.AlertBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {

	public Stage window;
	TextField nameInput;
	PasswordField passInput;
	
	public void start(Stage primaryStage) {
		//setup initial sign in page
		window = primaryStage;
		window.setTitle("Login");
		
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label nameLabel = new Label("Username:");
		GridPane.setConstraints(nameLabel, 0, 0);
		
		nameInput = new TextField();
		GridPane.setConstraints(nameInput, 1, 0);
		
		Label passLabel = new Label("Password:");
		GridPane.setConstraints(passLabel, 0, 1);
		
		passInput = new PasswordField();
		passInput.setPromptText("password");
		GridPane.setConstraints(passInput, 1, 1);
		
		Button loginButton = new Button("login");
		
		//use a lambda to handle what happens with the login
		loginButton.setOnAction(e-> handleLogin());
		GridPane.setConstraints(loginButton, 1, 2);
		
		//add all elements to the scene
		grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton);
		
		Scene scene = new Scene(grid, 300, 200);
		window.setScene(scene);
		window.show();
		
	}
	
	//when the login button is pressed 
	public void handleLogin() {
		
		//take the input from the textfields and store as strings
		String username = nameInput.getText().toString();
		String password = passInput.getText().toString();
	
		String title = "incorrect Login", message = "Username/Password combo incorrect";
		
		//test if the checklogin function returns true. i.e. if the credentials appear in the database
		if(!Controller.getInstance().checkLogin(username, password)) {
			//if not display the error message
			AlertBox.display(title, message);
		}
		//else continue on with the rest of the system
		else {
			Scene scene = new Scene(Controller.getInstance(),1000,600);
			
			window.setScene(scene);
			window.setTitle("");
			window.show();
		}
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
