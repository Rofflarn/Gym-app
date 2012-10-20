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
package com.Grupp01.gymapp.Controller.Exercise;

/**
 * @author GivDev
 * @version 0.1
 * @peer reviewed by Erik
 * @date 20/10/12
 * This class contains the data that is stored in an exercise object. The instance variables is
 * identical to the fields in the Exercises table in the database.
 */
public class ExerciseData {

	private int id;
	private int pri;
	private int sec;
	private String name;
	private String desc;
	private String note;
	private int sportId;
	private int typeId;

	/**
	 * Create new ExerciseData object and set all the instance variables.
	 * @param id This corresponds to the ExerciseId field in the table Exercises.
	 * @param exercisePri This corresponds to the ExercisePr field in the table Exercises.
	 * @param exerciseSec This corresponds to the ExerciseSec field in the table Exercises.
	 * @param exerciseName This corresponds to the ExerciseName field in the table Exercises.
	 * @param exerciseDesc This corresponds to the ExerciseDesc field in the table Exercises.
	 * @param exerciseNote This corresponds to the ExerciseNote field in the table Exercises.
	 * @param sportId This corresponds to the ExerciseSportId field in the table Exercises.
	 * @param exerciseTypeId This corresponds to the ExerciseTypeId field in the table Exercises.
	 */
	public ExerciseData(int id, int exercisePri, int exerciseSec, String exerciseName, 
			String exerciseDesc, String exerciseNote, int sportId, int exerciseTypeId)
	{
		this.id = id;
		this.pri = exercisePri;
		this.sec = exerciseSec;
		this.name = exerciseName;
		this.desc = exerciseDesc;
		this.note = exerciseNote;
		this.sportId = sportId;
		this.typeId = exerciseTypeId;
	}

	/**
	 * Create new ExerciseData object and set the minimum required instance variables.
	  * @param id This corresponds to the ExerciseId field in the table Exercises.
	  * @param exerciseName This corresponds to the ExerciseName field in the table Exercises.
	  * @param exerciseTypeId This corresponds to the ExerciseTypeId field in the table Exercises.
	  */
	public ExerciseData(int id, String exerciseName, int exerciseTypeId)
	{
		this.id = id;
		this.name = exerciseName;
		this.typeId = exerciseTypeId;
	}

	/**
	 * Gets the ExerciseId.
	 * @return ExerciseId.
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Gets the ExercisePri (Muscle).
	 * @return ExercisePri.
	 */
	public int getPri()
	{
		return pri;
	}

	/**
	 * Gets the ExerciseSec (Muscle).
	 * @return ExerciseSec.
	 */
	public int getSec()
	{
		return sec;
	}

	/**
	 * Gets the ExerciseName.
	 * @return ExerciseName.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Gets the ExerciseDesc.
	 * @return ExerciseDesc.
	 */
	public String getDesc()
	{
		return desc;
	}

	/**
	 * Gets the ExerciseNote.
	 * @return ExerciseNote.
	 */
	public String getNote()
	{
		return note;
	}

	/**
	 * Gets the ExerciseSportId.
	 * @return ExerciseSportId.
	 */
	public int getSportId()
	{
		return sportId;
	}

	/**
	 * Gets the ExerciseTypeId.
	 * @return ExerciseTypeId.
	 */
	public int getTypeId()
	{
		return typeId;
	}

	/**
	 * Sets the ExerciseId.
	 * @param id ExerciseId.
	 */
	public void putId(int id)
	{
		this.id = id;
	}

	/**
	 * Sets the ExercisePri (Muscle).
	 * @param id ExercisePri.
	 */
	public void putPri(int pri)
	{
		this.pri = pri;
	}

	/**
	 * Sets the ExerciseSec (Muscle).
	 * @param id ExerciseSec.
	 */
	public void putSec(int sec)
	{
		this.sec = sec;
	}

	/**
	 * Sets the ExerciseName.
	 * @param id ExerciseName.
	 */
	public void putName(String name)
	{
		this.name = name;
	}

	/**
	 * Sets the ExerciseDesc.
	 * @param id ExerciseDesc.
	 */
	public void putDesc(String desc)
	{
		this.desc = desc;
	}

	/**
	 * Sets the ExerciseSportId.
	 * @param id ExerciseSportId.
	 */
	public void putSport(int sportId)
	{
		this.sportId = sportId;
	}

	/**
	 * Sets the ExerciseNote.
	 * @param id ExerciseNote.
	 */
	public void putNote(String note)
	{
		this.note = note;
	}

	/**
	 * Sets the ExerciseTypeId.
	 * @param id ExerciseTypeId.
	 */
	public void putTypeId(int typeId)
	{
		this.typeId = typeId;
	}
}
