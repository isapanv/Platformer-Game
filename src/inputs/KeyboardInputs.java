package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			System.out.println("Its W");
			break;
		case KeyEvent.VK_A:
			System.out.println("Its A");
			break;
		case KeyEvent.VK_S:
			System.out.println("Its S");
			break;
		case KeyEvent.VK_D:
			System.out.println("Its D");
			break;
		}
		
	}

}
