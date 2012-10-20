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
package com.Grupp01.gymapp.Controller.History;

/**
 * @author GivDev
 * @version 0.1
 * @peer reviewed by Erik
 * @date 20/10/12
 * This class contains an ExerciseId and the associated ExerciseTypeId.
 */
public class ExerciseIdTypeId {
	
	private int exerciseId;
	private int exerciseTypeId;
	
	/**
	 * Creates new object and sets the instance variables.
	 * @param exerciseId
	 * @param exerciseTypeId
	 */
	public ExerciseIdTypeId(int exerciseId, int exerciseTypeId)
	{
		this.exerciseId = exerciseId;
		this.exerciseTypeId = exerciseTypeId;
	}
	
	/**
	 * Returns id for chosen exercise.
	 * @return Id for an exercise in form of Integer.
	 */
	public int getId()
	{
		return exerciseId;
	}
	
	/**
	 * Returns typeId for chosen exercise.
	 * @return TypeId for an exercise in form of Integer.
	 */
	public int getTypeId()
	{
		return exerciseTypeId;
	}
}
