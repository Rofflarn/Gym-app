package com.Grupp01.gymapp;

/** Holds Exercise data. */  
 public class Exercise 
{  
  private String name = "" ;  
  private boolean checked = false ;  
  public Exercise( String name ) {  
    this.name = name ;  
  }  
  public Exercise( String name, boolean checked ) {  
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
  public void toogleChecked() {  
    checked = !checked ;  
  }  
}  
  