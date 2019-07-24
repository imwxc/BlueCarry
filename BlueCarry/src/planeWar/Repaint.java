package planeWar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;

public class Repaint implements Runnable{

	public CopyOnWriteArrayList<plane> planeArray;
	public CopyOnWriteArrayList<bullet> bulletArray;
	JFrame f;
	public Repaint(JFrame f,CopyOnWriteArrayList<plane> planeArray,CopyOnWriteArrayList<bullet> bulletArray) {
		this.f=f;
		this.planeArray=planeArray;
		this.bulletArray=bulletArray;
	}
	@Override
	public void run() {
		bulletArray.add(new bullet());
		bulletArray.add(new bullet());
		bulletArray.add(new bullet());
		bulletArray.add(new bullet());
		bulletArray.add(new bullet());
		bulletArray.add(new bullet());
		// TODO Auto-generated method stub
		while(true) {
			if(plane_isEmpty()) {
				planeArray.add(new plane());
			}
			f.repaint();
//			try {
//				Thread.sleep(15);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	public boolean plane_isEmpty() {
		if(planeArray.size()==0) {
			return true;
		}
		return false;
		
	}
	
}
