package guiClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class InstructionsFrame extends JFrame implements ActionListener
{
	private JButton backButton;
	
	/**
	 * constructor
	 */
	public InstructionsFrame()
	{
		super("Instructions");
		setLayout(null);
		setSize(300,400);
		Theme.applyFrameTheme(this);
		
		JTextArea text = new JTextArea("How to play the Caveman Game!\n\n" +
				"--Creating--\n\n" +
				"First, select the item you want to place on your map. Then keep selecting " +
				"where you want it to be placed on the board. Once your done, save it wherever " +
				"you'd like, and your map is ready to be played!\n\n" +
				"-- Playing--\n\n" +
				"The goal of the game is to move the caveman " +
				"into the exit.  You can push boulders to " +
				"move them out of the way, but not trees, which act " +
				"like walls and cannot be moved.\n\n" +
				"Good luck!");
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(false);
		text.setOpaque(false);
		text.setBounds(40, 20, 220, 250);
		add(text);
		
		JScrollPane pane = new JScrollPane(text);
		pane.setBounds(text.getBounds());
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(pane);
		
		backButton = new JButton("Back");
		backButton.setBounds(40, 290, 100, 30);
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
		new HomePage();
		dispose();
	}
}
