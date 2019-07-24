package fractal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Version1 extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton but1=new JButton("start");
	int x = 240,y = 440;
    int d = 120,dx = 85,dy = 85;
    int time=100;
    Point[] ps= {};//初始点
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Version1 frame = new Version1();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Version1() {
		but1.setBounds(0, 0, 50, 30);
		but1.setSize(50, 30);
		but1.setPreferredSize(new Dimension(50,30));
		this.add(but1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("门格海绵");
		this.setSize(1024, 768);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		but1.addActionListener(this);
	}
//	@Override
////	public void paint(Graphics g) {
//////		super.paint(g);
////		try {
////			DrawBigCube(g);
//////			DrawSmallCube(g);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////
////	}

	public Point[] initPoint(int x,int y,int d,int dx,int dy) {
		Point p1=new Point(x,y);
		Point p2=new Point(x,y+d);
		Point p3=new Point(x+d,y+d);
		Point p4=new Point(x+d,y);
		Point p5=new Point(x+dx,y-dy);
		Point p6=new Point(x+d+dx,y-dy);
		Point p7=new Point(x+d+dx,y+d-dy);
		Point[] p= {p1,p2,p3,p4,p5,p6,p7};
		return p;
	}
	public Point[] resetPoint(Point p) {
		int x=p.x;
		int y=p.y;
		Point p1=new Point(x,y);
		Point p2=new Point(x,y+d);
		Point p3=new Point(x+d,y+d);
		Point p4=new Point(x+d,y);
		Point p5=new Point(x+dx,y-dy);
		Point p6=new Point(x+d+dx,y-dy);
		Point p7=new Point(x+d+dx,y+d-dy);
		Point[] ps= {p1,p2,p3,p4,p5,p6,p7};
		return ps;
	}
	public Point[] resetPoint(Point p,int a,int b) {
		int x=p.x;
		int y=p.y;
		Point p1=new Point(x+a,y+b);
		Point p2=new Point(p1.x,p1.y+d);
		Point p3=new Point(p1.x+d,p1.y+d);
		Point p4=new Point(p1.x+d,p1.y);
		Point p5=new Point(p1.x+dx,p1.y-dy);
		Point p6=new Point(p1.x+d+dx,p1.y-dy);
		Point p7=new Point(p1.x+d+dx,p1.y+d-dy);
		Point[] ps= {p1,p2,p3,p4,p5,p6,p7};
		return ps;
	}
	public Polygon[] OneCube(Point[] ps) {
		int[]x= {ps[0].x,ps[1].x,ps[2].x,ps[3].x,ps[4].x,ps[5].x,ps[6].x};
		int[]y={ps[0].y,ps[1].y,ps[2].y,ps[3].y,ps[4].y,ps[5].y,ps[6].y};
		
		int[]x1= {ps[0].x,ps[3].x,ps[2].x,ps[1].x};
		int[]y1= {ps[0].y,ps[3].y,ps[2].y,ps[1].y};
		int[]x2= {ps[0].x,ps[4].x,ps[5].x,ps[3].x};
		int[]y2= {ps[0].y,ps[4].y,ps[5].y,ps[3].y};
		int[]x3= {ps[2].x,ps[3].x,ps[5].x,ps[6].x};
		int[]y3= {ps[2].y,ps[3].y,ps[5].y,ps[6].y};
		Polygon m1=new Polygon(x1,y1,4);
		Polygon m2=new Polygon(x2,y2,4);
		Polygon m3=new Polygon(x3,y3,4);
		Polygon[] cubes= {m1,m2,m3};
		return cubes;
	}
	public void DrawCube(Graphics g,Polygon[] cs) {
		g.drawPolygon(cs[0]);
		g.setColor(new Color(220));
		g.fillPolygon(cs[0]);
		
		
		g.drawPolygon(cs[1]);
		g.setColor(new Color(190));
		g.fillPolygon(cs[1]);
		
		
		g.drawPolygon(cs[2]);
		g.setColor(new Color(160));
		g.fillPolygon(cs[2]);
	}
	public void drawButton_Top(Graphics g,Point[] ps) throws InterruptedException {
		DrawCube(g,OneCube(ps));
		Thread.sleep(time);
		ps=resetPoint(ps[3]);
		Thread.sleep(time);
		DrawCube(g,OneCube(ps));
		Thread.sleep(time);
		ps=resetPoint(ps[3]);
		DrawCube(g,OneCube(ps));
		Thread.sleep(time);
		ps=resetPoint(ps[0],-dx,dy);
		DrawCube(g,OneCube(ps));
		Thread.sleep(time);
		ps=resetPoint(ps[0],-2*d,0);
		DrawCube(g,OneCube(ps));
		Thread.sleep(time);
		ps=resetPoint(ps[0],-dx,dy);
		DrawCube(g,OneCube(ps));
		Thread.sleep(time);
		ps=resetPoint(ps[0],d,0);
		DrawCube(g,OneCube(ps));
		Thread.sleep(time);
		ps=resetPoint(ps[3]);
		DrawCube(g,OneCube(ps));
		Thread.sleep(time);
	}
	public void drawMiddle(Graphics g,Point[] ps) throws InterruptedException {
		DrawCube(g,OneCube(ps));
		Thread.sleep(time);
		ps=resetPoint(ps[3],d,0);
		DrawCube(g,OneCube(ps));
		Thread.sleep(time);
		ps=resetPoint(ps[0],-2*dx,2*dy);
		DrawCube(g,OneCube(ps));
		Thread.sleep(time);
		ps=resetPoint(ps[0],-2*d,0);
		DrawCube(g,OneCube(ps));
		Thread.sleep(time);
	}
	public void DrawBigCube(Graphics g) throws InterruptedException {
		ps=initPoint(x,y,d,dx,dy);
		drawButton_Top(g,ps);
		ps=resetPoint(ps[0],0,-d);
		drawMiddle(g,ps);
		ps=resetPoint(ps[0],0,-d);
		drawButton_Top(g,ps);
//		resetLenth();
	}
	public void DrawSmallCube(Graphics g) throws InterruptedException {
		resetLenth();
		ps=initPoint(x,y,d,dx,dy);
		drawButton_Top(g,ps);
		ps=resetPoint(ps[0],0,-d);
		drawMiddle(g,ps);
		ps=resetPoint(ps[0],0,-d);
		drawButton_Top(g,ps);
	}
	public void resetLenth() {
		this.d=this.d/3;
		this.dx=this.dx/3;
		this.dy=this.dy/3;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Graphics g=this.getGraphics();
		try {
			DrawBigCube(g);
			DrawSmallCube(g);
		} catch (InterruptedException ee1) {
			// TODO Auto-generated catch block
			ee1.printStackTrace();
		}

	}
}
