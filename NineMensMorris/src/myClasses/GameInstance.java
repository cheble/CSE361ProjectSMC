package myClasses;

import java.util.Arrays;

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
public class GameInstance{


	private GameInterface boardInterface;
	private Player[] players;
	private Options myOptions;
	private GameBoard myBoard;
	volatile private int[][] prevMove;
	volatile private int[][] prevPrevMove;
	volatile private int[] prevTake;
	volatile private int[] prevPrevTake;
	private boolean isPlacement;
	private int currentPlayer;
	private int gameStatus;

	/**
	 * @param contentPane - Window being used by the program.
	 * @param options
	 *            - The options for the game selected in the main menu.
	 */
	public GameInstance(Options options, JFrame contentPane) {
		// Initialize Variables
		isPlacement = true;
		gameStatus = -1;
		prevMove = new int[][] {{-1,-1},{-1,-1}};
		prevPrevMove = new int[][] {{-1,-1},{-1,-1}};
		prevTake = new int[] {-1,-1};
		prevPrevTake = new int[] {-1,-1};
		// create the players
		myOptions = options;
		players = new Player[2];
		players[0] = new Player();
		players[0].setIsHuman(true);
		if (myOptions.getComputerPlayer()) {
			players[1] = new Computer();
			players[1].setIsHuman(false);
		} else {
			players[1] = new Player();
			players[1].setIsHuman(true);
		}
		players[0].setName(options.getPlayerNames(0));
		players[1].setName(options.getPlayerNames(1));
		

		// create the gameboard and draw the Board gui
		myBoard = new GameBoard(players);
		boardInterface = new GameGUI(contentPane, players);
		
		// Wait Until Game Start
		while (true) {
			System.out.print("");
			if(boardInterface.isGameBegan()) {
				break;
			}
		}
		
		players[0].setName(boardInterface.getName(0));
		players[1].setName(boardInterface.getName(1));
		
		
		
		System.out.println("Game Started");
		if(isGameOver() < 0){
			// PlacementPhase
			placementPhase();
		}
		if(isGameOver() != 2){
			if(isGameOver() < 0){
				// MovementPhase
				movementPhase();
			}
		} 
		System.out.println("Game Ended");
		if(isGameOver() == 0){
			gameStatus = 0;
			boardInterface.drawWinnerMenu(getWinnerIndex());
			while (true){
				if (isGameOver() == 1){
					break;
				} else if(isGameOver() == 3){
					gameStatus = 3;
					break;
				}
			}	
		} else {
			gameStatus = isGameOver();
		}
		// Clear contentPane
		contentPane.getContentPane().removeAll();
	}
	
	/**
	 * Control method for handling the placement phase of the game.
	 */
	public void placementPhase() {
		currentPlayer = chooseStartingPlayer();
		//while non-starting player has pieces
		while(myBoard.piecesOnSide(currentPlayer) > 0){
			if(isGameOver() < 0){
				playerTurnPlace(currentPlayer);
			} else { 
				break;
			}
		}
		System.out.println("END PLACEMENT PHASE");
		if(isGameOver() != 2){
			isPlacement = false;
		}
	}

	/**
	 * Control method for handling the movement phase of the game.
	 */
	public void movementPhase() {
		System.out.println("Movement Phase started");
		//save board state 
		//while non-starting player has pieces
		while(true){
			if(isGameOver() < 0){
				playerTurnMove(currentPlayer);
			} else { 
				break;
			}
		}
	}

	/**
	 * Method that randomly chooses a number either 0 or 1 for 
	 * choosing the starting player
	 * @return The index for the player chosen to start.
	 */
	public int chooseStartingPlayer() {
		// choose random player to start the game
		return (int) ((Math.random() * 99.0) % 2);
	}

	/**
	 * Controls the logic and some validation behind allowing a 
	 * player to place their pieces
	 * @param playerID - ID of the current player who is placing pieces.
	 */
	public void playerTurnPlace(int playerID) {
		int position[] = {-1, -1};

		// Set to board and check if move is valid to board
		while ( isGameOver() < 0 && 
				(position[0] == -1 ||
				myBoard.addPiece(playerID, position) == -1)) {
			//if invalid move tell player or computer, and get new move
			if(players[playerID].getIsHuman()){
				boardInterface.setTurnInfo(playerID, "YOUR TURN<br>PLACE A PIECE");
				while(isGameOver() < 0 && 
						(position[0] == -1 || 
						myBoard.getPiece(position) != null
						))
				{
					if(boardInterface.isTurnSkipUndo() != 0){
						// reset undo and skip flags
						passBoard();
					}
					position = boardInterface.positionSelect();
				}
			} else {
				//Computer AI
				position = ((Computer)players[playerID]).placePiece();
			}
		}
		//Do not increment number of moves for player
		//Change current player
		currentPlayer = (currentPlayer+1) % 2;
		// pass the board to the gui
		passBoard();
	}

	/**
	 * Controls the logic and some validation behind allowing a
	 * player to move their pieces.
	 * @param player - ID of player making a move.
	 */
	public void playerTurnMove(int playerID) {
		if (!myBoard.isMovePossible(players[playerID]) && !(myOptions.getFlyRule() != 3  
				&& (((myOptions.getFlyRule() == 1) 
						&& myBoard.piecesOnSide(currentPlayer) >= 6) 
						|| ((myOptions.getFlyRule() == 2) 
								&& myBoard.piecesOnSide(currentPlayer) >= 5)))){
			//then skip turn they shouldn't have gotten trapped
			currentPlayer = (currentPlayer+1) % 2;
			System.out.println("player trapped");
		} else 
		{
			int take[] = {-1,-1};
			int position[][] = {{-1, -1},{-1, -1}};		
	
			if(players[playerID].getIsHuman()){
				boardInterface.setTurnInfo(playerID, "YOUR TURN<br>CLICK A PIECE");
				while(isGameOver() < 0 && 
						(position[0][0] == -1 || 
						myBoard.getPiece(position[0]) == null ||
						!myBoard.getPiece(position[0]).getOwner().equals(players[playerID])))
				{
					if(boardInterface.isTurnSkipUndo() == 2){
						System.out.println("UNDO");
						undo();
						return;
					}
					if(boardInterface.isTurnSkipUndo() == 1){
						System.out.println("SKIP");
						skip();
						return;
					}
					position[0] = boardInterface.positionSelect();
				}
				boardInterface.setPosSelected(position[0][0], position[0][1]);
				boardInterface.setTurnInfo(playerID, "YOUR TURN<br>CLICK A POSITION");
				while(isGameOver() < 0 && 
						(position[1][0] == -1 ||
						myBoard.getPiece(position[1]) != null ||
						!isMoveValid(position[0], position[1]) ||
						myBoard.movePiece(position[0], position[1]) == -1))
				{
					if(boardInterface.isTurnSkipUndo() == 2){
						System.out.println("UNDO");
						undo();
						return;
					}
					//Skip turn if button pressed
					if(boardInterface.isTurnSkipUndo() == 1){
						System.out.println("SKIP");
						skip();
						return;
					}
					position[1] = boardInterface.positionSelect();
					//undo first selection if second selection is same piece
					if(Arrays.equals(position[0], position[1])){
						//restart move process
						passBoard();
						return;
					}
				}
			} else {
				//Computer AI
				while(isGameOver() < 0 && 
					(position[0][0] == -1 || 
					myBoard.getPiece(position[0]) == null ||
					!isMoveValid(position[0], position[1]) ||
					myBoard.movePiece(position[0], position[1]) == -1))
				{
					position = players[playerID].movePiece();
					System.out.print("");
				}
			}
			//Increment number of moves for player
			players[playerID].incrementNumMoves();
			//check if move created a mill	
			if((isGameOver() < 0) && myBoard.isMill(position[1])){
				System.out.println("it is");
				passBoard();
				System.out.println("board passed");
				take = playerTake(playerID);
				// player press undo/skip during playerTake
				if(take[0] == -1){
					// undo game state to start of turn
					myBoard.movePiece(position[1], position[0]);
					players[playerID].decrementNumMoves();
					if(boardInterface.isTurnSkipUndo() == 2){
						// undo
						System.out.println("UNDO");
						undo();
						return;
					} else {
						// skip
						System.out.println("SKIP");
						skip();
						return;
					}
				}
			}
			
			// Save prev moves and takes
			prevPrevTake = prevTake.clone();
			prevTake = take.clone();
			prevPrevMove = prevMove.clone();
			prevMove = position.clone();
			
			// pass the board to the gui
			passBoard();
			
			//Change current player
			currentPlayer = (currentPlayer+1) % 2;
		}
	}
	
	/**
	 * Validation method to determine if a move is allowed.
	 * @param position1 - location of the piece being moved.
	 * @param position2 - location of where to place the piece.
	 * @return A boolean value as to if the move is valid.
	 */
	public boolean isMoveValid(int[] position1, int[] position2) {
		boolean fly = false;
		//if fly mode is on
		if(myOptions.getFlyRule() != 3){
			//check if fly mode has started for the player
			if ((myOptions.getFlyRule() == 1) && myBoard.piecesOnSide(currentPlayer) >= 6){
				fly = true;
			}
			else if ((myOptions.getFlyRule() == 2) && myBoard.piecesOnSide(currentPlayer) >= 5){
				fly = true;
			}
		}
		
		if(myBoard.getPiece(position1) == null){
			return false;
		}
		
		//check if piece is owned by the current player
		if(!(myBoard.getPiece(position1).getOwner().equals(players[currentPlayer]))){
			return false;
		}
		//check if fly mode has started for the player
		if(!fly){
			//fly mode is not enabled yet for player
			//check that its one step away on a path
			//if a corner piece is selected
			if(position1[1] % 2 == 0){
				//piece cannot move to different square
				//this would change for a advance game board type
				if(position1[0] != position2[0]){
					return false;
				}
				//piece must move only 1 space away
				if( (position1[1]+1) % 8 != position2[1] &&
					(position1[1]+7) % 8 != position2[1])
				{
					return false;
				}
				
			}
			//a middle piece is selected
			else{
				//if piece changes squares
				if(position1[0] != position2[0]){
					//piece can not change position on square number
					if(position1[1] != position2[1]){
						return false;
					}
					//square must be plus or minus on square
					if( (position1[0]+1) != position2[0] &&
						(position1[0]-1) != position2[0])
					{
						return false;
					}
				}
				//piece stays on same square
				//piece must move only 1 space away
				else if( (position1[1]+1) % 8 != position2[1] &&
						 (position1[1]+7) % 8 != position2[1])
				{
					
					return false;
				}
			}
		}
		//else doesn't matter where it's placed and ownership is checked earlier
		//System.out.println(fly);
		return true;
	}

	/**
	 * Controls the logic of taking a piece from another player.
	 * @param playerID - ID of player taking a piece.
	 * @return The position of the piece taken.
	 */
	public int[] playerTake(int playerID){
		System.out.println("Take a piece");
		int position[] = new int[] {-1, -1};		
		
		while ( isGameOver() < 0 && 
				(position[0] == -1 ||
				!isTakeValid(position) ||
				myBoard.takePiece((playerID+1)%2, position) == -1)) {
			//if invalid move tell player or computer, and get new move
			if(players[playerID].getIsHuman()){
				if(boardInterface.isTurnSkipUndo() != 0){
					return new int[] {-1,-1};
				}
				boardInterface.setTurnInfo(playerID, "YOUR TURN<br>TAKE A PIECE");			
				position = boardInterface.positionSelect();
				System.out.printf("[%d, %d]\n",position[0], position[1]);
			} else {
				//Computer AI
				position = ((Computer)players[playerID]).takePiece();
			}
		}
		
		passBoard();
		return position;
	}
	
	/**
	 * Validation method to check if a chosen piece can be taken.
	 * @param position - location of the piece to be taken.
	 * @return Whether the taking of the piece is allowed.
	 */
	public boolean isTakeValid(int[] position){
		//check if a piece is at the position
		if(myBoard.getPiece(position) == null){
			return false;
		}
		//check if piece is owned by the other player
		if(myBoard.getPiece(position).getOwner().equals(players[currentPlayer])){
			return false;
		}
		//check if piece is in a mill
		if(myBoard.isMill(position)){
			//false if piece is in one of multiple mills
			//get pieces from board owned by player and check if any are not in mills
			if(myBoard.hasNonMillPieces(players[(currentPlayer+1)%2])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @return The current status of the game.
	 */
	public int getGameStatus(){
		return gameStatus ;
	}
	
	/**
	 * Method to determine if the game should end.
	 * @return End game codes 
	 * <dl>
	 * <dt>-1</dt>
	 * <dd>- Game is not yet over</dd>
	 * <dt>0</dt>
	 * <dd>- Game ended normally with a winner,<br> 
	 * and is to go to main menu.</dd>
	 * <dt>1</dt>
	 * <dd>- Game was ended in game and is to go to the menu.</dd>
	 * <dt>2</dt>
	 * <dd>- Game was restarted in game.</dd>
	 * <dt>3</dt>
	 * <dd>- Game ended normally with a winner,<br> 
	 * and the game is to restart. </dd>
	 * </dl>
	 */
	public int isGameOver() {
		// If game is quit inside of interface.
		if (boardInterface.isGameQuit()) {
			System.out.print("");
			//quit game here
			return 1;
		}
		
		// If a player has more than 6 of his nine pieces taken
		if (!isPlacement) {
			if (myBoard.piecesOnSide(0) > 6 || myBoard.piecesOnSide(1) > 6) {
				if (boardInterface.isGameReset()){
					// Game was reset from winner menu
					return 3;
				}
				return 0; // Game is over
			}
		}

		if (boardInterface.isGameReset()){
			// Game was reset from in game menu
			return 2;
		}

		return -1;
	}

	
	/**
	 * Winner of the game
	 * @return The index of the winner of the game.
	 */
	public int getWinnerIndex() {
		if(isPlacement){
			return -1;
		}
		if (myBoard.piecesOnSide(0) > 6) {
			return 1; // players[1] wins
		}
		if (myBoard.piecesOnSide(1) > 6) {
			return 0; // players[0] wins
		}
		return -1;
	}

	
	/**
	 * Determines who won the game.
	 * @return The player that won the game.
	 */
	public Player getWinner() {
		if(isPlacement){
			return null;
		}
		if (myBoard.piecesOnSide(0) > 6) {
			System.out.println("Player 2 Won!");
			return players[1]; // players[1] wins
		}
		if (myBoard.piecesOnSide(1) > 6) {
			System.out.println("Player 1 Won!");
			return players[0]; // players[0] wins
		}
		return null;
	}

	/**
	 * Control logic for completing a skip during the game.
	 */
	public void skip(){
		// Save current move as a skip and save prev moves
		prevPrevTake = prevTake.clone();
		prevTake = new int[] {-1,-1};
		prevPrevMove = prevMove.clone();
		prevMove =  new int[][] {{-2,-2},{-2,-2}};	//signifies skip
		players[currentPlayer].incrementNumMoves();
		passBoard();
		currentPlayer = (currentPlayer+1) % 2;
	}
	
	/**
	 * Control logic for completing an undo during the game.
	 */
	public void undo() {
		// Only works for human players
		
		System.out.println("undo function");
		
		if(myOptions.getComputerPlayer()){
			System.out.println("undo hvc");
			// Human vs Computer game
			// UNDO Computer and Human moves here
			if (prevPrevMove[0][0] == -2){
				//last human move was a skip
				// UNDO Computer players[1] move here
				System.out.println("Computer Move");
				System.out.println(prevTake[0] + ", "+ prevTake[1]);
				if(prevTake[0] != -1){
					// 		undo take piece
					// take piece from side and add to board
					System.out.println("Adding Human Piece");
					System.out.println(myBoard.addPiece(0, prevTake));
					System.out.println("Added Human Piece");
				}
				myBoard.movePiece(prevMove[1], prevMove[0]);
				players[1].decrementNumMoves();
				players[currentPlayer].decrementNumMoves();
				// reset saved moves
				prevMove = new int[][] {{-1,-1},{-1,-1}};
				prevPrevMove = new int[][] {{-1,-1},{-1,-1}};
				prevTake = new int[] {-1,-1};
				prevPrevTake = new int[] {-1,-1};
			} else if(prevPrevMove[0][0] != -1){
				System.out.println("undo allowed");
				// Don't allow undo of placement
				// UNDO Computer players[1] move here
				System.out.println("Computer Move");
				System.out.println(prevTake[0] + ", "+ prevTake[1]);
				if(prevTake[0] != -1){
					// 		undo take piece
					// take piece from side and add to board
					System.out.println("Adding Human Piece");
					System.out.println(myBoard.addPiece(0, prevTake));
					System.out.println("Added Human Piece");
				}
				myBoard.movePiece(prevMove[1], prevMove[0]);
				players[1].decrementNumMoves();
				// UNDO Human players[0] move here
				System.out.println("Human Move");
				System.out.println(prevPrevTake[0] + ", "+ prevPrevTake[1]);
				if(prevPrevTake[0] != -1){
					// 		undo take piece
					// take piece from side and add to board
					System.out.println("Adding Computer Piece");
					System.out.println(myBoard.addPiece(1, prevPrevTake));
					System.out.println("Added Computer Piece");
				}
				myBoard.movePiece(prevPrevMove[1], prevPrevMove[0]);
				players[0].decrementNumMoves();
				// current player stays the same
				// reset saved moves
				prevMove = new int[][] {{-1,-1},{-1,-1}};
				prevPrevMove = new int[][] {{-1,-1},{-1,-1}};
				prevTake = new int[] {-1,-1};
				prevPrevTake = new int[] {-1,-1};
				System.out.println("undo complete");
			} else {
				System.out.println("undo NOT allowed");
			}
		}else{
			System.out.println("undo hvh");
			// Human vs Human
			System.out.println("undo piecesOS:" + myBoard.piecesOnSide((currentPlayer+1) % 2));
			if (prevMove[0][0] == -2){
				System.out.println("undoing a skip");
				// last human move was a skip
				// current player goes back to previous
				currentPlayer = (currentPlayer+1) % 2;
				players[currentPlayer].decrementNumMoves();
				// reset saved moves
				prevMove = new int[][] {{-1,-1},{-1,-1}};
				prevPrevMove = new int[][] {{-1,-1},{-1,-1}};
				prevTake = new int[] {-1,-1};
				prevPrevTake = new int[] {-1,-1};				
			} else if(prevMove[0][0] != -1){
				System.out.println("undo allowed");
				// current player goes back to previous
				currentPlayer = (currentPlayer+1) % 2;
				System.out.println(prevTake[0] + ", "+ prevTake[1]);
				if(prevTake[0] != -1){
					// 		undo take piece
					// take piece from side and add to board
					System.out.println("Adding Piece");
					System.out.println(myBoard.addPiece((currentPlayer+1) % 2, prevTake));
					System.out.println("Added Piece");
				}
				myBoard.movePiece(prevMove[1], prevMove[0]);
				
				players[currentPlayer].decrementNumMoves();

				prevMove = new int[][] {{-1,-1},{-1,-1}};
				prevPrevMove = new int[][] {{-1,-1},{-1,-1}};
				prevTake = new int[] {-1,-1};
				prevPrevTake = new int[] {-1,-1};
				System.out.println("undo complete");
			} else {
				System.out.println("undo NOT allowed");
			}
		}
		passBoard();
	}

	/**
	 * Sends the current state of the board to the gui.
	 */
	public void passBoard(){
		boardInterface.setBoard(myBoard);
	}	
}
