package application;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class Pane2 extends FlowPane {

	public HBox hBox;
	public Tab2 tab2;

	public Pane2(Controller controller) {

		tab2 = new Tab2(controller);
		hBox = new HBox(10);
		hBox.setPadding(new Insets(10, 10, 10, 10));

		hBox.getChildren().add(tab2.root);

		this.getChildren().add(hBox);

	}

}
