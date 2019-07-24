

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class BallV  extends Shape{
	public int x=100, y=10, r;
	public Color color;
	public JFrame frame;
	ArrayList<Shape> List;
	int move = 5;

	public BallV() {}
	public BallV( int r, Color color, JFrame frame,ArrayList<Shape> List) {
		this.r = r;
		this.color = color;
		this.frame = frame;
		this.List = List;
	}

	public void clearBallV(Graphics g) {
		
		g.setColor(frame.getContentPane().getBackground());
		g.fillOval(x, y - move, r, r);
	}
	ImageIcon imgb = new ImageIcon("image01/6.png"); 
	
	public void drawBallV(Graphics g) {
		for(int i=0;i<5;i++){
			g.drawImage(imgb.getImage(), x, y, null);
			x+=100;
		}
			g.setColor(Color.BLACK);
			g.fillOval(x, y, r, r);
	}
	public void moveBallV(ArrayList<Shape> List) {
			y += move;
		if (y > frame.HEIGHT ) {
		
		}
	}
	public void boomBallV(ArrayList<Shape> List){
		for(int i=0;i< List.size();i++){
			BallV ballv = (BallV)List.get(i);
				if(this != ballv){
				if ((ballv.x-x)*(ballv.x-x)+(ballv.y-y)*(ballv.y-y)<Math.pow(r, 2)) {
//					System.out.println("x="+x+"\ty="+y);
	                this.move = -move;
	                ballv.move = -move;
			}
            }
		}
	}
}
