package com.schinewitz.daniel.kafka.consumer;

import com.schinewitz.daniel.kafka.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class MessageListener {

    @Autowired
    MessageProducer producer;

    private int msgCounterPayment = 0;
    private int msgCounterLaundery = 0;

    @KafkaListener(topics = "${payment.topic.name}", groupId = "kafka-demo")
    public void listenForPaymentTopic(String message) {
        System.out.println("#" + msgCounterPayment++ + " Topic Payment - Received Message: " + message);

        if(isPaymentLargerThan1000(message)) {                  // Money Laundering Check
            producer.sendMessageToLaunderingTopic(message);
        }
    }

    @KafkaListener(topics = "${laundery.topic.name}", groupId = "kafka-demo")
    public void listenForLaunderyTopic(String message) {
        System.out.println("#" + msgCounterLaundery++ + " Topic LaunderyCheck - Received Message: " + message + " ----------------- DECLINED");
    }



    private boolean isPaymentLargerThan1000(String message) {

        return Double.parseDouble(message) > 1000;
    }
}

