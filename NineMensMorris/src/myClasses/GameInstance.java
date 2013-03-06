package myClasses;

/**
 * <b>Game Instance</b><p>
 * The main instance of the game. It controls the gameboard gui, the players, and the piece movement logic.
 * @version 0.1
 * @author Mitchel Pigsley, Chase Heble, Sam Troxel
 */
public class GameInstance {
	
	//TODO Should we have the playerID (array index) as a Player attribute?  This will get rid of a parameter in a number of functions.
	//TODO Also, should we do use playerID instead of players[] index?  We could then call the players player1 and player2.

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
		return (int) ((Math.random()*99.0) % 2);
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
		//if game is in movement phase
		if(true){
			//make a variable for phase of the game
		}
		//If a player has more than 6 of his nine pieces taken
		if( myBoard.piecesOnSide(0) > 6 ||
			myBoard.piecesOnSide(1) > 6)
		{
			return true;	//Game is over
		}
		
		return false;
	}

	public Player getWinner() {
		if( myBoard.piecesOnSide(0) > 6){
			return players[0];	//Game is over
		}
		if(myBoard.piecesOnSide(1) > 6){
			
		}
		return null;
	}

	public void Undo() {
		throw new UnsupportedOperationException();
	}

	public void Skip() {
		throw new UnsupportedOperationException();
	}

}