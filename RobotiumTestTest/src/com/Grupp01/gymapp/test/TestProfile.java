package com.Grupp01.gymapp.test;

import android.test.ActivityInstrumentationTestCase2;

import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.View.Profile.ProfileActivity;
import com.jayway.android.robotium.solo.Solo;

public class TestProfile extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	public TestProfile() {
		super("com.Grupp01.gymapp", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(),getActivity());
	}
	
	public void testOpenProfile()
	{
		solo.clickOnImageButton(4);
		solo.assertCurrentActivity("Wrong activity", ProfileActivity.class);
	}
	
	public void testAddProfile()
	{
		solo.clickOnImageButton(4);
		solo.typeText(0, "name");
		solo.sleep(1);
		solo.typeText(1, "1");
		solo.sleep(1);
		solo.typeText(2, "180");
		solo.sleep(1);
		solo.typeText(3, "70");
		solo.clickOnButton(0);
		solo.assertCurrentActivity("Wrong activity", MainActivity.class);
	}
	
	public void testAbortAddProfile()
	{
		solo.clickOnImageButton(4);
		solo.clickOnButton(1);
		solo.assertCurrentActivity("Wrong activity", MainActivity.class);
	}
	
	public void testEmptyName()
	{
		solo.clickOnImageButton(4);
		solo.typeText(1, "1");
		solo.sleep(1);
		solo.typeText(2, "180");
		solo.sleep(1);
		solo.typeText(3, "70");
		solo.clickOnButton(0);
		assertTrue("Should generate error message", solo.searchText("Fill in correct information"));
	}
	
	public void testEmptyAge()
	{
		solo.clickOnImageButton(4);
		solo.typeText(0, "name");
		solo.sleep(1);
		solo.typeText(2, "180");
		solo.sleep(1);
		solo.typeText(3, "70");
		solo.clickOnButton(0);
		assertTrue("Should generate error message", solo.searchText("Fill in correct information"));
	}
	
	public void testEmptyLength()
	{
		solo.clickOnImageButton(4);
		solo.typeText(0, "name");
		solo.sleep(1);
		solo.typeText(1, "1");
		solo.sleep(1);
		solo.typeText(3, "70");
		solo.clickOnButton(0);
		assertTrue("Should generate error message", solo.searchText("Fill in correct information"));
	}
	
	public void testEmptyWeigth()
	{
		solo.clickOnImageButton(4);
		solo.typeText(0, "name");
		solo.sleep(1);
		solo.typeText(1, "1");
		solo.sleep(1);
		solo.typeText(2, "180");
		solo.clickOnButton(0);
		assertTrue("Should generate error message", solo.searchText("Fill in correct information"));
	}
	
	protected void tearDown() throws Exception
	{
		solo.finishOpenedActivities();
	}
}