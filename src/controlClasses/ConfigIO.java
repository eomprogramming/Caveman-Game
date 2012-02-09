package controlClasses;

import java.io.*;

/**
 * Contains methods for writing configuration data to and reading from a
 * <code>.haox</code> file. The only way to get a ConfigIO is to call the static
 * getDefaultConfigIO() method to get one bound to the default file
 * (~/.caveman.haox).
 * 
 * @author Ian Dewan
 * 
 */
public class ConfigIO {
	private static ConfigIO defaultCIO = null;
	private File configFile;
	
	private String lastFile = null;
	private String defaultTheme = null;
	
	/**
	 * Creates a new ConfigIO that reads data from the file f.
	 * 
	 * @param f the file to read configuration data from
	 * @throws IllegalArgumentException
	 *             if the file is not creatable/readable.
	 */
	protected ConfigIO(File f) {
		configFile = f;
		try {
			configFile.createNewFile();
			BufferedReader read;
			read = new BufferedReader(new FileReader(f));
			lastFile = read.readLine();
			defaultTheme = read.readLine();
			read.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Bad file passed to ConfigIO.",
					e);
		}
	}
	
	/**
	 * Creates a new ConfigIO from the default location,
	 * <code>~/.caveman.haox</code>.
	 * 
	 * @return The ConfigIO
	 */
	public static ConfigIO getDefaultConfigIO() {
		if (defaultCIO == null) {
			defaultCIO = new ConfigIO(new File(System.getProperty("user.home")
					+ "/.caveman.haox"));
		}
		return defaultCIO;
	}
	
	/**
	 * Get the path of the last file opened in the map editor.
	 * 
	 * @return the path, or empty String to indicate a non-existant value or
	 *         error.
	 */
	public String getLastFile() {
		if (lastFile == null) { //Still null == error in first block
			return "";
		}
		return lastFile;
	}
	
	/**
	 * Set the value of the last file opened in the map editor.
	 * 
	 * @param lf the path to the last opened file.
	 */
	public void setLastFile(String lf) {
		lastFile = lf;
	}
	
	/**
	 * Get the user's preferred default theme.
	 * 
	 * @return the name of the default theme, or an empty string to indicate a
	 *         missing value or error.
	 */
	public String getDefaultTheme() {
		if (defaultTheme == null) { //Still null == error in first block
			return "";
		}
		return defaultTheme;
	}
	
	/**
	 * Set the value of the user's default theme.
	 * 
	 * @param lf the name of the default theme
	 */
	public void setDefaultTheme(String lf) {
		defaultTheme = lf;
	}
	
	/**
	 * Write out the new config file.
	 */
	public void write() throws IOException {
		configFile.delete();
		configFile.createNewFile();
		Writer fw = new FileWriter(configFile);
		fw.write(lastFile + "\n" + defaultTheme + "\n");
		fw.close();
	}
}
