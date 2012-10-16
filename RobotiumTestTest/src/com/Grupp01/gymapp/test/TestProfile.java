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
	
	/**Testing to start profile activity.
	 * Reference to Test-case in documentation: x.x
	 */
	public void testOpenProfile()
	{
		//Clicks on profile button
		solo.clickOnImageButton(4);
		//Checks that profile activity is shown
		solo.assertCurrentActivity("Wrong activity", ProfileActivity.class);
	}
	
	/**Testing to add a profile.
	 * Reference to Test-case in documentation: x.x
	 */
	public void testAddProfile()
	{
		//Clicks on profile button
		solo.clickOnImageButton(4);
		//Fills in all textboxes
		solo.typeText(0, "name");
		solo.sleep(1);
		solo.typeText(1, "1");
		solo.sleep(1);
		solo.typeText(2, "180");
		solo.sleep(1);
		solo.typeText(3, "70");
		//Clicks uppdate profile button
		solo.clickOnButton(0);
		//Checks that main activity is shown
		solo.assertCurrentActivity("Wrong activity", MainActivity.class);
	}
	
	/**Testing to cancel when trying to add a profile.
	 * Reference to Test-case in documentation: x.x
	 */
	public void testAbortAddProfile()
	{
		//Clicks on profile button
		solo.clickOnImageButton(4);
		//Clicks cancel button
		solo.clickOnButton(1);
		//Checks that main activity is shown
		solo.assertCurrentActivity("Wrong activity", MainActivity.class);
	}
	
	/**Testing to that a error message is shown when trying to add a profile
	 * whitout name
	 * Reference to Test-case in documentation: x.x
	 */
	public void testEmptyName()
	{
		//Clicks on profile button
		solo.clickOnImageButton(4);
		//Fills in all textboxes exept name
		solo.typeText(1, "1");
		solo.sleep(1);
		solo.typeText(2, "180");
		solo.sleep(1);
		solo.typeText(3, "70");
		//Clicks uppdate profile button
		solo.clickOnButton(0);
		//Checks that a error message is shown
		assertTrue("Should generate error message", solo.searchText("Fill in correct information"));
	}
	
	/**Testing to that a error message is shown when trying to add a profile
	 * whitout age
	 * Reference to Test-case in documentation: x.x
	 */
	public void testEmptyAge()
	{
		//Clicks on profile button
		solo.clickOnImageButton(4);
		//Fills in all textboxes exept age
		solo.typeText(0, "name");
		solo.sleep(1);
		solo.typeText(2, "180");
		solo.sleep(1);
		solo.typeText(3, "70");
		//Clicks uppdate profile button
		solo.clickOnButton(0);
		//Checks that a error message is shown
		assertTrue("Should generate error message", solo.searchText("Fill in correct information"));
	}
	
	/**Testing to that a error message is shown when trying to add a profile
	 * whitout length
	 * Reference to Test-case in documentation: x.x
	 */
	public void testEmptyLength()
	{
		//Clicks on profile button
		solo.clickOnImageButton(4);
		//Fills in all textboxes exept length
		solo.typeText(0, "name");
		solo.sleep(1);
		solo.typeText(1, "1");
		solo.sleep(1);
		solo.typeText(3, "70");
		//Clicks uppdate profile button
		solo.clickOnButton(0);
		//Checks that a error message is shown
		assertTrue("Should generate error message", solo.searchText("Fill in correct information"));
	}
	
	/**Testing to that a error message is shown when trying to add a profile
	 * whitout weigth
	 * Reference to Test-case in documentation: x.x
	 */
	public void testEmptyWeigth()
	{
		//Clicks on profile button
		solo.clickOnImageButton(4);
		//Fills in all textboxes exept weigth
		solo.typeText(0, "name");
		solo.sleep(1);
		solo.typeText(1, "1");
		solo.sleep(1);
		solo.typeText(2, "180");
		//Clicks uppdate profile button
		solo.clickOnButton(0);
		//Checks that a error message is shown
		assertTrue("Should generate error message", solo.searchText("Fill in correct information"));
	}
	
	protected void tearDown() throws Exception
	{
		solo.finishOpenedActivities();
	}
}