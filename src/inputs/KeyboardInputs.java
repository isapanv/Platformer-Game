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
			gamePanel.getGame().getPlayer().setUp(true);
			break;
		case KeyEvent.VK_A, KeyEvent.VK_LEFT:
			gamePanel.getGame().getPlayer().setLeft(true);
			break;
		case KeyEvent.VK_S, KeyEvent.VK_DOWN:
			gamePanel.getGame().getPlayer().setDown(true);
			break;
		case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
			gamePanel.getGame().getPlayer().setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			gamePanel.getGame().getPlayer().setJump(true);
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W, KeyEvent.VK_UP:
			gamePanel.getGame().getPlayer().setUp(false);
			break;
		case KeyEvent.VK_A, KeyEvent.VK_LEFT:
			gamePanel.getGame().getPlayer().setLeft(false);
			break;
		case KeyEvent.VK_S, KeyEvent.VK_DOWN:
			gamePanel.getGame().getPlayer().setDown(false);
			break;
		case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
			gamePanel.getGame().getPlayer().setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			gamePanel.getGame().getPlayer().setJump(false);
			break;
		}
	}
}