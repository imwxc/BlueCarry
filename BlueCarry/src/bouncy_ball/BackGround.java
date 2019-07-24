package bouncy_ball;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class BackGround extends Shape{

	private int x=0;
	private int y=0;
	private int x_speed=5;
	private int y_speed=5;
	ImageIcon img=new ImageIcon("src/image.png");
	
	public BackGround() {
		
	}
	public BackGround(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public void drawImage(Graphics g) {
		g.drawImage(img.getImage(), x, y, null);
	}
	public void moveIMage() {
		y+=y_speed;
		x+=x_speed;
		if(y<0||y>500) {
			y_speed=-y_speed;
		}
		if(x<0||x>500) {
			x_speed=-x_speed;
		}
	}
}
