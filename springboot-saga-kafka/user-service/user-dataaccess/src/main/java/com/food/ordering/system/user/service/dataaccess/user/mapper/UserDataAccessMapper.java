package com.food.ordering.system.user.service.dataaccess.user.mapper;

import com.food.ordering.system.user.service.dataaccess.user.entity.UserEntity;
import com.food.ordering.system.user.service.domain.entity.User;
import com.food.ordering.system.domain.valueobject.UserId;
import org.springframework.stereotype.Component;

@Component
public class UserDataAccessMapper {

    public User userEntityToUser(UserEntity customerEntity) {
        return new User(new UserId(customerEntity.getId()),
                customerEntity.getUsername(),
                customerEntity.getFirstName(),
                customerEntity.getLastName(),
                customerEntity.getUserType());
    }

    public UserEntity userToUserEntity(User customer) {
        return UserEntity.builder()
                .id(customer.getId().getValue())
                .username(customer.getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .userType(customer.getUserType())
                .build();
    }

}