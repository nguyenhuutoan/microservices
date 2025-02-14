package com.food.ordering.system.user.service.domain.event;

import com.food.ordering.system.user.service.domain.entity.User;
import com.food.ordering.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public class UserCreatedEvent implements DomainEvent<User> {

    private final User customer;

    private final ZonedDateTime createdAt;

    public UserCreatedEvent(User customer, ZonedDateTime createdAt) {
        this.customer = customer;
        this.createdAt = createdAt;
    }

    public User getUser() {
        return customer;
    }
}