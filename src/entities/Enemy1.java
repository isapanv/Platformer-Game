package entities;

import static utilities.Constants.Directions.LEFT;
import static utilities.Constants.EnemyConsts.*;
import static utilities.HelpMethods.CanMoveHere;
import static utilities.HelpMethods.GetEntityYPosUnderRoofOrAboveFloor;
import static utilities.HelpMethods.IsEntityOnFloor;
import static utilities.HelpMethods.IsFloor;

import main.Game;

public class Enemy1 extends Enemy {

	public Enemy1(float x, float y) {
		super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT, ENEMY1);
		initHitBox(x, y, (int)(22 * Game.SCALE),(int)(19*Game.SCALE));
	}
	public void update(int[][] lvlData, Player player) {
		updateMove(lvlData, player);
		updateAnimationTick();
		
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
			}
		}
		
	}
}