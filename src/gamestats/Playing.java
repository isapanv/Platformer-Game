package gamestats;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverLey;
import utilities.LoadSave;
import static utilities.Constants.*;

public class Playing extends State implements StateMethods {
	private Player player;
	private LevelManager levelManager;
	private PauseOverLey pauseOverLey;
	private EnemyManager enemyManager;
	private boolean paused = true; 
	
	private int xlvlOffset;
	private int lBorder = (int)(0.2* Game.GAME_WIDTH);
	private int rBorder = (int)(0.8* Game.GAME_WIDTH);
	private int lvlTilesWidth = LoadSave.GetLevelData()[0].length;
	private int maxTilesOffset = lvlTilesWidth - Game.TILES_IN_WIDTH;
	private int maxlvlOffsetX = maxTilesOffset *Game.TILES_SIZE;
	
	private BufferedImage play_bg, big_cloud, small_cloud;
	private int[] small_clouds_pos;
	private Random rand = new Random();
	public Playing(Game game) {
		super(game);
		init(); 
		play_bg = LoadSave.GetSpriteAtlas(LoadSave.PLAY_BG);
		big_cloud = LoadSave.GetSpriteAtlas(LoadSave.BIG_CLOUDS);
		small_cloud = LoadSave.GetSpriteAtlas(LoadSave.SMALL_CLOUDS);
		small_clouds_pos = new int[10];
		for(int i = 0; i < small_clouds_pos.length;++i) {
			small_clouds_pos[i] = (int)(90*Game.SCALE)+ rand.nextInt((int)(100*Game.SCALE));
		}
	}

	private void init() {
		levelManager = new LevelManager(game);
		enemyManager = new EnemyManager(this);
		player = new Player(200, 200, (int)(64 * Game.SCALE), (int)(64 * Game.SCALE));
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
		pauseOverLey = new PauseOverLey(this);
		
	}
	public Player getPlayer() {
		return player;
	}

	public void winFocusLost() {
		player.resetDirs();
		
	}

	@Override
	public void update() {
		if (!paused) {
			levelManager.update();
			player.updatePlayer();
			enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
			checkBorder();
		} else {
			pauseOverLey.update();
		}
	}

	//Changing offset to get precise player position at the screen
	private void checkBorder() {
		int playerX = (int)player.getHitBox().x;
		int diff = playerX - xlvlOffset;
		if(diff > rBorder) {
			xlvlOffset +=diff-rBorder;
			
		}else if(diff < lBorder) {
			xlvlOffset +=diff - lBorder;
		}
		if(xlvlOffset > maxlvlOffsetX) {
			xlvlOffset = maxlvlOffsetX;
		}else if(xlvlOffset < 0){
			xlvlOffset = 0;
		}
	}
	
	//Getting the image of game onto our game window
	@Override
	public void draw(Graphics g) {
		g.drawImage(play_bg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		drawClouds(g);
		levelManager.draw(g, xlvlOffset);
		player.renderPlayer(g,xlvlOffset);
		enemyManager.draw(g, xlvlOffset);
		if(paused) {
			g.setColor(new Color(0, 0 ,0, 200));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverLey.draw(g);
		}
	}

	private void drawClouds(Graphics g) {
		for(int i = 0; i < 3; i++) {
			g.drawImage(big_cloud, i * Environment.BIG_CLOUD_WITDH - (int)(0.3 * xlvlOffset), (int)(204 * Game.SCALE), Environment.BIG_CLOUD_WITDH, Environment.BIG_CLOUD_HEIGHT,null);
		}
		for(int i = 0; i < small_clouds_pos.length; i++) {
			g.drawImage(small_cloud,  Environment.SMALL_CLOUD_WITDH * 5 * i - (int)(0.8 * xlvlOffset), small_clouds_pos[i], Environment.SMALL_CLOUD_WITDH, Environment.SMALL_CLOUD_HEIGHT,null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			player.setAttacking(true);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A, KeyEvent.VK_LEFT:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_BACK_SPACE:
			GameState.state = GameState.MENU;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_A, KeyEvent.VK_LEFT:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}

	}
	public void unpauseGame() {
		paused = false;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (paused)
			pauseOverLey.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused)
			pauseOverLey.mouseReleased(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused)
			pauseOverLey.mouseMoved(e);
	}

	public void windowFocusLost() {
		player.resetDirs();
	}

	public void mouseDragged(MouseEvent e) {
		if(paused) {
			pauseOverLey.mouseDragged(e);
		}
		
	}


}