package me.lowen.jacob.Utils;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.prefs.Preferences;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import me.lowen.jacob.Components.DebugThings.ConsoleFrame;

public class Settings {
	private static Preferences settings;
	public static Preferences getSettings() {
		if (settings == null) 
			setup();
		return settings;
	}
	public static void setup() {
		
		try {
		settings = Preferences.userRoot();
		
		} catch (SecurityException e) {
			ConsoleFrame.output("Coule not set up settings, this is likley because this program doesn't have the priviledge", Color.RED);
			JOptionPane.showMessageDialog(null, "Could not setup settings: Access Denied", "Failed reading setting", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static LayoutManager getChosenLayout(BoxLayout layout) {
		if (settings == null) 
			setup();
		if (settings.getBoolean("Compact Mode", false))
			return layout;
		return new FlowLayout(FlowLayout.LEFT);
	}
	
}
