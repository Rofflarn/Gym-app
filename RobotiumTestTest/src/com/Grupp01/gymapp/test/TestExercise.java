package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.MainActivity;
import com.jayway.android.robotium.solo.Solo;

public class TestExercise extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	public TestExercise() {
		super("com.Grupp01.gymapp", MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testButton()
	{
		solo.clickOnImageButton(1);
	}

	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}
