package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import static utilz.Constants.Directions.*;
import static utilz.Constants.PlayerConstants.*;

public class GamePanel extends JPanel{
	
	private MouseInputs mouseInputs; 
	private float xDelta = 100, yDelta = 100;
	private BufferedImage img;
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 15;
	private int playerAction = IDLE;
	private int playerDir = -1;
	private boolean moving = false;
	private int movingSpeed = 5;
	 
	
	public GamePanel() {
		
		mouseInputs  = new MouseInputs(this);
		
		importImg();
		loadAnimations();
		
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		
	}

    private void loadAnimations() {
    	animations = new BufferedImage[7][8];
    	for (int i = 0; i < animations.length; i++) {
			for (int j = 0; j < animations[i].length; j++) {
				animations[i][j] = img.getSubimage(j * 64, i * 40, 64, 40);
	        }
    	}
    }
	
	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try{
				is.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	private void setPanelSize() {
		Dimension size = new Dimension(1280,800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		
	}

	public void setDirection(int direction) {
		this.playerDir = direction;
		moving = true;
	}


    public void setMoving(boolean moving) {
        this.moving = moving;
    }
	
	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
			}
		}
		
	}

    private void setAnimation() {
        if(moving){
			playerAction = RUNNING;
		} else {
			playerAction = IDLE;
		}
    }

    private void updatePos() {
		if (moving) {
			switch (playerDir) {
				case LEFT -> xDelta -= movingSpeed;
				case UP -> yDelta -= movingSpeed;
				case RIGHT -> xDelta += movingSpeed;
				case DOWN -> yDelta += movingSpeed;
			}
		}
    }

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// subImg = img.getSubimage(1*64, 2*40, 64, 40);
		// g.drawImage(subImg, (int) xDelta, (int) yDelta, 128, 80, null);
		
		updateAnimationTick();

		setAnimation();

		updatePos();
		
		g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta, 256, 160, null);
	}




}
