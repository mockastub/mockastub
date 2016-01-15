package hello;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

@SpringBootApplication
@EnableJms
public class Application {

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory result = new ActiveMQConnectionFactory();
        result.setBrokerURL("tcp://localhost:61616");
        return result;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}