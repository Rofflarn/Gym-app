/*This file is part of Gymapp.
*
*   Gymapp is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   Gymapp is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*  along with Gymapp.  If not, see <http://www.gnu.org/licenses/>.
*
*/
package com.Grupp01.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.Grupp01.gymapp.Model.Database;
import com.Grupp01.gymapp.Model.SQLView;
import com.Grupp01.gymapp.View.Exercise.ListExerciseActivity;
import com.Grupp01.gymapp.View.Workout.ListWorkoutActivity;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

public class MainActivity extends SherlockActivity {
	
	public Database databasen = new Database(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databasen.open();
    }
    //Kommentar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
    	inflater.inflate(R.menu.activity_main, menu);
    	
        return true;
    }
    
    public void workout(View view)
    {
    	Intent workout = new Intent(this, ListWorkoutActivity.class);
    	startActivity(workout);
    }
    
    public void historik(View view)
    {
    	Intent historik = new Intent(this, Historik.class);
    	startActivity(historik);
    }
    
    public void statistik(View view)
    {
    	Intent sqlview = new Intent(this, SQLView.class);
    	startActivity(sqlview);
    }
    
    public void exercise(View view)
    {
    	databasen.close();
    	Intent exercise = new Intent(this, ListExerciseActivity.class);
    	startActivity(exercise);
    }
}
