package tips;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {
    Random rand = new Random();
    int x = rand.nextInt(1000);
    int y = rand.nextInt(600);
    int R = rand.nextInt(100);// �뾶
    private int speedX = rand.nextInt(60), speedY = rand.nextInt(60);// С���˶��ٶ�
    private int r = 1000, d = 600;// ���ޣ�����
    private Graphics g;
    private Framball fb;

    public void setG(Graphics g, Framball fb) {
        this.g = g;
        this.fb = fb;
    }

    public void run() {// ��ʾС����˶����ɷ������������whileѭ���в���������
        g.setColor(fb.getContentPane().getBackground());//�л�Ϊ����ɫ
        g.fillOval(x - R - speedX, y - R - speedY, R, R);//����R��ʾ�������滻ΪԲ�ģ������ٶȱ�ʾ����һ��С���ڸǵ�
        g.setColor(Color.black);
        g.fillOval(x - R, y - R, R, R);
        if (y >= d)//��y�Ӵ�������ʱ
            speedY *= -1;//�ٶȷ���
        else if (y <= 0)
            speedY *= -1;
        if (x >= r)
            speedX *= -1;
        else if (x <= 0)
            speedX *= -1;
        x += speedX;//ÿһ��run�����ƶ�һ���ٶ�ֵ
        y += speedY;
        //ִֻ��һ��run����,С���ǲ��ᶯ��
        
        
    }
    
   }
