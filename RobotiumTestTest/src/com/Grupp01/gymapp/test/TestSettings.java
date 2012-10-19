package com.Grupp01.gymapp.test;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.test.ActivityInstrumentationTestCase2;
import com.Grupp01.gymapp.MainActivity;
import com.Grupp01.gymapp.View.Settings.SettingsActivity;
import com.jayway.android.robotium.solo.Solo;

public class TestSettings extends ActivityInstrumentationTestCase2<MainActivity> {
	private static final int SETTINGS_BUTTON = 5;
	private Solo solo;
	public TestSettings() {
		super("com.Grupp01.gymapp", MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(),getActivity());
	}

	public void testOpenSettings()
	{		
		solo.clickOnImageButton(SETTINGS_BUTTON);
		solo.assertCurrentActivity("Expected settings activity", SettingsActivity.class);
		
	}
	
	public void testChangeLanguage()
	{		
		solo.clickOnImageButton(SETTINGS_BUTTON);
		
		//Select swedish as language
		solo.clickOnText("Select desired language");
		solo.clickOnText("Svenska");
		
		//Go back to reload the view
		solo.goBack();
		solo.clickOnImageButton(SETTINGS_BUTTON);
		
		//Make sure the text is swedish
		assertTrue(solo.searchText("Språk"));
		
		//Go back to english as a language and make sure 
		//the language is correct just like above.
		solo.clickOnText("Ange önskat språk");
		solo.clickOnText("English");
		solo.goBack();
		solo.clickOnImageButton(SETTINGS_BUTTON);
		assertTrue(solo.searchText("Language"));
	}

	public void testChangeDistanceUnit()
	{		
		solo.clickOnImageButton(SETTINGS_BUTTON);
		
		//Select kilometers as unit
		solo.clickOnText("Select desired distance");
		solo.clickOnText("Kilometers");
		
		//Get sharedpreferences and make sure "km" is set to the correct unit
		SharedPreferences temp = PreferenceManager.getDefaultSharedPreferences(solo.getCurrentActivity());
		String unit = temp.getString("pref_key_distance", "km");
		assertTrue(unit.equals("km"));
		assertFalse(unit.equals("mi"));
		
		//Select miles as unit
		solo.clickOnText("Select desired distance");
		solo.clickOnText("Miles");
		
		//Get the preferences again and make sure it is different
		temp = PreferenceManager.getDefaultSharedPreferences(solo.getCurrentActivity());
		unit = temp.getString("pref_key_distance", "km");
		assertTrue(unit.equals("mi"));
		assertFalse(unit.equals("km"));
	}
	
	
	public void testChangeWeightUnit()
	{		
		solo.clickOnImageButton(SETTINGS_BUTTON);
		
		//Select kilogram as unit
		solo.clickOnText("Select desired weight");
		solo.clickOnText("Kilogram");
		
		//Get sharedpreferences and make sure "kg" is set to the correct unit
		SharedPreferences temp = PreferenceManager.getDefaultSharedPreferences(solo.getCurrentActivity());
		String unit = temp.getString("pref_key_weight", "kg");
		assertTrue(unit.equals("kg"));
		assertFalse(unit.equals("lb"));
		
		//Select miles as unit
		solo.clickOnText("Select desired weight");
		solo.clickOnText("Pounds");
		
		//Get the preferences again and make sure it is different
		temp = PreferenceManager.getDefaultSharedPreferences(solo.getCurrentActivity());
		unit = temp.getString("pref_key_weight", "kg");
		assertTrue(unit.equals("lb"));
		assertFalse(unit.equals("kg"));
	}
	
	
	 
	
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}
