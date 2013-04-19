package myClasses;


public class Options {

	private int flyRule;
	private boolean timer;
	private boolean gameRes;
	private boolean computerPlayer;
	private String[] playerNames ;
	
	public Options(){
		this.playerNames = new String[2];
	}

	/**
	 * 1 means 3 pieces left until flyRule starts
	 * 2 means 4 pieces left until flyRule starts
	 * 3 means flyRule off
	 * 
	 * @return flyRule
	 */
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
	 * @param index which player name (0 or 1)
	 * @return the playerNames
	 */
	public String getPlayerNames(int index) {
		return playerNames[index];
	}

	/**
	 * @param playerName the playerName to set
	 * @param index which player name to set (0 or 1)
	 */
	public void setPlayerNames(String playerName, int index) {
		this.playerNames[index] = playerName;
	}

	/**
	 * @param playerName the playerName to set
	 * @param index which player name to set
	 */
	public void setPlayerNames(String player1Name, String player2Name) {
		this.playerNames[0] = player1Name;
		this.playerNames[1] = player2Name;
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

	/**
	 * @return the gameRes
	 */
	public boolean getGameRes() {
		return gameRes;
	}

	/**
	 * @param gameRes the gameRes to set
	 */
	public void setGameRes(boolean gameRes) {
		this.gameRes = gameRes;
	}

}