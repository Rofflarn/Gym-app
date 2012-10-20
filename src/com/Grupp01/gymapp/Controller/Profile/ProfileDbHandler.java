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


import android.content.Context;
import android.database.Cursor;

import com.Grupp01.gymapp.Model.Database;

/** 
 * @author GivDev
 * @version 0.1
 * @peer reviewed by Robert Blomberg
 * @date 20/10/12
 *
 * Class ProfileDbHandler are a help class for ProfileActivity to write to the database. 
 */

public class ProfileDbHandler extends Database {
	
	/**
	 * Sets up profileDbHandler
	 * @param context from the profile activity
	 */
	public ProfileDbHandler(Context c)
	{
		super(c);
	}
	
	/**
	 * Adds a new user to the database
	 * @param profile contains name, age, weigth and length for the user
	 */
	public void addUser(Profile profile)
	{
		open();
		//Writes the input information to Users table
		ourDatabase.execSQL("INSERT INTO Users (UserName, UserBirthday, UserInitialWeight, UserHeight ) VALUES ('" + profile.getName() + "', '" + profile.getAge() + "', "
				+ profile.getWeigth() + ", " + profile.getLength() + ");");
		close();
	}
	
	/**
	 * This method return the number of Users in the table Users. This is only used for
	 * testing purposes.
	 * @return The number of users.
	 */
	public int numberOfUsers()
	{
		open();
		Cursor c = ourDatabase.rawQuery("SELECT COUNT(*) FROM Users;", null);
		c.moveToFirst();
		int count = c.getInt(0);
		close();
		return count;
	}
}
