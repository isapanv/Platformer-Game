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
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

import static utilities.Constants.PlayerConstants.*;
import static utilities.Constants.Directions.*;

public class GamePannel extends JPanel{
	private MouseInputs mouseInputs;
	private Game game;
	
	public GamePannel(Game game) {
		mouseInputs = new MouseInputs(this);
		this.game = game;
		
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	public void updateGame() {
		
	}


	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
		//System.out.println("size: " + GAME_WIDTH +  " : " + GAME_HEIGHT);
	}
	


	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		game.render(g);

	}
	public Game getGame() {
		return game;
	}

}