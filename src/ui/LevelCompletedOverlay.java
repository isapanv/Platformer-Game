package ui;

import static utilities.Constants.UI.FUNC.FUNC_SIZE;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestats.GameState;
import gamestats.Playing;
import main.Game;
import utilities.LoadSave;
import utilities.Constants.UI.FUNC;

public class LevelCompletedOverlay {
	
	private Playing playing;
	private FuncButton menu, next;
	private BufferedImage img;
	private int bgX, bgY, bgW, bgH; 
	public LevelCompletedOverlay(Playing playing) {
		this.playing = playing;
		initImg();
		initButtons();
	}
	private void initButtons() {
		int menuX = (int)(330 * Game.SCALE);
		int nextX = (int)(445 * Game.SCALE);
		int y = (int)(195 * Game.SCALE);
		next = new FuncButton(nextX,y,FUNC_SIZE, FUNC_SIZE, 0);
		menu = new FuncButton(menuX,y,FUNC_SIZE, FUNC_SIZE, 2);
	}
	private void initImg() {
		img = LoadSave.GetSpriteAtlas(LoadSave.COMPLETED_IMG);
		bgW = (int)(img.getWidth()*Game.SCALE);
		bgH = (int)(img.getHeight()*Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int)(75*Game.SCALE);
	}
	public void draw(Graphics g) {
		g.drawImage(img, bgX, bgY, bgW, bgH, null);
		next.draw(g);
		menu.draw(g);
	}
	public void update() {
		next.update();
		menu.update();
	}
	private boolean isIn(FuncButton b, MouseEvent e) {
		return b.getBounds().contains(e.getX(),e.getY());
	}
	public void MouseMoved(MouseEvent e) {
		next.setMouseOver(false);
		menu.setMouseOver(false);
		if (isIn(menu,e))
			menu.setMouseOver(true);
		else if (isIn(next,e))
			next.setMouseOver(true);
	}
	public void MouseReleased(MouseEvent e) {
		if (isIn(menu,e)) {
			if (menu.isMousePressed()) {
				playing.resetAll();
				GameState.state = GameState.MENU;
			}
		}
		else if (isIn(next,e))
			if (next.isMousePressed()) {
				playing.loadNextLevel();
			}
		menu.resetBools();
		next.resetBools();
	}
	public void MousePressed(MouseEvent e) {
		if (isIn(menu,e))
			menu.setMousePressed(true);
		else if (isIn(next,e))
			next.setMousePressed(true);
	}
}