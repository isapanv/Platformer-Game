package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import main.Game;

public abstract class Entity {
	protected float x, y;
	protected int width, height;
	protected Rectangle2D.Float hitbox;
	protected int animTick, animIndex;
	protected int state;
	protected float airSpeed;
	protected boolean inAir = false;
	protected int maxHealth;
	protected int currentHealth;
	protected Rectangle2D.Float attackBox;
	protected float walkSpeed;

	
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	protected void drawHitBox(Graphics g, int xlvlOffset) {
		// For debugging the hitbox
		g.setColor(Color.PINK);
		g.drawRect((int)hitbox.x - xlvlOffset, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
	}
	protected void initHitBox(int width, int height) {
		hitbox = new Rectangle2D.Float(x,y, (int)(width * Game.SCALE), (int)(height * Game.SCALE));
	}
	/*
	protected void updateHitBox() {
		hitbox.x = (int)x;
		hitbox.y = (int)y;
	}
	*/
	protected void drawAttackBox(Graphics g, int lvlOffsetX) {
		g.setColor(Color.red);
		//g.drawRect((int) attackBox.x - lvlOffsetX, (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);

	}
	public int getAniIndex() {
		return animIndex;
	}

	public int getState() {
		return state;
	}
	public Rectangle2D.Float getHitBox() {
		return hitbox;
	}
}