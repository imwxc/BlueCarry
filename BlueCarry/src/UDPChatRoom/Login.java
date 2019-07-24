package UDPChatRoom;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import java.awt.Font;

public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			Login frame = new Login();
			frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	JLabel lblNewLabel;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	JButton btnNewButton;
	JButton btnNewButton_1;
//	JButton btnNewButton_2;
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("登陆界面");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 24));
		lblNewLabel.setBounds(98, 47, 108, 39);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("用户名");
		lblNewLabel_1.setBounds(46, 117, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("密码");
		lblNewLabel_2.setBounds(46, 160, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(113, 114, 93, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("登陆");
		btnNewButton.setBounds(98, 205, 93, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("注册");
		btnNewButton_1.setBounds(98, 251, 93, 23);
		contentPane.add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(113, 157, 93, 21);
		contentPane.add(passwordField);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button=(JButton)e.getSource();
		switch(button.getText()) {
		case "登陆":{
			
		}
		case "注册":{
			
		}
		default :{
			
		}
		}
		
	}
}
