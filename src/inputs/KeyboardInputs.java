package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePannel;
import static utilities.Constants.Directions.*;

public class KeyboardInputs implements KeyListener{

	private GamePannel gamePanel;
	
	public KeyboardInputs(GamePannel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W, KeyEvent.VK_UP:
			gamePanel.setDirection(UP);
			break;
		case KeyEvent.VK_A, KeyEvent.VK_LEFT:
			gamePanel.setDirection(LEFT);
			break;
		case KeyEvent.VK_S, KeyEvent.VK_DOWN:
			gamePanel.setDirection(DOWN);
			break;
		case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
			gamePanel.setDirection(RIGHT);
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W, KeyEvent.VK_UP:
		case KeyEvent.VK_A, KeyEvent.VK_LEFT:
		case KeyEvent.VK_S, KeyEvent.VK_DOWN:
		case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
			gamePanel.setMoving(false);
			break;
		}

	}
}