package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(
        bootstrapServersProperty = "spring.kafka.bootstrap-servers",
        brokerProperties = {"listeners=PLAINTEXT://localhost:9093", "port=9093"}
)
public class DemoApplicationWithMockTests {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Autowired
    private OtherService otherService;

    @MockBean
    private ThirdService thirdService;

    @Test
    void contextLoads() {
        when(thirdService.get()).thenReturn("blubb");

        kafkaTemplate.send("testTopic", new TestEvent("schwampf"));

        await().until(() -> otherService.getText() != null && otherService.getText().endsWith("blubb"));
    }

}
