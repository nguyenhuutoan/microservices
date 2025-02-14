package com.food.ordering.system.user.service.messaging.mapper;

import com.food.ordering.system.user.service.domain.event.UserCreatedEvent;
import com.food.ordering.system.kafka.order.avro.model.UserAvroModel;
import org.springframework.stereotype.Component;

@Component
public class UserMessagingDataMapper {

    public UserAvroModel paymentResponseAvroModelToPaymentResponse(UserCreatedEvent
                                                                               customerCreatedEvent) {
        return UserAvroModel.newBuilder()
                .setId(customerCreatedEvent.getUser().getId().getValue().toString())
                .setUsername(customerCreatedEvent.getUser().getUsername())
                .setFirstName(customerCreatedEvent.getUser().getFirstName())
                .setLastName(customerCreatedEvent.getUser().getLastName())
                .setUserType(customerCreatedEvent.getUser().getUserType())
                .build();
    }
}
