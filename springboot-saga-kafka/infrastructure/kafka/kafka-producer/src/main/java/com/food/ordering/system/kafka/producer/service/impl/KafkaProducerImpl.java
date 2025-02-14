package com.food.ordering.system.kafka.producer.service.impl;

import com.food.ordering.system.kafka.producer.exception.KafkaProducerException;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import jakarta.annotation.PreDestroy;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    public KafkaProducerImpl(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //deprecated since version 6.0
//    @Override
//    public void send(String topicName, K key, V message, ListenableFutureCallback<SendResult<K, V>> callback) {
//        log.info("Sending message={} to topic={}", message, topicName);
//        try {
//            ListenableFuture<SendResult<K, V>> kafkaResultFuture = kafkaTemplate.send(topicName, key, message);
//            kafkaResultFuture.addCallback(callback);
//        } catch (KafkaException e) {
//            log.error("Error on kafka producer with key: {}, message: {} and exception: {}", key, message,
//                    e.getMessage());
//            throw new KafkaProducerException("Error on kafka producer with key: " + key + " and message: " + message);
//        }
//    }


    //since version 6.0
    @Override
    public void send(String topicName, K key, V message, CompletableFuture<SendResult<K, V>> callback) {
        log.info("Sending message={} to topic={}", message, topicName);
        try {
            CompletableFuture<SendResult<K, V>> kafkaResultFuture = kafkaTemplate.send(topicName, key, message);
            //kafkaResultFuture.addCallback(callback);
            kafkaResultFuture.whenComplete(
                    (res, error) -> {
                        if (error != null) {
                            // handle the exception scenario
                        } else if (res != null) {
                            // send data to DB
                        }
                    });
        } catch (KafkaException e) {
            log.error("Error on kafka producer with key: {}, message: {} and exception: {}", key, message,
                    e.getMessage());
            throw new KafkaProducerException("Error on kafka producer with key: " + key + " and message: " + message);
        }
    }

    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            log.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }
}