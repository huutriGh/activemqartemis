package com.example.demoactivemq.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demoactivemq.model.Department;
import com.example.demoactivemq.model.Employee;
//import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

//import jakarta.jms.TextMessage;

@Component
@Slf4j
public class ProducerService {

    private int id = 0;
    private int empId = 0;
    @Value("${spring.artemis.queue}")
    private String queue;
    @Value("${spring.artemis.topic}")
    private String topic;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 100)
    public void sendToQueue() {
        try {
            Employee employee = new Employee()
                    .setId(++empId)
                    .setFirstName("Nguyen")
                    .setLastName("Tri")
                    .setBirthday(new Date())
                    .setSalary(1000);

            // String jsonObj = new
            // ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(employee);
            // jmsTemplate.send(queue, messageCreator -> {
            // TextMessage message = messageCreator.createTextMessage();
            // message.setText(jsonObj);
            // return message;
            // });
            jmsTemplate.convertAndSend(queue, employee);
        } catch (Exception ex) {

            log.error("ERROR in sending message to queue", ex);
            ex.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 10000)
    public void sendToTopic() {
        try {
            Department department = new Department()
                    .setId(++id).setDepartmentName("IT").setNoOfEmployee(60);
            // String jsonObj = new
            // ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(department);
            // jmsTemplate.send(topic, messageCreator -> {
            // TextMessage message = messageCreator.createTextMessage();
            // message.setText(jsonObj);
            // return message;
            // });

            jmsTemplate.convertAndSend(topic, department);
        } catch (Exception ex) {
            log.error("ERROR in sending message to topic", ex);
            ex.printStackTrace();
        }
    }

}
