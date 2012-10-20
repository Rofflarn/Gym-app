package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.Controller.Exercise.ListExerciseDbHandler;
import com.Grupp01.gymapp.Controller.Profile.Profile;
import com.Grupp01.gymapp.Controller.Profile.ProfileDbHandler;
import com.Grupp01.gymapp.View.Profile.ProfileActivity;

public class TestProfileDbHandler extends ActivityInstrumentationTestCase2<ProfileActivity>
{	
	private ProfileActivity activity;
	private ProfileDbHandler dbP;
	private final int MANY_EXERCISES = 100;
	private final int ZERO_EXERCISES = 0;
	
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
	
	public void testAddProfile()
	{
		int nrOfUsers = dbP.numberOfUsers();
		dbP.addUser(new Profile("Name", "20", 70, 180));
		assertEquals("Did not work to add a user", dbP.numberOfUsers(), nrOfUsers + 1);
	}
}