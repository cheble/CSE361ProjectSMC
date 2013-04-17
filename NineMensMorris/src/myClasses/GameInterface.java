package myClasses;

import javax.swing.JPanel;

public interface GameInterface {

	/**
	 * Returns the position that the player has selected
	 * on the board.
	 * 
	 * @return the selected position on board
	 */
	int[] positionSelect();

	/**
	 * When given a JPanel, this function will draw the
	 * game menu onto that single JPanel.
	 * 
	 * @param The panel for menu to be drawn
	 */
	void drawGameMenu(final JPanel panel);

	/**
	 * Will draw the winner screen/menu when this
	 * function is called. The game is over at this
	 * point so the board view can be deleted.
	 * 
	 * @param Winner ID
	 */
	void drawWinnerMenu(int winnerID);
	
	/**
	 * Will draw the board on the JFrame passed on
	 * instantiation.
	 */
	void drawBoard();
	
	/**
	 * Returns a boolean value for if the game has 
	 * been quit in the in-game menu.
	 * 
	 * @return true if game is quit from menu
	 */
	boolean isGameQuit();
	
	/**
	 * Returns a boolean value for if the game has
	 * been reset in the in-game menu.
	 * 
	 * @returntrue if game is reset from menu
	 */
	boolean isGameReset();
	
	/**
	 * Returns if the menu buttons for "Skip" & "Undo"
	 * have been selected.
	 * 
	 * @return	1 for skip, 2 for undo, 0 for nothing
	 */
	int isTurnSkipUndo();
	
	/**
	 * Returns a boolean value for if the game has been begun,
	 * which is set to true after the board has been drawn.
	 * 
	 * @return	true for Game begin
	 */
	boolean isGameBegan();
	
	/**
	 * Clears the user selected position on board
	 */
	void clearSelections();
	
	/**
	 * Will set the game board, given a GameBoard. This
	 * will also clear the yellow pieces set in the 
	 * setPosSelected(..) function
	 * 
	 * @param GameBoard
	 */
	void setBoard(GameBoard gm);
	
	/**
	 * Set the Information given to each player at each
	 * point in the game. This function will delete the info
	 * given to the player opposite of the player passed
	 * into this function.
	 * 
	 * @param playerID
	 * @param Message to be displayed
	 */
	void setTurnInfo(int playerID, String message);
	
	/**
	 * Get the name of the player based on their player
	 * ID
	 * 
	 * @param playerID
	 * @return Name of Player
	 */
	String getName(int playerID);
	
	/**
	 * Set the piece yellow for the position that the
	 * player has selected. If another position has been
	 * selected, then this function will clear any other piece
	 * that has been set to yellow (Only one yellow 
	 * piece on the board at a time)
	 * 
	 * @param ring
	 * @param position
	 */
	void setPosSelected(int ring, int position);
	
	/**
	 * Enable/Disable the Banner for Fly Mode for a player
	 * based on the playerID passed.
	 * 
	 * @param playerID
	 * @param true for enable, false for disable
	 */
	void setFlyMode(int playerID, boolean setMode);

}