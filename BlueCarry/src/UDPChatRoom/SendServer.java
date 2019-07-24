package UDPChatRoom;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;;

//接收方
//发送方IP192.168.31.222
public class SendServer{
	public static String ip_add_local="192.168.31.94";
	public static int port_local=667;
	public static String ip_add_target="192.168.31.222";
	public static int port_target_message=667;
	public static int port_target_file=668;
	public static int port_target_img=669;
	
	SocketAddress localAdd;
	DatagramSocket receive;
	SendServer(){
		creat_client();
	}
	public void creat_client() {
		try {
			localAdd=new InetSocketAddress(ip_add_local,port_local);
			receive=new DatagramSocket(localAdd);	
		} catch (IOException e) {
			System.out.print("客户端建立失败 ");
		}
	}
	public void send_str_data(String message) throws IOException {
		SocketAddress add1=new InetSocketAddress(ip_add_target,port_target_message);
		byte buffer[]=(message+"-"+message.getBytes().length).getBytes();
		DatagramPacket packet1=new DatagramPacket(buffer,buffer.length,add1);
		receive.send(packet1);
		System.out.println("发送成功");
		receive.close();
	}
	public void send_file_data(String add) {
		File f=new File(add);
		FileInputStream fin = null;
		BufferedInputStream bin;
		byte[] file_data=null;
		byte[] file_name=null;
		try {
			//数据文件发送
			fin = new FileInputStream(f);
			bin=new BufferedInputStream(fin);
			//数据头,将中文转换为utf-8编码
			String head_message=new String(f.getName().getBytes(),"utf-8")+","+(int)f.length();
			System.out.println("发送头数据包为："+head_message);
			file_data=new byte[(int) f.length()];
			file_name=head_message.getBytes();
			bin.read(file_data);//读取数据内容
			if(file_data!=null) {
				SocketAddress add1=new InetSocketAddress(ip_add_target,port_target_file);
				//数据包
				DatagramPacket data_packet=new DatagramPacket(file_data,file_data.length,add1);
				//数据头包
				DatagramPacket name_packet=new DatagramPacket(file_name,file_name.length,add1);
				//先发数据头
				receive.send(name_packet);
				Thread.sleep(15);
				//休眠15毫秒后发送数据包
				receive.send(data_packet);
				System.out.println("发送成功");
				receive.close();
			}
		} catch (FileNotFoundException e1) {
			System.out.println("文件不存在！"+e1.getMessage());
			e1.printStackTrace();
		} catch (IOException e) {
			System.out.println("读取错误！"+e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("线程休眠失败");
			e.printStackTrace();
		}	
	}
	public boolean send_img_data(BufferedImage img) {
		if(img!= null) {
			SocketAddress add1=new InetSocketAddress(ip_add_target,port_target_img);
			try {//图象未压缩！！！
				ByteArrayOutputStream bos=new ByteArrayOutputStream();
				ImageIO.write(img, "jpg", bos);
				byte[] img_bytes=bos.toByteArray();
				System.out.println("视频数组大小："+img_bytes.length);
				DatagramPacket packet1=new DatagramPacket(img_bytes,img_bytes.length,add1);
				receive.setSendBufferSize(img_bytes.length);
				receive.send(packet1);
				System.out.println("图象发送成功");
				return true;
//				receive.close();
			} catch (IOException e) {
				System.out.println("图像发送失败"+e.getMessage());
				e.printStackTrace();
			}
		}
		return false;
	}
	public void send_close() {
		receive.close();
	}
}
