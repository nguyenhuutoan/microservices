package com.food.ordering.system.order.service.messaging.listener.kafka;

import com.food.ordering.system.kafka.consumer.KafkaConsumer;
//import com.food.ordering.system.kafka.order.avro.model.CustomerAvroModel;
import com.food.ordering.system.kafka.order.avro.model.UserAvroModel;
//import com.food.ordering.system.order.service.domain.ports.input.message.listener.customer.CustomerMessageListener;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.user.UserMessageListener;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserKafkaListener implements KafkaConsumer<UserAvroModel> {

    private final UserMessageListener userMessageListener;
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    public UserKafkaListener(UserMessageListener userMessageListener,
                                 OrderMessagingDataMapper orderMessagingDataMapper) {
        this.userMessageListener = userMessageListener;
        this.orderMessagingDataMapper = orderMessagingDataMapper;
    }

    @Override
    // @KafkaListener(id = "${kafka-consumer-config.customer-group-id}", topics = "${order-service.customer-topic-name}")
    @KafkaListener(id = "user_topic_consumer", topics = "user")
    public void receive(@Payload List<UserAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of customer create messages received with keys {}, partitions {} and offsets {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(userAvroModel ->
                userMessageListener.userCreated(orderMessagingDataMapper
                        .userAvroModeltoUserModel(userAvroModel)));
    }
}