package main;
import java.awt.Graphics;
import javax.swing.JPanel;
public class GamePannel extends JPanel{
	public GamePannel() {
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(100, 100, 200, 50);
	}
}

