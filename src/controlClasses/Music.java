package controlClasses;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The <code>Music</code> class is an easy-to-use implementation of the Java Sound API's
 * <code>{@link Clip}</code> to play audio.
 * @author Kenneth
 *
 */
public class Music {
	public static boolean playMusic;
	public static boolean playGameSounds;
	private String musicFile;
	private Clip musicClip;
	
	/**
	 * Creates a new audio Clip with the specified audio file
	 * @param fileName The name of the audio file
	 */
	public Music(String fileName) {
		musicFile = fileName;
		try {
			musicClip = AudioSystem.getClip();
			AudioInputStream aus = AudioSystem.getAudioInputStream(new File(musicFile));
			musicClip.open(aus);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Starts the audio playback at the beginning of the file
	 */
	public void startMusic() {
		musicClip.setFramePosition(0);
		if(musicFile.equalsIgnoreCase(RunCavemanGame.songName))
			musicClip.loop(Clip.LOOP_CONTINUOUSLY);
		else
			musicClip.start();
	}
	
	/**
	 * Stops the audio playback and discards any data left in the audio buffer
	 */
	public void stopMusic() {
		musicClip.stop();
		musicClip.flush();
	}

}
