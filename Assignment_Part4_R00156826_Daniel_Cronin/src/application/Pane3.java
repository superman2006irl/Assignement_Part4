package application;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;



public class Pane3 extends FlowPane{

	public VBox vBox;
	public Tab3 tab3;

	public Pane3(Controller controller) {
			
		tab3 = new Tab3(controller);
		vBox = new VBox();		
		vBox.setPadding(new Insets(10, 10, 10, 10));

		vBox.getChildren().addAll(tab3.textArea, tab3.choice, tab3.summaryAct, tab3.summaryDate);
		
		this.getChildren().add(vBox);
			
			

	}


}
