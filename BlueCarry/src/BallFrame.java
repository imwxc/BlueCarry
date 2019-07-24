

import java.util.ArrayList;

import javax.swing.JFrame;

public class BallFrame extends JFrame {

	public static void main(String[] args) {
		BallFrame bf = new BallFrame();
		bf.initUI();
		System.out.println(Thread.currentThread());
	}

	public void initUI() {
		this.setTitle("����");
		this.setSize(600, 500);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		BallListener bl = new BallListener(this,List);
		
		this.addMouseListener(bl);
		
		Thread t = new Thread(bl);
			t.start();
	}
	
	public ArrayList<Shape> List = new ArrayList<Shape>();
}
