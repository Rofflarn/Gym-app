/*This file is part of Gymapp.
*
*   Gymapp is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   Gymapp is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*  along with Gymapp.  If not, see <http://www.gnu.org/licenses/>.
*
*/
package com.Grupp01.gymapp.View.Workout;

import android.widget.CheckBox;
import android.widget.TextView;

/**@author GivDev
 * @version 0.1
 * @peer reviewed by Joel Olofsson
 * @date 07/10/12
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