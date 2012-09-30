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
	       
	       int iRow = c.getColumnIndex("ExerciseTypeID");
	       int iName = c.getColumnIndex("ExerciseTypeName");
	       //Forlopp som går igenom hela databasen, alla kolummer
	      // for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
	      // {
	           result = result + c.getString(iRow) + " " + c.getString(iName) + "\n";
	      // }
	       
	       return result;
	   }
	}
	

