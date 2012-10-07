package controlClasses;

import guiClasses.*;

public class RunCavemanGame 
{
	public static Music mainSong;
	public static Music boulderPush;
	public static Music victorySong;
	public static String songName = "music/ggggg.wav";
	public static String pushingSound = "music/boulder_push.wav";
	public static String victorySongFilename = "music/victory_sound.wav";
	
	/**
	 * the first method that runs
	 * you can use this method to test your code
	 * @param args
	 */
	public static void main(String args[])
	{
		new HomePage();
		mainSong = new Music(songName);
		boulderPush = new Music(pushingSound);
		victorySong = new Music(victorySongFilename);
	}
}
