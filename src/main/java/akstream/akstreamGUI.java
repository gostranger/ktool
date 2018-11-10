package akstream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import config.conf;

public class akstreamGUI extends JPanel {
	public akstreamGUI(conf cn) {
		JFrame frame = new JFrame("Coming Soon");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);

	}
}
