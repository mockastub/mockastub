package org.mockastub.viewer.atom;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	
    @Bean // Strictly speaking this bean is not necessary as boot creates a default
    ServletRegistrationBean myServletRegistrationBean(ConnectionFactory connectionFactory) {
    	return new ServletRegistrationBean(new MessageProviderServlet(), "/viewer/*");
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

    }

}
