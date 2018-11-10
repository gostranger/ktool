package main;

import datalake.adlsGUI;
import eventhub.ehGUI;
import jms.jmsGUI;

import java.awt.*;
import kafka.kafkaGUI;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import akfire.akfireGUI;
import akstream.akstreamGUI;
import config.conf;

public class App extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnadls;
	private JButton btnkafka;
	private JButton btnakfire;
	private JButton btnakstream;
	private JButton btneh;
	private JButton btnjms;
	private conf cn;

	public App() throws IOException {
		// construct components
		cn = new conf();
		btnadls = new JButton("Azure Data Lake");
		btnkafka = new JButton("Kafka");
		btnakfire = new JButton("Kinisis Fire");
		btnakstream = new JButton("Kinisis Stream");
		btneh = new JButton("Event Hub");
		btnjms = new JButton("JMS");

		// adjust size and set layout
		setPreferredSize(new Dimension(944, 553));
		setLayout(null);

		// add components
		add(btnadls);
		add(btnkafka);
		add(btnakfire);
		add(btnakstream);
		add(btneh);
		add(btnjms);

		// set component bounds (only needed by Absolute Positioning)
		btnadls.setBounds(0, 0, 155, 35);
		btnkafka.setBounds(155, 0, 155, 35);
		btnakfire.setBounds(0, 35, 155, 35);
		btnakstream.setBounds(155, 35, 155, 35);
		btneh.setBounds(0, 70, 155, 35);
		btnjms.setBounds(155, 70, 155, 35);

		btnadls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adlsGUI(cn);
			}
		});
		btnkafka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new kafkaGUI(cn);
			}
		});
		btnakfire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new akfireGUI(cn);
			}
		});
		btnakstream.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new akstreamGUI(cn);
			}
		});
		btneh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ehGUI(cn);
			}
		});
		btnjms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new jmsGUI(cn);
			}
		});

	}

	public static void main(String[] args) throws IOException {
		// Thread t = Thread.currentThread();
		JFrame frame = new JFrame("Tools");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new App());
		frame.pack();
		frame.setSize(316, 145);
		frame.setResizable(false);
		frame.setVisible(true);
	}

}
