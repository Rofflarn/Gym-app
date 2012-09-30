package com.Grupp01.gymapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

public class EditWorkout extends SherlockActivity implements OnItemSelectedListener{

		private ListView mainListView;  
	  private Planet[] planets;
	  private ArrayAdapter<Planet> listAdapter;
	  private String workoutName;
	  
	  String[] items = { "Hej", "detta", "�r", "Robert"};
	  
	@SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        workoutName = intent.getStringExtra(ListWorkoutActivity.WORKOUT_NAME);
        
        setContentView(R.layout.editworkout);
     // Find the ListView resource.   
        mainListView = (ListView) findViewById( R.id.mainListView );  
          
        // When item is tapped, toggle checked properties of CheckBox and Planet.  
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
          @Override  
          public void onItemClick( AdapterView<?> parent, View item,   
                                   int position, long id) {  
            Planet planet = listAdapter.getItem( position );  
            planet.toggleChecked();  
            PlanetViewHolder viewHolder = (PlanetViewHolder) item.getTag();  
            viewHolder.getCheckBox().setChecked( planet.isChecked() );  
          }  
        });  
      
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
		spin.setOnItemSelectedListener((OnItemSelectedListener) this);

		ArrayAdapter<String> aa = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_spinner_item, 
				items);

		aa.setDropDownViewResource(
		   android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(aa);
        
        
        
        
          
        // Create and populate planets.  
        planets = (Planet[]) getLastNonConfigurationInstance() ;  
        if ( planets == null ) {  
          planets = new Planet[] {   
              new Planet("Mercury"), new Planet("Venus"), new Planet("Earth"),   
              new Planet("Mars"), new Planet("Jupiter"), new Planet("Saturn"),   
              new Planet("Uranus"), new Planet("Neptune"), new Planet("Ceres"),  
              new Planet("Pluto"), new Planet("Haumea"), new Planet("Makemake"),  
              new Planet("Eris")  
          };    
        }  
        ArrayList<Planet> planetList = new ArrayList<Planet>();  
        planetList.addAll( Arrays.asList(planets) );  
          
        // Set our custom array adapter as the ListView's adapter.  
        listAdapter = new PlanetArrayAdapter(this, planetList);  
        mainListView.setAdapter( listAdapter );
      }  
        
      /** Holds planet data. */  
      private static class Planet {  
        private String name = "" ;  
        private boolean checked = false ;  
        public Planet( String name ) {  
          this.name = name ;  
        }  
        public Planet( String name, boolean checked ) {  
          this.name = name ;  
          this.checked = checked ;  
        }  
        public String getName() {  
          return name;  
        }   
        public boolean isChecked() {  
          return checked;  
        }  
        public void setChecked(boolean checked) {  
          this.checked = checked;  
        }  
        public String toString() {  
          return name ;   
        }  
        public void toggleChecked() {  
          checked = !checked ;  
        }  
      }  
        
      /** Holds child views for one row. */  
      private static class PlanetViewHolder {  
        private CheckBox checkBox ;  
        private TextView textView ;
        public PlanetViewHolder( TextView textView, CheckBox checkBox ) {  
          this.checkBox = checkBox ;  
          this.textView = textView ;  
        }  
        public CheckBox getCheckBox() {  
          return checkBox;  
        }   
        public TextView getTextView() {  
          return textView;  
        }       
      }  
        
      /** Custom adapter for displaying an array of Planet objects. */  
      private static class PlanetArrayAdapter extends ArrayAdapter<Planet> {  
          
        private LayoutInflater inflater;  
          
        public PlanetArrayAdapter( Context context, List<Planet> planetList ) {  
          super( context, R.layout.simplerow, R.id.rowTextView, planetList );  
          // Cache the LayoutInflate to avoid asking for a new one each time.  
          inflater = LayoutInflater.from(context) ;  
        }  
      
        @Override  
        public View getView(int position, View convertView, ViewGroup parent) {  
          // Planet to display  
          Planet planet = (Planet) this.getItem( position );   
      
          // The child views in each row.  
          CheckBox checkBox ;   
          TextView textView ;   
            
          // Create a new row view  
          if ( convertView == null ) {  
            convertView = inflater.inflate(R.layout.simplerow, null);  
              
            // Find the child views.  
            textView = (TextView) convertView.findViewById( R.id.rowTextView );  
            checkBox = (CheckBox) convertView.findViewById( R.id.CheckBox01 );  
              
            // Optimization: Tag the row with it's child views, so we don't have to   
            // call findViewById() later when we reuse the row.  
            convertView.setTag( new PlanetViewHolder(textView,checkBox) );  
      
            // If CheckBox is toggled, update the planet it is tagged with.  
            checkBox.setOnClickListener( new View.OnClickListener() {  
              public void onClick(View v) {  
                CheckBox cb = (CheckBox) v ;  
                Planet planet = (Planet) cb.getTag();  
                planet.setChecked( cb.isChecked() );  
              }  
            });          
          }  
          // Reuse existing row view  
          else {  
            // Because we use a ViewHolder, we avoid having to call findViewById().  
            PlanetViewHolder viewHolder = (PlanetViewHolder) convertView.getTag();  
            checkBox = viewHolder.getCheckBox() ;  
            textView = viewHolder.getTextView() ;  
          }  
      
          // Tag the CheckBox with the Planet it is displaying, so that we can  
          // access the planet in onClick() when the CheckBox is toggled.  
          checkBox.setTag( planet );   
            
          // Display planet data  
          checkBox.setChecked( planet.isChecked() );  
          textView.setText( planet.getName() );        
            
          return convertView;  
        }  
          
      }  
        
      public Object onRetainNonConfigurationInstance() {  
        return planets ;  
      }  
      
      
      @Override
      public boolean onCreateOptionsMenu(Menu menu) {
      	getSupportMenuInflater().inflate(R.menu.editworkout, menu);
          getSupportActionBar().setHomeButtonEnabled(true);
          
          //Set the title to the name of the workout
          getSupportActionBar().setTitle(workoutName);
          return true;
      }

  	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
  		// TODO Auto-generated method stub
  		
  	}

  	@Override
  	public void onNothingSelected(AdapterView<?> arg0) {
  		return;
  		
  	}  
    }  