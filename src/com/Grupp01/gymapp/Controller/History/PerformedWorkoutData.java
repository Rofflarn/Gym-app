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

package com.Grupp01.gymapp.Controller.History;

public class PerformedWorkoutData {
	private String workoutName;		//name of workout
	private String date;	//date of workout
	private int id;


	/**
	 * This class is used to get data from database and put that data in
	 * a usable object. Is used in HistoryDbHandler and ListHistoryAcitivy.
	 * @param id Id for the workout.
	 * @param date Date and time when the workout was performed.
	 * @param name Name of the workout.
	 */
	public PerformedWorkoutData(int id, String date, String name){
		this.workoutName = name;
		this.date = date;
		this.id = id;
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

	/**
	 * This method will return the id of the performed workout.
	 * @return The id of the workout.
	 */
	public int getId(){
		return id;
	}

}
