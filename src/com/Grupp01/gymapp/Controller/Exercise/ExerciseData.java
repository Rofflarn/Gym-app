package com.Grupp01.gymapp.Controller.Exercise;

public class ExerciseData {
	
	private int id;
	private int pri;
	private int sec;
	private String name;
	private String desc;
	private String note;
	private int typeId;

	
	public ExerciseData(int id, int exercisePri, int exerciseSec, String exerciseName, String exerciseDesc, String exerciseNote, int exerciseTypeId)
	{
		this.id = id;
		this.pri = exercisePri;
		this.sec = exerciseSec;
		this.name = exerciseName;
		this.desc = exerciseDesc;
		this.note = exerciseNote;
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
	
	public int getTyepId()
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
	
	public void putNote(String note)
	{
		this.note = note;
	}
	
	public void putTyepId(int typeId)
	{
		this.typeId = typeId;
	}
}
