package tips;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Framball extends JFrame{
    private static ArrayList <Ball> list = new ArrayList<Ball>();//�½���listһ��Ҫ��ʼ��
    private Graphics g;
    public void showUI() {

        this.setTitle("С��");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        // ���ò���,��ʽ����
        this.setLayout(new FlowLayout());
        this.setVisible(true);
         g=this.getGraphics();
    }

    public static void main(String[] args) {
        Framball fb=new Framball();
        fb.showUI();//�ɼ����ܵõ�����

        drawBall db=new drawBall();
        db.setG(fb.g,fb,list);//����������
        db.start();//�����̣߳��߳������������Զ����У�����ֻ����run��������Ҫ�����������������������

        //moveBall�е�list������drawBall�еģ����Ա����dbִ�����������moveBall
        moveBall mb=new moveBall();
        mb.setL(list);
        mb.start();//�����߳�

    }

}