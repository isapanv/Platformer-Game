package main;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
public class GamePannel extends JPanel{
	private MouseInputs mouseInputs;
	public GamePannel() {
		mouseInputs = new MouseInputs();
		addKeyListener(new KeyboardInputs());
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(100, 100, 200, 50);
	}
}