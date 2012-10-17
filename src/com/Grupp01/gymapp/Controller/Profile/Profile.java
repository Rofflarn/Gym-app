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
package com.Grupp01.gymapp.Controller.Profile;

/** 
 * @author GivDev
 * @version 0.1
 * @peer reviewed by
 * @date dd/mm/yy
 * Class Profile holds data about a user. 
 */

public class Profile {
	
	private String name;
	private String age;
	private double weigth;
	private double length;
	
	/**
	 * Sets up the profile
	 * @param name, age, weigth and length for the profile.
	 */
	public Profile(String name, String age, double weigth, double length)
	{
		this.name = name;
		this.age = age;
		this.weigth = weigth;
		this.length = length;
	}
	
	/**
	 * Returns the name of the profile.
	 * @return the name of the profile.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns the age of the profile.
	 * @return the age of the profile.
	 */
	public String getAge()
	{
		return age;
	}
	
	/**
	 * Returns the weigth of the profile.
	 * @return the weigth of the profile.
	 */
	public double getWeigth()
	{
		return weigth;
	}
	
	/**
	 * Returns the length of the profile.
	 * @return the length of the profile.
	 */
	public double getLength()
	{
		return length;
	}
}