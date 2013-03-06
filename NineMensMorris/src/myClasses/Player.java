package myClasses;

public class Player {

	private String name;
	private boolean isHuman;
	private int numMoves;

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsHuman() {
		return this.isHuman;
	}

	/**
	 * 
	 * @param isHuman
	 */
	public void setIsHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}

	public int getNumMoves() {
		return this.numMoves;
	}

	/**
	 * 
	 * @param numMoves
	 */
	public void setNumMoves(int numMoves) {
		this.numMoves = numMoves;
	}

}