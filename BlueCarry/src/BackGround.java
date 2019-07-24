import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class BackGround extends Shape {
	private int x=-200,y=0;
	private Graphics g;
	public JFrame frame;
	private static  int move = 5;
	
	ImageIcon img = new ImageIcon("image/image.png");
	
	public  BackGround (Graphics g){
		this.g = g;
	}
	public void  drawBack(Graphics g){
		g.drawImage(img.getImage(), x, y, null);
	}
	
	public void moveBack(){
			y += move;
		if (y < -410 || y >1) {
			move = -move;
			System.out.println("moveback="+move);
		}
	}
	
}
