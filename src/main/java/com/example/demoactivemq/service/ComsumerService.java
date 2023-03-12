package com.example.demoactivemq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.demoactivemq.model.Department;
import com.example.demoactivemq.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ComsumerService {

    @JmsListener(destination = "jms-Queue", containerFactory = "jmsListenerContainerFactoryQueue")
    public void receiveMessageQueue(Employee empployee) {
        log.info("Received <" + empployee.toString() + ">");

    }

    @JmsListener(destination = "jms-Topic", containerFactory = "jmsListenerContainerFactoryTopic" )
    public void receiveMessageTopic(Department department) {

        log.info("Received <" + department.toString() + ">");

    }

}
