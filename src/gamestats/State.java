package gamestats;
import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButtons;
public class State {

	protected Game game;

	public State(Game game) {
		this.game = game;
	}
	public boolean isIn(MouseEvent e, MenuButtons menu_button) {
		return menu_button.getBounds().contains(e.getX(), e.getY());
	}
	public Game getGame() {
		return game;
	}
}