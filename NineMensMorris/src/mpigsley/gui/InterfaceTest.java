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
	private boolean resolution = false;

	private String backL = "src/images/backgroundLarge.jpg";
	private String backS = "src/images/backgroundSmall.jpg";
	private String boardL = "src/images/boardLarge.jpg";
	private String boardS = "src/images/boardSmall.jpg";
	private String blue = "src/images/blue.png";
	private String red = "src/images/red.png";
	private ImageIcon bluePiece = new ImageIcon(blue);
	private ImageIcon redPiece = new ImageIcon(red);
	private ImageIcon off = new ImageIcon("src/images/off.png");
	private ImageIcon on = new ImageIcon("src/images/on.png");
	

	public InterfaceTest() {
		// Initialize contentPane
		contentPane = new JFrame();
		contentPane.setVisible(true);
		contentPane.setSize(new Dimension(1120, 700));
		contentPane.setResizable(false);
		contentPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setLayout(null);

		// Create Main Menu GUI
		createMain();
	}

	public void createMain() {
		// Initialize LayeredPane
		final JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, contentPane.getWidth(),
				contentPane.getHeight());
		layeredPane.setLayout(null);
		this.contentPane.add(layeredPane);

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

		// Create Buttons JPanel & Add to LayerdPane on Layer 1
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		buttons.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
		buttons.setVisible(true);
		buttons.setLayout(null);
		layeredPane.add(buttons);
		layeredPane.setLayer(buttons, 2);

		// Create Title
		JLabel title = new JLabel("NINE MEN'S MORRIS");
		title.setForeground(Color.LIGHT_GRAY);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Coalition", Font.BOLD, 70));
		title.setBounds(0, 86, contentPane.getWidth(), 127);
		buttons.add(title);

		// Create Human vs. Computer Button
		final JButton hvc = new JButton("HUMAN VS. COMPUTER");
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
		hvc.setBounds(0, title.getY() + 145, contentPane.getWidth(), 65);
		hvc.setOpaque(false);
		hvc.setContentAreaFilled(false);
		hvc.setBorderPainted(false);
		buttons.add(hvc);

		// Create Human vs. Human Button
		final JButton hvh = new JButton("HUMAN VS. HUMAN");
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
		hvh.setBounds(0, hvc.getY() + 70, contentPane.getWidth(), 65);
		hvh.setOpaque(false);
		hvh.setContentAreaFilled(false);
		hvh.setBorderPainted(false);
		buttons.add(hvh);

		// Create Leaderboard Button
		final JButton lb = new JButton("LEADERBOARD");
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
		lb.setBounds(0, hvh.getY() + 70, contentPane.getWidth(), 65);
		lb.setOpaque(false);
		lb.setContentAreaFilled(false);
		lb.setBorderPainted(false);
		buttons.add(lb);

		// Create Options Button
		final JButton options = new JButton("OPTIONS");
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
		options.setBounds(0, lb.getY() + 70, contentPane.getWidth(), 65);
		options.setOpaque(false);
		options.setContentAreaFilled(false);
		options.setBorderPainted(false);
		buttons.add(options);

		// Create Exit Button
		final JButton exit = new JButton("EXIT");
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
		exit.setBounds(0, options.getY() + 70, contentPane.getWidth(), 65);
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		buttons.add(exit);
	}

	public void createOptions() {
		// Initialize LayeredPane
		final JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, contentPane.getWidth(),
				contentPane.getHeight());
		layeredPane.setLayout(null);
		this.contentPane.add(layeredPane);

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
		background.setLayout(null);
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

		// Create howToPanel JPanel & Add to LayerdPane on Layer 3
		final JPanel howToPanel = new JPanel();
		howToPanel.setOpaque(false);
		layeredPane.add(howToPanel);
		layeredPane.setLayer(howToPanel, 3);

		// Create Title and add to Layer
		JLabel title = new JLabel("OPTIONS");
		title.setForeground(Color.LIGHT_GRAY);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Coalition", Font.BOLD, 70));
		title.setBounds(0, 86, contentPane.getWidth(), 127);
		buttons.add(title);

		// Create How to Play button & add to Layer
		final JButton howTo = new JButton("HOW TO PLAY");
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
		howTo.setBounds(0, title.getY() + 145, contentPane.getWidth(), 65);
		howTo.setOpaque(false);
		howTo.setContentAreaFilled(false);
		howTo.setBorderPainted(false);
		buttons.add(howTo);

		// Create Fly Mode Title & add to Layer
		JLabel fly = new JLabel("FLY MODE:      3      4      OFF");
		fly.setForeground(Color.WHITE);
		fly.setHorizontalAlignment(SwingConstants.CENTER);
		fly.setFont(new Font("Coalition", Font.BOLD, 40));
		fly.setBounds(0, howTo.getY() + 70, contentPane.getWidth(), 65);
		buttons.add(fly);

		// Create Fly Mode 4 Radio button & add to Layer
		final JButton fMThree = new JButton(off);
		fMThree.setBounds(490, fly.getY() + 14, 30, 30);
		fMThree.setVisible(true);
		fMThree.setOpaque(false);
		fMThree.setContentAreaFilled(false);
		fMThree.setBorderPainted(false);
		buttons.add(fMThree);

		// Create Fly Mode 3 Radio button & add to Layer
		final JButton fMFour = new JButton(off);
		fMFour.setBounds(640, fly.getY() + 14, 30, 30);
		fMFour.setVisible(true);
		fMFour.setOpaque(false);
		fMFour.setContentAreaFilled(false);
		fMFour.setBorderPainted(false);
		buttons.add(fMFour);

		// Create Fly Mode Off Radio button & add to Layer
		final JButton fMOff = new JButton(off);
		fMOff.setBounds(790, fly.getY() + 14, 30, 30);
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
				fMFour.setIcon(off);
				fMOff.setIcon(off);
				// Set to this one to on
				fMThree.setIcon(on);
				// Update global flyMode var
				flyMode = 1;
			}
		});

		fMFour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Set all others to off
				fMThree.setIcon(off);
				fMOff.setIcon(off);
				// Set to this one to on
				fMFour.setIcon(on);
				// Update global flyMode var
				flyMode = 2;
			}
		});

		fMOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Set all others to off
				fMFour.setIcon(off);
				fMThree.setIcon(off);
				// Set to this one to on
				fMOff.setIcon(on);
				// Update global flyMode var
				flyMode = 3;
			}
		});

		// Create Timer Title & add to Layer
		JLabel timerTitle = new JLabel("TIMER:      ON      OFF");
		timerTitle.setForeground(Color.WHITE);
		timerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		timerTitle.setFont(new Font("Coalition", Font.BOLD, 40));
		timerTitle.setBounds(0, fly.getY() + 70, contentPane.getWidth(), 65);
		buttons.add(timerTitle);

		// Create Timer On Radio button & add to Layer
		final JButton tOn = new JButton(off);
		tOn.setBounds(490, timerTitle.getY() + 14, 30, 30);
		tOn.setVisible(true);
		tOn.setOpaque(false);
		tOn.setContentAreaFilled(false);
		tOn.setBorderPainted(false);
		buttons.add(tOn);

		// Create Timer Off Radio button & add to Layer
		final JButton tOff = new JButton(off);
		tOff.setBounds(690, timerTitle.getY() + 14, 30, 30);
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
				tOff.setIcon(off);
				// Set to this one to on
				tOn.setIcon(on);
				// Update global timer var
				timer = true;
			}
		});

		tOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Set all others to off
				tOn.setIcon(off);
				// Set to this one to on
				tOff.setIcon(on);
				// Update global timer var
				timer = false;
			}
		});

		// Create Resolution Title & add to Layer
		JLabel res = new JLabel("GAME RESOLUTION:      HIGH      LOW");
		res.setForeground(Color.WHITE);
		res.setHorizontalAlignment(SwingConstants.CENTER);
		res.setFont(new Font("Coalition", Font.BOLD, 40));
		res.setBounds(0, timerTitle.getY() + 70, contentPane.getWidth(), 65);
		buttons.add(res);

		// Create Timer On Radio button & add to Layer
		final JButton rOn = new JButton(off);
		rOn.setBounds(620, res.getY() + 14, 30, 30);
		rOn.setVisible(true);
		rOn.setOpaque(false);
		rOn.setContentAreaFilled(false);
		rOn.setBorderPainted(false);
		buttons.add(rOn);

		// Create Timer Off Radio button & add to Layer
		final JButton rOff = new JButton(off);
		rOff.setBounds(865, res.getY() + 14, 30, 30);
		rOff.setVisible(true);
		rOff.setOpaque(false);
		rOff.setContentAreaFilled(false);
		rOff.setBorderPainted(false);
		buttons.add(rOff);

		// If timer buttons are pressed...
		rOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Set other to off
				rOff.setIcon(off);
				// Set to this one to on
				rOn.setIcon(on);
				// Update global timer var
				resolution = true;
			}
		});

		rOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Set all others to off
				rOn.setIcon(off);
				// Set to this one to on
				rOff.setIcon(on);
				// Update global timer var
				resolution = false;
			}
		});

		// Create Main Menu button & add to Layer
		final JButton main = new JButton("MAIN MENU");
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
		main.setBounds(0, res.getY() + 70, contentPane.getWidth(), 65);
		main.setOpaque(false);
		main.setContentAreaFilled(false);
		main.setBorderPainted(false);
		buttons.add(main);

		// Set Default Settings for Options Menu
		if (timer) {
			tOn.setIcon(on);
		} else {
			tOff.setIcon(on);
		}
		if (flyMode == 1) {
			fMThree.setIcon(on);
		} else if (flyMode == 2) {
			fMFour.setIcon(on);
		} else {
			fMOff.setIcon(on);
		}
		if (resolution) {
			rOn.setIcon(on);
		} else {
			rOff.setIcon(on);
		}

	}

	public void createGame() {
		// Initialize LayeredPane
		final JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
		layeredPane.setLayout(null);
		this.contentPane.add(layeredPane);

		// Create Background JPanel & Add to LayeredPane on Layer 1
		JPanel background;
		try {
			background = new JPanelWithBackground(boardS);
		} catch (IOException e) {
			e.printStackTrace();
			background = null;
		}
		background.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
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
		final JButton menu = new JButton("MENU");
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
				dim.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
				dim.setVisible(true);
				dim.setOpaque(false);
				dim.setLayout(null);
				layeredPane.add(dim);
				layeredPane.setLayer(dim, 3);

				// Create Menu with and add as layer 4
				final JPanel menu = new JPanel();
				menu.setBounds(((contentPane.getWidth() -550) / 2), ((contentPane.getHeight() - 600) / 2) - 10, 550, 600);
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
						createHowTo(howToPanel);
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
						// Remove Current Screen
						contentPane.remove(layeredPane);

						// Open Main Menu
						createMain();
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
						dim.setVisible(false);
						menu.setVisible(false);
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
		});
		menu.setFont(new Font("Coalition", Font.PLAIN, 40));
		menu.setForeground(Color.WHITE);
		menu.setBounds(contentPane.getWidth() - 220,
				contentPane.getHeight() - 100, 200, 65);
		menu.setOpaque(false);
		menu.setContentAreaFilled(false);
		menu.setBorderPainted(false);
		buttons.add(menu);

		// Add Player One Name JLabel and add to JPanel
		JLabel pOne = new JLabel("PLAYER 1");
		pOne.setForeground(Color.LIGHT_GRAY);
		pOne.setHorizontalAlignment(SwingConstants.CENTER);
		pOne.setFont(new Font("Coalition", Font.BOLD, 30));
		pOne.setBounds(39, 233, 218, 51);
		buttons.add(pOne);

		// Add Player Two Name JLabel and add to JPanel
		JLabel pTwo = new JLabel("PLAYER 2");
		pTwo.setForeground(Color.LIGHT_GRAY);
		pTwo.setHorizontalAlignment(SwingConstants.CENTER);
		pTwo.setFont(new Font("Coalition", Font.BOLD, 30));
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
		JPanel bOne;
		try {
			bOne = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bOne = null;
		}
		bOne.setBounds(50, 350, 50, 50);
		bOne.setVisible(true);
		bOne.setOpaque(false);
		pieces.add(bOne);

		// Create bTwo & add the pieces
		JPanel bTwo;
		try {
			bTwo = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bTwo = null;
		}
		bTwo.setBounds(bOne.getX() + 50, bOne.getY(), 50, 50);
		bTwo.setVisible(true);
		bTwo.setOpaque(false);
		pieces.add(bTwo);

		// Create bThree & add the pieces
		JPanel bThree;
		try {
			bThree = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bThree = null;
		}
		bThree.setBounds(bTwo.getX() + 50, bTwo.getY(), 50, 50);
		bThree.setVisible(true);
		bThree.setOpaque(false);
		pieces.add(bThree);

		// Create bFour & add the pieces
		JPanel bFour;
		try {
			bFour = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bFour = null;
		}
		bFour.setBounds(bThree.getX() + 50, bThree.getY(), 50, 50);
		bFour.setVisible(true);
		bFour.setOpaque(false);
		pieces.add(bFour);

		// Create bFive & add the pieces
		JPanel bFive;
		try {
			bFive = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bFive = null;
		}
		bFive.setBounds(bOne.getX() - (bOne.getWidth() / 2), bFour.getY() + 50, 50, 50);
		bFive.setVisible(true);
		bFive.setOpaque(false);
		pieces.add(bFive);

		// Create bSix & add the pieces
		JPanel bSix;
		try {
			bSix = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bSix = null;
		}
		bSix.setBounds(bFive.getX() + 50, bFive.getY(), 50, 50);
		bSix.setVisible(true);
		bSix.setOpaque(false);
		pieces.add(bSix);

		// Create bSeven & add the pieces
		JPanel bSeven;
		try {
			bSeven = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bSeven = null;
		}
		bSeven.setBounds(bSix.getX() + 50, bSix.getY(), 50, 50);
		bSeven.setVisible(true);
		bSeven.setOpaque(false);
		pieces.add(bSeven);

		// Create bEight & add the pieces
		JPanel bEight;
		try {
			bEight = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bEight = null;
		}
		bEight.setBounds(bSeven.getX() + 50, bSeven.getY(), 50, 50);
		bEight.setVisible(true);
		bEight.setOpaque(false);
		pieces.add(bEight);

		// Create bNine & add the pieces
		JPanel bNine;
		try {
			bNine = new JPanelWithBackground(blue);
		} catch (IOException e) {
			e.printStackTrace();
			bNine = null;
		}
		bNine.setBounds(bEight.getX() + 50, bEight.getY(), 50, 50);
		bNine.setVisible(true);
		bNine.setOpaque(false);
		pieces.add(bNine);

		// Create aOne & add the pieces
		JPanel aOne;
		try {
			aOne = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			aOne = null;
		}
		aOne.setBounds(contentPane.getWidth() - 250, bOne.getY(), 50, 50);
		aOne.setVisible(true);
		aOne.setOpaque(false);
		pieces.add(aOne);

		// Create aTwo & add the pieces
		JPanel aTwo;
		try {
			aTwo = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			aTwo = null;
		}
		aTwo.setBounds(aOne.getX() + 50, aOne.getY(), 50, 50);
		aTwo.setVisible(true);
		aTwo.setOpaque(false);
		pieces.add(aTwo);

		// Create aThree & add the pieces
		JPanel aThree;
		try {
			aThree = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			aThree = null;
		}
		aThree.setBounds(aTwo.getX() + 50, aTwo.getY(), 50, 50);
		aThree.setVisible(true);
		aThree.setOpaque(false);
		pieces.add(aThree);

		// Create aFour & add the pieces
		JPanel aFour;
		try {
			aFour = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			aFour = null;
		}
		aFour.setBounds(aThree.getX() + 50, aThree.getY(), 50, 50);
		aFour.setVisible(true);
		aFour.setOpaque(false);
		pieces.add(aFour);

		// Create aFive & add the pieces
		JPanel aFive;
		try {
			aFive = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			aFive = null;
		}
		aFive.setBounds(aOne.getX() - (aOne.getWidth() / 2), aFour.getY() + 50, 50, 50);
		aFive.setVisible(true);
		aFive.setOpaque(false);
		pieces.add(aFive);

		// Create aSix & add the pieces
		JPanel aSix;
		try {
			aSix = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			aSix = null;
		}
		aSix.setBounds(aFive.getX() + 50, aFive.getY(), 50, 50);
		aSix.setVisible(true);
		aSix.setOpaque(false);
		pieces.add(aSix);

		// Create aSeven & add the pieces
		JPanel aSeven;
		try {
			aSeven = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			aSeven = null;
		}
		aSeven.setBounds(aSix.getX() + 50, aSix.getY(), 50, 50);
		aSeven.setVisible(true);
		aSeven.setOpaque(false);
		pieces.add(aSeven);

		// Create aEight & add the pieces
		JPanel aEight;
		try {
			aEight = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			aEight = null;
		}
		aEight.setBounds(aSeven.getX() + 50, aSeven.getY(), 50, 50);
		aEight.setVisible(true);
		aEight.setOpaque(false);
		pieces.add(aEight);

		// Create bNine & add the pieces
		JPanel aNine;
		try {
			aNine = new JPanelWithBackground(red);
		} catch (IOException e) {
			e.printStackTrace();
			aNine = null;
		}
		aNine.setBounds(aEight.getX() + 50, aSeven.getY(), 50, 50);
		aNine.setVisible(true);
		aNine.setOpaque(false);
		pieces.add(aNine);

		// Create button posOne and add to pieces
		final JButton posOne = new JButton(bluePiece);
		posOne.setBounds(270, 12, 50, 50);
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
		final JButton posTwo = new JButton(bluePiece);
		posTwo.setBounds(posOne.getX() + 264, posOne.getY(), 50, 50);
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
		final JButton posThree = new JButton(bluePiece);
		posThree.setBounds(posTwo.getX() + 264, posOne.getY(), 50, 50);
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
		final JButton posFour = new JButton(bluePiece);
		posFour.setBounds(posOne.getX() + 70, posOne.getY() + 70, 50, 50);
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
		final JButton posFive = new JButton(bluePiece);
		posFive.setBounds(posTwo.getX(), posFour.getY(), 50, 50);
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
		final JButton posSix = new JButton(bluePiece);
		posSix.setBounds(posThree.getX() - 70, posFour.getY(), 50, 50);
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
		final JButton posSeven = new JButton(bluePiece);
		posSeven.setBounds(posFour.getX() + 70, posFour.getY() + 70, 50, 50);
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
		final JButton posEight = new JButton(bluePiece);
		posEight.setBounds(posFive.getX(), posSeven.getY(), 50, 50);
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
		final JButton posNine = new JButton(bluePiece);
		posNine.setBounds(posSix.getX() - 70, posEight.getY(), 50, 50);
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
		final JButton posTen = new JButton(bluePiece);
		posTen.setBounds(posOne.getX(), posOne.getY() + 265, 50, 50);
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
		final JButton posEleven = new JButton(bluePiece);
		posEleven.setBounds(posFour.getX(), posTen.getY(), 50, 50);
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
		final JButton posTwelve = new JButton(bluePiece);
		posTwelve.setBounds(posSeven.getX(), posEleven.getY(), 50, 50);
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
		final JButton posThirteen = new JButton(bluePiece);
		posThirteen.setBounds(posNine.getX(), posTwelve.getY(), 50, 50);
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
		final JButton posFourteen = new JButton(bluePiece);
		posFourteen.setBounds(posSix.getX(), posThirteen.getY(), 50, 50);
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
		final JButton posFifteen = new JButton(bluePiece);
		posFifteen.setBounds(posThree.getX(), posFourteen.getY(), 50, 50);
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
		final JButton posSixteen = new JButton(bluePiece);
		posSixteen.setBounds(posTwelve.getX(), posTwelve.getY() + (posTwelve.getY() - posSeven.getY()) , 50, 50);
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
		final JButton posSeventeen = new JButton(bluePiece);
		posSeventeen.setBounds(posEight.getX(), posSixteen.getY(), 50, 50);
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
		final JButton posEighteen = new JButton(bluePiece);
		posEighteen.setBounds(posThirteen.getX(), posSeventeen.getY(), 50, 50);
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
		final JButton posNineteen = new JButton(bluePiece);
		posNineteen.setBounds(posEleven.getX(), posSixteen.getY() + 70, 50, 50);
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
		final JButton posTwenty = new JButton(bluePiece);
		posTwenty.setBounds(posSeventeen.getX(), posNineteen.getY(), 50, 50);
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
		final JButton posTwentyOne = new JButton(bluePiece);
		posTwentyOne.setBounds(posFourteen.getX(), posTwenty.getY(), 50, 50);
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
		final JButton posTwentyTwo = new JButton(bluePiece);
		posTwentyTwo.setBounds(posTen.getX(), posNineteen.getY() + 70, 50, 50);
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
		final JButton posTwentyThree = new JButton(bluePiece);
		posTwentyThree.setBounds(posTwenty.getX(), posTwentyTwo.getY(), 50, 50);
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
		final JButton posTwentyFour = new JButton(bluePiece);
		posTwentyFour.setBounds(posFifteen.getX(), posTwentyThree.getY(), 50, 50);
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
