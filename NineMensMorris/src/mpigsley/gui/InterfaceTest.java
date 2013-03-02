package mpigsley.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class InterfaceTest {

	private JFrame contentPane;
	private int slideNum = 1;
	private int flyMode = 1;
	private boolean timer = false;
	private ImageIcon bluePiece = new ImageIcon("src/images/blue.png");
	private ImageIcon redPiece = new ImageIcon("src/images/red.png");

	public InterfaceTest() {
		// Initialize contentPane
		contentPane = new JFrame();
		contentPane.setVisible(true);
		contentPane.setSize(new Dimension(1440, 900));
		contentPane.setResizable(false);
		contentPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setLayout(null);

		// Create Main Menu GUI
		createMain();
	}

	public void createMain() {
		// Initialize LayeredPane
		final JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1440, 900);
		layeredPane.setLayout(null);
		this.contentPane.add(layeredPane);

		// Create Background JPanel & Add to LayeredPane on Layer 1
		JPanel background;
		try {
			background = new JPanelWithBackground("src/images/background.jpg");
		} catch (IOException e) {
			e.printStackTrace();
			background = null;
		}
		background.setBounds(0, 0, 1440, 900);
		background.setVisible(true);
		layeredPane.add(background);
		layeredPane.setLayer(background, 1);

		// Create Buttons JPanel & Add to LayerdPane on Layer 1
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		buttons.setBounds(0, 0, 1440, 900);
		buttons.setVisible(true);
		layeredPane.add(buttons);
		layeredPane.setLayer(buttons, 2);

		// Create Title
		JLabel title = new JLabel("Nine Men's Morris");
		title.setForeground(Color.LIGHT_GRAY);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Coalition", Font.BOLD, 89));
		title.setBounds(0, 110, 1440, 127);

		// Create Human vs. Computer Button
		final JButton hvc = new JButton("Human vs. Computer");
		hvc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				hvc.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				hvc.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Remove Current Screen
				contentPane.remove(layeredPane);

				// Open Options Menu
				createGame();
			}
		});
		hvc.setFont(new Font("Coalition", Font.PLAIN, 40));
		hvc.setForeground(Color.WHITE);
		hvc.setBounds(0, 350, 1440, 65);
		hvc.setOpaque(false);
		hvc.setContentAreaFilled(false);
		hvc.setBorderPainted(false);

		// Create Human vs. Human Button
		final JButton hvh = new JButton("Human vs. Human");
		hvh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				hvh.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				hvh.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Remove Current Screen
				contentPane.remove(layeredPane);

				// Open Options Menu
				createGame();
			}
		});
		hvh.setFont(new Font("Coalition", Font.PLAIN, 40));
		hvh.setForeground(Color.WHITE);
		hvh.setBounds(0, 430, 1440, 65);
		hvh.setOpaque(false);
		hvh.setContentAreaFilled(false);
		hvh.setBorderPainted(false);

		// Create Leaderboard Button
		final JButton lb = new JButton("Leaderboard");
		lb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lb.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lb.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		lb.setFont(new Font("Coalition", Font.PLAIN, 40));
		lb.setForeground(Color.WHITE);
		lb.setBounds(0, 510, 1440, 65);
		lb.setOpaque(false);
		lb.setContentAreaFilled(false);
		lb.setBorderPainted(false);

		// Create Options Button
		final JButton options = new JButton("Options");
		options.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				options.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				options.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Remove Current Screen
				contentPane.remove(layeredPane);

				// Open Options Menu
				createOptions();
			}
		});
		options.setFont(new Font("Coalition", Font.PLAIN, 40));
		options.setForeground(Color.WHITE);
		options.setBounds(0, 590, 1440, 65);
		options.setOpaque(false);
		options.setContentAreaFilled(false);
		options.setBorderPainted(false);

		// Create Exit Button
		final JButton exit = new JButton("Exit");
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				exit.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				exit.setForeground(Color.WHITE);
			}

			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(contentPane.ABORT);
			}
		});
		exit.setFont(new Font("Coalition", Font.PLAIN, 40));
		exit.setForeground(Color.WHITE);
		exit.setBounds(0, 670, 1440, 65);
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);

		// Add elements to the buttons JPanel
		buttons.setLayout(null);
		buttons.add(title);
		buttons.add(hvc);
		buttons.add(exit);
		buttons.add(lb);
		buttons.add(options);
		buttons.add(hvh);
	}

	public void createOptions() {
		// Initialize LayeredPane
		final JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1440, 900);
		layeredPane.setLayout(null);
		this.contentPane.add(layeredPane);

		// Create Background JPanel & Add to LayeredPane on Layer 1
		JPanel background;
		try {
			background = new JPanelWithBackground("src/images/background.jpg");
		} catch (IOException e) {
			e.printStackTrace();
			background = null;
		}
		background.setBounds(0, 0, 1440, 900);
		background.setVisible(true);
		background.setLayout(null);
		layeredPane.add(background);
		layeredPane.setLayer(background, 1);

		// Create Buttons JPanel & Add to LayerdPane on Layer 2
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		buttons.setBounds(0, 0, 1440, 900);
		buttons.setVisible(true);
		buttons.setLayout(null);
		layeredPane.add(buttons);
		layeredPane.setLayer(buttons, 2);

		// Create howToPanel JPanel & Add to LayerdPane on Layer 3
		final JPanel howToPanel = new JPanel();
		howToPanel.setOpaque(false);
		layeredPane.add(howToPanel);
		layeredPane.setLayer(howToPanel, 3);

		// Create Title and add to Layer
		JLabel title = new JLabel("Options");
		title.setForeground(Color.LIGHT_GRAY);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Coalition", Font.BOLD, 89));
		title.setBounds(0, 110, 1440, 127);
		buttons.add(title);

		// Create How to Play button & add to Layer
		final JButton howTo = new JButton("How To Play");
		howTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				howTo.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				howTo.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Open How To Play
				createHowTo(howToPanel);
			}
		});
		howTo.setFont(new Font("Coalition", Font.PLAIN, 40));
		howTo.setForeground(Color.WHITE);
		howTo.setBounds(0, 350, 1440, 65);
		howTo.setOpaque(false);
		howTo.setContentAreaFilled(false);
		howTo.setBorderPainted(false);
		buttons.add(howTo);

		// Create Fly Mode Title & add to Layer
		JLabel fly = new JLabel("Fly Mode:      3      4      Off");
		fly.setForeground(Color.WHITE);
		fly.setHorizontalAlignment(SwingConstants.CENTER);
		fly.setFont(new Font("Coalition", Font.BOLD, 40));
		fly.setBounds(0, 456, 1440, 65);
		buttons.add(fly);

		// Create Fly Mode 4 Radio button & add to Layer
		final JButton fMThree = new JButton(new ImageIcon("src/images/off.png"));
		fMThree.setBounds(660, 470, 30, 30);
		fMThree.setVisible(true);
		fMThree.setOpaque(false);
		fMThree.setContentAreaFilled(false);
		fMThree.setBorderPainted(false);
		buttons.add(fMThree);

		// Create Fly Mode 3 Radio button & add to Layer
		final JButton fMFour = new JButton(new ImageIcon("src/images/off.png"));
		fMFour.setBounds(810, 470, 30, 30);
		fMFour.setVisible(true);
		fMFour.setOpaque(false);
		fMFour.setContentAreaFilled(false);
		fMFour.setBorderPainted(false);
		buttons.add(fMFour);

		// Create Fly Mode Off Radio button & add to Layer
		final JButton fMOff = new JButton(new ImageIcon("src/images/off.png"));
		fMOff.setBounds(960, 470, 30, 30);
		fMOff.setVisible(true);
		fMOff.setOpaque(false);
		fMOff.setContentAreaFilled(false);
		fMOff.setBorderPainted(false);
		buttons.add(fMOff);

		// If Fly Mode buttons are clicked...
		fMThree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Set all others to off
				fMFour.setIcon(new ImageIcon("src/images/off.png"));
				fMOff.setIcon(new ImageIcon("src/images/off.png"));
				// Set to this one to on
				fMThree.setIcon(new ImageIcon("src/images/on.png"));
				// Update global flyMode var
				flyMode = 1;
			}
		});

		fMFour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Set all others to off
				fMThree.setIcon(new ImageIcon("src/images/off.png"));
				fMOff.setIcon(new ImageIcon("src/images/off.png"));
				// Set to this one to on
				fMFour.setIcon(new ImageIcon("src/images/on.png"));
				// Update global flyMode var
				flyMode = 2;
			}
		});

		fMOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Set all others to off
				fMFour.setIcon(new ImageIcon("src/images/off.png"));
				fMThree.setIcon(new ImageIcon("src/images/off.png"));
				// Set to this one to on
				fMOff.setIcon(new ImageIcon("src/images/on.png"));
				// Update global flyMode var
				flyMode = 3;
			}
		});

		// Create Timer Title & add to Layer
		JLabel timerTitle = new JLabel("Timer:      On      Off");
		timerTitle.setForeground(Color.WHITE);
		timerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		timerTitle.setFont(new Font("Coalition", Font.BOLD, 40));
		timerTitle.setBounds(0, 562, 1440, 65);
		buttons.add(timerTitle);

		// Create Timer On Radio button & add to Layer
		final JButton tOn = new JButton(new ImageIcon("src/images/off.png"));
		tOn.setBounds(660, 575, 30, 30);
		tOn.setVisible(true);
		tOn.setOpaque(false);
		tOn.setContentAreaFilled(false);
		tOn.setBorderPainted(false);
		buttons.add(tOn);

		// Create Timer Off Radio button & add to Layer
		final JButton tOff = new JButton(new ImageIcon("src/images/off.png"));
		tOff.setBounds(860, 575, 30, 30);
		tOff.setVisible(true);
		tOff.setOpaque(false);
		tOff.setContentAreaFilled(false);
		tOff.setBorderPainted(false);
		buttons.add(tOff);

		// If timer buttons are pressed...
		tOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Set other to off
				tOff.setIcon(new ImageIcon("src/images/off.png"));
				// Set to this one to on
				tOn.setIcon(new ImageIcon("src/images/on.png"));
				// Update global timer var
				timer = true;
			}
		});

		tOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Set all others to off
				tOn.setIcon(new ImageIcon("src/images/off.png"));
				// Set to this one to on
				tOff.setIcon(new ImageIcon("src/images/on.png"));
				// Update global timer var
				timer = false;
			}
		});

		// Create Main Menu button & add to Layer
		final JButton main = new JButton("Main Menu");
		main.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				main.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				main.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Remove Current Screen
				contentPane.remove(layeredPane);

				// Open Main Menu
				createMain();
			}
		});
		main.setFont(new Font("Coalition", Font.PLAIN, 40));
		main.setForeground(Color.WHITE);
		main.setBounds(0, 668, 1440, 65);
		main.setOpaque(false);
		main.setContentAreaFilled(false);
		main.setBorderPainted(false);
		buttons.add(main);

		// Set Default Settings for Options Menu
		if (this.timer) {
			tOn.setIcon(new ImageIcon("src/images/on.png"));
		} else {
			tOff.setIcon(new ImageIcon("src/images/on.png"));
		}
		if (flyMode == 1) {
			fMThree.setIcon(new ImageIcon("src/images/on.png"));
		} else if (flyMode == 2) {
			fMFour.setIcon(new ImageIcon("src/images/on.png"));
		} else {
			fMOff.setIcon(new ImageIcon("src/images/on.png"));
		}

	}

	public void createGame() {
		// Initialize LayeredPane
		final JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1440, 852);
		layeredPane.setLayout(null);
		this.contentPane.add(layeredPane);

		// Create Background JPanel & Add to LayeredPane on Layer 1
		JPanel background;
		try {
			background = new JPanelWithBackground("src/images/board.jpg");
		} catch (IOException e) {
			e.printStackTrace();
			background = null;
		}
		background.setBounds(0, 0, 1440, 900);
		background.setVisible(true);
		layeredPane.add(background);
		layeredPane.setLayer(background, 1);

		// Create Buttons JPanel & Add to LayerdPane on Layer 2
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		buttons.setBounds(0, 0, 1440, 900);
		buttons.setVisible(true);
		buttons.setLayout(null);
		layeredPane.add(buttons);
		layeredPane.setLayer(buttons, 2);

		// Add undo button to JPanel
		final JButton undo = new JButton("Undo");
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
		undo.setBounds(620, 775, 200, 65);
		undo.setOpaque(false);
		undo.setContentAreaFilled(false);
		undo.setBorderPainted(false);
		buttons.add(undo);

		// Add skip button to JPanel
		final JButton skip = new JButton("Skip");
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
		skip.setBounds(20, 775, 168, 65);
		skip.setOpaque(false);
		skip.setContentAreaFilled(false);
		skip.setBorderPainted(false);
		buttons.add(skip);

		// Add menu button to JPanel
		final JButton menu = new JButton("Menu");
		menu.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {
				menu.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				menu.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				// Create Dim Panel and add as layer 3
				final JPanel dim = new JPanel();
				dim.setBounds(0, 0, 1440, 900);
				dim.setVisible(true);
				dim.setOpaque(false);
				dim.setLayout(null);
				layeredPane.add(dim);
				layeredPane.setLayer(dim, 3);

				// Create Menu with and add as layer 4
				final JPanel menu = new JPanel();
				menu.setBounds(445, 50, 550, 700);
				menu.setVisible(true);
				menu.setBackground(Color.LIGHT_GRAY);
				menu.setBorder(new LineBorder(Color.BLACK, 5));
				menu.setLayout(null);
				layeredPane.add(menu);
				layeredPane.setLayer(menu, 4);

				// Create howToPanel Panel and add as layer 5
				final JPanel howToPanel = new JPanel();
				howToPanel.setOpaque(false);
				layeredPane.add(howToPanel);
				layeredPane.setLayer(howToPanel, 5);

				// Create Menu Title and add to Menu
				JLabel menuTitle = new JLabel("Menu");
				menuTitle.setForeground(Color.BLACK);
				menuTitle.setHorizontalAlignment(SwingConstants.CENTER);
				menuTitle.setFont(new Font("Coalition", Font.BOLD, 89));
				menuTitle.setBounds(0, 150, 550, 100);
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
						createHowTo(howToPanel);
					}
				});
				howTo.setFont(new Font("Coalition", Font.PLAIN, 40));
				howTo.setForeground(Color.BLACK);
				howTo.setBounds(0, 300, 550, 65);
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
				restart.setBounds(0, 375, 550, 65);
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
						// Remove Current Screen
						contentPane.remove(layeredPane);

						// Open Main Menu
						createMain();
					}
				});
				main.setFont(new Font("Coalition", Font.PLAIN, 40));
				main.setForeground(Color.BLACK);
				main.setBounds(0, 450, 550, 65);
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
						dim.setVisible(false);
						menu.setVisible(false);
					}
				});
				close.setFont(new Font("Coalition", Font.PLAIN, 40));
				close.setForeground(Color.BLACK);
				close.setBounds(0, 525, 550, 65);
				close.setOpaque(false);
				close.setContentAreaFilled(false);
				close.setBorderPainted(false);
				menu.add(close);
			}
		});
		menu.setFont(new Font("Coalition", Font.PLAIN, 40));
		menu.setForeground(Color.WHITE);
		menu.setBounds(1225, 775, 200, 65);
		menu.setOpaque(false);
		menu.setContentAreaFilled(false);
		menu.setBorderPainted(false);
		buttons.add(menu);

		// Add Player One Name JLabel and add to JPanel
		JLabel pOne = new JLabel("Player 1");
		pOne.setForeground(Color.LIGHT_GRAY);
		pOne.setHorizontalAlignment(SwingConstants.CENTER);
		pOne.setFont(new Font("Coalition", Font.BOLD, 40));
		pOne.setBounds(50, 300, 280, 66);
		buttons.add(pOne);

		// Add Player Two Name JLabel and add to JPanel
		JLabel pTwo = new JLabel("Player 2");
		pTwo.setForeground(Color.LIGHT_GRAY);
		pTwo.setHorizontalAlignment(SwingConstants.CENTER);
		pTwo.setFont(new Font("Coalition", Font.BOLD, 40));
		pTwo.setBounds(1120, 300, 280, 66);
		buttons.add(pTwo);

		// Add Player One Score JLabel and add to JPanel
		JLabel pOneScore = new JLabel("0");
		pOneScore.setForeground(Color.LIGHT_GRAY);
		pOneScore.setHorizontalAlignment(SwingConstants.CENTER);
		pOneScore.setFont(new Font("Coalition", Font.BOLD, 40));
		pOneScore.setBounds(50, 350, 280, 66);
		buttons.add(pOneScore);

		// Add Player Two Score JLabel and add to JPanel
		JLabel pTwoScore = new JLabel("0");
		pTwoScore.setForeground(Color.LIGHT_GRAY);
		pTwoScore.setHorizontalAlignment(SwingConstants.CENTER);
		pTwoScore.setFont(new Font("Coalition", Font.BOLD, 40));
		pTwoScore.setBounds(1120, 350, 280, 66);
		buttons.add(pTwoScore);

		// Create Pieces JPanel & Add to LayerdPane on Layer 3
		JPanel pieces = new JPanel();
		pieces.setOpaque(false);
		pieces.setBounds(0, 0, 1440, 900);
		pieces.setVisible(true);
		pieces.setLayout(null);
		layeredPane.add(pieces);
		layeredPane.setLayer(pieces, 3);

		// Create bOne & add the pieces
		JPanel bOne;
		try {
			bOne = new JPanelWithBackground("src/images/blue.png");
		} catch (IOException e) {
			e.printStackTrace();
			bOne = null;
		}
		bOne.setBounds(65, 450, 50, 50);
		bOne.setVisible(true);
		bOne.setOpaque(false);
		pieces.add(bOne);

		// Create bTwo & add the pieces
		JPanel bTwo;
		try {
			bTwo = new JPanelWithBackground("src/images/blue.png");
		} catch (IOException e) {
			e.printStackTrace();
			bTwo = null;
		}
		bTwo.setBounds(130, 450, 50, 50);
		bTwo.setVisible(true);
		bTwo.setOpaque(false);
		pieces.add(bTwo);

		// Create bThree & add the pieces
		JPanel bThree;
		try {
			bThree = new JPanelWithBackground("src/images/blue.png");
		} catch (IOException e) {
			e.printStackTrace();
			bThree = null;
		}
		bThree.setBounds(195, 450, 50, 50);
		bThree.setVisible(true);
		bThree.setOpaque(false);
		pieces.add(bThree);

		// Create bFour & add the pieces
		JPanel bFour;
		try {
			bFour = new JPanelWithBackground("src/images/blue.png");
		} catch (IOException e) {
			e.printStackTrace();
			bFour = null;
		}
		bFour.setBounds(260, 450, 50, 50);
		bFour.setVisible(true);
		bFour.setOpaque(false);
		pieces.add(bFour);

		// Create bFive & add the pieces
		JPanel bFive;
		try {
			bFive = new JPanelWithBackground("src/images/blue.png");
		} catch (IOException e) {
			e.printStackTrace();
			bFive = null;
		}
		bFive.setBounds(32, 515, 50, 50);
		bFive.setVisible(true);
		bFive.setOpaque(false);
		pieces.add(bFive);

		// Create bSix & add the pieces
		JPanel bSix;
		try {
			bSix = new JPanelWithBackground("src/images/blue.png");
		} catch (IOException e) {
			e.printStackTrace();
			bSix = null;
		}
		bSix.setBounds(97, 515, 50, 50);
		bSix.setVisible(true);
		bSix.setOpaque(false);
		pieces.add(bSix);

		// Create bSeven & add the pieces
		JPanel bSeven;
		try {
			bSeven = new JPanelWithBackground("src/images/blue.png");
		} catch (IOException e) {
			e.printStackTrace();
			bSeven = null;
		}
		bSeven.setBounds(162, 515, 50, 50);
		bSeven.setVisible(true);
		bSeven.setOpaque(false);
		pieces.add(bSeven);

		// Create bEight & add the pieces
		JPanel bEight;
		try {
			bEight = new JPanelWithBackground("src/images/blue.png");
		} catch (IOException e) {
			e.printStackTrace();
			bEight = null;
		}
		bEight.setBounds(227, 515, 50, 50);
		bEight.setVisible(true);
		bEight.setOpaque(false);
		pieces.add(bEight);

		// Create bNine & add the pieces
		JPanel bNine;
		try {
			bNine = new JPanelWithBackground("src/images/blue.png");
		} catch (IOException e) {
			e.printStackTrace();
			bNine = null;
		}
		bNine.setBounds(292, 515, 50, 50);
		bNine.setVisible(true);
		bNine.setOpaque(false);
		pieces.add(bNine);

		// Create aOne & add the pieces
		JPanel aOne;
		try {
			aOne = new JPanelWithBackground("src/images/red.png");
		} catch (IOException e) {
			e.printStackTrace();
			aOne = null;
		}
		aOne.setBounds(1130, 450, 50, 50);
		aOne.setVisible(true);
		aOne.setOpaque(false);
		pieces.add(aOne);

		// Create aTwo & add the pieces
		JPanel aTwo;
		try {
			aTwo = new JPanelWithBackground("src/images/red.png");
		} catch (IOException e) {
			e.printStackTrace();
			aTwo = null;
		}
		aTwo.setBounds(1195, 450, 50, 50);
		aTwo.setVisible(true);
		aTwo.setOpaque(false);
		pieces.add(aTwo);

		// Create aThree & add the pieces
		JPanel aThree;
		try {
			aThree = new JPanelWithBackground("src/images/red.png");
		} catch (IOException e) {
			e.printStackTrace();
			aThree = null;
		}
		aThree.setBounds(1260, 450, 50, 50);
		aThree.setVisible(true);
		aThree.setOpaque(false);
		pieces.add(aThree);

		// Create aFour & add the pieces
		JPanel aFour;
		try {
			aFour = new JPanelWithBackground("src/images/red.png");
		} catch (IOException e) {
			e.printStackTrace();
			aFour = null;
		}
		aFour.setBounds(1325, 450, 50, 50);
		aFour.setVisible(true);
		aFour.setOpaque(false);
		pieces.add(aFour);

		// Create aFive & add the pieces
		JPanel aFive;
		try {
			aFive = new JPanelWithBackground("src/images/red.png");
		} catch (IOException e) {
			e.printStackTrace();
			aFive = null;
		}
		aFive.setBounds(1098, 515, 50, 50);
		aFive.setVisible(true);
		aFive.setOpaque(false);
		pieces.add(aFive);

		// Create aSix & add the pieces
		JPanel aSix;
		try {
			aSix = new JPanelWithBackground("src/images/red.png");
		} catch (IOException e) {
			e.printStackTrace();
			aSix = null;
		}
		aSix.setBounds(1163, 515, 50, 50);
		aSix.setVisible(true);
		aSix.setOpaque(false);
		pieces.add(aSix);

		// Create aSeven & add the pieces
		JPanel aSeven;
		try {
			aSeven = new JPanelWithBackground("src/images/red.png");
		} catch (IOException e) {
			e.printStackTrace();
			aSeven = null;
		}
		aSeven.setBounds(1228, 515, 50, 50);
		aSeven.setVisible(true);
		aSeven.setOpaque(false);
		pieces.add(aSeven);

		// Create aEight & add the pieces
		JPanel aEight;
		try {
			aEight = new JPanelWithBackground("src/images/red.png");
		} catch (IOException e) {
			e.printStackTrace();
			aEight = null;
		}
		aEight.setBounds(1293, 515, 50, 50);
		aEight.setVisible(true);
		aEight.setOpaque(false);
		pieces.add(aEight);

		// Create bNine & add the pieces
		JPanel aNine;
		try {
			aNine = new JPanelWithBackground("src/images/red.png");
		} catch (IOException e) {
			e.printStackTrace();
			aNine = null;
		}
		aNine.setBounds(1358, 515, 50, 50);
		aNine.setVisible(true);
		aNine.setOpaque(false);
		pieces.add(aNine);

		// Create button posOne and add to pieces
		final JButton posOne = new JButton(new ImageIcon("src/images/blue.png"));
		posOne.setBounds(355, 25, 50, 50);
		posOne.setVisible(true);
		posOne.setOpaque(false);
		posOne.setContentAreaFilled(false);
		posOne.setBorderPainted(false);
		posOne.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posOne.getIcon().equals(bluePiece)) {
					posOne.setIcon(redPiece);
				} else {
					posOne.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posOne);

		// Create button posTwo and add to pieces
		final JButton posTwo = new JButton(new ImageIcon("src/images/blue.png"));
		posTwo.setBounds(695, 25, 50, 50);
		posTwo.setVisible(true);
		posTwo.setOpaque(false);
		posTwo.setContentAreaFilled(false);
		posTwo.setBorderPainted(false);
		posTwo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posTwo.getIcon().equals(bluePiece)) {
					posTwo.setIcon(redPiece);
				} else {
					posTwo.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posTwo);

		// Create button posThree and add to pieces
		final JButton posThree = new JButton(new ImageIcon("src/images/blue.png"));
		posThree.setBounds(1035, 25, 50, 50);
		posThree.setVisible(true);
		posThree.setOpaque(false);
		posThree.setContentAreaFilled(false);
		posThree.setBorderPainted(false);
		posThree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posThree.getIcon().equals(bluePiece)) {
					posThree.setIcon(redPiece);
				} else {
					posThree.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posThree);

		// Create button posFour and add to pieces
		final JButton posFour = new JButton(new ImageIcon("src/images/blue.png"));
		posFour.setBounds(443, 113, 50, 50);
		posFour.setVisible(true);
		posFour.setOpaque(false);
		posFour.setContentAreaFilled(false);
		posFour.setBorderPainted(false);
		posFour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posFour.getIcon().equals(bluePiece)) {
					posFour.setIcon(redPiece);
				} else {
					posFour.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posFour);

		// Create button posFive and add to pieces
		final JButton posFive = new JButton(new ImageIcon("src/images/blue.png"));
		posFive.setBounds(695, 113, 50, 50);
		posFive.setVisible(true);
		posFive.setOpaque(false);
		posFive.setContentAreaFilled(false);
		posFive.setBorderPainted(false);
		posFive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posFive.getIcon().equals(bluePiece)) {
					posFive.setIcon(redPiece);
				} else {
					posFive.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posFive);

		// Create button posSix and add to pieces
		final JButton posSix = new JButton(new ImageIcon("src/images/blue.png"));
		posSix.setBounds(947, 113, 50, 50);
		posSix.setVisible(true);
		posSix.setOpaque(false);
		posSix.setContentAreaFilled(false);
		posSix.setBorderPainted(false);
		posSix.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posSix.getIcon().equals(bluePiece)) {
					posSix.setIcon(redPiece);
				} else {
					posSix.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posSix);

		// Create button posSeven and add to pieces
		final JButton posSeven = new JButton(new ImageIcon("src/images/blue.png"));
		posSeven.setBounds(531, 201, 50, 50);
		posSeven.setVisible(true);
		posSeven.setOpaque(false);
		posSeven.setContentAreaFilled(false);
		posSeven.setBorderPainted(false);
		posSeven.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posSeven.getIcon().equals(bluePiece)) {
					posSeven.setIcon(redPiece);
				} else {
					posSeven.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posSeven);

		// Create button posEight and add to pieces
		final JButton posEight = new JButton(new ImageIcon("src/images/blue.png"));
		posEight.setBounds(695, 201, 50, 50);
		posEight.setVisible(true);
		posEight.setOpaque(false);
		posEight.setContentAreaFilled(false);
		posEight.setBorderPainted(false);
		posEight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posEight.getIcon().equals(bluePiece)) {
					posEight.setIcon(redPiece);
				} else {
					posEight.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posEight);

		// Create button posNine and add to pieces
		final JButton posNine = new JButton(new ImageIcon("src/images/blue.png"));
		posNine.setBounds(859, 201, 50, 50);
		posNine.setVisible(true);
		posNine.setOpaque(false);
		posNine.setContentAreaFilled(false);
		posNine.setBorderPainted(false);
		posNine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posNine.getIcon().equals(bluePiece)) {
					posNine.setIcon(redPiece);
				} else {
					posNine.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posNine);

		// Create button posTen and add to pieces
		final JButton posTen = new JButton(new ImageIcon("src/images/blue.png"));
		posTen.setBounds(355, 365, 50, 50);
		posTen.setVisible(true);
		posTen.setOpaque(false);
		posTen.setContentAreaFilled(false);
		posTen.setBorderPainted(false);
		posTen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posTen.getIcon().equals(bluePiece)) {
					posTen.setIcon(redPiece);
				} else {
					posTen.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posTen);

		// Create button posEleven and add to pieces
		final JButton posEleven = new JButton(new ImageIcon("src/images/blue.png"));
		posEleven.setBounds(443, 365, 50, 50);
		posEleven.setVisible(true);
		posEleven.setOpaque(false);
		posEleven.setContentAreaFilled(false);
		posEleven.setBorderPainted(false);
		posEleven.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posEleven.getIcon().equals(bluePiece)) {
					posEleven.setIcon(redPiece);
				} else {
					posEleven.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posEleven);

		// Create button posTwelve and add to pieces
		final JButton posTwelve = new JButton(new ImageIcon("src/images/blue.png"));
		posTwelve.setBounds(531, 365, 50, 50);
		posTwelve.setVisible(true);
		posTwelve.setOpaque(false);
		posTwelve.setContentAreaFilled(false);
		posTwelve.setBorderPainted(false);
		posTwelve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posTwelve.getIcon().equals(bluePiece)) {
					posTwelve.setIcon(redPiece);
				} else {
					posTwelve.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posTwelve);

		// Create button posThirteen and add to pieces
		final JButton posThirteen = new JButton(
				new ImageIcon("images/blue.png"));
		posThirteen.setBounds(859, 365, 50, 50);
		posThirteen.setVisible(true);
		posThirteen.setOpaque(false);
		posThirteen.setContentAreaFilled(false);
		posThirteen.setBorderPainted(false);
		posThirteen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posThirteen.getIcon().equals(bluePiece)) {
					posThirteen.setIcon(redPiece);
				} else {
					posThirteen.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posThirteen);

		// Create button posFourteen and add to pieces
		final JButton posFourteen = new JButton(
				new ImageIcon("images/blue.png"));
		posFourteen.setBounds(947, 365, 50, 50);
		posFourteen.setVisible(true);
		posFourteen.setOpaque(false);
		posFourteen.setContentAreaFilled(false);
		posFourteen.setBorderPainted(false);
		posFourteen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posFourteen.getIcon().equals(bluePiece)) {
					posFourteen.setIcon(redPiece);
				} else {
					posFourteen.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posFourteen);

		// Create button posFifteen and add to pieces
		final JButton posFifteen = new JButton(new ImageIcon("src/images/blue.png"));
		posFifteen.setBounds(1035, 365, 50, 50);
		posFifteen.setVisible(true);
		posFifteen.setOpaque(false);
		posFifteen.setContentAreaFilled(false);
		posFifteen.setBorderPainted(false);
		posFifteen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posFifteen.getIcon().equals(bluePiece)) {
					posFifteen.setIcon(redPiece);
				} else {
					posFifteen.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posFifteen);

		// Create button posSixteen and add to pieces
		final JButton posSixteen = new JButton(new ImageIcon("src/images/blue.png"));
		posSixteen.setBounds(531, 529, 50, 50);
		posSixteen.setVisible(true);
		posSixteen.setOpaque(false);
		posSixteen.setContentAreaFilled(false);
		posSixteen.setBorderPainted(false);
		posSixteen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posSixteen.getIcon().equals(bluePiece)) {
					posSixteen.setIcon(redPiece);
				} else {
					posSixteen.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posSixteen);

		// Create button posSeventeen and add to pieces
		final JButton posSeventeen = new JButton(new ImageIcon(
				"images/blue.png"));
		posSeventeen.setBounds(695, 529, 50, 50);
		posSeventeen.setVisible(true);
		posSeventeen.setOpaque(false);
		posSeventeen.setContentAreaFilled(false);
		posSeventeen.setBorderPainted(false);
		posSeventeen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posSeventeen.getIcon().equals(bluePiece)) {
					posSeventeen.setIcon(redPiece);
				} else {
					posSeventeen.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posSeventeen);

		// Create button posEighteen and add to pieces
		final JButton posEighteen = new JButton(
				new ImageIcon("images/blue.png"));
		posEighteen.setBounds(859, 529, 50, 50);
		posEighteen.setVisible(true);
		posEighteen.setOpaque(false);
		posEighteen.setContentAreaFilled(false);
		posEighteen.setBorderPainted(false);
		posEighteen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posEighteen.getIcon().equals(bluePiece)) {
					posEighteen.setIcon(redPiece);
				} else {
					posEighteen.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posEighteen);

		// Create button posNineteen and add to pieces
		final JButton posNineteen = new JButton(
				new ImageIcon("images/blue.png"));
		posNineteen.setBounds(443, 617, 50, 50);
		posNineteen.setVisible(true);
		posNineteen.setOpaque(false);
		posNineteen.setContentAreaFilled(false);
		posNineteen.setBorderPainted(false);
		posNineteen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posNineteen.getIcon().equals(bluePiece)) {
					posNineteen.setIcon(redPiece);
				} else {
					posNineteen.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posNineteen);

		// Create button posTwenty and add to pieces
		final JButton posTwenty = new JButton(new ImageIcon("src/images/blue.png"));
		posTwenty.setBounds(695, 617, 50, 50);
		posTwenty.setVisible(true);
		posTwenty.setOpaque(false);
		posTwenty.setContentAreaFilled(false);
		posTwenty.setBorderPainted(false);
		posTwenty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posTwenty.getIcon().equals(bluePiece)) {
					posTwenty.setIcon(redPiece);
				} else {
					posTwenty.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posTwenty);

		// Create button posTwentyOne and add to pieces
		final JButton posTwentyOne = new JButton(new ImageIcon(
				"images/blue.png"));
		posTwentyOne.setBounds(947, 617, 50, 50);
		posTwentyOne.setVisible(true);
		posTwentyOne.setOpaque(false);
		posTwentyOne.setContentAreaFilled(false);
		posTwentyOne.setBorderPainted(false);
		posTwentyOne.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posTwentyOne.getIcon().equals(bluePiece)) {
					posTwentyOne.setIcon(redPiece);
				} else {
					posTwentyOne.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posTwentyOne);

		// Create button posTwentyTwo and add to pieces
		final JButton posTwentyTwo = new JButton(new ImageIcon(
				"images/blue.png"));
		posTwentyTwo.setBounds(355, 705, 50, 50);
		posTwentyTwo.setVisible(true);
		posTwentyTwo.setOpaque(false);
		posTwentyTwo.setContentAreaFilled(false);
		posTwentyTwo.setBorderPainted(false);
		posTwentyTwo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posTwentyTwo.getIcon().equals(bluePiece)) {
					posTwentyTwo.setIcon(redPiece);
				} else {
					posTwentyTwo.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posTwentyTwo);

		// Create button posTwentyThree and add to pieces
		final JButton posTwentyThree = new JButton(new ImageIcon(
				"images/blue.png"));
		posTwentyThree.setBounds(695, 705, 50, 50);
		posTwentyThree.setVisible(true);
		posTwentyThree.setOpaque(false);
		posTwentyThree.setContentAreaFilled(false);
		posTwentyThree.setBorderPainted(false);
		posTwentyThree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posTwentyThree.getIcon().equals(bluePiece)) {
					posTwentyThree.setIcon(redPiece);
				} else {
					posTwentyThree.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posTwentyThree);

		// Create button posTwentyFour and add to pieces
		final JButton posTwentyFour = new JButton(new ImageIcon(
				"images/blue.png"));
		posTwentyFour.setBounds(1035, 705, 50, 50);
		posTwentyFour.setVisible(true);
		posTwentyFour.setOpaque(false);
		posTwentyFour.setContentAreaFilled(false);
		posTwentyFour.setBorderPainted(false);
		posTwentyFour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (posTwentyFour.getIcon().equals(bluePiece)) {
					posTwentyFour.setIcon(redPiece);
				} else {
					posTwentyFour.setIcon(bluePiece);
				}
			}
		});
		pieces.add(posTwentyFour);
	}

	public void createHowTo(final JPanel panel) {
		// Initialize LayeredPane
		final JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1440, 900);
		layeredPane.setLayout(null);
		panel.setOpaque(true);
		panel.setBounds(0, 0, 1440, 900);
		panel.setLayout(null);
		panel.setVisible(true);
		panel.add(layeredPane);

		// Create Background JPanel & Add to LayeredPane on Layer 1
		JPanel background;
		try {
			background = new JPanelWithBackground("src/images/background.jpg");
		} catch (IOException e) {
			e.printStackTrace();
			background = null;
		}
		background.setBounds(0, 0, 1440, 900);
		background.setVisible(true);
		layeredPane.add(background);
		layeredPane.setLayer(background, 1);

		// Create Buttons JPanel and Add to LayeredPane on Layer 2
		JPanel buttons = new JPanel();
		buttons.setLayout(null);
		buttons.setOpaque(false);
		buttons.setBounds(0, 0, 1440, 900);
		buttons.setVisible(true);
		layeredPane.add(buttons);
		layeredPane.setLayer(buttons, 2);

		// Create Title and add to Layer
		JLabel title = new JLabel("How To Play");
		title.setForeground(Color.LIGHT_GRAY);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Coalition", Font.BOLD, 89));
		title.setBounds(0, 75, 1440, 125);
		buttons.add(title);

		// Add previous button to JPanel
		final JButton previous = new JButton("Previous");
		previous.setFont(new Font("Coalition", Font.PLAIN, 40));
		previous.setForeground(Color.WHITE);
		previous.setBounds(20, 775, 325, 65);
		previous.setOpaque(false);
		previous.setContentAreaFilled(false);
		previous.setBorderPainted(false);
		buttons.add(previous);

		// Add back button to JPanel
		final JButton back = new JButton("Back");
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
		back.setBounds(620, 775, 200, 65);
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		buttons.add(back);

		// Add next button to JPanel
		final JButton next = new JButton("Next");
		next.setFont(new Font("Coalition", Font.PLAIN, 40));
		next.setForeground(Color.WHITE);
		next.setBounds(1225, 775, 200, 65);
		next.setContentAreaFilled(false);
		next.setBorderPainted(false);
		buttons.add(next);

		// Create cardPanel and Add to Layered Pane on Layer 3
		final JPanel cardPanel = new JPanel();
		cardPanel.setVisible(true);
		cardPanel.setOpaque(false);
		cardPanel.setBounds(270, 225, 900, 500);
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

	public int getFlyMode() {
		return flyMode;
	}

	public boolean isTimer() {
		return timer;
	}

	public int getSlideNum() {
		return slideNum;
	}

}