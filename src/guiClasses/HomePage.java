package guiClasses;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
<<<<<<< HEAD
import java.io.FileNotFoundException;
=======
>>>>>>> origin/master
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
<<<<<<< HEAD
import javax.swing.JOptionPane;
=======
>>>>>>> origin/master
import javax.swing.filechooser.FileNameExtensionFilter;

import controlClasses.*;

@SuppressWarnings("serial")
public class HomePage extends JFrame implements ActionListener
{
	private JButton playGame;
	private JButton editMap;
	private JButton mapEditor;
	private JButton options;
	private JButton instructions;
	private JButton about;
	private JButton quit;
	
	/**
	 * constructor
	 */
	public HomePage()
	{
		super("Caveman Game - Main Menu");
		setLayout(null);
		setSize(320,380);
		Theme.applyFrameTheme(this);
		
		JLabel title = new JLabel("THE CAVEMAN GAME");
		title.setFont(new Font("comic sans ms",Font.BOLD,22));
		title.setBounds(30, 20, getWidth()-30, 40);
		add(title);
		
		playGame = new JButton("Play Game");
		playGame.setBounds(75, 90, 150, 30);
		playGame.addActionListener(this);
		add(playGame);
		
		instructions = new JButton("Instructions");
		instructions.setBounds(75, 120, 150, 30);
		instructions.addActionListener(this);
		add(instructions);
		
		mapEditor = new JButton("Create New Map");
		mapEditor.setBounds(75, 150, 150, 30);
		mapEditor.addActionListener(this);
		add(mapEditor);
		
		editMap = new JButton("Edit Map");
		editMap.setBounds(75, 180, 150, 30);
		editMap.addActionListener(this);
		add(editMap);
		
		options = new JButton("Options");
		options.setBounds(75, 210, 150, 30);
		options.addActionListener(this);
		add(options);
		
		about = new JButton("About");
		about.setBounds(75, 240, 150, 30);
		about.addActionListener(this);
		add(about);
		
		quit = new JButton("Quit");
		quit.setBounds(75, 270, 150, 30);
		quit.addActionListener(this);
		add(quit);
		
		JLabel version = new JLabel("V1.2");
		version.setFont(new Font("Times New Roman",Font.PLAIN,12));
		version.setBounds(20, 300, 150, 20);
		add(version);
		
<<<<<<< HEAD
		if(IO.readObject("tournament.hao") == null)
		{
			String name = JOptionPane.showInputDialog("what is your name?");
			Tournament t = new Tournament(name);
			try {
				IO.writeObject(t, "tournament.hao");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
=======
>>>>>>> origin/master
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * determines what happens when buttons are pressed
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		if(o == playGame) {
			try {
				JFileChooser choose = new JFileChooser();
<<<<<<< HEAD
				if(IO.openInputFile("options.hao"))
				{
					try {
						choose.setSelectedFile(new File(IO.readLine()));
						IO.closeInputFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
=======
				choose.setSelectedFile(new
						File(ConfigIO.getDefaultConfigIO().getLastFile()));
>>>>>>> origin/master
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Map File (*.map)", "map");
				choose.addChoosableFileFilter(filter);
				choose.setAcceptAllFileFilterUsed(false);
				if (choose.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
					String s = choose.getSelectedFile().getPath();
<<<<<<< HEAD
					IO.createOutputFile("options.hao");
					IO.print(s);
					IO.closeOutputFile();
					try{
						new CavemanGameFrame(new PlayBoard (new MapIO(s).read().getBoard()), Integer.parseInt(choose.getSelectedFile().getName().substring(0, 1)));
						dispose();
					}catch(NullPointerException exp){}
=======
					ConfigIO.getDefaultConfigIO().setLastFile(s);
						new CavemanGameFrame(new PlayBoard (new MapIO(s).read()));
						dispose();
>>>>>>> origin/master
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(o == instructions) {
			new InstructionsFrame();
			dispose();
		}
		else if(o == mapEditor) {
			new MapEditorFrame(new EditBoard(), null);
			dispose();
		}
		else if(o == editMap)
		{
			JFileChooser choose = new JFileChooser();
<<<<<<< HEAD
			if(IO.openInputFile("options.hao"))
			{
				try {
					choose.setSelectedFile(new File(IO.readLine()));
					IO.closeInputFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
=======
			choose.setSelectedFile(new
					File(ConfigIO.getDefaultConfigIO().getLastFile()));
>>>>>>> origin/master
			//choose.setSelectedFile(file);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Map File (*.map)", "map");
			choose.addChoosableFileFilter(filter);
			choose.setAcceptAllFileFilterUsed(false);
			if (choose.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				String s = choose.getSelectedFile().getPath();
<<<<<<< HEAD
				IO.createOutputFile("options.hao");
				IO.print(s);
				IO.closeOutputFile();
				System.out.println(s);
				try {
					if(s.equals(""))
=======
				ConfigIO.getDefaultConfigIO().setLastFile(s);
				System.out.println(s);
				try {
					if(s.equals("")||s.lastIndexOf(".")<0
							||!s.substring(s.lastIndexOf("."),s.length()).equals(".map"))
>>>>>>> origin/master
						new MapEditorFrame(new EditBoard(), null);
					else
					{
						MapIO io = new MapIO(s);
						new MapEditorFrame(new EditBoard (io.read().getBoard()), io);
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		}
		else if(o == options) {
			new OptionsFrame();
			dispose();
		}
		else if(o == about) {
			new AboutFrame();
			dispose();
		}
		else if(o == quit) {
<<<<<<< HEAD
=======
			System.out.println(System.getProperty("user.home"));
			try {
				ConfigIO.getDefaultConfigIO().write();
			} catch (IOException e1) {
				// Oh well.
				System.exit(1);
				System.out.println("Die");
			}
>>>>>>> origin/master
			System.exit(0);
		}
	}
}
