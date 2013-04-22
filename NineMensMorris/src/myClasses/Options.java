package myClasses;


public class Options {

	private int flyRule;
	private boolean timer;
	private boolean gameRes;
	private boolean computerPlayer;
	private String[] playerNames ;
	
	/**
	 * Set's up the options with the default values.
	 */
	public Options(){
		this.playerNames = new String[2];
	}

	/**
	 * Tells when fly mode is to be enabled.
	 * 1 means 3 pieces left when flyRule starts
	 * 2 means 4 pieces left when flyRule starts
	 * 3 means flyRule off
	 * 
	 * @return <b>flyRule</b> <ul>
	 * <li>1 means 3 pieces left until flyRule starts</li>
	 * <li>2 means 4 pieces left until flyRule starts</li>
	 * <li>3 means flyRule off</li></ul>
	 */
	public int getFlyRule() {
		return this.flyRule;
	}

	/**
	 * Set's which setting flyRule is.
	 * @param flyRule <ul>
	 * <li>1 means 3 pieces left until flyRule starts</li>
	 * <li>2 means 4 pieces left until flyRule starts</li>
	 * <li>3 means flyRule off</li></ul>
	 */
	public void setFlyRule(int flyRule) {
		this.flyRule = flyRule;
	}

	/**
	 * Returns the Timer's current setting.
	 * @return The current setting of the Timer. 
	 */
	public boolean getTimer() {
		return this.timer;
	}

	/**
	 * Set's the Timer
	 * @param timer Boolean True:On, False:OFF
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