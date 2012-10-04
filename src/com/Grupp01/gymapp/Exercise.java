package com.Grupp01.gymapp;

/** Holds Exercise data. */  
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
  