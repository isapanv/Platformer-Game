package entities;

import static utilities.Constants.EnemyConsts.*;

import main.Game;

public class Enemy1 extends Enemy {

	public Enemy1(float x, float y) {
		super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT, ENEMY1);
		initHitBox(x, y, (int)(22 * Game.SCALE),(int)(9*Game.SCALE));
	}

}