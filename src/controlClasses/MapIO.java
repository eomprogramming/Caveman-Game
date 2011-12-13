package controlClasses;

import java.io.*;

import javax.swing.JOptionPane;

/**
 * A MapIO class represents a source of map data (Board objects).
 * @author Ian
 *
 */
public class MapIO {
	private String filename;
	private ObjectInputStream read;
	private ObjectOutputStream write;
	
	/**
	 * Hi, I'm MapIO's constructor, and I'd love to get to know you better.
	 * My interests are creating new MapIO objects and using the java.io package.
	 * Someday,  I'd like to be able to open files over networks too!
	 * @param fileToOpen If I have to explain what this means to you, you might want to
	 *        switch to being a member of the cooking club.
	 * @throws IOException if the messenger to the underlying filesystem is stopped
	 *        by the Black Knight.
	 */
	public MapIO(String fileToOpen) throws IOException{
		filename = fileToOpen;
		//Someday, there should be a version check here.
	}
	
	/**
	 * Never fear, amnesiacs! If you forget the filename, getFileName() is here to help!
	 * It can't help you with your own name though. And it has weird capitalisation.
	 * @return A retarded lumpfish could figure this one out.
	 */
	public String getFileName() {
		return filename;
	}
	
	/**
	 * Obviously, the read() method <b>writes</b> the file to the disk. Any moron could
	 * figure that out.<br/>
	 * <span style="font-size: 9pt;">The above was sarcasm.</span>
	 * @return The board described in the file, or null if the file is malformed.
	 * @throws IOException if the file data falls off a cliff on their way to from the
	 * filesystem to the program.
	 */
	public Board read() throws IOException {
		try {
			read = new ObjectInputStream(new FileInputStream(filename));
			Board b = (Board) read.readObject();
			read.close();
			return b;
		} catch (ClassNotFoundException e) {
			// this never happens
			return null;
		}catch(FileNotFoundException exp){
			JOptionPane.showMessageDialog(null,"Please make sure you are selecting a .map file!");
			return null;
		}
	}
	
	/**
	 * It writes stuff.
	 * @param b the board
	 * @return 
	 * @throws IOException if the data is attacked by highwaymen on the way to the
	 * filesystem.
	 */
	public void write(Board b) throws IOException {
		write = new ObjectOutputStream(new FileOutputStream(filename));
		write.writeObject(b);
		write.flush();
		write.close();
	}

}
