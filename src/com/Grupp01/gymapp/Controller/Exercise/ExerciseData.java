package com.Grupp01.gymapp.Controller.Exercise;

public class ExerciseData {
	
	private int id;
	private int pri;
	private int sec;
	private String name;
	private String desc;
	private String note;
	private int sportId;
	private int typeId;

	
	public ExerciseData(int id, int exercisePri, int exerciseSec, String exerciseName, String exerciseDesc, String exerciseNote, int sportId, int exerciseTypeId)
	{
		this.id = id;
		this.pri = exercisePri;
		this.sec = exerciseSec;
		this.name = exerciseName;
		this.desc = exerciseDesc;
		this.note = exerciseNote;
		this.sportId = sportId;
		this.typeId = exerciseTypeId;
	}
	
	public ExerciseData(int id, String exerciseName, int exerciseTypeId)
	{
		this.id = id;
		this.name = exerciseName;
		this.typeId = exerciseTypeId;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getPri()
	{
		return pri;
	}
	
	public int getSec()
	{
		return sec;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDesc()
	{
		return desc;
	}
	
	public String getNote()
	{
		return note;
	}
	
	public int getSportId()
	{
		return sportId;
	}
	
	public int getTypeId()
	{
		return typeId;
	}
	
	public void putId(int id)
	{
		this.id = id;
	}
	
	public void putPri(int pri)
	{
		this.pri = pri;
	}
	
	public void putSec(int sec)
	{
		this.sec = sec;
	}
	
	public void putName(String name)
	{
		this.name = name;
	}
	
	public void putDesc(String desc)
	{
		this.desc = desc;
	}
	
	public void putSport(int sportId)
	{
		this.sportId = sportId;
	}
	
	public void putNote(String note)
	{
		this.note = note;
	}
	
	public void putTypeId(int typeId)
	{
		this.typeId = typeId;
	}
}
