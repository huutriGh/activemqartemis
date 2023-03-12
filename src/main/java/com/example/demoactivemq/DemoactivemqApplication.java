package com.example.demoactivemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

//import com.example.demoactivemq.service.ProducerService;

@SpringBootApplication
@EnableJms
@EnableScheduling
public class DemoactivemqApplication {

	public static void main(String[] args) {
		//var context = 
		SpringApplication.run(DemoactivemqApplication.class, args);

		// ProducerService producerService  = context.getBean(ProducerService.class);
		// producerService.sendToQueue();
		// producerService.sendToTopic();
	}

}
