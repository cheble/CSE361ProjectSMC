package myClasses;

import javax.swing.JPanel;

public interface GameInterface {

	int[][] pieceSelect();

	int[][] positionSelect();

	void drawGameMenu(final JPanel panel);

	void drawWinnerMenu(final JPanel panel);
	
	void drawBoard();
	
	boolean isGameQuit();

}