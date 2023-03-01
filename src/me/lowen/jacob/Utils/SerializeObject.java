package me.lowen.jacob.Utils;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import me.lowen.jacob.Components.DebugThings.ConsoleFrame;

public class SerializeObject {

	public SerializeObject() {
	}
	public static void Serialize(File path, Object obj) {
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(path);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	        objectOut.writeObject(obj);
	        objectOut.close();
	        ConsoleFrame.output("Successfully wrote the robots to a file", Color.RED);
		} catch (FileNotFoundException e) {
			ConsoleFrame.output("Could not write the robots to a file!", Color.RED);
        	ConsoleFrame.output(e.toString(), Color.RED);
			e.printStackTrace();
		} catch (IOException e) {
			ConsoleFrame.output("Could not write the robots to a file!", Color.RED);
        	ConsoleFrame.output(e.toString(), Color.RED);
			e.printStackTrace();
		}
        
	}
	 public static Object ReadObjectFromFile(File path) {
		 
	        try {
	        	if (!path.exists()) 
	        		return null;
	            FileInputStream fileIn = new FileInputStream(path);
	            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	 
	            Object obj = objectIn.readObject();
	            
	            
	            objectIn.close();
	            ConsoleFrame.output("The stored robots were succesfully read from the file", Color.WHITE);
	            return obj;
	 
	        } catch (StreamCorruptedException ex) {
	        	ConsoleFrame.output("Could not read the stored robots, Likley because the file isnt a stored robots file: ", Color.RED);
	        	ConsoleFrame.output(ex.toString(), Color.RED);
	            ex.printStackTrace();
	            return null;
	        } catch (IOException e) {
	        	ConsoleFrame.output("Could not read the stored robots, Likley because the file does not exist", Color.RED);
	        	ConsoleFrame.output(e.toString(), Color.RED);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				ConsoleFrame.output("Could not read the stored robots, we don't know why, maybe the file isnt a stored robots file?", Color.RED);
	        	ConsoleFrame.output(e.toString(), Color.RED);
				e.printStackTrace();
			}
	        return null;
	    }

}
