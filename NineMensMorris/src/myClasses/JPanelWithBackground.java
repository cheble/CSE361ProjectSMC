package myClasses;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JPanelWithBackground extends JPanel {

	private Image backgroundImage;

	public JPanelWithBackground(String fileName) throws IOException {
		backgroundImage = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(fileName));
				//ImageIO.read(new File(fileName));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw the background image.
		g.drawImage(backgroundImage, 0, 0, null);
	}
}
