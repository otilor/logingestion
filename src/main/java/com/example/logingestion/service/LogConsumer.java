package com.example.logingestion.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LogConsumer {
    @KafkaListener(topics = "logs-topic", groupId = "log-consumer-group")
    public void consume(String message) {
        System.out.println("Consumed log: " + message);
    }
}
