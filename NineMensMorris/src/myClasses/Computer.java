package myClasses;


public class Computer extends Player {

	/**
	 * Creates placement move for the AI player
	 * @return Single dimension array representing a move.
	 */
	public int[] placePiece() {
		int[] temp = new int[2];
		temp[0] = (int) ((Math.random() * 99.0) % 3);
		temp[1] = (int) ((Math.random() * 99.0) % 8);
		return temp;
	}

	/** 
	 * Creates a move for the computer player to take.
	 * @return A double array int that has the location 
	 * of the piece selected in the first array and the 
	 * position to move to in the second.
	 */
	public int[][] movePiece() {
		int[][] temp = new int[2][2];
		temp[0][0] = (int) ((Math.random() * 99.0) % 3);
		temp[0][1] = (int) ((Math.random() * 99.0) % 8);
		temp[1][0] = (int) ((Math.random() * 99.0) % 3);
		temp[1][1] = (int) ((Math.random() * 99.0) % 8);
		return temp;
	}

	/**
	 * Chooses an opponent's piece to take.
	 * @return A location for a piece to choose.
	 */
	public int[] takePiece() {
		int[] temp = new int[2];
		temp[0] = (int) ((Math.random() * 99.0) % 3);
		temp[1] = (int) ((Math.random() * 99.0) % 8);
		return temp;
	}

}