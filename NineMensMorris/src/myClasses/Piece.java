package myClasses;

public class Piece {

	private Player owner;
	
	/**
	 * Creates a piece belonging to a player.
	 * @param player - Player the piece belongs to.
	 */
	public Piece(Player player) {
		owner = player;
	}

	/**
	 * @return The owner of a piece.
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Set's the owner of a piece.
	 * @param player - the player a piece belongs to.
	 */
	public void setOwner(Player player) {
		owner = player;
	}

}