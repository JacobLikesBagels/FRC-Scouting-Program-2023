package me.lowen.jacob.Components.MenuBars;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Map;

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

public class FilterSortMenu extends JMenu{

	private static final long serialVersionUID = -756323538168176931L;
	private valueIsPanel shootHeightPanel;
	private valueIsPanel shootRangePanel;
	private valueIsPanel climbHeightPanel;
	private valueIsPanel autonRankPanel;
	private valueIsPanel extraNotesPanel;
	public FilterSortMenu() {
		
		super("Filter");
		this.setMnemonic(KeyEvent.VK_S);
		shootHeightPanel = new valueIsPanel("Shoot Height", "Contains");
		shootRangePanel = new valueIsPanel("Shoot Range", "Contains");
			
		climbHeightPanel = new valueIsPanel("Climb Height", "Contains");
		autonRankPanel = new valueIsPanel("Autonomous Rank", "Contains");
		extraNotesPanel = new valueIsPanel("Extra Notes", "Contains");
		
		JMenuItem refresh = new JMenuItem("Clear", KeyEvent.VK_C);
		refresh.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    		GeneralUtilities.resetMainFrame();
		            clearBoxes();
		    	
		    }
		});
		add(refresh);
		
		
		
		this.addSeparator();
		
		
		this.add(shootHeightPanel);
			this.add(shootRangePanel);
		
		this.add(climbHeightPanel);
		
		this.add(autonRankPanel);
		
		
		this.add(extraNotesPanel);
		
		JMenuItem submit = new JMenuItem("Submit", KeyEvent.VK_S);
		submit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	//I haven't a clue on why I chose to do it this way... this all seems garbage
		    	String sh = shootHeightPanel.inputBox.getText();
		    	if (sh == null || sh.equalsIgnoreCase(""))
		    		sh = "IGNORETHISVALUEFORTESTINGWEDONTCAREABOUTIT";
		    	String sr = shootRangePanel.inputBox.getText();
		    	if (sr == null || sr.equalsIgnoreCase(""))
		    		sr = "IGNORETHISVALUEFORTESTINGWEDONTCAREABOUTIT";
		    	String ch = climbHeightPanel.inputBox.getText();
		    	if (ch == null || ch.equalsIgnoreCase("")) 
		    		ch = "IGNORETHISVALUEFORTESTINGWEDONTCAREABOUTIT";
		    	
		    	String ar = autonRankPanel.inputBox.getText();
		    	if (ar == null || ar.equalsIgnoreCase(""))
		    		ar = "IGNORETHISVALUEFORTESTINGWEDONTCAREABOUTIT";
		    	String en = extraNotesPanel.inputBox.getText();
		    	System.out.println( extraNotesPanel.inputBox.getText() + " and " + en);
		    	if (en == null || en.equalsIgnoreCase(""))
		    		en = "IGNORETHISVALUEFORTESTINGWEDONTCAREABOUTIT";
		            Map<Integer, Robot> bots = GeneralUtilities.sortTeamsByValuesEqual(
		            		sh, sr, ch, ar, true, en);
		            TestingMain.theMainFrame.rdf.removeAll();
		            TestingMain.theMainFrame.rdf.add(new RobotDisplayFrame(bots));
		            TestingMain.theMainFrame.rdf.revalidate();
		            TestingMain.theMainFrame.rdf.repaint();
		            SwingUtilities.getWindowAncestor(TestingMain.theMainFrame.rdf).pack();
		    	clearBoxes();
		    	
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
	
	private void clearBoxes() {
		shootHeightPanel.inputBox.setText("");
		shootRangePanel.inputBox.setText("");
		climbHeightPanel.inputBox.setText("");
		autonRankPanel.inputBox.setText("");
		extraNotesPanel.inputBox.setText("");
	}
	
	class valueIsPanel extends JPanel {
		public JLabel measureLabel;
		public JTextField inputBox;
		public valueIsPanel(String value, String isWhat) {
			measureLabel = new JLabel(value);
			this.add(measureLabel);
			this.add(new JLabel(isWhat));
			inputBox = new JTextField();
			inputBox.setColumns(5);
			this.add(inputBox);
		}
	}
	

	

}
