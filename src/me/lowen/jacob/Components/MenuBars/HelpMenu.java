package me.lowen.jacob.Components.MenuBars;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import me.lowen.jacob.Components.AboutFrame;
import me.lowen.jacob.Components.DebugThings.ConsoleFrame;



public class HelpMenu extends JMenu {

	private static final long serialVersionUID = 4L;

	public HelpMenu() {
		super("Help");
		
		this.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem preferences = new JMenuItem("Debug Terminal", KeyEvent.VK_H);
		preferences.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
				
				ConsoleFrame.debug.setVisible(true);
		           // new DefaultPathsGUI().sourceAndOutputGUI("Change Your Default File Paths");
		    }
		});
		add(preferences);
		
		JMenuItem About = new JMenuItem("About", KeyEvent.VK_A);
		About.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	
		    	long first = System.currentTimeMillis();
		    	new AboutFrame();
		    	long second = System.currentTimeMillis();
		    	ConsoleFrame.output("The 'about' frame took " + (second - first) + " milliseconds to open. Resizing images hits hard", Color.WHITE);
		            
		    }
		});
		add(About);
	}



}