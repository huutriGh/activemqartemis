package com.example.demoactivemq.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Session;

@Configuration
public class AppConfig {

    @Value("${spring.artemis.broker-url}")
    private String BROKER_URL;

    @Value("${spring.artemis.user}")
    private String USER;

    @Value("${spring.artemis.password}")
    private String PASSWORD;

    @Bean("activeMQConnectionFactory")
    ActiveMQConnectionFactory getActiveMQConnectionFactory() throws JMSException {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(BROKER_URL);
        activeMQConnectionFactory.setUser(USER);
        activeMQConnectionFactory.setPassword(PASSWORD);
        return activeMQConnectionFactory;
    }

    @Bean("jmsListenerContainerFactoryTopic")
    JmsListenerContainerFactory<?> JmsListenerContainerFactoryTopic() throws JMSException {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(getActiveMQConnectionFactory());
        factory.setPubSubDomain(true);
        factory.setConcurrency("3");
        return factory;
    }
    @Bean("jmsListenerContainerFactoryQueue")
    JmsListenerContainerFactory<?> JmsListenerContainerFactoryQueue() throws JMSException {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(getActiveMQConnectionFactory());
        factory.setPubSubDomain(false);
        factory.setConcurrency("3");
        return factory;
    }

    @Bean
    CachingConnectionFactory cachingConnectionFactory() throws JMSException {
        return new CachingConnectionFactory(
                getActiveMQConnectionFactory());
    }

    @Bean
    JmsTemplate jmsTemplate() throws JMSException {
        JmsTemplate jmsTemplate = new JmsTemplate(getActiveMQConnectionFactory());
        jmsTemplate.setDestinationResolver(destinationResolver());
        return jmsTemplate;
    }

    @Bean
    DynamicDestinationResolver destinationResolver() {
        return new DynamicDestinationResolver() {
            @Override
            public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain) throws JMSException {
                if(destinationName.endsWith("Topic")) {
                    pubSubDomain = true;
                }
                else {
                    pubSubDomain = false;
                }
                return super.resolveDestinationName(session,destinationName,pubSubDomain);
            }
        };
    }

}
