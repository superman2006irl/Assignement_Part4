package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MyEventHandler implements EventHandler<ActionEvent> {

	public Controller controller;
	public Activity activity;

	public MyEventHandler(Controller controller) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void handle(ActionEvent event) {

		System.out.println(event);
		/*if (event.getSource() == controller.tabPane.pane3.tab3.summaryAct) {
			String res = "Activity \t\t\t\t\tDate \tPoints \tWeek \n";

			List<String> g = new ArrayList<String>();
			for (Activity a : controller.list.getActivitylist()) {
				boolean check = false;
				if (g.contains(a.getActivity().toLowerCase())) {
					check = true;
				}
				g.add(a.getActivity().toLowerCase());
				Collections.sort(g);
				if (check == false) {
					res += a.getActivity() + "\n";
				}
				for (Activity c : controller.list.getActivitylist()) {

					if (a.getActivity().equalsIgnoreCase(c.getActivity()) && check == false) {

						res += "\t\t\t\t\t " + c.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyy")) + "\t"
								+ c.getPoints() + "\t\t\t" + c.getWeek() + "\n";
					} else {

						res += "";

					}
				}

			}

			controller.tabPane.pane3.tab3.textArea.setText(res);

		} else if (event.getSource() == controller.tabPane.pane3.tab3.summaryDate) {
			String res = "";

			List<LocalDate> g = new ArrayList<LocalDate>();
			for (Activity a : controller.list.getActivitylist()) {
				boolean check = false;
				if (g.contains(a.getDate())) {
					check = true;
				}
				g.add(a.getDate());
				Collections.sort(g);
				if (check == false) {
					res += a.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyy")) + "\n";
				}
				for (Activity c : controller.list.getActivitylist()) {

					if (a.getDate().equals(c.getDate()) && check == false) {

						res += "\t\t\t" + c.getActivity() + ", Points = " + c.getPoints() + ", Week = " + c.getWeek()
								+ "\n";
					} else {

						res += "";

					}
				}

			}

			controller.tabPane.pane3.tab3.textArea.setText(res);
			
		} else*/
		//String id = ((Node) event.getSource()).getId();
		if (event.getSource() == controller.tabPane.pane2.tab2.add) {

			if (controller.tabPane.pane2.tab2.points.getText().isEmpty()) {
				controller.tabPane.pane2.tab2.root.add(controller.tabPane.pane2.tab2.errorMsg, 2, 2);
				controller.tabPane.pane2.tab2.comboBox.setStyle("-fx-border-color: red;");
			} else {
				controller.tabPane.pane2.tab2.comboBox.setStyle("");
				controller.tabPane.pane2.tab2.errorMsg.setVisible(false);
				if (controller.tabPane.pane2.tab2.h.exists()) {
					controller.list = (Activitylist) FileReadWrite.readFromFile("control.ser");
					activity = new Activity(controller.tabPane.pane2.tab2.week.getText(),
							controller.tabPane.pane2.tab2.datePicker.getValue(),
							controller.tabPane.pane2.tab2.comboBox.getSelectionModel().getSelectedItem().toString(),
							controller.tabPane.pane2.tab2.points.getText());
					controller.list.add(activity);

				} else {
					activity = new Activity(controller.tabPane.pane2.tab2.week.getText(),
							controller.tabPane.pane2.tab2.datePicker.getValue(),
							controller.tabPane.pane2.tab2.comboBox.getSelectionModel().getSelectedItem().toString(),
							controller.tabPane.pane2.tab2.points.getText());
					controller.list.add(activity);
				}
			

		}/* else if (event.getSource() == controller.tabPane.pane2.tab2.remove) {

			ObservableList<Activity> allActivities, selectedActivity;
			allActivities = controller.tabPane.pane2.tab2.tableView.getItems();
			selectedActivity = controller.tabPane.pane2.tab2.tableView.getSelectionModel().getSelectedItems();
			int selectedIndex = controller.tabPane.pane2.tab2.tableView.getSelectionModel().getSelectedIndex();
			controller.list.getActivitylist().remove(selectedIndex);
			allActivities.removeAll(selectedActivity);

		} else if (event.getSource() == controller.tabPane.pane2.tab2.listDis) {

			controller.tabPane.pane2.tab2.tableView.getItems().clear();

			for (Activity activity : controller.list.getActivitylist()) {
				controller.tabPane.pane2.tab2.tableView.getItems().add(activity);
			}

			controller.tabPane.pane2.tab2.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		} else if (event.getSource() == controller.tabPane.pane2.tab2.comboBox) {
			String selection = controller.tabPane.pane2.tab2.comboBox.getValue();

			if (selection == "Walking 10 Points") {
				controller.tabPane.pane2.tab2.points.setText("10");
			} else if (selection == "Eating a 8oz Steak -10 Points") {
				controller.tabPane.pane2.tab2.points.setText("-10");

			} else if (selection == "Cycling 5 Points") {
				controller.tabPane.pane2.tab2.points.setText("5");

			} else if (selection == "Driving to work -5 Points") {
				controller.tabPane.pane2.tab2.points.setText("-5");

			} else if (selection == "Leisure drive 3 Points") {
				controller.tabPane.pane2.tab2.points.setText("3");

			} else if (selection == "Vegtarian for the day 7 Points") {
				controller.tabPane.pane2.tab2.points.setText("7");

			} else if (selection == "Cycling to work 7 Points") {
				controller.tabPane.pane2.tab2.points.setText("7");
			}
		} else if (event.getSource().equals(controller.tabPane.pane2.tab2.exit)) {

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
			saveButton.setOnAction(e -> {

				FileReadWrite.writeToFile("control.ser", controller.list);
				System.exit(0);
			});
			closeButton.setOnAction(e -> {

				System.exit(0);
			});

			cancelButton.setOnAction(e ->

			window.close()
			);
			
			label.setPadding(new Insets(10));
			BorderPane.setAlignment(label, Pos.CENTER);
			
			layout.getChildren().addAll(saveButton, closeButton, cancelButton);
			layout.setAlignment(Pos.CENTER);
			
		
			borderPane.setTop(label);
			borderPane.setCenter(layout);

			window.setResizable(true);
			Scene scene = new Scene(borderPane, 300, 150);
			window.setScene(scene);
			window.showAndWait();

		}*/
	}
	}
}
