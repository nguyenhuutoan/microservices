package com.food.ordering.system.user.service.domain.mapper;

import com.food.ordering.system.user.service.domain.create.CreateUserCommand;
import com.food.ordering.system.user.service.domain.create.CreateUserResponse;
import com.food.ordering.system.user.service.domain.entity.User;
import com.food.ordering.system.domain.valueobject.UserId;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {

    public User createUserCommandToUser(CreateUserCommand createCustomerCommand) {
        return new User(new UserId(createCustomerCommand.getUserId()),
                createCustomerCommand.getUsername(),
                createCustomerCommand.getFirstName(),
                createCustomerCommand.getLastName(),
                createCustomerCommand.getUserType());
    }

    public CreateUserResponse customerToCreateCustomerResponse(User customer, String message) {
        return new CreateUserResponse(customer.getId().getValue(), message);
    }
}