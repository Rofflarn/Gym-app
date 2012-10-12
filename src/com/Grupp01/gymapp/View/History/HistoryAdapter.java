/*	Copyright © 2012 GivDev
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

import java.util.LinkedList;
import java.util.List;
import com.Grupp01.gymapp.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/**
 * This is a custom adapter to be used with a History object to display
 * vital information in the view of "History" and the subactivity/activities.
 * 
 * @author GivDev
 * @version 0.1
 * @peer by Robert Blomberg
 * @date 
 *
 */
public class HistoryAdapter extends ArrayAdapter<History>{
	
	//This is our list of history types
	private LinkedList<History> historyList;
	
	/**
	 * Constructor of HistoryAdapter
	 * 
	 * @param context The current context.
	 * @param textViewResourceId	The id for the layout for the listview
	 * @param objects	The list with History objects which will be displayed in the view. 
	 */
	public HistoryAdapter(Context context, int textViewResourceId,
			List<History> objects) {
		super(context, textViewResourceId, objects);
		
		//Set the list to be the same as the passed one (the list which is passed on from calling activity)
		historyList = (LinkedList<History>) objects;
	}
	
	
	/**
	 * 
	 * This method will insert the object of the list into the ListView and set the correct
	 * text for the TextViews which are placed in the view.
	 * 
	 * @param	position The current position in the list
	 * @param	convertView The view to use
	 * @param	parent The parent the view will return to.
	 * @return 	v The resulting view
	 *
	 */
	public View getView(int position, View convertView, ViewGroup parent){
		
		//Get the view
		View v = convertView;
		
		//If the view is null then inflate the correct view (the layout for the ListView)
		if(v == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.history_list_layout, null);
		}
		
		//Get the History object at the current position (as we iterate through the linked list
		History h = historyList.get(position);
		if(h != null){
			
			//Update the textview in the list that holds the name.
			TextView name = (TextView) v.findViewById(R.id.historyName);
			name.setText(h.getName());
			
			//Update the date in the list
			TextView date = (TextView) v.findViewById(R.id.historyDate);
			date.setText(h.getDate());
			
		}
		//Return the resulting view.
		return v;
	}

	
	
}
