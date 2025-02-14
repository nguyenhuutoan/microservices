package com.food.ordering.system.user.service.domain;

import com.food.ordering.system.user.service.domain.entity.User;
import com.food.ordering.system.user.service.domain.event.UserCreatedEvent;

public interface UserDomainService {

    UserCreatedEvent validateAndInitiateUser(User customer);

}