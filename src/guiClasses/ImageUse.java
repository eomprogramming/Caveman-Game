package guiClasses;

import controlClasses.Boardx;

/**
 * This enumerated type represents an individual image that appears in a theme.
 * @author Ian Dewan
 *
 */
public enum ImageUse {
	/** A pushable thing. */
	BOULDER ("obj1"),
	/** An un-pushable thing*/
	WALL ("obj2"),
	/** The player */
	PLAYER ("obj3"),
	/** An area that can be walked over */
	EMPTY ("obj4"),
	/** A square you must step on to win */
	EXIT ("obj5"),
	/** Swallows up boulders (kills player?) */
	HOLE ("obj6");
	/*
	 * Add new images like this:
	 * INTERNAL_NAME ("image_file_name")
	 */
	
	private final String file;
	
	ImageUse (String file) {
		this.file = file;
	}
	
	/**
	 * Return the filename associated with the image type.
	 * @return The filename, minus extension
	 */
	String getFile() {
		return file;
	}
	
	/**
	 * Get the ImageUse for the given constant from Boardx.
	 * @param i the constant for the desired use
	 * @return the corresponding ImageUse
	 * @throws IllegalArgumentException if the int is not a constant from Boardx
	 */
	public static ImageUse getByNum(int i) {
		switch (i) {
			case Boardx.BOULDER:
				return BOULDER;
			case Boardx.CAVEMAN:
				return PLAYER;
			case Boardx.EMPTY:
				return EMPTY;
			case Boardx.EXIT:
				return EXIT;
			case Boardx.HOLE:
				return HOLE;
			case Boardx.WALL:
				return WALL;
			default:
				throw new IllegalArgumentException();
		}
	}
}