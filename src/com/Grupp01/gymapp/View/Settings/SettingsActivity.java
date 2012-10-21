/* Copyright © 2012 GivDev
 * 
 * 	This file is part of Gymapp.
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
package com.Grupp01.gymapp.View.Settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.R;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * This method will set up the layout of the settings screen and
 * let the user change these settings.
 * <p>This class is a part of the </p><i>View</i><p> package, and a part of the </p><i>Settings</i>
 * <p> Subpackage</p> 
 * 
 * @author GivDev
 * @version 0.1
 * @peer by Robert Blomberg
 * @date 20/10/12
 */
public class SettingsActivity extends SherlockPreferenceActivity{


	/**
	 * This method is called on start and will set the preference xml layout to
	 * this activity.
	 *
	 */
	@SuppressWarnings("deprecation")
	/*The method "addPreferencesFromResource" is a deprecated method, however Googles developer 
	 * information regarding PreferenceActivity states that you should use this method when inflating
	 * the xml view for a settings/preferences activity.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Inflate the view of the preferences layout.
		addPreferencesFromResource(R.xml.preferences);

	}


	/**
	 * Build the menu in the actionbar.
	 * @menu The menu item to be used.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Set layout of actionbar
		getSupportMenuInflater().inflate(R.menu.activity_settings, menu);

		//Enable the app icon to be clickable
		getSupportActionBar().setHomeButtonEnabled(true);
		return true;
	}

	/**
	 * Set up actions for buttons in actionbar
	 * @param MenuItem item - The menuitem thas has been pressed
	 * 
	 */
	@Override
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

			//when clicking "About" a dialog pops up with input for the name
		case	R.id.menu_about:
			showAboutDialog();
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * This method will show an "about" dialog.
	 * 
	 */
	private void showAboutDialog() {

		//Create a new dialog
		AlertDialog.Builder aDialog = new AlertDialog.Builder(this);

		//Set the title to display "About <ApplicationName>
		aDialog.setTitle(R.string.app_name);

		//Set the message to show information about this application
		aDialog.setMessage(R.string.about_descr);

		//Set up the only button for the dialog which will dismiss the dialog
		aDialog.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener(){

			/**When clicking ok, the dialog will be closed.
			 * 
			 */
			public void onClick(DialogInterface d, int arg0){
				d.dismiss();
			}
		});

		//Show the dialog
		aDialog.show();
	}

}
