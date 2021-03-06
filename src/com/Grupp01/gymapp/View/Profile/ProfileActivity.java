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
*			Copyright � 2012 GivDev
*/
package com.Grupp01.gymapp.View.Profile;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.Grupp01.gymapp.R;
import com.Grupp01.gymapp.Controller.Profile.Profile;
import com.Grupp01.gymapp.Controller.Profile.ProfileDbHandler;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

/** 
 * @author GivDev
 * @version 0.1
 * @peer reviewed by Erik
 * @date 20/10/12
 *
 * Class ProfileActivity starts when the user pushs Profile button from the start menu. 
 */

public class ProfileActivity extends SherlockActivity {

	/**
     * Sets the layout for the activity.
     * 
     * @param savedInstanceState
     */
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        //Makes the keybord hidden when the activity starts
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //In a later version it should find the user and set the textfiels whit the information
        searchUser();
    }

	/**
     * Sets the menu for the activity.
     * 
     * @param meny
     * @return true = false =
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getSupportMenuInflater();
    	inflater.inflate(R.menu.universal_menu, menu);
        return true;
    }
    
    /**
     * Finds the user in the database and sets the textfields with the information.
     */
	public void searchUser()
	{
	}
    
    /**
     * When the user commits the changes by pressing the button this method is called.
     * 
     * @param view
     */
    public void updateProfile(View view)
    {
    	//Gets the text from all textfields
    	String name = ((EditText)findViewById(R.id.name)).getText().toString();
    	String age = ((EditText)findViewById(R.id.age)).getText().toString();
    	String length = ((EditText)findViewById(R.id.length)).getText().toString();
    	String weight = ((EditText)findViewById(R.id.weight)).getText().toString();
    	//Checks so that textfiels not are empty
    	if(name.length() == 0 || age.length() == 0 || length.length() == 0 || weight.length() == 0)
    	{
    		//Toast is shown if any field is empty
    		Toast.makeText(this, "Fill in correct information", Toast.LENGTH_SHORT).show();
    	}
    	else
    	{
    		//Makes connection to database
    		ProfileDbHandler dbHandler = new ProfileDbHandler(this);
    		dbHandler.open();
    		//Creates a profile object with the input information
    		Profile profile = new Profile(name, age,
    				Double.parseDouble(weight), Double.parseDouble(length));
    		//Writes to the database
    		dbHandler.addUser(profile);
    		dbHandler.close();
    		finish();
    	}
    }
    
    /**
     * When the user pushs cancel.
     * 
     * @param view
     */
    public void cancel(View view)
    {
    	//Return to main activity
    	finish();
    }
}