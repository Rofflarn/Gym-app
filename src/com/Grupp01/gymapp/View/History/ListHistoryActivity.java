/*	Copyright � 2012 GivDev
 * This file is part of Gymapp.
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
*/
package com.Grupp01.gymapp.View.History;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.R;
import com.Grupp01.gymapp.Controller.History.HistoryDbHandler;
import com.Grupp01.gymapp.Controller.History.PerformedWorkoutData;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;



/**
 * This activity will list all performed workouts with name and date.
 * By pressing a workout a new activity will start and show performed exercises
 * for that workout.
 * 
 * @author GivDev
 * @version 0.1
 * @peer by Robert Blomberg
 * @date 20/10/12
 *
 */
public class ListHistoryActivity extends SherlockListActivity {
	//Message to pass via intent that the putExtra contains an history id.
	public static final String HISTORY_ID = "HISTORY.ID";
	
	/**
	 * Is called on start, set the correct layout from xml file and then create the list.
	 * 
	 */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_history);
        createList();       
    }


	/**
	 * This method will fetch data from the database in form a LinkedList, then pass
	 * this list on to our custom adapter to build a listview out of data from database.
	 * 
	 */
    private void createList() {
    	
    	//Creates connection to Database
    	HistoryDbHandler dbHandler = new HistoryDbHandler(this);

    	//The list with History objects, get the list from the database.
    	List<PerformedWorkoutData> hList = dbHandler.getPerformedWorkoutsList();

		//Set up the adapter we will use to adapt the layout.
		HistoryAdapter hAdapter = new HistoryAdapter(this, R.layout.history_list_layout, hList);
		setListAdapter(hAdapter);
	}

    
    /**
     * Set up the layout of actionbar
     * @param menu The menu to pass the layout to.
     * @return true to set the menu to be visible.
     */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	//Set the layout xml 
		getSupportMenuInflater().inflate(R.menu.activity_list_history, menu);

		//Set the home button in actionbar to be clickable so the user can return to 
		//main menu by pressing it.
    	getSupportActionBar().setHomeButtonEnabled(true);
    	
    	//Return true, otherwise the menu will not be visible.
        return true;
    }


	/**
	 * This method will be called when the user presses an item in the ListView of history objects.
	 * The method will check what position was clicked, check the object in LinkedList for that position
	 * and start a new activity which will display exercises for that History object.
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		
	}


	/**
     * Set up actions for buttons in actionbar
     * @param MenuItem item - The menuitem thas has been pressed
     * 
     */
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
    		//Make app icon navigate back to the applications start screen.
    		case	android.R.id.home:
    			Intent intent = new Intent(this, MainActivity.class);
    			
    			//Remove "newer" activities from the stack.
    			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    			
    			//Start activity
    			startActivity(intent);
    			return true;		
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    }
}