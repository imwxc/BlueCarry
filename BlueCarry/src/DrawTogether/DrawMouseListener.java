package DrawTogether;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * 
 * @author imwxc
 *画图鼠标监听器
 */
public class DrawMouseListener implements MouseListener ,ActionListener{

	int x1,y1,x2,y2;
	private Graphics g;
	private NumUtil util=new NumUtil();
	private SendServer send;
	private ReceiveServer receive;
	int shape=0;
	Color c=Color.BLACK;
	//构造方法
	DrawMouseListener(JFrame f){
		this.g=f.getGraphics();
		x1=y1=x2=y2=0;
		receive=new ReceiveServer(g);
		receive.start();
	}
	SendServer createSendServer() {
		SendServer s=SendServer.SendServer();
		return s;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x1=e.getX();
		y1=e.getY();
//		System.out.println("x1:"+x1+"   y1:"+y1);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		x2=e.getX();
		y2=e.getY();
		System.out.println("shape:"+shape+":");
		Draw(shape);
		System.out.println("发送数据...");
		try {
			send=createSendServer();
			byte[] data=int_points_packed(shape,c.getRGB(),x1, y1, x2, y2);
			for (int i:util.byte_transformto_int(data)) {
				System.out.println("数据"+i);
			}
//			System.out.println("长度："+util.byte_transformto_int(data).length);
			send.send(data);
		} catch (IOException e1) {
			System.out.println("数据发送失败："+e1.getMessage());
//			e1.printStackTrace();
		}
	}

	private void Draw(int shape) {
		switch(shape) {
			case 0:{//画直线
			g.setColor(c);
			g.drawLine(x1, y1, x2, y2);
			break;
			}
			case 1:{//画圆
			g.setColor(c);
			int width=Math.abs(x1-x2);
			int height=Math.abs(y1-y2);
			g.drawOval(x1, y1, width, height);
			break;
			}
			case 2:{//画长方形
			g.setColor(c);
			int width=Math.abs(x1-x2);
			int height=Math.abs(y1-y2);
			g.drawRect(x1, y1, width, height);
			break;
			}
			case 3:{//画三角形
			g.setColor(c);
			int[] x_points=new int[3];
			int[] y_points=new int[3];
			x_points[0]=x1;
			y_points[0]=y2;
			x_points[1]=x2;
			y_points[1]=y2;
			x_points[2]=(x1+x2)/2;
			y_points[2]=y1;
			Polygon p=new Polygon(x_points,y_points,3);
			g.drawPolygon(p);
			break;
			}
			default :{
			System.out.println("无画图参数传入:shape="+shape);
			break;
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
	public byte[] int_points_packed(int shape,int color,int x1,int y1,int x2,int y2) {
		int[] int_points={shape,color,x1,y1,x2,y2};
		byte[] byte_points=util.int_transformto_byte(int_points);
		if(byte_points!=null) {
			return byte_points;
		}
		return null;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button=(JButton) e.getSource();
		if(button.getText()!="") {
			String type=button.getText();
			if(type.equals("圆")) {
				shape=1;
			}else if(type.equals("直线")) {
				shape=0;
			}else if(type.equals("长方形")) {
				shape=2;
			}else if(type.equals("三角形")) {
				shape=3;
			}
		}else {
			c=button.getBackground();
		}
	}
}
