package entities;

import static utilities.Constants.Directions.*;
import static utilities.Constants.PlayerConstants.*;
import static utilities.HelpMethods.*;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D.Float;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;
import utilities.LoadSave;


public class Player extends Entity {

	private int animTick, animIndex, animSpeed = 15;
	private int playerAction = IDLE;
	private int playerDirection = -1;
	private boolean moving = false;
	private boolean attacking = false;
	private boolean left, right, up, down, jump;
	private BufferedImage[][] anim;
	private int playerSpeed = 5;
	private int[][] lvlData;
	private float xDrawOffSet = 21 * Game.SCALE;
	private float yDrawOffSet = 25 * Game.SCALE;
	private float airSpeed = 0f;
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = -2.5f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;
	
	public Player(float x, float y, int width, int height) {
		super(x,y,width,height);
		loadAnimation();
		initHitBox(x, y, 20*Game.SCALE, 28*Game.SCALE);
		
	}
	
	public void updatePlayer() {
		updatePos();
		updateAnimation();
		setAnimation();
	}
	public void renderPlayer(Graphics g) {
		g.drawImage(anim[playerAction][animIndex], (int)(hitbox.x - xDrawOffSet), (int)(hitbox.y - yDrawOffSet), width, height, null);
		//drawHitBox(g);
	}
	
	
	
	private void updateAnimation() {
		++animTick;
		if (animTick >= animSpeed) {
			animTick = 0;
			++animIndex;
			if (animIndex >= getSpriteAmount(playerAction)) {
				animIndex = 0;
				attacking = false;
			}
		}

	}
	private void setAnimation() {
		int start = playerAction;
		
		if (moving) {
			playerAction = WALK;
		}
		else {
			playerAction = IDLE;
		}
		if (inAir) {
			if (airSpeed < 0)
				playerAction = JUMP;
			else 
				playerAction = SPIN;
		}
		if(attacking) {
			playerAction = FAST_SHOT;
		}
		if(start != playerAction) {
			resetAnim();
		}
	}

	private void resetAnim() {
		animTick = 0;
		animIndex = 0;
		
		
	}

	private void updatePos() {
		moving = false;
		if (jump)
			jump();
		if (!left && !right && !inAir)
			return;
		float xSpeed = 0;
		
		if(left) {
			xSpeed -= playerSpeed;
		}
		if(right) {
			xSpeed += playerSpeed;
		}
		if (!inAir) {
			if (!IsEntityOnFloor(hitbox,lvlData)) {
				inAir = true;
			}
		}
		if (inAir) {
			if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height,  lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
				if (airSpeed > 0) {
					resetInAir();
				} else {
					airSpeed = fallSpeedAfterCollision;
				}
				updateXPos(xSpeed);
			}
		} else {
			updateXPos(xSpeed);
		}
		moving = true;
	}

	private void jump() {
		if (inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;
		
	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
		
	}

	private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
		} else {
			hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
		}
	}

	private void loadAnimation() {
			BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
			
			anim = new BufferedImage[7][10];
			
			for(int i = 0; i <anim.length; ++i) {
				for(int j = 0; j < anim[i].length; ++j) {
					anim[i][j] = img.getSubimage(j*64, i*64, 64, 64);
				}
			}
	}
	
	public void loadLevelData(int[][] lvlData) {
		this.lvlData = lvlData;
		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void resetDirs() {
		left = false;
		right = false;
		down = false;
		up = false;
		
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}
	
	
}