package application;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

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

public class Tab2 extends GridPane {

	public Activity activity;
	@SuppressWarnings("rawtypes")
	public TableView tableView;
	public ComboBox<String> comboBox;
	public GridPane root;
	public File h;
	public TextField week;
	public DatePicker datePicker;
	public TextField points;
	public Button add;
	public Button remove;
	public Button listDis;
	public Button load;
	public Button save;
	public Button exit;
	public Label errorMsg;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Tab2(Controller controller) {

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

		add.setOnAction(e -> {
			System.out.println(e);

			if (points.getText().isEmpty()) {
				root.add(errorMsg, 2, 2);
				comboBox.setStyle("-fx-border-color: red;");
			} else {
				comboBox.setStyle("");
				errorMsg.setVisible(false);

				activity = new Activity(week.getText(), datePicker.getValue(),
						comboBox.getSelectionModel().getSelectedItem().toString(), points.getText());
				controller.list.add(activity);

			}

		});
		remove.setOnAction(e -> {
			ObservableList<Activity> allActivities, selectedActivity;
			allActivities = controller.tabPane.pane2.tab2.tableView.getItems();
			selectedActivity = controller.tabPane.pane2.tab2.tableView.getSelectionModel().getSelectedItems();
			int selectedIndex = controller.tabPane.pane2.tab2.tableView.getSelectionModel().getSelectedIndex();
			controller.list.getActivitylist().remove(selectedIndex);
			allActivities.removeAll(selectedActivity);
		});
		listDis.setOnAction(e -> {
			tableView.getItems().clear();

			DatabaseConnection db = new DatabaseConnection();
			db.makeConnection();
			try {
				List<List<String>> results = db.executeQueryForResults("select * from activities;");

				for (List<String> Act : results) {
					activity = new Activity(
							Act.get(0), 
							LocalDate.parse(Act.get(1)), 
							Act.get(2), 
							Act.get(3));
					
					controller.list.add(activity);
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			for (Activity activity : controller.list.getActivitylist()) {
				tableView.getItems().add(activity);
			}

			tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		});
		comboBox.setOnAction(e -> {
			// controller.event.handle(e);
			String selection = comboBox.getValue();

			if (selection == "Walking 10 Points") {
				points.setText("10");
			} else if (selection == "Eating a 8oz Steak -10 Points") {
				points.setText("-10");

			} else if (selection == "Cycling 5 Points") {
				points.setText("5");

			} else if (selection == "Driving to work -5 Points") {
				points.setText("-5");

			} else if (selection == "Leisure drive 3 Points") {
				points.setText("3");

			} else if (selection == "Vegtarian for the day 7 Points") {
				points.setText("7");

			} else if (selection == "Cycling to work 7 Points") {
				points.setText("7");
			}
		});
		load.setOnAction(e -> {
			controller.list = (Activitylist) FileReadWrite.readFromFile("control.ser");
		});
		save.setOnAction(e -> {
			saveListToDB(controller);
		});
		exit.setOnAction(e -> {
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
			saveButton.setOnAction(event -> {
				saveListToDB(controller);
				System.exit(0);
			});
			closeButton.setOnAction(event -> {

				System.exit(0);
			});

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
		});

	}

	public void datePickerAction() {

		LocalDate date1 = datePicker.getValue();
		Locale locale1 = Locale.UK;
		int weekOfYear1 = date1.get(WeekFields.of(locale1).weekOfWeekBasedYear());

		week.setText(Integer.toString(weekOfYear1));

	}
	
	public void saveListToDB(Controller controller) {
		
		DatabaseConnection db = new DatabaseConnection();
		
		db.makeConnection();
		
		for(Activity r : controller.list.getActivitylist()) {
		
		
		String query = "INSERT INTO activities VALUES("+ Integer.parseInt(r.getWeek()) + ",'" + r.getDate().toString() 
				+ "','" + r.getActivity() + "'," 
				+Integer.parseInt(r.getPoints()) + ");"; 
				
				
		try {
			db.executeUpdate(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		}
		db.closeConnection();
		
		
	}

}
