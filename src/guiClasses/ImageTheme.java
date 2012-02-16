package guiClasses;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.ImageIcon;

import controlClasses.*;

/**
 * Represents a theme for the images in the game.
 * @author Ian Dewan
 *
 */
public class ImageTheme {
	private static Map<String, ImageTheme> themes =
		new HashMap<String, ImageTheme>();
	private Map<ImageUse, ImageIcon> icons = new HashMap<ImageUse, ImageIcon>();
	
	/**
	 * Creates a new theme. Did I really have to explain that?
	 * @param src The directory to get the images from.
	 */
	protected ImageTheme(String src) {
		for (ImageUse i: ImageUse.values()) {
			icons.put(i, new ImageIcon(src + "/" + i.getFile() + ".png"));
		}
	}
	
	/**
	 * Get the ImageIcon for the image associated with the given ImageUse
	 * @param use the ImageUse for which to return the icon
	 * @return The ImageIcon for the given use.
	 */
	public ImageIcon getImage(ImageUse use) {
		return icons.get(use);
	}
	
	/**
	 * Gets the ImageTheme with the passed name.
	 * @param name the name of the theme to return
	 * @return The theme with that name.
	 */
	public static ImageTheme getThemeByName(String name) {
		if (themes.containsKey(name)) {
			return themes.get(name);
		} else {
			if(new File("pictures/"+name).isDirectory()) {
				ImageTheme tmp = new ImageTheme("pictures/" + name);
				themes.put(name, tmp);
				return tmp;
			}
			return new ImageTheme("pictures/original");
		}
	}
	
	/**
	 * Get the user's default theme.
	 * @return The ImageTheme corresponding to the default theme.`
	 */
	public static ImageTheme getDefaultTheme() {
		String s = ConfigIO.getDefaultConfigIO().getDefaultTheme();
		return getThemeByName(s.equals("") ? "original" : s);
	}
	
	/**
	 * Return all the valid theme names currently available.
	 * @return An array of Strings containing the names of all available themes.
	 */
	public static String[] getThemeNames() {
		LinkedList<String> ret = new LinkedList<String>();
		File[] imageDirContents = new File("pictures").listFiles();
		if (imageDirContents == null) {
			return new String[] {};
		}
		for (File f: imageDirContents) {
			if (f.isDirectory()) {
				ret.add(f.getName());
			}
		}
		String[] tmp = new String[ret.size()];
		ret.toArray(tmp);
		return tmp;
	}

}
