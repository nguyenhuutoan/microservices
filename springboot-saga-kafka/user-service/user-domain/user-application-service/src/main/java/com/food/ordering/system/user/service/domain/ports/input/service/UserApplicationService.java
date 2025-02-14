package com.food.ordering.system.user.service.domain.ports.input.service;

import com.food.ordering.system.user.service.domain.create.CreateUserCommand;
import com.food.ordering.system.user.service.domain.create.CreateUserResponse;

import jakarta.validation.Valid;

public interface UserApplicationService {

    CreateUserResponse createUser(@Valid CreateUserCommand createCustomerCommand);

}