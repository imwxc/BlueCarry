

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.omg.CORBA.IMP_LIMIT;


public class Ball extends Shape{
	
	public int x, y, r;
	public Color color;

	
	Random ron=new Random();
	int r1 = ron.nextInt(255);
	int b1 = ron.nextInt(255);
	int g1 = ron.nextInt(255);
	
	ArrayList<Shape> List;
	int move = 5;

	public Ball() {
		
	}
	
	public Ball(int x, int y, int r, Color color,ArrayList<Shape> List) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.color = color;
		this.List = List;
	}
	//擦除痕迹方法
	public void clearBall(Graphics g,Color c) {
		g.setColor(c);
		g.fillOval(x, y - move, r, r);
	}
//	ImageIcon imgb = new ImageIcon("image/ball.png"); 
	
	//画球
	public void drawBall(Graphics g) {
		
//		g.drawImage(imgb.getImage(), x, y, null);
		g.setColor(new Color(r1, g1, b1));
		g.fillOval(x, y, r, r);
	}
	
	public void moveBall(ArrayList<Shape> List) {
			y += move;
		if (y < 10 || y > 450) {
			move = -move;
		}
	}
	
	public void boomBall(ArrayList<Shape> List){
		
		for(int i=0;i< List.size();i++){
			
			Ball ball = (Ball)List.get(i);
				if(this != ball){
					
				if ((ball.x-x)*(ball.x-x)+(ball.y-y)*(ball.y-y)<Math.pow(r, 2)) {
					
	                this.move = -move;
	                
	                ball.move = -move;
	                
				}
            }
		}
	}
		
}
