package tips;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class drawBall extends Thread{
    ArrayList <Ball> list=new ArrayList<Ball>();//ע������д����д��ᱨ��ָ���쳣
    Graphics g;
    Framball fb;

    public void setG(Graphics g,Framball fb,ArrayList <Ball> list) {
        this.g=g;
        this.fb=fb;
        this.list=list;
    }

    public void run() {
        while(true) {
            Ball b=new Ball();//ѭ���в����½�һ����
            b.setG(g, fb);//ÿ���������Լ���g��fb
            list.add(b);//����Ž�������
            try{
                sleep(200);
            }catch(Exception ef) {}
            System.out.println(list.size());
            if(list.size()==10)break;//����С��洢�ĸ���
        }
    }
}