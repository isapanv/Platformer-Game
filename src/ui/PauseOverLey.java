package ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestats.GameState;
import gamestats.Playing;
import utilities.Constants;
import main.Game;
import utilities.LoadSave;
import static utilities.Constants.UI.PauseButtons.*;
import static utilities.Constants.UI.VolumeButtons.*;
import static utilities.Constants.UI.FUNC.*;

public class PauseOverLey {
	private Playing playing;
	private BufferedImage background;
	private int bgX, bgY, bgW, bgH;
	private FuncButton menuButton, replayButton, unpauseButton;
	private AudioOptions audioOptions;
	public PauseOverLey(Playing playing) {
		this.playing = playing;
		loadBackground();
		audioOptions = playing.getGame().getAudioOptions();
		createFuncButtons();
	}

	private void createFuncButtons() {
		int menuX = (int) (313 * Game.SCALE);
		int replayX = (int) (387 * Game.SCALE);
		int unpauseX = (int) (462 * Game.SCALE);
		int bY = (int) (325 * Game.SCALE);

		menuButton = new FuncButton(menuX, bY, FUNC_SIZE, FUNC_SIZE, 2);
		replayButton = new FuncButton(replayX, bY, FUNC_SIZE, FUNC_SIZE, 1);
		unpauseButton = new FuncButton(unpauseX, bY, FUNC_SIZE, FUNC_SIZE, 0);
		
	}

	private void loadBackground() {
		background = LoadSave.GetSpriteAtlas(LoadSave.PAUSE);
		bgW = (int)(background.getWidth() * Game.SCALE);
		bgH = (int)(background.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int)(25 * Game.SCALE);
	}
	public void update() {

		menuButton.update();
		replayButton.update();
		unpauseButton.update();
		AudioOptions.update();
	}
	public void draw(Graphics g) {
		// background
		g.drawImage(background, bgX, bgY, bgW, bgH, null);

		// functions buttons
		menuButton.draw(g);
		replayButton.draw(g);
		unpauseButton.draw(g);
		
		audioOptions.draw(g);
	}

	public void mouseDragged(MouseEvent e) {
		audioOptions.mouseDragged(e);
	}

	public void mousePressed(MouseEvent e) {
		if (isin(e, menuButton))
			menuButton.setMousePressed(true);
		else if (isin(e, replayButton))
			replayButton.setMousePressed(true);
		else if (isin(e, unpauseButton))
			unpauseButton.setMousePressed(true);
		else
			audioOptions.setMousePressed(true);
	}

	public void mouseReleased(MouseEvent e) {
		if (isin(e, menuButton)) {
				if (menuButton.isMousePressed()) {
					GameState.state = GameState.MENU;
					playing.unpauseGame();
				}
		} else if (isin(e, replayButton)) {
				if (replayButton.isMousePressed()) {
					playing.resetAll();
					playing.unpauseGame();
				}
		} else if (isin(e, unpauseButton)) {
				if (unpauseButton.isMousePressed()) {
					playing.unpauseGame();
				}
		}else {
			audioOptions.mouseReleased(e);
		}
		
		menuButton.resetBools();
		replayButton.resetBools();
		unpauseButton.resetBools();
	}
	
	public void mouseMoved(MouseEvent e) {
		menuButton.setMouseOver(false);
		replayButton.setMouseOver(false);
		unpauseButton.setMouseOver(false);
		if (isin(e, menuButton)) {
			menuButton.setMouseOver(true);}
		else if (isin(e, replayButton)) {
			replayButton.setMouseOver(true);}
		else if (isin(e, unpauseButton)) {
			unpauseButton.setMouseOver(true);}
		else {
			audioOptions.setMouseOver(true);}
	}
	
	private boolean isin(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
}