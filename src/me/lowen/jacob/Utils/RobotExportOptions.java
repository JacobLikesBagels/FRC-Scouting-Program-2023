package me.lowen.jacob.Utils;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import org.json.JSONObject;

import me.lowen.jacob.Components.Robot;
import me.lowen.jacob.Components.DebugThings.ConsoleFrame;

public class RobotExportOptions {

	public static boolean exportAsJSON(HashMap<Integer, Robot> robots, File outputFile) {
		JSONObject allBots = new JSONObject();
		for (int i : robots.keySet()) {
			Robot bot = robots.get(i);
			JSONObject singleBot = new JSONObject();
			singleBot.put("isEveryBot", bot.isEveryBot);
			singleBot.put("lowerPole",  bot.lowerPole);
			singleBot.put("middleShelf",  bot.middleShelf);
			singleBot.put("upperPole",  bot.upperPole);
			singleBot.put("upperShelf",  bot.upperShelf);
			singleBot.put("autonMeasure",  bot.autonMeasure);
			singleBot.put("extraNotes",  bot.extraNotes);
			singleBot.put("poleNotes",  bot.poleNotes);
			singleBot.put("shelfNotes",  bot.shelfNotes);
			allBots.put(String.valueOf(bot.teamNumber), singleBot);
		}
		try {
			PrintWriter wtr = new PrintWriter(outputFile);
			allBots.write(wtr, 2, 0);
			wtr.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public static boolean exportAsYAML(HashMap<Integer, Robot> robots, File outputFile) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(outputFile));
			for (int num : robots.keySet()) {
				Robot bot = robots.get(num);
				br.write(num + ":" + "\n");
				br.write("  isEveryBot: " + bot.isEveryBot + "\n");
				br.write("  lowerPole: " +  bot.lowerPole + "\n");
				br.write("  middleShelf: " + bot.middleShelf + "\n");
				br.write("  upperPole: " +  bot.upperPole + "\n");
				br.write("  upperShelf: " +  bot.upperShelf + "\n");
				br.write("  autonMeasure: " +  bot.autonMeasure + "\n");
				br.write("  extraNotes: " + bot.extraNotes + "\n");
				br.write("  poleNotes: " +  bot.poleNotes + "\n");
				br.write("  shelfNotes: " +  bot.shelfNotes + "\n");
			}
			br.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void copyFromHereToThere(File here, File there) {
		InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(here);
            os = new FileOutputStream(there);

            // buffer size 1K
            byte[] buf = new byte[1024];

            int bytesRead;
            while ((bytesRead = is.read(buf)) > 0) {
                os.write(buf, 0, bytesRead);
            }
            ConsoleFrame.output("Successfully exported data to " + there.getPath(), Color.WHITE);
        }  catch (IOException e) {
        	ConsoleFrame.output("Robot data failed to export:", Color.RED);
			ConsoleFrame.output(e.toString(), Color.RED);
			e.printStackTrace();
		} finally {
            try {
				is.close();
				os.close();
			} catch (IOException e) {
				ConsoleFrame.output("Robot data failed to export (hit finally):", Color.RED);
				ConsoleFrame.output(e.toString(), Color.RED);
				e.printStackTrace();
			}
            
        }
	}
	

}
