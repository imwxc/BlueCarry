package planeWar;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class moveThread implements Runnable{

	CopyOnWriteArrayList<plane> planeArray;//=new CopyOnWriteArrayList<plane>();
	CopyOnWriteArrayList<bullet> bulletArray;//=new CopyOnWriteArrayList<bullet>();
	Graphics g;
	public moveThread(CopyOnWriteArrayList<plane> planeArray,CopyOnWriteArrayList<bullet> bulletArray,Graphics g) {
		this.planeArray=planeArray;
		this.bulletArray=bulletArray;
		this.g=g;
		System.out.println("线程启动");
	}
	@Override
	public void run() {
		System.out.println("线程运行");
		while(true) {
			try {
				Thread.sleep(32);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i=0;i<planeArray.size();i++) {
				planeArray.get(i).movePlane();
				if(planeArray.get(i).destory()) {
					planeArray.remove(i);
				}		
			}
			for(int i=0;i<bulletArray.size();i++) {
				bulletArray.get(i).moveBullet();
				if(bulletArray.get(i).destory()) {
					bulletArray.remove(i);
				}

			}
		}
	}
}
