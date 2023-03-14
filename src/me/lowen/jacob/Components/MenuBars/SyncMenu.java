package me.lowen.jacob.Components.MenuBars;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import me.lowen.jacob.Components.DebugThings.ConsoleFrame;
import me.lowen.jacob.Components.Syncing.RobotClient;
import me.lowen.jacob.Components.Syncing.RobotServer;

public class SyncMenu extends JMenu{
	private RobotServer server;
	private RobotClient client;

	Thread serverThread;
	
	boolean threadOnline = false;
	
	private static final long serialVersionUID = 1L;

	
	
	public SyncMenu(){
		
		super("Sync");
		this.setMnemonic(KeyEvent.VK_S);
		server = new RobotServer();
		client = null;
		
		JMenuItem refresh = new JMenuItem("Send Data", KeyEvent.VK_D);
		refresh.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    		//client.sendData();
		    	
		    	if (client == null) {
		    		String ip = JOptionPane.showInputDialog("Enter the server's IP address");
		    		client = new RobotClient();
		    		try {
						client.getSetup(ip);
					} catch (UnknownHostException e) {
						ConsoleFrame.output("Couldn't find an active server on the ip " + ip + "!", Color.RED);
						JOptionPane.showMessageDialog(null, "Could could not a server running on ip: " + ip, "Ip not found!", JOptionPane.ERROR_MESSAGE);
						client = null;
						e.printStackTrace();
						return;
					} catch (ConnectException e) {
						ConsoleFrame.output("Couldn't send data to the server at " + ip + ", this is likey because the server is not online yet, or is an invalid recipient", Color.RED);
						JOptionPane.showMessageDialog(null, "That server is not online, or could not be found!", "Connection refused", JOptionPane.ERROR_MESSAGE);
						client = null;
						
						e.printStackTrace();
						return;
					}
		    		ConsoleFrame.output("Server found at " + ip + "! Sending data...", Color.WHITE);
					JOptionPane.showMessageDialog(null, "Success! Found server at: " + ip, "Success!", JOptionPane.INFORMATION_MESSAGE);

		    	}
		    	client.sendRobotData();
		    	ConsoleFrame.output("Data to " + client.getIP() + " successfully sent!", Color.WHITE);
		    }
		});
		add(refresh);
		
		JMenuItem exit = new JMenuItem("Start Server", KeyEvent.VK_S);
		exit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	
		    	if (threadOnline) {
		    		ConsoleFrame.output("Another server was started", Color.YELLOW);
		    		JOptionPane.showMessageDialog(null, "Each running server uses it's own thread, please be careful about creating too many", "Proceed with caution",
		    				JOptionPane.WARNING_MESSAGE);
		    	}
		    		
		    		serverThread = new Thread(()-> {
		    		server.startServer();
		    		
		    	});
		    		
		           serverThread.start();
		           threadOnline = true;
		           try {
		        	ConsoleFrame.output("Starting up a server in ip " + InetAddress.getLocalHost() + ". Omit the computer name if present", Color.white);
					JOptionPane.showMessageDialog(null, "Server started on ip " + InetAddress.getLocalHost(), "Server Startup Succesful",
						        JOptionPane.INFORMATION_MESSAGE);
				} catch (HeadlessException | UnknownHostException e) {
					ConsoleFrame.output("Something went wrong with server creation! Error Below:", Color.RED);
					ConsoleFrame.output(e.getMessage(), Color.RED);
					JOptionPane.showMessageDialog(null, "Something went wrong with server creation!", "Server Startup Failed",
					        JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} 
		           
		    	/*} else {
		    		
		    		JOptionPane.showMessageDialog(null, "Server is already running! " , "Server Startup Canceled",
					        JOptionPane.ERROR_MESSAGE);
		    		System.out.println("Thread is already running!");
		    	} */
		    }
		});
		
		add(exit);
		JMenuItem killNicely = new JMenuItem("Kill Server Nicely", KeyEvent.VK_N);
		killNicely.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	try {
				Socket close = new Socket("localhost", 8888);
				close.getOutputStream().write(-1);
				//close.getOutputStream().flush();
				close.close();
				ConsoleFrame.output("Kindly asking the server to stop working", Color.WHITE);
				JOptionPane.showMessageDialog(null, "The server will stop accepting data, but the thread is still running cause I'm stupid and can't kill a thread for the life of me");
				return;
		    	} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		    	JOptionPane.showMessageDialog(null, "There's no server running you silly billy");
		    }
		    
		    
		});
		add(killNicely);
		/*JMenuItem killServerMeanly = new JMenuItem("Kill Server Meanly", KeyEvent.VK_M);
		killServerMeanly.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	System.out.println( serverThread.isInterrupted());
		    	serverThread.interrupt();
		    	serverThread.stop();
		    	System.out.println( serverThread.isInterrupted());
		    	
		    	
		    		//new RobotClient().sendData();
		    }
		});
		add(killServerMeanly); */
		
}
}
