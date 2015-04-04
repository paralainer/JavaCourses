package game1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Window extends JFrame implements KeyListener{
	
	private Field field;

	public Window(){
		addKeyListener(this);
		setFocusable(true);
		
		setBounds(0, 0, 800, 600);
		setTitle("Новогодний дождь");
		
		
		field = new Field();
		getContentPane().add(field);
		
		
		setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_ESCAPE: System.exit(0); break;
			case KeyEvent.VK_LEFT: field.direction = 'l'; break;
			case KeyEvent.VK_RIGHT: field.direction = 'r'; break;
			case KeyEvent.VK_C: field.gameOver = false; break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		field.direction = 0;
	}
}
