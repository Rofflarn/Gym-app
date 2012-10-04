package com.Grupp01.gymapp;

import android.widget.CheckBox;
import android.widget.TextView;

/**@author GivDev
 * @version 0.1
 * @peer reviewed by
 * @date 04/10/12
 *
 * Holds child views for one row.
 * 
 * <p>This class i a part of the </p><i>View</i><p> package, and a part of the </p><i>Workout</i>
 * <p> Subpackage</p> 
 *  */	 
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