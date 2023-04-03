package me.lowen.jacob.Components.MenuBars;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import me.lowen.jacob.Components.Robot;
import me.lowen.jacob.Components.DebugThings.ConsoleFrame;
import me.lowen.jacob.Utils.GeneralUtilities;
import me.lowen.jacob.Utils.RobotExportOptions;
import me.lowen.jacob.Utils.SerializeObject;

public class DataMenu extends JMenu{

	private static final long serialVersionUID = 6233748242233891029L;
	
	public DataMenu() {
		super("Data");
		this.setMnemonic(KeyEvent.VK_D);
		
		JMenuItem refresh = new JMenuItem("Import Data", KeyEvent.VK_I);
		refresh.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	String tmpdir = System.getProperty("java.io.tmpdir");
		    	File storedFile = new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt");
		    	if (storedFile.exists()) {
		    		Object[] options = {"Yes",
		    		        "No", "Merge data instead"};
		    		    	int input = JOptionPane.showOptionDialog(null, "Are you sure you would like to import? it will clear all the existing data", 
		    		      "Please select an option", 
		    		      JOptionPane.YES_NO_OPTION, 
		    		      JOptionPane.WARNING_MESSAGE, 
		    		      null, options, options[2]);
		    		    	if (input == 1) 
		    		    		return;
		    		    	if (input == 2) {
		    		    		mergeData();
		    		    		return;
		    		    	}
		    		    	
		    	} 
		    		JFileChooser j = new JFileChooser();
					j.setFileSelectionMode(JFileChooser.FILES_ONLY);
					j.setDialogTitle("Choose where to import data from");
					@SuppressWarnings("unused")
					Integer opt = j.showSaveDialog(new JFrame());
					File f = j.getSelectedFile();
					
					InputStream is = null;
			        OutputStream os = null;
			        try {
			            is = new FileInputStream(f);
			            os = new FileOutputStream(storedFile);

			            // buffer size 1K
			            byte[] buf = new byte[1024];

			            int bytesRead;
			            while ((bytesRead = is.read(buf)) > 0) {
			                os.write(buf, 0, bytesRead);
			            }
			            ConsoleFrame.output("Robot data successfullt imported", Color.WHITE);
			        }  catch (IOException e) {
						ConsoleFrame.output("Robot data failed to import:", Color.RED);
						ConsoleFrame.output(e.toString(), Color.RED);
						e.printStackTrace();
					} finally {
			            try {
							is.close();
							os.close();
						} catch (IOException e) {
							ConsoleFrame.output("Robot data failed to import (hit finally):", Color.RED);
							ConsoleFrame.output(e.toString(), Color.RED);
							e.printStackTrace();
						}
			            
			        
					
		    	}
			        GeneralUtilities.resetMainFrame();
		    	}
		    
		});
		add(refresh);
		
		JMenuItem export = new JMenuItem("Export Data", KeyEvent.VK_E);
		export.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	//all this cod ei this file is terrible ir r egoncie this now
		    	String tmpdir = System.getProperty("java.io.tmpdir");
		    	File storedFile = new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt");
		    	JFileChooser j = new JFileChooser("sd");
				j.setFileSelectionMode(JFileChooser.FILES_ONLY);
				j.setDialogTitle("Choose where to export data");
				@SuppressWarnings("unused")
				Integer opt = j.showSaveDialog(new JFrame());
				File f = j.getSelectedFile();
				RobotExportOptions.copyFromHereToThere(storedFile, f);
		    }
		});
		add(export);
		
		JMenuItem merge = new JMenuItem("Merge Data", KeyEvent.VK_M);
		merge.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		            mergeData();
		            GeneralUtilities.resetMainFrame();
		    }
		});
		add(merge);
		
		addSeparator();
		
		JMenu otherExportOptions = new JMenu("Other Export Options");
		this.add(otherExportOptions);
		
		JMenuItem exportJSON = new JMenuItem("Export Data as JSON", KeyEvent.VK_E);
		exportJSON.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	//all this cod ei this file is terrible ir r egoncie this now
		    	String tmpdir = System.getProperty("java.io.tmpdir");
		    	File storedFile = new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt");
		    	JFileChooser j = new JFileChooser("sd");
				j.setFileSelectionMode(JFileChooser.FILES_ONLY);
				j.setDialogTitle("Choose where to export JSON data");
				@SuppressWarnings("unused")
				Integer opt = j.showSaveDialog(new JFrame());
				File f = j.getSelectedFile();
				HashMap<Integer, Robot> robots = (HashMap<Integer, Robot>) SerializeObject.ReadObjectFromFile(new File(GeneralUtilities.robotFilePath));
				RobotExportOptions.exportAsJSON(robots, f);
		    }
		});
		otherExportOptions.add(exportJSON);
		
		JMenuItem exportYAML = new JMenuItem("Export Data as YAML", KeyEvent.VK_E);
		exportYAML.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	//all this cod ei this file is terrible ir r egoncie this now
		    	String tmpdir = System.getProperty("java.io.tmpdir");
		    	File storedFile = new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt");
		    	JFileChooser j = new JFileChooser("sd");
				j.setFileSelectionMode(JFileChooser.FILES_ONLY);
				j.setDialogTitle("Choose where to export YAML data");
				@SuppressWarnings("unused")
				Integer opt = j.showSaveDialog(new JFrame());
				File f = j.getSelectedFile();
				HashMap<Integer, Robot> robots = (HashMap<Integer, Robot>) SerializeObject.ReadObjectFromFile(new File(GeneralUtilities.robotFilePath));
				RobotExportOptions.exportAsYAML(robots, f);
		    }
		});
		otherExportOptions.add(exportYAML);
		
		this.addSeparator();
		
		JMenuItem clear = new JMenuItem("Clear Data", KeyEvent.VK_C);
		clear.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	deleteData("Are you sure you would like to clear all of your data?");
		    	GeneralUtilities.resetMainFrame();
		    }
		});
		add(clear);
		
	}
	private void deleteData(String text) {
		Object[] options = {"Yes",
        "No"};
    	int input = JOptionPane.showOptionDialog(null, text, 
      "Please select an option", 
      JOptionPane.YES_NO_OPTION, 
      JOptionPane.WARNING_MESSAGE, 
      null, options, options[1]);
    	if (input == 0) {
    		String tmpdir = System.getProperty("java.io.tmpdir");
    	File fileToDelete = new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt");
    	if (!fileToDelete.exists()) {
    		JOptionPane.showConfirmDialog(null, "There is no data stored!", "Error",
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
    			return;
    	}
    	if (fileToDelete.delete()) {
    		ConsoleFrame.output("Robot data successfully deleted", Color.WHITE);
    		int input3 = JOptionPane.showConfirmDialog(null, "Data successfully cleared","Information",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    	} else {
    		ConsoleFrame.output("Failed to delete robot data!", Color.RED);
    		try {
				ConsoleFrame.output("If you would like, you can manually delete the data at " + fileToDelete.getCanonicalPath(), Color.RED);
			} catch (IOException e) {
				ConsoleFrame.output("This double fucked up, not only did the robot data not delete, we also couldn't fetch the canonical pathname of the file:", Color.RED);
				ConsoleFrame.output("You can try to delete the data, at this non-absolute, non-canonical path: " + fileToDelete.getPath(), Color.RED);
				e.printStackTrace();
			}
    		System.out.println(fileToDelete.getAbsolutePath());
    		int input4 = JOptionPane.showConfirmDialog(null, "File failed to delete", "Error",
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
    	}
    	}
	}
	
	public void mergeData() {
		String tmpdir = System.getProperty("java.io.tmpdir");
    	File storedFile = new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt");
		JFileChooser j = new JFileChooser();
		j.setFileSelectionMode(JFileChooser.FILES_ONLY);
		@SuppressWarnings("unused")
		Integer opt = j.showSaveDialog(new JFrame());
		File f = j.getSelectedFile();
		Object storedObj1 = SerializeObject.ReadObjectFromFile(storedFile);
		Map<Integer, Robot> stored1 = (Map<Integer, Robot>) storedObj1;
		Object storedObj2 = SerializeObject.ReadObjectFromFile(f);
		Map<Integer, Robot> stored2 = (Map<Integer, Robot>) storedObj2;
		stored2.putAll(stored1);
		SerializeObject.Serialize(storedFile, stored2);
		
	}

}
 