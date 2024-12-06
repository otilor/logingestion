package com.example.logingestion.controller;

import com.example.logingestion.producer.LogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private LogProducer logProducer;

    @PostMapping
    public String receiveLog(@RequestBody LogRequest logRequest) {
        logProducer.sendLog("logs-topic", logRequest.getMessage());
        return "Log sent to Kafka";
    }
}

class LogRequest {
    private long timestamp; // Ensure the field matches the expected type (e.g., 'long')

    private String level;
    private String message;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}