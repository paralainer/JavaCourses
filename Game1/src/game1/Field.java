package game1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Field extends JPanel {
	
	private int capSpeed = 20;
	private int presentsSpeed = 10;
	private int presentsCount = 4;
	
	private Image background;
	private Image cap;
	
	private Present[] presents;
	
	private int x = 375;
	
	public char direction = 0;
	
	private int ticksDelay = 100;
	private long currentTick = 0;
	
	
	public Field(){		
		try {
			background = ImageIO.read(new File("background.jpg"));
			cap = ImageIO.read(new File("cap.png"));
			presents = new Present[presentsCount];
			for (int i = 1; i <= presents.length; i++){
				Present present = new Present();
				present.setImg(ImageIO.read(new File("p" + i + ".png")));
				present.setStopped(true);
				present.setSpeed(presentsSpeed);
				initPresentPosition(present);
				
				presents[i - 1] = present;
			}			
		}catch(Exception e){}
		
		Timer drawTimer = new Timer(20, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {			
				if (direction == 'l'){
					x -= capSpeed;
				} else if (direction == 'r'){
					x += capSpeed;
				}
				
				if (x < 0){
					x = 0;
				}else if (x > 750){
					x = 750;
				}
				
				for (Present present: presents){
					present.moveDown();
					
					if (present.getY() > 600){
						//present.setStopped(true);
						initPresentPosition(present);
					}
				}
				
				
				currentTick++;
				if (currentTick % ticksDelay == 0){
					for (Present present: presents){
						if (present.isStopped()){
							present.setStopped(false);
							break;
						}
					}
				}
				if (currentTick == Long.MAX_VALUE){
					currentTick = 0;
				}
				
				
				repaint();		
			}		
		});
 
		drawTimer.start();
	}
	
	private void initPresentPosition(Present present){
		present.setX(new Random().nextInt(700));
		present.setY(-200);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);	
		g.drawImage(cap, x, 480, null);
		
		for (Present present: presents){
			g.drawImage(present.getImg(), present.getX(), present.getY(), null);
		}
	}	
}
