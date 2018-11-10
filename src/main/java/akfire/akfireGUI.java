package akfire;

import javax.swing.JFrame;
import javax.swing.JPanel;

import config.conf;

public class akfireGUI extends JPanel {
	public akfireGUI(conf cn) {
		JFrame frame = new JFrame("Coming Soon");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);

	}
}
