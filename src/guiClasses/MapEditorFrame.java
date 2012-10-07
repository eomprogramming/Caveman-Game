package guiClasses;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlClasses.*;

@SuppressWarnings("serial")
public class MapEditorFrame extends JFrame implements ActionListener
{

	private EditBoard editBoard;	
	private JButton quitButton, saveButton, saveAsButton, openFileButton, newMapButton;
	private JButton map[][];
	private JRadioButton options[];
	private MapIO m;
	private int shiftX = 120, lastChosen = 3;
	private boolean saved;
	
	/*
	 * obj0 = rock(boulder)
	 * obj1 = tree(wall)
	 * obj2 = caveman
	 * obj3 = grass(empty)
	 * obj4 = exit
	 * obj5 = hole
	 */
	
	/**
	 * constructor
	 * @param editBoard
	 */
	public MapEditorFrame(EditBoard board, MapIO io)
	{
		super("Caveman Game");
		editBoard = board;
		setSize(680,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		int yPos=40,xPos=0;
		Theme.applyFrameTheme(this);

		saved = true;
		m = io;
		
		//makes the board buttons
		map = new JButton[13][13];
		for(int i=0; i<13;i++){
			
			for(int j=0;j<13;j++)
			{
				map[i][j] = new JButton();
				map[i][j].setSize(30,30);
				map[i][j].setLocation(xPos+=30,yPos);
				map[i][j].setBackground(Color.WHITE);
				map[i][j].setActionCommand(i+","+j);
				map[i][j].addActionListener(this);
				map[i][j].setBorder(null);
				add(map[i][j] );
			}
			yPos+=30;
			xPos=0;			
		}
		
		//makes the options for which block to use
		options = new JRadioButton[6];
		for(int i=0; i<options.length;i++){			
			options[i] = new JRadioButton();
			options[i].setSize(170,30);
			options[i].setLocation(450,i*30+40);
			options[i].setActionCommand(""+i);
			options[i].addActionListener(this);
			options[i].setBackground(Color.BLACK);
			options[i].setIcon(new ImageIcon("pictures/obj"+i+".png"));
			options[i].setRolloverIcon(options[i].getIcon());
			options[i].setSelectedIcon(options[i].getIcon());
			add(options[i]);
		}
		options[0].setText("Rock");	
		options[1].setText("Tree");
		options[2].setText("Caveman");
		options[3].setText("Grass is selected!");
		options[3].setOpaque(true);
		options[3].setForeground(getContentPane().getBackground());
		options[4].setText("Exit");
		options[5].setText("Hole");
		ButtonGroup groupOptions = new ButtonGroup();
		for(int i=0;i<options.length;i++)
			groupOptions.add(options[i]);
		options[3].setSelected(true);

		quitButton = new JButton();
		quitButton.setBounds(420 + shiftX, 380, 80, 40);
		quitButton.setText("QUIT");
		quitButton.addActionListener(this);
		add(quitButton);

		saveButton = new JButton();
		saveButton.setBounds(320 + shiftX, 230, 80, 40);
		saveButton.setText("SAVE");
		saveButton.addActionListener(this);
		add(saveButton);
		saveAsButton = new JButton();
		saveAsButton.setBounds(410 + shiftX, 230, 100, 40);
		saveAsButton.setText("SAVE AS");
		saveAsButton.addActionListener(this);
		add(saveAsButton);
		openFileButton = new JButton();
		openFileButton.setBounds(410 + shiftX, 280, 100, 40);
		openFileButton.setText("OPEN FILE");
		openFileButton.addActionListener(this);
		add(openFileButton);
		newMapButton = new JButton();
		newMapButton.setBounds(320 + shiftX, 280, 80, 40);
		newMapButton.setText("RESET");
		newMapButton.addActionListener(this);
		add(newMapButton);
		
		update();
		repaint();
	}
	
	/**
	 * determines what happens when buttons are pressed
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().contains(",")){
			saved = false;
			int row = Integer.parseInt(e.getActionCommand().substring(0, e.getActionCommand().indexOf(",")));
			int col = Integer.parseInt(e.getActionCommand().substring(e.getActionCommand().indexOf(",")+1,e.getActionCommand().length()));
			Loc loc = new Loc(row,col);
			if(options[0].isSelected())
				editBoard.place(Boardx.BOULDER, loc);
			else if(options[1].isSelected())
				editBoard.place(Boardx.WALL, loc);
			else if(options[2].isSelected())
				editBoard.setCavemanLoc(loc);
			else if(options[3].isSelected())
				editBoard.place(Boardx.EMPTY, loc);
			else if(options[4].isSelected())
				editBoard.place(Boardx.EXIT, loc);
			else if(options[5].isSelected())
				editBoard.place(Boardx.HOLE, loc);
				
			update();
		}else if(e.getSource()==quitButton)
		{
			if(!saved)
				if(JOptionPane.showOptionDialog(this, "do you want to save?", "do you want to save?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION)
					save();
			new HomePage();
			dispose();
		}
		else if(e.getSource()==newMapButton)
		{
			if(JOptionPane.showOptionDialog(this, "are you sure you want to reset?", "are you sure you want to reset?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.NO_OPTION) == JOptionPane.NO_OPTION)
				return;
			editBoard = new EditBoard();
			update();
		}
		else if(e.getSource()==saveButton && editBoard.isValidBoard())
		{
			save();
		}
		else if(e.getSource()==saveAsButton && editBoard.isValidBoard())
		{
			saveAs();
		}
		else if(e.getSource()==openFileButton)
		{
			if(!saved)
				if(JOptionPane.showOptionDialog(this, "do you want to save?", "do you want to save?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION)
					save();
			open();
		}else{
			options[lastChosen].setOpaque(false);
			options[lastChosen].setForeground(Color.BLACK);
			options[lastChosen].setText(options[lastChosen].getText().substring(0,options[lastChosen].getText().indexOf(" ")));
			int n = Integer.parseInt(e.getActionCommand());
			options[n].setText(options[n].getText()+" is selected!");
			options[n].setOpaque(true);
			options[n].setForeground(getContentPane().getBackground());
			lastChosen = n;
		}
			
		
	}

	/**
	 * opens a file
	 */
	public void open()
	{
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
			if(choose.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				String s = choose.getSelectedFile().getPath();
				if(!s.equals(""))
				{
					IO.createOutputFile("options.hao");
					IO.print(s);
					IO.closeOutputFile();
					m = new MapIO(s);
					editBoard = new EditBoard (m.read().getBoard());
					update();
					saved = true;
				}
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * saves file
	 */
	public void save()
	{
		if(m == null)
			saveAs();
		else
		{
			try {
				m.write(new Boardx(editBoard.getBoard()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			saved = true;
		}
	}
	
	/**
	 * saves file with file chooser
	 */
	public void saveAs()
	{
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
			if(choose.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				String s = choose.getSelectedFile().getPath();
				IO.createOutputFile("options.hao");
				IO.print(s);
				IO.closeOutputFile();
				if(!s.endsWith(".map"))
					s += ".map";
				m = new MapIO(s);
				m.write(new Boardx(editBoard.getBoard()));
				saved = true;
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void update() {
		for(int i=0;i<13;i++)
			for(int j=0;j<13;j++)
			{
				if(editBoard.get(i, j) == Boardx.EMPTY)
					map[i][j].setIcon(new ImageIcon("pictures/obj3.png"));
				else if(editBoard.get(i, j) == Boardx.WALL)
					map[i][j].setIcon(new ImageIcon("pictures/obj1.png"));
				else if(editBoard.get(i, j) == Boardx.BOULDER)
					map[i][j].setIcon(new ImageIcon("pictures/obj0.png"));
				else if(editBoard.get(i, j) == Boardx.CAVEMAN)
					map[i][j].setIcon(new ImageIcon("pictures/obj2.png"));
				else if(editBoard.get(i, j) == Boardx.EXIT)
					map[i][j].setIcon(new ImageIcon("pictures/obj4.png"));
				else if(editBoard.get(i, j) == Boardx.HOLE)
					map[i][j].setIcon(new ImageIcon("pictures/obj5.png"));
			}
	}

	@SuppressWarnings("unused")
	private int getChoice() {
		if(options[0].isSelected())
			return 0;
		if(options[1].isSelected())
			return 1;
		if(options[2].isSelected())
			return 2;
		if(options[3].isSelected())
			return 3;
		if(options[4].isSelected())
			return 4;
		return 5;
	}
}
