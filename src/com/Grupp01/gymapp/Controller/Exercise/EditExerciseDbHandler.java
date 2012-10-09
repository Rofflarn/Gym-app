package com.Grupp01.gymapp.Controller.Exercise;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.Grupp01.gymapp.Controller.IdName;
import com.Grupp01.gymapp.Model.Database;

public class EditExerciseDbHandler extends Database {
	
	
		public EditExerciseDbHandler(Context c, int id)
		{
			super(c);
		}
		
		public ExerciseData getExercise(int exerciseId)
		{
			open();
			Cursor c = ourDatabase.rawQuery("SELECT * FROM Exercises WHERE ExerciseId= '" + exerciseId + "';", null);
			c.moveToFirst();
			int id = c.getColumnIndex("ExerciseId");
			int pri = c.getColumnIndex("ExercisePri");
			int sec = c.getColumnIndex("ExerciseSec");
			int name = c.getColumnIndex("ExerciseName");
			int note = c.getColumnIndex("ExerciseDesc");
			int desc = c.getColumnIndex("ExerciseNote");
			int type = c.getColumnIndex("ExerciseTypeId");
			ExerciseData temp = new ExerciseData(c.getInt(id), c.getInt(pri), c.getInt(sec), c.getString(name), c.getString(note), c.getString(desc), c.getInt(type));
			c.close();
			close();
			return temp;
		}
		

}
