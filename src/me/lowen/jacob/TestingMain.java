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
import me.lowen.jacob.Components.DebugThings.ConsoleFrame;
import me.lowen.jacob.Utils.SerializeObject;

public class TestingMain {
public static JPanel panel;
	public static MainMenuFrame theMainFrame;
	public static void main(String[] args) {
		
	/*	JFrame  frame = new JFrame("hehehehaw");
		panel = new JPanel();
		frame.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(new RobotCollectionPanel(95, Color.RED));
		panel.add(new RobotCollectionPanel(94, Color.RED));
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null); */
	//	new RobotDisplayFrame(null);
		
		//JFrame s = new JFrame("sdasd");
		//s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		} 
		ConsoleFrame.debug = new ConsoleFrame();
//		ConsoleFrame.debug.setVisible(true);
		String tmpdir = System.getProperty("java.io.tmpdir");
		Object readObj = SerializeObject.ReadObjectFromFile(new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt"));
	//	s.add(new RobotDisplayFrame((Map<Integer, Robot>) readObj));
		//s.setSize(100 ,100);
		//s.setVisible(true);
		//new ConsoleFrame();
		
		
		theMainFrame = new MainMenuFrame(((Map<Integer, Robot>) readObj));
	}

}
