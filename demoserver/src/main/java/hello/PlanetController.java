package hello;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanetController {

    @Autowired
    private JmsTemplate jmsTemplate;
    
    @RequestMapping("/planets")
    public List<Planet> searchPlanets(@RequestParam(value="hostile", defaultValue="false") boolean hostile, 
            @RequestParam(value="location", defaultValue="all") String location) {
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(location);
            }
        };
        
        jmsTemplate.send("planetSearch", messageCreator);

        List<Planet> result = new ArrayList<Planet>();
        result.add(new Planet("Earth", false));
        result.add(new Planet("Mars", false));
        result.add(new Planet("Alderon", true));
        return result;
    }
}