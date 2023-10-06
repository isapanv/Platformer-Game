package main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static utilities.Constants.PlayerConstants.*;
import static utilities.Constants.Directions.*;

public class GamePannel extends JPanel{
	private MouseInputs mouseInputs;
	private float xDelta = 100, yDelta = 100;
	private BufferedImage img;
	private BufferedImage[][] anim;
	private int animTick, animIndex, animSpeed = 15;
	private int playerAction = IDLE;
	private int playerDirection = -1;
	private boolean moving = false;
	
	public GamePannel() {
		mouseInputs = new MouseInputs(this);
		ImportImg();
		loadAnimation();
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	private void loadAnimation() {
		anim = new BufferedImage[7][10];
		
		for(int i = 0; i <anim.length; ++i) {
			for(int j = 0; j < anim[i].length; ++j) {
				anim[i][j] = img.getSubimage(j*64, i*64, 64, 64);
			}
		}
	}
	private void ImportImg() {
		InputStream is = getClass().getResourceAsStream("/cat.png");
		try {
			img = ImageIO.read(is);
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
	private void setPanelSize() {
		Dimension size = new Dimension(1280,800);
		setPreferredSize(size);
	}
	
	public void setDirection(int direction) {
		this.playerDirection = direction;
		moving = true;
		
	}
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	private void updateAnimation() {
		animTick++;
		if (animTick >= animSpeed) {
			animTick = 0;
			animIndex++;
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
				xDelta -= 5;
				break;
			case UP:
				yDelta -= 5;
				break;
			case RIGHT:
				xDelta += 5;
				break;
			case DOWN:
				yDelta += 5;
				break;
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(img, 0, 0, null);
		//g.drawImage(img.getSubimage(0, 0, 75, 100), 0, 0, null);
		//g.drawImage(img.getSubimage(0, 0, 75, 100), 0, 0, 150, 200, null);
		//subImg = img.getSubimage(4*100, 0, 100, 100);
		updateAnimation();
		setAnimation();
		updatePos();
		g.drawImage(anim[playerAction][animIndex], (int)xDelta, (int)yDelta, 200, 200, null);
	}

}