package com.Grupp01.gymapp.Controller.Profile;

public class Profile {
	
	private String name;
	private int age;
	private double weigth;
	private double length;
	
	public Profile(String name, int age, double weigth, double length)
	{
		this.name = name;
		this.age = age;
		this.weigth = weigth;
		this.length = length;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public double getWeigth()
	{
		return weigth;
	}
	
	public double getLength()
	{
		return length;
	}

}
