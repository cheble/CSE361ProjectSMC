package myClasses;

import javax.swing.JPanel;

import mpigsley.gui.InterfaceTest;

public class Main {

	private static GameInstance game;
	private JPanel contentPane;
	private static MenuInterface menu;
	
	public static void main(String[] args) {
		// Instantiate new Menu GUI
		menu = new MenuGUI();
		while(!menu.isGameReady()){
		}
		game = new GameInstance(menu.getOptions());
	}

	public void playGame() {
		throw new UnsupportedOperationException();
	}

	public void placementPhase() {
		throw new UnsupportedOperationException();
	}

	public void movementPhase() {
		throw new UnsupportedOperationException();
	}

	public void exitProgram() {
		throw new UnsupportedOperationException();
	}

	public void updateLeaderboard() {
		throw new UnsupportedOperationException();
	}

}