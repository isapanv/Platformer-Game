package ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestats.GameState;
import main.Game;
import utilities.LoadSave;
import static utilities.Constants.UI.PauseButtons.*;

public class PauseOverLey {
	private BufferedImage background;
	private int bgX, bgY, bgW, bgH;
	private SoundButton musicButton, sfxButton;
	public PauseOverLey() {
		loadBackground();
		createSoundButtons();
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
	}
	public void draw(Graphics g) {
		// background
		g.drawImage(background, bgX, bgY, bgW, bgH, null);
		// sound buttons
		musicButton.draw(g);
		sfxButton.draw(g);
	}

	public void mousePressed(MouseEvent e) {
		if (isin(e,musicButton))
			musicButton.setMousePressed(true);
		else if (isin(e,sfxButton))
			sfxButton.setMousePressed(true);
	}

	public void mouseReleased(MouseEvent e) {
		if (isin(e,musicButton)) {
			if (musicButton.isMousePressed()) {
				musicButton.setMuted(!musicButton.isMuted());
			}
		}
		else if (isin(e,sfxButton))
			if (sfxButton.isMousePressed()) {
				sfxButton.setMuted(!sfxButton.isMuted());
			}
		musicButton.resetBools();
		sfxButton.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		if (isin(e,musicButton))
			musicButton.setMouseOver(true);
		else if (isin(e,sfxButton))
			sfxButton.setMouseOver(true);
	}
	private boolean isin(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
}
