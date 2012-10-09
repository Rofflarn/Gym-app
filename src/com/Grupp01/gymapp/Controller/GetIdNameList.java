package com.Grupp01.gymapp.Controller;
//givdev
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;

import com.Grupp01.gymapp.Model.Database;


public class GetIdNameList extends Activity {
	
	private static final String DATABASE_NAME = "GymAppDatabase"; 
	private static final int DATABASE_VERSION = 1;
	private Database databas;
	
	public GetIdNameList(Context c)
	{
		databas = new Database(c);
	}
	
	
	public List getExerciseIdName()
	{
		databas.open();
		Cursor c = databas.getExercises();
			
		
		List<IdName> idNameList = new LinkedList<IdName>();

		int id = c.getColumnIndex("ExerciseId");
		int name = c.getColumnIndex("ExerciseName");
		//Forlopp som g�r igenom hela databasen, alla kolummer
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			idNameList.add(new IdName(c.getInt(0),c.getString(3)));
			System.out.println(c.getInt(0) + " " + c.getString(3));
		}
		System.out.println("After Database");
		c.close();
		databas.close();
		System.out.println("Closed Database");
		return idNameList;
	}
}