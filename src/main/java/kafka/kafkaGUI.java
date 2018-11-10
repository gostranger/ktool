package kafka;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

import javax.swing.*;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;


import config.conf;
import datalake.adlsGUI;
import kafka.utils.ZkUtils;
import scala.collection.JavaConversions;

public class kafkaGUI extends JPanel {
	/**
	* 
	*/
	private static final long serialVersionUID = 2L;
	private JTextField txtServer;
	private JLabel lblServer;
	private JLabel lblPort;
	private JTextField txtPort;
	private JComboBox<String> comboTopic;
	private JLabel lblTopic;
	private JTextArea txtConsole;
	private JLabel lblKey;
	private JTextField txtKey;
	private JLabel lblType;
	private JComboBox<String> comboSelectType;
	private JButton btnrun;
	private JButton btnrefresh;
	private JLabel lblSchema;
	private JComboBox<String> comboSchema;
	private JLabel lblSchemaFile;
	private JTextField txtSchemaSelect;
	private JButton btnSchemaSelect;
	private JLabel lblDataFile;
	private JTextField txtDataSelect;
	private JButton btnDataSelect;
	private Properties p;
	private connection con;
	public kafkaGUI(conf cn) {
		// construct preComponents
		JFrame frame = new JFrame("Kafka");
		String[] comboSelectTypeItems = { "Producer", "Consumer" };
		String[] comboSchemaItems = { "Json", "Avro", "Xml" };

		// construct components
		txtServer = new JTextField(5);
		lblServer = new JLabel("Server :");
		lblPort = new JLabel("Port :");
		txtPort = new JTextField(5);
		comboTopic = new JComboBox<String>();
		lblTopic = new JLabel("Topic :");
		txtConsole = new JTextArea(5, 5);
		lblKey = new JLabel("key :");
		txtKey = new JTextField(5);
		lblType = new JLabel("Type :");
		comboSelectType = new JComboBox<String>(comboSelectTypeItems);
		btnrun = new JButton("Run");
		btnrefresh = new JButton("Refresh");
		lblSchema = new JLabel("Schema :");
		comboSchema = new JComboBox<String>(comboSchemaItems);
		lblSchemaFile = new JLabel("Schema File :");
		txtSchemaSelect = new JTextField(5);
		btnSchemaSelect = new JButton("Select");
		lblDataFile = new JLabel("Data File :");
		txtDataSelect = new JTextField(5);
		btnDataSelect = new JButton("Select");

		// adjust size and set layout
		setPreferredSize(new Dimension(944, 547));
		setLayout(null);
		// add components
		add(txtServer);
		add(lblServer);
		add(lblPort);
		add(txtPort);
		add(comboTopic);
		add(lblTopic);
		add(txtConsole);
		add(lblKey);
		add(txtKey);
		add(lblType);
		add(comboSelectType);
		add(btnrun);
		add(btnrefresh);
		add(lblSchema);
		add(comboSchema);
		add(lblSchemaFile);
		add(txtSchemaSelect);
		add(btnSchemaSelect);
		add(lblDataFile);
		add(txtDataSelect);
		add(btnDataSelect);

		// set component bounds (only needed by Absolute Positioning)
		txtServer.setBounds(110, 25, 170, 25);
		lblServer.setBounds(20, 25, 100, 25);
		lblPort.setBounds(20, 50, 100, 25);
		txtPort.setBounds(110, 50, 170, 25);
		comboTopic.setBounds(110, 75, 170, 25);
		lblTopic.setBounds(20, 75, 100, 25);
		txtConsole.setBounds(20, 165, 910, 370);
		lblKey.setBounds(20, 100, 100, 25);
		txtKey.setBounds(110, 100, 170, 25);
		lblType.setBounds(20, 125, 100, 25);
		comboSelectType.setBounds(110, 125, 170, 25);
		btnrun.setBounds(300, 125, 100, 25);
		btnrefresh.setBounds(300, 25, 100, 25);
		lblSchema.setBounds(430, 25, 100, 25);
		comboSchema.setBounds(515, 25, 135, 25);
		lblSchemaFile.setBounds(430, 60, 100, 25);
		txtSchemaSelect.setBounds(515, 60, 320, 25);
		btnSchemaSelect.setBounds(840, 60, 95, 25);
		lblDataFile.setBounds(430, 85, 100, 25);
		txtDataSelect.setBounds(515, 85, 320, 25);
		btnDataSelect.setBounds(840, 85, 95, 25);
		txtServer.setText(cn.getKafkaConfig().getProperty("Server"));
		txtPort.setText(cn.getKafkaConfig().getProperty("Port"));
		p = cn.getKafkaConfig();
		con = new connection(p);
		getTopicList(comboTopic);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);
		btnrefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				con = new connection(p);
				comboTopic.removeAllItems();
				getTopicList(comboTopic);
			}
		});
	}

	public void getTopicList(JComboBox<String> comboTopic) {
		java.util.List<String> topics = con.zookeeperConnect();
		for (String topic : topics) {
			System.out.println(topic);
			comboTopic.addItem(topic);
		}
	}

}
