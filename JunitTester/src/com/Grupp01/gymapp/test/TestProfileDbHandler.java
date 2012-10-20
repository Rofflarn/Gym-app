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
 *	Copyright © 2012 GivDev
 *
 */

package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.Controller.Profile.Profile;
import com.Grupp01.gymapp.Controller.Profile.ProfileDbHandler;
import com.Grupp01.gymapp.View.Profile.ProfileActivity;

/** 
 * @author GivDev
 * @version 0.1
 * @peer reviewed by xxxxxxxxxxx
 * @date dd/mm/yyyy
 */
public class TestProfileDbHandler extends ActivityInstrumentationTestCase2<ProfileActivity>
{	
	
	private ProfileActivity activity;
	private ProfileDbHandler dbP;
	
	public TestProfileDbHandler()	
	{		
		super("com.Grupp01.gymapp", ProfileActivity.class);
	}	
	
	protected void setUp() throws Exception	
	{
		super.setUp();
		activity = getActivity();
		dbP= new ProfileDbHandler(activity);
	}
	
	/**Tests to add one user
	 */
	public void testAddProfile()
	{
		//Gets number of users
		int nrOfUsers = dbP.numberOfUsers();
		//Adds one user
		dbP.addUser(new Profile("Name", "20", 70, 180));
		assertEquals("Did not work to add a user", dbP.numberOfUsers(), nrOfUsers + 1);
	}
}