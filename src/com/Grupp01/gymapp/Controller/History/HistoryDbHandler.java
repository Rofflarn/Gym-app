package com.Grupp01.gymapp.Controller.History;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import android.content.Context;
import android.database.Cursor;

import com.Grupp01.gymapp.Controller.Workout.SetsData;
import com.Grupp01.gymapp.Model.Database;

/**
 * @author GivDev
 * @version 0.1
 * @peer reviewed 
 * @date 
 * This class contains the necessary methods for accessing the database from the History part of
 * the GUI.
 */
public class HistoryDbHandler extends Database {

	//Used for timeformating equation
	public static final int NUMBER_SECONDS_IN_HOUR  = 3600;
	public static final int NUMBER_SECONDS_IN_MIN = 60;
	
	//Number of sets that are going to be shown in text: "Latest sets"
	public static final int NUMBER_OF_LATEST_SET = 4;


	/**
	 * This method forwards the Context to the superclass.
	 * @param c Reference to calling object.
	 */
	public HistoryDbHandler(Context c)
	{
		super(c);
	}

	/**
	 * This method is used for return every unice exerciseId from a performed workout
	 * @param workoutId WorkoutId for the selected performed workout.
	 * @return LinkedList containing Integers of all unice ExerciseId in a performed workout
	 */
	public List<Integer> getAllExerciseIdFromPerformedWorkout(int workoutId)
	{
		List<Integer> exerciseIdList = new LinkedList<Integer>();
		open();
		Cursor c = ourDatabase.rawQuery("SELECT ExerciseId FROM Sets WHERE WorkoutId="+
		workoutId + ";", null); 
		c.moveToFirst();
		int id = c.getColumnIndex("ExerciseId");
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			int temp = c.getInt(id);
			//Add exerciseId to the list if its not already in the list.
			if(!(exerciseIdList.contains(temp)))
			{
				exerciseIdList.add(temp);
			}
		}
		return exerciseIdList;
	}

	/**
	 * Gets exerciseTypeId for an exerciseId.
	 * @param exerciseIdList A LinkedList<Integer> of exerciseId.
	 * @return LinkedList<ExerciseIdTypeId> with exerciseId and exerciseTypeId as parameters.
	 */
	public List<ExerciseIdTypeId> getExerciseIdTypeIdList(List<Integer> exerciseIdList)
	{
		List<ExerciseIdTypeId> idTypeIdList = new LinkedList<ExerciseIdTypeId>();
		open();
		for(Integer id: exerciseIdList)
		{
			Cursor c = ourDatabase.rawQuery("SELECT ExerciseTypeId FROM Exercises Where " +
					"ExerciseId=" + id + ";", null);
			c.moveToFirst();
			int typeId = c.getColumnIndex("ExerciseTypeId");
			idTypeIdList.add(new ExerciseIdTypeId(id,c.getInt(typeId)));
			c.close();
		}
		close();
		return idTypeIdList;
	}

	/**
	 * This method returns a list of all performed workouts in form PerformedWorkoutData objects
	 * @return List of performed workouts in PerformedWorkoutData objects
	 */
	public List<PerformedWorkoutData> getPerformedWorkoutsList()
	{
		List<PerformedWorkoutData> workoutsList = new LinkedList<PerformedWorkoutData>();
		open();
		Cursor c = ourDatabase.rawQuery("SELECT * FROM Workouts ORDER BY WorkoutDate DESC;", null);
		c.moveToFirst();
		//Define the necessary column id's
		int id = c.getColumnIndex("WorkoutId");
		int date = c.getColumnIndex("WorkoutDate");
		int name = c.getColumnIndex("WorkoutName");

		//Create object to handle date and time in GMT timezone
		SimpleDateFormat gmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    gmt.setTimeZone(TimeZone.getTimeZone("GMT"));  
	    //Create object to handle date and time in local timezone
		SimpleDateFormat loc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    loc.setTimeZone(TimeZone.getDefault());
 
	    //Loop through the cursor
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			//Get the date from database
			String dbDate = c.getString(date);
		    Date parsedDate = null;
		    
		    //Parse the date and convert to the local timezone
		    try {
		        parsedDate = gmt.parse(dbDate);
		    } catch (ParseException e) {e.printStackTrace();}
			String listDate = loc.format(parsedDate);

			//Add element to list
			workoutsList.add(new PerformedWorkoutData(c.getInt(id), listDate ,c.getString(name)));
		}
		c.close();
		close();
		return workoutsList;
	}

	/**
	 * This method is used to get a list of SetsData elements of the performed cardio sets.
	 * @param exerciseId The id of the cardio exercise.
	 * @param workoutId The id of the workout that the sets is associated to.
	 * @return List of SetsData elements.
	 */
	public List<SetsData> getPerformedCardioSets(int exerciseId, int workoutId)
	{
		//Create list to store the sets in
		List<SetsData> setsList = new LinkedList<SetsData>();
		open();
		Cursor c = ourDatabase.rawQuery("SELECT * FROM Sets WHERE WorkoutId = " + workoutId +
				" AND " + "ExerciseId = " + exerciseId + ";", null);
		c.moveToFirst();
		int setDistance = c.getColumnIndex("SetDistance");
		int setDuration = c.getColumnIndex("SetDuration");
		//Procedure that runs through the cursor.
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			//Creates a timestring based on an integer which contains number of seconds
			String durationString = (((int) (c.getInt(setDuration) / NUMBER_SECONDS_IN_HOUR)) + ":"
			+ (((int) (c.getInt(setDuration)/ NUMBER_SECONDS_IN_MIN)) % NUMBER_SECONDS_IN_MIN) + ":"
			+ (c.getInt(setDuration) % NUMBER_SECONDS_IN_MIN)); 
			//Add element to list
			setsList.add(new SetsData(durationString,c.getInt(setDistance)));
		}	
		c.close();
		close();
		return setsList;
	}
}
