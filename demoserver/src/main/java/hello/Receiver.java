package hello;

import java.io.File;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

@Component
public class Receiver {
    @JmsListener(destination = "planetSearch", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
    }
}