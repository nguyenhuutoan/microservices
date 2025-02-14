package com.food.ordering.system.order.service.dataaccess.user.mapper;

import com.food.ordering.system.domain.valueobject.UserId;
import com.food.ordering.system.order.service.dataaccess.user.entity.UserEntity;
import com.food.ordering.system.order.service.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataAccessMapper {

    public User userEntityToUser(UserEntity customerEntity) {
        return new User(new UserId(customerEntity.getId()));
    }

    public UserEntity userToUserEntity(User customer) {
        return UserEntity.builder()
                .id(customer.getId().getValue())
                .username(customer.getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }
}