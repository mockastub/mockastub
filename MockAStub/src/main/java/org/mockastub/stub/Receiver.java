package org.mockastub.stub;

import java.util.Date;

import org.mockastub.viewer.atom.MessageEntry;
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

    private MessageStore messageStore;

    @Autowired
    public Receiver(RequestResponseMapper requestResponseMapper, MessageStore messageStore) {
        this.requestResponseMapper = requestResponseMapper;
        this.messageStore = messageStore;        
    }
    
    @JmsListener(destination = "planetSearch", containerFactory = "myJmsContainerFactory")
    @SendTo("planetResponse")
    public String planetSearch(String message) {
        String key = String.valueOf(message.hashCode()); 
        logMessageIn(message);        
        String response = requestResponseMapper.getResponseFor(key);
        logMessageOut(message);        
        return response;
    }

    private void logMessageIn(String message) {
        MessageEntry msg = new MessageEntry();
        msg.setMessage(message);
        msg.setUpdated(new Date());
        String title = "message received";
        msg.setTitle(title);
        msg.setId(MessageStore.getAndIncrementNextId());
        messageStore.put(msg);
    }

    private void logMessageOut(String message) {
        MessageEntry msg = new MessageEntry();
        msg.setMessage(message);
        msg.setUpdated(new Date());
        msg.setTitle("message replied");
        msg.setId(MessageStore.getAndIncrementNextId());
        messageStore.put(msg);
    }

    @JmsListener(destination = "planetBlowup", containerFactory = "myJmsContainerFactory")
    @SendTo("planetResponse")
    public String receiveMessage(String message) {
        return "Received obliterated <" + message + ">";
    }
}