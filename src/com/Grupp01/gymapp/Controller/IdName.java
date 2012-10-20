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
package com.Grupp01.gymapp.Controller;

/**
 * @author GivDev
 * @version 0.1
 * @peer reviewed 
 * @date 
 * This class is used for containing an id as an int and a name as a string.
 */
public class IdName {

	private String name;
	private int id;

	/**
	 * 
	 * @param id Sets the id of the object
	 * @param name Sets the name of the object
	 */
	public IdName(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	/**
	 * Access method for getting name.
	 * @return the name of the object.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Access method for getting id.
	 * @return the id of the object.
	 */
	public int getId()
	{
		return id;
	}
}
