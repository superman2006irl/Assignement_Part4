package application;

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

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
			controller.event.handle(e);
		});
		remove.setOnAction(e -> {
			controller.event.handle(e);
		});
		listDis.setOnAction(e -> {
			controller.event.handle(e);
		});
		comboBox.setOnAction(e -> {
			controller.event.handle(e);
		});
		load.setOnAction(e -> {
			controller.list = (Activitylist) FileReadWrite.readFromFile("control.ser");
		});
		save.setOnAction(e -> {
			FileReadWrite.writeToFile("control.ser", controller.list);
		});
		exit.setOnAction(e -> {
			controller.event.handle(e);
		});

	}

	public void datePickerAction() {

		LocalDate date1 = datePicker.getValue();
		Locale locale1 = Locale.UK;
		int weekOfYear1 = date1.get(WeekFields.of(locale1).weekOfWeekBasedYear());

		week.setText(Integer.toString(weekOfYear1));

	}

}
