package com.Grupp01.gymapp.test;

import com.Grupp01.gymapp.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

public class test_StartPage extends ActivityInstrumentationTestCase2<MainActivity>
{
	private static final String ENTER = "DPAD_RIGHT ENTER";  

	public test_StartPage()
	{
		super("com.Grupp01.gymapp", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		MainActivity mainActivity = getActivity();
	}
	
	public void testButton()
	{
		sendKeys(ENTER);
	}
}
