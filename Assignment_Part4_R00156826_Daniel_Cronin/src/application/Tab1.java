package application;

import javafx.scene.text.Text;

public class Tab1 {
	
	private Text text;
	
	public Tab1() {
		
		text = new Text();
		text.setText("Hi, "
				+ "\n\nAre you worried about your impact on the enviorment?"
				+ "\n\nHere we can calculate your impact and summarize it so "
				+ "that you can see where you might make changes."
				+ "\n\nOn the next tab you can fill out your weekly activities!"
				+ "\n\nThen on the sumarize tab we will display your impact per week.");
		
		
		
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

}
