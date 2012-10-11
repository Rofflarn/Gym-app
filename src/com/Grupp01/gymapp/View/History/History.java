package com.Grupp01.gymapp.View.History;

public class History {
	private String workoutName;
	private String date;
	
	public History(String name, String date){
		workoutName = name;
		this.date = date;
	}
	
	public String getName(){
		return workoutName;
	}
	
	public String getDate(){
		return date;
	}

}
