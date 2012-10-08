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
			Exercise temp = new Exercise(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3), c.getString(4), c.getString(5), c.getInt(6));
			c.close();
			databasen.close();
			return temp;
		}

}
