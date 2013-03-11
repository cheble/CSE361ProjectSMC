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
		boardInterface = new GameGUI(contentPane, numHumans, players);

		// Start Placement Phase
		while (!isGameOver()) {
			if (isPlacement) {
				// PlacementPhase
				placementPhase();
			} else {
				// MovementPhase
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
			playerTurnPlace(currentPlayer);
			playerTurnPlace(currentPlayer);
		}
	}

	public void movementPhase() {
		//while non-starting player has pieces
		while(!isGameOver()){
			playerTurnMove(currentPlayer);
			if(!isGameOver()){
				playerTurnMove(currentPlayer);
			}
		}
	}

	public int chooseStartingPlayer() {
		// choose random player to start the game
		return (int) ((Math.random() * 99.0) % 2);
	}

	public void playerTurnPlace(int playerID) {
		int[] selectedPosition = null;
		if(players[playerID].getIsHuman()){
			while(true){
				selectedPosition = boardInterface.pieceSelect();
				if(selectedPosition != null){
					break;
				}
			}
		} else {
			//TODO do something about the computer AI to set selectedPosition
		}
		// TODO implement skip/undo somehow

		// TODO put some conditions in here

		// TODO Set to board and check if move is valid to board
		while (myBoard.addPiece(playerID, selectedPosition) == -1) {
			// TODO if invalid move tell player or computer, and get new move
			if(players[playerID].getIsHuman()){
				while(true){
					selectedPosition = boardInterface.pieceSelect();
					if(selectedPosition != null){
						break;
					}
				}
			} else {
				//TODO do something about the computer AI to set selectedPosition
			}
		}
		//Increment number of moves for player
		players[playerID].incrementNumMoves();
		//Change current player
		currentPlayer = (currentPlayer+1) % 2;
		// pass the board to the gui
	}

	/**
	 * 
	 * @param player
	 */
	public void playerTurnMove(int playerID) {
		int position[][] = new int[2][2];
		position[0] = null;		//piece selected
		position[1] = null;		//position selected
		if(players[playerID].getIsHuman()){
			while(true){
				position[0] = boardInterface.pieceSelect();
				if(position[0] != null){
					break;
				}
			}
			while(true){
				position[1] = boardInterface.positionSelect();
				if(position[1] != null){
					break;
				}
			}
		} else {
			//TODO do something about the computer AI to set selectedPiece and selectedPosition
			position = players[playerID].movePiece();
		}
		
		
		// TODO Check if move is valid to game rules
		// TODO implement skip/undo somehow
		if(isMoveValid(position[0], position[1])){
			while (myBoard.movePiece(position[0], position[1]) == -1) {
				// invalid move
				// tell player or computer
				// get new move
				if(players[playerID].getIsHuman()){
					while(true){
						position[0] = boardInterface.pieceSelect();
						if(position[0] != null){
							break;
						}
					}
					while(true){
						position[1] = boardInterface.positionSelect();
						if(position[1] != null){
							break;
						}
					}
				} else {
					//TODO do something about the computer AI to set selectedPiece and selectedPosition
					position = players[playerID].movePiece();
				}
			}
			//Increment number of moves for player
			players[playerID].incrementNumMoves();
			//Change current player
			currentPlayer = (currentPlayer+1) % 2;
			// pass the board to the gui
			//check if move created a mill
			if(myBoard.isMill(position[1])){
				playerTake(playerID);
			}
		}
						
	}
	
	public boolean isMoveValid(int[] position1, int[] position2) {
		//if fly mode is on
		if(myOptions.getFlyRule() != 3){
			//check if fly mode has started for the player
		}
		
		//check if piece is owned by the current player
		if(myBoard.getPiece(position1).getOwner() != players[currentPlayer]){
			return false;
		}
		//check that its one step away on a path
		//if a corner piece is selected
		if(position1[0] % 2 == 0){
			//piece cannot move to different square
			//this would change for a advance game board type
			if(position1[0] != position2[0]){
				return false;
			}
			//piece must move only 1 space away
			if( (position1[1]+1) % 8 != position2[1] ||
				(position1[1]-1) % 8 != position2[1])
			{
				return false;
			}
			
		}
		//a middle piece is selected
		else{
			//if piece changes squares
			if(position1[0] != position2[0]){
				//square must be plus or minus on square
				if( position1[0]+1 != position2[0] ||
					position1[0]-1 != position2[0])
				{
					return false;
				}
			}
			//piece stays on same square
			//piece must move only 1 space away
			else if( (position1[1]+1) % 8 != position2[1] ||
					 (position1[1]-1) % 8 != position2[1])
			{
				return false;
			}
		}
		
		return true;
	}

	public void playerTake(int playerID){
		int position[] = null;	//piece selected
		if(players[playerID].getIsHuman()){
			while(true){
				position = boardInterface.pieceSelect();
				if(position != null){
					break;
				}
			}
		} else {
			//TODO do something about the computer AI to set selectedPiece and selectedPosition
			position = players[playerID].takePiece();
		}
		
		// TODO Check if move is valid to game rules
		// TODO implement skip/undo somehow
		if(isTakeValid(position)){
			while (myBoard.takePiece(playerID, position) == -1) {
				// invalid move
				// tell player or computer
				// get new move
				if(players[playerID].getIsHuman()){
					while(true){
						position = boardInterface.pieceSelect();
						if(position != null){
							break;
						}
					}
				} else {
					//TODO do something about the computer AI to set selectedPiece and selectedPosition
					position = players[playerID].takePiece();
				}
			}
			
		}

	}
	
	public boolean isTakeValid(int[] position){
		//check if a piece is at the position
		if(myBoard.getPiece(position) == null){
			return false;
		}
		//check if piece is owned by the other player
		if(myBoard.getPiece(position).getOwner() == players[currentPlayer]){
			return false;
		}
		//check if piece is in a mill
		if(myBoard.isMill(position)){
			//false if piece is in one of multiple mills
			//get pieces from board owned by player and check if any are not in mills
			if(myBoard.hasNonMillPieces(players[(currentPlayer+1)%2])){
				return false;
			}
		}
		return true;
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

}
