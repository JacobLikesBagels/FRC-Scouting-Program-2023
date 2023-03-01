package me.lowen.jacob.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import me.lowen.jacob.Components.DebugThings.ConsoleFrame;
import me.lowen.jacob.Components.MenuBars.DataMenu;
import me.lowen.jacob.Components.MenuBars.FileMenu;
import me.lowen.jacob.Components.MenuBars.FilterSortMenu;
import me.lowen.jacob.Components.MenuBars.HelpMenu;
import me.lowen.jacob.Utils.GeneralUtilities;

public class MainMenuFrame extends JFrame{
	public JPanel test;
	public RobotDisplayFrame rdf;
	
	private JButton resetFilters;
	
	private static final long serialVersionUID = 6387209013456510987L;

	public MainMenuFrame(Map<Integer, Robot> robots) {
		super("6101 Robot Scouting Program");
		test = new JPanel();
		//test.setPreferredSize(new Dimension( 1500,2000));
		
		JButton moreDataButton = new JButton("Add More Data");
		moreDataButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new NewAddMoreDataFrame();
				
			}
			
		});
		JButton searchButton = new JButton("Search!");
		
		JTextField teamNumberInput = new JTextField();
		teamNumberInput.setMaximumSize(new Dimension(100, teamNumberInput.getPreferredSize().height));
		teamNumberInput.setColumns(10);
		JScrollPane scrollFrame = new JScrollPane(test);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		test.setLayout(new BoxLayout(test, BoxLayout.Y_AXIS));
		test.setAutoscrolls(true);
		topPanel.add(moreDataButton);
		topPanel.add(searchButton);
		
		
		
		topPanel.add(teamNumberInput);
		resetFilters = new JButton("X");
		resetFilters.setVisible(false);
		resetFilters.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConsoleFrame.output("clearing filters", Color.WHITE);
				resetFilters.setVisible(false);
				teamNumberInput.setText("");
				GeneralUtilities.resetMainFrame();
				
			}
			
		});
		topPanel.add(resetFilters);
		Box box = Box.createHorizontalBox();
		topPanel.add(box);
		box.add(Box.createHorizontalGlue());
		
		//topPanel.add(teamNumberInput);
		
		test.add(topPanel);
		//scrollFrame.setPreferredSize(new Dimension( 900,300));
		rdf = new RobotDisplayFrame(robots);
		test.add(rdf);
		
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetFilters.setVisible(true);
				String TNI = teamNumberInput.getText();
				ConsoleFrame.output("Search for team numbers: " + TNI, Color.WHITE);
				if (TNI.split("\\s+").length > 0) {
					ArrayList<Integer> tNums = new ArrayList<Integer>();
					for (String s : TNI.split("\\s+")) {
						tNums.add(Integer.parseInt(s));
					}
					rdf.removeAll();
					GeneralUtilities.showTeamsOnPanel(rdf, tNums);
					rdf.revalidate();
					rdf.repaint();
					SwingUtilities.getWindowAncestor(scrollFrame).pack();
					
				}
				
			}
			
		});
		
		this.add(scrollFrame);
		this.setJMenuBar(assembleMenuBars());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private JMenuBar assembleMenuBars() {
		JMenuBar mb = new JMenuBar();
		mb.add(new FileMenu());
		mb.add(new FilterSortMenu());
		mb.add(new DataMenu());
		mb.add(new HelpMenu());
		return mb;
		
	}

}
