package guiClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JCheckBox;

import controlClasses.Music;
import controlClasses.RunCavemanGame;

@SuppressWarnings("serial")
public class OptionsFrame extends JFrame implements ActionListener
{
	private JButton backButton;
	private JCheckBox music;
	private JCheckBox gameSounds;
	
	/**
	 * constructor
	 */
	public OptionsFrame()
	{
		super("Options");
		setLayout(null);
		Theme.applyFrameTheme(this);
		setSize(300,300);
		
		music = new JCheckBox("play music");
		music.setBounds(50, 100, 150, 20);
		music.addActionListener(this);
		add(music);
		
		if(Music.playMusic)
			music.setSelected(true);
		
		gameSounds = new JCheckBox("play game sounds");
		gameSounds.setBounds(50, 130, 150, 20);
		gameSounds.addActionListener(this);
		add(gameSounds);
		
		if(Music.playGameSounds)
			gameSounds.setSelected(true);
		
		backButton = new JButton("Back");
		backButton.setBounds(40, 200, 100, 30);
		backButton.addActionListener(this);
		add(backButton);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * determines what happens when buttons are pressed
	 */
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		if(o == backButton) {
			new HomePage();
			dispose();
		}
		else if(o == music)
		{
			if(music.isSelected())
			{
				RunCavemanGame.mainSong = new Music(RunCavemanGame.songName);
				RunCavemanGame.mainSong.start();
				Music.playMusic = true;
			}
			else
			{
				Music.playMusic = false;
				RunCavemanGame.mainSong.stop();
			}
		}
		else if(o == gameSounds)
		{
			if(gameSounds.isSelected())
			{
				Music.playGameSounds = true;
			}
			else
			{
				Music.playGameSounds = false;
			}
		}
	}
}
