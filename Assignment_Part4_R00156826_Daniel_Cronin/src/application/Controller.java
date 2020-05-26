package application;

import javafx.scene.layout.BorderPane;

public class Controller extends BorderPane {


	public TabPane_1 tabPane;
	public Activitylist list;
	public MyEventHandler event;

public Controller() {
		
	event = new MyEventHandler(this);
	list = new Activitylist();
	tabPane = new TabPane_1(this);	
	
	this.setCenter(tabPane);

	}

}
