package Dev;
import java.awt.*;
import javax.swing.*;


public class GameBoard implements Runnable{
	private JFrame board;
	public void run() {
		initComponents();
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setTitle("Nine Men's Morris");
		board.setResizable(false);
		board.getContentPane().setPreferredSize(board.getSize());
		board.pack();
		board.setVisible(true);
		board.setName("Board");
	}
	
	private void initComponents() {
		// Create the window
		board = new JFrame();
		//GroupLayout layout = new GroupLayout(board);
		board.setLayout(new FlowLayout());
		board.setSize(700, 450);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(board.getSize());

        //layeredPane.addMouseMotionListener(board.getMouseMotionListeners()[0]);
		JLabel lbl = new JLabel(new ImageIcon("boardimg.jpg"));
		
		//This is the offset for computing the origin for the next label.
        int offset = 150;
        Point origin = new Point(205, 225);
        lbl.setVerticalAlignment(JLabel.TOP);
        lbl.setHorizontalAlignment(JLabel.CENTER);
        lbl.setOpaque(true);
        lbl.setBounds(0, 0, board.getSize().width, board.getSize().height);
        layeredPane.add(lbl, new Integer(0));
        
        JLabel label = new JLabel("Hello, world!");
        label.setBounds(origin.x, origin.y, 140, 35);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
		
        
        layeredPane.add(label, new Integer(1));
        
        origin.x += offset;
        
		JButton button = new JButton("Press me!");
		button.setVerticalAlignment(JLabel.CENTER);
		button.setHorizontalAlignment(JLabel.CENTER);
		button.setOpaque(true);
		button.setBounds(origin.x, origin.y, 140, 35);
		layeredPane.add(button, new Integer(2));
        
		board.add(layeredPane);
	}
	
	public static void main(String[] args) {
		GameBoard se = new GameBoard();
        // Schedules the application to be run at the correct time in the event queue.
        SwingUtilities.invokeLater(se);
    }
}
