package myClasses;

import javax.swing.JPanel;

public interface GameInterface {

	int[] positionSelect();

	void drawGameMenu(final JPanel panel);

	void drawWinnerMenu(int winnerID);
	
	void drawBoard();
	
	boolean isGameQuit();
	
	boolean isGameReset();
	
	int isTurnSkipUndo();
	
	boolean isGameBegan();
	
	void clearSelections();
	
	void setBoard(GameBoard gm);
	
	void setTurnInfo(int playerID, String message);
	
	public String getName(int playerID);

}