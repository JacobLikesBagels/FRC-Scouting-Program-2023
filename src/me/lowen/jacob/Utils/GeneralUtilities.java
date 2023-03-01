package me.lowen.jacob.Utils;

import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import me.lowen.jacob.TestingMain;
import me.lowen.jacob.Components.Robot;
import me.lowen.jacob.Components.RobotDisplayFrame;

public class GeneralUtilities {
	
	private static Map<Integer, Robot> robotMap = null;
	public static String tmpdir = System.getProperty("java.io.tmpdir");
	public static String robotFilePath = tmpdir + System.getProperty("file.separator") + "storedrobots.txt";
	public GeneralUtilities() {
		
	}
	
	public static Map<Integer, Robot> sortTeamsByValuesEqual(String shootHeight, String shootRange, String climbHeight, String autonRank, boolean everyBot, String extraNotes) {
		Map<Integer, Robot> botsToAdd= new HashMap<Integer, Robot>();
		String[] vs = {shootHeight, shootRange, climbHeight, autonRank, extraNotes};
		
		if (robotMap == null) 
			robotMap = (Map<Integer, Robot>)SerializeObject.ReadObjectFromFile(new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt"));
		for (Integer i : robotMap.keySet()) {
			boolean passTest = true;
			Robot bot = robotMap.get(i);
			String[] vs2 = {bot.shootHeight, bot.shootRange, String.valueOf(bot.climbHeight), bot.autonMeasure, bot.extraNotes};
			for (int i2 = 0; i2 < vs.length; i2 ++) {
				//If the two values dont equal eachother AND the value doesnt ask to be ignored, dont add the robot
				//this line below orginally had .equas ignnore case and no .tolowercases
				System.out.println(vs2[i2]);
				if (!(vs2[i2].toLowerCase().contains(vs[i2].toLowerCase()))) {
					if (!(vs[i2].equalsIgnoreCase("IGNORETHISVALUEFORTESTINGWEDONTCAREABOUTIT"))) {
					
					passTest = false;
				}}
					
			}
			if (passTest) {
				
				botsToAdd.put(bot.teamNumber, bot);
			}
			
		//	this.add(new displaypanel(bot.teamNumber, bot.shootHeight, bot.shootRange, bot.climbHeight, bot.autonMeasure, 
		//			bot.isEveryBot, bot.extraNotes));
		}
		return botsToAdd;
	}
	
	public static void showTeamsOnPanel(JPanel displayPanel, ArrayList<Integer> teamNumbers) {
		Map<Integer, Robot> botsToAdd= new HashMap<Integer, Robot>();
		for (int tn : teamNumbers) {
			Robot bot = fetchRobotFromNumber(tn);
			if (bot != null && bot.teamNumber == tn) {
				botsToAdd.put(tn, bot);
			}
		}
		displayPanel.add(new RobotDisplayFrame(botsToAdd));
	}
	
	
	public static Robot fetchRobotFromNumber(int teamNumber) {
		
		//To save on system resources if the file already been read before
		if (robotMap == null) 
			robotMap = (Map<Integer, Robot>)SerializeObject.ReadObjectFromFile(new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt"));
			
			return robotMap.get(teamNumber);
	}
	
	//When the stored robots file get updated, calling this will get the above method up to date
	public static void updateRobotMap() {
		robotMap = (Map<Integer, Robot>)SerializeObject.ReadObjectFromFile(new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt"));
	}
	
	public static void resetMainFrame() {
		TestingMain.theMainFrame.rdf.removeAll();
        TestingMain.theMainFrame.rdf.add(new RobotDisplayFrame((Map<Integer, Robot>) SerializeObject.ReadObjectFromFile(new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "storedrobots.txt"))));
        TestingMain.theMainFrame.rdf.revalidate();
        TestingMain.theMainFrame.rdf.repaint();
        
        SwingUtilities.getWindowAncestor(TestingMain.theMainFrame.rdf).pack();
	}
	
	/*public static Map<Integer, Robot> getValuesFromTextBox(JPanel panel) {
		int teamNumber = 0;
		String shootHeight = null;
		String shootRange = null;
		String climbHeight = null;
		String autonMeasure = null;
		boolean isEveryBot = false;
		String extraNotes = null;
		Map<Integer, Robot> allRobots = new HashMap<Integer, Robot>();
		for (Component c : panel.getComponents()) {
			
	        if (c instanceof JTextArea) {
	        	String v = ((JTextArea) c).getText();
	        	switch (((JTextArea) c).getToolTipText()) {
	        	case ("heightBox"): 
	        		System.out.println("hiefht");
	        		shootHeight = v;
	        		break;
	        	case ("rangeBox"): 
	        		System.out.println("range");
	        		shootRange = v;
	        		break;
	        	case ("climbBox"): 
	        		System.out.println("climb");
	        		climbHeight = v;
	        		break;
	        	case ("autonBox"): 
	        		System.out.println("aton");
	        		autonMeasure = v;
	        		break;
	        	case ("notesBox"): 
	        		System.out.println("ontes");
	        		extraNotes = v;
	        	System.out.println("puting the robot in");
	        		Robot robot = new Robot(teamNumber, shootHeight, shootRange, climbHeight, autonMeasure, isEveryBot, extraNotes);
	        		allRobots.put(teamNumber, robot);
	        		break;
	        	
	        	}
	        		
	           
	        } else if (c instanceof JLabel)  {
	        	if (((JLabel) c).getText().contains("Team ")) {
	        		System.out.println("Contains team " + ((JLabel) c).getToolTipText());
	        		teamNumber = Integer.valueOf(((JLabel) c).getToolTipText());
	        	}
	        } else if (c instanceof JCheckBox)  {
	        	System.out.println("Checboc");
	        	isEveryBot = ((JCheckBox) c).isSelected();
	        } else if (c instanceof Container) {
	        	System.out.println("found one JPanel");
	        	allRobots.putAll(getValuesFromTextBox((JPanel) c));
	        }
	        
	    }
		//delete is you made sure this works once
		for (Integer key: allRobots.keySet()) {
		    
		}
		
		return allRobots;
	} */

}
