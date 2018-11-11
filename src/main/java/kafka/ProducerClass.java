package kafka;

import java.util.concurrent.ExecutionException;

import javax.swing.JTextArea;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import config.constants;

public class ProducerClass {

	public ProducerClass(String Schema) {
		
	}

	/**
	 * @param Topic
	 * @param Schema
	 * @param Key 
	 * @param txt
	 * @param con 
	 * @return boolean
	 */
	public boolean send(String Topic,String Schema,int Key, JTextArea txt, connection con) {
		if(Schema == constants.schema.Json.toString()) {
			KafkaProducer<Integer, String> kp = con.getKafkaProducer(Schema);
			try {
				System.out.println(Topic+" "+Key+" "+txt.getText());
				kp.send(new ProducerRecord<>(Topic,Key,txt.getText())).get();
				return true;
			} catch (InterruptedException | ExecutionException e) {
				System.out.println("Unable to Send Data (Reason):"+e.toString());
				return false;
			}
		}else if(Schema == constants.schema.Avro.toString()) {
			System.out.println("s");
		}
		return false;
	}

}
