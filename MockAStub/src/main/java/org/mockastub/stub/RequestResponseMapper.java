package org.mockastub.stub;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class RequestResponseMapper {

    private Map<String, String> requestResponseMapper = new HashMap<String, String>();
    
    public RequestResponseMapper() {
        requestResponseMapper.put("46612798", "Earth, Alderaan, Mars"); // 1,2,3
    }
    
    public String getResponseFor(String key) {
        return requestResponseMapper.get(key);
    }
    
}
