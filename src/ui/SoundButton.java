package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilities.LoadSave;
import static utilities.Constants.UI.PauseButtons.*;

public class SoundButton extends PauseButton{
	private BufferedImage[][] soundImg;
	private boolean mouseOver, mousePressed;
	private boolean muted;
	private int rowIndex, colIndex;
	public SoundButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		loadSoundImg();
	}
	private void loadSoundImg() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND);
		soundImg = new BufferedImage[2][3];
		for (int j = 0; j < soundImg.length; ++j)
			for (int i = 0; i < soundImg[j].length; ++i)
				soundImg[j][i] = temp.getSubimage(i * SOUND_SIZE_DEF, j * SOUND_SIZE_DEF, SOUND_SIZE_DEF, SOUND_SIZE_DEF);
	}
	public void update() {
		if (muted)
			rowIndex = 1;
		else 
			rowIndex = 0;
		colIndex = 0;
		if (mouseOver)
			colIndex = 1;
		if (mousePressed)
			colIndex = 2;
		
	}
	public void draw(Graphics g) {
		g.drawImage(soundImg[rowIndex][colIndex], x, y, width, height, null);
	}
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;		
	}
	public boolean isMouseOver() {
		return mouseOver;
	}
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	public boolean isMousePressed() {
		return mousePressed;
	}
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	public boolean isMuted() {
		return muted;
	}
	public void setMuted(boolean muted) {
		this.muted = muted;
	}
	
}
