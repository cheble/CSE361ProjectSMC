package myClasses;

public interface MenuInterface {

	/**
	 * Get's the options object from the menu.
	 * @return The options of the menu object.
	 */
	Options getOptions();

	/**
	 * Get's the status of if the game is ready yet.
	 * @return If the game is ready or not.
	 */
	boolean isGameReady();

	/**
	 * Set's the options to be managed by the menu.
	 * @param myOptions - Options to set.
	 */
	void setOptions(Options myOptions);

}