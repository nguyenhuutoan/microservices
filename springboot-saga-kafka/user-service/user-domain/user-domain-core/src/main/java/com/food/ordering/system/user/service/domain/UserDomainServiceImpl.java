package com.food.ordering.system.user.service.domain;

import com.food.ordering.system.user.service.domain.entity.User;
import com.food.ordering.system.user.service.domain.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class UserDomainServiceImpl implements UserDomainService {

    public UserCreatedEvent validateAndInitiateUser(User customer) {
        //Any Business logic required to run for a customer creation
        log.info("Customer with id: {} is initiated", customer.getId().getValue());
        return new UserCreatedEvent(customer, ZonedDateTime.now(ZoneId.of("UTC")));
    }
}