package UDPChatRoom;



import java.awt.Dimension;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JFileChooser;

public class ChatrRoom extends JFrame implements ActionListener{

	private JPanel contentPane;
	public JTextPane textPane1;
	public JTextPane textPane2;
	public JTextField textField_1;
	private JButton send_button;
	private Document doc;
	JPanel panel;
	JButton btnNewButton,btnNewButton_1,btnNewButton_2;
	MessageReceiveServer message_server;
	FileReceiveServer file_server;
	SendServer send;
	private static int send_message_document_location=0;
	private JToolBar toolBar_1;
	private JButton btnNewButton_3;
	private JBubble b=new JBubble();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ChatrRoom frame = new ChatrRoom();
	}
	/**
	 * Create the frame.
	 */
	public ChatrRoom() {
		setTitle("ChatRoom");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 729);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(3);
		setResizable(false);
		
		textPane1 = new JTextPane();
		textPane1.setBounds(10, 10, 354, 600);
		contentPane.add(textPane1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(12, 615, 600, 70);
		contentPane.add(textField_1);
		textField_1.setPreferredSize(new Dimension(600,70));
		
		send_button=new JButton();
		send_button.setBounds(617, 615, 100, 70);
		contentPane.add(send_button);
		send_button.setPreferredSize(new Dimension(100,70));
		send_button.setText("发送");
		send_button.addActionListener(this);
		
		toolBar_1 = new JToolBar();
		toolBar_1.setBounds(728, 10, 101, 600);
		contentPane.add(toolBar_1);
		
		panel = new JPanel();
		toolBar_1.add(panel);
		panel.setLayout(null);
		
		btnNewButton = new JButton("设置");
		btnNewButton.setBounds(5, 5, 73, 23);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnNewButton_1 = new JButton("退出");
		btnNewButton_1.setBounds(5, 38, 73, 23);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		btnNewButton_2 = new JButton("文件");
		btnNewButton_2.setBounds(5, 71, 73, 23);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
		
		btnNewButton_3 = new JButton("视频");
		btnNewButton_3.setBounds(5, 104, 73, 23);
		panel.add(btnNewButton_3);
		
		textPane2 = new JTextPane();
		textPane2.setBounds(374, 10, 343, 600);
		contentPane.add(textPane2);
		btnNewButton_3.addActionListener(this);
		
		doc=textPane2.getDocument();
		
		receiveStart();	
		
		this.setVisible(true);
		
	}
	public void receiveStart() {
		message_server=new MessageReceiveServer(this);
		message_server.creat_client();
		message_server.start();
		file_server=new FileReceiveServer(this);
		file_server.start();
	}
	public void sendStart() {
		send=new SendServer();
	}
	public void send_message() {
		String message=textField_1.getText()+"\n";//获取消息
		SimpleAttributeSet att=new SimpleAttributeSet();
		try {
			send.send_str_data(message);
			System.out.println("发送消息"+message);
			doc.insertString(send_message_document_location, message,att);
			textPane2.setDocument(doc);
			send_message_document_location+=message.length();
		} catch (IOException e) {
			System.out.println("发送消息错误"+e.getMessage());
		} catch (BadLocationException e) {
			System.out.println("发送消息文档添加错误"+e.getMessage());
			e.printStackTrace();
		}
	}
	public void send_file(String add) {
		//dosth
		sendStart();
		send.send_file_data(add);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(send_button)) {
			System.out.println("发送点击1");
			sendStart();
			send_message();
//			System.out.println("添加气泡");
//			textField.add(new JLabel("ceshi"));
//			textField.add(new JBubble("测试"));
//			textField.setCaretPosition(textField.getCaretPosition()+1);
			//发送之后将内容清空
		}else if(e.getSource().equals(btnNewButton_2)){//文件发送按钮
			JFileChooser f_choose=new JFileChooser();
			f_choose.showOpenDialog(new JFrame());
			String add=null;//这里取消发送会报错
			add=f_choose.getSelectedFile().getAbsolutePath();
			if(add!=null) {
				System.out.println("文件路径："+add);
				send_file(add);
			}
		}else if(e.getSource().equals(btnNewButton_3)) {
//			System.out.println("开始视频聊天");
			Video video=new Video();
		}
	}
}