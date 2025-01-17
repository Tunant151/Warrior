package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;
import static utilz.Constants.Directions.*;

public class KeyboardInputs implements KeyListener { 
	
	private GamePanel gamePanel;
	
	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
    public void keyTyped(KeyEvent e) {
        // Handle key typed events (when a key is pressed and released)
		// System.out.println("Key typed: " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Handle key pressed events (when a key is pressed down)
//    	System.out.println("Key pressed: " + e.getKeyCode());
    	switch(e.getKeyCode()) {
			case KeyEvent.VK_W -> gamePanel.setDirection(UP);
			case KeyEvent.VK_A -> gamePanel.setDirection(LEFT);
			case KeyEvent.VK_S -> gamePanel.setDirection(DOWN);
			case KeyEvent.VK_D -> gamePanel.setDirection(RIGHT);
		}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key released events (when a key is released)
        // System.out.println("Key released: " + e.getKeyCode());
    	switch(e.getKeyCode()) {
			case KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D -> gamePanel.setMoving(false);
		}
    	
    }

}
