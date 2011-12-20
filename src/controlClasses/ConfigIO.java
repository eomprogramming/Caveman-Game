package controlClasses;

import java.io.*;

/**
 * Contains methods for writing configuration data to and reading from  a
 * <code>.haox</code> file.
 * The only way to get a ConfigIO is to call the static getDefaultConfigIO() method
 * to get one bound to the default file (~/.haox).
 * @author Ian Dewan
 *
 */
public class ConfigIO {
	private Writer write;
	private Reader read;
	
	/**
	 * Creates a new ConfigIO that reads data from the file f.
	 * @param f the file to read configuration data from
	 */
	protected ConfigIO(File f) {
		try {
			f.createNewFile();
			write = new BufferedWriter(new FileWriter(f));
			read = new BufferedReader(new FileReader(f));
		} catch (IOException e) {
			throw new IllegalArgumentException("Bad file passed to ConfigIO.", e);
		}
	}
	
	
	/**
	 * Creates a new ConfigIO from the default location, <code>~/.haox</code>.
	 * @return The ConfigIO
	 */
	public static ConfigIO getDefaultConfigIO() {
		return new ConfigIO(new File(System.getProperty("user.home") + "/.haox"));
	}
}
