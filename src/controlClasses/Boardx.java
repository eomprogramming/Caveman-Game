package controlClasses;

import guiClasses.ImageTheme;

public class Boardx
{
	public int[][] board;
	protected String theme;
	
	public static final int EMPTY = 0;
	public static final int CAVEMAN = 1;
	public static final int BOULDER = 2;
	public static final int WALL = 3;
	public static final int EXIT = 4;
	public static final int HOLE = 5;
	
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	
	public int BOARD_HEIGHT;
	public int BOARD_WIDTH;

	/**
	 * constructs a Board object 
	 */
	public Boardx()
	{
		BOARD_HEIGHT = 15;
		BOARD_WIDTH = 10;
		board = new int[BOARD_HEIGHT][BOARD_WIDTH];
		for(int i = 0; i < BOARD_HEIGHT; i++)
			for(int j = 0; j < BOARD_WIDTH; j++)
				board[i][j] = EMPTY;
		board[0][0] = CAVEMAN;
		board[BOARD_HEIGHT-1][BOARD_WIDTH-1] = EXIT;
	}
	
	/**
	 * constructs a Board object 
	 * @param board
	 */
	public Boardx(int[][] board)
	{
		BOARD_HEIGHT = board.length;
		BOARD_WIDTH = board[0].length;
		this.board = board;
	}
	
	public Boardx(Boardx b) {
		this.BOARD_HEIGHT = b.BOARD_HEIGHT;
		this.BOARD_WIDTH = b.BOARD_WIDTH;
		this.board = b.board;
		this.theme = b.theme;
	}
	
	/**
	 * 
	 * @return location of caveman(use the constants in this class)
	 */
	public Loc getCavemanLoc()
	{
		int row=0, col=0;
		for(int i = 0;i<BOARD_HEIGHT;i++)
		{
			for(int j = 0;j<BOARD_WIDTH;j++)
			{
				if(this.get(i,j)==CAVEMAN)
				{
					row=i;
					col=j;
				}
			}
		}
		Loc caveman = new Loc(row,col);
		return caveman;
	}
	
	/**
	 * 
	 * @param location
	 * @return an integer indicating what is at that location on the board(use the constants in this class)
	 */
	public int get(Loc l)
	{
		return board[l.getRow()][l.getCol()];
	}
	
	/**
	 * sets the location l to the value a on the board
	 * @param l
	 * @param a
	 */
	public void set(Loc l, int a)
	{
		if(a >= EMPTY && a <= HOLE)
			board[l.getRow()][l.getCol()] = a;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return an integer indicating what is at that location on the board(use the constants in this class)
	 */
	public int get(int row, int col)
	{
		return board[row][col];
	}
	
	/**
	 * 
	 * @return the entire board
	 */
	public int[][] getBoard()
	{
		return board;
	}
	
	/**
	 * 
	 * @return number of rows on the board
	 */
	public int getNumRows()
	{
		return BOARD_HEIGHT;
	}
	
	/**
	 * 
	 * @return number of columns on the board
	 */
	public int getNumCols()
	{
		return BOARD_WIDTH;
	}
	
	/**
	 * Get the theme with which to display the board.
	 * @return The ImageTheme object corresponding to the theme.
	 */
	public ImageTheme getTheme() {
		if (theme == null) {
			return ImageTheme.getDefaultTheme();
		} else {
			return ImageTheme.getThemeByName(theme);
		}
	}
	
	/**
	 * on a valid board:
	 * -there are only numbers from 0-4 inclusive
	 * -there is only one caveman
	 * -there is at least one exit
	 * (use the constants in this class)
	 * @return if the board is valid
	 */
	public boolean isValidBoard()
	{
		byte caveman=0, exit=0, error=0;
		for(int i = 0;i<BOARD_HEIGHT;i++)
		{
			for(int j = 0;j<BOARD_WIDTH;j++)
			{
				if(this.get(i,j)==CAVEMAN)
					caveman++;
				if(this.get(i,j)==EXIT)
					exit++;
				if(this.get(i,j)<0||this.get(i,j)>5)
					error++;
			}
		}
		if(caveman==1&&exit>=1&&error==0)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param location
	 * @return if this location on the board is empty(use the constants in this class)
	 */
	public boolean isEmpty(Loc location)
	{
		if(this.get(location)==EMPTY)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param row
	 * @param column
	 * @return if this location on the board is empty(use the constants in this class)
	 */
	public boolean isEmpty(int row, int column)
	{
		if(this.get(row, column)==EMPTY)
			return true;
		return false;
	}
	
	/**
	 * prints out the board to the console as a grid of integers
	 */
	public void print()
	{
		for(int i = 0;i<BOARD_HEIGHT;i++)
		{
			for(int j = 0;j<BOARD_WIDTH;j++)
			{
				System.out.print(this.get(i, j));
			}
			System.out.println();
		}
	}
	
	/**
	 * 
	 * @param b
	 * @return if the two boards are equal
	 */
	public boolean equals(Boardx b)
	{
		if(b.getBoard()==this.getBoard())
			return true;
		return false;
	}
} // end class