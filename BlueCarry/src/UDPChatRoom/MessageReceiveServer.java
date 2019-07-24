package UDPChatRoom;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;;

//接收方
//发送方IP192.168.31.222
public class MessageReceiveServer extends Thread{
	SocketAddress add;
	DatagramSocket recevie;
	ChatrRoom mychatroom;
	ArrayList<String> messageList;
	public static String ip_add="192.168.31.94";
	public static int port=5465;
	private Document doc;
	private static int message_location=0;
	private SimpleAttributeSet att;
	MessageReceiveServer(ChatrRoom chatroom){
		mychatroom=chatroom;
//		messageList=new ArrayList();
	}
	public void creat_client() {
		try {
			add=new InetSocketAddress(ip_add,port);
			recevie=new DatagramSocket(add);
		} catch (IOException e) {
			System.out.print("消息客户端建立失败 ");
		}
	}
	public void run() {
		receive_data();
	}
	public void receive_data(){
		att=new SimpleAttributeSet();
		 while(recevie!=null){
			 doc=mychatroom.textPane1.getDocument();
			 byte[] buffer  = new byte[100];
			 //3.指定接收缓冲区大小:每个包100字节      
			 //4.创建接收数据包对象
			 DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			 //5.阻塞等待数据到来,如果收到数据,存入packet中的缓冲区中 
			 System.out.println("UDP服务器等待接收数据:");
			 try {
				recevie.receive(packet);
				if(recevie!=null) {
					System.out.println(recevie);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //6.在此等待接收对方发的UDP包        
			 //得到发送方的IP和端口        
			 //转换接收到的数据为字符串        
			 String msg;
			try {
				msg = new String(packet.getData(),"GBK").trim();
				msg+="\n";
				messageList.add(msg);
				doc.insertString(message_location, msg,att);
				mychatroom.textPane1.setDocument(doc);
//				String m="";
//				for(String message:messageList) {
//					m+=(message+"\n");
//					mychatroom.textPane1.setText(m);
//				}
//				addMessage(mychatroom.textField,msg);
				//将消息队列中的消息进行打印，然后试图实现双方消息的显示
			} catch (UnsupportedEncodingException e) {
				System.out.println("消息转码错误"+e.getMessage());
				e.printStackTrace();
			} catch (BadLocationException e) {
				System.out.println("接受消息文档添加错误"+e.getMessage());
				e.printStackTrace();
			}
		 }
	}
//	private void addMessage(JTextPane textField,String message) {
//		Document d=textField.getDocument();
//		SimpleAttributeSet a = new SimpleAttributeSet();
//        StyleConstants.setFontSize(a,24);
//		try {
//			d.insertString(d.getLength(), message, a);
//		} catch (BadLocationException e) {
//			e.printStackTrace();
//		}
//	}
}