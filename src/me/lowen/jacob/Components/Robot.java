package me.lowen.jacob.Components;

import java.io.Serializable;

public class Robot implements Serializable{

	private static final long serialVersionUID = 7829108401263012458L;
	
	public int teamNumber;
	public boolean lowerPole;
	public boolean upperPole;
	public String poleNotes;
	public boolean middleShelf;
	public boolean upperShelf;
	public String shelfNotes;
	public String shootHeight;
	public String shootRange;
	public String climbHeight;
	public String autonMeasure;
	public boolean isEveryBot;
	public String extraNotes;
	
	//Should I have made a RobotFactory instead? Yes. Did I? No
	public Robot(int TeamNumber, boolean LowerPole, boolean UpperPole, String PoleNotes, 
			boolean LowerShelf, boolean UpperShelf, String ShelfNotes, String AutonMeasure, boolean IsEveryBot, String ExtraNotes) {
		teamNumber = TeamNumber;
		lowerPole = LowerPole;
		upperPole = UpperPole;
		poleNotes = PoleNotes;
		middleShelf = LowerShelf;
		upperShelf = UpperShelf;
		shelfNotes = ShelfNotes;
		
		
		
		autonMeasure = AutonMeasure;
		isEveryBot = IsEveryBot;
		extraNotes = ExtraNotes;
	}
}
