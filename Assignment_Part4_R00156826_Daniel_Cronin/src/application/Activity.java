package application;

import java.time.LocalDate;

public class Activity{
	
	private String week;
	private LocalDate date;
	private String points;
	private String activity;
	
	public Activity(String week, LocalDate date, String activity, String points) {
		
		this.week = week;
		this.date = date;
		this.activity = activity;
		this.points = points;
		
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

}
