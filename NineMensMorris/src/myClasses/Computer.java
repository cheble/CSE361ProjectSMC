package myClasses;


public class Computer extends Player {

	/**
	 * creates placement move
	 */
	public int[] placePiece() {
		int[] temp = new int[2];
		temp[0] = (int) ((Math.random() * 99.0) % 3);
		temp[1] = (int) ((Math.random() * 99.0) % 8);
		return temp;
	}

	public int[][] movePiece() {
		int[][] temp = new int[2][2];
		temp[0][0] = (int) ((Math.random() * 99.0) % 3);
		temp[0][1] = (int) ((Math.random() * 99.0) % 8);
		temp[1][0] = (int) ((Math.random() * 99.0) % 3);
		temp[1][1] = (int) ((Math.random() * 99.0) % 8);
		return temp;
	}

	public int[] takePiece() {
		int[] temp = new int[2];
		temp[0] = (int) ((Math.random() * 99.0) % 3);
		temp[1] = (int) ((Math.random() * 99.0) % 8);
		return temp;
	}

}