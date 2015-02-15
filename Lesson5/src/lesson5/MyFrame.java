package lesson5;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {

	public MyFrame(int size) {
		MyPanel panel = new MyPanel();
		Container container = getContentPane();
		container.add(panel);

		setBounds(100, 100, size, size);
		setVisible(true);
	}
}

class MyPanel extends JPanel {

	@Override
	public void paintComponent(Graphics gr) {
		drawBackground(gr);
		drawSnowman(gr);
		drawCristmasTree(gr);
	}
	
	private void drawCristmasTree(Graphics gr){
		gr.setColor(Color.GRAY);
		gr.fillRect(250, 20, 10, 200);
		gr.setColor(Color.GREEN);

		int offset = 0;
		for (int i = 0; i < 4; i++) {
			gr.fillRect(245 - i * 10, 10 + offset, 20 + i * 20, 20 + i * 20);

			offset += 20 + i * 20;
		}
		
			
		for (int i = 0; i < 50; i++) {
			gr.setColor(
					new Color(
							(int)(Math.random() * 255), 
							(int)(Math.random() * 255), 
							(int)(Math.random() * 255))
					);
			
			
			gr.fillOval(
					230 + (int)(Math.random() * 40), 
					(int)(Math.random() * 150), 10, 10);

			
		}	
	}

	private void drawBackground(Graphics gr) {
		gr.setColor(Color.BLUE);
		gr.fillRect(0, 0, 500, 500);
	}

	private void drawSnowman(Graphics gr) {
		drawSnowmansBody(gr);
		drawSnowmansNose(gr);
	}

	private void drawSnowmansNose(Graphics gr) {
		gr.setColor(Color.YELLOW);
		gr.drawLine(80, 15, 95, 25);
		gr.drawLine(80, 20, 95, 25);
	}
	
	private void drawSnowmansBody(Graphics gr) {
		gr.setColor(Color.WHITE);

		int offset = 0;
		for (int i = 0; i < 3; i++) {
			gr.fillOval(70 - i * 10, 10 + offset, 20 + i * 20, 20 + i * 20);

			offset += 20 + i * 20;
		}
	}
}