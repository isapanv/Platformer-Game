package entities;

import static utilities.Constants.Directions.*;
import static utilities.Constants.PlayerConstants.*;
import static utilities.HelpMethods.*;
import static utilities.Constants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import gamestats.Playing;
import main.Game;
import ui.GameOverlay;
import utilities.LoadSave;


public class Player extends Entity {

	private int playerDirection = -1;
	private boolean moving = false;
	private boolean attacking = false;
	private boolean left, right, jump;
	private BufferedImage[][] anim;
	//private float playerSpeed = 1.5f * Game.SCALE;
	
	private int[][] lvlData;
	private float xDrawOffSet = 21 * Game.SCALE;
	private float yDrawOffSet = 25 * Game.SCALE;
	
	private float jumpSpeed = -2.5f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	
	
	private BufferedImage statusBarImg;
	private int statusBarWidth = (int) (192 * Game.SCALE);
	private int statusBarHeight = (int) (58 * Game.SCALE);
	private int statusBarX = (int) (10 * Game.SCALE);
	private int statusBarY = (int) (10 * Game.SCALE);

	private int healthBarWidth = (int) (150 * Game.SCALE);
	private int healthBarHeight = (int) (4 * Game.SCALE);
	private int healthBarXStart = (int) (34 * Game.SCALE);
	private int healthBarYStart = (int) (14 * Game.SCALE);


	private int healthWidth = healthBarWidth;
	

	private int flipX = 0;
	private int flipW = 1;

	private boolean attackChecked;
	private Playing playing;

	public Player(float x, float y, int width, int height, Playing playing) {
		super(x,y,width,height);
		this.playing = playing;
		this.state = IDLE;
		this.maxHealth = 100;
		this.currentHealth = this.maxHealth;
		this.walkSpeed =1.5f * Game.SCALE;
		loadAnimation();
		initHitBox(20, 28);
		initAttackBox();
	}
	public void setSpawn(Point spawn) {
		this.x = spawn.x;
		this.y = spawn.y;
		hitbox.x = x;
		hitbox.y = y;
	}
	
	private void updateHealthBar() {
		healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);
	}
	
	private void updateAttackBox() {
		if (right)
			attackBox.x = hitbox.x + hitbox.width + (int) (Game.SCALE * 10);
		else if (left)
			attackBox.x = hitbox.x - hitbox.width - (int) (Game.SCALE * 10);

		attackBox.y = hitbox.y + (Game.SCALE * 10);
	}
	
	private void checkAttack() {
		if (attackChecked || animIndex != 1)
			return;
		attackChecked = true;
		playing.checkEnemyHit(attackBox);
		playing.checkObjectHit(attackBox);

	}
	
	public void render(Graphics g, int lvlOffset) {
		g.drawImage(anim[state][animIndex], (int) (hitbox.x - xDrawOffSet) - lvlOffset + flipX, (int) (hitbox.y - yDrawOffSet), width * flipW, height, null);
//		drawHitbox(g, lvlOffset);
//		drawAttackBox(g, lvlOffset);
		drawUI(g);
	}

	public void updatePlayer() {
		updateHealthBar();

		if (currentHealth <= 0) {
			playing.setGameOver(true);
			return;
		}

		updateAttackBox();
		updatePos();
		if (moving) {
			checkPotionTouched();
			checkSpikesTouched();
		}
		if (attacking)
			checkAttack();
		updateAnimation();
		setAnimation();
	}
	private void checkPotionTouched() {
		playing.checkPotionTouched(hitbox);
	}
	private void checkSpikesTouched() {
		playing.checkSpikesTouched(this);
		
	}
	private void initAttackBox() {
		attackBox = new Rectangle2D.Float(x, y, (int) (20 * Game.SCALE), (int) (20 * Game.SCALE));
	}
	public void renderPlayer(Graphics g, int lvlOffset) {
		g.drawImage(anim[state][animIndex], (int) (hitbox.x - xDrawOffSet) - lvlOffset + flipX, (int) (hitbox.y - yDrawOffSet), width * flipW, height, null);
		//drawHitBox(g, lvlOffset);
		//drawAttackBox(g, lvlOffset);
		drawUI(g);
	}

	public void changeHealth(int value) {
		currentHealth += value;

		if (currentHealth <= 0)
			currentHealth = 0;
		else if (currentHealth >= maxHealth)
			currentHealth = maxHealth;
	}

	
	private void drawUI(Graphics g) {
		g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
		g.setColor(Color.red);
		g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
	
	}

	private void updateAnimation() {
		++animTick;
		if (animTick >= ANIM_SPEED) {
			animTick = 0;
			++animIndex;
			if (animIndex >= getSpriteAmount(state)) {
				animIndex = 0;
				attacking = false;
				attackChecked = false;

			}
		}

	}
	private void setAnimation() {
		int start = state;
		
		if (moving) {
			state = WALK;
		}
		else {
			state = IDLE;
		}
		if (inAir) {
			if (airSpeed < 0)
				state = JUMP;
			else 
				state = SPIN;
		}
		if (attacking) {
			state = POWER_SHOT;
			if (start != POWER_SHOT) {
				animIndex = 1;
				animTick = 0;
				return;
			}
		}
		if (start != state)
			resetAnim();
	}

	private void resetAnim() {
		animTick = 0;
		animIndex = 0;
		
		
	}

	private void updatePos() {
		moving = false;
		if (jump)
			jump();
		if (!inAir && ((!left && !right) || (left && right)))
			return;
		float xSpeed = 0;
		
		if (left) {
			xSpeed -= walkSpeed;
			flipX = width;
			flipW = -1;
		}
		if (right) {
			xSpeed += walkSpeed;
			flipX = 0;
			flipW = 1;
		}
		if (!inAir) {
			if (!IsEntityOnFloor(hitbox,lvlData)) {
				inAir = true;
			}
		}
		if (inAir) {
			if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height,  lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += GRAVITY;
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

	public void changePower(int value) {
		// TO DO
	}
	private void loadAnimation() {
			BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
			
			anim = new BufferedImage[7][10];
			
			for(int i = 0; i <anim.length; ++i) {
				for(int j = 0; j < anim[i].length; ++j) {
					anim[i][j] = img.getSubimage(j*64, i*64, 64, 64);
				}
			}
			statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);
	}
	
	public void loadLevelData(int[][] lvlData) {
		this.lvlData = lvlData;
		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}
	
	public void resetAll() {
		resetDirs();
		inAir = false;
		attacking = false;
		moving = false;
		state = IDLE;
		currentHealth = maxHealth;

		hitbox.x = x;
		hitbox.y = y;

		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}
	public void kill() {
		currentHealth = 0;
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



	public void resetDirs() {
		left = false;
		right = false;

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