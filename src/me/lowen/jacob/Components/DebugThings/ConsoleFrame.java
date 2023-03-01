package me.lowen.jacob.Components.DebugThings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JFrame;

public class ConsoleFrame extends JFrame{

	private static final long serialVersionUID = 6408159905401333645L;
	private CommandLinePanel cl;
	public static ConsoleFrame debug;
	public static void output(String text, Color color) {
		debug.cl.send(text, color);
	}
	public ConsoleFrame() {
		super("Debug Terminal");
		//this.setSize(400, 300);
		cl = new CommandLinePanel(new Dimension(600, 400));
		HashMap<String, Runnable> cmds = new HashMap<String, Runnable>();
		Runnable s = new Runnable()  {

			@Override
			public void run() {
				cl.send("There are no commands registered as this console is purley for debug", Color.RED);
				
			}
			
		};
		
		cmds.put("hi", s);
		cl.overrideDefaultOnInputBehavoir(s);
		
		
		this.setLayout(new GridLayout());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.add(cl);
		this.pack();
		
	}
	
}
