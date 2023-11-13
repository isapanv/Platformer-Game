package levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Enemy1;
import main.Game;
import objects.Cannon;
import objects.GameContainer;
import objects.Potion;
import objects.Spike;


import utilities.HelpMethods;
import static utilities.HelpMethods.GetLevelData;
import static utilities.HelpMethods.GetEnemies;
import static utilities.HelpMethods.GetPlayerSpawn;

public class Level {
	private BufferedImage img;
	private int[][] lvlData;
	private ArrayList<Enemy1> enemies;
	private ArrayList<Potion> potions;
	private ArrayList<GameContainer> containers;
	private ArrayList<Spike> spikes;
	private ArrayList<Cannon> cannons;
	private int lvlTilesWidth;
	private int maxTilesOffset;
	private int maxlvlOffsetX;
	private Point playerSpawn;
	
	public Level(BufferedImage img) {
		this.img = img;
		
		createLevelData();
		createEnemies();
		createPotions();
		createContainers();
		createSpikes();
		createCannons();
		calcLvlOffSets();
		calcPlayerSpawn();
	}
	private void createCannons() {
		cannons = HelpMethods.GetCannons(img);
	}
	private void createSpikes() {
		spikes = HelpMethods.GetSpikes(img);
	}
	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}
	private void createContainers() {
		containers = HelpMethods.GetContainers(img);
	}

	private void createPotions() {
		potions = HelpMethods.GetPotions(img);
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
	public ArrayList<Potion> getPotions() {
		return potions;
	}

	public ArrayList<GameContainer> getContainers() {
		return containers;
	}
	public ArrayList<Spike> getSpikes() {
		return spikes;
	}
	public ArrayList<Cannon> getCannons(){
		return cannons;
	}
}