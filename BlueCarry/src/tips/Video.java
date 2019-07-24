package tips;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Video {
	
	public void init() {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(3);
		jf.setSize(800, 800);
		jf.setVisible(true);
		jf.setLayout(null);
		JButton b=new JButton("结束");
		b.setBounds(0, 0, 100, 54);
		b.setPreferredSize(new Dimension(100,54));
		jf.add(b);
			
		Graphics g = jf.getGraphics();
		
		//获取默认的摄像头
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		webcam.open();
		while(true) {
			//从摄像头获取一张图片
			BufferedImage img = webcam.getImage();
			if(img != null) {
				//字节数组输出流
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				try {
					int width = img.getWidth();
					int height = img.getHeight();
					
					//处理后的图片
					BufferedImage img2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
					Graphics g2 = img2.getGraphics();
					
					for(int i=0; i<width; i++) {
						for(int j=0; j<height; j++) {
							//特效处理
							Color color = new Color(img.getRGB(i, j));
							
							int v = (color.getRed() * 77 + color.getGreen() * 150 + color.getBlue() * 29 + 128)>>8;//灰度处理（保留光线强度）
						
							g2.setColor(new Color(v));
							g2.drawLine(width-i, j, width-i, j);
						}
					}
					
					
					
					
					//把图片写入到bos输出流
//					ImageIO.write(img, "jpg", bos);
					
					g.drawImage(img2, 100, 100, null);
					
					//把图片转成字节
//					byte[] bytes = bos.toByteArray();
					//通过upd把bytes发送到对方
					//....
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				//
//				byte[] buffer = new byte[1000];
//				//字节数组输入流
//				ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
//				try {
//					BufferedImage recvImg = ImageIO.read(bis);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		}
		
	}

	public static void main(String[] args) {
		new Video().init();
	}

}
