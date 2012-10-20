package com.Grupp01.gymapp.Controller.History;

public class ExerciseIdTypeId {
	
	private int exerciseId;
	private int exerciseTypeId;
	
	/**
	 * Creates new object and sets the instance variables.
	 * @param exerciseId
	 * @param exerciseTypeId
	 */
	public ExerciseIdTypeId(int exerciseId, int exerciseTypeId)
	{
		this.exerciseId = exerciseId;
		this.exerciseTypeId = exerciseTypeId;
	}
	
	/**
	 * Returns id for chosen exercise.
	 * @return Id for an exercise in form of Integer.
	 */
	public int getId()
	{
		return exerciseId;
	}
	
	/**
	 * Returns typeId for chosen exercise.
	 * @return TypeId for an exercise in form of Integer.
	 */
	public int getTypeId()
	{
		return exerciseTypeId;
	}
	

}
