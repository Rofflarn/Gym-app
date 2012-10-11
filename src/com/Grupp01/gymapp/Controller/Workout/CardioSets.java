package com.Grupp01.gymapp.Controller.Workout;

public class CardioSets {

	private int min;
	private int sec;
	private int distance;
	private int workoutId;
	private int exerciseId;
	private String duration;
	
	public CardioSets(int min, int sec,int dist, int workoutId, int exerciseId)
	{
		this.min = min;
		this.sec = sec;
		this.distance = dist;
		this.workoutId = workoutId;
		this.exerciseId = exerciseId;
	}
	
	public CardioSets(String duration, int distance)
	{
		this.duration = duration;
		this.distance = distance;
	}
	
	public int getMin()
	{
		return min;
	}
	
	public int getSec()
	{
		return sec;
	}
	
	public int getDistance()
	{
		return distance;
	}
	
	public int getworkoutId()
	{
		return workoutId;
	}
	
	public int getexerciseid()
	{
		return exerciseId;
	}
	
	public String getDuration()
	{
		return duration;
	}
	
	
}
