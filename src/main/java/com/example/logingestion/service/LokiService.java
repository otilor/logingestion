package com.example.logingestion.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LokiService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String LOKI_URL = "http://localhost:3100/loki/api/v1/push";

    public void sendLog(String level, String message) {
        Map<String, Object> logEntry = new HashMap<>();
        logEntry.put("streams", new Object[] {
            new HashMap<String, Object>() {{
                put("stream", new HashMap<String, String>() {{
                    put("level", level);
                }});
                put("values", new Object[] {
                    new Object[] { String.valueOf(System.currentTimeMillis() * 1000000), message }
                });
            }}
        });

        restTemplate.postForObject(LOKI_URL, logEntry, String.class);
    }
}
