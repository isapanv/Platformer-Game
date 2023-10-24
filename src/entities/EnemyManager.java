package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestats.Playing;
import utilities.LoadSave;
import static utilities.Constants.EnemyConsts.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] enemy1Arr;
	private ArrayList<Enemy1> enemies1 = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}

	private void addEnemies() {
		enemies1 = LoadSave.GetEnemies();
		//System.out.println("size of enemy1: " + enemies1.size());
	}

	public void update(int[][] lvlData, Player player) {
		for (Enemy1 c : enemies1)
			c.update(lvlData, player);
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawEnemy1(g, xLvlOffset);
	}

	private void drawEnemy1(Graphics g, int xLvlOffset) {
		for (Enemy1 c : enemies1)
			g.drawImage(enemy1Arr[c.getEnemyState()][c.getAniIndex()], (int) c.getHitBox().x - xLvlOffset - ENEMY_DRAW_OFFSET_X,
					(int) c.getHitBox().y - ENEMY_DRAW_OFFSET_Y, ENEMY_WIDTH, ENEMY_HEIGHT, null);

	}

	private void loadEnemyImgs() {
		enemy1Arr = new BufferedImage[5][9];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.ENEMY1_SPRITE);
		for (int j = 0; j < enemy1Arr.length; j++)
			for (int i = 0; i < enemy1Arr[j].length; i++)
				enemy1Arr[j][i] = temp.getSubimage(i * ENEMY_WIDTH_DEF, j * ENEMY_HEIGHT_DEF, ENEMY_WIDTH_DEF, ENEMY_HEIGHT_DEF);
	}
}