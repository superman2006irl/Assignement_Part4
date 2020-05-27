package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Tab3 {

	public TextArea textArea;
	public Button summaryAct;
	public Button summaryDate;
	public Label choice;
	public String res;

	public Tab3(Controller controller) {

		res = "";
		textArea = new TextArea();
		summaryAct = new Button("Activities");
		summaryDate = new Button("Date");
		choice = new Label("Would you like organise by activities or by date?");

		textArea.setEditable(false);

		summaryAct.setOnAction(e -> {
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
		});

		summaryDate.setOnAction(e -> {
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
		});

	}

}
