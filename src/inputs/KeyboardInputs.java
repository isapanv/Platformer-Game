package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePannel;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			gamePanel.changeYDelta(-15);
			break;
		case KeyEvent.VK_A:
			gamePanel.changeXDelta(-15);
			break;
		case KeyEvent.VK_S:
			gamePanel.changeYDelta(15);
			break;
		case KeyEvent.VK_D:
			gamePanel.changeXDelta(15);
			break;
		}
		
	}

}
