package myClasses;

public class Piece {

	private Player owner;
	
	public Piece(Player player) {
		owner = player;
	}

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