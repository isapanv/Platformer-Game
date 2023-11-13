package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestats.Playing;
import levels.Level;
import utilities.LoadSave;
import static utilities.Constants.EnemyConsts.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] enemy1Arr;
	private ArrayList<Enemy1> enemies1 = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
	}

	public void loadEnemies(Level level) {
		enemies1 = level.getEnemy();
		//System.out.println("size of enemy1: " + enemies1.size());
	}

	public void update(int[][] lvlData, Player player) {
		boolean isAnyActive = false;
		for (Enemy1 c : enemies1)
			if (c.isActive()) {
				c.update(lvlData, player);
				isAnyActive = true;
			}
		if (!isAnyActive)
			playing.setLevelCompleted(true);
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawEnemy1(g, xLvlOffset);
	}

	private void drawEnemy1(Graphics g, int xLvlOffset) {
		for (Enemy1 c : enemies1) {
			if (c.isActive()) {
			g.drawImage(enemy1Arr[c.getState()][c.getAniIndex()], (int) c.getHitBox().x - xLvlOffset - ENEMY_DRAW_OFFSET_X + c.flipX(),
					(int) c.getHitBox().y - ENEMY_DRAW_OFFSET_Y, ENEMY_WIDTH* c.flipW(), ENEMY_HEIGHT, null);
			//c.drawHitBox(g, xLvlOffset);
			//c.drawAttackBox(g, xLvlOffset);
			}
		}

	}
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for (Enemy1 c : enemies1)
			if (c.isActive())
				if (c.getCurrentHealth() > 0)
					if (attackBox.intersects(c.getHitBox())) {
						c.hurt(10);
						return;
					}
	}

	private void loadEnemyImgs() {
		enemy1Arr = new BufferedImage[5][9];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.ENEMY1_SPRITE);
		for (int j = 0; j < enemy1Arr.length; j++)
			for (int i = 0; i < enemy1Arr[j].length; i++)
				enemy1Arr[j][i] = temp.getSubimage(i * ENEMY_WIDTH_DEF, j * ENEMY_HEIGHT_DEF, ENEMY_WIDTH_DEF, ENEMY_HEIGHT_DEF);
	}

	public void resetAllEnemies() {
		for (Enemy1 c : enemies1)
			c.resetEnemy();
	}
}