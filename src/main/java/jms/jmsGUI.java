package jms;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import config.conf;

public class jmsGUI extends JPanel {
	public jmsGUI(conf cn) {
		JFrame frame = new JFrame("Coming Soon");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);

	}
}
