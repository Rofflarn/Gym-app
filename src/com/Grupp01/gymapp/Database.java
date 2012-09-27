package com.Grupp01.gymapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {
	
	private static final String DATABASE_NAME = "GymAppDatabase"; 
	private static final int DATABASE_VERSION = 1;
	
	
	private static class DbHelper extends SQLiteOpenHelper{
		
		public DbHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			createExercises(db);
			createMeasureDataTypes(db);
			createExerciseTypes(db);
			createPasses(db);
			createMeasures(db);
			createMeasureData(db);			 
		}
		
		private void createExercises(SQLiteDatabase db)
		{
			db.execSQL("CREATE TABLE Exercises (ExceriseId INTEGER PRIMARY KEY AUTOINCREMENT, ExercisePri INTEGER, ExerciseSec INTEGER," +
					" ExerciseName TEXT NOT NULL, ExerciseDesc TEXT, ExersiceNote Text, ExerciseSportId INTEGER, ExerciseTypeId INTEGER NOT NULL);");	
		}
		
		private void createExerciseTypes(SQLiteDatabase db)
		{
			db.execSQL("CREATE TABLE ExerciseTypes (ExerciseTypeId INTEGER PRIMARY KEY AUTOINCREMENT, ExerciseTypeName TEXT);");
		}
		
		private void createMeasureDataTypes(SQLiteDatabase db)
		{
			db.execSQL("CREATE TABLE MeasureDataTypes (MeasureDataTypeId INTEGER PRIMARY KEY AUTOINCREMENT, MeasureDataTypeName Text);");
		}
		
		private void createMeasureData(SQLiteDatabase db)
		{
			db.execSQL("CREATE TABLE MeasureData (MeasureDataId INTEGER PRIMARY KEY AUTOINCREMENT, MeasureData REAL NOT NULL, " +
						"MeasureDataTypeId INTEGER NOT NULL, MeasureId INTEGER NOT NULL);");
		}
				
		private void createPasses(SQLiteDatabase db)
		{
			db.execSQL("CREATE TABLE Passes (PassID INTEGER PRIMARY KEY AUTOINCREMENT, PassTime NUMERIC NOT NULL, Passname TEXT, UserId INTEGER NOT NULL);");
		}
		
		private void createMeasures(SQLiteDatabase db)
		{
			db.execSQL("CREATE TABLE Measures (MeasureId INTEGER PRIMARY KEY AUTONINCREMENT, MeasureData NUMERIC NOT NULL, UserId INTEGER NOT NULL);");
		}
	}

}
