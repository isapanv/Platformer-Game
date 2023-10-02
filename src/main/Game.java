package main;

public class Game {
	private GameWindow gameWindow;
	private GamePannel gamePanel;
	public Game() {
		gamePanel = new GamePannel();
		gameWindow = new GameWindow(gamePanel); 
	}
}
