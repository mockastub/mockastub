package org.mockastub.stub;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class RequestResponseMapper {

    private Map<String, String> requestResponseMapper = new HashMap<String, String>();
    
    public RequestResponseMapper() {
        requestResponseMapper.put("1,2,3", "Earth, Alderaan, Mars"); // 1,2,3
        requestResponseMapper.put("Earth", "NotOK"); // 1,2,3
        requestResponseMapper.put("Alderaan", "booooom"); // 1,2,3
        requestResponseMapper.put("Mars", "booooom"); // 1,2,3
    }
    
    public String getResponseFor(String key) {
        return requestResponseMapper.get(key);
    }
    
}
