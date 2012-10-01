package com.Grupp01.gymapp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/** Custom adapter for displaying an array of Exercise objects. */  
 public class ExerciseArrayAdapter extends ArrayAdapter<Exercise> {  
    
  private LayoutInflater inflater;  
    
  public ExerciseArrayAdapter( Context context, List<Exercise> exerciseList )
  {  
    super( context, R.layout.simplerow, R.id.rowTextView, exerciseList );  
    // Cache the LayoutInflate to avoid asking for a new one each time.  
    inflater = LayoutInflater.from(context);
  }

  
  //Since we display other things than a TextView we need to Override a getView-method
  @Override  
  public View getView(int position, View convertView, ViewGroup parent)
  {  
    // Exercise to display  
    Exercise exercise = (Exercise) this.getItem( position );   

    //What each view contains. 
    CheckBox checkBox;
    TextView textView;
      
    //If the view doesn't exist, we create a new row view
    if ( convertView == null)
    {
      convertView = inflater.inflate(R.layout.simplerow, null);
        
      // Find the child views.
      textView = (TextView) convertView.findViewById( R.id.rowTextView );
      checkBox = (CheckBox) convertView.findViewById( R.id.CheckBox01 );
        
      // Optimization: Tag the row with it's child views, so we don't have to   
      // call findViewById() later when we reuse the row.  
      convertView.setTag( new ExerciseViewHolder(textView,checkBox) );

      // If CheckBox is toggled, update the Exercise it is tagged with. 
      checkBox.setOnClickListener( new View.OnClickListener()
      {
        public void onClick(View v)
        {  
          CheckBox cb = (CheckBox) v;
          Exercise exercise = (Exercise) cb.getTag();
          exercise.setChecked( cb.isChecked() );
        }
      });
    }
    // Reuse existing row view
    else
    {
      // Because we use a ViewHolder, we avoid having to call findViewById().  
      ExerciseViewHolder viewHolder = (ExerciseViewHolder) convertView.getTag();  
      checkBox = viewHolder.getCheckBox();
      textView = viewHolder.getTextView();
    }

    // Tag the CheckBox with the Exercise it is displaying, so that we can  
    // access the Exercise in onClick() when the CheckBox is clicked.
    checkBox.setTag( exercise );   
      
    // Display Exercise 
    checkBox.setChecked( exercise.isChecked() );  
    textView.setText( exercise.getName() );        
      
    return convertView;  
  }  
    
}  