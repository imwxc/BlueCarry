package DrawTogether;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class DrawActionListener implements ActionListener{

	public String shape;
	public Color c;
	@Override
	public void actionPerformed(ActionEvent e) {
		shape=e.getActionCommand();
		System.out.println(shape);
		JButton color=(JButton) e.getSource();
		Color bg=new Color(240,240,240);
		if(color.getBackground()!=bg) {
			c=color.getBackground();
		}
	}

}
