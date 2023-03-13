package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class KafkaHandler {

    private final OtherService otherService;

    public KafkaHandler(OtherService otherService) {
        this.otherService = otherService;
    }

    @KafkaListener(topics = "testTopic", groupId = "testGroup")
    public void handleEvent(Message<TestEvent> event) {
        otherService.enhance(event.getPayload().data());
    }

}
