package Tabs;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import application.Activity;
import application.Controller;
import database.DatabaseConnection;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Tab2 extends GridPane {

	public Activity activity;
	public TableView tableView;
	public ComboBox<String> comboBox;
	public GridPane root;
	public File h;
	public TextField week, points;
	public DatePicker datePicker;
	public Button add, remove, button, listDis, load, save, exit;
	public Label errorMsg;

	public Tab2(Controller controller) {

		//creates the layout for tab2(activities)
		Label datelbl = new Label("Date----- ");
		Text weeklbl = new Text("Week------- ");
		Label activitylbl = new Label("Activity----- ");
		Text pointslbl = new Text("Points-10 -- +10 ");
		TableColumn weekCol = new TableColumn("Week");
		TableColumn dateCol = new TableColumn("Date");
		TableColumn activityCol = new TableColumn("Activity");
		TableColumn pointsCol = new TableColumn("Points");

		tableView = new TableView();
		comboBox = new ComboBox<>();
		root = new GridPane();
		h = new File("control.ser");
		week = new TextField();
		datePicker = new DatePicker();
		points = new TextField();
		add = new Button("Add");
		add.setId("addButton");
		remove = new Button("Remove");
		listDis = new Button("List");
		load = new Button("Load");
		save = new Button("Save");
		exit = new Button("Exit");
		errorMsg = new Label("Please select an activity from the list");

		week.setEditable(false);
		points.setEditable(false);

		datePicker.setValue(LocalDate.now());
		datePickerAction();
		datePicker.setOnAction(e -> {
			datePickerAction();
		});

		comboBox.getItems().addAll("Walking 10 Points", "Eating a 8oz Steak -10 Points", "Cycling 5 Points",
				"Driving to work -5 Points", "Leisure drive 3 Points", "Vegtarian for the day 7 Points",
				"Cycling to work 7 Points", "Please Select activity");

		week.setPrefColumnCount(10);
		comboBox.setPromptText("Please Select activity");

		// Properties for the tableview
		weekCol.setCellValueFactory(new PropertyValueFactory<>("week"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		activityCol.setCellValueFactory(new PropertyValueFactory<>("activity"));
		pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
		tableView.getColumns().addAll(weekCol, dateCol, activityCol, pointsCol);
		tableView.setPrefHeight(200);

		// Add the elements to the pane
		root.add(datelbl, 0, 0);
		root.add(datePicker, 1, 0);
		root.add(weeklbl, 0, 1);
		root.add(week, 1, 1);
		root.add(activitylbl, 0, 2);
		root.add(comboBox, 1, 2);
		root.add(pointslbl, 0, 3);
		root.add(points, 1, 3);
		root.add(add, 0, 4);
		root.add(remove, 1, 4);
		root.add(tableView, 0, 5, 110, 3);
		root.add(load, 0, 9);
		root.add(save, 1, 9);
		root.add(exit, 100, 9);
		root.add(listDis, 2, 4);

		
		// call to whichever function is required
		add.setOnAction(e -> {
			addToActivities(controller);

		});
		remove.setOnAction(e -> {
			removefromScreen(controller);
		});
		listDis.setOnAction(e -> {

			displayOnScreen(controller);
		});
		comboBox.setOnAction(e -> {
			comboSelection(controller);
		});
		load.setOnAction(e -> {
			load(controller);
		});
		save.setOnAction(e -> {
			saveListToDB(controller);
		});
		exit.setOnAction(e -> {
			exitOptions(controller);
		});

	}

	//function to format the date 
	public void datePickerAction() {

		LocalDate date1 = datePicker.getValue();
		Locale locale1 = Locale.UK;
		int weekOfYear1 = date1.get(WeekFields.of(locale1).weekOfWeekBasedYear());

		week.setText(Integer.toString(weekOfYear1));

	}

	//Adding to activities list does not save to database
	//This function takes the contents of the ArrayList and saves to the database
	public void saveListToDB(Controller controller) {

		//create connection
		DatabaseConnection db = new DatabaseConnection();

		//Open a connection
		db.makeConnection();

		//Loop through list and create individual Activities 
		for (Activity r : controller.list.getActivitylist()) {

			//to prevent activities from uploading more than once
			//Each Activity has a variable that checks if it has been uploaded
			if (r.getUploaded() == "No") {
				r.setUploaded("Yes");
				
				//create a prepared string to upload to database
				String query = "INSERT INTO activities VALUES(" + Integer.parseInt(Controller.getIDNumber()) + ","
						+ Integer.parseInt(r.getWeek()) + ",'" + r.getDate().toString() + "','" + r.getActivity() + "',"
						+ Integer.parseInt(r.getPoints()) + ",'" + r.getUploaded() + "'" + ");";

				//try catch to upload to the database, if it does not upload 
				//prints the stack trace in order to narrow down the issue
				try {
					db.executeUpdate(query);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}

		}
		//close the connection
		db.closeConnection();
	}

	//Function to create new Activities and add to list
	public void addToActivities(Controller controller) {
		//check if points text field is empty and print error if so
		//prevents the program just crashing
		if (points.getText().isEmpty()) {
			root.add(errorMsg, 2, 2);
			comboBox.setStyle("-fx-border-color: red;");
			
		//passes the above test so creates a new activity and adds to list
		} else {
			comboBox.setStyle("");
			errorMsg.setVisible(false);

			activity = new Activity(week.getText(), datePicker.getValue(),
					comboBox.getSelectionModel().getSelectedItem().toString(), points.getText());
			controller.list.add(activity);

		}
	}

	//function to delete activity from screen and database
	public void removefromScreen(Controller controller) {
		
		//using tableview i can get the selected index using an observable list
		//then i can use that index to manipulate the display and the database
		ObservableList<Activity> allActivities, selectedActivity;
		allActivities = tableView.getItems();
		selectedActivity = tableView.getSelectionModel().getSelectedItems();
		int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
		
		//creating a new activity from the selected item, i use that to find it in my database 
		//and delete it 
		Activity selectedItem = controller.list.getActivitylist().get(selectedIndex);
		controller.list.getActivitylist().remove(selectedIndex);
		allActivities.removeAll(selectedActivity);
		
		
		//create a connection and conntect
		DatabaseConnection db = new DatabaseConnection();
		db.makeConnection();
		
		try {
			//update query with statement 
			db.executeUpdate("delete from activities where ID =" + Controller.getIDNumber() +" and week =" 
						+selectedItem.getWeek() +" and activity = '" 
						+ selectedItem.getActivity()
						+ "' and points =" + selectedItem.getPoints() + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//close connection
		db.closeConnection();
	}

	//function to load stored items in the database to the Arraylist
	public void load(Controller controller) {
		//check to see if we have already loaded from the database
		//if we havn't then we load 
		if (controller.loaded == "No") {
			
			//create connection and connect
			DatabaseConnection db = new DatabaseConnection();
			db.makeConnection();
			try {
				//store the results that meet the criteria in a list
				List<List<String>> results = db.executeQueryForResults(
						"select * from activities where ID =" + Controller.getIDNumber() + ";");

				//loop through that list and create new Activities.
				//then add to the ArrayList
				for (List<String> Act : results) {
					activity = new Activity(Act.get(1), LocalDate.parse(Act.get(2)), Act.get(3), Act.get(4));
					activity.setUploaded("Yes");
					controller.list.add(activity);
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			//change the loaded variable to yes that we can't reload and duplicate data
			controller.loaded = "Yes";
			db.closeConnection();
		}
	}

	//function to display the contents of the ArrayList on screen
	public void displayOnScreen(Controller controller) {
		
		//clear the table view first
		tableView.getItems().clear();

		//check if we have loaded the database 
		//if we have then we just load the contents of the list
		if (controller.loaded == "Yes") {
			for (Activity activity : controller.list.getActivitylist()) {
				tableView.getItems().add(activity);
			}

			tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
		//if not then we call the load function first and recall this function
		} else {
			load(controller);
			displayOnScreen(controller);
		}
	}

	//function to automatically fill a number in a box depending on the users choice
	public void comboSelection(Controller controller) {
		String selection = comboBox.getValue();
		
		switch(selection) {
		
		case "Walking 10 Points":
			points.setText("10");
			break;
			
		case "Eating a 8oz Steak -10 Points":
			points.setText("-10");
			break;
			
		case "Cycling 5 Points":
			points.setText("5");
			break;
			
		case "Driving to work -5 Points":
			points.setText("-5");
			break;
			
		case "Leisure drive 3 Points":
			points.setText("3");
			break;
			
		case "Vegtarian for the day 7 Points":
			points.setText("7");
			break;
			
		case "Cycling to work 7 Points":
			points.setText("7");
			break;
			
		}
				
	}
	
	//function to offer the user the chance to save before exiting 
	public void exitOptions(Controller controller) {
		
		//creates a new window and populates 
		Stage window = new Stage();
		Label label = new Label();
		Button saveButton = new Button("Save");
		Button cancelButton = new Button("Cancel");
		Button closeButton = new Button("Close without saving");
		HBox layout = new HBox(10);
		BorderPane borderPane = new BorderPane();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Save dialog");
		window.setMinWidth(250);

		label.setText("Would you like to save before exit");
		
		//if the user choices to save then the save function from above is called
		//this will only save anything that has not been saved
		saveButton.setOnAction(event -> {
			saveListToDB(controller);
			System.exit(0);
		});
		
		//if the user choices not to save the system shuts down
		closeButton.setOnAction(event -> {

			System.exit(0);
		});

		//the user also has the choice to cancel and return to the previous window
		cancelButton.setOnAction(event ->

		window.close());

		// label.setLabelPadding(new Insets(10));
		BorderPane.setAlignment(label, Pos.CENTER);

		layout.getChildren().addAll(saveButton, closeButton, cancelButton);
		layout.setAlignment(Pos.CENTER);

		borderPane.setTop(label);
		borderPane.setCenter(layout);

		window.setResizable(true);
		Scene scene = new Scene(borderPane, 300, 150);
		window.setScene(scene);
		window.showAndWait();
	}
}
