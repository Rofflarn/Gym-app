package com.Grupp01.gymapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SQLView extends Activity {
	


	@Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_sqlview);
       Toast.makeText(this, "Inne i sqlview", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.activity_sqlview, menu);
       return true;
    }
    
    public void openDatabase(View view)
    {
    	Toast.makeText(this, "Knapp tryckt", Toast.LENGTH_SHORT).show();
    	Database entry = new Database(this);
    	entry.open();
    	entry.addUser("Jojje", "1990-06-22");
    	entry.addUser("Zotty", "1990-02-10");
    	entry.addUser("Rofflarn", "1991-09-12");
    	entry.close();
    }
    
    public void showDatabase(View view)
    {
    	/*TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
        Database info = new Database(this);
        info.open();
        String data = info.getData();
        info.close();
        tv.setText(data);
        */
        
    	Database info = new Database(this);
        info.open();
    	Cursor c = info.getUsers();

		int id = c.getColumnIndex("UserId");
		int name = c.getColumnIndex("UserName");
		//Forlopp som går igenom hela databasen, alla kolummer
        //String[] columns = new String[]{ "ExerciseTypeID", "ExerciseTypeName"};
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			//result = result + c.getString(id) + " " + c.getString(name) + "\n";
			System.out.println("Id: " + Integer.toString(c.getInt(id)) + " | Name: " + c.getString(name));
		}
    }
}
