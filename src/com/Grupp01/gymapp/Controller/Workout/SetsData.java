package com.Grupp01.gymapp.Controller.Workout;

public class SetsData {

	private int min;
	private int sec;
	private int distance;
	private int weight;
	private int reps;
	private int workoutId;
	private int exerciseId;
	private String duration;
	
	public SetsData(int min, int sec,int dist, int workoutId, int exerciseId)
	{
		this.min = min;
		this.sec = sec;
		this.distance = dist;
		this.workoutId = workoutId;
		this.exerciseId = exerciseId;
	}
	
	public SetsData(String duration, int distance)
	{
		this.duration = duration;
		this.distance = distance;
	}
	
	public SetsData(int weight, String duration)
	{
		this.duration = duration;
		this.weight = weight;
	}
	
	public SetsData(int weight, int reps, int workoutId, int exerciseId)
	{
		this.weight = weight;
		this.reps = reps;
		this.workoutId = workoutId;
		this.exerciseId = exerciseId;
	}
	
	public SetsData(int weight, int reps)
	{
		this.weight = weight;
		this.reps = reps;
	}
	
	public SetsData(int min, int sec, int weight)
	{
		this.min = min;
		this.sec = sec;
		System.out.println(weight);
		this.weight = weight;
	}
	
	/**
	 * 
	 * @return number of minutes that the set has taken.
	 */
	public int getMin()
	{
		return min;
	}
	
	/**
	 * 
	 * @return number of seconds that the set has taken.
	 */
	public int getSec()
	{
		return sec;
	}
	
	/**
	 * 
	 * @return The distance that in the set.
	 */
	public int getDistance()
	{
		return distance;
	}
	
	/**
	 * 
	 * @return current weight in the set.
	 */
	public int getWeight()
	{
		return weight;
	}
	
	/**
	 * 
	 * @return how many reps that are in the set.
	 */
	public int getReps()
	{
		return reps;
	}
	
	/**
	 * 
	 * @return workoutId that the current exercise are in. 
	 */
	public int getworkoutId()
	{
		return workoutId;
	}
	
	/**
	 * 
	 * @return the id for current exercise.
	 */
	public int getexerciseid()
	{
		return exerciseId;
	}
	
	/**
	 * 
	 * @return the duration of the set, in format: hh:mm:ss
	 */
	public String getDuration()
	{
		return duration;
	}
	
	
}
