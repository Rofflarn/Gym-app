package com.Grupp01.gymapp.Model;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Main database-class. Contains methods for creating the database and accessing data.
 * @author GivDev
 *
 */
public class Database {


	private DbHelper ourHelper;
	private final Context ourContext;
	protected SQLiteDatabase ourDatabase;
	
	/**
	 * 
	 * @param c
	 */
	public Database(Context c)
	{
		ourContext = c;
		if(c == null)
		{
			System.out.println("Context == null");
		}
	}

	/**
	 * Opens a new connection to the database.  
	 * @return
	 * @throws SQLException
	 */
	public Database open() throws SQLException
	{
		System.out.println("Innan skapning av ourHelper");
		ourHelper = new DbHelper(ourContext);
		System.out.println("Skapat ourHelper");
		ourDatabase = ourHelper.getWritableDatabase();
		System.out.println("ourdatbase = ourhelper.getdatabase");
		return this;	
	}

	/**
	 * 
	 */
	public void close()
	{
		ourDatabase.close();
		ourHelper.close();
	}
	

	

	public String[] getExerciseTypes(){
		Cursor c = ourDatabase.rawQuery("SELECT * FROM ExerciseTypes;", null);
		ArrayList<String> strings = new ArrayList<String>();
		System.out.println("Runnig Ex erciseTypes");
		
		int id = c.getColumnIndex("ExerciseTypeId");
		int name = c.getColumnIndex("ExerciseTypeName");
		
		//Forlopp som g�r igenom hela databasen, alla kolummer
        //String[] columns = new String[]{ "ExerciseTypeID", "ExerciseTypeName"};
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			
			
			strings.add(c.getString(name));
		}
		System.out.println("Exit ExerciseTypes");
		return (String[]) strings.toArray(new String[strings.size()]);
	}
	
	public void addExercise(int ExerciseMusclePri, int ExerciseMuscleSec, String ExerciseName, String ExerciseDesc, String ExerciseNote, int ExerciseSportId, int ExerciseTypeId){
		ourDatabase.execSQL("INSERT INTO Exercises (ExerciseMusclePri, ExerciseMuscleSec, ExerciseName, ExerciseDesc, ExerciseNote, ExerciseSportId, ExerciseTypeId) " +
				"VALUES (" + ExerciseMusclePri + ", " + ExerciseMuscleSec + ", " + ExerciseName + ", " + ExerciseDesc + ", " + ExerciseNote + ", " + ExerciseTypeId + ");");
	}
	
	public Cursor getMuscleGroups(){
		return ourDatabase.rawQuery("SELECT * FROM MuscleGroups;" ,null);
	}
	
	
	
	
	
	public Cursor getMusclesByMuscleByGroupId(int muscleGroupId){
		return ourDatabase.rawQuery("SELECT * FROM Muscles WHERE MuscleGroupId = '" + muscleGroupId + "';", null);
	}

	
	public String[] getMuscles()
	{
		Cursor c = ourDatabase.rawQuery("SELECT * FROM Muscles;", null);
		ArrayList<String> strings = new ArrayList<String>();
		System.out.println("Runnig Ex erciseTypes");
		
		int id = c.getColumnIndex("MuscleId");
		int name = c.getColumnIndex("MuscleName");

		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
			strings.add(c.getString(name));
		
		return (String[]) strings.toArray(new String[strings.size()]);
	}

	/*
	 * public Cursor getMusclesByMuscleGroupName(String MuscleGroupName){
	 *	return ourDatabase.rawQuery("SELECT * FROM Muscles WHERE MuscleGroupId = (SELECT MuscleGroupId FROM MuslceGroups WHERE MuscleGroupName = '" + MuscleGroupName + "');", null);
	 * }
	 * */


	public Cursor getUsers(){
		return ourDatabase.rawQuery("SELECT * FROM Users;", null);
		//Kanske borde returnera map?
	}

	public void addUser(String UserName, String UserBirthday){
		ourDatabase.execSQL("INSERT INTO Users (UserName, UserBirthday) VALUES ('" + UserName + "', '" + UserBirthday + "');");
		//TODO change to insert() instead of execSql()
	}
	
	public Cursor getExercisesByTypeId(int ExerciseTypeId){
		return ourDatabase.rawQuery("SELECT * FROM Exercises WHERE ExerciseTypeId = '" + ExerciseTypeId + "';", null);
	}


	public String[] getSports(){
		Cursor c = ourDatabase.rawQuery("SELECT * FROM Sports;", null);
		ArrayList<String> strings = new ArrayList<String>();
		System.out.println("Runnig Ex erciseTypes");
		
		int id = c.getColumnIndex("SportId");
		int name = c.getColumnIndex("SportName");

		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
			strings.add(c.getString(name));
		
		return (String[]) strings.toArray(new String[strings.size()]);
	}

	


	/**
	 * Gets all SetTemplates and ExerciseNames that belong to a PassTemplate. Note: only gets SetsId and ExerciseNames.
	 * 
	 * @return
	 */
	public Cursor getSetTemplatesByPassId(String PassTemplateId){
		return ourDatabase.rawQuery("SELECT SetTemplates.SetTemplateId, Exercises.ExerciseName FROM SetTemplates, Exercises " +
				"WHERE Exercises.ExerciseId = SetTemplates.ExerciseId AND SetTemplates.PassTemplateId = '" + PassTemplateId + "';", null);
	}
}
