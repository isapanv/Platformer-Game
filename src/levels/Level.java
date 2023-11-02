package levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Enemy1;
import main.Game;
import static utilities.HelpMethods.GetLevelData;
import static utilities.HelpMethods.GetEnemies;
import static utilities.HelpMethods.GetPlayerSpawn;

public class Level {
	private BufferedImage img;
	private int[][] lvlData;
	private ArrayList<Enemy1> enemies;
	private int lvlTilesWidth;
	private int maxTilesOffset;
	private int maxlvlOffsetX;
	private Point playerSpawn;
	public Level(BufferedImage img) {
		this.img = img;
		createLevelData();
		createEnemies();
		calcLvlOffSets();
		calcPlayerSpawn();
	}
	private void calcPlayerSpawn() {
		playerSpawn = GetPlayerSpawn(img);	
	}
	private void createLevelData() {
		lvlData = GetLevelData(img);
	}
	private void createEnemies() {
		enemies = GetEnemies(img);
	}
	private void calcLvlOffSets() {
		lvlTilesWidth = img.getWidth();
		maxTilesOffset = lvlTilesWidth - Game.TILES_IN_WIDTH;
		maxlvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
	}
	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}
	public int[][] getLevelData() {
		return lvlData;
	}
	public int getLevelOffset() {
		return maxlvlOffsetX;
	}
	public ArrayList<Enemy1> getEnemy() {
		return enemies;
	}
	public Point getPlayerSpawn() {
		return playerSpawn;
	}
}