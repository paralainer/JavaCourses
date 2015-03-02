package lesson6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyImgPanel extends JPanel implements KeyListener {

	private char direction = 0;
	private boolean isMoving = false;
	
	private int width = 1000;
	private int height = 700;
	
	private int robotIndex = 0;

	
	private MovingImage[] images;
	
	private int moveCounter = 0;

	public MyImgPanel() {
		addKeyListener(this);
		setFocusable(true);
		try {
			
			images = new MovingImage[3];
			
			BufferedImage image1 = ImageIO.read(new File("robot.png"));
			images[0] = new MovingImage();
			images[0].setImage(image1);
			images[0].moveToDefaultPos();
			
			BufferedImage image3 = ImageIO.read(new File("robot.png"));
			images[2] = new MovingImage();
			images[2].setImage(image1);
			images[2].setDefaultY(height - image3.getHeight());
			images[2].moveToDefaultPos();

			
			BufferedImage image2 = ImageIO.read(new File("robot2.png"));
			images[1] = new MovingImage();
			images[1].setImage(image2);
			images[1].setDefaultX(width - image2.getWidth());
			images[1].setDefaultY(height - image2.getHeight());
			images[1].setSpeed(3);
			images[1].moveToDefaultPos();
			
		} catch (Exception e) {
		}
		Timer timer = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				onTimer();
			}
		});
		timer.start();
	}

	private void onTimer() {
		if(!isMoving){
			return;
		}
		
		char[] directions = new char[]{'u','d', 'l', 'r'};
		int i = 0;
		for (MovingImage image: images){
			if (i != robotIndex){
				if (moveCounter > 50){
					moveCounter = 0;
					image.direction = directions[new Random().nextInt(4)];
				}
				moveCounter++;
				image.move(width, height);
			}
			i++;
		}
		
		
		images[robotIndex].direction = direction;
		images[robotIndex].move(width, height);
		
		repaint();
	}

	@Override
	protected void paintComponent(Graphics gr) {
		gr.setColor(Color.LIGHT_GRAY);
		gr.fillRect(0, 0, width, height);
		
		int i = 0;
		for (MovingImage image: images){
			if (i != robotIndex){	
			gr.drawImage(
				image.getImage(), 
				image.getX(), 
				image.getY(), null);
			}
			i++;
		}
		
		gr.drawImage(
				images[robotIndex].getImage(), 
				images[robotIndex].getX(), 
				images[robotIndex].getY(), null);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()){
		case KeyEvent.VK_UP: direction = 'u'; break;
		case KeyEvent.VK_DOWN: direction = 'd'; break;
		case KeyEvent.VK_LEFT: direction = 'l'; break;
		case KeyEvent.VK_RIGHT: direction = 'r'; break;
		case KeyEvent.VK_S: 
			MovingImage img = images[robotIndex];	
			img.setSpeed(img.getSpeed() + 1);	
			if (img.getSpeed() > 15){
				img.setSpeed(1);
			}
			break;

		case KeyEvent.VK_SPACE: 
			robotIndex++;
			if (robotIndex >= images.length){
				robotIndex = 0;
			}
			break;
		case KeyEvent.VK_BACK_SPACE: 
			images[robotIndex].moveToDefaultPos(); direction = 0;
			break;
		
		}
		isMoving = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		isMoving = false;
	}

}
