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
import javax.swing.SwingConstants;

import mpigsley.gui.JPanelWithBackground;

public class MenuGUI implements MenuInterface {
	private Options myOptions;
	private JFrame contentPane;
	private boolean isGameReady;
	private int slideNum;
	private int flyMode;
	private boolean timer;
	private boolean resolution;

	// How To Slides
	private JPanel[] slides;

	// private String backL = "src/images/backgroundLarge.jpg";
	private String backS = "src/images/backgroundSmall.jpg";
	private ImageIcon off = new ImageIcon("src/images/off.png");
	private ImageIcon on = new ImageIcon("src/images/on.png");

	public MenuGUI(JFrame contentPane) {
		// Initialize variables
		this.contentPane = contentPane;
		this.myOptions = new Options();
		isGameReady = false;
		slideNum = 1;
		flyMode = 1;
		timer = false;
		resolution = false;

		// Draw Main Menu
		drawMenu();
	}

	public MenuGUI(JFrame contentPane, Options lastOptions) {
		// Initialize variables
		this.contentPane = contentPane;
		this.myOptions = new Options();
		isGameReady = false;
		slideNum = 1;
		flyMode = lastOptions.getFlyRule();
		timer = lastOptions.getTimer();
		resolution = lastOptions.getGameRes();

		// Draw Main Menu
		drawMenu();
	}

	public void drawMenu() {
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
		title.setFont(new Font("Coalition", Font.PLAIN, 70));
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
				// Clear contentPane
				contentPane.remove(layeredPane);

				// Update Options
				myOptions.setComputerPlayer(true);

				// Set isGameReady
				isGameReady = true;
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
				// Clear contentPane
				contentPane.remove(layeredPane);

				// Update Options
				myOptions.setComputerPlayer(false);

				// Set isGameReady
				isGameReady = true;
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
				drawOptions();
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

	public void drawOptions() {
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
				drawHowTo(howToPanel);
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
				myOptions.setFlyRule(1);
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
				myOptions.setFlyRule(2);
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
				myOptions.setFlyRule(3);
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
				myOptions.setTimer(true);
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
				myOptions.setTimer(false);
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
				myOptions.setGameRes(true);
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
				myOptions.setGameRes(false);
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
				drawMenu();
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
			cardPanel.add(slides[i], "" + (i + 1));
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

	public void drawLeaderboards() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Options getOptions() {
		return myOptions;
	}

	@Override
	public void setOptions(Options myOptions) {
		this.myOptions = myOptions;
	}

	@Override
	public boolean isGameReady() {
		return isGameReady;
	}

}
