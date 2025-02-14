package com.food.ordering.system.order.service.domain.ports.output.repository;

//import com.food.ordering.system.order.service.domain.entity.User;
import com.food.ordering.system.order.service.domain.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findUser(UUID userId);

    User save(User user);
}