package lesson6;

import java.awt.Graphics;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

	public MyFrame(){
		getContentPane().add(new MyImgPanel());
		setBounds(0, 0, 1000, 700);
		setVisible(true);
	}
}
