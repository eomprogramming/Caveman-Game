                                      
package controlClasses;

import java.io.IOException;

public class EditBoard extends Boardx
{
	/**
	 * constructor
	 */
	public EditBoard()
	{
		super();
	}
	
	/**
	 * constructor
	 * @param board
	 */
	public EditBoard(int[][] board)
	{
		super(board);
	}
	
	/**
	 * sets caveman location
	 * make sure that there is always only one caveman on the board
	 * use the constants in the Board class
	 * @param location
	 */
	public void setCavemanLoc(Loc location)
	{
		board[getCavemanLoc().getRow()][getCavemanLoc().getCol()] = EMPTY;
		board[location.getRow()][location.getCol()] = CAVEMAN;
	}
	
	/**
	 * Set the theme of this Boardx
	 * @param name the name of the theme.
	 */
	public void setTheme(String name) {
		theme = name;
	}
	
	/**
	 * places an object such as wall, boulder or exit at a certain location
	 * you cannot place an object on the caveman
	 * use the constants in the Board class
	 * @param objectToPlace
	 * @param location
	 */
	public void place(int objectToPlace, Loc location)
	{
		if (board[location.getRow()][location.getCol()] != CAVEMAN) // check if there's a caveman
		{	
			board[location.getRow()][location.getCol()] = EMPTY;
			board[location.getRow()][location.getCol()] = objectToPlace;
		}
	}
	
	/**
	 * empties the specified location
	 * you cannot empty the square that the caveman is on
	 * use the constants in the Board class
	 * @param location
	 */
	public void empty(Loc location)
	{
		if (board[location.getRow()][location.getCol()] != CAVEMAN) // check if there's a caveman
		{	
			board[location.getRow()][location.getCol()] = EMPTY;
		} 
	}
	
	/**
	 * moves the object at location1 to location2
	 * you cannot move an object on top of the caveman
	 * @param location1
	 * @param location2
	 */
	public void move(Loc location1, Loc location2)
	{
		if (this.get(location2) != CAVEMAN) // check if there's a caveman
		{	
			int objectToMove;
			objectToMove = this.get(location1);
			board[location2.getRow()][location2.getCol()] = objectToMove;
		}
	}
	
	/**
	 * Ian will wrtie this part
	 * @param fileName
	 * @return if the file was successfully saved
	 * @throws IOException 
	 */
	public boolean save(String fileName) throws IOException
	{
		MapIO writer = new MapIO (fileName); 
		
		writer.write(new Boardx(this.getBoard()));
		return false;
	}
}