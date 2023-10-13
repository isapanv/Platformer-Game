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
	private SoundButton musicButton, sfxButton;
	private FuncButton menuButton, replayButton, unpauseButton;
	private VolumeButton volumeButton;
	public PauseOverLey(Playing playing) {
		this.playing = playing;
		loadBackground();
		createSoundButtons();
		createFuncButtons();
		createVolumeButton();
	}
	private void createVolumeButton() {
		int vX = (int) (309 * Game.SCALE);
		int vY = (int) (278 * Game.SCALE);
		volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
		
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
	private void createSoundButtons() {
		int soundX = (int)(450 * Game.SCALE);
		int musicY = (int)(140 * Game.SCALE);
		int sfxY = (int)(186 * Game.SCALE);
		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
	}
	private void loadBackground() {
		background = LoadSave.GetSpriteAtlas(LoadSave.PAUSE);
		bgW = (int)(background.getWidth() * Game.SCALE);
		bgH = (int)(background.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int)(25 * Game.SCALE);
	}
	public void update() {
		musicButton.update();
		sfxButton.update();
		menuButton.update();
		replayButton.update();
		unpauseButton.update();
		volumeButton.update();
	}
	public void draw(Graphics g) {
		// background
		g.drawImage(background, bgX, bgY, bgW, bgH, null);
		// sound buttons
		musicButton.draw(g);
		sfxButton.draw(g);
		// functions buttons
		menuButton.draw(g);
		replayButton.draw(g);
		unpauseButton.draw(g);
		volumeButton.draw(g);
	}

	public void mouseDragged(MouseEvent e) {
		if (volumeButton.isMousePressed()) {
			volumeButton.changeX(e.getX());
		}
	}

	public void mousePressed(MouseEvent e) {
		if (isin(e,musicButton))
			musicButton.setMousePressed(true);
		else if (isin(e,sfxButton))
			sfxButton.setMousePressed(true);
		else if (isin(e, menuButton))
			menuButton.setMousePressed(true);
		else if (isin(e, replayButton))
			replayButton.setMousePressed(true);
		else if (isin(e, unpauseButton))
			unpauseButton.setMousePressed(true);
		else if (isin(e, volumeButton))
			volumeButton.setMousePressed(true);
	}

	public void mouseReleased(MouseEvent e) {
		if (isin(e,musicButton)) {
			if (musicButton.isMousePressed()) {
				musicButton.setMuted(!musicButton.isMuted());
			}
		}else if (isin(e,sfxButton)) {
			if (sfxButton.isMousePressed()) {
				sfxButton.setMuted(!sfxButton.isMuted());
			}
		}else if (isin(e, menuButton)) {
				if (menuButton.isMousePressed()) {
					GameState.state = GameState.MENU;
					playing.unpauseGame();
				}
		} else if (isin(e, replayButton)) {
				if (replayButton.isMousePressed())
					System.out.println("replay lvl!");
		} else if (isin(e, unpauseButton)) {
				if (unpauseButton.isMousePressed()) {
					playing.unpauseGame();
				}
		}
		musicButton.resetBools();
		sfxButton.resetBools();
		menuButton.resetBools();
		replayButton.resetBools();
		unpauseButton.resetBools();
		volumeButton.resetBools();
	}
	
	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		menuButton.setMouseOver(false);
		replayButton.setMouseOver(false);
		unpauseButton.setMouseOver(false);
		volumeButton.setMouseOver(false);
		if (isin(e,musicButton)) {
			musicButton.setMouseOver(true);}
		else if (isin(e,sfxButton)) {
			sfxButton.setMouseOver(true);}
		else if (isin(e, menuButton)) {
			menuButton.setMouseOver(true);}
		else if (isin(e, replayButton)) {
			replayButton.setMouseOver(true);}
		else if (isin(e, unpauseButton)) {
			unpauseButton.setMouseOver(true);}
		else if (isin(e, volumeButton)) {
			volumeButton.setMouseOver(true);}
	}
	
	private boolean isin(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
}