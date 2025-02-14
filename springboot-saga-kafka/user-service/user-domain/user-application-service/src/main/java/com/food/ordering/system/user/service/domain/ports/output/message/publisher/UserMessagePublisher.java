package com.food.ordering.system.user.service.domain.ports.output.message.publisher;

import com.food.ordering.system.user.service.domain.event.UserCreatedEvent;

public interface UserMessagePublisher {

    void publish(UserCreatedEvent customerCreatedEvent);

}