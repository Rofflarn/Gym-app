package com.Grupp01.gymapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class SQLView extends Activity {
	


	@Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_sqlview);
       Toast.makeText(this, "Inne i sqlview", Toast.LENGTH_SHORT).show();
       //TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
      /*Training info = new Training(this);
       info.open();
       String data = info.getData();
       info.close();
       tv.setText(data);*/
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
    	
    	
    }
}
