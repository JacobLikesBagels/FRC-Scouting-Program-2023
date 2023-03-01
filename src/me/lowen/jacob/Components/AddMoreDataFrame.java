package me.lowen.jacob.Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddMoreDataFrame extends JFrame{

	public AddMoreDataFrame() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//this.setSize(100, 160);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		//mainPanel.add(new JTextField());
		//hard coding, very great
		JPanel panelBoxOne = new JPanel();
		JTextField field1 = new JTextField(), field2 = new JTextField();
		field1.setColumns(5);
		field1.setBackground(new Color(247, 143, 141));
		field2.setColumns(5);
		field2.setBackground(new Color(141, 194, 247));
		
		panelBoxOne.add(field1);
		panelBoxOne.add(Box.createRigidArea(new Dimension(5, 0)));
		panelBoxOne.add(field2);
		
		JPanel panelBoxTwo = new JPanel();
		JTextField field3 = new JTextField(), field4 = new JTextField();
		field3.setColumns(5);
		field3.setBackground(new Color(247, 143, 141));
		field4.setColumns(5);
		field4.setBackground(new Color(141, 194, 247));
		panelBoxTwo.add(field3);
		panelBoxTwo.add(Box.createRigidArea(new Dimension(5, 0)));
		panelBoxTwo.add(field4);
		
		JPanel panelBoxThree = new JPanel();
		JTextField field5 = new JTextField(), field6 = new JTextField();
		field5.setColumns(5);
		field5.setBackground(new Color(247, 143, 141));
		field6.setColumns(5);
		field6.setBackground(new Color(141, 194, 247));
		panelBoxThree.add(field5);
		panelBoxThree.add(Box.createRigidArea(new Dimension(5, 0)));
		panelBoxThree.add(field6);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				submit(mainPanel);
				disposeOfThisFrame();
			}
			
		});
		
		mainPanel.add(panelBoxOne);
		mainPanel.add(panelBoxTwo);
		mainPanel.add(panelBoxThree);
		mainPanel.add(submitButton);
		
		
		this.add(mainPanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	private void disposeOfThisFrame() {
		this.dispose();
	}
	
	private void submit(JPanel panel) {
		JFrame collectionFrame = new JFrame();
		JPanel collectionPanel = new JPanel();
		collectionPanel.setLayout(new BoxLayout(collectionPanel, BoxLayout.Y_AXIS));
		
		ArrayList<String> blues = new ArrayList<String>();
		for (Component c : panel.getComponents()) {
			if (c instanceof JTextField) {
				if (c.getBackground() == new Color(247, 143, 141)) {
					int teamNumber = Integer.parseInt(((JTextField)c).getText());
					collectionPanel.add(new RobotCollectionPanel(collectionPanel, teamNumber, Color.RED));
				} else {
					blues.add(((JTextField)c).getText());
				}
				//the lazy way because I dont care
			} else if (c instanceof JPanel) {
				for (Component c2 : ((JPanel)c).getComponents()) {
					if (c2 instanceof JTextField) {
						if (c2.getBackground().equals( new Color(247, 143, 141))) {
							
							int teamNumber = Integer.parseInt(((JTextField)c2).getText());
							collectionPanel.add(new RobotCollectionPanel(collectionPanel, teamNumber, Color.RED));
						} else {
							blues.add(((JTextField)c2).getText());
						}
			}}
		}}
//			ConsoleFrame
		for (String teamNumbers: blues) {
			collectionPanel.add(new RobotCollectionPanel(collectionPanel, Integer.parseInt(teamNumbers), Color.BLUE));
		}
		collectionFrame.add(collectionPanel);
		collectionFrame.pack();
		collectionFrame.setLocationRelativeTo(null);
		collectionFrame.setVisible(true);
	

}
		
	}
