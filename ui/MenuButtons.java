package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static utilities.Constants.UI.Buttons.*;
import gamestats.GameState;
import utilities.LoadSave;

public class MenuButtons {

	
	private int xPos, yPos, rowIndex, index;
	private int xOffsetCenter = B_WIDTH / 2;
	private GameState state;
	private Rectangle bounds;
	private BufferedImage[] images;
	private boolean mouseOver, mousePressed;
	public MenuButtons(int xPos, int yPos, int rowIndex, GameState state)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.rowIndex = rowIndex;
		this.state = state;
		loadImages();
		initBounds();
	}
	private void initBounds() {
		bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);		
	}
	private void loadImages() {
		images = new BufferedImage[3];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
		for (int i = 0; i < images.length; i++)
			images[i] = temp.getSubimage(i * B_WIDTH_DEF, rowIndex * B_HEIGHT_DEF, B_WIDTH_DEF, B_HEIGHT_DEF);
	
	}
	public void draw(Graphics g) {
		g.drawImage(images[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
	}
	public void update() {
		index = 0;
		if (mouseOver)
			index = 1;
		if (mousePressed)
			index = 2;
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
	public void applyGamestate() {
		GameState.state = state;
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
	public Rectangle getBounds() {
		return bounds;
	}

	
	
}