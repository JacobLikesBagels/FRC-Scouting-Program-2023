package me.lowen.jacob.Components.DebugThings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


public class CommandLinePanel extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7608377161157339061L;
	private Color black = Color.BLACK;
    private JPanel test;
    private JTextField field;
    private JScrollPane scrollFrame;
    private boolean askingForInput = false;
    private String lastLine;
    private Runnable whatToRunNext;
    private String lastReadInput;
    
    private HashMap<String, Runnable> commands;
    
    /**
     * Returns the last input the user entered in from the readNextLineThenRun method.
     * @return The latest input the user entered in from the readNextLineThenRun method
     */
    public String getLastReadInput() {
    	return lastReadInput;
    }
    /**
     * Returns the last entered text
     * @return The latest entered text
     */
    public String getLastLine() {
    	return lastLine;
    }
    public void readNextLineThenRun(Runnable runs) {
    	askingForInput = true;
    	whatToRunNext = runs;
    }
	
    private Runnable whatItRuns = new Runnable() {
		@Override
		public void run() {
			String v = field.getText();
            String cmd = doesCommandExist(v);
            send(v, Color.WHITE);
            if (cmd != null) {
            	
            	commands.get(cmd).run();
            } else {
            	send("Command not found!", Color.RED);
            }
            
		}
    	
    };
    
    /**
     * A command line interface JPanel. No commands are built in, you can add commands with the registerCommands method
     * @param size The size of the panel. Scroll bars will be added if text get too long or tall, so don't worry about running out of room
     */
    public CommandLinePanel(Dimension size) {

    	test = new JPanel();
    	//test.setPreferredSize(new Dimension( 200,200));
    	scrollFrame = new JScrollPane(test);
    	test.setAutoscrolls(true);
    	scrollFrame.setPreferredSize(size);
    	
    	scrollFrame.setAutoscrolls(true);
    	
    	
    	this.setAutoscrolls(true);
    	test.setBackground(Color.BLACK);
    	this.add(scrollFrame);
        test.setLayout(new BoxLayout(test, BoxLayout.Y_AXIS));
        field = new JTextField();
        field.setBackground(black);
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setMaximumSize(new Dimension(1000, 20));
        Border b = BorderFactory.createEmptyBorder();
        scrollFrame.setBorder(b);
        field.setBorder(b);
        System.out.println("sadsasd before: " + String.valueOf(commands == null));
        field.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String v = field.getText();
				lastLine = v;
				if (askingForInput) {
					lastReadInput = v;
					send(v, Color.WHITE);
					askingForInput = false;
					if (whatToRunNext != null)
					whatToRunNext.run();
					return;
					
				} else {
					whatItRuns.run();
				}
			}
        
        });
        this.setBackground(black);
        test.add(field);
      
        
        
       

    }
    /**
     * Lets you register 'commands'. When a user types a line that starts with a command in this list, it will autmatically run the runnnable
     * @param commandList
     * The String is what the command is (e.g. "help", "say"). The runnable argument is what code runs when a command is entered
     */
    public void registerCommands(HashMap<String, Runnable> commandList) {
    	commands = commandList;
    	for (String cmd: commands.keySet())
    		System.out.println(cmd + " is cmd");
    }
    public HashMap<String, Runnable> getCommands() {
    	return commands;
    }
    
    private String doesCommandExist(String line) {

    	String real = null;
    	
    	if (commands == null) {
    		return real;
    		
    	}
    	for (String cmd: commands.keySet()) {
    		if (line.toLowerCase().startsWith(cmd.toLowerCase())) {
    		
    		real = cmd.toLowerCase();
    		}
    	}
    	return real;
    }
    /**
     * Outputs text of the chosen color
     * @param text The text you want outputted
     * @param color The color of the text you want outputted
     */
    public void send(String text, Color color) {
    	test.remove(field);
        JLabel label = new JLabel(text);
        label.setBackground(black);
        label.setForeground(color);
        
        test.add(label);
        field.setText("");
        test.add(field);
        field.requestFocus();
        CommandLinePanel.this.repaint();
        CommandLinePanel.this.revalidate();
        JScrollBar bar = scrollFrame.getVerticalScrollBar();
        bar.revalidate();
        if (bar.isVisible()) {
        	
        	SwingUtilities.invokeLater(() -> {

                bar.setValue(bar.getMaximum());
        });
        }
        
    }
    /**
     * Instead of handling commands normally, you can choose what happens when a command is entered
     * @param run
     */
    public void overrideDefaultOnInputBehavoir(Runnable run) {
    	whatItRuns = run;
    }

}