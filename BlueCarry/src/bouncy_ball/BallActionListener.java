package bouncy_ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class BallActionListener implements Runnable, MouseListener,KeyListener{

	JFrame f;
	Graphics g;
	ArrayList<Ball> array; 
	BackGround back=new BackGround();

	public BallActionListener(JFrame f,ArrayList array) {
		this.f=f;
		this.array=array;
		this.array.add(new Ball(2*f.getHeight()/3,f.getWidth()/2,20,f));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Ball b=new Ball(e.getX(),e.getY(),20,array,f);
		array.add(b);
	}
	@Override
	public void run() {
		while(true) {
			for(int i=0;i<array.size();i++) {
				Ball b=array.get(i);
				if(!b.equals(array.get(0))) {
					b.crash(array);
				}else {
					b.crash(array);
				}
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}		
		}	
	}
	@Override
	public void mouseReleased(MouseEvent e) {

		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char str=e.getKeyChar();
		array.get(0).keybodardControl(str);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	
}
