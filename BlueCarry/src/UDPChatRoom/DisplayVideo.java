package UDPChatRoom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.util.concurrent.*;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

/**
 * 视频聊天的实现
 * @author imwxc
 *
 */
public class DisplayVideo extends Thread implements ActionListener{
	Video v;
	Webcam camera;
	SendServer send;
	SocketAddress add;
	DatagramSocket receive_server;
	
	public static String ip_add="192.168.31.94";
	public static int port=1026;
	public static int special_effects=0;
	final ExecutorService exec = Executors.newFixedThreadPool(1);
	DisplayVideo(Video v){
		this.v=v;
		create_client();
		send=new SendServer();
		camera=Webcam.getDefault();
		camera.setViewSize(WebcamResolution.VGA.getSize());
		camera.open();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
	}
	private void create_client() {
		try {
			add=new InetSocketAddress(ip_add,port);
			receive_server=new DatagramSocket(add);
			System.out.println("文件客户端开始监听："+add);
		} catch (IOException e) {
			System.out.print("视频客户端建立失败 ");
		}
	}
	public void run() {
		display();
	}
	public Webcam getCamera() {
		this.send.send_close();
		return this.camera;
	}
	 Callable<String> call = new Callable<String>() {
	        public String call() throws Exception {
	        		receive_phone_img(v.getGraphics(),receive_server);
		            return "true"; 
	        	
	        }  
	    };
	public void display() {
		Graphics g=v.getGraphics();
		while(send.send_img_data(camera.getImage())) {
			
			//从摄像头获取的图片
			BufferedImage img=camera.getImage();
			if(img!=null) {
//				BufferedImage img2=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
//				dosth2img(img,img2);
//				g.drawImage(turn_img(img), 0, 0,null);
				BufferedImage img2=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
				switch(special_effects) {
					case 0:{
//					g.drawImage(OIP.turn_img(img), 0, 0,null);
					g.drawImage(OIP.sobel(img),0,0,null);
//					g.drawImage(OIP.to_gray(img), 0, 0, null);
//					g.drawImage(OIP.scharr(OIP.turn_img(img)), 0, 0,null);
					break;
					}
					case 1:{
					OIP.dosth2img(img, img2);
					g.drawImage(img2, 0, 0,null);
					break;
					}
					case 2:{
					OIP.mosaic(img,img2);
					g.drawImage(img2, 0, 0,null);
					break;
					}
					default:{
//					g.drawImage(opencv_face(img,img2), 0, 0,null);
					break;
					}
				}
				Future<String> future = exec.submit(call);
				try {
					future.get(1,TimeUnit.MILLISECONDS);
				} catch (InterruptedException | ExecutionException | TimeoutException e) {
					continue;
				}
			}
		}
		System.out.println("发送失败");
	}
	private void receive_img(Graphics g,DatagramSocket receive_server) {
		try {
			if(receive_server.getSendBufferSize()!=0) {
			byte[] buffer=new byte[receive_server.getSendBufferSize()];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			receive_server.receive(packet);//没有收到会阻塞
			System.out.println("收到的数据长度："+buffer.length);
			ByteArrayInputStream bin=new ByteArrayInputStream(buffer);
			BufferedImage recimg=ImageIO.read(bin);
			g.drawImage(recimg, 670, 0, null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void receive_phone_img(Graphics g,DatagramSocket receive_server) {
		try {
			if(receive_server.getSendBufferSize()!=0) {
			byte[] buffer=new byte[receive_server.getSendBufferSize()];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			receive_server.receive(packet);//没有收到会阻塞
			System.out.println("收到的数据长度："+buffer.length);
			ByteArrayInputStream bin=new ByteArrayInputStream(buffer);
			BufferedImage recimg=ImageIO.read(bin);
			
			//需要重新设置图片大小
			g.drawImage(OIP.resizeBufferedImage(recimg, 192*4, 108*4, true), 670, 0, null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button=(JButton) e.getSource();
		switch (button.getText()) {
		case "马赛克":{
			special_effects=2;
			break;
		}
		case "镜像":{
			special_effects=3;
			break;
		}
		case "黑白":{
			special_effects=1;
			break;
		}
		case "颠倒":{
			special_effects=4;
			break;
		}
		default:{
			
			break;
		}
		}
	}
	
}
