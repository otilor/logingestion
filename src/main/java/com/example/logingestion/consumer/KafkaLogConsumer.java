package com.example.logingestion.consumer;

import org.springframework.stereotype.Service;
import com.example.logingestion.service.LokiService;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class KafkaLogConsumer {

    private final LokiService lokiService;

    public KafkaLogConsumer(LokiService lokiService) {
        this.lokiService = lokiService;
    }

    @KafkaListener(topics = "logs-topic", groupId = "log-consumer-group")
    public void consume(String log) {
        lokiService.sendLog("INFO", log);
    }
}
