package myClasses;

public class Piece {

	public Piece(Player owner) {
		super();
		Owner = owner;
	}

	private Player Owner;

	public Player getOwner() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Owner
	 */
	public void setOwner(Player Owner) {
		throw new UnsupportedOperationException();
	}

}