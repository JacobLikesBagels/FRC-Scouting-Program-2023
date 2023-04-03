package me.lowen.jacob.Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import me.lowen.jacob.Components.DebugThings.ConsoleFrame;
import me.lowen.jacob.Utils.GeneralUtilities;
import me.lowen.jacob.Utils.SerializeObject;
import me.lowen.jacob.Utils.Settings;

public class RobotCollectionPanel extends JPanel implements KeyListener{

	private static final long serialVersionUID = -1794043423055467965L;
	
	private JPanel theHostPanel;
	/*
	JCheckBox heightCheck;
	JTextField heightBox;
	JCheckBox rangeCheck;
	JTextField rangeBox;
	JCheckBox climbCheck;
	JTextField climbBox;
	JCheckBox autonCheck;
	JTextField autonBox;
	JCheckBox notesCheck;
	JTextField notesBox;*/
	 
	int teamNumber;
	JLabel teamLabel;
//	JLabel heightLabel;
//	JLabel rangeLabel;
//	JLabel climbLabel;
//	JLabel autonLabel;
//	JLabel notesLabel;
	
	JCheckBox lowerPoleBox;
	JCheckBox upperPoleBox;
	JLabel poleLabel;
	JTextField poleField;
	
	JCheckBox middleShelfBox;
	JCheckBox upperShelfBox;
	JLabel shelfLabel;
	JTextField shelfField;
	
	JLabel autonLabel;
	JTextField autonField;
	
	JCheckBox everyBotCBox;
	
	JLabel extraNoteLabel;
	JTextField extraNotesField;
	public RobotCollectionPanel(JPanel hostPanel, int TeamNumber, Color TeamColor) {
		teamNumber = TeamNumber;
		 try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		} 
		theHostPanel = hostPanel;
		this.setLayout(Settings.getChosenLayout(new BoxLayout(this, BoxLayout.X_AXIS)));
//		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		teamLabel = new JLabel("Team " + TeamNumber);
		teamLabel.setToolTipText(String.valueOf(TeamNumber));
		teamLabel.setForeground(TeamColor);
		this.add(teamLabel);
		
		lowerPoleBox = new JCheckBox("Lower Pole", false);
		this.add(lowerPoleBox);
		upperPoleBox = new JCheckBox("Upper Pole", false);
		this.add(upperPoleBox);
		poleLabel = new JLabel("Pole Notes:");
		this.add(poleLabel);
		poleField = new JTextField();
		poleField.setColumns(5);
		this.add(poleField);
		
		middleShelfBox = new JCheckBox("Middle Shelf", false);
		this.add(middleShelfBox);
		upperShelfBox = new JCheckBox("Upper Shelf", false);
		this.add(upperShelfBox);
		shelfLabel = new JLabel("Shelf Notes:");
		this.add(shelfLabel);
		shelfField = new JTextField();
		shelfField.setColumns(5);
		this.add(shelfField);
		
		autonLabel = new JLabel("Auton Notes:");
		this.add(autonLabel);
		autonField = new JTextField();
		autonField.setColumns(5);
		this.add(autonField);
		
		everyBotCBox = new JCheckBox("Is Every Bot?");
		this.add(everyBotCBox);
		
		extraNoteLabel = new JLabel("Extra Notes");
		this.add(extraNoteLabel);
		extraNotesField = new JTextField();
		extraNotesField.setColumns(5);
		this.add(extraNotesField);
		
		
	/*	heightCheck = new JCheckBox("", false);
		heightCheck.setMargin(new Insets(0, 0, 0, 0));
		heightLabel = new JLabel("Shoot Height:");
		heightBox = new JTextField();
		heightBox.setMargin(new Insets(0, 0, 0, 0));
		heightBox.setToolTipText("heightBox");
		heightBox.addKeyListener(this);
		heightBox.setColumns(5);
		heightBox.addKeyListener(this);
		
		rangeLabel = new JLabel("Shoot Range");
		rangeCheck = new JCheckBox("", false);
		rangeCheck.setMargin(new Insets(0, 0, 0, 0));
		rangeBox = new JTextField();
		rangeBox.setToolTipText("rangeBox");
		rangeBox.setColumns(5);
		rangeBox.addKeyListener(this);
		
		climbLabel = new JLabel("Climb Height");
		climbCheck = new JCheckBox("", false);
		climbCheck.setMargin(new Insets(0, 0, 0, 0));
		climbBox = new JTextField();
		climbBox.setToolTipText("");
		climbBox.setColumns(5);
		climbBox.addKeyListener(this);
		
		autonLabel = new JLabel("Autonomous Rank");
		autonCheck = new JCheckBox("", false);
		autonCheck.setMargin(new Insets(0, 0, 0, 0));
		autonBox = new JTextField();
		autonBox.setToolTipText("autonBox");
		autonBox.setColumns(5);
		autonBox.addKeyListener(this);
		
		notesLabel = new JLabel("Extra Notes");
		notesCheck = new JCheckBox("", false);
		notesCheck.setMargin(new Insets(0, 0, 0, 0));
		notesBox = new JTextField();
		notesBox.setToolTipText("notesBox");
		notesBox.setColumns(16);
		notesBox.addKeyListener(this);
		
		everyBotCBox = new JCheckBox("Every Bot?");
		everyBotCBox.setToolTipText("everyBotCBox");
		everyBotCBox.addKeyListener(this);
		
		this.add(teamLabel);
		this.add(heightLabel);
		this.add(heightCheck);
		this.add(heightBox);
		this.add(rangeLabel);
		this.add(rangeCheck);
		this.add(rangeBox);
		this.add(climbLabel);
		this.add(climbCheck);
		this.add(climbBox);
		this.add(autonLabel);
		this.add(autonCheck);
		this.add(autonBox);
		this.add(everyBotCBox);
		this.add(notesLabel);
		this.add(notesCheck);
		this.add(notesBox); */
		
		this.setFocusable(true);
		this.addKeyListener(this);
		//System.out.println("dsf");
	}
	
	public void save(Map<Integer, Robot> storage) {
		ConsoleFrame.output("Attempting to save data...", Color.WHITE);
		
		int teamNum = teamNumber;
		boolean lowerPole = lowerPoleBox.isSelected();
		boolean upperPole = upperPoleBox.isSelected();
		String poleNotes = poleField.getText();
		boolean middleShelf = middleShelfBox.isSelected();
		boolean upperShelf = upperShelfBox.isSelected();
		String shelfNotes = shelfField.getText();
		String auton = autonField.getText();
		boolean isEveryBot = everyBotCBox.isSelected();
		String extraNotes = extraNotesField.getText();
		Robot robot = new Robot(teamNum, lowerPole, upperPole, poleNotes, middleShelf, upperShelf, shelfNotes, auton , isEveryBot, extraNotes);
		String tmpdir = System.getProperty("java.io.tmpdir");
		storage.put(teamNum, robot);
		Object readObj = SerializeObject.ReadObjectFromFile(new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt"));
		if (readObj == null) {
			ConsoleFrame.output("This is the first time you're saving data, making new file...", Color.WHITE);
		SerializeObject.Serialize(new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt"), storage);
		} else {
			System.out.println("not null");
			ConsoleFrame.output("You already have data saved, adding new data to old...", Color.WHITE);
			storage.putAll((Map<Integer, Robot>) readObj);
			SerializeObject.Serialize(new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt"), storage);
		}
		GeneralUtilities.resetMainFrame();
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("fdsf");
	//This section set each different box to a hotkey that can be pressed to focus it. This is done to make recording data faster
	/*	if ('!' == e.getKeyChar())  {
			e.consume();
			heightBox.requestFocus();
		}
		if ('@' == e.getKeyChar()) { 
			e.consume();
			rangeBox.requestFocus();
		}
		if ('#' == e.getKeyChar()) { 
			e.consume();
			climbBox.requestFocus();
		}
		if ('$' == e.getKeyChar()) {
			e.consume();
			autonBox.requestFocus();
		}
		if ('%' == e.getKeyChar()) {
			e.consume();
			everyBotCBox.requestFocus();
		}
		if ('^' == e.getKeyChar()) {
			e.consume();
			notesBox.requestFocus();
		} */
		if ('S' == e.getKeyChar()) {
			System.out.println("fdsf");
			e.consume();
			/*Robot bot = new Robot(1, "Hello","Hello" , 2,"Hello" , true, "HEL ");
			Robot bot2 = new Robot(12, "Hello","Hello" , 2,"Hello" , true, "Hello");
			Robot bot3 = new Robot(123, "Hello","Hello" , 2,"Hello" , true, "Hello");
			Map<Integer, Robot> map = new HashMap<Integer, Robot>();
			map.put(1, bot);
			map.put(12, bot2);
			map.put(123, bot3); */
			//save(GeneralUtilities.getValuesFromTextBox(TestingMain.panel));
			//save(GeneralUtilities.getValuesFromTextBox(theHostPanel));
			//saveAll(theHostPanel);
			
		}
		
	}
	public Robot getRobot() {
		int teamNum = teamNumber;
		boolean lowerPole = lowerPoleBox.isSelected();
		boolean upperPole = upperPoleBox.isSelected();
		String poleNotes = poleField.getText();
		boolean middleShelf = middleShelfBox.isSelected();
		boolean upperShelf = upperShelfBox.isSelected();
		String shelfNotes = shelfField.getText();
		String auton = autonField.getText();
		boolean isEveryBot = everyBotCBox.isSelected();
		String extraNotes = extraNotesField.getText();
		Robot robot = new Robot(teamNum, lowerPole, upperPole, poleNotes, middleShelf, upperShelf, shelfNotes, auton , isEveryBot, extraNotes);
		return robot;
	}
	

}
