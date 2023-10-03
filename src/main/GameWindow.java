package main;
import javax.swing.JFrame;
public class GameWindow {
	private JFrame jframe;
	public GameWindow(GamePannel gamePanel) {
		jframe = new JFrame();
		jframe.setSize(800, 600);
		//jframe.setSize(1920, 1080);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
	}
}