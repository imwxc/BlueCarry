package UDPChatRoom;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JTextPane;

import sun.font.FontDesignMetrics;
/**
 * 气泡组件类
 * @author imwxc
 *
 */
public class JBubble extends JComponent{
	private String text=null;
	private int[] xPoints=new int[3];
	private int[] yPoints=new int[3];
	private int x,y,height;
	JTextPane jtp;
	private static int BubbleWidth=100;
	private ArrayList<String> message_array=new ArrayList<String>();;
	JBubble(String message){
		super();
		text=message;
	}
	JBubble(String message,JTextPane jtextpane){
		super();
		jtp=jtextpane;
		text=message;
	}
	JBubble(){
		super();
	}
	//初始化组件，将文字分行并且绘制出来
	public void init() {
		subString(text);//分行
		if(jtp==null) {
			jtp=new JTextPane();
		}
		height=message_array.size()*(getStringHeight(jtp.getFont(), text)+2);//设置高度
	}
	//分割字符串
	private void subString(String message) {
		int len=message.length();
		if(len<10) {
			message_array.add(message);
		}else {
			int beggin=0;
			int end=10;
			while(len>end) {
				message_array.add(message.substring(beggin, end));
				beggin+=10;
				end+=10;
			}
			message_array.add(message.substring(beggin,len));
		}
	}
	public void setText(String text) {
		this.text=text;
	}
	//重写paint
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.GREEN);
		Rectangle r= this.getBounds();
		x=r.x;
		y=r.y;
		r.width=BubbleWidth;
		r.height=height;
		xPoints[0]=BubbleWidth-10;
		yPoints[0]=height-5;
		xPoints[1]=BubbleWidth;
		yPoints[1]=height-5;
		xPoints[2]=BubbleWidth-10;
		yPoints[2]=height-15;
		g.fillPolygon(xPoints, yPoints, 3);
		g.fillRoundRect(x, y, BubbleWidth-10, height, 10, 10);
		int x=this.x-5;
		int y=this.y-5;
		for(String text:message_array) {
			g.drawString(text, x, y);
			y-=10;
		}
	}
//	public int getStringWidth(Font font,String message) {
//		return FontDesignMetrics.getMetrics(font).stringWidth(message);
//	}
	public int getStringHeight(Font font,String message) {
		return FontDesignMetrics.getMetrics(font).getHeight();
	}
	
}
