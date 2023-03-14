package me.lowen.jacob.Components;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import me.lowen.jacob.Utils.Settings;

public class RobotDisplayFrame extends JPanel {

	private static final long serialVersionUID = 3141433468218715001L;

	public RobotDisplayFrame(Map<Integer, Robot> savedRobots) {
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		if (savedRobots == null || savedRobots.isEmpty()) {
			this.add(new JLabel("No robots are saved! Press \"Add More Data\" to add some!"));
		} else
		for (Integer i : savedRobots.keySet()) {
			
			Robot bot = savedRobots.get(i);
		
			this.add(new displaypanel(bot.teamNumber, bot.lowerPole, bot.upperPole, bot.poleNotes, bot.middleShelf,
					bot.upperShelf, bot.shelfNotes, bot.autonMeasure, bot.isEveryBot, bot.extraNotes));
		}
		
		//this.pack();
		this.setVisible(true);
	}
	
	class displaypanel extends JPanel {
		JLabel teamLabel;
//		JLabel heightLabel;
//		JLabel rangeLabel;
//		JLabel climbLabel;
//		JLabel autonLabel;
//		JLabel notesLabel;
		
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
		private static final long serialVersionUID = 2782955370956704166L;
		//teamNumber = TeamNumber;
		private displaypanel(int TeamNumber, boolean LowerPole, boolean UpperPole, String PoleNotes, 
				boolean LowerShelf, boolean UpperShelf, String ShelfNotes, String AutonMeasure, boolean IsEveryBot, String ExtraNotes) {
		 try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		} 
		
		 //teamNumber = TeamNumber;
		 try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		} 
		//theHostPanel = hostPanel;
		this.setLayout(Settings.getChosenLayout(new BoxLayout(this, BoxLayout.X_AXIS)));
		//this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		teamLabel = new JLabel("Team " + TeamNumber);
		teamLabel.setToolTipText(String.valueOf(TeamNumber));
		//teamLabel.setForeground(TeamColor);
		this.add(teamLabel);
		
		lowerPoleBox = new JCheckBox("Lower Pole", false);
		lowerPoleBox.setForeground(Color.GRAY);
		lowerPoleBox.setFocusable(false);
		lowerPoleBox.removeMouseListener(lowerPoleBox.getMouseListeners()[0]);
		lowerPoleBox.setSelected(LowerPole);
		this.add(lowerPoleBox);
		upperPoleBox = new JCheckBox("Upper Pole", false);
		upperPoleBox.setForeground(Color.GRAY);
		upperPoleBox.setFocusable(false);
		upperPoleBox.removeMouseListener(upperPoleBox.getMouseListeners()[0]);
		upperPoleBox.setSelected(UpperPole);
		this.add(upperPoleBox);
		poleLabel = new JLabel("Pole Notes:");
		poleLabel.setForeground(Color.GRAY);
		this.add(poleLabel);
		poleField = new JTextField();
		poleField.setBorder(null);
		poleField.setEditable(false);
		poleField.setText(PoleNotes);
		poleField.setColumns(5);
		this.add(poleField);
		
		middleShelfBox = new JCheckBox("Middle Shelf", false);
		middleShelfBox.setForeground(Color.GRAY);
		middleShelfBox.setFocusable(false);
		middleShelfBox.removeMouseListener(middleShelfBox.getMouseListeners()[0]);
		middleShelfBox.setSelected(LowerShelf);
		this.add(middleShelfBox);
		upperShelfBox = new JCheckBox("Upper Shelf", false);
		upperShelfBox.setForeground(Color.GRAY);
		upperShelfBox.setFocusable(false);
		upperShelfBox.removeMouseListener(upperShelfBox.getMouseListeners()[0]);
		upperShelfBox.setSelected(UpperShelf);
		this.add(upperShelfBox);
		shelfLabel = new JLabel("Shelf Notes:");
		shelfLabel.setForeground(Color.GRAY);
		this.add(shelfLabel);
		shelfField = new JTextField();
		shelfField.setBorder(null);
		shelfField.setEditable(false);
		shelfField.setText(ShelfNotes);
		shelfField.setColumns(5);
		this.add(shelfField);
		
		autonLabel = new JLabel("Auton Notes:");
		autonLabel.setForeground(Color.GRAY);
		this.add(autonLabel);
		autonField = new JTextField();
		autonField.setBorder(null);
		autonField.setEditable(false);
		autonField.setText(AutonMeasure);
		autonField.setColumns(5);
		this.add(autonField);
		
		everyBotCBox = new JCheckBox("Is Every Bot?");
		everyBotCBox.setForeground(Color.GRAY);
		everyBotCBox.setFocusable(false);
		everyBotCBox.removeMouseListener(everyBotCBox.getMouseListeners()[0]);
		everyBotCBox.setSelected(IsEveryBot);
		this.add(everyBotCBox);
		
		extraNoteLabel = new JLabel("Extra Notes");
		extraNoteLabel.setForeground(Color.GRAY);
		this.add(extraNoteLabel);
		extraNotesField = new JTextField();
		extraNotesField.setBorder(null);
		extraNotesField.setEditable(false);
		extraNotesField.setText(ExtraNotes);
		extraNotesField.setColumns(5);
		this.add(extraNotesField);
		this.setEnabled(false);;
	}
		
	}
	
}

