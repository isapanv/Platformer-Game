package main;

import java.awt.Graphics;

import entities.Player;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePannel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;

	private Player player;
	public Game() {
		init();
		gamePanel = new GamePannel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		
		startGameLoop();
		
	}

	private void init() {
		player = new Player(200, 200);
		
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		player.updatePlayer();
	}
	public void render(Graphics g) {
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

}