package myClasses;


public class Options {

	private int flyRule;
	private boolean timer;
	private boolean computerPlayer;

	public int getFlyRule() {
		return this.flyRule;
	}

	/**
	 * 
	 * @param flyRule
	 */
	public void setFlyRule(int flyRule) {
		this.flyRule = flyRule;
	}

	public boolean getTimer() {
		return this.timer;
	}

	/**
	 * 
	 * @param timer
	 */
	public void setTimer(boolean timer) {
		this.timer = timer;
	}

	/**
	 * <b>Get Computer Player</b><p>
	 * This returns what type of game is selected. 
	 * @return 
	 * True = Computer vs Human;<p>
	 * False = Human vs Human
	 */
	public boolean getComputerPlayer() {
		return this.computerPlayer;
	}

	/**
	 * 
	 * @param PC
	 */
	public void setComputerPlayer(boolean PC) {
		this.computerPlayer = PC;
	}

}