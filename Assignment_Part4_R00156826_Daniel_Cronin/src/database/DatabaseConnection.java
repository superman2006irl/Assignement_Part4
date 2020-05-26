package database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/dentist";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";
	Connection conn;
	Statement stmt;
	ResultSet rs;

	public void makeConnection() {

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected to database!");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}// end make connection

	private void getResults(String query) throws SQLException {
		// Statements allow to issue SQL queries to the database
		stmt = conn.createStatement();
		// Result set get the result of the SQL query
		rs = stmt.executeQuery(query);
	}

	// generic method which will return a list of rows where each row contains a
	// list of strings
	// we can convert the columns later when we get them back
	public List<List<String>> executeQueryForResults(String query) throws SQLException {
		System.out.println(query);
		getResults(query);
		ResultSetMetaData meta = rs.getMetaData();
		final int columnCount = meta.getColumnCount();
		List<List<String>> rowList = new ArrayList<List<String>>();
		while (rs.next()) {
			List<String> columnList = new ArrayList<String>();
			rowList.add(columnList);

			for (int column = 1; column <= columnCount; column++) {
				Object value = rs.getObject(column);
				columnList.add(String.valueOf(value));
			}
		}
		return rowList;
	}

	//execute a query where we will not get results from the database but just update it
	public void executeUpdate(String query) throws SQLException {
		// Statements allow to issue SQL queries to the database
		stmt = conn.createStatement();
		// Result set get the result of the SQL query
		stmt.executeQuery(query);
	}

	// start close connections
	public void closeConnection() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se2) {
		} // nothing we can do
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}finally{

			System.out.println("disConnected from database!");
			// end finally try
		}
	}
}// end FirstExample