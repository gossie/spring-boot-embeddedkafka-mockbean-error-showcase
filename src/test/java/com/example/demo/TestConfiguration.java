package com.example.demo;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
public class TestConfiguration {

    @Bean
    KafkaTemplate<Object, Object> kafkaTemplate(ProducerFactory<Object, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory, Map.of(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class));
    }

    @Bean
    NewTopic testTopic() {
        return new NewTopic("testTopic", 1, (short) 1);
    }
}
