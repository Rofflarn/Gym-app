package com.Grupp01.gymapp.Controller;

public class IdName {
	
	private String name;
	private int id;
	
	public IdName(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}

	public int getId()
	{
		return id;
	}
}
