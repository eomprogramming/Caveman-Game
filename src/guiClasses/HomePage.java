package guiClasses;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
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
		
//		JLabel version = new JLabel("SVN Revision 47");
//		version.setFont(new Font("Times New Roman",Font.PLAIN,12));
//		version.setBounds(0, 300, 150, 20);
//		add(version);
		
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
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Map File (*.map)", "map");
				choose.addChoosableFileFilter(filter);
				choose.setAcceptAllFileFilterUsed(false);
				if (choose.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
					String s = choose.getSelectedFile().getPath();
					IO.createOutputFile("options.hao");
					IO.print(s);
					IO.closeOutputFile();
					try{
						new CavemanGameFrame(new PlayBoard (new MapIO(s).read().getBoard()));
						dispose();
					}catch(NullPointerException exp){}
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
			//choose.setSelectedFile(file);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Map File (*.map)", "map");
			choose.addChoosableFileFilter(filter);
			choose.setAcceptAllFileFilterUsed(false);
			if (choose.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				String s = choose.getSelectedFile().getPath();
				IO.createOutputFile("options.hao");
				IO.print(s);
				IO.closeOutputFile();
				System.out.println(s);
				try {
					if(s.equals(""))
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
			System.exit(0);
		}
	}
}
