package hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanetController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping("/planets")
    public List<Planet> searchPlanets(@RequestParam(value = "hostile", defaultValue = "false") boolean hostile,
            @RequestParam(value = "location", defaultValue = "all") String location) throws Exception {
        MessageCreator messageCreator = new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(location);
            }
        };

        jmsTemplate.send("planetSearch", messageCreator);
        TextMessage msg = (TextMessage) jmsTemplate.receive("planetResponse");

        List<Planet> planetList = new ArrayList<Planet>();
        try {
            String message = msg.getText();
            String[] planets = message.split(",");
            for (String string : planets) {
                planetList.add(new Planet(string, false));
            }
            return planetList;
        } catch (JMSException e) {
            throw new Exception("not connected to the MockerAStub.", e);
        }

    }

    @RequestMapping(value = "/planet/{name}", method = RequestMethod.DELETE)
    public String obliterate(@PathVariable("name") String name) {
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(name);
            }
        };

        jmsTemplate.send("planetBlowup", messageCreator);

        String response = String.format("{\"message\":\"%s\"}", String.format("obliterated %s", name));
        System.out.println(response);
        return response;
    }

}