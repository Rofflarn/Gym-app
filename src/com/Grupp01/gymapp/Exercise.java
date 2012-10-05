package com.Grupp01.gymapp;
 
/**@author GivDev
 * @version 0.1
 * @peer reviewed by
 * @date 04/10/12
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
  