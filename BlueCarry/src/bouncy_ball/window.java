package bouncy_ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;


import javax.swing.JFrame;

public class window extends JFrame {


	

	ArrayList<Ball> array=new ArrayList<Ball>();
	private BackGround back=new BackGround();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
					window frame = new window();	
//					frame.showwindow();
					
			
		}

	/**
	 * Create the frame.
	 */
	public window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 601);
		this.setTitle("BouncyBall");
		this.setResizable(false);
		setVisible(true);
		BallActionListener l=new BallActionListener(this,array);
		this.addMouseListener(l);
		this.addKeyListener(l);
		new Thread(l).start();
		new Thread(new Repaint()).start();
	}
	

	private class Repaint implements Runnable{
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				repaint();
			}
			
		}
		
	}
	public void update(Graphics g) {
		Image offScreenImage=createImage(getWidth(), getHeight());
		Graphics goffscreen=offScreenImage.getGraphics();
		Color c=goffscreen.getColor();
		goffscreen.setColor(getBackground());
		goffscreen.fillRect(0, 0, getWidth(), getHeight());
		goffscreen.setColor(c);
		paint(goffscreen);
		goffscreen.dispose();
		g.drawImage(offScreenImage, 0, 0, null);
	}
	public void paint(Graphics g) {
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(back.img.getImage(), 0, 0,null);
		for(int i=0;i<array.size();i++) {
			if(!array.get(i).equals(array.get(0))) {
				array.get(i).cleanBall(g,getBackground(),array.get(i).move());
				array.get(i).drawBall(g);
			}else {
				array.get(i).cleanKeyBall(g, getBackground());
				array.get(i).drawBall(g);
			}	
		}
	}
}
