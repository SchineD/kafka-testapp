package com.schinewitz.daniel.kafka;

import com.schinewitz.daniel.kafka.consumer.MessageListener;
import com.schinewitz.daniel.kafka.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);  // Core Banking System
        MessageListener listener = context.getBean(MessageListener.class);

        produceRandomPayments(producer);
    }

    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }

    public static void produceRandomPayments(MessageProducer producer) throws InterruptedException {

        System.out.println("************----------- Generate 100 random Payments -----------************");

        Random random = new Random();

        for(int i = 0; i < 100; i++) {
            int randomPaymentAmount = random.nextInt(2000);
            producer.sendMessageToPaymentTopic(String.valueOf(randomPaymentAmount));
            Thread.sleep(700);
        }
    }
}
