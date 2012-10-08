		package com.Grupp01.gymapp.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "GymAppDatabase"; 
	private static final int DATABASE_VERSION = 1;

	
	public DbHelper(Context context)
	{	
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**
	 * Creates new tables in database.
	 * 
	 * @param db The SQLite database in which the tables is added.
	 */
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		createExercises(db);
		createMeasureDataTypes(db);
		createExerciseTypes(db);
		createWorkout(db);
		createMeasures(db);
		createMeasureData(db);		
		createMuscles(db);
		createSets(db);
		createUsers(db);
		createPassTemplates(db);
		createSetTemplates(db);
		createSports(db);
		createMuscleGroups(db);
	}
	
	/**
	 * Deletes all the old tables and creates fresh ones.
	 * 
	 * @param db The SQLite database to upgrade.
	 * @param oldVersion The version number of the current database.
	 * @param newVersion The version number of the new database.
	 * @author Anders Gustafsson, Tobias Hallberg
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXIST Exercises");
			db.execSQL("DROP TABLE IF EXIST ExerciseTypes");
			db.execSQL("DROP TABLE IF EXIST MeasureDataTypes");
			db.execSQL("DROP TABLE IF EXIST MeasureData");
			db.execSQL("DROP TABLE IF EXIST Workout");
			db.execSQL("DROP TABLE IF EXIST Measures");
			db.execSQL("DROP TABLE IF EXIST Muscles");
			db.execSQL("DROP TABLE IF EXIST Sets");
			db.execSQL("DROP TABLE IF EXIST Sports");
			db.execSQL("DROP TABLE IF EXIST MuscleGroups");
			db.execSQL("DROP TABLE IF EXIST Users");
			db.execSQL("DROP TABLE IF EXIST PassTemplates");
			db.execSQL("DROP TABLE IF EXIST SetTemplates");
			onCreate(db);//Creates fresh tables
       }
	
	/**
	 * Adds table Exercises database and adds default content.
	 * 
	 * @author Anders Gustafsson, Tobias Hallberg
	 * @param db The SQLite database in which the table is added.
	 */
	private void createExercises(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE Exercises (ExerciseId INTEGER PRIMARY KEY AUTOINCREMENT, ExercisePri INTEGER, " +
				"ExerciseSec INTEGER, ExerciseName TEXT NOT NULL, ExerciseDesc TEXT, ExerciseNote Text, " +
				"ExerciseTypeId INTEGER NOT NULL);");
		//db.execSQL("INSERT INTO Exercises(ExercisePri, ExerciseSec, ExerciseName, ExerciseTypeId) VALUES (2, , 'Sit-ups', 1);");
		db.execSQL("INSERT INTO Exercises(ExercisePri, ExerciseSec, ExerciseName, ExerciseDesc, ExerciseNote, ExerciseTypeId) VALUES (3, 1, 'Chins', 'Desc', 'Note', 1);");
		db.execSQL("INSERT INTO Exercises(ExercisePri, ExerciseSec, ExerciseName, ExerciseTypeId) VALUES (1, 4, 'Bench press', 3);");
		db.execSQL("INSERT INTO Exercises(ExercisePri, ExerciseSec, ExerciseName, ExerciseTypeId) VALUES (1, 2, 'Push-ups', 1);");
		db.execSQL("INSERT INTO Exercises(ExercisePri, ExerciseSec, ExerciseName, ExerciseTypeId) VALUES (1, 2, 'Push-ups', 1);");
		db.execSQL("INSERT INTO Exercises(ExercisePri, ExerciseSec, ExerciseName, ExerciseTypeId) VALUES (1, 2, 'Joels Ölhäfv', 1);");
		db.execSQL("INSERT INTO Exercises(ExercisePri, ExerciseSec, ExerciseName, ExerciseTypeId) VALUES (1, 2, 'Schlobbans sjuor', 2);");
		
	}
	
	/**
	 * Adds table ExerciseTypes to database and adds default content.
	 * 
	 * @author Anders Gustafsson, Tobias Hallberg
	 * @param db The SQLite database in which the table is added.
	 */
	private void createExerciseTypes(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE ExerciseTypes (ExerciseTypeId INTEGER PRIMARY KEY AUTOINCREMENT, ExerciseTypeName TEXT);");
		db.execSQL("insert into ExerciseTypes (ExerciseTypeName) VALUES ('Cardio');");
		db.execSQL("insert into ExerciseTypes (ExerciseTypeName) VALUES ('Dynamic');");
		db.execSQL("insert into ExerciseTypes (ExerciseTypeName) VALUES ('Static');");		
	}
	
	/**
	 * Adds table MeasureDataTypes to database.
	 * 
	 * @author Anders Gustafsson, Tobias Hallberg
	 * @param db The SQLite database in which the table is added.
	 */
	private void createMeasureDataTypes(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE MeasureDataTypes (MeasureDataTypeId INTEGER PRIMARY KEY AUTOINCREMENT, MeasureDataTypeName TEXT);");
	}
	
	/**
	 * Adds table MeasureData to database.
	 * 
	 * @author Anders Gustafsson, Tobias Hallberg
	 * @param db The SQLite database in which the table is added.
	 */
	private void createMeasureData(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE MeasureData (MeasureDataId INTEGER PRIMARY KEY AUTOINCREMENT, MeasureData REAL NOT NULL, " +
					"MeasureDataTypeId INTEGER NOT NULL, MeasureId INTEGER NOT NULL);");
	}
	
	/**
	 * Adds table Workout to database.
	 * 
	 * @author Anders Gustafsson, Tobias Hallberg
	 * @param db The SQLite database in which the table is added.
	 */
	private void createWorkout(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE Workout (WorkoutID INTEGER PRIMARY KEY AUTOINCREMENT, WorkoutTime TEXT NOT NULL, WorkoutName TEXT, " +
				"UserId INTEGER NOT NULL);");
	}
	
	/**
	 * Adds table Measures to database.
	 * 
	 * @author Anders Gustafsson, Tobias Hallberg
	 * @param db The SQLite database in which the table is added.
	 */
	private void createMeasures(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE Measures (MeasureId INTEGER PRIMARY KEY AUTOINCREMENT, MeasureData REAL NOT NULL, " +
				"UserId INTEGER NOT NULL);");
	}
	
	/**
	 * Adds table MuscleGroups to database and adds default content.
	 * 
	 * @author Anders Gustafsson, Tobias Hallberg
	 * @param db The SQLite database in which the table is added.
	 */
	private void createMuscleGroups(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE MuscleGroups (MuscleGroupId  INTEGER PRIMARY KEY AUTOINCREMENT, MuscleGroupName TEXT NOT NULL);");
		db.execSQL("INSERT INTO MuscleGroups(MuscleGroupName) VALUES ('Arms');");
		db.execSQL("INSERT INTO MuscleGroups(MuscleGroupName) VALUES ('Chest');");
		db.execSQL("INSERT INTO MuscleGroups(MuscleGroupName) VALUES ('Back');");
		db.execSQL("INSERT INTO MuscleGroups(MuscleGroupName) VALUES ('Shoulders');");
		db.execSQL("INSERT INTO MuscleGroups(MuscleGroupName) VALUES ('Abdomen');");
		db.execSQL("INSERT INTO MuscleGroups(MuscleGroupName) VALUES ('Legs');");
	}
	
	/**
	 * Adds table Muscles to database and adds default content.
	 * 
	 * @author Anders Gustafsson, Tobias Hallberg
	 * @param db The SQLite database in which the table is added.
	 */
	private void createMuscles(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE Muscles (MuscleId INTEGER PRIMARY KEY AUTOINCREMENT, MuscleName TEXT NOT NULL, " +
				"MuscleGroupId INTEGER NOT NULL, MuscleImage TEXT);");
		db.execSQL("INSERT INTO Muscles (MuscleName, MuscleGroupId) VALUES ('Biceps', 1);");
		db.execSQL("INSERT INTO Muscles (MuscleName, MuscleGroupId) VALUES ('Triceps', 1);");
		db.execSQL("INSERT INTO Muscles (MuscleName, MuscleGroupId) VALUES ('Abs', 5);");
		db.execSQL("INSERT INTO Muscles (MuscleName, MuscleGroupId) VALUES ('Gluteus Maximus', 6);");
		db.execSQL("INSERT INTO Muscles (MuscleName, MuscleGroupId) VALUES ('Straight thigh', 6);");
		db.execSQL("INSERT INTO Muscles (MuscleName, MuscleGroupId) VALUES ('Tailor', 6);");
	}
	
	/**
	 * Adds table Sets to database.
	 * 
	 * @author Anders Gustafsson, Tobias Hallberg
	 * @param db The SQLite database in which the table is added.
	 */
	private void createSets(SQLiteDatabase db)
	{
		 db.execSQL("CREATE TABLE Sets (SetId INTEGER PRIMARY KEY AUTOINCREMENT, SetReps INTEGER, SetWeight REAL, " +
		 		"SetDistance INTEGER, PassId INTEGER, SetDuration TEXT, ExerciseId INTEGER NOT NULL);");
	}
	
	/**
	 * Adds table Sports to database and adds default content.
	 * 
	 * @author Anders Gustafsson, Tobias Hallberg
	 * @param db The SQLite database in which the table is added.
	 */
	private void createSports(SQLiteDatabase db)
	{
		 db.execSQL("CREATE TABLE Sports (SportId  INTEGER PRIMARY KEY AUTOINCREMENT, SportName TEXT NOT NULL);");
		 db.execSQL("INSERT INTO Sports (SportName) VALUES ('Cycling');");
		 db.execSQL("INSERT INTO Sports (SportName) VALUES ('Powerwalking');");
		 db.execSQL("INSERT INTO Sports (SportName) VALUES ('Mountainbiking');");
		 db.execSQL("INSERT INTO Sports (SportName) VALUES ('Orienteering');");
		 db.execSQL("INSERT INTO Sports (SportName) VALUES ('Running');");
		 db.execSQL("INSERT INTO Sports (SportName) VALUES ('Spinning');");
		 db.execSQL("INSERT INTO Sports (SportName) VALUES ('Walking');");
		 db.execSQL("INSERT INTO Sports (SportName) VALUES ('Skiing');");
		 db.execSQL("INSERT INTO Sports (SportName) VALUES ('Swimming');");
		 db.execSQL("INSERT INTO Sports (SportName) VALUES ('Rowing');");
		 db.execSQL("INSERT INTO Sports (SportName) VALUES ('Floorball');");
	}
	
	/**
	 * Adds table Users to database.
	 * 
	 * @author Anders Gustafsson, Tobias Hallberg
	 * @param db The SQLite database in which the table is added.
	 */
	private void createUsers(SQLiteDatabase db)
	{
		 db.execSQL("CREATE TABLE Users (UserId INTEGER PRIMARY KEY AUTOINCREMENT, UserName TEXT NOT NULL, " +
		 		"UserBirthday TEXT NOT NULL, UserInitialWeight REAL);");
	}
	
	/**
	 * Adds table PassTemplates to database and adds deafult content.
	 * 
	 * @author Anders Gustafsson, Tobias Hallberg
	 * @param db The SQLite database in which the table is added.
	 */
	private void createPassTemplates(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE PassTemplates (PassTemplateId INTEGER PRIMARY KEY AUTOINCREMENT, PassTemplateName TEXT NOT NULL);");
		db.execSQL("INSERT INTO PassTemplates (PassTemplateName) VALUES ('Upper body');");
		db.execSQL("INSERT INTO PassTemplates (PassTemplateName) VALUES ('Core');");
		db.execSQL("INSERT INTO PassTemplates (PassTemplateName) VALUES ('Legs');");
		db.execSQL("INSERT INTO PassTemplates (PassTemplateName) VALUES ('Spinning');");
	}
	
	/**
	 * Adds table SetTemplates to database and adds default content.
	 * 
	 * @author Anders Gustafsson, Tobias Hallberg
	 * @param db The SQLite database in which the table is added.
	 */
	private void createSetTemplates(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE SetTemplates (SetTemplateId INTEGER PRIMARY KEY AUTOINCREMENT, SetTemplateReps INTEGER," +
		 		" SetTemplateWeight REAL, SetTemplateDistance INTEGER,ExerciseId INTEGER NOT NULL, PassTemplateId INTEGER NOT NULL);");
		db.execSQL("INSERT INTO SetTemplates (SetTemplateReps, SetTemplateWeight, ExerciseId, PassTemplateId) VALUES (10, 10, 3, 1);");
		db.execSQL("INSERT INTO SetTemplates (SetTemplateReps, SetTemplateWeight, ExerciseId, PassTemplateId) VALUES (25, 10, 2, 1);");
		db.execSQL("INSERT INTO SetTemplates (SetTemplateReps, SetTemplateWeight, ExerciseId, PassTemplateId) VALUES (10, 12, 3, 2);");
		db.execSQL("INSERT INTO SetTemplates (SetTemplateReps, SetTemplateWeight, ExerciseId, PassTemplateId) VALUES (10, 10, 1, 1);");
	}
	
}
