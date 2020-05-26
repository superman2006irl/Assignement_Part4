package application;

import java.io.File;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Main extends Application {

	public activitylist list;
	public Activity activity;
	public Stage window;
	@SuppressWarnings("rawtypes")
	public TableView tableView;
	public activitylist activitylist;
	// public FileReadWrite control;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage primaryStage) {

		window = primaryStage;

		list = new activitylist();
		File h = new File("control.ser");

		GridPane root = new GridPane();
		Scene scene = new Scene(root, 1000, 600);
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setVgap(5);
		root.setHgap(5);

		Text weeklbl = new Text("Week------- ");
		TextField week = new TextField();
		Label datelbl = new Label("Date----- ");
		DatePicker date = new DatePicker();
		Label activitylbl = new Label("Activity----- ");
		TextField activityField = new TextField();
		Text pointslbl = new Text("Points-10 -- +10 ");
		TextField points = new TextField();
		Button add = new Button("ADD");
		add.setOnAction(e -> {
			
			if(h.exists()) {
				list = (activitylist) FileReadWrite.readFromFile("control.ser");
				activity = new Activity(week.getText(), date.getValue(), activityField.getText(), points.getText());
				list.add(activity);
			}
			else {
				activity = new Activity(week.getText(), date.getValue(), activityField.getText(), points.getText());
				list.add(activity);
			}

		});
		Button remove = new Button("Remove");
		Button listDis = new Button("List");
		Button summary = new Button("Summary");
		Button load = new Button("Load");
		Button save = new Button("Save");
		Button exit = new Button("Exit");

		tableView = new TableView();

		root.add(weeklbl, 0, 0);
		week.setPrefColumnCount(10);
		root.add(week, 1, 0);
		root.add(datelbl, 0, 1);
		root.add(date, 1, 1);
		root.add(activitylbl, 0, 2);
		root.add(activityField, 1, 2);
		root.add(pointslbl, 0, 3);
		root.add(points, 1, 3);
		root.add(add, 0, 4);
		root.add(remove, 1, 4);
		root.add(listDis, 2, 4);
		root.add(summary, 4, 4);

		remove.setOnAction(e -> {

			ObservableList<Activity> allActivities, selectedActivity;
			allActivities = tableView.getItems();
			selectedActivity = tableView.getSelectionModel().getSelectedItems();
			allActivities.removeAll(selectedActivity);
		});
		listDis.setOnAction(e -> {

			tableView.getItems().clear();

			for (Activity activity : list.getActivitylist()) {
				tableView.getItems().add(activity);
			}

			tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		});
		summary.setOnAction(e -> {
			GridPane summaryPane = new GridPane();

			String res = "";
			int weeklyscore = 0;

			Scene scene2 = new Scene(summaryPane, 1000, 600);
			window.setScene(scene2);
			window.show();

			Button back = new Button("Go Back");
			summaryPane.add(back, 7, 7);
			back.setOnAction(b -> {
				window.setScene(scene);
				window.show();
			});

			for (int i = 0; i <= 52; i++) {

				for (Activity a : list.getActivitylist()) {
					if (Integer.parseInt(a.getWeek()) == i) {
						weeklyscore += Integer.parseInt(a.getPoints());
					}

				}

				if (weeklyscore != 0) {
					res += "\nWeek: " + i + " Total Points: " + weeklyscore;
					weeklyscore = 0;
				} else {
					res += "";
					weeklyscore = 0;
				}
			}
			Label summaryView = new Label(res);
			summaryPane.add(summaryView, 1, 0);

		});

		TableColumn weekCol = new TableColumn("Week");
		weekCol.setCellValueFactory(new PropertyValueFactory<>("week"));
		TableColumn dateCol = new TableColumn("Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		TableColumn activityCol = new TableColumn("Activity");
		activityCol.setCellValueFactory(new PropertyValueFactory<>("activity"));
		TableColumn pointsCol = new TableColumn("Points");
		pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));

		tableView.getColumns().addAll(weekCol, dateCol, activityCol, pointsCol);
		tableView.setPrefHeight(200);
		root.add(tableView, 0, 5, 110, 3);
		root.add(load, 0, 9);
		load.setOnAction(e -> list = (activitylist) FileReadWrite.readFromFile("control.ser"));
		root.add(save, 1, 9);
		save.setOnAction(e -> {

			FileReadWrite.writeToFile("control.ser", list);

		});

		root.add(exit, 100, 9);
		exit.setOnAction(e -> {
			window.close();
		});

		window.setTitle("R00156826 Daniel Cronin");
		window.setScene(scene);

		window.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
