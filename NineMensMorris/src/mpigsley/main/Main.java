package mpigsley.main;

import java.awt.Dimension;

import javax.swing.JFrame;

import mpigsley.gui.InterfaceTest;
import myClasses.GameInstance;
import myClasses.MenuInterface;
import myClasses.MenuGUI;

public class Main {
	private static GameInstance game;
	private static JFrame contentPane;
	private static MenuInterface menu;
	
	public static void main(String[] args) {
		// Instantiate new GUI
		//InterfaceTest gui = new InterfaceTest();
		
		// Initialize contentPane
		contentPane = new JFrame();
		contentPane.setVisible(true);
		contentPane.setSize(new Dimension(1120, 700));
		contentPane.setResizable(false);
		contentPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setLayout(null);
		
		// Instantiate Menu Gui
		MenuGUI menu = new MenuGUI(contentPane);
		
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
