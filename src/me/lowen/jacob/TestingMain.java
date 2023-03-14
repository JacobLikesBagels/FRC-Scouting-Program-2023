package me.lowen.jacob;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import me.lowen.jacob.Components.MainMenuFrame;
import me.lowen.jacob.Components.NewAddMoreDataFrame;
import me.lowen.jacob.Components.Robot;
import me.lowen.jacob.Components.RobotCollectionPanel;
import me.lowen.jacob.Components.RobotDisplayFrame;
import me.lowen.jacob.Components.SettingsFrame;
import me.lowen.jacob.Components.DebugThings.ConsoleFrame;
import me.lowen.jacob.Utils.SerializeObject;

public class TestingMain {
public static JPanel panel;
	public static MainMenuFrame theMainFrame;
	public static void main(String[] args) {
		
	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		} 
		
		ConsoleFrame.debug = new ConsoleFrame();
		String tmpdir = System.getProperty("java.io.tmpdir");
		Object readObj = SerializeObject.ReadObjectFromFile(new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt"));
		
		theMainFrame = new MainMenuFrame(((Map<Integer, Robot>) readObj));
	}

}
