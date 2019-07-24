package UDPChatRoom;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

public class Video extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		Video frame = new Video();
//		
//	}
	/**
	 * Create the frame.
	 */
	Video() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1310, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(2);
		setVisible(true);
		
		DisplayVideo dis=new DisplayVideo(this);
		
		
		
		JButton btnNewButton = new JButton("结束");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(10, 528, 69, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("镜像");
		btnNewButton_1.setBounds(10, 495, 93, 23);
		btnNewButton_1.addActionListener(dis);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("马赛克");
		btnNewButton_2.setBounds(10, 462, 93, 23);
		btnNewButton_2.addActionListener(dis);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("黑白");
		btnNewButton_3.setBounds(113, 462, 93, 23);
		btnNewButton_3.addActionListener(dis);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("颠倒");
		btnNewButton_4.setBounds(113, 495, 93, 23);
		btnNewButton_4.addActionListener(dis);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setBounds(642, 10, 11, 445);
		contentPane.add(lblNewLabel);
		
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button=(JButton)e.getSource();
				if(button!=null) {
					dis.getCamera().close();
					dis.stop();
					dispose();
				}
			}
		});
		dis.start();
	}
}
