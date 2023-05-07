package com.example.showcase.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

    @Value(value = "${topic.update-process}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;
    public LoggerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void processLogger(String message){
        sendMessage(message);
    }

    public void sendMessage(String msg) {
        kafkaTemplate.send(topicName, msg);
    }
}
