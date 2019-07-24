package planeWar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * 在plane中加入bullet数组，只存储当前飞机的bullet。
 * 每间隔x毫秒发射一颗子弹，加入到list
 * 构造一个destroy方法销毁飞机和bullet。
 * 绘制的性能为O(n^2)
 * @author imwxc
 *
 */
public class warFrame extends JFrame {
	plane myplane;
	CopyOnWriteArrayList<plane> planeArray=new CopyOnWriteArrayList<plane>();
	CopyOnWriteArrayList<bullet> bulletArray=new CopyOnWriteArrayList<bullet>();
	ImageIcon background=new ImageIcon("E:/Java/BlueCarry/src/planeWar/background.jpg");
//	public moveThread m;
	public GameListener listener;
	public static void main(String[] agrs) {
		warFrame wf=new warFrame();
	}
	public warFrame() {
		this.setTitle("飞机大战");
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		
		this.setVisible(true);
		myplane=new plane(this.getWidth()/2,this.getHeight()/2,10,10);
		listener=new GameListener(myplane,planeArray,bulletArray,this.getGraphics());
		init();
	}
	public void init() {	
		this.addMouseListener(listener);
		this.addKeyListener(listener);
		new Thread(new Repaint(this,planeArray,bulletArray)).start();
		new Thread(new moveThread(planeArray,bulletArray,getGraphics())).start();
		
	}
	
	public void update(Graphics g) {
		
		Image offScreenImage=createImage(getWidth(), getHeight());
		Graphics goffscreen=offScreenImage.getGraphics();
		Color c=goffscreen.getColor();
		goffscreen.setColor(getBackground());
		goffscreen.fillRect(0, 0, getWidth(), getHeight());
		goffscreen.setColor(c);
		paint(goffscreen);
		goffscreen.dispose();
		g.drawImage(offScreenImage, 0, 0, null);
	}
	public void paint(Graphics g) {
		//这里画背景
		g.drawImage(background.getImage(), 0, 0, background.getImageObserver());
		g.drawImage(background.getImage(), background.getIconWidth(), 0, background.getImageObserver());
		myplane.drawMyplane(g);
		for(int i=0;i<planeArray.size();i++) {
			planeArray.get(i).drawPlane(g, Color.black);
		}
		for(int i=0;i<bulletArray.size();i++) {
			bulletArray.get(i).drawBullet(g, Color.black);
		}
		
	}
}
