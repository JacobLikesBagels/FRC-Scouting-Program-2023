package me.lowen.jacob.Components.MenuBars;

import java.awt.Color;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import me.lowen.jacob.TestingMain;
import me.lowen.jacob.Components.Robot;
import me.lowen.jacob.Components.RobotDisplayFrame;
import me.lowen.jacob.Components.DebugThings.ConsoleFrame;
import me.lowen.jacob.Utils.GeneralUtilities;
import me.lowen.jacob.Utils.SerializeObject;

public class RobotServer {
			
	ObjectInputStream dIn;
	ServerSocket ss;
	Socket socket;
	
	synchronized public void startServer() {
			System.out.println("started");
			try {
				ss = new ServerSocket(8888);
				socket = ss.accept();
				dIn = new ObjectInputStream(socket.getInputStream());

				boolean done = false;
				while(!done) {
					byte messageType = 0;
					if (dIn.available() > 0)
						messageType = dIn.readByte();

				  if (messageType == 1) {
					  try {
						HashMap<Integer, Robot> bots = (HashMap<Integer, Robot>) dIn.readObject();
						String tmpdir = GeneralUtilities.tmpdir;
						Object readObj = SerializeObject.ReadObjectFromFile(new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt"));
						if (readObj == null) {
							ConsoleFrame.output("This is the first time you're saving data, making new file...", Color.WHITE);
						SerializeObject.Serialize(new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt"), bots);
						} else {
							System.out.println("not null");
							ConsoleFrame.output("You already have data saved, adding new data to old...", Color.WHITE);
							bots.putAll((Map<Integer, Robot>) readObj);
							SerializeObject.Serialize(new File(tmpdir + System.getProperty("file.separator") + "storedrobots.txt"), bots);
						}
						System.out.println("foint it");
						TestingMain.theMainFrame.rdf.removeAll(); 
			            TestingMain.theMainFrame.rdf.add(new RobotDisplayFrame(bots));
			            TestingMain.theMainFrame.rdf.revalidate();
			            TestingMain.theMainFrame.rdf.repaint();
			            SwingUtilities.getWindowAncestor(TestingMain.theMainFrame.rdf).pack();
			            System.out.println("fs");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						System.out.println("read failed");
						e.printStackTrace();
					}
					  
				  } else if (messageType == 2) {
					  System.out.println(dIn.readUTF());
				  } else if (messageType == -1) {
					  System.out.println("closesdd");
					 // dIn.close();
					  //socket.close();
					  //ss.close();
					  done = true;
				  }
				 /* switch(messageType)
				  {
				  case 1: // Type A
				    System.out.println("Message A: " + dIn.readUTF());
				    break;
				  case 2: // Type B
				    System.out.println("Message B: " + dIn.readUTF());
				    break;
				  case 3: // Type C
				    System.out.println("Message C [1]: " + dIn.readUTF());
				    System.out.println("Message C [2]: " + dIn.readUTF());
				    break;
				  default:
				    done = true;
				  } */
				}
				System.out.println("closed");
				dIn.close();
			}catch (EOFException e) {
				System.out.println("Reach EOF");
				try {
					//dIn.close();
					socket.close();
					ss.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}	
			catch (IOException e) {
				e.printStackTrace();
			}
	}
		synchronized public void stopServer() {
			try {
				dIn.close();
				socket.close();
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
