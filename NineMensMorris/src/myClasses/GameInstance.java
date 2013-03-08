package myClasses;

import java.awt.Component;

import javax.swing.JFrame;

/**
 * <b>Game Instance</b>
 * <p>
 * The main instance of the game. It controls the gameboard gui, the players,
 * and the piece movement logic.
 * 
 * @version 0.1
 * @author Mitchel Pigsley, Chase Heble, Sam Troxel
 */
public class GameInstance {

	// TODO Should we have the playerID (array index) as a Player attribute?
	// This will get rid of a parameter in a number of functions.
	// TODO Also, should we do use playerID instead of players[] index? We could
	// then call the players player1 and player2.

	private GameInterface boardInterface;
	private Player[] players;
	private Options myOptions;
	private GameBoard myBoard;
	private boolean isPlacement;
	private int currentPlayer;

	/**
	 * @param options
	 *            - The options for the game selected in the main menu.
	 */
	public GameInstance(Options options, JFrame contentPane) {
		// Initialize Variables
		isPlacement = true;
		int numHumans;
		
		// create the players
		myOptions = options;
		players = new Player[2];
		players[0] = new Player();
		if (myOptions.getComputerPlayer()) {
			players[1] = new Computer();
			numHumans = 1;
		} else {
			players[1] = new Player();
			numHumans = 2;
		}

		// create the gameboard and draw the Board gui
		myBoard = new GameBoard(players);
		boardInterface = new GameGUI(contentPane, numHumans);

		// Start Placement Phase
		while (!isGameOver()) {
			if (isPlacement) {
				placementPhase();
			} else {
				movementPhase();
			}
		}

		// Clear contentPane
		contentPane.getContentPane().removeAll();

	}

	public void placementPhase() {
		currentPlayer = chooseStartingPlayer();
		//while non-starting player has pieces
		while(myBoard.piecesOnSide((currentPlayer+1) % 2) > 0){
			playerTurnPlace(startingPlayer);
			playerTurnPlace((startingPlayer+1) % 2);
		}
	}

	public void movementPhase() {
		startingPlayer = chooseStartingPlayer();
		//while non-starting player has pieces
		while(!isGameOver()){
			playerTurnPlace(startingPlayer);
			if(!isGameOver()){
				playerTurnPlace((startingPlayer+1) % 2);
			}
		}
	}

	public int chooseStartingPlayer() {
		// choose random player to start the game
		return (int) ((Math.random() * 99.0) % 2);
	}

	public void playerTurnPlace(int playerID) {
		int position[] = players[playerID].placePiece();
		// TODO Might have to get rid of function call and implement here
		// using condition for human or computer

		// TODO implement skip/undo somehow

		// TODO put some conditions in here
		// TODO Check if move is valid to game rules

		// TODO Set to board and check if move is valid to board
		while (myBoard.addPiece(playerID, position) == -1) {
			// invalid move
			// tell player or computer
			// get new move
			position = players[playerID].placePiece();
		}
		//Increment number of moves for player
		players[playerID].incrementNumMoves();
		//Change current player
		currentPlayer = (currentPlayer+1) % 2;
		// pass the board to the gui
		passBoard();
	}

	/**
	 * 
	 * @param player
	 */
	public void playerTurnMove(Player player) {
		// TODO Might have to get rid of function call and implement here
		// using condition for human or computer

		// TODO implement skip/undo somehow

		// TODO put some conditions in here
		// TODO Check if move is valid to game rules

		// TODO Set to board and check if move is valid to board
		while (myBoard.addPiece(playerID, position) == -1) {
			// invalid move
			// tell player or computer
			// get new move
			position = players[playerID].placePiece();
		}
		//Increment number of moves for player
		players[playerID].incrementNumMoves();
		//Change current player
		currentPlayer = (currentPlayer+1) % 2;
		// pass the board to the gui
		passBoard();
		
		throw new UnsupportedOperationException();
	}

	public boolean isTurnValid() {
		//

		//

		//
		throw new UnsupportedOperationException();
	}

	public boolean isGameOver() {
		// If game is quit inside of interface.
		if (boardInterface.isGameQuit()) {
			System.out.print("");
			return true;
		}

		// If a player has more than 6 of his nine pieces taken
		if (!isPlacement) {
			if (myBoard.piecesOnSide(0) > 6 || myBoard.piecesOnSide(1) > 6) {
				return true; // Game is over
			}
		}

		return false;
	}

	public Player getWinner() {
		if (myBoard.piecesOnSide(0) > 6) {
			return players[0]; // players[0] wins
		}
		if (myBoard.piecesOnSide(1) > 6) {
			return players[1]; // players[1] wins
		}
		return null;
	}

	public void undo() {
		throw new UnsupportedOperationException();
	}

	public void skip() {
		throw new UnsupportedOperationException();
	}

	// Pass board to gameInterface
	// Or use drawBaord in gameInterface class???
	public void passBoard() {
		//
	}

}