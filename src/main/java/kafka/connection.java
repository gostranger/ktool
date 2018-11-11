package kafka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kafka.admin.AdminUtils;
import kafka.utils.ZKStringSerializer$;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import config.constants;
import config.constants.Kafka;
import kafka.utils.ZkUtils;

public class connection {
	private ZooKeeper zk = null;
	ZkClient zkClient = null;
    ZkUtils zkUtils = null;
	public String SERVER;
	public String PORT;
	public String ZOOKEEPERSERVER;
	public String ZOOKEEPERPORT;
	public String REPLICATIONFACTOR;
	public java.util.List<String> topics;
	public connection(Properties p) {
		this.SERVER = p.getProperty(Kafka.SERVER);
		this.PORT = p.getProperty(Kafka.PORT);
		this.ZOOKEEPERSERVER = p.getProperty(Kafka.ZOOKEEPERSERVER);
		this.ZOOKEEPERPORT = p.getProperty(Kafka.ZOOKEEPERPORT);
		this.REPLICATIONFACTOR = p.getProperty(Kafka.REPLICATION_FACTOR);
		connectionTest();
	}
	
	public boolean connectionTest() {
		// TODO Auto-generated method stub
		try {
			zk = new ZooKeeper(this.ZOOKEEPERSERVER+":"+this.ZOOKEEPERPORT,10000,null);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	public List<String> fetchTopics() {
		connectionTest();
		topics = new ArrayList<String>();
		try {
			topics = zk.getChildren("/brokers/topics", false);
		} catch ( KeeperException | InterruptedException e) {
			topics.add("unable to connect (Reason) :"+e.toString());
		}
		return topics;
	}
	public KafkaProducer getKafkaProducer(String Schema) {
		
		if(Schema == constants.schema.Json.toString()) {
			Properties prop = new Properties();
	        prop.put("bootstrap.servers", this.SERVER + ":" + this.PORT);
	        prop.put("client.id","Producer");
	        prop.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
	        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        prop.put("max.block.ms",10000);
	        return new KafkaProducer<Integer,String>(prop);
			
		}else if(Schema == constants.schema.Avro.toString()) {
			Properties prop = new Properties();
	        prop.put("bootstrap.servers", this.SERVER + ":" + this.PORT);
	        prop.put("client.id","Producer");
	        prop.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
	        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        prop.put("max.block.ms",10000);
			return new KafkaProducer<String,byte[]>(prop);
			
		}else if(Schema == constants.schema.Xml.toString()) {
			Properties prop = new Properties();
	        prop.put("bootstrap.servers", this.SERVER + ":" + this.PORT);
	        prop.put("client.id","Producer");
	        prop.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
	        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        prop.put("max.block.ms",10000);
	        return new KafkaProducer<Integer,String>(prop);
		}
		return null;
		
	}
	
	public String createTopic(String topicName) {
		
		ZkClient zkClient = new ZkClient(this.ZOOKEEPERSERVER+":"+this.ZOOKEEPERPORT, 10000, 10000, ZKStringSerializer$.MODULE$);
		ZkUtils zkUtils = new ZkUtils(zkClient,new ZkConnection(this.ZOOKEEPERSERVER+":"+this.ZOOKEEPERPORT),false);
		if (!AdminUtils.topicExists(zkUtils, topicName)) {
            int replicationFactor = Integer.parseInt(this.REPLICATIONFACTOR);
            AdminUtils.createTopic(zkUtils, topicName, 1, replicationFactor, new Properties(), null);
            return "Topic \""+topicName+"\" Created Successfully";
        } else {
            return "Topic \""+topicName+"\" Exists";
        }
	}

	public KafkaConsumer<Integer, String> getKafkaConsumer() {
		Properties prop = new Properties();
		 prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,this.SERVER + ":" + this.PORT);
	        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "producer");
	        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
	        prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
	        prop.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
	        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
	        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		return new KafkaConsumer<Integer, String>(prop);
		
	}
	
}
