package controlClasses;

import java.io.File;
import java.io.IOException;

<<<<<<< HEAD
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music extends Thread 
{
	public static boolean playMusic;
	public static boolean playGameSounds;
    private String filename;
    private Position curPosition;
    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb
 
    enum Position {
        LEFT, RIGHT, NORMAL
    };
 
    public Music(String wavfile) {
        filename = wavfile;
        curPosition = Position.NORMAL;
    }
 
    @SuppressWarnings("deprecation")
	public void run() {
 
    	do
    	{
    		File soundFile = new File(filename);
            if (!soundFile.exists()) {
                System.err.println("Wave file not found: " + filename);
                return;
            }
     
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }
     
            AudioFormat format = audioInputStream.getFormat();
            SourceDataLine auline = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
     
            try {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
     
            if (auline.isControlSupported(FloatControl.Type.PAN)) {
                FloatControl pan = (FloatControl) auline
                        .getControl(FloatControl.Type.PAN);
                if (curPosition == Position.RIGHT)
                    pan.setValue(1.0f);
                else if (curPosition == Position.LEFT)
                    pan.setValue(-1.0f);
            } 
     
            auline.start();
            int nBytesRead = 0;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
            try {
                while (nBytesRead != -1) {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0)
                        auline.write(abData, 0, nBytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } finally {
                auline.drain();
                auline.close();
            }
    	}while(filename.equals(RunCavemanGame.songName));
        if(filename.equals(RunCavemanGame.victorySongFilename) && playMusic)
        {
        	RunCavemanGame.mainSong = new Music(RunCavemanGame.songName);
        	RunCavemanGame.mainSong.start();
        }
        this.stop();
    }
=======
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

>>>>>>> origin/master
}
