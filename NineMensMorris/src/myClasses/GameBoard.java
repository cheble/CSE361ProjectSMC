package myClasses;

public class GameBoard {
	
	//Game rules Validation is not handled in this class

	//Board:
	//First dimension is square id, 0 being outermost to 2 being innermost
	//Second dimension is position on square, from 0 to 7 going clockwise from top-left
	//Position:
	//One dimensional array containing square id and position id on square
	//
	private Piece[][] board;
	private Piece[][] side;

	public GameBoard(Player[] players) {
		// Initialize Global Variables
		board = new Piece[3][8];
		side = new Piece[2][9];
		
		// Place all Pieces on Player's Sides
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 9; j++) {
				side[i][j] = new Piece(players[i]);
			}
		}
	}

	/**
	 * Add's a piece belonging to the player with the given PlayerID
	 * to the position given.
	 * @return returns -1 for error
	 * @param position
	 */
	public int addPiece(int playerID, int[] position) {
		//check if position is in range
		if(!isPositionValid(position)){
			return -1;
		}
		// look for highest indexed piece on players side
		int index = piecesOnSide(playerID) - 1;

		//--------Conditions BEGIN
		//check for no pieces on side
		if (index == -1) {
			return -1;
		}
		//check if board position is taken
		if (board[position[0]][position[1]] != null) {
			return -1;
		}
		//--------Conditions END

		//add piece to board
		board[position[0]][position[1]] = side[playerID][index];
		//remove piece from side
		side[playerID][index] = null;

		return 0;
	}

	/**
	 * Moves a piece in position1 to position2 if possible.
	 * @return returns -1 for error
	 * @param position1 : position to move from
	 * @param position2 : position to move to
	 */
	public int movePiece(int[] position1, int[] position2) {
		//check if position1 and position2 are in range
		if(!isPositionValid(position1) || !isPositionValid(position1)){
			return -1;
		}
		//--------Conditions BEGIN
		//check if a piece is at position1 on board
		if (board[position1[0]][position1[1]] == null) {
			return -1;
		}
		//check if board at position2 is taken
		if (board[position2[0]][position2[1]] != null) {
			return -1;
		}
		//--------Conditions END

		//move piece to position2
		board[position2[0]][position2[1]] = board[position1[0]][position1[1]];
		//remove piece from position1
		board[position1[0]][position1[1]] = null;

		return 0;
	}

	/**
	 * Takes a piece at the given position from the board and places it on the side
	 * board belonging to the given player	 * 
	 * @param position : a position on the board
	 * @param playerID : ID of the player who's side the
	 *  piece will go to. Should be the owner of the piece.
	 *  @return -1 if an error occurs and can't take piece.
	 */
	public int takePiece(int playerID, int[] position) {
		//check if position is in range
		if(!isPositionValid(position)){
			return -1;
		}
		//--------Conditions BEGIN
		//check if a piece is at position on board
		if (board[position[0]][position[1]] == null) {
			return -1;
		}
		//--------Conditions END

		int index = piecesOnSide(playerID);
		// add piece to side
		side[playerID][index] = board[position[0]][position[1]];
		// remove piece from position on board
		board[position[0]][position[1]] = null;
		return 0;
	}

	
	/**
	 * Checks if a piece on the gameboard is a part of a mill
	 * @param position : Position of the piece to check.
	 * @return A boolean value as to if the piece is in a mill.
	 */
	public boolean isMill(int[] position) {
		//check if position is in range
		if(!isPositionValid(position)){
			return false;
		}
		//--------Conditions BEGIN
		//check if a piece is at position on board
		if(board[position[0]][position[1]] == null){
			return false;
		}
		//--------Conditions END

		//get piece owner
		Player player = board[position[0]][position[1]].getOwner();
		//if middle piece
		if(position[1] % 2 == 1){
			//check across squares mill
			if( board[(position[0]+1)%3][position[1]] != null &&
				board[(position[0]+2)%3][position[1]] != null)
			{
				if( board[(position[0]+1)%3][position[1]].getOwner() == player &&
					board[(position[0]+2)%3][position[1]].getOwner() == player)
				{
					return true;
				}
			}
			//check same square mill
			if( board[(position[0])][(position[1]+7) % 8] != null &&
				board[(position[0])][(position[1]+1) % 8] != null)
			{
				if( board[position[0]][(position[1]+7) % 8].getOwner() == player &&
					board[position[0]][(position[1]+1) % 8].getOwner() == player)
				{
					return true;
				}
			}
		}else{	//it is a corner piece
			//check clockwise mill
			if( board[(position[0])][(position[1]+1) % 8] != null &&
				board[(position[0])][(position[1]+2) % 8] != null)
			{
				if( board[position[0]][(position[1]+1) % 8].getOwner() == player &&
					board[position[0]][(position[1]+2) % 8].getOwner() == player)
				{
					return true;
				}
			}
			//check counter-clockwise mill
			if( board[(position[0])][(position[1]+7) % 8] != null &&
				board[(position[0])][(position[1]+6) % 8] != null)
			{
				if( board[position[0]][(position[1]+7) % 8].getOwner() == player &&
					board[position[0]][(position[1]+6) % 8].getOwner() == player)
				{
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * Checks if the player has any pieces that aren't in a Mill.
	 * @param player
	 * @return A boolean value as to if the player has non mill pieces.
	 */
	public boolean hasNonMillPieces(Player player) {
		//Go through each piece on board
		for(int i=0; i<=2; i++){
			for(int j=0; j<=7; j++){
				//check if piece is at position
				if(board[i][j] == null){
					//go to the next position
				}
				//check if piece is owned by player
				else if(board[i][j].getOwner() == player){
					//check if piece is in a mill
					if(!isMill(new int[]{i,j})){
						return true;
					}
				}
			}
		}
		
		return false;
	}

	/**
	 * Gives the number of pieces a player has on their side.
	 * @param playerID 
	 * @return The number pieces on that player's side.
	 */
	public int piecesOnSide(int playerID) {
		int i = 9;
		while (side[playerID][i - 1] == null && i > 0) {
			i--;
			if (i == 0) {
				break;
			}
		}
		// change from index to count
		return i;
	}

	/**
	 * Retrieves the piece at the given location.
	 * @param position
	 * @return The Piece object at the given position.
	 */
	public Piece getPiece(int[] position) {
		//check if position is in range
		if(!isPositionValid(position)){
			return null;
		}
		return board[position[0]][position[1]];
	}
	
	/**
	 * Checks if a position exists on the board.
	 * @param position
	 * @return A boolean value as to if the position is on the board.
	 */
	public boolean isPositionValid(int[] position) {
		if(position == null){
			return false;
		}
		if(position[0] < 0 || position[0] > 2){
			return false;
		}
		if(position[1] < 0 || position[1] > 7){
			return false;
		}
		return true;
	}
	
	/**
	 * Get's the Piece array representing the board.
	 * @return The board array.
	 */
	public Piece[][] getBoard() {
		return board;
	}
	
	/**
	 * Checks if it is possible for a player to make a move at all.
	 * Does not account for fly mode, so must be checked as well when calling.
	 * @param player
	 * @return A boolean value as to if the player will 
	 * be able to make a move that turn.
	 */
	public boolean isMovePossible(Player player){
		//Go through each piece on board
		for(int i=0; i<=2; i++){
			for(int j=0; j<=7; j++){
				//check if piece is at position
				if(board[i][j] == null){
					//go to the next position
				}
				//check if piece is owned by player
				else if(board[i][j].getOwner() == player){
					//check if positions on either side in same square are open
					if(board[i][(j+1) % 8] == null || board[i][(j+7) % 8] == null){
						return true;
					}
					// check if a middle piece is selected
					if(j % 2 != 0){
						//check if piece can change squares
						if((i % 2) == 0 ){
							// it's on the outer or the inner most square
							// so only need to check if it can go to the middle square
							if (board[1][j] == null){
								return true;
							}
						}
						else {
							// otherwise it's in the middle and have to check either side.
							if (board[0][j] == null || board[2][j] == null){
								return true;
							}
						}
					}
				}
			}
		}	
		return false;
	}

}
