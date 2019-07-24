

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.text.StyledEditorKit.ForegroundAction;

public class BallListener extends MouseAdapter implements Runnable ,KeyListener{
	public JFrame frame;
	public Graphics g;
	public ArrayList<Shape> List;
	BackGround backGround = new BackGround(g);

	public BallListener(JFrame frame, ArrayList<Shape> List) {
		this.frame = frame;
		this.List = List;
	}

	public void mousePressed(MouseEvent e) {
		
		Ball ball = new Ball(e.getX(), e.getY(),28, Color.BLACK, List);
		List.add(ball);
	}
	public void run() {
		
		while (true) {
			if (g == null)
				g = frame.getGraphics();// ��ȡ����
//				backGround.drawBack(g);
//				backGround.moveBack();
				// ѭ������ballList�������
			for (int i = 0; i < List.size(); i++) {
				Ball ball =(Ball)List.get(i);
				ball.clearBall(g,frame.getBackground());
				ball.drawBall(g);
				ball.moveBall(List);
				ball.boomBall(List);
			}System.out.println(List.size());
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
