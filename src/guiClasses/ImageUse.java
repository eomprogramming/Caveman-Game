package guiClasses;

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
}