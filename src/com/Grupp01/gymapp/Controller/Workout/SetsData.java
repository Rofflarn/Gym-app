/*Copyright © 2012 GivDev
 * 
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
 *
 */

package com.Grupp01.gymapp.Controller.Workout;

public class SetsData {

	private int min;
	private int sec;
	private int distance;
	private int weight;
	private int reps;
	private int workoutId;
	private int exerciseId;
	private String duration;


	/**
	 * Construcs a SetData.
	 * with:
	 * @param min
	 * @param sec
	 * @param dist
	 * @param workoutId
	 * @param exerciseId
	 */
	public SetsData(int min, int sec,int dist, int workoutId, int exerciseId)
	{
		this.min = min;
		this.sec = sec;
		this.distance = dist;
		this.workoutId = workoutId;
		this.exerciseId = exerciseId;
	}

	/**
	 * Constructs a SetData
	 * wit:
	 * @param duration
	 * @param distance
	 */
	public SetsData(String duration, int distance)
	{
		this.duration = duration;
		this.distance = distance;
	}

	/**
	 * Constructs a SetData
	 * with:
	 * @param weight
	 * @param duration
	 */
	public SetsData(int weight, String duration)
	{
		this.duration = duration;
		this.weight = weight;
	}

	/**
	 * Constructs a SetDat
	 * with:
	 * @param weight
	 * @param reps
	 * @param workoutId
	 * @param exerciseId
	 */
	public SetsData(int weight, int reps, int workoutId, int exerciseId)
	{
		this.weight = weight;
		this.reps = reps;
		this.workoutId = workoutId;
		this.exerciseId = exerciseId;
	}

	/**
	 * Constructs a SetData
	 * with:
	 * @param weight
	 * @param reps
	 */
	public SetsData(int weight, int reps)
	{
		this.weight = weight;
		this.reps = reps;
	}

	/**
	 * Construcs a SetData
	 * with:
	 * @param min
	 * @param sec
	 * @param weight
	 */
	public SetsData(int min, int sec, int weight)
	{
		this.min = min;
		this.sec = sec;
		this.weight = weight;
	}

	/**
	 * 
	 * @return number of minutes that the set has taken.
	 */
	public int getMin()
	{
		return min;
	}

	/**
	 * 
	 * @return number of seconds that the set has taken.
	 */
	public int getSec()
	{
		return sec;
	}

	/**
	 * 
	 * @return The distance that in the set.
	 */
	public int getDistance()
	{
		return distance;
	}

	/**
	 * 
	 * @return current weight in the set.
	 */
	public int getWeight()
	{
		return weight;
	}

	/**
	 * 
	 * @return how many reps that are in the set.
	 */
	public int getReps()
	{
		return reps;
	}

	/**
	 * 
	 * @return workoutId that the current exercise are in. 
	 */
	public int getworkoutId()
	{
		return workoutId;
	}

	/**
	 * 
	 * @return the id for current exercise.
	 */
	public int getexerciseid()
	{
		return exerciseId;
	}

	/**
	 * 
	 * @return the duration of the set, in format: hh:mm:ss
	 */
	public String getDuration()
	{
		return duration;
	}


}
