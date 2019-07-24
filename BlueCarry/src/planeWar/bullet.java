package planeWar;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

public class bullet {

	private int x,y;
	private int r;
	private int x_speed;
	private int y_speed;
	private Random random=new Random();
	
	ImageIcon img=new ImageIcon("");
	
	public bullet() {
		this.x=random.nextInt(1000);
		this.y=1000-random.nextInt(300);
		this.r=10;
		this.x_speed=5;
		this.y_speed=5;
	}
	public  void drawBullet(Graphics g,Color c) {
		g.setColor(c);
		g.fillOval(x, y, r, r);
	}
	public void cleanBullet(Graphics g,Color c) {
		g.setColor(c);
		g.fillOval(x, y+y_speed, r, r);
	}
	public  synchronized void moveBullet() {
		this.y-=y_speed;
	}
	public boolean destory() {
		if(x>=1000||y>=1000) {
			return true;
		}
		
		return false;
		
	}
}
