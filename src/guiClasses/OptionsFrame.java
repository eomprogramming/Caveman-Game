package guiClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

@SuppressWarnings("serial")
public class OptionsFrame extends JFrame implements ActionListener
{
	private JButton backButton;
	private JCheckBox music;
	private JCheckBox gameSounds;
	private JComboBox themeSelect;
	
	/**
	 * Constructs a new Options menu with the user's current settings set
	 */
	public OptionsFrame()
	{
		super("Options");
		setLayout(null);
		Theme.applyFrameTheme(this);
		setSize(300,300);
		
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
		music.setBounds(50, 100, 150, 20);
		music.addActionListener(this);
		add(music);
		
		if(Music.playMusic)
			music.setSelected(true);
		
		gameSounds = new JCheckBox("Play game sounds");
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
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		if(o == backButton) {
			try {
				ConfigIO.getDefaultConfigIO().setDefaultTheme((String)themeSelect.getSelectedItem());
				ConfigIO.getDefaultConfigIO().write();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this,
						"An error occured writing stuff.", "Bad news",
						JOptionPane.ERROR_MESSAGE);
			}
			new HomePage();
			dispose();
		}
		else if(o == music)
		{
			if(music.isSelected())
			{
				RunCavemanGame.mainSong.startMusic();
				Music.playMusic = true;
			}
			else
			{
				Music.playMusic = false;
				RunCavemanGame.mainSong.stopMusic();
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
