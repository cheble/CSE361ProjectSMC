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

	// Image Locations, etc.
	// private String backL = "src/images/backgroundLarge.jpg";
	private String backS = "src/images/backgroundSmall.jpg";
	// private String boardL = "src/images/boardLarge.jpg";
	private String boardS = "src/images/boardSmall.jpg";
	private String blue = "src/images/blue.png";
	private String red = "src/images/red.png";
	private ImageIcon bluePiece = new ImageIcon(blue);
	private ImageIcon redPiece = new ImageIcon(red);

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

		JButton ready = new JButton("Ready");
		ready.setVisible(true);
		ready.setBounds(200, info.getHeight() - 150, info.getWidth() - 400, 50);
		ready.setFont(new Font("Coalition", Font.PLAIN, 30));
		ready.setOpaque(false);
		info.add(ready);
		ready.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (numHumans == 1) {
					if (nameOne.getText() != null) {
						// Set Name
						names[0] = nameOne.getText();

						// Remove Layered Pane
						contentPane.remove(layeredPane);

						// Draw Game With Updated Names
						drawBoard();
					}
				} else {
					if (nameOne.getText() != null && nameTwo.getText() != null) {
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

		// Create bOne & add the pieces
		try {
			bSide[0] = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bSide[0] = null;
		}
		bSide[0].setBounds(50, 350, 50, 50);
		bSide[0].setVisible(true);
		bSide[0].setOpaque(false);
		pieces.add(bSide[0]);

		// Create bTwo & add the pieces
		try {
			bSide[1] = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bSide[1] = null;
		}
		bSide[1].setBounds(bSide[0].getX() + 50, bSide[0].getY(), 50, 50);
		bSide[1].setVisible(true);
		bSide[1].setOpaque(false);
		pieces.add(bSide[1]);

		// Create bThree & add the pieces
		try {
			bSide[2] = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bSide[2] = null;
		}
		bSide[2].setBounds(bSide[1].getX() + 50, bSide[1].getY(), 50, 50);
		bSide[2].setVisible(true);
		bSide[2].setOpaque(false);
		pieces.add(bSide[2]);

		// Create bFour & add the pieces
		try {
			bSide[3] = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bSide[3] = null;
		}
		bSide[3].setBounds(bSide[2].getX() + 50, bSide[2].getY(), 50, 50);
		bSide[3].setVisible(true);
		bSide[3].setOpaque(false);
		pieces.add(bSide[3]);

		// Create bFive & add the pieces
		try {
			bSide[4] = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bSide[4] = null;
		}
		bSide[4].setBounds(bSide[0].getX() - (bSide[0].getWidth() / 2),
				bSide[3].getY() + 50, 50, 50);
		bSide[4].setVisible(true);
		bSide[4].setOpaque(false);
		pieces.add(bSide[4]);

		// Create bSix & add the pieces
		try {
			bSide[5] = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bSide[5] = null;
		}
		bSide[5].setBounds(bSide[4].getX() + 50, bSide[4].getY(), 50, 50);
		bSide[5].setVisible(true);
		bSide[5].setOpaque(false);
		pieces.add(bSide[5]);

		// Create bSeven & add the pieces
		try {
			bSide[6] = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bSide[6] = null;
		}
		bSide[6].setBounds(bSide[5].getX() + 50, bSide[5].getY(), 50, 50);
		bSide[6].setVisible(true);
		bSide[6].setOpaque(false);
		pieces.add(bSide[6]);

		// Create bEight & add the pieces
		try {
			bSide[7] = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bSide[7] = null;
		}
		bSide[7].setBounds(bSide[6].getX() + 50, bSide[6].getY(), 50, 50);
		bSide[7].setVisible(true);
		bSide[7].setOpaque(false);
		pieces.add(bSide[7]);

		// Create bNine & add the pieces
		try {
			bSide[8] = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bSide[8] = null;
		}
		bSide[8].setBounds(bSide[7].getX() + 50, bSide[7].getY(), 50, 50);
		bSide[8].setVisible(true);
		bSide[8].setOpaque(false);
		pieces.add(bSide[8]);

		// Create aOne & add the pieces
		try {
			rSide[0] = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			rSide[0] = null;
		}
		rSide[0].setBounds(contentPane.getWidth() - 250, bSide[0].getY(), 50,
				50);
		rSide[0].setVisible(true);
		rSide[0].setOpaque(false);
		pieces.add(rSide[0]);

		// Create aTwo & add the pieces
		try {
			rSide[1] = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			rSide[1] = null;
		}
		rSide[1].setBounds(rSide[0].getX() + 50, rSide[0].getY(), 50, 50);
		rSide[1].setVisible(true);
		rSide[1].setOpaque(false);
		pieces.add(rSide[1]);

		// Create aThree & add the pieces
		try {
			rSide[2] = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			rSide[2] = null;
		}
		rSide[2].setBounds(rSide[1].getX() + 50, rSide[1].getY(), 50, 50);
		rSide[2].setVisible(true);
		rSide[2].setOpaque(false);
		pieces.add(rSide[2]);

		// Create aFour & add the pieces
		try {
			rSide[3] = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			rSide[3] = null;
		}
		rSide[3].setBounds(rSide[2].getX() + 50, rSide[2].getY(), 50, 50);
		rSide[3].setVisible(true);
		rSide[3].setOpaque(false);
		pieces.add(rSide[3]);

		// Create aFive & add the pieces
		try {
			rSide[4] = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			rSide[4] = null;
		}
		rSide[4].setBounds(rSide[0].getX() - (rSide[0].getWidth() / 2),
				rSide[3].getY() + 50, 50, 50);
		rSide[4].setVisible(true);
		rSide[4].setOpaque(false);
		pieces.add(rSide[4]);

		// Create aSix & add the pieces
		try {
			rSide[5] = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			rSide[5] = null;
		}
		rSide[5].setBounds(rSide[4].getX() + 50, rSide[4].getY(), 50, 50);
		rSide[5].setVisible(true);
		rSide[5].setOpaque(false);
		pieces.add(rSide[5]);

		// Create aSeven & add the pieces
		try {
			rSide[6] = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			rSide[6] = null;
		}
		rSide[6].setBounds(rSide[5].getX() + 50, rSide[5].getY(), 50, 50);
		rSide[6].setVisible(true);
		rSide[6].setOpaque(false);
		pieces.add(rSide[6]);

		// Create aEight & add the pieces
		try {
			rSide[7] = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			rSide[7] = null;
		}
		rSide[7].setBounds(rSide[6].getX() + 50, rSide[6].getY(), 50, 50);
		rSide[7].setVisible(true);
		rSide[7].setOpaque(false);
		pieces.add(rSide[7]);

		// Create aNine & add the pieces
		try {
			rSide[8] = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			rSide[8] = null;
		}
		rSide[8].setBounds(rSide[7].getX() + 50, rSide[7].getY(), 50, 50);
		rSide[8].setVisible(true);
		rSide[8].setOpaque(false);
		pieces.add(rSide[8]);

		// Create button posOne and add to pieces
		board[0][0] = new JButton();
		board[0][0].setBounds(270, 12, 50, 50);
		board[0][0].setVisible(true);
		board[0][0].setOpaque(false);
		board[0][0].setContentAreaFilled(false);
		board[0][0].setBorderPainted(false);
		board[0][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[0][0])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 0;
				} else if (!isPositionSelected && board[0][0].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 0;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[0][0]);

		// Create button posTwo and add to pieces
		board[0][1] = new JButton();
		board[0][1].setBounds(board[0][0].getX() + 264, board[0][0].getY(), 50,
				50);
		board[0][1].setVisible(true);
		board[0][1].setOpaque(false);
		board[0][1].setContentAreaFilled(false);
		board[0][1].setBorderPainted(false);
		board[0][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[0][1])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 1;
				} else if (!isPositionSelected && board[0][1].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 1;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[0][1]);

		// Create button posThree and add to pieces
		board[0][2] = new JButton();
		board[0][2].setBounds(board[0][1].getX() + 264, board[0][0].getY(), 50,
				50);
		board[0][2].setVisible(true);
		board[0][2].setOpaque(false);
		board[0][2].setContentAreaFilled(false);
		board[0][2].setBorderPainted(false);
		board[0][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[0][2])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 2;
				} else if (!isPositionSelected && board[0][2].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 2;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[0][2]);

		// Create button posFour and add to pieces
		board[1][0] = new JButton();
		board[1][0].setBounds(board[0][0].getX() + 70, board[0][0].getY() + 70,
				50, 50);
		board[1][0].setVisible(true);
		board[1][0].setOpaque(false);
		board[1][0].setContentAreaFilled(false);
		board[1][0].setBorderPainted(false);
		board[1][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[1][0])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 0;
				} else if (!isPositionSelected && board[1][0].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 0;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[1][0]);

		// Create button posFive and add to pieces
		board[1][1] = new JButton();
		board[1][1].setBounds(board[0][1].getX(), board[0][1].getY(), 50, 50);
		board[1][1].setVisible(true);
		board[1][1].setOpaque(false);
		board[1][1].setContentAreaFilled(false);
		board[1][1].setBorderPainted(false);
		board[1][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[1][1])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 1;
				} else if (!isPositionSelected && board[1][1].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 1;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[1][1]);

		// Create button posSix and add to pieces
		board[1][2] = new JButton();
		board[1][2].setBounds(board[0][2].getX() - 70, board[0][2].getY(), 50,
				50);
		board[1][2].setVisible(true);
		board[1][2].setOpaque(false);
		board[1][2].setContentAreaFilled(false);
		board[1][2].setBorderPainted(false);
		board[1][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[1][2])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 2;
				} else if (!isPositionSelected && board[1][2].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 2;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[1][2]);

		// Create button posSeven and add to pieces
		board[2][0] = new JButton();
		board[2][0].setBounds(board[1][0].getX() + 70, board[1][0].getY() + 70,
				50, 50);
		board[2][0].setVisible(true);
		board[2][0].setOpaque(false);
		board[2][0].setContentAreaFilled(false);
		board[2][0].setBorderPainted(false);
		board[2][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[2][0])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 0;
				} else if (!isPositionSelected && board[2][0].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 0;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[2][0]);

		// Create button posEight and add to pieces
		board[2][1] = new JButton();
		board[2][1].setBounds(board[1][1].getX(), board[2][0].getY(), 50, 50);
		board[2][1].setVisible(true);
		board[2][1].setOpaque(false);
		board[2][1].setContentAreaFilled(false);
		board[2][1].setBorderPainted(false);
		board[2][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[2][1])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 1;
				} else if (!isPositionSelected && board[2][1].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 1;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[2][1]);

		// Create button posNine and add to pieces
		board[2][2] = new JButton();
		board[2][2].setBounds(board[1][2].getX() - 70, board[2][1].getY(), 50,
				50);
		board[2][2].setVisible(true);
		board[2][2].setOpaque(false);
		board[2][2].setContentAreaFilled(false);
		board[2][2].setBorderPainted(false);
		board[2][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[2][2])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 2;
				} else if (!isPositionSelected && board[2][2].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 2;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[2][2]);

		// Create button posTen and add to pieces
		board[0][7] = new JButton();
		board[0][7].setBounds(board[0][0].getX(), board[0][0].getY() + 265, 50,
				50);
		board[0][7].setVisible(true);
		board[0][7].setOpaque(false);
		board[0][7].setContentAreaFilled(false);
		board[0][7].setBorderPainted(false);
		board[0][7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[0][7])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 7;
				} else if (!isPositionSelected && board[0][7].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 7;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[0][7]);

		// Create button posEleven and add to pieces
		board[1][7] = new JButton();
		board[1][7].setBounds(board[1][0].getX(), board[0][7].getY(), 50, 50);
		board[1][7].setVisible(true);
		board[1][7].setOpaque(false);
		board[1][7].setContentAreaFilled(false);
		board[1][7].setBorderPainted(false);
		board[1][7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[1][7])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 7;
				} else if (!isPositionSelected && board[1][7].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 7;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[1][7]);

		// Create button posTwelve and add to pieces
		board[2][7] = new JButton();
		board[2][7].setBounds(board[2][0].getX(), board[1][7].getY(), 50, 50);
		board[2][7].setVisible(true);
		board[2][7].setOpaque(false);
		board[2][7].setContentAreaFilled(false);
		board[2][7].setBorderPainted(false);
		board[2][7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[2][7])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 7;
				} else if (!isPositionSelected && board[2][7].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 7;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[2][7]);

		// Create button posThirteen and add to pieces
		board[2][3] = new JButton();
		board[2][3].setBounds(board[2][2].getX(), board[2][7].getY(), 50, 50);
		board[2][3].setVisible(true);
		board[2][3].setOpaque(false);
		board[2][3].setContentAreaFilled(false);
		board[2][3].setBorderPainted(false);
		board[2][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[2][3])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 3;
				} else if (!isPositionSelected && board[2][3].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 3;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[2][3]);

		// Create button posFourteen and add to pieces
		board[1][3] = new JButton();
		board[1][3].setBounds(board[1][2].getX(), board[2][3].getY(), 50, 50);
		board[1][3].setVisible(true);
		board[1][3].setOpaque(false);
		board[1][3].setContentAreaFilled(false);
		board[1][3].setBorderPainted(false);
		board[1][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[1][3])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 3;
				} else if (!isPositionSelected && board[1][3].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 3;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[1][3]);

		// Create button posFifteen and add to pieces
		board[0][3] = new JButton();
		board[0][3].setBounds(board[0][2].getX(), board[1][3].getY(), 50, 50);
		board[0][3].setVisible(true);
		board[0][3].setOpaque(false);
		board[0][3].setContentAreaFilled(false);
		board[0][3].setBorderPainted(false);
		board[0][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[0][3])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 3;
				} else if (!isPositionSelected && board[0][3].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 3;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[0][3]);

		// Create button posSixteen and add to pieces
		board[2][6] = new JButton();
		board[2][6].setBounds(board[2][7].getX(), board[2][7].getY()
				+ (board[2][7].getY() - board[2][0].getY()), 50, 50);
		board[2][6].setVisible(true);
		board[2][6].setOpaque(false);
		board[2][6].setContentAreaFilled(false);
		board[2][6].setBorderPainted(false);
		board[2][6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[2][6])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 6;
				} else if (!isPositionSelected && board[2][6].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 6;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[2][6]);

		// Create button posSeventeen and add to pieces
		board[2][5] = new JButton();
		board[2][5].setBounds(board[2][1].getX(), board[2][6].getY(), 50, 50);
		board[2][5].setVisible(true);
		board[2][5].setOpaque(false);
		board[2][5].setContentAreaFilled(false);
		board[2][5].setBorderPainted(false);
		board[2][5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[2][5])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 5;
				} else if (!isPositionSelected && board[2][5].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 5;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[2][5]);

		// Create button posEighteen and add to pieces
		board[2][4] = new JButton();
		board[2][4].setBounds(board[2][3].getX(), board[2][5].getY(), 50, 50);
		board[2][4].setVisible(true);
		board[2][4].setOpaque(false);
		board[2][4].setContentAreaFilled(false);
		board[2][4].setBorderPainted(false);
		board[2][4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[2][4])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 2;
					pieceSelectedPos[1] = 4;
				} else if (!isPositionSelected && board[2][4].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 2;
					selectedPos[1] = 4;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[2][4]);

		// Create button posNineteen and add to pieces
		board[1][6] = new JButton();
		board[1][6].setBounds(board[1][7].getX(), board[2][6].getY() + 70, 50,
				50);
		board[1][6].setVisible(true);
		board[1][6].setOpaque(false);
		board[1][6].setContentAreaFilled(false);
		board[1][6].setBorderPainted(false);
		board[1][6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[1][6])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 6;
				} else if (!isPositionSelected && board[1][6].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 6;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[1][6]);

		// Create button posTwenty and add to pieces
		board[1][5] = new JButton();
		board[1][5].setBounds(board[2][5].getX(), board[1][6].getY(), 50, 50);
		board[1][5].setVisible(true);
		board[1][5].setOpaque(false);
		board[1][5].setContentAreaFilled(false);
		board[1][5].setBorderPainted(false);
		board[1][5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[1][5])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 5;
				} else if (!isPositionSelected && board[1][5].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 5;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[1][5]);

		// Create button posTwentyOne and add to pieces
		board[1][4] = new JButton();
		board[1][4].setBounds(board[1][3].getX(), board[1][5].getY(), 50, 50);
		board[1][4].setVisible(true);
		board[1][4].setOpaque(false);
		board[1][4].setContentAreaFilled(false);
		board[1][4].setBorderPainted(false);
		board[1][4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[1][4])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 1;
					pieceSelectedPos[1] = 4;
				} else if (!isPositionSelected && board[1][4].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 1;
					selectedPos[1] = 4;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[1][4]);

		// Create button posTwentyTwo and add to pieces
		board[0][6] = new JButton();
		board[0][6].setBounds(board[0][7].getX(), board[1][6].getY() + 70, 50,
				50);
		board[0][6].setVisible(true);
		board[0][6].setOpaque(false);
		board[0][6].setContentAreaFilled(false);
		board[0][6].setBorderPainted(false);
		board[0][6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[0][6])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 6;
				} else if (!isPositionSelected && board[0][6].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 6;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[0][6]);

		// Create button posTwentyThree and add to pieces
		board[0][5] = new JButton();
		board[0][5].setBounds(board[1][5].getX(), board[0][6].getY(), 50, 50);
		board[0][5].setVisible(true);
		board[0][5].setOpaque(false);
		board[0][5].setContentAreaFilled(false);
		board[0][5].setBorderPainted(false);
		board[0][5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[0][5])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 5;
				} else if (!isPositionSelected && board[0][5].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 5;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[0][5]);

		// Create button posTwentyFour and add to pieces
		board[0][4] = new JButton();
		board[0][4].setBounds(board[0][3].getX(), board[0][5].getY(), 50, 50);
		board[0][4].setVisible(true);
		board[0][4].setOpaque(false);
		board[0][4].setContentAreaFilled(false);
		board[0][4].setBorderPainted(false);
		board[0][4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!isPieceSelected || board[pieceSelectedPos[0]][pieceSelectedPos[1]].equals(board[0][4])) {
					isPieceSelected = true;
					pieceSelectedPos[0] = 0;
					pieceSelectedPos[1] = 4;
				} else if (!isPositionSelected && board[0][4].getIcon() == null) {
					isPositionSelected = true;
					selectedPos[0] = 0;
					selectedPos[1] = 4;
				} else {
					// Do Nothing
				}
			}
		});
		pieces.add(board[0][4]);

	}

	public void drawHowTo(final JPanel panel) {
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

		// Create Basics Slide (Slide 1)
		JPanel basics = new JPanel();
		basics.setVisible(true);
		basics.setOpaque(false);
		basics.setBounds(25, 25, cardPanel.getWidth() - 50,
				cardPanel.getHeight() - 50);
		JLabel basicsText = new JLabel("Basics:\n");
		basicsText.setHorizontalAlignment(SwingConstants.LEFT);
		basicsText.setFont(new Font("Coalition", Font.PLAIN, 30));
		basicsText.setBounds(0, 0, basics.getWidth(), basics.getHeight());
		basicsText.setForeground(Color.WHITE);
		basics.add(basicsText);
		cardPanel.add(basics, "1");

		// Create Placement Mode Slide (Slide 2)
		JPanel place = new JPanel();
		place.setVisible(true);
		place.setOpaque(false);
		place.setBounds(25, 25, cardPanel.getWidth() - 50,
				cardPanel.getHeight() - 50);
		JLabel placeText = new JLabel("Placement Mode:\n");
		placeText.setHorizontalAlignment(SwingConstants.LEFT);
		placeText.setBounds(0, 0, place.getWidth(), place.getHeight());
		placeText.setFont(new Font("Coalition", Font.PLAIN, 30));
		placeText.setForeground(Color.WHITE);
		place.add(placeText);
		cardPanel.add(place, "2");

		// Create Movement Mode Slide (Slide 3)
		JPanel move = new JPanel();
		move.setVisible(true);
		move.setOpaque(false);
		move.setBounds(25, 25, cardPanel.getWidth() - 50,
				cardPanel.getHeight() - 50);
		JLabel moveText = new JLabel("Movement Mode:\n");
		moveText.setHorizontalAlignment(SwingConstants.LEFT);
		moveText.setBounds(0, 0, move.getWidth(), move.getHeight());
		moveText.setFont(new Font("Coalition", Font.PLAIN, 30));
		moveText.setForeground(Color.WHITE);
		move.add(moveText);
		cardPanel.add(move, "3");

		// Create Fly Mode Slide (Slide 4)
		JPanel fly = new JPanel();
		fly.setVisible(true);
		fly.setOpaque(false);
		fly.setBounds(25, 25, cardPanel.getWidth() - 50,
				cardPanel.getHeight() - 50);
		JLabel flyText = new JLabel("Fly Mode:\n");
		flyText.setHorizontalAlignment(SwingConstants.LEFT);
		flyText.setBounds(0, 0, fly.getWidth(), fly.getHeight());
		flyText.setFont(new Font("Coalition", Font.PLAIN, 30));
		flyText.setForeground(Color.WHITE);
		fly.add(flyText);
		cardPanel.add(fly, "4");

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
					this.board[i][j].setIcon(bluePiece);
				} else if (gm.getBoard()[i][j].getOwner().equals(players[1])) {
					this.board[i][j].setIcon(redPiece);
				} else {
					// Do nothing
				}
			}
		}

		// Set Pieces on Side
		for (int i = 0; i < 2; i++) {
			setSideState(i, gm.piecesOnSide(i));
		}

		// Refresh the board
		contentPane.getContentPane().repaint();
	}

	public void setSideState(int playerID, int numPieces) {
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