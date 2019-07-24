package UDPChatRoom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import javax.swing.JDialog;

public class FileReceiveServer extends Thread{

	SocketAddress add;
	DatagramSocket receive_server;
	ChatrRoom mychatroom;
	//默认的存储位置
	public static String File_add="D:\\";
	
	public static String ip_add="192.168.31.94";
	public static int port=5455;
	
	
	FileReceiveServer(ChatrRoom c){
		this.creat_client();
		this.mychatroom=c;
	}
	public void creat_client() {
		try {
			add=new InetSocketAddress(ip_add,port);
			receive_server=new DatagramSocket(add);
			System.out.println("文件客户端开始监听："+add);
		} catch (IOException e) {
			System.out.print("文件客户端建立失败 ");
		}
	}
	public void run() {
		receive_data();
	}
	public void receive_data(){
		 while(true){ 
			 //数据包头缓冲区
			 byte[] buffer  = new byte[50];
			 //数据包头
			 DatagramPacket head_packet = new DatagramPacket(buffer, buffer.length);  
			 try {
				receive_server.receive(head_packet);
				System.out.println("收到数据包头：");
				//收到数据包头之后解析
				String file_name=getName_Len(buffer)[0];
				System.out.println(getName_Len(buffer)[1]);
				int file_len=Integer.parseInt(getName_Len(buffer)[1].trim());
				System.out.println("文件名："+file_name+" 长度："+file_len);
				//根据解析的信息来接受文件
				byte[] file_data=new byte[file_len];//文件内容的缓存
				DatagramPacket data_packet = new DatagramPacket(file_data, file_data.length);
				receive_server.receive(data_packet);
				//将或得到的数据写入文件
				File f=new File(File_add+"\\"+file_name);
				FileOutputStream fout=new FileOutputStream(f);
				fout.write(file_data);
				fout.close();
				if(f.exists()) {
					JDialog tip=new JDialog(this.mychatroom,"文件接受成功");
					tip.setDefaultCloseOperation(2);
					tip.setBounds(100, 100, 100, 80);
					tip.show();
				}
			} catch (Exception e) {
//				e.printStackTrace();
			}
		 }
	}
	private String[] getName_Len(byte[] buffer){
		try {
			String m = new String(buffer,"GBK");
			System.out.println(m);
			return m.split(",");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
