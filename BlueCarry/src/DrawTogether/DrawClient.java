package DrawTogether;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;


public class DrawClient extends JFrame {

	/**
	 * Launch the application.
	 */
	public DrawMouseListener dmlistener;
	JButton btnNewButton,btnNewButton_1,
			btnNewButton_2,btnNewButton_3,
			btnNewButton_4,btnNewButton_5,
			btnNewButton_6,btnNewButton_7;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawClient frame = new DrawClient();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public DrawClient() {
		setBounds(100, 100, 900, 700);
		setVisible(true);
		this.setDefaultCloseOperation(3);
		getContentPane().setLayout(null);
		dmlistener=new DrawMouseListener(this);
		this.addMouseListener(dmlistener);
		
		btnNewButton = new JButton("直线");
		btnNewButton.setBounds(10, 10, 93, 40);
		btnNewButton.addActionListener(dmlistener);
		getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("长方形");
		btnNewButton_1.setBounds(10, 60, 93, 40);
		btnNewButton_1.addActionListener(dmlistener);
		getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("圆");
		btnNewButton_2.setBounds(10, 110, 93, 40);
		btnNewButton_2.addActionListener(dmlistener);
		getContentPane().add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("三角形");
		btnNewButton_3.setBounds(10, 160, 93, 40);
		btnNewButton_3.addActionListener(dmlistener);
		getContentPane().add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("");
		btnNewButton_4.setBounds(113, 10, 40, 40);
		btnNewButton_4.setBackground(Color.BLACK);
		btnNewButton_4.addActionListener(dmlistener);
		getContentPane().add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("");
		btnNewButton_5.setBounds(163, 10, 40, 40);
		btnNewButton_5.setBackground(Color.BLUE);
		btnNewButton_5.addActionListener(dmlistener);
		getContentPane().add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("");
		btnNewButton_6.setBounds(213, 10, 40, 40);
		btnNewButton_6.setBackground(Color.RED);
		btnNewButton_6.addActionListener(dmlistener);
		getContentPane().add(btnNewButton_6);
		
		btnNewButton_7 = new JButton("");
		btnNewButton_7.setBounds(263, 10, 40, 40);
		btnNewButton_7.setBackground(Color.GREEN);
		btnNewButton_7.addActionListener(dmlistener);
		getContentPane().add(btnNewButton_7);
		
	}
}
