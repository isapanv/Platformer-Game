package entities;
import static utilities.Constants.EnemyConsts.*;
import static utilities.HelpMethods.*;
import static utilities.Constants.Directions.*;
import main.Game;
public abstract class Enemy extends Entity{
	private int animIdx, state, type;
	private int animTick, animSpeed = 25;
	private boolean firstUpdate = true;
	private boolean inAir;
	private float fallSpeed, gravity = 0.04f * Game.SCALE;
	private float walkSpeed = 0.35f * Game.SCALE;
	private int walkDir = LEFT;
	
	public Enemy(float x, float y, int width, int height, int type) {
		super(x, y, width, height);
		this.type = type;
		initHitBox(x, y, width, height);
	}
	private void updateAnimTick() {
		animTick++;
		if(animTick >=animSpeed) {
			animTick = 0;
			animIdx++;
			if(animIdx >= getSpriteAmount(type, state)) {
				animIdx = 0;
			}
		}
	}
	public void update(int[][] lvlData) {
		updateMove(lvlData);
		updateAnimTick();
		
	}
	
	private void updateMove(int[][] lvlData) {
		if(firstUpdate) {
			if(!IsEntityOnFloor(hitbox, lvlData)) {
				inAir = true;
			}
			firstUpdate = false;
		}
		if(inAir) {
			if(CanMoveHere(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, lvlData))
			{	hitbox.y +=fallSpeed;
				fallSpeed +=gravity;
			}else {
				inAir = false;
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, fallSpeed);
			}
		}else {
			switch(state) {
			case IDLE:
				state = RUN;
				break;
			case RUN:
				float xSpeed = 0;
				if (walkDir == LEFT)
					xSpeed = -walkSpeed;
				else
					xSpeed = walkSpeed;

				if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
					if (IsFloor(hitbox, xSpeed, lvlData)) {
						hitbox.x += xSpeed;
						return;
					}

				changeWalkDir();

				break;
			}
		}
	}
	
	private void changeWalkDir() {
		if (walkDir == LEFT)
			walkDir = RIGHT;
		else
			walkDir = LEFT;

	}

	
	public int getAnimIdx() {
		return animIdx;
	}
	public int getState() {
		return state;
	}

}
