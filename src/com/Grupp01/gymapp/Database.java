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
			db.execSQL("CREATE TABLE " + "Exercises" + " (" + "ExceriseId" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
		               + "ExcersiePri")
		}
		
		
	}

}
