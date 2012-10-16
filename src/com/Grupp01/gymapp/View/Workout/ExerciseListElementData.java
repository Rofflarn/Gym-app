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
package com.Grupp01.gymapp.View.Workout;

/**@author GivDev
 * @version 0.1
 * @peer reviewed by
 * @date 04/10/12
 *
 * Holds Exercise data.
 * 
 * <p>This class i a part of the </p><i>View</i><p> package, and a part of the </p><i>Workout</i>
 * <p> Subpackage</p> 
 *  */	
public class ExerciseListElementData 
{  
	private int id;
	private String name = "" ;  
	private boolean checked = false ;

	/**The constructor for the ExerciseListElementData
	 * 
	 * @param id		id of the exercise in the database
	 * @param name		the name of the workout
	 * @param checked	is it checked or not
	 */
	public ExerciseListElementData( int id, String name, boolean checked )
	{  
		this.id = id;
		this.name = name ;  
		this.checked = checked ;  
	}
	/** Get the name of the exercise as a int
	 * @return string*/
	public int getId()
	{
		return id;
	}

	/** Get the name of the exercise into a string
	 * @return string*/
	public String getName() 
	{  
		return name;  
	}
	/** Check if the exercise is checked*/
	public boolean isChecked() 
	{  
		return checked;  
	}
	/** Sets the exercise to the boolean parameter
	 * @param checked*/
	public void setChecked(boolean checked) 
	{  
		this.checked = checked;  
	}
	/** Invert the boolean value of the variable checked*/
	public void toogleChecked()
	{  
		checked = !checked ;  
	}
	/** Set the id of the exercise
	 * @return string*/
	public void setId(int id)
	{
		this.id = id;
	}
}  
