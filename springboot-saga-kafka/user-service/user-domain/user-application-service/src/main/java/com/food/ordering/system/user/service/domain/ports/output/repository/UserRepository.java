package com.food.ordering.system.user.service.domain.ports.output.repository;

import com.food.ordering.system.user.service.domain.entity.User;

public interface UserRepository {

    User createUser(User customer);
}
