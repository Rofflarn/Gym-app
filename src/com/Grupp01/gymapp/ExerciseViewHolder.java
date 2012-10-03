package com.Grupp01.gymapp;

import android.widget.CheckBox;
import android.widget.TextView;

/** Holds child views for one row. */  
     public class ExerciseViewHolder
     {
        private CheckBox checkBox;
        private TextView textView;
     
        /**
         * Creates a new holder with the given name and a checkbox
         * The name is the name of the exercise
         */
        public ExerciseViewHolder( TextView textView, CheckBox checkBox )
        {
          this.checkBox = checkBox;
          this.textView = textView;
        }
        /**
         * Gets the checkbox of the holder
         * @return the holders checkbox.
         */
        public CheckBox getCheckBox()
        {
          return checkBox;
        }
        /**
         * Gets the name of the holder
         * @return the holders name
         */
        public TextView getTextView()
        {  
          return textView;
        }
     }