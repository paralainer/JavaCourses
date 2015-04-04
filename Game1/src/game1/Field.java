package game1;

import java.awt.Color;
import java.awt.Font;
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
	private int presentsCount = 7;
	
	private Image background;
	private Image cap;
	
	private Present[] presents;
	
	private int capX = 375;
	private int capY = 480;
	private Integer score = 0;
	
	public char direction = 0;
	
	private int ticksDelay = 100;
	private long currentTick = 0;
	public boolean gameOver = false;
	
	
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
				if (gameOver){
					repaint();
					return;
				}
				
				if (direction == 'l'){
					capX -= capSpeed;
				} else if (direction == 'r'){
					capX += capSpeed;
				}
				
				if (capX < 0){
					capX = 0;
				}else if (capX > 750){
					capX = 750;
				}
				
				for (Present present: presents){
					present.moveDown();
					
					if (!present.isStopped() && present.getY() > 600){
						gameOver = true;	
						initPresentPosition(present);
						present.setStopped(true);
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
				
				
				for (Present present: presents){
					if (!present.isStopped()){
						//capX;
						//capY;
						int capWidth  = cap.getWidth(null);
						int capHeight = cap.getHeight(null);
						
						int presentX = present.getX();
						int presentY = present.getY();
						int presentWidth = present.getImg().getWidth();
						int presentHeight = present.getImg().getHeight();
						
						if (isIntersects(
								capX, 
								capX + capWidth,
								capY,
								capY + capHeight,
								presentX,
								presentX + presentWidth,
								presentY,
								presentY + presentHeight							
								)){
							initPresentPosition(present);
							score++;
						}
						
					}
				}
				
				repaint();		
			}		
		});
 
		drawTimer.start();
	}
	
	private boolean isIntersects(
			int r1left, 
			int r1right, 
			int r1top, 
			int r1bottom, 
			int r2left,
			int r2right,
			int r2top,
			int r2bottom
			){
		return !(r2left > r1right 
			        || r2right <r1left 
			        || r2top > r1bottom 
			        || r2bottom < r1top); 
	}
	
	private void initPresentPosition(Present present){
		present.setX(new Random().nextInt(700));
		present.setY(-200);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);	
		g.drawImage(cap, capX, capY, null);
		
		for (Present present: presents){
			g.drawImage(present.getImg(), present.getX(), present.getY(), null);
		}
		
		g.setColor(Color.GREEN);
		g.setFont(g.getFont().deriveFont(24.0f));
		g.drawString(score.toString(), 760, 40);
		
		if (gameOver){
			g.setColor(Color.RED);
			g.setFont(g.getFont().deriveFont(50.0f));
			g.drawString("GAME OVER!!!", 300, 300);
		}
	}	
}
