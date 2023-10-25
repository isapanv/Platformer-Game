package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestats.GameState;
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
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			
			GameState.state = GameState.MENU;
		}
		switch(GameState.state) {
		case MENU:
			gamePanel.getGame().getMenu().keyPressed(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().keyPressed(e);
			break;
		default:
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(GameState.state) {
		case MENU:
			gamePanel.getGame().getMenu().keyReleased(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().keyReleased(e);
			break;
		default:
			break;
		
		}
	}
}