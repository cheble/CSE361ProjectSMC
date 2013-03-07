package myClasses;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	private static GameInstance game;
	private static JFrame contentPane;
	private static MenuInterface menu;
	private static Player winner;

	public static void main(String[] args) {
		// Instantiate new GUI
		// InterfaceTest gui = new InterfaceTest();

		// Initialize contentPane
		contentPane = new JFrame();
		contentPane.setVisible(true);
		contentPane.setSize(new Dimension(1120, 700));
		contentPane.setResizable(false);
		contentPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setLayout(null);

		// Forever until user quit on close
		while (true) {
			// Instantiate Menu Gui
			menu = new MenuGUI(contentPane);
			
			// Wait Until Game Ready
			while (true) {
				// Cause it doesn't work with out print statement? wtf...
				System.out.print("");
				if (menu.isGameReady() == true) {
					break;
				}
			}
			
			// Play Game
			playGame();
			
			
		}
	}

	public static void playGame() {
		// Initiate Game
		game = new GameInstance(menu.getOptions(), contentPane);

		// Wait until game done
		while (true) {
			// Cause it doesn't work with out print statement? wtf...
			System.out.print("");
			if (game.isGameOver() == true) {
				break;
			}
		}

		// Get Winner
		winner = game.getWinner();

		// Update Leaderboard
		updateLeaderboard(winner);

	}

	public static void updateLeaderboard(Player winner) {

	}
}
