package guiClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JCheckBox;

import controlClasses.Music;
import controlClasses.RunCavemanGame;
=======
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controlClasses.Music;
import controlClasses.RunCavemanGame;
import controlClasses.ConfigIO;
>>>>>>> origin/master

@SuppressWarnings("serial")
public class OptionsFrame extends JFrame implements ActionListener
{
	private JButton backButton;
	private JCheckBox music;
	private JCheckBox gameSounds;
<<<<<<< HEAD
	
	/**
	 * constructor
=======
	private JComboBox themeSelect;
	
	/**
	 * Constructs a new Options menu with the user's current settings set
>>>>>>> origin/master
	 */
	public OptionsFrame()
	{
		super("Options");
		setLayout(null);
		Theme.applyFrameTheme(this);
		setSize(300,300);
		
<<<<<<< HEAD
		music = new JCheckBox("play music");
=======
		JLabel themeLabel = new JLabel("Theme:");
		themeLabel.setBounds(50, 50, 100, 20);
		add(themeLabel);
		
		themeSelect = new JComboBox(ImageTheme.getThemeNames());
		themeSelect.setBounds(50, 70, 150, 20);
		for(int i = 0; i < themeSelect.getItemCount(); i++) {
			if(ConfigIO.getDefaultConfigIO().getDefaultTheme()
				.equalsIgnoreCase((String)themeSelect.getItemAt(i))) {
				themeSelect.setSelectedIndex(i);
				break;
			}
			if(((String)(themeSelect.getItemAt(i))).equalsIgnoreCase("original")) {
				themeSelect.setSelectedIndex(i);
			}
		}
		add(themeSelect);
		
		music = new JCheckBox("Play music");
>>>>>>> origin/master
		music.setBounds(50, 100, 150, 20);
		music.addActionListener(this);
		add(music);
		
		if(Music.playMusic)
			music.setSelected(true);
		
<<<<<<< HEAD
		gameSounds = new JCheckBox("play game sounds");
=======
		gameSounds = new JCheckBox("Play game sounds");
>>>>>>> origin/master
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
<<<<<<< HEAD
	@SuppressWarnings("deprecation")
=======
>>>>>>> origin/master
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		if(o == backButton) {
<<<<<<< HEAD
=======
			try {
				ConfigIO.getDefaultConfigIO().setDefaultTheme((String)themeSelect.getSelectedItem());
				ConfigIO.getDefaultConfigIO().write();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this,
						"An error occured writing stuff.", "Bad news",
						JOptionPane.ERROR_MESSAGE);
			}
>>>>>>> origin/master
			new HomePage();
			dispose();
		}
		else if(o == music)
		{
			if(music.isSelected())
			{
<<<<<<< HEAD
				RunCavemanGame.mainSong = new Music(RunCavemanGame.songName);
				RunCavemanGame.mainSong.start();
=======
				RunCavemanGame.mainSong.startMusic();
>>>>>>> origin/master
				Music.playMusic = true;
			}
			else
			{
				Music.playMusic = false;
<<<<<<< HEAD
				RunCavemanGame.mainSong.stop();
=======
				RunCavemanGame.mainSong.stopMusic();
>>>>>>> origin/master
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
<<<<<<< HEAD
=======
		
>>>>>>> origin/master
	}
}
