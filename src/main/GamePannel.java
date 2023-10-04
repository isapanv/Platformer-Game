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

public class GamePannel extends JPanel{
	private MouseInputs mouseInputs;
	private float xDelta = 100, yDelta = 100;
	private BufferedImage img, subImg;
	public GamePannel() {
		mouseInputs = new MouseInputs(this);
		ImportImg();
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	private void ImportImg() {
		InputStream is = getClass().getResourceAsStream("/player.png");
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void setPanelSize() {
		Dimension size = new Dimension(1280,800);
		setPreferredSize(size);
	}
	public void changeXDelta(int value) {
		this.xDelta += value;
		repaint();
	}
	public void changeYDelta(int value) {
		this.yDelta += value;
		repaint();
	}
	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(img, 0, 0, null);
		//g.drawImage(img.getSubimage(0, 0, 75, 100), 0, 0, null);
		//g.drawImage(img.getSubimage(0, 0, 75, 100), 0, 0, 150, 200, null);
		subImg = img.getSubimage(4*100, 0, 100, 100);
		g.drawImage(subImg, (int)xDelta, (int)yDelta, 200, 200, null);
	}
}