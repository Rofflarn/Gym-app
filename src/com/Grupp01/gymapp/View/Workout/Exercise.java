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
*  
*  			Copyright © 2012 GivDev
*
*/
package com.Grupp01.gymapp.View.Workout;
 
/**@author GivDev
 * @version 0.1
 * @peer reviewed by Joel Olofsson
 * @date 07/10/12
 *
 * Holds Exercise data.
 * 
 * <p>This class i a part of the </p><i>View</i><p> package, and a part of the </p><i>Workout</i>
 * <p> Subpackage</p> 
 *  */	
 public class Exercise 
{  
 
  private String name = "" ;  
  private boolean checked = false ;
  
  public Exercise( String name, boolean checked )
  {  
    this.name = name ;  
    this.checked = checked ;  
  }
  /** Get the name of the exercise into a string
   * @return string*/
  public String getName() 
  {  
    return name;  
  }
  /** Check if the exercise is checked*/
  public boolean isChecked() 
  {  
    return checked;  
  }
  /** Sets the exercise to the boolean parameter
   * @param checked*/
  public void setChecked(boolean checked) 
  {  
    this.checked = checked;  
  }
  /** Invert the boolean value of the variable checked*/
  public void toogleChecked()
  {  
    checked = !checked ;  
  }  
}  
  