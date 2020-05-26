package application;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;



public class Pane extends FlowPane{

	public HBox hBox;
	public Tab1 tab1;
	

	public Pane(Controller controller) {
			
			hBox = new HBox();	
			tab1 = new Tab1();
			hBox.setPadding(new Insets(50, 50, 50, 50));
			
			
			// add content to vbox
			hBox.getChildren().add(tab1.getText());
			
			
			// add vbox to this pane
			this.getChildren().add(hBox);

	}


}
