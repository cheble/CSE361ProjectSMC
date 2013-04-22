package myClasses;

/**
 *Class meant to be used when testing the menu 
 *section of the game without a gui.
 *@deprecated
 */
public class MenuTest implements MenuInterface {
	
	/* (non-Javadoc)
	 * @see myClasses.MenuInterface#getOptions()
	 */
	public Options getOptions() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see myClasses.MenuInterface#isGameReady()
	 */
	public boolean isGameReady() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see myClasses.MenuInterface#setOptions(myClasses.Options)
	 */
	public void setOptions(Options myOptions) {
		
	}

}