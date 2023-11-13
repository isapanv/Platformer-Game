 package gamestats;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import ui.MenuButtons;
import utilities.LoadSave;
import main.Game;

public class Menu extends State implements StateMethods {

	private MenuButtons[] buttons = new MenuButtons[3];
	private BufferedImage backgroundImg, backgroundImgPink;
	private int menuX, menuY, menuWidth, menuHeight;
	
	public Menu(Game game) {
		super(game);
		loadButtons();
		loadBackground();
		backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU);
		menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
		menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
		menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
		menuY = (int) (45 * Game.SCALE);
		
	}

	private void loadButtons() {
		buttons[0] = new MenuButtons(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, GameState.PLAYING);
		buttons[1] = new MenuButtons(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, GameState.OPTIONS);
		buttons[2] = new MenuButtons(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, GameState.QUIT);
	
		
	}

	@Override
	public void update() {
		for (MenuButtons menu_button : buttons) {
			menu_button.update();
		}
	}

	@Override
	public void draw(Graphics g) {

		g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);

		for (MenuButtons menu_button : buttons)
			menu_button.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (MenuButtons menu_button : buttons) {
			if (isIn(e, menu_button)) {
				menu_button.setMousePressed(true);
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (MenuButtons menu_button : buttons) {
			if (isIn(e, menu_button)) {
				if (menu_button.isMousePressed())
					menu_button.applyGamestate();
				break;
			}
		}

		resetButtons();
	}

	private void resetButtons() {
		for (MenuButtons menu_button : buttons) {
			menu_button.resetBools();
		}		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (MenuButtons menu_button : buttons)
			menu_button.setMouseOver(false);

		for (MenuButtons menu_button : buttons)
			if (isIn(e, menu_button)) {
				menu_button.setMouseOver(true);
				break;
			}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		//if (e.getKeyCode() == KeyEvent.VK_ENTER)
		//	GameState.state = GameState.PLAYING;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}