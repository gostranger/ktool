package kafka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import config.constants;
import config.constants.Kafka;

public class connection {
	private ZooKeeper zk = null;
	public String SERVER;
	public String PORT;
	public String ZOOKEEPERSERVER;
	public String ZOOKEEPERPORT;
	public java.util.List<String> topics;
	public connection(Properties p) {
		this.SERVER = p.getProperty(Kafka.SERVER);
		this.PORT = p.getProperty(Kafka.PORT);
		this.ZOOKEEPERSERVER = p.getProperty(Kafka.ZOOKEEPERSERVER);
		this.ZOOKEEPERPORT = p.getProperty(Kafka.ZOOKEEPERPORT);
		
	}
	public List<String> zookeeperConnect() {
		topics = new ArrayList<String>();
		try {
			zk = new ZooKeeper(this.ZOOKEEPERSERVER+":"+this.ZOOKEEPERPORT,10000,null);
			topics = zk.getChildren("/brokers/topics", false);
		} catch (IOException | KeeperException | InterruptedException e) {
			topics.add("unable to connect");
		}
		return topics;
	}
}
