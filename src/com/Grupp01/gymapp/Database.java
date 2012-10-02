package com.Grupp01.gymapp;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Database {

	private static final String DATABASE_NAME = "GymAppDatabase"; 
	private static final int DATABASE_VERSION = 1;

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	

	public Database(Context c)
	{
		ourContext = c;
	}

	public Database open() throws SQLException
	{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close()
	{
		ourHelper.close();
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ "ExerciseTypeID", "ExerciseTypeName"};
		Cursor c = ourDatabase.rawQuery("SELECT * FROM ExerciseTypes;", null);
		String result = "";

		int id = c.getColumnIndex("ExerciseTypeId");
		int name = c.getColumnIndex("ExerciseTypeName");
		//Forlopp som går igenom hela databasen, alla kolummer
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			result = result + c.getString(id) + " " + c.getString(name) + "\n";
			System.out.println(Integer.toString(c.getInt(id)) + " | " + c.getString(name));
		}

		return result;
	}

	public String[] getExerciseTypes(){
		Cursor c = ourDatabase.rawQuery("SELECT * FROM ExerciseTypes;", null);
		ArrayList<String> strings = new ArrayList<String>();
		System.out.println("Runnig Ex erciseTypes");
		
		int id = c.getColumnIndex("ExerciseTypeId");
		int name = c.getColumnIndex("ExerciseTypeName");
		
		//Forlopp som går igenom hela databasen, alla kolummer
        //String[] columns = new String[]{ "ExerciseTypeID", "ExerciseTypeName"};
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			
			//result = result + c.getString(id) + " " + c.getString(name) + "\n";
			//System.out.println("Id: " + Integer.toString(c.getInt(id)) + " | Name: " + c.getString(name));
			//items[c.getInt(0)] = c.getString(1);
			//System.out.println(""+items[c.getInt(id)]);
			strings.add(c.getString(name));
		}
		System.out.println("Exit ExerciseTypes");
		//return items;
		return (String[]) strings.toArray(new String[strings.size()]);
		//Kanske borde returnera map?
	}
	
	public void addExercise(int ExerciseMusclePri, int ExerciseMuscleSec, String ExerciseName, String ExerciseDesc, String ExerciseNote, int ExerciseSportId, int ExerciseTypeId){
		ourDatabase.execSQL("INSERT INTO Exercises (ExerciseMusclePri, ExerciseMuscleSec, ExerciseName, ExerciseDesc, ExerciseNote, ExerciseSportId, ExerciseTypeId) " +
				"VALUES (" + ExerciseMusclePri + ", " + ExerciseMuscleSec + ", " + ExerciseName + ", " + ExerciseDesc + ", " + ExerciseNote + ", " + ExerciseTypeId + ");");
	}
	
	public Cursor getMuscleGroups(){
		return ourDatabase.rawQuery("SELECT * FROM MuscleGroups;" ,null);
	}
	
	public Cursor getMusclesByMuscleGroupId(int MuscleGroupId){
		return ourDatabase.rawQuery("SELECT * FROM Muscles WHERE MuscleGroupId = '" + MuscleGroupId + "';", null);
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
