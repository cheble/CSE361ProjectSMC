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