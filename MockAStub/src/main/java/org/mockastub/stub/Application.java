package org.mockastub.stub;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

@SpringBootApplication
@EnableJms
public class Application {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public BrokerService mainBroker() throws Exception {
        return BrokerFactory.createBroker("broker:(tcp://localhost:61616)?persistent=false&useJmx=true");
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory result = new ActiveMQConnectionFactory();
        result.setBrokerURL("tcp://localhost:61616");
        return result;
    }
    
    @Bean
    public JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

//    @Bean // Strictly speaking this bean is not necessary as boot creates a default
//    ServletRegistrationBean myServletRegistrationBean(ConnectionFactory connectionFactory) {
//    	return new ServletRegistrationBean(new MessageProviderServlet(), "/view/*");
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);   
    }
}