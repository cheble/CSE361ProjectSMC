package myClasses;

import javax.swing.JPanel;

public interface GameInterface {

	int[] pieceSelect();

	int[] positionSelect();

	void drawGameMenu(final JPanel panel);

	void drawWinnerMenu(int winnerID);
	
	void drawBoard();
	
	boolean isGameQuit();
	
	boolean isGameBegan();
	
	void clearSelections();
	
	void setBoard(GameBoard gm);
	
	void setTurnInfo(int playerID, String message);

}