package bouncy_ball;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class Ball extends Shape {

	int x;
	int y;
	int r;
	int y_speed=20;
	int x_speed=20;
	JFrame f;

	Color c=randomColor();
//	ImageIcon img=new ImageIcon("image/image.png");
	public Ball(int x,int y,int r,JFrame f) {
		this.x=x;
		this.y=y;
		this.r=r;
		this.f=f;
	}
	public Ball(int x,int y,int r,ArrayList<Ball> array,JFrame f) {
		this.x=x;
		this.y=y;
		this.r=r;
		this.y_speed=randomSpeed();
		this.x_speed=randomSpeed();
		this.f=f;
	}
	public Color randomColor() {
		Random r=new Random();
		int R=r.nextInt(255);
		int G=r.nextInt(255);
		int B=r.nextInt(255);
		Color c=new Color(R,G,B);
		System.out.println(c);
		return c;
	}
	public void drawBall( Graphics g) {
//		g.drawImage(img.getImage(), x, y,null);
		g.setColor(c);
		g.fillOval(x, y, r, r);
	}
	public void cleanBall(Graphics g,Color c,int[] ps) {
		g.setColor(c);
		g.fillOval(ps[0], ps[1], r, r);
//		g.fillOval(this.x-x_speed, this.y-y_speed, r, r);
//		g.fillOval(this.x, this.y-y_speed, r, r);
//		g.fillOval(this.x-x_speed, this.y, r, r);
	}
	public int[] move() {
		int[] ps= {0,0};
		ps[0]=x;
		ps[1]=y;
		y+=this.y_speed;
		x+=x_speed;
		if(y<-10||y>f.getHeight()-10) {
			this.y_speed=-this.y_speed;
		}
		if(x<-10||x>f.getWidth()-10) {
			this.x_speed=-this.x_speed;
		}
		return ps;
	}
	public void crash(ArrayList<Ball> array) {
		for(int i=0;i<array.size();i++) {
			Ball b=array.get(i);
			if(this!=b) {
				if((b.x-x)*(b.x-x)+(b.y-y)*(b.y-y)<Math.pow(r, 2)) {
					this.y_speed=-this.y_speed;
					b.y_speed=-b.y_speed;
				}
			}
		}
		
	}
	public int randomSpeed() {
		Random r=new Random();
		int s=r.nextInt(20);
		Boolean b;
		b=r.nextBoolean();
		if(b) {
			s=s;
		}else {
			s=-s;
		}
		return s;
	}

	public void cleanKeyBall(Graphics g,Color c) {
		g.setColor(c);
//		g.fillOval(loc[0], loc[1], r, r);
		g.fillOval(this.x-x_speed, this.y, r, r);
		g.fillOval(this.x+x_speed, this.y+y_speed, r, r);
		g.fillOval(this.x-x_speed, this.y-y_speed, r, r);
		g.fillOval(this.x, this.y-y_speed, r, r);
		
		g.fillOval(this.x, this.y+y_speed, r, r);
		g.fillOval(this.x+x_speed, this.y, r, r);
	}
	public int[] keybodardControl(char str) {
		int[]loc= {x,y};
		if(str=='a') {
			x-=x_speed;
		}else if(str=='w') {
			y-=y_speed;
		}else if(str=='s') {
			y+=y_speed;
		}else if(str=='d') {
			x+=x_speed;
		}
		return loc;
	}

}
