package application;

import java.sql.SQLException;
import java.util.List;

import Dialogs.AlertBox;
import database.DatabaseConnection;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewUser extends GridPane{

	Stage window;
	public VBox newUser;
	PasswordField passInput;
	TextField nameInput;
	GridPane grid;
	
	
	public NewUser(Controller controller) {
		
		window = new Stage();
		window.setTitle("Register new User");
		
		
		grid = new GridPane();
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
		
		Button submit = new Button("Submit");
		Button cancel = new Button("Cancel");
		

		submit.setOnAction(e-> handle(controller));
		GridPane.setConstraints(submit, 1, 2);
		cancel.setOnAction(e-> window.close());
		GridPane.setConstraints(cancel, 2, 2);

		
		//add all elements to the scene
		grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, submit, cancel);
		
		Scene scene = new Scene(grid, 300, 200);
		window.setScene(scene);
		window.show();
		
		
	}
	
	public void handle(Controller controller) {
		
		DatabaseConnection db = new DatabaseConnection();
		db.makeConnection();
		
		try {
			List<List<String>> results = db.executeQueryForResults(
					"select * from login where username = '" + nameInput.getText() + "' and pass_word = '" + passInput.getText() + "'");
			
			if(results.size() >0) {
				Label warning = new Label ("User and password combo\n"
						+ " Already exists!!!");
				GridPane.setConstraints(warning, 1, 3);
				grid.getChildren().add(warning);
			}
			else {
				db.executeUpdate("insert into login(username, pass_word) values('"
						+nameInput.getText()+"','" +passInput.getText()+"');" );
				AlertBox.display("User Registration", "User Created Successfully");
				window.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		
	}
	
}
