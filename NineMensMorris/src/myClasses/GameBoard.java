package myClasses;


public class GameBoard {
	//Validation is not handled in this class
	
	
	
	//Board:
	//First dimension is square id, 0 being outermost to 2 being innermost
	//Second dimension is position on square, from 0 to 7 going clockwise from top-left
	//Position:
	//
	//
	private Piece[][] board;
	private Piece[][] side;

	public GameBoard(Player[] players) {
		for(int i=0; i<=1; i++){
			for(int j=0; j<=9; j++){
				this.side[i][j] = new Piece(players[i]);
			}
		}
	}

	/**
	 * 
	 * @param position
	 */
	public int addPiece(int playerID, int[] position) {
		//look for highest indexed piece on players side
		int index = piecesOnSide(playerID);
		//no pieces on side
		if(index == 0){
			
		}
		//add piece to board
		board[position[0]][position[1]] = side[playerID][index];
		//remove piece from side
		side[playerID][index] = null;
		
	}

	/**
	 * 
	 * @param position1
	 * @param position2
	 */
	public void movePiece(int[] position1, int[] postition2) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param position
	 */
	public void takePiece(int[] position) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Checks if a piece on the gameboard is apart of a mill
	 * 
	 * @param position
	 */
	public boolean isMill(int[] position) {
		
		
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 */
	public boolean isMill(Player player) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 */
	public int piecesOnSide(int playerID) {
		int i = 8;
		while(side[playerID][i] == null && i>=0){
			i--;
		}
		//change from index to count
		return i+1;
	}

}