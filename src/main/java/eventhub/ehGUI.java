package eventhub;

import javax.swing.JFrame;
import javax.swing.JPanel;

import config.conf;

public class ehGUI extends JPanel {
	public ehGUI(conf cn) {
		JFrame frame = new JFrame("Coming Soon");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);

	}
}
