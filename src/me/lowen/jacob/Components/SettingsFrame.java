package me.lowen.jacob.Components;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import me.lowen.jacob.Utils.GeneralUtilities;
import me.lowen.jacob.Utils.Settings;

public class SettingsFrame extends JFrame{

	private static final long serialVersionUID = -2217000604079523299L;

	public SettingsFrame() {
		super("Settings");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new BooleanCollectionPanel("Compact Mode", Settings.getSettings().getBoolean("Compact Mode", false)));
		JButton button = new JButton("Save and Close");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Component c : panel.getComponents()) {
					if (c instanceof TextCollectionPanel) {
						Settings.getSettings().put(((TextCollectionPanel)c).getKey(), ((TextCollectionPanel)c).getText());
					} else if (c instanceof BooleanCollectionPanel) {
						Settings.getSettings().putBoolean(((BooleanCollectionPanel)c).getKey(), ((BooleanCollectionPanel)c).getChecked());
					}
				}
				SettingsFrame.this.dispose();
				GeneralUtilities.resetMainFrame();
			}
		});
		panel.add(button);
		this.add(panel);
		this.pack();
		this.setSize(300, this.getHeight());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}

class TextCollectionPanel extends JPanel{
	private static final long serialVersionUID = -2995342204839114724L;
	
	JTextField field;
	String key;
	
	public TextCollectionPanel(String key, String defaultValue) {
		this.key = key;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(new JLabel(key + ":"));
		field = new JTextField(defaultValue);
		this.add(field);
	}
	public String getKey() {
		return key;
	}
	public String getText() {
		return field.getText();
	}
}

class BooleanCollectionPanel extends JPanel {

	private static final long serialVersionUID = 3926597729147090659L;
	JCheckBox box;
	String key;
	
	public BooleanCollectionPanel(String key, boolean defaultValue) {
		this.key = key;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		box = new JCheckBox(key, defaultValue);
		this.add(box);
	}
	public String getKey() {
		return key;
	}
	public boolean getChecked() {
		return box.isSelected();
	}
}
