package guiClasses;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlClasses.*;

@SuppressWarnings("serial")
public class CavemanGameFrame extends JFrame implements KeyListener, ActionListener
{
	private PlayBoard board;
	private JLabel[][] map;
	private int[][] originalMap; //stores the originalMap
	private JButton quitButton;
	private JMenuItem restart;
	private JMenuItem newMap;
	
	private static final int UP_ARROW = 38;
	private static final int DOWN_ARROW = 40;
	private static final int LEFT_ARROW = 37;
	private static final int RIGHT_ARROW = 39;
	public static final int BLOCK_SIZE = 30;
	
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
	 * @param gameBoard
	 */
	public CavemanGameFrame(PlayBoard playBoard)
	{
		super("Caveman Game");
		setLayout(null);

		Theme.applyFrameTheme(this);
		
		if(playBoard.isValidBoard())
			board = playBoard;
		else
			board = new PlayBoard();
		
		originalMap = new int[board.getSize()][board.getSize()];
		for(int i = 0; i < 13; i++)
		{
			for(int j = 0; j < 13; j++)
			{
				originalMap[i][j] = board.get(i, j);
			}
		}
		
		map = new JLabel[board.getSize()][board.getSize()];
		
		System.out.println(map.length);
		System.out.println(map[0].length);
		
		createLabels();
		createButtons();
		createMenuBar();
		update();

		addKeyListener(this);
		setSize(BLOCK_SIZE * board.getSize() + 112, BLOCK_SIZE * board.getSize() + 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu gameControls = new JMenu("Game");
		
		restart = new JMenuItem("Restart");
		restart.addActionListener(this);
		gameControls.add(restart);
		
		newMap = new JMenuItem("New Map");
		newMap.addActionListener(this);
		gameControls.add(newMap);
		
		menuBar.add(gameControls);
		setJMenuBar(menuBar);
	}
	
	public void createButtons()
	{
		quitButton = new JButton("Quit");
		quitButton.addActionListener(this);
		quitButton.setBounds(BLOCK_SIZE * board.getSize()/2, BLOCK_SIZE * board.getSize()-10 + 60, 100, 30);
	//	System.out.println(quitButton.getBounds().toString());
		quitButton.setFocusable(false);
		add(quitButton);
	}
	
	public void createLabels()
	{
		for(int i = 0; i < board.getSize(); i++)
		{
			for(int j = 0; j < board.getSize(); j++)
			{
				map[i][j] = new JLabel();
				map[i][j].setBounds(j*BLOCK_SIZE + 50, i*BLOCK_SIZE + 30, BLOCK_SIZE, BLOCK_SIZE);
				map[i][j].setOpaque(true);
				map[i][j].setIcon(new ImageIcon("pictures/obj3.png"));
				this.add(map[i][j]);
			}
		}
	}
	
	public void update()
	{
		for(int i = 0; i < board.getSize(); i++)
		{
			for(int j = 0; j < board.getSize(); j++)
			{
				if(board.get(i, j) == Board.EMPTY)
				{
					map[i][j].setIcon(new ImageIcon("pictures/obj3.png"));
				}
				else if(board.get(i, j) == Board.CAVEMAN)
				{
					map[i][j].setIcon(new ImageIcon("pictures/obj2.png"));
				}
				else if(board.get(i, j) == Board.BOULDER)
				{
					map[i][j].setIcon(new ImageIcon("pictures/obj0.png"));
				}
				else if(board.get(i, j) == Board.WALL)
				{
					map[i][j].setIcon(new ImageIcon("pictures/obj1.png"));
				}
				else if(board.get(i, j) == Board.EXIT)
				{
					map[i][j].setIcon(new ImageIcon("pictures/obj4.png"));
				}
				else if(board.get(i, j) == Board.HOLE)
				{
					map[i][j].setIcon(new ImageIcon("pictures/obj5.png"));
				}
			}
		}
		checkWin();
	}
	
	public void checkWin()
	{
		if(originalMap[board.getCavemanLoc().getRow()][board.getCavemanLoc().getCol()] == Board.EXIT)
		{
			if(Music.playGameSounds)
			{
				if(Music.playMusic)
					RunCavemanGame.mainSong.stop();
				RunCavemanGame.victorySong = new Music(RunCavemanGame.victorySongFilename);
				RunCavemanGame.victorySong.start();
			}
			JOptionPane.showMessageDialog(this, "You win!!!");
			new HomePage();
			dispose();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		
	}

	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode() == UP_ARROW)
		{
			if(board.moveCaveman(Board.UP))
				update();
		}
		else if(e.getKeyCode() == DOWN_ARROW)
		{
			if(board.moveCaveman(Board.DOWN))
				update();
		}
		else if(e.getKeyCode() == LEFT_ARROW)
		{
			if(board.moveCaveman(Board.LEFT))
				update();
		}
		else if(e.getKeyCode() == RIGHT_ARROW)
		{
			if(board.moveCaveman(Board.RIGHT))
				update();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == quitButton)
		{
			new HomePage();
			dispose();
		}
		else if(e.getSource() == restart) {
			try {
				IO.openInputFile("options.hao");
				String s = IO.readLine();
				IO.closeInputFile();
				new CavemanGameFrame(new PlayBoard(new MapIO(s).read().getBoard()));
				dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == newMap) {
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
					new CavemanGameFrame(new PlayBoard (new MapIO(s).read().getBoard()));
					dispose();
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
