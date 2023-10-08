package entities;

import static utilities.Constants.Directions.*;
import static utilities.Constants.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;



public class Player extends Entity {

	private int animTick, animIndex, animSpeed = 15;
	private int playerAction = IDLE;
	private int playerDirection = -1;
	private boolean moving = false;
	private BufferedImage[][] anim;
	
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
	
	public void setDirection(int direction) {
		this.playerDirection = direction;
		moving = true;
		
	}
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	private void updateAnimation() {
		++animTick;
		if (animTick >= animSpeed) {
			animTick = 0;
			++animIndex;
			if (animIndex >= getSpriteAmount(playerAction)) {
				animIndex = 0;
			}
		}

	}
	private void setAnimation() {
		if (moving) {
			playerAction = WALK;
		}
		else {
			playerAction = IDLE;
		}
	}

	private void updatePos() {
		if (moving) {
			switch (playerDirection) {
			case LEFT:
				x -= 5;
				break;
			case UP:
				y -= 5;
				break;
			case RIGHT:
				x += 5;
				break;
			case DOWN:
				y += 5;
				break;
			}
		}
	}
	
	private void loadAnimation() {
		InputStream is = getClass().getResourceAsStream("/cat.png");
		try {
			BufferedImage img = ImageIO.read(is);
			
			anim = new BufferedImage[7][10];
			
			for(int i = 0; i <anim.length; ++i) {
				for(int j = 0; j < anim[i].length; ++j) {
					anim[i][j] = img.getSubimage(j*64, i*64, 64, 64);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
}
