package com.Grupp01.gymapp;

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

	public Cursor getExerciseTypes(){
		return ourDatabase.rawQuery("SELECT * FROM ExerciseTypes;", null);
		//Kanske borde returnera map?
	}
	
	public void addExercise(int ExerciseMusclePri, int ExerciseMuscleSec, String ExerciseName, String ExerciseDesc, String ExerciseNote, int ExerciseSportId, int ExerciseTypeId){
		ourDatabase.execSQL("INSERT INTO Exercises (ExerciseMusclePri, ExerciseMuscleSec, ExerciseName, ExerciseDesc, ExerciseNote, ExerciseSportId, ExerciseTypeId) " +
				"VALUES (" + ExerciseMusclePri + ", " + ExerciseMuscleSec + ", " + ExerciseName + ", " + ExerciseDesc + ", " + ExerciseNote + ", " + ExerciseTypeId + ");");
	}
	
	 
	 public String getDataSports() {
	       // TODO Auto-generated method stub
	       String[] columns = new String[]{ "SportId", "SportName"};
	       Cursor c = ourDatabase.rawQuery("SELECT * FROM Sports;", null);
	       String result = "";
	       
	       int id = c.getColumnIndex("SportId");
	       int name = c.getColumnIndex("SportName");
	       //Forlopp som går igenom hela databasen, alla kolummer
	      for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
	      {
	    	  result = result + c.getString(id) + " " + c.getString(name) + "\n";
	    	  System.out.println(Integer.toString(c.getInt(id)) + " | " + c.getString(name));
	      }
	      
	       return result;
	   }
	 
	 public String getListWorkout(){
		 Cursor c = ourDatabase.rawQuery("SELECT * FROM Passes;", null);
		 int id = c.getColumnIndex("PassId");
		 int name = c.getColumnIndex("PassName");
		 String result = "";
		 for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
	      {
	    	  result = result + c.getString(id) + " " + c.getString(name) + "\n";
	      }
		 return result;
	 	}
	 
	 public String getListExercise(){
		 Cursor c = ourDatabase.rawQuery("SELECT * FROM Exercises;", null);
		 System.out.println("Hämtat rawQuery");
		 int id = c.getColumnIndex("ExerciseId");
		 int name = c.getColumnIndex("ExerciseName");
		 int pri = c.getColumnIndex("ExercisePri");
		 int sec = c.getColumnIndex("ExerciseSec");
		 String result = "";
		 System.out.println("Innan for loop");

	    	  result = result + c.getString(id) + " " + c.getString(name) + " " + c.getString(pri) +" " + c.getString(sec) + "\n";
	     
		 
		 System.out.println("Efter forloop");
		 return result;
	 }
	 

	public Cursor getMuscleGroups(){
		return ourDatabase.rawQuery("SELECT * FROM MuscleGroups;" ,null);

	}
	
	public Cursor getMusclesByMuscleGroupId(int MuscleGroupId){
		return ourDatabase.rawQuery("SELECT * FROM Muscles WHERE MuscleGroupId = '" + MuscleGroupId + "';", null);
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

	public Cursor getSports(){
		return ourDatabase.rawQuery("SELECT * FROM Sports;", null);
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
