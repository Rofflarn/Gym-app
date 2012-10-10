package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.View.History.Historik;
import com.jayway.android.robotium.solo.Solo;

public class TestHistory extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	public TestHistory() {
		super("com.Grupp01.gymapp", MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(),getActivity());
	}

	public void testOpenHistory()
	{		
		solo.clickOnImageButton(2);
		solo.assertCurrentActivity("Wrong activity", Historik.class);
	}
	
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}
