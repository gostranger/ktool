package kafka;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.*;

import config.conf;
import config.constants;

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
	private JLabel lblTopicCreate;
	private JTextField txtTopicCreate;
	private JButton btnCreateTopic;
	private JScrollPane txtAreaScroll;
	private JLabel lblSuccessMessage;
	private Properties p;
	private connection con;
	private ProducerClass pc;
	private ConsumerClass cc;
	
	public kafkaGUI(final conf cn) {
		// construct preComponents
		JFrame frame = new JFrame("Kafka - Producer");
		String[] comboSelectTypeItems = { "Producer", "Consumer" };
		String[] comboSchemaItems = { "Json", "Avro", "Xml" };
		//frame.setBackground(new Color(100,100,100));
		this.setBackground(new Color(200,200,200));
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
		btnrun = new JButton("Send");
		btnrefresh = new JButton("Refresh");
		lblSchema = new JLabel("Schema :");
		comboSchema = new JComboBox<String>(comboSchemaItems);
		lblSchemaFile = new JLabel("Schema File :");
		txtSchemaSelect = new JTextField(5);
		btnSchemaSelect = new JButton("Select");
		lblDataFile = new JLabel("Data File :");
		txtDataSelect = new JTextField(5);
		btnDataSelect = new JButton("Select");
		lblTopicCreate = new JLabel("Enter New Topic :");
		txtTopicCreate = new JTextField(5);
		btnCreateTopic = new JButton("Create Topic");
		txtAreaScroll = new JScrollPane(txtConsole);
		txtAreaScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		txtAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		lblSuccessMessage = new JLabel("Message Sent: ");

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
		//add(txtConsole);
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
		add(lblTopicCreate);
		add(txtTopicCreate);
		add(btnCreateTopic);
		add(txtAreaScroll);
		add(lblSuccessMessage);
		
		lblSchemaFile.setVisible(false);
		txtSchemaSelect.setVisible(false);
		btnSchemaSelect.setVisible(false);
		lblDataFile.setVisible(false);
		txtDataSelect.setVisible(false);
		btnDataSelect.setVisible(false);
		
		// set component bounds (only needed by Absolute Positioning)
		txtServer.setBounds(110, 25, 170, 25);
		lblServer.setBounds(20, 25, 100, 25);
		lblPort.setBounds(20, 50, 100, 25);
		txtPort.setBounds(110, 50, 170, 25);
		comboTopic.setBounds(110, 75, 170, 25);
		lblTopic.setBounds(20, 75, 100, 25);
		txtConsole.setBounds(20, 165, 910, 370);
		txtAreaScroll.setBounds(20,165,910,350);
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
		lblTopicCreate.setBounds(430,125,320,25);
		txtTopicCreate.setBounds(550,125,250,25);
		btnCreateTopic.setBounds(800,125,120,25);
		lblSuccessMessage.setBounds(20,520,200,25);
		txtConsole.setBackground(new Color(255, 255, 255));
		txtServer.setText(cn.getKafkaConfig().getProperty("Server"));
		txtPort.setText(cn.getKafkaConfig().getProperty("Port"));
		
		p = cn.getKafkaConfig();
		con = new connection(p);
		pc = new ProducerClass(comboSchema.getSelectedItem().toString());
		getTopicList(comboTopic);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);
		if(con.connectionTest() == false) {
			JOptionPane.showMessageDialog(kafkaGUI.this, "Either Zookeeper/Kafka is Offline");
		}
		btnrefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				con = new connection(p);
				comboTopic.removeAllItems();
				getTopicList(comboTopic);
			}
		});
		comboSelectType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(comboSelectType.getSelectedItem().toString() == constants.Type.Producer.toString()){
					lblSchema.setVisible(true);
					comboSchema.setVisible(true);
					btnrun.setText("Send");
				}else {
					lblSchema.setVisible(false);
					comboSchema.setVisible(false);
					lblSchemaFile.setVisible(false);
					txtSchemaSelect.setVisible(false);
					btnSchemaSelect.setVisible(false);
					lblDataFile.setVisible(false);
					txtDataSelect.setVisible(false);
					btnDataSelect.setVisible(false);
					btnrun.setText("Start");
				}
				
			}
		});
		comboSchema.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(comboSchema.getSelectedItem().toString() == constants.schema.Avro.toString()){
					lblSchemaFile.setVisible(true);
					txtSchemaSelect.setVisible(true);
					btnSchemaSelect.setVisible(true);
					lblDataFile.setVisible(true);
					txtDataSelect.setVisible(true);
					btnDataSelect.setVisible(true);
					txtConsole.setVisible(false);
				}else {
					lblSchemaFile.setVisible(false);
					txtSchemaSelect.setVisible(false);
					btnSchemaSelect.setVisible(false);
					lblDataFile.setVisible(false);
					txtDataSelect.setVisible(false);
					btnDataSelect.setVisible(false);
					txtConsole.setVisible(true);
				}
				
			}
		});
		btnCreateTopic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(kafkaGUI.this, con.createTopic(txtTopicCreate.getText()));
				con = new connection(p);
				comboTopic.removeAllItems();
				getTopicList(comboTopic);
			}
		});
		btnrun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(btnrun.getText() == "Start") {
					btnrun.setText("Stop");
					cc = new ConsumerClass(comboTopic.getSelectedItem().toString(), con,txtConsole);
					cc.run();
				}else if(btnrun.getText() == "Stop") {
					btnrun.setText("Start");
					cc.stopper();
				}else {
					SendData();
				}
			}
		});
	}
	public void SendData() {
		try {
			Integer.parseInt(txtKey.getText());
		}catch(Exception e){
			txtKey.setText("0");
		}
		if(pc.send(comboTopic.getSelectedItem().toString(),comboSchema.getSelectedItem().toString(),Integer.parseInt(txtKey.getText()),txtConsole,con)) {
			lblSuccessMessage.setText("Message Sent: Success");
		}else {
			lblSuccessMessage.setText("Message Sent: Failed");
		}
	}
	public void getTopicList(JComboBox<String> comboTopic) {
		java.util.List<String> topics = con.fetchTopics();
		if(topics.size()<=0) {
			topics.add("No Topics Found.");
		}
		for (String topic : topics) {
			comboTopic.addItem(topic);
		}
	}
}
