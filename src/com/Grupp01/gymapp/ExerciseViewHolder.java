package com.Grupp01.gymapp;

import android.widget.CheckBox;
import android.widget.TextView;

/** Holds child views for one row. */  
     public class ExerciseViewHolder
     {  
        private CheckBox checkBox ;  
        private TextView textView ;
        public ExerciseViewHolder( TextView textView, CheckBox checkBox ) 
        {  
          this.checkBox = checkBox ;  
          this.textView = textView ;  
        }  
        public CheckBox getCheckBox() 
        {  
          return checkBox;  
        }   
        public TextView getTextView() 
        {  
          return textView;  
        }       
     }