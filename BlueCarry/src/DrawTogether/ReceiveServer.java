package DrawTogether;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class ReceiveServer extends Thread{
	SocketAddress localadd;
	DatagramSocket receive;
	Graphics g;
	int x1,y1,x2,y2,x3,y3;
	int[] x_points;
	int[] y_points;
	int shape;
	Color c;
	NumUtil util;
	ReceiveServer(Graphics g){
		this.g=g;
		util=new NumUtil();
		init();
	}
	private void init(){
		try {
			localadd=new InetSocketAddress("192.168.31.93",1234);
			if(localadd!=null) {
				System.out.println("接收端监听端口："+localadd);
				receive=new DatagramSocket(localadd);
			}
		} catch (SocketException e) {
			System.out.println("通信建立失败");
			e.printStackTrace();
		}
		x_points=new int[3];
		y_points=new int[4];
	}
	//读取数据
	private void receiveData(byte[] buffer) {
		
		int[] points=util.byte_transformto_int(buffer);//所有数据的int值
		for(int i=0;i<points.length;i++) {
			System.out.println("points["+i+"]:"+points[i]);
		}
//		System.out.println("形状："+points[0]);
		shape=points[0];
		c=new Color(points[1]);
		x_points[0]=points[2];
		y_points[0]=points[3];
		x_points[1]=points[4];
		y_points[1]=points[5];
		Draw();
	}
	public void Draw() {
		switch (shape) {
			case 0:{//画线
				g.setColor(c);
				g.drawLine(x_points[0], y_points[0], x_points[1], y_points[1]);
				break;
			}
			case 2:{//画长方形
				g.setColor(c);
				int x=x_points[0];
				int y=y_points[0];
//				int width=Math.abs(y_points[1]-y);
//				int height=Math.abs(x_points[1]-x);
				g.drawRect(x, y,x_points[1], y_points[1]);
				break;
			}
			case 1:{//画圆
				g.setColor(c);
//				int width=Math.abs(this.x_points[0]-this.x_points[1]);
//				int height=Math.abs(this.y_points[0]-this.y_points[1]);
				g.drawOval(x_points[0],y_points[0], x_points[1], y_points[1]);
				break;
			}
			case 3:{//画三角形
				g.setColor(c);
				int[] x_points=new int[3];
				int[] y_points=new int[3];
				x_points[0]=this.x_points[0];
				y_points[0]=this.y_points[1];
				x_points[1]=this.x_points[1];
				y_points[1]=this.y_points[1];
				x_points[2]=(this.x_points[0]+this.x_points[1])/2;
				y_points[2]=this.y_points[0];
				Polygon p=new Polygon(x_points,y_points,3);
				g.drawPolygon(p);
				break;
			}
			default:{
				
			}
		}
			
	}
	public void run() {
		while(receive!=null) {
			byte[] buffer=new byte[50];//缓冲区
			DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);//接受数据包
			try {
				receive.receive(receivePacket);
				System.out.println("接收形状："+util.byte_transformto_int(buffer)[0]);
			} catch (IOException e) {
				System.out.println("接收数据包错误："+e.getMessage());
//				e.printStackTrace();
			}
			receiveData(buffer);
			System.out.println("x1:"+x1+"  y1:"+y1+"  x2:"+x2+"  y2："+y2);
		}
	}
}
