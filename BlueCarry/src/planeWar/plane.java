package planeWar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;

public class plane {
	CopyOnWriteArrayList<bullet> bullets=new CopyOnWriteArrayList<bullet>();
	private int x,y;
	private int r;
	private int x_speed;
	private int y_speed;
	private boolean flag;
	ImageIcon enemy;
	ImageIcon hero;
	private Random random;
	public int getx() {
		return this.x;
	}
	public plane() {
		enemy=new ImageIcon("E:/Java/BlueCarry/src/planeWar/enemy1.png");
		this.random=new Random();
		this.r=10;
		this.x=random.nextInt(600);
		this.y=random.nextInt(400);
		this.x_speed=5+random.nextInt(20);
		this.y_speed=5+random.nextInt(10);
	}
	public plane(int x,int y,int x_speed,int y_speed) {
		this.x=x;
		this.y=y;
		this.x_speed=x_speed;
		this.y_speed=y_speed;
		hero=new ImageIcon("e:/Java/BlueCarry/src/planeWar/hero12.png");
	}
	public  void drawPlane(Graphics g,Color c) {
		g.drawImage(enemy.getImage(), x, y, enemy.getImageObserver());
	}
	public void drawMyplane(Graphics g) {
		g.drawImage(hero.getImage(), x, y, hero.getImageObserver());
	}
	public void cleanPlane(Graphics g,Color c) {
		g.setColor(c);
		g.fillOval(x, y-y_speed, r, r);
	}
	public  synchronized void movePlane() {
		this.y+=this.y_speed;
	}
	public synchronized void moveMyplane(MouseEvent e) {
		int movex=10;
		int movey=10;
//		if(e.getKeyChar()=='w') {
//			this.y-=y_speed;
//		}else if(e.getKeyChar()=='a') {
//			this.x-=x_speed;
//		}else if(e.getKeyChar()=='s') {
//			this.y+=y_speed;
//		}else if(e.getKeyChar()=='d') {
//			this.x+=x_speed;
//		}
		this.x=e.getX();
		this.y=e.getY();
	}
	public boolean destory() {
		if(x>=1000||y>=1000) {
			return true;
		}
		return false;	
	}
}
