package application;

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

		summaryAct.setOnAction(e -> {controller.event.handle(e);});
		
		summaryDate.setOnAction(e -> {controller.event.handle(e);});

	}

}
