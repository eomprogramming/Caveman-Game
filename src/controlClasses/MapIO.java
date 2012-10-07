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
	private LineNumberReader readx;
	
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
	 * The above was sarcasm.
	 * @return The board described in the file, or null if the file is malformed.
	 * @throws IOException if the file data fall off a cliff on their way to from the
	 * filesystem to the program.
	 */
	public Boardx read() throws IOException {
		try {
			
			read = new ObjectInputStream(new FileInputStream(filename));
			Object o = read.readObject();
			if(o instanceof Board) // original format
			{
				return new Boardx(((Board) o).getBoard());
			}
			if (o instanceof int[][]) { // second format
				Boardx b = new Boardx((int[][]) o);
				return b;
			}
			/* 
			 * There is no way for read.readObject() to not throw an exception, but to
			 * return an object of a type other than the above: the file must be
			 * malformed.
			 */
			return null;
		} catch (ClassNotFoundException e) {
			// this never happens
			return null;
		} catch (ObjectStreamException ex) { // new (hopefully final) format
			return readNewFormat();
		} catch(FileNotFoundException exp){
			JOptionPane.showMessageDialog(null,
					"Please make sure you are selecting a .map file!");
			return null;
		} finally {
			if (read != null)
				read.close();
			if (readx != null)
				readx.close();
		}
	}
	
	/*
	 * A small utility method to parse the new format.
	 */
	private Boardx readNewFormat() throws FileNotFoundException, IOException {
		String buffer;
		String block;
		EditBoard board = new EditBoard();
		readx = new LineNumberReader(new FileReader(filename));
		while ((buffer = readx.readLine()) != null) {
			block = buffer.trim();
			if (block.endsWith("{")) {
				block = block.substring(0, block.length()-1);
			} else {
				continue;
			}
			if (block.equals("map")) {
				String currLine;
				String[] elems;
				int[][] cells = new int[board.BOARD_HEIGHT][board.BOARD_WIDTH];
				try {
					for (int i = 0;
							!(currLine = readx.readLine()).trim().endsWith("}"); i++) {
						elems = currLine.split("\t");
						for (int j = 0; j < elems.length; j++) {
							cells[i][j] = Integer.parseInt(elems[j]);
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					// The name of this exception is way too long
					// It will indicate a malformed file.
					return null;
				}
				board.board = cells;
			} else if (block.equals("theme")) {
				String t = readx.readLine().trim();
				board.setTheme(t.equals("null") ? null : t);
				while (!readx.readLine().endsWith("}"))
					continue;
			} else { //skip unidentified block
				while (!readx.readLine().endsWith("}"))
					continue;
			}
		}
		return board;
	}
	
	/**
	 * It writes stuff.
	 * @param b the board
	 * @throws IOException if the data are attacked by highwaymen on the way to the
	 * filesystem.
	 */
	public void write(Boardx b) throws IOException {
		PrintWriter w = new PrintWriter(filename);
		
		/* Write out map */
		w.println("map{");
		for (int[] i: b.getBoard()) {
			for (int j: i) {
				w.print(j);
				w.print('\t');
			}
			w.println();
		}
		w.println("}");
		
		/* Write out theme */
		w.println("theme{");
		w.println(b.theme);
		w.println("}");
		
		w.close();
	}

}
