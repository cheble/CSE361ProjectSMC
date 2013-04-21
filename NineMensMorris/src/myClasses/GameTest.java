package myClasses;

import javax.swing.JPanel;

public class GameTest implements GameInterface {

	@Override
	public int[] positionSelect() {
		return null;
	}

	@Override
	public void drawGameMenu(JPanel panel) {
		
	}

	@Override
	public void drawWinnerMenu(int winnerID) {
		
	}

	@Override
	public void drawBoard() {
		
	}

	@Override
	public boolean isGameQuit() {
		return false;
	}

	@Override
	public void setBoard(GameBoard gm) {
		
	}

	@Override
	public void setTurnInfo(int playerID, String message) {
		
	}

	@Override
	public boolean isGameBegan() {
		return false;
	}

	@Override
	public void clearSelections() {
		
	}

	@Override
	public String getName(int playerID) {
		return null;
	}

	@Override
	public int isTurnSkipUndo() {
		return 0;
	}

	@Override
	public boolean isGameReset() {
		return false;
	}

	@Override
	public void setPosSelected(int ring, int position) {
		
	}

	@Override
	public void setFlyMode(int playerID, boolean setMode) {
		
	}

}
