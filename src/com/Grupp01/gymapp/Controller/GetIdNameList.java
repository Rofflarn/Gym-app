package com.Grupp01.gymapp.Controller;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;

import com.Grupp01.gymapp.Model.Database;


public class GetIdNameList extends Activity {
	
	private static final String DATABASE_NAME = "GymAppDatabase"; 
	private static final int DATABASE_VERSION = 1;
	private Database databas;
	
	public GetIdNameList()
	{	
		databas = new Database(getApplicationContext());
	}
	
	
	public List getExerciseIdName()
	{
		
		System.out.println("Before open Database");
		databas.open();
		System.out.println("Opened Database");
		Cursor c = databas.getExercises();
		databas.close();
		System.out.println("Closed Database");
		List<IdName> idNameList = new LinkedList<IdName>();

		int id = c.getColumnIndex("ExerciseId");
		int name = c.getColumnIndex("ExerciseName");
		//Forlopp som går igenom hela databasen, alla kolummer
		System.out.println("Before forloop");
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			idNameList.add(new IdName(c.getInt(id),c.getString(name)));
		}
		System.out.println("After Database");
		return idNameList;
	}

}
