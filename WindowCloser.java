/**
 Class WindowCloser allows the window to close and stores the current user object

 @author Sulaiman Bakali Mueden
 @version 1
 */

import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.FileWriter;

class WindowCloser extends WindowAdapter{
	public void windowClosing(WindowEvent evt) {
		String frameClass = evt.getSource().getClass().toString();
		if (frameClass.equals("class GUIApp")) {
			try {
				String filename = "userStorage.txt";
				FileOutputStream outputStream = new FileOutputStream(filename);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
				objectOutputStream.writeObject(GUIApp.getUser());
				objectOutputStream.close();
				outputStream.close();
			}
			catch (IOException exception){
				//exception.printStackTrace();
			}
		}

		System.exit(0);
	}
}