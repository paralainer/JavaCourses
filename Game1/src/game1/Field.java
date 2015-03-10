package game1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Field extends JPanel {
	
	private Image background;
	private Image cap;
	
	private int x = 375;
	
	public char direction = 0;
	
	public Field(){		
		try {
			background = ImageIO.read(new File("background.jpg"));
			cap = ImageIO.read(new File("cap.png"));
		}catch(Exception e){}
		
		Timer drawTimer = new Timer(20, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {			
				if (direction == 'l'){
					x -= 5;
				} else if (direction == 'r'){
					x += 5;
				}
				
				repaint();		
			}		
		});
 
		drawTimer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);	
		g.drawImage(cap, x, 480, null);
	}	
}
