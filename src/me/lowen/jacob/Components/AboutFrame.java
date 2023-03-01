package me.lowen.jacob.Components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutFrame extends JFrame{

	public AboutFrame() {
		super("About this program");
		JPanel aboutPanel = new JPanel();
		aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));
		
		ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/trans_6101_logo.png"));
		Image editImage = ii.getImage(); // transform it 
		Image newimg = editImage.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		ii = new ImageIcon(newimg);
	
		JLabel image = new JLabel(ii);
		image.setSize(30, 30);
		aboutPanel.add(image);
		
		aboutPanel.add(new JLabel(" This program was made by Jacob L, a member of the 6101 WTMC robotics team."));
		aboutPanel.add(new JLabel(" Lisence: GPL-3.0"));
		aboutPanel.add(new JLabel(" Started prodcution in October, 2022"));
		JPanel hyperLinkPanel = new JPanel();
		hyperLinkPanel.setLayout(new BoxLayout(hyperLinkPanel, BoxLayout.X_AXIS));
		hyperLinkPanel.add(new JLabel("Check out 6101's website: "));
		JLabel hyperlink = new JLabel(" Check out 6101's website");
		hyperlink.setForeground(Color.BLUE.darker());
        hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
 
        hyperlink.addMouseListener(new MouseAdapter() {
 
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://wtmcrobotics.com/"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }});
        hyperLinkPanel.add(hyperlink);
        aboutPanel.add(hyperlink);
	//	aboutPanel.add(new JLabel("<html>Check out 6101's website: <a href=\"http://wtmcrobotics.com/\">http://wtmcrobotics.com/</a></html>"));
		this.setResizable(false);
		this.add(aboutPanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
		
	}

}
