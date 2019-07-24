package DrawTogether;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * 
 * @author imwxc
 *发送的服务器
 *使用单实例模式：只创建一个对象
 */

public class SendServer{

	SocketAddress local;
	SocketAddress target;
	DatagramSocket packet;
	static SendServer s;
	public static SendServer SendServer() {
		if(s==null) {
			s=new SendServer();
		}
		return s;
	}
	private SendServer(){
		local=new InetSocketAddress("192.168.31.93",667);
		target=new InetSocketAddress("192.168.31.222",1555);
		init();
	}
	void init() {
		try {
			packet=new DatagramSocket(local);
		} catch (SocketException e) {
			System.out.println("数据包建立失败");
			e.printStackTrace();
		}
	}
	public void send(byte[] points) throws IOException {
		packet.send(new DatagramPacket(points,points.length,target));
	}
	
}
