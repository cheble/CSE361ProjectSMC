package myClasses;

public class Piece {

	public Piece(Player player) {
		player = owner;
	}

	private Player owner;

	public Player getOwner() {
		return owner;
	}

	/**
	 * 
	 * @param Owner
	 */
	public void setOwner(Player player) {
		owner = player;
	}

}