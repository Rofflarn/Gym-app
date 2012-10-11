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

public class History {
	private String workoutName;		//name of workout
	private String date;			//date of workout
	
	
	/**
	 * THis is just a temporary class to use until the we use a class from the database.
	 * The class from the database should look similar but may (should) contain more variables.
	 * Maybe require small amount of changes to the adapter class.
	 * 
	 * @param name Name of workout
	 * @param date The date the workout was performed
	 */
	public History(String name, String date){
		workoutName = name;
		this.date = date;
	}
	
	
	/**
	 * This method will return the name of the workout
	 * 
	 * @return The name of the current workout
	 */
	public String getName(){
		return workoutName;
	}
	
	
	/**
	 * This method will return the date of the performed workout.
	 * 
	 * @return The date the workout was performed.
	 */
	public String getDate(){
		return date;
	}

}
