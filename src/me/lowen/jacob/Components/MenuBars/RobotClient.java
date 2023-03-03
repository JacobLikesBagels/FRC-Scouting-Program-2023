package me.lowen.jacob.Components.MenuBars;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import me.lowen.jacob.Utils.GeneralUtilities;
import me.lowen.jacob.Utils.SerializeObject;

public class RobotClient {

	ObjectOutputStream dOut;
	
	synchronized public void getSetup(String ip) throws UnknownHostException, ConnectException {
		try {
			System.out.println("fdf");
			
			Socket socket = new Socket(ip, 8888); // Create and connect the socket
			dOut = new ObjectOutputStream(socket.getOutputStream());
	
				
			
			
		} catch (UnknownHostException e) {
			throw new UnknownHostException();
		} catch (ConnectException e) {
			throw new ConnectException();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	synchronized public void sendRobotData() {
		

		// Send first message
		try {
			

			//Object hi = SerializeObject.ReadObjectFromFile(new File(GeneralUtilities.robotFilePath));
			Object hi = SerializeObject.ReadObjectFromFile(new File(GeneralUtilities.robotFilePath));
			//Robot hi2 = new Robot(100, false, false, "sdf", false, false, "Fdsf", "aw", false, "fdf");
			//((HashMap<Integer, Robot>)hi).put(100, hi2);
			// Send first message
			
//			dOut.writeByte(1);
//			dOut.writeUTF("This is the first type of message.");
//			dOut.flush(); // Send off the data
//
//			// Send the second message
//			dOut.writeByte(2);
//			dOut.writeUTF("This is the second type of message.");
//			dOut.flush(); // Send off the data
//
//			// Send the third message
//			dOut.writeByte(3);
//			dOut.writeUTF("This is the third type of message (Part 1).");
//			dOut.writeUTF("This is the third type of message (Part 2).");
//			dOut.flush(); // Send off the data
			dOut.writeByte(1);
			dOut.writeObject(hi);
			// Send the exit message
			//dOut.writeByte(-1);
			dOut.flush();

			//dOut.close();
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendData(String hii) {
		try {
			dOut.writeByte(2);
			dOut.writeUTF("fdfdfdf");
			dOut.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
