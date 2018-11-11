package config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import config.constants.*;

public class conf {
	// kafka
	private String kafkaserver;
	private String kafkaport;
	private String zkserver;
	private String zkport;
	private String replication_factor;
	private String partitions;

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
			p.setProperty(Kafka.SERVER, "");
			p.setProperty(Kafka.PORT, "");
			p.setProperty(Kafka.ZOOKEEPERSERVER, "");
			p.setProperty(Kafka.ZOOKEEPERPORT, "");
			p.setProperty(Kafka.REPLICATION_FACTOR, "1");
			p.setProperty(Kafka.PARTITIONS, "1");
			p.store(new FileWriter(KafkaFile), "Kafka Properties");
		}
		f = new File(this.AdlsFile);
		if (!f.exists()) {
			p = new Properties();
			p.setProperty(Adls.ACCOUNT_NAME, "");
			p.setProperty(Adls.CLIENT_ID, "");
			p.setProperty(Adls.KEY, "");
			p.setProperty(Adls.DIRECTORY, "");
			p.setProperty(Adls.AUTH_END_POINT, "");
			p.store(new FileWriter(AdlsFile), "Adls Properties");
		}
		f = new File(KinesisFile);
		if (!f.exists()) {
			p = new Properties();
			p.setProperty(Kinesis.ACCESS_ID, "");
			p.setProperty(Kinesis.ACCESS_KEY, "");
			p.store(new FileWriter(KinesisFile), "kinesis Properties");

		}
		f = new File(EventHubFile);
		if (!f.exists()) {
			p = new Properties();
			p.setProperty(EventHub.TENENT_ID, "");
			p.setProperty(EventHub.SUBSCRIPTION, "");
			p.setProperty(EventHub.CLIENT_APP_ID, "");
			p.setProperty(EventHub.CLIENTKEY, "");
			p.setProperty(EventHub.NAME_SPACE, "");
			p.setProperty(EventHub.RESOURCE_GROUP, "");
			p.setProperty(EventHub.SHARED_ACCESS_POLICY_NAME, "");
			p.setProperty(EventHub.SHARED_ACCESS_POLICY_KEY, "");
			p.store(new FileWriter(EventHubFile), "EventHub Properties");

		}
		f = new File(JmsFile);
		if (!f.exists()) {
			p = new Properties();
			p.setProperty(Jms.URI, "");
			p.setProperty(Jms.USERNAME, "");
			p.setProperty(Jms.PASSWORD, "");
			p.setProperty(Jms.JNDI_CONTEXT_FACTORY, "");
			p.setProperty(Jms.CONNECTION_FACTORY, "");
			p.store(new FileWriter(JmsFile), "Jms Properties");
		}
		loadall();
	}

	public Properties getKafkaConfig() {
		p = new Properties();
		p.setProperty(Kafka.SERVER, this.kafkaserver);
		p.setProperty(Kafka.PORT, this.kafkaport);
		p.setProperty(Kafka.ZOOKEEPERSERVER, this.zkserver);
		p.setProperty(Kafka.ZOOKEEPERPORT, this.zkport);
		p.setProperty(Kafka.REPLICATION_FACTOR, this.replication_factor);
		p.setProperty(Kafka.PARTITIONS, this.partitions);
		return p;
	}

	public Properties getAdlsConfig() {
		p = new Properties();
		p.setProperty(Adls.ACCOUNT_NAME, this.adls_accountName);
		p.setProperty(Adls.CLIENT_ID, this.adls_clientID);
		p.setProperty(Adls.KEY, this.adls_key);
		p.setProperty(Adls.DIRECTORY, this.adls_Directory);
		p.setProperty(Adls.AUTH_END_POINT, this.adls_authEndpoint);
		return p;
	}

	public Properties getKinisisConfig() {
		p = new Properties();
		p.setProperty(Kinesis.ACCESS_ID, this.ak_accessId);
		p.setProperty(Kinesis.ACCESS_KEY, this.ak_key);
		return p;
	}

	public Properties getEventHubConfig() {
		p = new Properties();
		p.setProperty(EventHub.TENENT_ID, this.eh_tenentId);
		p.setProperty(EventHub.SUBSCRIPTION, this.eh_subscriptionId);
		p.setProperty(EventHub.CLIENT_APP_ID, this.eh_ClientAppId);
		p.setProperty(EventHub.CLIENTKEY, this.eh_Clientkey);
		p.setProperty(EventHub.NAME_SPACE, this.eh_eventhubNameSpace);
		p.setProperty(EventHub.RESOURCE_GROUP, this.eh_resourceGroup);
		p.setProperty(EventHub.SHARED_ACCESS_POLICY_NAME, this.eh_sharedAccessPolicyName);
		p.setProperty(EventHub.SHARED_ACCESS_POLICY_KEY, this.eh_sharedAccessPolicykey);
		return p;
	}

	public Properties getJmsConfig() {
		p = new Properties();
		p.setProperty(Jms.URI, this.jms_connectionUri);
		p.setProperty(Jms.USERNAME, this.jms_username);
		p.setProperty(Jms.PASSWORD, this.jms_password);
		p.setProperty(Jms.JNDI_CONTEXT_FACTORY, this.jms_jndiContextFactory);
		p.setProperty(Jms.CONNECTION_FACTORY, this.jms_connectionFactory);
		return p;
	}

	private void loadall() throws IOException {
		// read kafka
		reader = new FileReader(this.KafkaFile);
		p = new Properties();
		p.load(reader);
		this.kafkaserver = p.getProperty(Kafka.SERVER);
		this.kafkaport = p.getProperty(Kafka.PORT);
		this.zkserver = p.getProperty(Kafka.ZOOKEEPERSERVER);
		this.zkport = p.getProperty(Kafka.ZOOKEEPERPORT);
		this.replication_factor = p.getProperty(Kafka.REPLICATION_FACTOR);
		this.partitions = p.getProperty(Kafka.PARTITIONS);

		// read adls
		reader = new FileReader(this.AdlsFile);
		p = new Properties();
		p.load(reader);
		this.adls_accountName = p.getProperty(Adls.ACCOUNT_NAME);
		this.adls_clientID = p.getProperty(Adls.CLIENT_ID);
		this.adls_key = p.getProperty(Adls.KEY);
		this.adls_Directory = p.getProperty(Adls.DIRECTORY);
		this.adls_authEndpoint = p.getProperty(Adls.AUTH_END_POINT);

		// read Kinesis
		reader = new FileReader(this.KinesisFile);
		p = new Properties();
		p.load(reader);
		this.ak_accessId = p.getProperty(Kinesis.ACCESS_ID);
		this.ak_key = p.getProperty(Kinesis.ACCESS_KEY);

		// read EventHub
		reader = new FileReader(this.EventHubFile);
		p = new Properties();
		p.load(reader);
		this.eh_tenentId = p.getProperty(EventHub.TENENT_ID);
		this.eh_subscriptionId = p.getProperty(EventHub.SUBSCRIPTION);
		this.eh_ClientAppId = p.getProperty(EventHub.CLIENT_APP_ID);
		this.eh_Clientkey = p.getProperty(EventHub.CLIENTKEY);
		this.eh_eventhubNameSpace = p.getProperty(EventHub.NAME_SPACE);
		this.eh_resourceGroup = p.getProperty(EventHub.RESOURCE_GROUP);
		this.eh_sharedAccessPolicyName = p.getProperty(EventHub.SHARED_ACCESS_POLICY_NAME);
		this.eh_sharedAccessPolicykey = p.getProperty(EventHub.SHARED_ACCESS_POLICY_KEY);

		// read Jms
		reader = new FileReader(this.JmsFile);
		p = new Properties();
		p.load(reader);
		this.jms_connectionUri = p.getProperty(Jms.URI);
		this.jms_username = p.getProperty(Jms.USERNAME);
		this.jms_password = p.getProperty(Jms.PASSWORD);
		this.jms_jndiContextFactory = p.getProperty(Jms.JNDI_CONTEXT_FACTORY);
		this.jms_connectionFactory = p.getProperty(Jms.CONNECTION_FACTORY);

	}
}
