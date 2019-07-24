package tips;


import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.ml.SVM;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.HOGDescriptor;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
  
public class CaptureBasic extends JPanel {  
      
    private BufferedImage mImg;  
      
    private BufferedImage mat2BI(Mat mat){  
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
  
    public void paintComponent(Graphics g){  
        if(mImg!=null){  
            g.drawImage(mImg, 0, 0, mImg.getWidth(),mImg.getHeight(),this);  
        }  
    }  
      
    public static void main(String[] args) {  
        try{  
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  
              
            Mat capImg=new Mat();  
            VideoCapture capture=new VideoCapture(0);
            int height = (int)capture.get(Videoio.CAP_PROP_FRAME_HEIGHT);  
            int width = (int)capture.get(Videoio.CAP_PROP_FRAME_WIDTH);  
            if(height==0||width==0){
                throw new Exception("camera not found!");  
            } 
            JFrame frame=new JFrame("camera");  
            frame.setDefaultCloseOperation(3);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  
            CaptureBasic panel=new CaptureBasic();
            frame.setContentPane(panel);  
            frame.setVisible(true);  
            frame.setSize(width+frame.getInsets().left+frame.getInsets().right,  
                    height+frame.getInsets().top+frame.getInsets().bottom);  
            int n=0;
            Mat temp=new Mat();
            while(frame.isShowing()&& n<500){
                capture.read(capImg);
                Imgproc.cvtColor(capImg, temp, Imgproc.COLOR_RGB2GRAY);
                panel.mImg=panel.mat2BI(detectFace(capImg));
                panel.repaint();
            }  
            capture.release();  
            frame.dispose();  
        }catch(Exception e){  
            System.out.println("例外：" + e);  
        }finally{  
            System.out.println("--done--");  
        }  
  
    }
    /**
     * opencv实现人脸识别
     * @param mat_img
     */
    public static Mat detectFace(Mat mat_img) throws Exception
    {

        System.out.println("Running DetectFace ... ");
        // 从配置文件lbpcascade_frontalface.xml中创建一个人脸识别器，该文件位于opencv安装目录中
        CascadeClassifier faceDetector = new CascadeClassifier("E:\\Java\\Libs\\opencv_4.0.1\\opencv\\sources\\data\\haarcascades\\"        														+ "haarcascade_smile.xml");
        // 在图片中检测人脸
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(mat_img, faceDetections);
        //System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

        Rect[] rects = faceDetections.toArray();
        if(rects != null && rects.length >= 1){          
            for (Rect rect : rects) {  
                Imgproc.rectangle(mat_img, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),  
                        new Scalar(0, 0, 255), 2);  
            } 
        }
        return mat_img;
    }
    /**
     * opencv实现人型识别，hog默认的分类器。所以效果不好。
     * @param img
     */
    public static Mat detectPeople(Mat img) {
        //System.out.println("detectPeople...");
        if (img.empty()) {  
            System.out.println("image is exist");  
        }
        HOGDescriptor hog = new HOGDescriptor();
        hog.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());
//        System.out.println(HOGDescriptor.getDefaultPeopleDetector());
        //hog.setSVMDetector(HOGDescriptor.getDaimlerPeopleDetector());
        MatOfRect regions = new MatOfRect();
        MatOfDouble foundWeights = new MatOfDouble();
        //System.out.println(foundWeights.toString());
        hog.detectMultiScale(img, regions, foundWeights);
        for (Rect rect : regions.toArray()) {             
            Imgproc.rectangle(img, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(0, 0, 255), 2);  
        }  
        return img;  
    } 
    
}