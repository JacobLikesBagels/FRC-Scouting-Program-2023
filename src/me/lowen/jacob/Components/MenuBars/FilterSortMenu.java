package me.lowen.jacob.Components.MenuBars;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import me.lowen.jacob.TestingMain;
import me.lowen.jacob.Components.Robot;
import me.lowen.jacob.Components.RobotDisplayFrame;
import me.lowen.jacob.Utils.GeneralUtilities;
import me.lowen.jacob.Utils.SerializeObject;

public class FilterSortMenu extends JMenu{

	private static final long serialVersionUID = -756323538168176931L;
	private booleanPanel lowerPole;
	private booleanPanel upperPole;
	private valueIsPanel poleNotes;
	private booleanPanel lowerShelf;
	private booleanPanel upperShelf;
	private valueIsPanel shelfNotes;
	private valueIsPanel autonNotes;
	private valueIsPanel extraNotes;
	private booleanPanel isNotEveryBot;
	public FilterSortMenu() {
		super("Filter");
		
		lowerPole = new booleanPanel("Lower Pole:");
		upperPole = new booleanPanel("Upper Pole:");
		poleNotes = new valueIsPanel("Pole Notes", "Contains");
		
		lowerShelf = new booleanPanel("Lower Shelf:");
		upperShelf = new booleanPanel("Upper Shelf:");
		shelfNotes = new valueIsPanel("Shelf Notes", "Contains");
		
		autonNotes = new valueIsPanel("Auton Notes", "Contains");
		extraNotes = new valueIsPanel("Extra Notes", "Contains");
		isNotEveryBot = new booleanPanel("Not Everybot");
		
		
		this.setMnemonic(KeyEvent.VK_S);
		
		
		JMenuItem refresh = new JMenuItem("Clear", KeyEvent.VK_C);
		refresh.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    		GeneralUtilities.resetMainFrame();
		            clearFields();
		    	
		    }
		});
		add(refresh);
		
		this.addSeparator();
		
		this.add(poleNotes);
		this.add(lowerPole);
		this.add(upperPole);
		
		this.add(shelfNotes);
		this.add(lowerShelf);
		this.add(upperShelf);
		
		this.add(autonNotes);
		this.add(extraNotes);
		this.add(isNotEveryBot);
		
		
//		this.add(shootHeightPanel);
//			this.add(shootRangePanel);
//		
//		this.add(climbHeightPanel);
//		
//		this.add(autonRankPanel);
//		
//		
//		this.add(extraNotesPanel);
		
		JMenuItem submit = new JMenuItem("Submit", KeyEvent.VK_S);
		submit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	Object robotobj = SerializeObject.ReadObjectFromFile(new File(GeneralUtilities.robotFilePath));
		    	HashMap<Integer, Robot> robots = (HashMap<Integer, Robot>) robotobj;
		    	
		    	String poleNotesStr = poleNotes.getValue().toLowerCase();
		    	boolean lowerPoleBool = lowerPole.getValue();
		    	boolean upperPoleBool = upperPole.getValue();
		    	
		    	String shelfNotesStr = shelfNotes.getValue().toLowerCase();
		    	boolean lowerShelfBool = lowerShelf.getValue();
		    	boolean upperShelfBool = upperShelf.getValue();
		    	
		    	String autonNotesStr = autonNotes.getValue().toLowerCase();
		    	String extraNotesStr = extraNotes.getValue().toLowerCase();
		    	boolean notEveryBotBool = isNotEveryBot.getValue();
		    	HashMap<Integer, Robot> matchingBots = new HashMap<Integer, Robot>();
		    	for (Integer botNumber : robots.keySet()) {
		    		Robot bot = robots.get(botNumber);
		    		boolean passes = true;
		    		
		    		if (!bot.poleNotes.toLowerCase().contains(poleNotesStr))
		    			if (!poleNotesStr.equals(""))
		    			passes = false;
		    		if (!twoBooleansPassCheck(lowerPoleBool, bot.lowerPole))
		    			passes = false;
		    		if (!twoBooleansPassCheck(upperPoleBool, bot.upperPole))
		    			passes = false;
		    		
		    		if (!bot.shelfNotes.toLowerCase().contains(shelfNotesStr))
		    			if (!shelfNotesStr.equals(""))
		    			passes = false;
		    		if (!twoBooleansPassCheck(lowerShelfBool, bot.middleShelf))
		    			passes = false;
		    		if (!twoBooleansPassCheck(upperShelfBool, bot.upperShelf))
		    			passes = false;
		    		
		    		if (!bot.autonMeasure.toLowerCase().contains(autonNotesStr))
		    			if (!autonNotesStr.equals(""))
		    			passes = false;
		    		if (!bot.extraNotes.toLowerCase().contains(extraNotesStr))
		    			if (!extraNotesStr.equals(""))
		    			passes = false;
		    		if (!isNotEveryBotPassCheck(notEveryBotBool, bot.isEveryBot))
		    			passes = false;
		    		if (passes)
		    			matchingBots.put(botNumber, bot);
		    	}
		    	TestingMain.theMainFrame.rdf.removeAll(); 
	            TestingMain.theMainFrame.rdf.add(new RobotDisplayFrame(matchingBots));
	            TestingMain.theMainFrame.rdf.revalidate();
	            TestingMain.theMainFrame.rdf.repaint();
	            SwingUtilities.getWindowAncestor(TestingMain.theMainFrame.rdf).pack();
	            
	            clearFields();
	            
//	    	clearBoxes();
//		    	//I haven't a clue on why I chose to do it this way... this all seems garbage
//		    	String sh = shootHeightPanel.inputBox.getText();
//		    	if (sh == null || sh.equalsIgnoreCase(""))
//		    		sh = "IGNORETHISVALUEFORTESTINGWEDONTCAREABOUTIT";
//		    	String sr = shootRangePanel.inputBox.getText();
//		    	if (sr == null || sr.equalsIgnoreCase(""))
//		    		sr = "IGNORETHISVALUEFORTESTINGWEDONTCAREABOUTIT";
//		    	String ch = climbHeightPanel.inputBox.getText();
//		    	if (ch == null || ch.equalsIgnoreCase("")) 
//		    		ch = "IGNORETHISVALUEFORTESTINGWEDONTCAREABOUTIT";
//		    	
//		    	String ar = autonRankPanel.inputBox.getText();
//		    	if (ar == null || ar.equalsIgnoreCase(""))
//		    		ar = "IGNORETHISVALUEFORTESTINGWEDONTCAREABOUTIT";
//		    	String en = extraNotesPanel.inputBox.getText();
//		    	System.out.println( extraNotesPanel.inputBox.getText() + " and " + en);
//		    	if (en == null || en.equalsIgnoreCase(""))
//		    		en = "IGNORETHISVALUEFORTESTINGWEDONTCAREABOUTIT";
//		            Map<Integer, Robot> bots = GeneralUtilities.sortTeamsByValuesEqual(
//		            		sh, sr, ch, ar, true, en);
//		            TestingMain.theMainFrame.rdf.removeAll();
//		            TestingMain.theMainFrame.rdf.add(new RobotDisplayFrame(bots));
//		            TestingMain.theMainFrame.rdf.revalidate();
//		            TestingMain.theMainFrame.rdf.repaint();
//		            SwingUtilities.getWindowAncestor(TestingMain.theMainFrame.rdf).pack();
//		    	clearBoxes();
		    	
		    }
		});
		
		this.addSeparator();
		add(submit);
		
	//	ititp.add(new JComboBoxMenuItem())
	//	fake.add(ititp);
		//fake.addItemListener(this);
		//fake.addActionListener(this);
		//this.add(fake);
		
		
		
		//this.add(new IfThisIsThatPanel());
	}
	private boolean twoBooleansPassCheck(boolean bool1, boolean bool2) {
		if (bool1 == false)
			return true;
		if (bool1 == true && bool2 == true)
			return true;
		return false;
	}
	private boolean isNotEveryBotPassCheck(boolean bool1, boolean bool2) {
		if (bool1 == false)
			return true;
		if (bool1 == true && bool2 == false)
			return true;
		return false;
	}
	
	private void clearFields() {
		upperPole.filterBox.setSelected(false);
		lowerPole.filterBox.setSelected(false);
		poleNotes.inputBox.setText("");
		lowerShelf.filterBox.setSelected(false);
		upperShelf.filterBox.setSelected(false);
		shelfNotes.inputBox.setText("");
		autonNotes.inputBox.setText("");
		extraNotes.inputBox.setText("");
		isNotEveryBot.filterBox.setSelected(false);
	}
	
	class valueIsPanel extends JPanel {
		public JLabel measureLabel;
		public JTextField inputBox;
		
		public String getValue() {
			if (inputBox.getText() == null || inputBox.getText().isEmpty())
				return "";
			return inputBox.getText();
		}
		public valueIsPanel(String value, String isWhat) {
			measureLabel = new JLabel(value);
			this.add(measureLabel);
			this.add(new JLabel(isWhat));
			inputBox = new JTextField();
			inputBox.setColumns(5);
			this.add(inputBox);
		}
	}
	
	class booleanPanel extends JPanel {
		public JLabel filterLabel;
		public JCheckBox filterBox;
		public boolean getValue() {
			return filterBox.isSelected();
		}
		public booleanPanel(String value) {
			filterLabel = new JLabel(value);
			filterBox = new JCheckBox("", false);
			
			this.add(filterLabel);
			this.add(filterBox);
			
		}
	}
	

	

}
