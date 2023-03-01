package me.lowen.jacob.Components.MenuBars;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import me.lowen.jacob.TestingMain;
import me.lowen.jacob.Components.Robot;
import me.lowen.jacob.Components.RobotDisplayFrame;
import me.lowen.jacob.Utils.GeneralUtilities;
import me.lowen.jacob.Utils.SerializeObject;


public class FileMenu extends JMenu{

	private static final long serialVersionUID = 1L;

	public FileMenu(){
		
		super("File");
		this.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem refresh = new JMenuItem("Refresh", KeyEvent.VK_R);
		refresh.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    		GeneralUtilities.resetMainFrame();
		    }
		});
		add(refresh);
		
		JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_E);
		exit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		            System.exit(0);
		    }
		});
		add(exit);
		
	
	}
		

}