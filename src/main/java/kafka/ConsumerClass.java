package kafka;

import java.util.Collections;

import javax.swing.JTextArea;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerClass implements Runnable {
	private KafkaConsumer<Integer, String> consumer=null;
	private boolean stop = false;
	private String topic;
	private connection con;
	private JTextArea txt;
	public ConsumerClass(String topic,connection con,JTextArea txt) {
		this.topic = topic;
		this.con = con;
		this.txt = txt;
	}
	public void stopper() {
		// TODO Auto-generated method stub
		stop = false;
	}
	@Override
	public void run() {
		consumer = con.getKafkaConsumer();
		consumer.subscribe(Collections.singletonList(topic));
        ConsumerRecords<Integer, String> records = consumer.poll(1000);
        for (ConsumerRecord<Integer, String> record : records) {
            System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
            txt.append("\n offset :"+ record.offset() + "|Key{" + record.key() + "}|Record:" + record.value());
            if(stop) {
            	break;
            }
        }
        stop = true;
		
	}
}
