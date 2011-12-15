package guiClasses;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a theme for the images in the game.
 * @author Ian Dewan
 *
 */
public class ImageTheme {
	private static Map<String, ImageTheme> themes =
		new HashMap<String, ImageTheme>();
	
	/**
	 * Creates a new theme. Did I really have to explain that?
	 * @param src The directory to get the images from.
	 */
	protected ImageTheme(File src) {
		if (!src.isDirectory())
			throw new IllegalArgumentException(
					"Only directories can be passed to ImageTheme()");
		
	}
	
	public static ImageTheme getThemeByName(String name) {
		if (themes.containsKey(name)) {
			return themes.get(name);
		} else {
			ImageTheme tmp = new ImageTheme(new File("pictures/" + name));
			themes.put(name, tmp);
			return tmp;
		}
	}
	
	public static ImageTheme getDefaultTheme() {
		return getThemeByName("default");
	}

}
