package org.mockastub.stub;

import java.io.File;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

@Component
public class Receiver {
    @JmsListener(destination = "mailbox-destination", containerFactory = "myJmsContainerFactory")
    public void planetSearch(String message) {
        System.out.println("Received searched <" + message + ">");
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
    }
}