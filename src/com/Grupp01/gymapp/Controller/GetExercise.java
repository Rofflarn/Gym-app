package com.Grupp01.gymapp.Controller;

import android.content.Context;
import android.database.Cursor;

import com.Grupp01.gymapp.Model.Database;

public class GetExercise {
	
		private Database databasen;
		private int id;
	
		public GetExercise(Context c, int id)
		{
			databasen = new Database(c);
			this.id = id;
		}
		
		public Exercise getExercise()
		{
			databasen.open();
			Cursor c = databasen.getExerciseById(id);
			c.moveToFirst();
			int id = c.getColumnIndex("ExerciseId");
			int pri = c.getColumnIndex("ExercisePri");
			int sec = c.getColumnIndex("ExerciseSec");
			int name = c.getColumnIndex("ExerciseName");
			int note = c.getColumnIndex("ExerciseDesc");
			int desc = c.getColumnIndex("ExerciseNote");
			int type = c.getColumnIndex("ExerciseTypeId");
			Exercise temp = new Exercise(c.getInt(id), c.getInt(pri), c.getInt(sec), c.getString(name), c.getString(note), c.getString(desc), c.getInt(type));
			c.close();
			databasen.close();
			return temp;
		}

}
