package UDPChatRoom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class OIP {
	public static BufferedImage opencv_face(BufferedImage img,BufferedImage img2) {
		Mat mat_img=M2I.bufImg2Mat(img);
		System.out.println("Running DetectFace ... ");
	        // 从配置文件lbpcascade_frontalface.xml中创建一个人脸识别器，该文件位于opencv安装目录中
	        CascadeClassifier faceDetector = new CascadeClassifier("E:\\Java\\Libs\\opencv_4.0.1\\opencv\\sources\\data\\haarcascades\\"
	        + "haarcascade_frontalface_alt.xml");
	        // 在图片中检测人脸
	        MatOfRect faceDetections = new MatOfRect();
	        faceDetector.detectMultiScale(mat_img, faceDetections);
	        Rect[] rects = faceDetections.toArray();
	        if(rects != null && rects.length >= 1){          
	            for (Rect rect : rects) {  
	                Imgproc.rectangle(mat_img, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),  
	                        new Scalar(0, 255, 255), 2);  
	            } 
	        }
	    new M2I();
		return M2I.mat2BI(mat_img);
	}
	public static void reverse(BufferedImage img,BufferedImage img2) {
		if(img!=null) {
			int width=img.getWidth();
			int height=img.getHeight();
			Graphics g2=img2.getGraphics();
			System.out.println("图象处理ing...");
			//像素点处理
			for(int i=0;i<width;i++) {
				for(int j=0;j<height;j++) {
					Color c=new Color(img.getRGB(i, j));
					g2.setColor(c);
					g2.fillRect(i, j, 20, 20);
				}
			}
		}
	}
	public static void mosaic(BufferedImage img,BufferedImage img2) {
		if(img!=null) {
			int width=img.getWidth();
			int height=img.getHeight();
			Graphics g2=img2.getGraphics();
			System.out.println("图象处理ing...");
			//像素点处理
			for(int i=0;i<width;i+=20) {
				for(int j=0;j<height;j+=20) {
					Color c=new Color(img.getRGB(i, j));
					g2.setColor(c);
					g2.fillRect(i, j, 20, 20);
				}
			}
		}
	}
	public static void dosth2img(BufferedImage img,BufferedImage img2) {
		if(img!=null) {
			int width=img.getWidth();
			int height=img.getHeight();
			Graphics g2=img2.getGraphics();
			System.out.println("图象处理ing...");
			//像素点处理
			for(int i=0;i<width;i++) {
				for(int j=0;j<height;j++) {
					int rgb_c=img.getRGB(i, j);
					Color color=new Color(rgb_c);
					int v = (color.getRed() * 77 + 
							color.getGreen() * 150 + 
							color.getBlue() * 100 + 128)>>8;//灰度处理（保留光线强度）
					g2.setColor(new Color(v));
					g2.drawLine(width-i, j, width-i, j);
				}
			}
		}
	}
	public static BufferedImage turn_img(BufferedImage img) {
		if(img!=null) {
			int width=img.getWidth();
			int height=img.getHeight();
			BufferedImage img2=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			Graphics g=img2.getGraphics();
			for(int i=0;i<width;i++) {
				for (int j=0;j<height;j++) {
					int rgb=img.getRGB(i, j);
					g.setColor(new Color(rgb));
					g.drawLine(width-i, j, width-i, j);
				}
			}
			return img2;
		}
		return null;
	}
	public static BufferedImage sobel(BufferedImage img) {
		if(img!=null) {
			Mat rgba = M2I.bufImg2Mat(img);
	        Imgproc.Sobel(rgba, rgba, CvType.CV_8U, 1, 1);//调用了C++底层方法
	        Core.convertScaleAbs(rgba, rgba, 10, 0);
	        Imgproc.cvtColor(rgba, rgba, Imgproc.COLOR_RGB2GRAY, 4); 
	        return M2I.mat2BI(rgba);
		}
		return null;
	}
	public static BufferedImage to_gray(BufferedImage img) {
		if(img!=null) {
			Mat rgb=M2I.bufImg2Mat(img);
			Imgproc.cvtColor(rgb, rgb, Imgproc.COLOR_RGB2GRAY);
//			Imgproc.Scharr(rgb, rgb,CvType.CV_8U, 1, 0);
			return M2I.mat2BI(rgb);
		}
		return null;
	}
	public static BufferedImage scharr(BufferedImage img) {
		if(img!=null) {
			Mat rgb=M2I.bufImg2Mat(img);
			Imgproc.Scharr(rgb, rgb,CvType.CV_8U, 0, 1);
//			Core.convertScaleAbs(rgb, rgb, 50, 0);
			return M2I.mat2BI(rgb);
		}
		return null;
	}
	public static BufferedImage resizeBufferedImage(BufferedImage source, int targetW, int targetH, boolean flag) {
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		if (flag && sx > sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		} else if(flag && sx <= sy){
			sy = sx;
			targetH = (int) (sy * source.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else {
			target = new BufferedImage(targetW, targetH, type);
		}
		Graphics2D g = target.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}
}
