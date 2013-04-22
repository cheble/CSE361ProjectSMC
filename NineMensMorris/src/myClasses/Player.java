package myClasses;

public class Player {

	private String name;
	private boolean isHuman;
	private int numMoves;

	public String getName() {
		return this.name;
	}

	/**
	 * Set's the name of the player.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Is the player a human?
	 * @return True: is a human<br>False: is not a human
	 */
	public boolean getIsHuman() {
		return this.isHuman;
	}

	/**
	 * Set's whether or not the player is human
	 * @param isHuman: true is human, false is not human
	 */
	public void setIsHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}

	/**
	 * Get's the number of moves that a player has taken so far.
	 * @return The number of rounds a player has gone.
	 */
	public int getNumMoves() {
		return this.numMoves;
	}

	/**
	 * Edit's the number of moves taken
	 * @param numMoves
	 */
	public void setNumMoves(int numMoves) {
		this.numMoves = numMoves;
	}
	
	
	/**
	 * A method to increment the number of moves a player has taken by one.
	 * 
	 */
	public void incrementNumMoves(){
		numMoves++;
	}
	
	/**
	 * A method to decrement the number of moves a player has taken by one.
	 * 
	 */
	public void decrementNumMoves(){
		numMoves--;
	}
}