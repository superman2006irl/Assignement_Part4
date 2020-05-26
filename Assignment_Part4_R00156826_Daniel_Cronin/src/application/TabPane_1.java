package application;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabPane_1 extends TabPane {
		
	public Pane pane1;
	public Pane2 pane2;
	public Pane3 pane3;
	
	public TabPane_1(Controller controller) {
		
		// create tabs
		Tab tab1 = new Tab("Info");
		Tab tab2 = new Tab("Activity");
		Tab tab3 = new Tab("Summary");
		
		// ensures tabs can't be closed by user
		tab1.setClosable(false);
		tab2.setClosable(false);
		tab3.setClosable(false);
		
		// create contents for tabs
		pane1 = new Pane(controller);
		pane2 = new Pane2(controller);
		pane3 = new Pane3(controller);
		
		// fill tabs with content
		tab1.setContent(pane1);
		tab2.setContent(pane2);
		tab3.setContent(pane3);

		

		// add tabs containing content to Tab-pane
		// for loading into control pane
		this.getTabs().addAll(tab1,tab2,tab3);
			
		
		
	}

}
