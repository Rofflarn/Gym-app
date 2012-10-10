package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.View.Exercise.ListExerciseActivity;
import com.jayway.android.robotium.solo.Solo;

public class TestOpenExercise extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	public TestOpenExercise() {
		super("com.Grupp01.gymapp", MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(),getActivity());
	}

	public void testOpenWorkout()
	{		
		solo.clickOnImageButton(1);
		solo.assertCurrentActivity("Wrong activity", ListExerciseActivity.class);
	}
	
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}
