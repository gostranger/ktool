package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class conf {
	// kafka
	private String kafkaserver;
	private String kafkaport;
	private String zkserver;
	private String zkport;

	// ADLS
	private String adls_accountName;
	private String adls_clientID;
	private String adls_key;
	private String adls_Directory;
	private String adls_authEndpoint;

	// Amazon kinisis
	private String ak_accessId;
	private String ak_key;

	// eventhub
	private String eh_tenentId;
	private String eh_subscriptionId;
	private String eh_resourceGroup;
	private String eh_ClientAppId;
	private String eh_Clientkey;
	private String eh_eventhubNameSpace;
	private String eh_sharedAccessPolicyName;
	private String eh_sharedAccessPolicykey;

	// JMS
	private String jms_connectionUri;
	private String jms_username;
	private String jms_password;
	private String jms_jndiContextFactory;
	private String jms_connectionFactory;

	private File f;
	private Properties p;
	private FileReader reader;

	// files fixed
	private String KafkaFile = "kafka.properties";
	private String AdlsFile = "Adls.properties";
	private String KinesisFile = "Kinesis.properties";
	private String EventHubFile = "EventHub.properties";
	private String JmsFile = "Jms.properties";

	public conf() throws IOException {
		f = new File(this.KafkaFile);
		if (!f.exists()) {
			p = new Properties();
			p.setProperty("Server", "");
			p.setProperty("Port", "");
			p.setProperty("ZookeeperServer", "");
			p.setProperty("ZookeeperPort", "");
			p.store(new FileWriter(KafkaFile), "Kafka Properties");
		}
		f = new File(this.AdlsFile);
		if (!f.exists()) {
			p = new Properties();
			p.setProperty("AccountName", "");
			p.setProperty("ClientID", "");
			p.setProperty("Key", "");
			p.setProperty("Directory", "");
			p.setProperty("AuthEndPoint", "");
			p.store(new FileWriter(AdlsFile), "Adls Properties");
		}
		f = new File(KinesisFile);
		if (!f.exists()) {
			p = new Properties();
			p.setProperty("AccessID", "");
			p.setProperty("AccessKey", "");
			p.store(new FileWriter(KinesisFile), "kinesis Properties");

		}
		f = new File(EventHubFile);
		if (!f.exists()) {
			p = new Properties();
			p.setProperty("TenentID", "");
			p.setProperty("Subscription", "");
			p.setProperty("ClientAppID", "");
			p.setProperty("ClientKey", "");
			p.setProperty("NameSpace", "");
			p.setProperty("ResourceGroup", "");
			p.setProperty("SharedAPName", "");
			p.setProperty("SharedAPkey", "");
			p.store(new FileWriter(EventHubFile), "EventHub Properties");

		}
		f = new File(JmsFile);
		if (!f.exists()) {
			p = new Properties();
			p.setProperty("URI", "");
			p.setProperty("UserName", "");
			p.setProperty("Password", "");
			p.setProperty("JndiContextFactory", "");
			p.setProperty("ConnectionFactory", "");
			p.store(new FileWriter(JmsFile), "Jms Properties");
		}
		loadall();
	}

	public Properties getKafkaConfig() {
		p = new Properties();
		p.setProperty("Server", this.kafkaserver);
		p.setProperty("Port", this.kafkaport);
		p.setProperty("ZookeeperServer", this.zkserver);
		p.setProperty("ZookeeperPort", this.zkport);
		return p;
	}

	public Properties getAdlsConfig() {
		p = new Properties();
		p.setProperty("AccountName", this.adls_accountName);
		p.setProperty("ClientID", this.adls_clientID);
		p.setProperty("Key", this.adls_key);
		p.setProperty("Directory", this.adls_Directory);
		p.setProperty("AuthEndPoint", this.adls_authEndpoint);
		return p;
	}

	public Properties getKinisisConfig() {
		p = new Properties();
		p.setProperty("AccessID", this.ak_accessId);
		p.setProperty("AccessKey", this.ak_key);
		return p;
	}

	public Properties getEventHubConfig() {
		p = new Properties();
		p.setProperty("TenentID", this.eh_tenentId);
		p.setProperty("Subscription", this.eh_subscriptionId);
		p.setProperty("ClientAppID", this.eh_ClientAppId);
		p.setProperty("ClientKey", this.eh_Clientkey);
		p.setProperty("NameSpace", this.eh_eventhubNameSpace);
		p.setProperty("ResourceGroup", this.eh_resourceGroup);
		p.setProperty("SharedAPName", this.eh_sharedAccessPolicyName);
		p.setProperty("SharedAPkey", this.eh_sharedAccessPolicykey);
		return p;
	}

	public Properties getJmsConfig() {
		p = new Properties();
		p.setProperty("URI", this.jms_connectionUri);
		p.setProperty("UserName", this.jms_username);
		p.setProperty("Password", this.jms_password);
		p.setProperty("JndiContextFactory", this.jms_jndiContextFactory);
		p.setProperty("ConnectionFactory", this.jms_connectionFactory);
		return p;
	}

	private void loadall() throws IOException {
		// read kafka
		reader = new FileReader(this.KafkaFile);
		p = new Properties();
		p.load(reader);
		this.kafkaserver = p.getProperty("Server");
		this.kafkaport = p.getProperty("Port");
		this.zkserver = p.getProperty("ZookeeperServer");
		this.zkport = p.getProperty("ZookeeperPort");

		// read adls
		reader = new FileReader(this.AdlsFile);
		p = new Properties();
		p.load(reader);
		this.adls_accountName = p.getProperty("AccountName");
		this.adls_clientID = p.getProperty("ClientID");
		this.adls_key = p.getProperty("Key");
		this.adls_Directory = p.getProperty("Directory");
		this.adls_authEndpoint = p.getProperty("AuthEndPoint");

		// read Kinesis
		reader = new FileReader(this.KinesisFile);
		p = new Properties();
		p.load(reader);
		this.ak_accessId = p.getProperty("AccessID");
		this.ak_key = p.getProperty("AccessKey");

		// read EventHub
		reader = new FileReader(this.EventHubFile);
		p = new Properties();
		p.load(reader);
		this.eh_tenentId = p.getProperty("TenentID");
		this.eh_subscriptionId = p.getProperty("Subscription");
		this.eh_ClientAppId = p.getProperty("ClientAppID");
		this.eh_Clientkey = p.getProperty("ClientKey");
		this.eh_eventhubNameSpace = p.getProperty("NameSpace");
		this.eh_resourceGroup = p.getProperty("ResourceGroup");
		this.eh_sharedAccessPolicyName = p.getProperty("SharedAPName");
		this.eh_sharedAccessPolicykey = p.getProperty("SharedAPkey");

		// read Jms
		reader = new FileReader(this.JmsFile);
		p = new Properties();
		p.load(reader);
		this.jms_connectionUri = p.getProperty("URI");
		this.jms_username = p.getProperty("UserName");
		this.jms_password = p.getProperty("Password");
		this.jms_jndiContextFactory = p.getProperty("JndiContextFactory");
		this.jms_connectionFactory = p.getProperty("ConnectionFactory");

	}
}
