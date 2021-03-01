package com.schinewitz.daniel.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class MessageListener {

    private CountDownLatch latch = new CountDownLatch(3);

    @KafkaListener(topics = "${payment.topic.name}", groupId = "kafka-demo")
    public void listenForPaymentTopic(String message) {
        System.out.println("Topic Payment - Received Message: " + message);
        latch.countDown();
    }

    @KafkaListener(topics = "${laundery.topic.name}", groupId = "kafka-demo")
    public void listenForLaunderyTopic(String message) {
        System.out.println("Topic Laundery - Received Message: " + message);
        latch.countDown();
    }

}

