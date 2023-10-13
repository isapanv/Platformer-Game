package gamestats;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverLey;

public class Playing extends State implements StateMethods {
	private Player player;
	private LevelManager levelManager;
	private PauseOverLey pauseOverLey;
	private boolean paused = true; 

	public Playing(Game game) {
		super(game);
		init(); 
	}

	private void init() {
		levelManager = new LevelManager(game);
		player = new Player(200, 200, (int)(64 * Game.SCALE), (int)(64 * Game.SCALE));
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
		pauseOverLey = new PauseOverLey(this);
	}
	public Player getPlayer() {
		return player;
	}

	public void winFocusLost() {
		player.resetDirs();
		
	}

	@Override
	public void update() {
		if (!paused) {
			levelManager.update();
			player.updatePlayer();
		} else {
			pauseOverLey.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g);
		player.renderPlayer(g);
		if(paused)
		pauseOverLey.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			player.setAttacking(true);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A, KeyEvent.VK_LEFT:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_BACK_SPACE:
			GameState.state = GameState.MENU;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_A, KeyEvent.VK_LEFT:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}

	}
	public void unpauseGame() {
		paused = false;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (paused)
			pauseOverLey.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused)
			pauseOverLey.mouseReleased(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused)
			pauseOverLey.mouseMoved(e);
	}

	public void windowFocusLost() {
		player.resetDirs();
	}

	public void mouseDragged(MouseEvent e) {
		if(paused) {
			pauseOverLey.mouseDragged(e);
		}
		
	}


}