package lesson6;

import java.awt.image.BufferedImage;

public class MovingImage {

	private BufferedImage image;

	private int x;
	private int y;

	private int defaultX = 0;
	private int defaultY = 0;
	
	private int speed = 1;

	public char direction;

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDefaultX() {
		return defaultX;
	}

	public void setDefaultX(int defaultX) {
		this.defaultX = defaultX;
	}

	public int getDefaultY() {
		return defaultY;
	}

	public void setDefaultY(int defaultY) {
		this.defaultY = defaultY;
	}

	public void move(int maxX, int maxY) {
		switch (direction) {
		case 'u':
			y -= speed;
			break;
		case 'd':
			y+=speed;
			break;
		case 'l':
			x-=speed;
			break;
		case 'r':
			x+=speed;
			break;
		}
		
		if (x < 0){
			x = 0;
		}
		
		if (y < 0){
			y = 0; 
		}
		
		if (x + image.getWidth() > maxX){
			x = maxX - image.getWidth();
		}
		
		if (y + image.getHeight() > maxY){
			y = maxY - image.getHeight();
		}
	}

	public void moveToDefaultPos() {
		x = defaultX;
		y = defaultY;
	}

}
