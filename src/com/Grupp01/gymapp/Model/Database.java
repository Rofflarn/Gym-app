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

package com.Grupp01.gymapp.Model;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author GivDev
 * @version 0.1
 * @peer reviewed 
 * @date 
 * Main database-class. Contains methods for creating the database and accessing data.
 */
public class Database {
	private DbHelper ourHelper;
	private final Context ourContext;
	protected SQLiteDatabase ourDatabase;

	/**
	 * This method forwards the Context to the superclass.
	 * @param c Reference to calling object.
	 */
	public Database(Context c)
	{
		ourContext = c;
	}

	/**
	 * Opens a new connection to the database.  
	 * @return
	 * @throws SQLException
	 */
	public Database open() throws SQLException
	{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;	
	}

	/**
	 * Closes connection to database and closes connection to DatabaseHelper.
	 */
	public void close()
	{
		ourDatabase.close();
		ourHelper.close();
	}
}
