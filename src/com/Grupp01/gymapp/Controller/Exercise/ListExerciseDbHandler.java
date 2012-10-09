package com.Grupp01.gymapp.Controller.Exercise;
//givdev
import java.util.LinkedList;
import java.util.List;

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
	

	public void addExercise(ExerciseData exerciseData)
	{
		ourDatabase.execSQL("INSERT INTO Exercises (ExerciseName) VALUES ('" + exerciseData.getName() + "');");
	}

}
