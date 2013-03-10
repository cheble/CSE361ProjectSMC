package myClasses;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import mpigsley.gui.JPanelWithBackground;

public class GameGUI implements GameInterface {

	// Variables
	private JFrame contentPane;
	private int slideNum;
	private boolean isGameQuit;
	private String names[];
	private boolean isPieceSelected;
	private boolean isPositionSelected;
	private int[] pieceSelectedPos;
	private int[] selectedPos;
	private Player[] players;

	// Side Images
	private JPanel[] bSide;
	private JPanel[] rSide;

	// Button Game Board
	private JButton[][] board;

	// How To Slides
	private JPanel[] slides;

	// Image Locations, etc.
	// private String backL = "src/images/backgroundLarge.jpg";
	private String backS = "src/images/backgroundSmall.jpg";
	// private String boardL = "src/images/boardLarge.jpg";
	private String boardS = "src/images/boardSmall.jpg";
	private String blue = "src/images/blue.png";
	private String red = "src/images/red.png";
	private String blank = "src/images/blankPiece.png";
	private ImageIcon bluePiece = new ImageIcon(blue);
	private ImageIcon redPiece = new ImageIcon(red);
	private ImageIcon blankPiece = new ImageIcon(blank);

	public GameGUI(JFrame contentPane, int numHumans, Player players[]) {
		// Initiate Variables
		this.contentPane = contentPane;
		isGameQuit = false;
		slideNum = 1;
		names = new String[numHumans];
		isPieceSelected = false;
		isPositionSelected = false;
		pieceSelectedPos = new int[2];
		selectedPos = new int[2];
		bSide = new JPanel[9];
		rSide = new JPanel[9];
		board = new JButton[3][8];
		slides = new JPanel[4];
		this.players = players;

		// Ask for Player Names
		playerNames(numHumans);

	}

	private void playerNames(final int numHumans) {
		// Initialize LayeredPane
		final JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, contentPane.getWidth(),
				contentPane.getHeight());
		layeredPane.setLayout(null);
		contentPane.add(layeredPane);

		// Create Background JPanel & Add to LayeredPane on Layer 1
		JPanel background;
		try {
			background = new JPanelWithBackground(backS);
		} catch (IOException e) {
			e.printStackTrace();
			background = null;
		}
		background.setBounds(0, 0, contentPane.getWidth(),
				contentPane.getHeight());
		background.setVisible(true);
		layeredPane.add(background);
		layeredPane.setLayer(background, 1);

		// Create info JPanel & Add to LayerdPane on Layer 2
		JPanel info = new JPanel();
		info.setOpaque(false);
		info.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
		info.setVisible(true);
		info.setLayout(null);
		layeredPane.add(info);
		layeredPane.setLayer(info, 2);

		JLabel titleOne = new JLabel("<html><center>Player 1<br>Name</center>");
		titleOne.setHorizontalAlignment(SwingConstants.CENTER);
		titleOne.setVisible(true);
		titleOne.setBounds(0, 100, info.getWidth() / numHumans, 200);
		titleOne.setFont(new Font("Coalition", Font.PLAIN, 50));
		titleOne.setForeground(Color.white);
		info.add(titleOne);

		final JTextField nameOne = new JTextField();
		nameOne.setBounds(100, titleOne.getY() + 250,
				(info.getWidth() / numHumans) - 200, 50);
		nameOne.setFont(new Font("Coalition", Font.PLAIN, 30));
		nameOne.setVisible(true);
		nameOne.setHorizontalAlignment(SwingConstants.CENTER);
		info.add(nameOne);

		final JTextField nameTwo;
		if (numHumans == 2) {
			JLabel titleTwo = new JLabel(
					"<html><center>Player 2<br>Name</center>");
			titleTwo.setHorizontalAlignment(SwingConstants.CENTER);
			titleTwo.setVisible(true);
			titleTwo.setBounds((info.getWidth() / 2), 100,
					(info.getWidth() / numHumans), 200);
			titleTwo.setFont(new Font("Coalition", Font.PLAIN, 50));
			titleTwo.setForeground(Color.white);
			info.add(titleTwo);

			nameTwo = new JTextField();
			nameTwo.setBounds((info.getWidth() / 2) + 100,
					titleTwo.getY() + 250, (info.getWidth() / numHumans) - 200,
					50);
			nameTwo.setFont(new Font("Coalition", Font.PLAIN, 30));
			nameTwo.setVisible(true);
			nameTwo.setHorizontalAlignment(SwingConstants.CENTER);
			info.add(nameTwo);
		} else {
			nameTwo = null;
		}

		final JButton ready = new JButton("Ready");
		ready.setContentAreaFilled(false);
		ready.setBorderPainted(false);
		ready.setVisible(true);
		ready.setBounds(0, info.getHeight() - 150, info.getWidth(), 50);
		ready.setFont(new Font("Coalition", Font.PLAIN, 40));
		ready.setForeground(Color.white);
		ready.setOpaque(false);
		info.add(ready);
		ready.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ready.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				ready.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (numHumans == 1) {
					if (nameOne.getText().length() != 0) {
						// Set Name
						names[0] = nameOne.getText();

						// Remove Layered Pane
						contentPane.remove(layeredPane);

						// Draw Game With Updated Name
						drawBoard();
					}
				} else {
					if (nameOne.getText().length() != 0
							&& nameTwo.getText().length() != 0) {
						// Set Name
						names[0] = nameOne.getText();
						names[1] = nameTwo.getText();

						// Remove Layered Pane
						contentPane.remove(layeredPane);

						// Draw Game With Updated Names
						drawBoard();
					}
				}
			}
		});

		info.repaint();

	}

	public void drawBoard() {
		// Initialize LayeredPane
		final JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, contentPane.getWidth(),
				contentPane.getHeight());
		layeredPane.setLayout(null);
		contentPane.add(layeredPane);

		// Create Background JPanel & Add to LayeredPane on Layer 1
		JPanel background;
		try {
			background = new JPanelWithBackground(boardS);
		} catch (IOException e) {
			e.printStackTrace();
			background = null;
		}
		background.setBounds(0, 0, contentPane.getWidth(),
				contentPane.getHeight());
		background.setVisible(true);
		layeredPane.add(background);
		layeredPane.setLayer(background, 1);

		// Create Buttons JPanel & Add to LayerdPane on Layer 2
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		buttons.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
		buttons.setVisible(true);
		buttons.setLayout(null);
		layeredPane.add(buttons);
		layeredPane.setLayer(buttons, 2);

		// Create Menu JPanel & Add to LayeredPane on Layer 3
		final JPanel menu = new JPanel();
		menu.setOpaque(false);
		menu.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
		menu.setVisible(true);
		menu.setLayout(null);
		layeredPane.add(menu);
		layeredPane.setLayer(menu, 3);

		// Add undo button to JPanel
		final JButton undo = new JButton("UNDO");
		undo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				undo.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				undo.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// DO SOMETHING

			}
		});
		undo.setFont(new Font("Coalition", Font.PLAIN, 40));
		undo.setForeground(Color.WHITE);
		undo.setBounds(20, contentPane.getHeight() - 100, 200, 65);
		undo.setOpaque(false);
		undo.setContentAreaFilled(false);
		undo.setBorderPainted(false);
		buttons.add(undo);

		// Add skip button to JPanel
		final JButton skip = new JButton("SKIP");
		skip.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				skip.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				skip.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// DO SOMETHING

			}
		});
		skip.setFont(new Font("Coalition", Font.PLAIN, 40));
		skip.setForeground(Color.WHITE);
		skip.setBounds((contentPane.getWidth() / 2) - 100,
				contentPane.getHeight() - 100, 200, 65);
		skip.setOpaque(false);
		skip.setContentAreaFilled(false);
		skip.setBorderPainted(false);
		buttons.add(skip);

		// Add menu button to JPanel
		final JButton gameMenu = new JButton("MENU");
		gameMenu.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {
				gameMenu.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				gameMenu.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Draw Menu
				drawGameMenu(menu);

				// Repaint
				contentPane.repaint();
			}
		});
		gameMenu.setFont(new Font("Coalition", Font.PLAIN, 40));
		gameMenu.setForeground(Color.WHITE);
		gameMenu.setBounds(contentPane.getWidth() - 220,
				contentPane.getHeight() - 100, 200, 65);
		gameMenu.setOpaque(false);
		gameMenu.setContentAreaFilled(false);
		gameMenu.setBorderPainted(false);
		buttons.add(gameMenu);

		// Add Player One Name JLabel and add to JPanel
		JLabel pOne = new JLabel(names[0].toUpperCase());
		pOne.setForeground(Color.LIGHT_GRAY);
		pOne.setHorizontalAlignment(SwingConstants.CENTER);
		pOne.setFont(new Font("Coalition", Font.PLAIN, 25));
		pOne.setBounds(39, 233, 218, 51);
		buttons.add(pOne);

		// Add Player Two Name JLabel and add to JPanel
		JLabel pTwo = new JLabel();
		if (names.length == 2) {
			pTwo.setText(names[1].toUpperCase());
		} else {
			pTwo.setText("COMPUTER");
		}
		pTwo.setForeground(Color.LIGHT_GRAY);
		pTwo.setHorizontalAlignment(SwingConstants.CENTER);
		pTwo.setFont(new Font("Coalition", Font.PLAIN, 25));
		pTwo.setBounds(871, 233, 218, 51);
		buttons.add(pTwo);

		// Create Pieces JPanel & Add to LayerdPane on Layer 3
		JPanel pieces = new JPanel();
		pieces.setOpaque(false);
		pieces.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
		pieces.setVisible(true);
		pieces.setLayout(null);
		layeredPane.add(pieces);
		layeredPane.setLayer(pieces, 3);

		// Create Blue Side Pieces
		for (int i = 0; i < 9; i++) {
			try {
				bSide[i] = new JPanelWithBackground(blue);
			} catch (IOException e) {
				e.printStackTrace();
				bSide[i] = null;
			}
			bSide[i].setVisible(true);
			bSide[i].setOpaque(false);
			pieces.add(bSide[i]);
		}

		// Set Individual bounds for each JPanel
		bSide[0].setBounds(50, 350, 50, 50);
		bSide[1].setBounds(bSide[0].getX() + 50, bSide[0].getY(), 50, 50);
		bSide[2].setBounds(bSide[1].getX() + 50, bSide[1].getY(), 50, 50);
		bSide[3].setBounds(bSide[2].getX() + 50, bSide[2].getY(), 50, 50);
		bSide[4].setBounds(bSide[0].getX() - (bSide[0].getWidth() / 2),
				bSide[3].getY() + 50, 50, 50);
		bSide[5].setBounds(bSide[4].getX() + 50, bSide[4].getY(), 50, 50);
		bSide[6].setBounds(bSide[5].getX() + 50, bSide[5].getY(), 50, 50);
		bSide[7].setBounds(bSide[6].getX() + 50, bSide[6].getY(), 50, 50);
		bSide[8].setBounds(bSide[7].getX() + 50, bSide[7].getY(), 50, 50);

		// Create Blue Side Pieces
		for (int i = 0; i < 9; i++) {
			try {
				rSide[i] = new JPanelWithBackground(red);
			} catch (IOException e) {
				e.printStackTrace();
				rSide[i] = null;
			}
			rSide[i].setVisible(true);
			rSide[i].setOpaque(false);
			pieces.add(rSide[i]);
		}

		// Set Individual bounds for each JPanel
		rSide[0].setBounds(contentPane.getWidth() - 250, bSide[0].getY(), 50,
				50);
		rSide[1].setBounds(rSide[0].getX() + 50, rSide[0].getY(), 50, 50);
		rSide[2].setBounds(rSide[1].getX() + 50, rSide[1].getY(), 50, 50);
		rSide[3].setBounds(rSide[2].getX() + 50, rSide[2].getY(), 50, 50);
		rSide[4].setBounds(rSide[0].getX() - (rSide[0].getWidth() / 2),
				rSide[3].getY() + 50, 50, 50);
		rSide[5].setBounds(rSide[4].getX() + 50, rSide[4].getY(), 50, 50);
		rSide[6].setBounds(rSide[5].getX() + 50, rSide[5].getY(), 50, 50);
		rSide[7].setBounds(rSide[6].getX() + 50, rSide[6].getY(), 50, 50);
		rSide[8].setBounds(rSide[7].getX() + 50, rSide[7].getY(), 50, 50);

		// Initialize all of the Board Buttons & set to a Blank Piece
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new JButton();
				board[i][j].setVisible(true);
				board[i][j].setOpaque(false);
				board[i][j].setContentAreaFilled(false);
				board[i][j].setBorderPainted(false);
				board[i][j].setIcon(blankPiece);
				pieces.add(board[i][j]);
			}
		}

		// Set Individual Mouse Listeners & Locations
		board[0][0].setBounds(270, 12, 50, 50);
		board[0][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[0][0].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 0;
				} else if (!isPositionSelected
						&& board[0][0].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 0;
				} else {
					// Do Nothing
				}
			}
		});

		board[0][1].setBounds(board[0][0].getX() + 264, board[0][0].getY(), 50,
				50);
		board[0][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[0][1].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 1;
				} else if (!isPositionSelected
						&& board[0][1].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 1;
				} else {
					// Do Nothing
				}
			}
		});

		board[0][2].setBounds(board[0][1].getX() + 264, board[0][0].getY(), 50,
				50);
		board[0][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[0][2].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 2;
				} else if (!isPositionSelected
						&& board[0][2].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 2;
				} else {
					// Do Nothing
				}
			}
		});

		board[1][0].setBounds(board[0][0].getX() + 70, board[0][0].getY() + 70,
				50, 50);
		board[1][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[1][0].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 0;
				} else if (!isPositionSelected
						&& board[1][0].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 0;
				} else {
					// Do Nothing
				}
			}
		});

		board[1][1].setBounds(board[0][1].getX(), board[1][0].getY(), 50, 50);
		board[1][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[1][1].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 1;
				} else if (!isPositionSelected
						&& board[1][1].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 1;
				} else {
					// Do Nothing
				}
			}
		});

		board[1][2].setBounds(board[0][2].getX() - 70, board[1][0].getY(), 50,
				50);
		board[1][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[1][2].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 2;
				} else if (!isPositionSelected
						&& board[1][2].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 2;
				} else {
					// Do Nothing
				}
			}
		});

		board[2][0].setBounds(board[1][0].getX() + 70, board[1][0].getY() + 70,
				50, 50);
		board[2][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[2][0].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 0;
				} else if (!isPositionSelected
						&& board[2][0].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 0;
				} else {
					// Do Nothing
				}
			}
		});

		board[2][1].setBounds(board[1][1].getX(), board[2][0].getY(), 50, 50);
		board[2][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[2][1].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 1;
				} else if (!isPositionSelected
						&& board[2][1].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 1;
				} else {
					// Do Nothing
				}
			}
		});

		board[2][2].setBounds(board[1][2].getX() - 70, board[2][1].getY(), 50,
				50);
		board[2][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[2][2].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 2;
				} else if (!isPositionSelected
						&& board[2][2].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 2;
				} else {
					// Do Nothing
				}
			}
		});

		board[0][7].setBounds(board[0][0].getX(), board[0][0].getY() + 265, 50,
				50);
		board[0][7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[0][7].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 7;
				} else if (!isPositionSelected
						&& board[0][7].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 7;
				} else {
					// Do Nothing
				}
			}
		});

		board[1][7].setBounds(board[1][0].getX(), board[0][7].getY(), 50, 50);
		board[1][7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[1][7].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 7;
				} else if (!isPositionSelected
						&& board[1][7].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 7;
				} else {
					// Do Nothing
				}
			}
		});

		board[2][7].setBounds(board[2][0].getX(), board[1][7].getY(), 50, 50);
		board[2][7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[2][7].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 7;
				} else if (!isPositionSelected
						&& board[2][7].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 7;
				} else {
					// Do Nothing
				}
			}
		});

		board[2][3].setBounds(board[2][2].getX(), board[2][7].getY(), 50, 50);
		board[2][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[2][3].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 3;
				} else if (!isPositionSelected
						&& board[2][3].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 3;
				} else {
					// Do Nothing
				}
			}
		});

		board[1][3].setBounds(board[1][2].getX(), board[2][3].getY(), 50, 50);
		board[1][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[1][3].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 3;
				} else if (!isPositionSelected
						&& board[1][3].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 3;
				} else {
					// Do Nothing
				}
			}
		});

		board[0][3].setBounds(board[0][2].getX(), board[1][3].getY(), 50, 50);
		board[0][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[0][3].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 3;
				} else if (!isPositionSelected
						&& board[0][3].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 3;
				} else {
					// Do Nothing
				}
			}
		});

		board[2][6].setBounds(board[2][7].getX(), board[2][7].getY()
				+ (board[2][7].getY() - board[2][0].getY()), 50, 50);
		board[2][6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[2][6].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 6;
				} else if (!isPositionSelected
						&& board[2][6].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 6;
				} else {
					// Do Nothing
				}
			}
		});

		board[2][5].setBounds(board[2][1].getX(), board[2][6].getY(), 50, 50);
		board[2][5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[2][5].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 5;
				} else if (!isPositionSelected
						&& board[2][5].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 5;
				} else {
					// Do Nothing
				}
			}
		});

		board[2][4].setBounds(board[2][3].getX(), board[2][5].getY(), 50, 50);
		board[2][4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[2][4].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 4;
				} else if (!isPositionSelected
						&& board[2][4].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 4;
				} else {
					// Do Nothing
				}
			}
		});

		board[1][6].setBounds(board[1][7].getX(), board[2][6].getY() + 70, 50,
				50);
		board[1][6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[1][6].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 6;
				} else if (!isPositionSelected
						&& board[1][6].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 6;
				} else {
					// Do Nothing
				}
			}
		});

		board[1][5].setBounds(board[2][5].getX(), board[1][6].getY(), 50, 50);
		board[1][5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[1][5].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 5;
				} else if (!isPositionSelected
						&& board[1][5].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 5;
				} else {
					// Do Nothing
				}
			}
		});

		board[1][4].setBounds(board[1][3].getX(), board[1][5].getY(), 50, 50);
		board[1][4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[1][4].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 4;
				} else if (!isPositionSelected
						&& board[1][4].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 4;
				} else {
					// Do Nothing
				}
			}
		});

		board[0][6].setBounds(board[0][7].getX(), board[1][6].getY() + 70, 50,
				50);
		board[0][6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[0][6].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 6;
				} else if (!isPositionSelected
						&& board[0][6].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 6;
				} else {
					// Do Nothing
				}
			}
		});

		board[0][5].setBounds(board[1][5].getX(), board[0][6].getY(), 50, 50);
		board[0][5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[0][5].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 5;
				} else if (!isPositionSelected
						&& board[0][5].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 5;
				} else {
					// Do Nothing
				}
			}
		});

		board[0][4].setBounds(board[0][3].getX(), board[0][5].getY(), 50, 50);
		board[0][4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected
						|| board[pieceSelectedPos[0]][pieceSelectedPos[1]]
								.getIcon().equals(board[0][4].getIcon())) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 4;
				} else if (!isPositionSelected
						&& board[0][4].getIcon().equals(blankPiece)) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 4;
				} else {
					// Do Nothing
				}
			}
		});

	}

	public void drawHowTo(final JPanel panel) {
		// Reset to Slide 1
		slideNum = 1;
		
		// Initialize LayeredPane
		final JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, contentPane.getWidth(),
				contentPane.getHeight());
		layeredPane.setLayout(null);
		panel.setOpaque(true);
		panel.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
		panel.setLayout(null);
		panel.setVisible(true);
		panel.add(layeredPane);

		// Create Background JPanel & Add to LayeredPane on Layer 1
		JPanel background;
		try {
			background = new JPanelWithBackground(backS);
		} catch (IOException e) {
			e.printStackTrace();
			background = null;
		}
		background.setBounds(0, 0, contentPane.getWidth(),
				contentPane.getHeight());
		background.setVisible(true);
		layeredPane.add(background);
		layeredPane.setLayer(background, 1);

		// Create Buttons JPanel and Add to LayeredPane on Layer 2
		JPanel buttons = new JPanel();
		buttons.setLayout(null);
		buttons.setOpaque(false);
		buttons.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
		buttons.setVisible(true);
		layeredPane.add(buttons);
		layeredPane.setLayer(buttons, 2);

		// Create Title and add to Layer
		JLabel title = new JLabel("HOW TO PLAY");
		title.setForeground(Color.LIGHT_GRAY);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Coalition", Font.BOLD, 70));
		title.setBounds(0, 60, contentPane.getWidth(), 125);
		buttons.add(title);

		// Add previous button to JPanel
		final JButton previous = new JButton("PREVIOUS");
		previous.setFont(new Font("Coalition", Font.PLAIN, 40));
		previous.setForeground(Color.WHITE);
		previous.setBounds(20, contentPane.getHeight() - 100, 325, 65);
		previous.setOpaque(false);
		previous.setContentAreaFilled(false);
		previous.setBorderPainted(false);
		buttons.add(previous);

		// Add back button to JPanel
		final JButton back = new JButton("BACK");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				back.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				back.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Delete Layer
				panel.setVisible(false);
			}
		});
		back.setFont(new Font("Coalition", Font.PLAIN, 40));
		back.setForeground(Color.WHITE);
		back.setBounds((contentPane.getWidth() / 2) - 100,
				contentPane.getHeight() - 100, 200, 65);
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		buttons.add(back);

		// Add next button to JPanel
		final JButton next = new JButton("NEXT");
		next.setFont(new Font("Coalition", Font.PLAIN, 40));
		next.setForeground(Color.WHITE);
		next.setBounds(contentPane.getWidth() - 220,
				contentPane.getHeight() - 100, 200, 65);
		next.setContentAreaFilled(false);
		next.setBorderPainted(false);
		buttons.add(next);

		// Create cardPanel and Add to Layered Pane on Layer 3
		final JPanel cardPanel = new JPanel();
		cardPanel.setVisible(true);
		cardPanel.setOpaque(false);
		cardPanel.setBounds(210, 200, 700, 375);
		final CardLayout cl = new CardLayout(10, 10);
		cardPanel.setLayout(cl);
		layeredPane.add(cardPanel);
		layeredPane.setLayer(cardPanel, 3);

		for (int i = 0; i < slides.length; i++) {
			slides[i] = new JPanel();
			slides[i].setVisible(true);
			slides[i].setOpaque(false);
			slides[i].setBounds(25, 25, cardPanel.getWidth() - 50,
					cardPanel.getHeight() - 50);
			cardPanel.add(slides[i], ""+ (i+1));
		}
		
		JLabel basicsText = new JLabel("Basics:\n");
		basicsText.setHorizontalAlignment(SwingConstants.LEFT);
		basicsText.setFont(new Font("Coalition", Font.PLAIN, 30));
		basicsText.setBounds(0, 0, slides[0].getWidth(), slides[0].getHeight());
		basicsText.setForeground(Color.WHITE);
		slides[0].add(basicsText);

		JLabel placeText = new JLabel("Placement Mode:\n");
		placeText.setHorizontalAlignment(SwingConstants.LEFT);
		placeText.setBounds(0, 0, slides[0].getWidth(), slides[0].getHeight());
		placeText.setFont(new Font("Coalition", Font.PLAIN, 30));
		placeText.setForeground(Color.WHITE);
		slides[1].add(placeText);

		JLabel moveText = new JLabel("Movement Mode:\n");
		moveText.setHorizontalAlignment(SwingConstants.LEFT);
		moveText.setBounds(0, 0, slides[0].getWidth(), slides[0].getHeight());
		moveText.setFont(new Font("Coalition", Font.PLAIN, 30));
		moveText.setForeground(Color.WHITE);
		slides[2].add(moveText);

		JLabel flyText = new JLabel("Fly Mode:\n");
		flyText.setHorizontalAlignment(SwingConstants.LEFT);
		flyText.setBounds(0, 0, slides[0].getWidth(), slides[0].getHeight());
		flyText.setFont(new Font("Coalition", Font.PLAIN, 30));
		flyText.setForeground(Color.WHITE);
		slides[3].add(flyText);

		// Show first slide on start
		cl.show(cardPanel, "" + slideNum);

		// Add Next and Previous Button Listeners
		next.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				next.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				next.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (slideNum < 4) {
					slideNum++;
					cl.show(cardPanel, "" + slideNum);
				}
			}
		});
		previous.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				previous.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				previous.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (slideNum > 1) {
					slideNum--;
					cl.show(cardPanel, "" + slideNum);
				}
			}
		});
	}

	public void drawGameMenu(final JPanel panel) {

		// Initialize LayeredPane
		final JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, contentPane.getWidth(),
				contentPane.getHeight());
		layeredPane.setLayout(null);
		layeredPane.setOpaque(false);
		layeredPane.setVisible(true);
		panel.setOpaque(false);
		panel.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
		panel.setLayout(null);
		panel.setVisible(true);
		panel.add(layeredPane);

		// Create Dim Panel and add as layer 1
		final JPanel dim = new JPanel();
		dim.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
		dim.setVisible(true);
		dim.setOpaque(false);
		dim.setLayout(null);
		layeredPane.add(dim);
		layeredPane.setLayer(dim, 1);

		// Create Menu with and add as layer 2
		final JPanel menu = new JPanel();
		menu.setBounds(((contentPane.getWidth() - 550) / 2),
				((contentPane.getHeight() - 600) / 2) - 10, 550, 600);
		menu.setVisible(true);
		menu.setBackground(Color.LIGHT_GRAY);
		menu.setBorder(new LineBorder(Color.BLACK, 5));
		menu.setLayout(null);
		layeredPane.add(menu);
		layeredPane.setLayer(menu, 2);

		// Create howToPanel Panel and add as layer 3
		final JPanel howToPanel = new JPanel();
		howToPanel.setOpaque(false);
		howToPanel.setVisible(true);
		layeredPane.add(howToPanel);
		layeredPane.setLayer(howToPanel, 3);

		// Create Menu Title and add to Menu
		JLabel menuTitle = new JLabel("Menu");
		menuTitle.setForeground(Color.BLACK);
		menuTitle.setHorizontalAlignment(SwingConstants.CENTER);
		menuTitle.setFont(new Font("Coalition", Font.BOLD, 70));
		menuTitle.setBounds(0, 75, menu.getWidth(), 75);
		menu.add(menuTitle);

		// How To Play Button add to Menu
		final JButton howTo = new JButton("How To Play");
		howTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				howTo.setForeground(Color.DARK_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				howTo.setForeground(Color.BLACK);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Create HowTo
				slideNum = 1;
				drawHowTo(howToPanel);

			}
		});
		howTo.setFont(new Font("Coalition", Font.PLAIN, 40));
		howTo.setForeground(Color.BLACK);
		howTo.setBounds(0, menuTitle.getY() + 150, menu.getWidth(), 65);
		howTo.setOpaque(false);
		howTo.setContentAreaFilled(false);
		howTo.setBorderPainted(false);
		menu.add(howTo);

		// Restart Button add to Menu
		final JButton restart = new JButton("Restart");
		restart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				restart.setForeground(Color.DARK_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				restart.setForeground(Color.BLACK);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// DO SOMETHING
			}
		});
		restart.setFont(new Font("Coalition", Font.PLAIN, 40));
		restart.setForeground(Color.BLACK);
		restart.setBounds(0, howTo.getY() + 80, menu.getWidth(), 65);
		restart.setOpaque(false);
		restart.setContentAreaFilled(false);
		restart.setBorderPainted(false);
		menu.add(restart);

		// Main Menu Button add to Menu
		final JButton main = new JButton("Main Menu");
		main.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {
				main.setForeground(Color.DARK_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				main.setForeground(Color.BLACK);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Set Game Over
				isGameQuit = true;

				// Delete Layer
				panel.setVisible(false);
			}
		});
		main.setFont(new Font("Coalition", Font.PLAIN, 40));
		main.setForeground(Color.BLACK);
		main.setBounds(0, restart.getY() + 80, menu.getWidth(), 65);
		main.setOpaque(false);
		main.setContentAreaFilled(false);
		main.setBorderPainted(false);
		menu.add(main);

		// Close Button add to Menu
		final JButton close = new JButton("Close");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				close.setForeground(Color.DARK_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				close.setForeground(Color.BLACK);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Delete Layer
				panel.setVisible(false);
			}
		});
		close.setFont(new Font("Coalition", Font.PLAIN, 40));
		close.setForeground(Color.BLACK);
		close.setBounds(0, main.getY() + 80, menu.getWidth(), 65);
		close.setOpaque(false);
		close.setContentAreaFilled(false);
		close.setBorderPainted(false);
		menu.add(close);

	}

	public void drawWinnerMenu(final JPanel panel) {
		throw new UnsupportedOperationException();
	}

	public int[] pieceSelect() {
		return pieceSelectedPos;
	}

	public int[] positionSelect() {
		return selectedPos;
	}

	public boolean isGameQuit() {
		return isGameQuit;
	}

	@Override
	public void setBoard(GameBoard gm) {
		// Clear The selected Positions on setBoard
		for (int i = 0; i < pieceSelectedPos.length; i++) {
			pieceSelectedPos[i] = (Integer) null;
			selectedPos[i] = (Integer) null;
		}

		// Set Pieces On Board
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				if (gm.getBoard()[i][j].getOwner().equals(players[0])) {
					board[i][j].setIcon(bluePiece);
				} else if (gm.getBoard()[i][j].getOwner().equals(players[1])) {
					board[i][j].setIcon(redPiece);
				} else {
					board[i][j].setIcon(blankPiece);
				}
			}
		}

		// Set Pieces on Side
		setSideState(0, gm.piecesOnSide(0));
		setSideState(1, gm.piecesOnSide(1));

		// Refresh the board
		contentPane.getContentPane().repaint();
	}

	private void setSideState(int playerID, int numPieces) {
		for (int i = 0; i < numPieces; i++) {
			if (playerID == 0) {
				try {
					bSide[i] = new JPanelWithBackground(blue);
				} catch (IOException e) {
					e.printStackTrace();
					bSide[i] = null;
				}
			} else {
				try {
					rSide[i] = new JPanelWithBackground(red);
				} catch (IOException e) {
					e.printStackTrace();
					rSide[i] = null;
				}
			}
		}
	}

}