package com.Grupp01.gymapp.Controller.Exercise;
//givdev
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.Grupp01.gymapp.Controller.IdName;
import com.Grupp01.gymapp.Model.Database;


public class ListExerciseDbHandler extends Database {
	
	
	
	public ListExerciseDbHandler(Context c)
	{
		super(c);
	}
	
	
	public List<IdName> getExerciseIdName()
	{
		open();
		Cursor c = ourDatabase.rawQuery("SELECT ExerciseId, ExerciseName FROM Exercises;", null);
		List<IdName> idNameList = new LinkedList<IdName>();
		c.moveToFirst();
		int id = c.getColumnIndex("ExerciseId");
		int name = c.getColumnIndex("ExerciseName");
		//Forlopp som går igenom hela databasen, alla kolummer
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			idNameList.add(new IdName(c.getInt(id),c.getString(name)));
		}
		c.close();
		close();
		return idNameList;
	}
	

	/*public long addExercise(ExerciseData exerciseData)
	{
		ContentValues values = new ContentValues();
	    values.put("ExerciseName", exerciseData.getName());
		/*ourDatabase.execSQL("INSERT INTO Exercises (ExerciseName) VALUES ('" + exerciseData.getName() + "');");
		Cursor c = ourDatabase.rawQuery("SELECT last_insert_rowid();", null);
		c.moveToFirst();
		int id = c.getInt(0);
	    return ourDatabase.insert("Exercises", null, values);
		/*System.out.println(id);
		return id;
	}*/
	
	/**
	 * Adds new Exercise to database.
	 * @param name
	 * @return
	 */
	public int addExercise(String name)
	{
		open();
		ContentValues values = new ContentValues();
	    values.put("ExerciseName", name);
		int id = (int) ourDatabase.insert("Exercises", null, values);
		close();
		return id;
	}
	
	public void editExercise(ExerciseData exerciseData)
	{
		open();
		//int id, int exercisePri, int exerciseSec, String exerciseName, String exerciseDesc, String exerciseNote, int sportId, int exerciseTypeId)
		//ExerciseData test = new ExerciseData(1, 2, 3, "Testing", "EditedD2325esc", "EditedN234ote", 1, 2);
		//exerciseData = test;
		//ourDatabase.update(Exercises, values, whereClause, whereArgs)
		String tmp = "UPDATE Exercises (ExerciseMusclePri, ExerciseMuscleSec, ExerciseDesc, ExerciseNote, ExerciseSportId, ExerciseTypeId) " +
				"VALUES ('" + exerciseData.getPri() + "', '" + exerciseData.getSec() + "', '" + exerciseData.getDesc() + "', '" +
				"" + exerciseData.getNote() + "', '" + exerciseData.getSportId() + "', '" + exerciseData.getTypeId() + "') " +
				"WHERE ExerciseId = '" + exerciseData.getId() + "';";
		String tmp2 = "UPDATE Exercises SET ExercisePri = '" + exerciseData.getPri() + "', ExerciseSec = '" + exerciseData.getSec() + "', ExerciseDesc = '" + exerciseData.getDesc() + "', ExerciseNote = '" + exerciseData.getNote() + "', ExerciseSportId = '" + exerciseData.getSportId() + "', ExerciseTypeId = '" + exerciseData.getTypeId() + "' WHERE ExerciseId = '" + exerciseData.getId() + "';";
		System.out.println(tmp2);
				
		ourDatabase.execSQL(tmp2);
		close();
	}
	
	public int getExerciseIdFromName(String name)
	{
		open();
		Cursor c = ourDatabase.rawQuery("SELECT ExerciseId FROM Exercises WHERE ExerciseName= '" + name +"';", null);
		c.moveToFirst();
		int id = c.getInt(c.getColumnIndex("ExerciseId"));
		c.close();
		close();
		return id;
	}

}
