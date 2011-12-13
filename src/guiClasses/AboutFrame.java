package guiClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AboutFrame extends JFrame implements ActionListener
{
	private JButton backButton;
	
	/**
	 * constructor
	 */
	public AboutFrame()
	{
		super("About");
		setLayout(null);
		setSize(300,450);
		Theme.applyFrameTheme(this);
		
		JTextArea text = new JTextArea("The Caveman Game\n\n" +
				"Hao - Creator, Developer, Designer\n" +
				"David H. - VP and Instructor\n" +
				"Aly - GUI Designer (and developer)\n" +
				"Andrew - Developer, Graphics\n" +
				"Chris - Head of Marketing\n" + 
				"Eric - Developer\n" +
				"Ian - Developer\n" +  
				"Jason - Music\n" +
				"Kenneth - Developer\n" + 
				"Sunghyun - Graphics\n" + 
				"Sammnet - Developer\n" +
				"Gordon - Developer\n" +
				"Peter - Map Designer\n" +
				"Christopher - Game Tester\n\n" +
				"Special thanks to\n" +
				"Ms. Clark\n" +
				"Mr. Talbot");
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(false);
		text.setOpaque(false);
		text.setBounds(40, 20, 220, 350);
		add(text);
		
		backButton = new JButton("Back");
		backButton.setBounds(40, 370, 100, 30);
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
