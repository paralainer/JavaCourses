package game1;

import java.awt.image.BufferedImage;

public class Present {
	
	private BufferedImage img;	
	private int x;
	private int y;
	private int speed;
	private boolean isStopped;
	
	public BufferedImage getImg() {
		return img;
	}
	public void setImg(BufferedImage img) {
		this.img = img;
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
	public boolean isStopped() {
		return isStopped;
	}
	public void setStopped(boolean isStopped) {
		this.isStopped = isStopped;
	}
	
	
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void moveDown(){
		if (!isStopped){
			y += speed;
		}
	}
	
	

}
