package main;

import java.awt.Graphics;

import gamestats.GameState;
import gamestats.Menu;
import gamestats.Playing;
import gamestats.GameOptions;

import utilities.LoadSave;
import ui.AudioOptions;



public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePannel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	private Playing playing;
	private Menu menu;
	private AudioOptions audioOptions;
	private GameOptions gameOptions;
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1.5f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

	
	public Game() {

		init();
		gamePanel = new GamePannel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		
		startGameLoop();
		
	}

	private void init() {
		audioOptions = new AudioOptions();
		menu = new Menu(this);
		playing = new Playing(this);
		gameOptions = new GameOptions(this);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		switch(GameState.state) {
		case MENU:
			menu.update();
			break;
		case OPTIONS:
			gameOptions.update();
			break;
		case QUIT:
		case PLAYING:
			playing.update();
			break;
		default:
			System.exit(0);
			break;
		}
	}
	public void render(Graphics g) {
		switch(GameState.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
			break;
		case OPTIONS:
			gameOptions.draw(g);
			break;
		default:
			break;
		}
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
	

	public void winFocusLost() {	
		if(GameState.state == GameState.PLAYING) {
			playing.getPlayer().resetDirs();
		}
	}
	public Menu getMenu() {
		return menu;
		
	}
	public Playing getPlaying() {
		return playing;
		
	}
	public GameOptions getGameOptions() {
		return gameOptions;
	}

	public AudioOptions getAudioOptions() {
		return audioOptions;
	}

}