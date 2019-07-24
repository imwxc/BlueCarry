package UDPChatRoom;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class M2I {

    /**
     * Mat转换成BufferedImage
     * 
     * @param matrix
     *            要转换的Mat
     * @param fileExtension
     *            格式为 ".jpg", ".png", etc
     * @return
     */
	public static BufferedImage mat2BI(Mat mat){  
        int dataSize =mat.cols()*mat.rows()*(int)mat.elemSize();  
        byte[] data=new byte[dataSize];
        mat.get(0, 0,data);  
        int type=mat.channels()==1?  
                BufferedImage.TYPE_BYTE_GRAY:BufferedImage.TYPE_3BYTE_BGR;  
        if(type==BufferedImage.TYPE_3BYTE_BGR){  
            for(int i=0;i<dataSize;i+=3){  
                byte blue=data[i+0];  
                data[i+0]=data[i+2];  
                data[i+2]=blue;  
            }  
        }  
        BufferedImage image=new BufferedImage(mat.cols(),mat.rows(),type);  
        image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);     
        return image;  
    }  

    /**
     * BufferedImage转换成Mat
     * 
     * @param src
     *            要转换的BufferedImage
     * @param matType
     *            转换成mat的type 如 CvType.CV_8UC3
     */
	public static Mat bufImg2Mat(BufferedImage src) {
		if(src.getType() != BufferedImage.TYPE_3BYTE_BGR) {
			BufferedImage image = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
			image.getGraphics().drawImage(src, 0, 0, null);
			src = image;
		}
		
		WritableRaster raster = src.getRaster();
		DataBufferByte buffer = (DataBufferByte)raster.getDataBuffer();
		byte[] pixels = buffer.getData();
        Mat mat = Mat.eye(src.getHeight(), src.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, pixels);
        return mat;
	}
}