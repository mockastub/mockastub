package org.mockastub.stub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);
    
    private RequestResponseMapper requestResponseMapper;

    @Autowired
    public Receiver(RequestResponseMapper requestResponseMapper) {
        this.requestResponseMapper = requestResponseMapper;        
    }
    
    @JmsListener(destination = "planetSearch", containerFactory = "myJmsContainerFactory")
    @SendTo("planetResponse")
    public String planetSearch(String message) {
        System.out.println("Received searched <" + message + ">");
        
        String key = String.valueOf(message.hashCode()); 
        
        String response = requestResponseMapper.getResponseFor(key);
        
       
        
        return response;
    }

    @JmsListener(destination = "planetBlowup", containerFactory = "myJmsContainerFactory")
    @SendTo("planetResponse")
    public String receiveMessage(String message) {
        return "Received obliterated <" + message + ">";
    }
}