package application;

import java.sql.SQLException;
import java.util.List;
import database.DatabaseConnection;
import javafx.scene.layout.BorderPane;

public class Controller extends BorderPane {

	private static Controller instance;
	public TabPane_1 tabPane;
	public Activitylist list;
	public MyEventHandler event;

	public Controller() {

		instance = this;
		
		event = new MyEventHandler(this);
		list = new Activitylist();
		tabPane = new TabPane_1(this);

		this.setCenter(tabPane);

	}
	
	public static Controller getInstance() {
		if (instance == null) {
			return new Controller();
		}
		return instance;
	}

	public boolean checkLogin(String username, String password) {
		boolean returnVal = false;
		DatabaseConnection db = new DatabaseConnection();
		try {
			db.makeConnection();
			List<List<String>> results = db.executeQueryForResults("select * from login where username = '" + username +"' and pass_word = '" + password +"'");
			if(results.size() > 0);
				returnVal = true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection();
		}

		return returnVal;
	}

}
