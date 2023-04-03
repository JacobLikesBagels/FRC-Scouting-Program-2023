package me.lowen.jacob.Components;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import me.lowen.jacob.Components.DebugThings.ConsoleFrame;
import me.lowen.jacob.Utils.GeneralUtilities;
import me.lowen.jacob.Utils.SerializeObject;

public class NewAddMoreDataFrame extends JFrame{

	JPanel collectionPanel;
	/**
	 * 
	 */
	private static final long serialVersionUID = -648297380668540976L;
	private TeamPanel redTeam;
	private TeamPanel blueTeam;
	public NewAddMoreDataFrame() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel collectPanel = new JPanel();
		collectPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		redTeam = new TeamPanel(new Color(223, 109, 106));
		blueTeam = new TeamPanel(new Color(108, 112, 221));
		collectPanel.add(redTeam);
		collectPanel.add(Box.createRigidArea(new Dimension(5, 5)));
		collectPanel.add(blueTeam);
		mainPanel.add(collectPanel);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				onSubmit();
			}
			
		});
		buttonPanel.add(submitButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(5, 5)));
		mainPanel.add(buttonPanel);
		//mainPanel.add(new JButton("Submit"));
		this.add(mainPanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void onSubmit() {
		this.dispose();
		ArrayList<Integer> reds = new ArrayList<Integer>();
		ArrayList<Integer> blues = new ArrayList<Integer>();
		for (Component c: redTeam.textFieldPanel.getComponents()) {
			if (c instanceof JTextField) {
				String text = ((JTextField) c).getText();
				System.out.println(text.matches("[0-9]+"));
				if (text.matches("[0-9]+")) {
				reds.add(Integer.valueOf(text));
				} else {
					ConsoleFrame.output("Your input of \""  + text + "\" didn't match the regex \"[0-9]+\", omitting it.", Color.RED);
				}
			}
		}
		for (Component c: blueTeam.textFieldPanel.getComponents()) {
			if (c instanceof JTextField) {
				String text = ((JTextField) c).getText();
				System.out.println(text.matches("[0-9]+"));
				if (text.matches("[0-9]+")) {
				blues.add(Integer.valueOf(text));
				} else {
					ConsoleFrame.output("Your input of \""  + text + "\" didn't match the regex \"[0-9]+\", omitting it.", Color.RED);
				}
			}
		}
		JFrame collectionFrame = new JFrame();
		collectionPanel = new JPanel();
		collectionPanel.setLayout(new BoxLayout(collectionPanel, BoxLayout.Y_AXIS));
		for (int num : reds) {
			collectionPanel.add(new RobotCollectionPanel(collectionPanel, num, Color.RED));
		}
		for (int num : blues) {
			collectionPanel.add(new RobotCollectionPanel(collectionPanel, num, Color.BLUE));
		}
		
		;
		collectionFrame.add(collectionPanel);
		collectionFrame.setJMenuBar(new SaveMenuBar(collectionPanel));
		collectionFrame.pack();
		collectionFrame.setLocationRelativeTo(null);
		collectionFrame.setVisible(true);
	
}


}
class SaveMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = -7757562928692368254L;

	boolean saved = false;
	
	public SaveMenuBar(JPanel host) {
		//JMenu save = new JMenu("Save");
		//save.Liste
		JMenu save = new JMenu("Save");
		save.setMnemonic(KeyEvent.VK_S);
		save.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuSelected(MenuEvent arg0) {
				saveAll(host);
				GeneralUtilities.resetMainFrame();
				
			}
			
		});
		this.add(save);
		JMenu close = new JMenu("Close");
		close.setMnemonic(KeyEvent.VK_C);
		close.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuSelected(MenuEvent arg0) {
				if (!saved)  {
					if (JOptionPane.showConfirmDialog(null, "Data is unsaved, are you sure you'd like to continue?") == 0) {
						((JFrame)SwingUtilities.getWindowAncestor(host)).dispose();
		
						return;
				 
					}
				} else {
					((JFrame)SwingUtilities.getWindowAncestor(host)).dispose();
				}
				
				
			}
			
		});
		this.add(close);
	}
	public void saveAll(JPanel host) {
		saved = true;
		HashMap<Integer, Robot> robots = new HashMap<Integer, Robot>();
		for (Component c : host.getComponents()) {
			if (c instanceof RobotCollectionPanel) {
				robots.put(((RobotCollectionPanel)c).teamNumber, ((RobotCollectionPanel)c).getRobot());
			}
		}
		String tmpdir = System.getProperty("java.io.tmpdir");
		Object readObj = SerializeObject.ReadObjectFromFile(new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt"));
		if (readObj == null) {
			ConsoleFrame.output("This is the first time you're saving data, making new file...", Color.WHITE);
		SerializeObject.Serialize(new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt"), robots);
		} else {
			System.out.println("not null");
			ConsoleFrame.output("You already have data saved, adding new data to old...", Color.WHITE);
			robots.putAll((Map<Integer, Robot>) readObj);
			SerializeObject.Serialize(new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt"), robots);
		}
	}
}
class TeamPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8517803883102538614L;
	JButton addButton;
	JButton subtractButton;
	public JPanel textFieldPanel;
	ArrayList<JTextField> allBoxes;
	ArrayList<Component> allRigidBoxes;
	public TeamPanel(Color backgroundColor) {
		addButton = new JButton("+");
		subtractButton = new JButton("-");
		textFieldPanel = new JPanel();
		allBoxes = new ArrayList<JTextField>();
		allRigidBoxes = new ArrayList<Component>();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		textFieldPanel.add(Box.createRigidArea(new Dimension(15, 5)));
		textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
		addBox(backgroundColor);
		addBox(backgroundColor);
		addBox(backgroundColor);
		
		this.add(addButton);
		this.add(textFieldPanel);
		this.add(subtractButton);
		
		
		
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addBox(backgroundColor);
				SwingUtilities.getWindowAncestor(textFieldPanel).setVisible(true);
				SwingUtilities.getWindowAncestor(textFieldPanel).pack();
				}
			
		});
		subtractButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				removeBox();
				SwingUtilities.getWindowAncestor(textFieldPanel).setVisible(true);
				SwingUtilities.getWindowAncestor(textFieldPanel).pack();
				}
			
		});
	}
public void addBox(Color backgroundColor) {
	JTextField field = new JTextField();
	field.setBackground(backgroundColor);
	//field.setColumns(5);
	field.setMaximumSize(new Dimension(200, 60));
	
	
	allBoxes.add(field);
	textFieldPanel.add(field);
	Component box = Box.createRigidArea(new Dimension(5, 5));
	textFieldPanel.add(box);
	allRigidBoxes.add(box);
	

}
public void removeBox() {
	if (allBoxes.size() < 1)
		return;
	textFieldPanel.remove(allRigidBoxes.get(allRigidBoxes.size() - 1));
	textFieldPanel.remove(allBoxes.get(allBoxes.size() - 1));
	
	allBoxes.remove(allBoxes.size() - 1);
	allRigidBoxes.remove(allRigidBoxes.size() - 1);
}
}
