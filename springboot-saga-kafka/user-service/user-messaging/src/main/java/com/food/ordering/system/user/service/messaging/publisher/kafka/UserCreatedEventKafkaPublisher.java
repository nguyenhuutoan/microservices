package com.food.ordering.system.user.service.messaging.publisher.kafka;

import com.food.ordering.system.user.service.domain.config.UserServiceConfigData;
import com.food.ordering.system.user.service.domain.event.UserCreatedEvent;
import com.food.ordering.system.user.service.domain.ports.output.message.publisher.UserMessagePublisher;
import com.food.ordering.system.user.service.messaging.mapper.UserMessagingDataMapper;
import com.food.ordering.system.kafka.order.avro.model.UserAvroModel;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class UserCreatedEventKafkaPublisher implements UserMessagePublisher {

    private final UserMessagingDataMapper customerMessagingDataMapper;

    private final KafkaProducer<String, UserAvroModel> kafkaProducer;

    private final UserServiceConfigData customerServiceConfigData;

    public UserCreatedEventKafkaPublisher(UserMessagingDataMapper customerMessagingDataMapper,
                                              KafkaProducer<String, UserAvroModel> kafkaProducer,
                                              UserServiceConfigData customerServiceConfigData) {
        this.customerMessagingDataMapper = customerMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.customerServiceConfigData = customerServiceConfigData;
    }

    @Override
    public void publish(UserCreatedEvent customerCreatedEvent) {
        log.info("Received CustomerCreatedEvent for customer id: {}",
                customerCreatedEvent.getUser().getId().getValue());
        try {
            UserAvroModel customerAvroModel = customerMessagingDataMapper
                    .paymentResponseAvroModelToPaymentResponse(customerCreatedEvent);

            kafkaProducer.send(customerServiceConfigData.getUserTopicName(), customerAvroModel.getId(),
                    customerAvroModel,
                    getCallback(customerServiceConfigData.getUserTopicName(), customerAvroModel));

            log.info("CustomerCreatedEvent sent to kafka for customer id: {}",
                    customerAvroModel.getId());
        } catch (Exception e) {
            log.error("Error while sending CustomerCreatedEvent to kafka for customer id: {}," +
                    " error: {}", customerCreatedEvent.getUser().getId().getValue(), e.getMessage());
        }
    }

    private CompletableFuture<SendResult<String, UserAvroModel>>
    getCallback(String topicName, UserAvroModel message) {
        return new CompletableFuture() {
            //@Override
            public void onFailure(Throwable throwable) {
                log.error("Error while sending message {} to topic {}", message.toString(), topicName, throwable);
            }

            //@Override
            public void onSuccess(SendResult<String, UserAvroModel> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received new metadata. Topic: {}; Partition {}; Offset {}; Timestamp {}, at time {}",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime());
            }
        };
    }
}