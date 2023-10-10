package main;

import java.awt.Graphics;

import entities.Player;
import levels.LevelManager;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePannel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 2f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	private Player player;
	private LevelManager levelManager; 
	
	public Game() {
		init();
		gamePanel = new GamePannel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		
		startGameLoop();
		
	}

	private void init() {
		levelManager = new LevelManager(this);
		player = new Player(200, 200, (int)(64 * SCALE), (int)(64 * SCALE));
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		player.updatePlayer();
		levelManager.update();
	}
	public void render(Graphics g) {
		levelManager.draw(g);
		player.renderPlayer(g);
	}
	
	@Override
	public void run() {

		double timePerFrame = 1500000000.0 / FPS_SET;
		double timePerUpdate = 1500000000.0 / UPS_SET;
		long lastFrame = System.nanoTime();
		long now = System.nanoTime();

		long prevTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
		
		while (true) {

			long currTime = System.nanoTime();

			deltaU += (currTime - prevTime) / timePerUpdate;
			deltaF += (currTime - prevTime) / timePerFrame;
			prevTime = currTime;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;

			}
		}

	}
	
	public Player getPlayer() {
		return player;
	}

	public void winFocusLost() {
		player.resetDirs();
		
	}

}