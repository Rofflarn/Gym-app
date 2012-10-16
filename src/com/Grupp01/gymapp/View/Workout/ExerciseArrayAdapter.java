/*Copyright © 2012 GivDev
 *
 * This file is part of Gymapp.
 *
 * Gymapp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Gymapp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Gymapp. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.Grupp01.gymapp.View.Workout;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.Grupp01.gymapp.R;
/**@author GivDev
 * @version 0.1
 * @peer reviewed by
 * @date 04/10/12
 *
 * Custom adapter for displaying an array of Exercise objects.
 *
 * <p>This class i a part of the </p><i>View</i><p> package, and a part of the </p><i>Workout</i>
 * <p> Subpackage</p>
 * */
public class ExerciseArrayAdapter extends ArrayAdapter<ExerciseListElementData>
{

	private LayoutInflater inflater;

	public ExerciseArrayAdapter( Context context, List<ExerciseListElementData> exerciseList )
	{
		super( context, R.layout.editworkoutrow, R.id.rowTextView, exerciseList );
		// Cache the LayoutInflate to avoid asking for a new one each time.
		inflater = LayoutInflater.from(context);
	}


	/**Since we display other things than a TextView we need to Override a getView-method.
	 * This method is the one that got the focus on the screen and is returning the View
	 * when the user clicks on a exercise.
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return View Returns the edited object you clicked on,
	 * */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// Exercise to display
		ExerciseListElementData exercise = (ExerciseListElementData) this.getItem( position );

		//What each view contains.
		CheckBox checkBox;
		TextView textView;

		//If the view doesn't exist, we create a new row view. Initially
		//the screen is filled up, when the screen is full the convertview
		// is not null and will there after go to the else-statement
		if ( convertView == null)
		{
			convertView = inflater.inflate(R.layout.editworkoutrow, null);

			// Find the child views.
			textView = (TextView) convertView.findViewById( R.id.rowTextView );
			checkBox = (CheckBox) convertView.findViewById( R.id.CheckBox01 );

			// Optimization: Set a tag with it's child views, so we don't have to
			// call findViewById() later when we reuse the row.
			convertView.setTag( new ExerciseViewHolder(textView,checkBox) );

			// If CheckBox is toggled, update the Exercise it is tagged with.
			checkBox.setOnClickListener( new View.OnClickListener()
			{
				/** When the user click on a checkbox, the checkbox
				 * for the exercise is getting unchecked or checked*/
				public void onClick(View v)
				{
					//Get the checkbox clicked as a variable
					CheckBox checkBox = (CheckBox) v;
					//Get the exercise for the checkbox that was clicked
					ExerciseListElementData exercise = (ExerciseListElementData) checkBox.getTag();
					//Set the exercise to checked or unchecked
					exercise.setChecked( checkBox.isChecked() );
				}
			});
		}
		//Is used when the user are scrolling down or up to list existing views but now the
		//the elements in the highest view with another element if the user scroll up or
		//the lowest view if the user scroll down.
		else
		{
			// Because we use a ViewHolder, we avoid having to call findViewById()
			// multiple times.
			//Get the view that should be replaced
			ExerciseViewHolder viewHolder = (ExerciseViewHolder) convertView.getTag();
			//Set a new checkbox to the view
			checkBox = viewHolder.getCheckBox();
			//Set a new name to the view
			textView = viewHolder.getTextView();
		}

		// Tag the CheckBox with the Exercise it is displaying, so that we can
		// access the Exercise in onClick() when the CheckBox is clicked.
		checkBox.setTag( exercise );

		// Display Exercise
		checkBox.setChecked( exercise.isChecked() );
		textView.setText( exercise.getName() );

		return convertView;
	}//End of getView

}