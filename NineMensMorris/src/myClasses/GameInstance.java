package myClasses;

/**
 * <b>Game Instance</b><p>
 * The main instance of the game. It controls the gameboard gui, the players, and the piece movement logic.
 * @version 0.1
 * @author Mitchel Pigsley, Chase Heble, Sam Troxel
 */
public class GameInstance {

	private GameInterface boardInterface;
	private Player[] players;
	private Options myOptions;
	private GameBoard myBoard;

	/**
	 * @param options - The options for the game selected in the main menu.
	 */
	public GameInstance(Options options) {
		// create the players
		myOptions = options;
		players = new Player[2];
		players[0] = new Player();
		if (myOptions.getComputerPlayer()){
			players[1] = new Computer();
		} else {
			players[1] = new Player();
		}
		
		// create the gameboard and draw the Board gui
		myBoard = new GameBoard(players);
		boardInterface = new GameGUI();
		((GameGUI)boardInterface).drawBoard();
	}

	public int chooseStartingPlayer() {
		throw new UnsupportedOperationException();
	}

	public void playerTurnPlace() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 */
	public void playerTurnMove(Player player) {
		throw new UnsupportedOperationException();
	}

	public boolean isTurnValid() {
		throw new UnsupportedOperationException();
	}

	public boolean isGameOver() {
		throw new UnsupportedOperationException();
	}

	public Player getWinner() {
		throw new UnsupportedOperationException();
	}

	public void Undo() {
		throw new UnsupportedOperationException();
	}

	public void Skip() {
		throw new UnsupportedOperationException();
	}

}