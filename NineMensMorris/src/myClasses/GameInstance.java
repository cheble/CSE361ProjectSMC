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

	// TODO Should we move human input to the player class and pass the gameInterface
	// TODO We need to give feedback to player for incorrect moves, instructions, etc...

	private GameInterface boardInterface;
	private Player[] players;
	private Options myOptions;
	private GameBoard myBoard;
	private boolean isPlacement;
	private int currentPlayer;
	private int gameStatus;

	/**
	 * @param options
	 *            - The options for the game selected in the main menu.
	 */
	public GameInstance(Options options, JFrame contentPane) {
		// Initialize Variables
		isPlacement = true;
		gameStatus = -1;
		// create the players
		myOptions = options;
		players = new Player[2];
		players[0] = new Player();
		//TODO should we get name here? Or should we delete attribute from class?
		players[0].setIsHuman(true);
		if (myOptions.getComputerPlayer()) {
			players[1] = new Computer();
			players[1].setIsHuman(false);
		} else {
			players[1] = new Player();
			players[1].setIsHuman(true);
		}
		

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
		isPlacement = false;
	}

	public void movementPhase() {
		System.out.println("Movement Phase started");
		//while non-starting player has pieces
		while(true){
			//playerTurnMove(currentPlayer);
			if(isGameOver() < 0){
				playerTurnMove(currentPlayer);
			} else { 
				break;
			}
		}
	}

	public int chooseStartingPlayer() {
		// choose random player to start the game
		return (int) ((Math.random() * 99.0) % 2);
	}

	public void playerTurnPlace(int playerID) {
		int position[] = {-1, -1};

		// TODO implement skip/undo somehow
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
					position = boardInterface.positionSelect();
				}
			} else {
				//Computer AI
				position = players[playerID].placePiece();
			}
		}
		//Do not increment number of moves for player
		//Change current player
		currentPlayer = (currentPlayer+1) % 2;
		// pass the board to the gui
		passBoard();
	}

	/**
	 * 
	 * @param player
	 */
	public void playerTurnMove(int playerID) {
		int position[][] = {{-1, -1},{-1, -1}};		
		// TODO implement skip/undo somehow
		
		//old while loop conditions:
//		while (isGameOver() < 0 && ( position[0][0] == -1 || 
//				position[1][0] == -1 ||
//				!isMoveValid(position[0], position[1]) || 
//				myBoard.movePiece(position[0], position[1]) == -1)) {

		// invalid move
		// tell player or computer
		// get new move
		if(players[playerID].getIsHuman()){
			boardInterface.setTurnInfo(playerID, "YOUR TURN<br>CLICK A PIECE");
			while(isGameOver() < 0 && 
					(position[0][0] == -1 || 
					myBoard.getPiece(position[0]) == null ||
					!myBoard.getPiece(position[0]).getOwner().equals(players[playerID])))
			{
				position[0] = boardInterface.positionSelect();
			}
			boardInterface.setPosSelected(position[0][0], position[0][1]);
			boardInterface.setTurnInfo(playerID, "YOUR TURN<br>CLICK A POSITION");
			while(isGameOver() < 0 && 
					(position[1][0] == -1 ||
					myBoard.getPiece(position[1]) != null ||
					!isMoveValid(position[0], position[1]) ||
					myBoard.movePiece(position[0], position[1]) == -1)){
				position[1] = boardInterface.positionSelect();
				//undo first selection if second selection is same piece
				if(Arrays.equals(position[0], position[1])){
					//restart move process
					boardInterface.setBoard(myBoard);
					return;
				}else{
					System.out.println("not equal");
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
			}
//			while(isGameOver() < 0 && 
//					(position[0][0] == -1 || 
//					myBoard.getPiece(position[0]) == null ||
//					!myBoard.getPiece(position[0]).getOwner().equals(players[playerID]) ||
//					!isMovePossible(position[0])))
//			{
//				position[0] = players[playerID].placePiece();
//			}
//			while(isGameOver() < 0 && 
//					(position[1][0] == -1 ||
//					myBoard.getPiece(position[0]) != null) ||
//					myBoard.getPiece(position[0]).getOwner().equals(players[(playerID + 1)%2])){
//				position[1] = players[playerID].placePiece();
//				if(position[1][0] != -1){
//					break;
//				}
//			}
		}
		System.out.printf("[%d, %d]   to   [%d, %d]\n",position[0][0], position[0][1],position[1][0], position[1][1]);
		System.out.println("move accecpted");
		//Increment number of moves for player
		players[playerID].incrementNumMoves();
		//check if move created a mill
		System.out.println("is Mill?");

		if((isGameOver() < 0) && myBoard.isMill(position[1])){
			System.out.println("it is");
			passBoard();
			System.out.println("board passed");
			playerTake(playerID);
		}
		// pass the board to the gui
		passBoard();
		
		//Change current player
		currentPlayer = (currentPlayer+1) % 2;
	}
	public boolean isMovePossible(int[] position){
		int[] testPosition = new int[2] ;
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
		//check if fly mode has started for the player
		if(!fly){
			//fly mode is not enabled yet for player
			//check if positions on either side in same square are open
			testPosition[0] = position[0];
			testPosition[1] = (position[1]+1)% 8;
			if(myBoard.getPiece(testPosition) == null){
				return true;
			}
			testPosition[1] = (position[1]+7)% 8;
			if(myBoard.getPiece(testPosition) == null){
				return true;
			}						
			//a middle piece is selected
			if(position[1] % 2 != 0){
				//check if piece can change squares
				testPosition[1] = position[1];
				if((position[0] % 2) == 0 ){
					testPosition[0] = 1;
					if (myBoard.getPiece(testPosition) == null){
						return true;
					}
				}
				else {
					testPosition[0] = 0;
					if (myBoard.getPiece(testPosition) == null){
						return true;
					}
					testPosition[0] = 2;
					if (myBoard.getPiece(testPosition) == null){
						return true;
					}
				}
			}
			return false;
		}
		return true;
	}
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
		
		return true;
	}

	public void playerTake(int playerID){
		System.out.println("Take a piece");
		int position[] = new int[] {-1, -1};		
		
		while ( isGameOver() < 0 && 
				(position[0] == -1 ||
				!isTakeValid(position) ||
				myBoard.takePiece((playerID+1)%2, position) == -1)) {
			//if invalid move tell player or computer, and get new move
			if(players[playerID].getIsHuman()){
				boardInterface.setTurnInfo(playerID, "YOUR TURN<br>TAKE A PIECE");			
				position = boardInterface.positionSelect();
					
			} else {
				//Computer AI
				position = players[playerID].takePiece();
			}
		}
		
		// TODO implement skip/undo somehow
		passBoard();
	}
	
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
	public int getGameStatus(){
		return gameStatus ;
	}
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

	public void undo() {
		throw new UnsupportedOperationException();
	}

	public void skip() {
		throw new UnsupportedOperationException();
	}

	public void passBoard(){
		boardInterface.setBoard(myBoard);
	}	
}
