package entities;

import static utilities.Constants.Directions.*;
import static utilities.Constants.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import utilities.LoadSave;



public class Player extends Entity {

	private int animTick, animIndex, animSpeed = 15;
	private int playerAction = IDLE;
	private int playerDirection = -1;
	private boolean moving = false;
	private boolean attacking = false;
	private boolean left, right, up, down;
	private BufferedImage[][] anim;
	private int playerSpeed = 5;
	
	public Player(float x, float y) {
		super(x, y);
		loadAnimation();
	}
	
	public void updatePlayer() {
		
		updatePos();
		updateAnimation();
		setAnimation();
	}
	public void renderPlayer(Graphics g) {
		g.drawImage(anim[playerAction][animIndex], (int)x, (int)y, 200, 200, null);
	
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
		
		if(left && !right) {
			x-= playerSpeed;
			moving = true;
		}else if(right && !left) {
			x+= playerSpeed;
			moving = true;
		}
		if(up && !down) {
			y-= playerSpeed;
			moving = true;
		}else if(down && !up) {
			y+=playerSpeed;
			moving = true;
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
	
	
}