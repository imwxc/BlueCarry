package UDPChatRoom;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class intnet_Control {

	Robot robot;
	Toolkit toolkit;
	Dimension d;
	Rectangle r;
	
	BufferedImage screen;
	JFrame frame=new JFrame() {
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(screen, 0, 0, null);
		}
	};
	public void init() {
		try {
			robot=new Robot();
		} catch (AWTException e) {
			System.out.println("robot获取失败");
			e.printStackTrace();
		}
		toolkit=Toolkit.getDefaultToolkit();
		d=toolkit.getScreenSize();
		r=new Rectangle(d);
	}
	public void init_frame() {
		frame.setSize(800,800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public void move_mouse() {
		for (int i=0;i<100;i+=10) {
			robot.mouseMove(500, i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String agrs[]) throws AWTException {
		Robot robot=new Robot();
		Toolkit toolkit;
		Dimension d;
		Rectangle r;
		BufferedImage screen;
		toolkit=Toolkit.getDefaultToolkit();
		d=toolkit.getScreenSize();
		r=new Rectangle(d);
		screen=robot.createScreenCapture(r);
		JFrame frame=new JFrame() {
			public void paint(Graphics g) {
				super.paint(g);
				g.drawImage(screen, 0, 0, null);
			}
		};
		frame.setSize(800,800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		for (int i=0;i<100;i+=2) {
			robot.mouseMove(500, i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		frame.getGraphics().drawImage(screen, 0, 0, null);
	}
}
