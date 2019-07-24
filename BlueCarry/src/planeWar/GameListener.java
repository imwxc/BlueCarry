package planeWar;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;

public class GameListener implements ActionListener,KeyListener,MouseListener{
	CopyOnWriteArrayList<plane> planeArray;
	CopyOnWriteArrayList<bullet> bulletArray;
	Graphics g;
	plane myplane;
	public GameListener(plane myplane,CopyOnWriteArrayList<plane> planeArray,CopyOnWriteArrayList<bullet> bulletArray,Graphics g) {
		this.planeArray=planeArray;
		this.bulletArray=bulletArray;
		this.g=g;
		this.myplane=myplane;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		JButton button=(JButton)e.getSource();
//		if(button.getText().equals("plane")) {
//			planeArray.add(new plane());
////			System.out.println(planeArray.size());
//		}else if(button.getText().equals("bullet")) {
//			bulletArray.add(new bullet());
////			System.out.println(bulletArray.size());
//		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		bulletArray.add(new bullet());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		myplane.moveMyplane(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		myplane.moveMyplane(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	

}
