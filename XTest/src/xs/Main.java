package xs;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("xs1122");
		frame.setBounds(new Rectangle(100, 100, 200, 200));
		frame.setUndecorated(true);
		//frame.setBackground(new Color(0, 1, 1, 1));
		frame.setVisible(true);
	}

}
