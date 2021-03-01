package com.schinewitz.daniel.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${payment.topic.name}")
    private String paymentTopic;

    @Value(value = "${laundery.topic.name}")
    private String launderyTopic;

    public void sendMessageToPaymentTopic(String message) {

       kafkaTemplate.send(paymentTopic, message);
    }

    public void sendMessageToLaunderingTopic(String message) {

        kafkaTemplate.send(launderyTopic, message);
    }
}
