package entities;

import static utilities.Constants.Directions.LEFT;
import static utilities.Constants.EnemyConsts.*;
import static utilities.Constants.Directions.*;
import static utilities.HelpMethods.CanMoveHere;
import static utilities.HelpMethods.GetEntityYPosUnderRoofOrAboveFloor;
import static utilities.HelpMethods.IsEntityOnFloor;
import static utilities.HelpMethods.IsFloor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

import main.Game;

public class Enemy1 extends Enemy {
	private Rectangle2D.Float attackBox;
	private int attackBoxOffsetX;

	public Enemy1(float x, float y) {
		super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT, ENEMY1);
		initHitBox(x, y, (int)(22 * Game.SCALE),(int)(19*Game.SCALE));
		initAttackBox();
	}
	public void update(int[][] lvlData, Player player) {
		updateMove(lvlData, player);
		updateAnimationTick();
		updateAttackBox();
		
	}
	private void updateAttackBox() {
		attackBox.x = hitbox.x - attackBoxOffsetX;
		attackBox.y = hitbox.y;

	}
	private void initAttackBox() {
		attackBox = new Rectangle2D.Float(x, y, (int) (82 * Game.SCALE), (int) (19 * Game.SCALE));
		attackBoxOffsetX = (int) (Game.SCALE * 30);
		
	}
	private void updateMove(int[][] lvlData, Player player) {
		if(firstUpdate) {
			firstUpdateCheck(lvlData);
		}
		if(inAir) {
			updateInAir(lvlData);
		}else {
			switch(enemyState) {
			case IDLE:
				newState(RUN);
				break;
			case RUN:
				if(canSeePlayer(lvlData, player))
				{
					turnTowardsPlayer(player);
				}
				if(isPlayerCloseForAttack(player))
				{
					newState(ATTACK);
				}
				move(lvlData);
				break;
			case ATTACK:
				if (aniIndex == 0)
					attackChecked = false;
				if (aniIndex == 3 && !attackChecked)
					checkEnemyHit(attackBox, player);

				break;
			case HIT:
				break;
			}
		}
		
	}
	
	public void drawAttackBox(Graphics g, int xLvlOffset) {
		g.setColor(Color.red);
		g.drawRect((int) (attackBox.x - xLvlOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
	} 
	public int flipX() {
		if (walkDir == RIGHT)
			return width;
		else
			return 0;
	}

	public int flipW() {
		if (walkDir == RIGHT)
			return -1;
		else
			return 1;

	}
}