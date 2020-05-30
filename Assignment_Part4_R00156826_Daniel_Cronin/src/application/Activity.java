package application;

import java.time.LocalDate;

//structure of the Activity 
public class Activity{
	
	private String week;
	private LocalDate date;
	private String points;
	private String activity;
	private String uploaded;
	
	public Activity(String week, LocalDate date, String activity, String points) {
		
		this.week = week;
		this.date = date;
		this.activity = activity;
		this.points = points;
		this.setUploaded("No");
		
	}
	
	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getUploaded() {
		return uploaded;
	}

	public void setUploaded(String uploaded) {
		this.uploaded = uploaded;
	}

}
