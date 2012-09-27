package com.Grupp01.gymapp;

<<<<<<< HEAD
=======
import android.app.Dialog;
import android.content.Intent;
>>>>>>> f22e36ff53d81966827745cabc9a19cd87443913
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
<<<<<<< HEAD
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
=======
import com.actionbarsherlock.view.MenuItem;
>>>>>>> f22e36ff53d81966827745cabc9a19cd87443913

public class Ovningar extends SherlockActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ovningar);
<<<<<<< HEAD
        ListView listView = (ListView)findViewById(R.id.listView1);
        TextView tv = new TextView(this);
        tv.setText("funkar");
=======
        getSupportActionBar().setHomeButtonEnabled(true);
>>>>>>> f22e36ff53d81966827745cabc9a19cd87443913
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getSupportMenuInflater();
    	inflater.inflate(R.menu.ovningar, menu);
    	com.actionbarsherlock.view.MenuItem item = menu.add("New exercise");
    	item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    	item.setOnMenuItemClickListener(new OnMenuItemClickListener()
    	{

			@Override
			public boolean onMenuItemClick(com.actionbarsherlock.view.MenuItem item)
			{
				return false;
			}
    		
    	});
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intentMain = new Intent(this, MainActivity.class);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentMain);
                return true;
            case R.id.menu_addExe:
            	Intent intentAddExe = new Intent(this, AddExercise.class);
                intentAddExe.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentAddExe);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
//    public void newExercise(View view)
//    {
//    	final Dialog dialog = new Dialog(this);
//    	dialog.setContentView(R.layout.dialog);
//    	dialog.setTitle("New Exercise");
//    	
//    	Button button = (Button) dialog.findViewById(R.id.button);
//    	button.setOnClickListener(
//    			new OnClickListener()
//    			{
//    				public void onClick(View view)
//    				{
//    					TextView tv = (TextView) findViewById(R.id.test);
//    					EditText et = (EditText) dialog.findViewById(R.id.exerciseName);
//    					tv.setText(et.getText().toString());
//    					dialog.dismiss();
//    				}
//    			});
//    	dialog.show();
//    }
}