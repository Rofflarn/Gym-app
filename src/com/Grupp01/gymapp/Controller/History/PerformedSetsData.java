package com.Grupp01.gymapp.Controller.History;

public class PerformedSetsData {
	
	private int exerciseId;
	private int sets;
	private int reps;
	private int weight;
	private int distance;
	private String duration;
	private String setsString;
	private String exerciseName;
	
	public PerformedSetsData(String duration, int distance)
	{
		this.duration = duration;
		this.distance = distance;
	}
	
	public PerformedSetsData(String exerciseName, String sets)
	{
		this.exerciseName = exerciseName;
		this.setsString = sets;
	}
	
	public String getDuration()
	{
		return duration;
	}
	
	public int getDistance()
	{
		return distance;
	}
	
	public String getSetsString()
	{
		return setsString;
	}

}
