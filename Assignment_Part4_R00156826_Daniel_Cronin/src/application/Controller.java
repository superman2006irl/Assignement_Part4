package application;

import java.sql.SQLException;
import java.util.List;

import Lists.Activitylist;
import database.DatabaseConnection;
import javafx.scene.layout.BorderPane;

public class Controller extends BorderPane {

	private static Controller instance;
	public TabPane_1 tabPane;
	public Activitylist list;
	private static String IDNumber;
	public String loaded;

	public Controller() {

		instance = this;

		list = new Activitylist();
		tabPane = new TabPane_1(this);
		loaded = "No";
		this.setCenter(tabPane);

	}

	public static Controller getInstance() {
		if (instance == null) {
			return new Controller();
		}
		return instance;
	}

	// login function to connect to the database and test if credentials appear
	public boolean checkLogin(String username, String password) {

		boolean returnVal = false;
		// create a new connection
		DatabaseConnection db = new DatabaseConnection();
		try {
			// make the connection
			db.makeConnection();
			// send a query to the database, telling it where to look and what to look for.
			List<List<String>> results = db.executeQueryForResults(
					"select * from login where username = '" + username + "' and pass_word = '" + password + "'");
			for (List<String> Act : results) {
				IDNumber = Act.get(0);
				
			}
			// simple test to see if results is empty
			if (results.size() > 0) {
				// if results is not empty then set true
				returnVal = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection();
		}

		return returnVal;

	}

	public static String getIDNumber() {
		return IDNumber;
	}

}