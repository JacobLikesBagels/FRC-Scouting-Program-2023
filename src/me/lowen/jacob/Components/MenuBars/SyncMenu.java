package me.lowen.jacob.Components.MenuBars;

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
						System.out.println("thing in sync");
						JOptionPane.showMessageDialog(null, "Could could not a server running on ip: " + ip, "Ip not found!", JOptionPane.ERROR_MESSAGE);
						client = null;
						e.printStackTrace();
						return;
					} catch (ConnectException e) {
						JOptionPane.showMessageDialog(null, "That server is not online, or could not be found!", "Connection refused", JOptionPane.ERROR_MESSAGE);
						client = null;
						e.printStackTrace();
						return;
					}
					JOptionPane.showMessageDialog(null, "Success! Found server at: " + ip, "Success!", JOptionPane.INFORMATION_MESSAGE);

		    	}
		    	client.sendRobotData();
		    }
		});
		add(refresh);
		
		JMenuItem exit = new JMenuItem("Start Server", KeyEvent.VK_S);
		exit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	
		    	if (threadOnline) {
		    		JOptionPane.showMessageDialog(null, "Each running server uses it's own thread, please be careful about creating too many", "Proceed with caution",
		    				JOptionPane.WARNING_MESSAGE);
		    	}
		    		
		    		serverThread = new Thread(()-> {
		    		server.startServer();
		    		
		    	});
		    		
		           serverThread.start();
		           threadOnline = true;
		           try {
					JOptionPane.showMessageDialog(null, "Server started on ip " + InetAddress.getLocalHost(), "Server Startup Succesful",
						        JOptionPane.INFORMATION_MESSAGE);
				} catch (HeadlessException | UnknownHostException e) {
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
