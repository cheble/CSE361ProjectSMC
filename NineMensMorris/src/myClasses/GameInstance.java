public class GameInstance {

	private GameInterface myInterface;
	private Player[] players;
	private Options myOptions;
	private Gameboard myBoard;

	public int chooseStartingPlayer() {
		throw new UnsupportedOperationException();
	}

	public void playerTurnPlace() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 */
	public void playerTurnMove(Player player) {
		throw new UnsupportedOperationException();
	}

	public boolean isTurnValid() {
		throw new UnsupportedOperationException();
	}

	public boolean isGameOver() {
		throw new UnsupportedOperationException();
	}

	public Player getWinner() {
		throw new UnsupportedOperationException();
	}

	public void Undo() {
		throw new UnsupportedOperationException();
	}

	public void Skip() {
		throw new UnsupportedOperationException();
	}

}