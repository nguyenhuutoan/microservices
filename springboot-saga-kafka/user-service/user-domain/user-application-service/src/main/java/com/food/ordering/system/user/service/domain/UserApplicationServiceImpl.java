package com.food.ordering.system.user.service.domain;

import com.food.ordering.system.user.service.domain.create.CreateUserCommand;
import com.food.ordering.system.user.service.domain.create.CreateUserResponse;
import com.food.ordering.system.user.service.domain.event.UserCreatedEvent;
import com.food.ordering.system.user.service.domain.mapper.UserDataMapper;
import com.food.ordering.system.user.service.domain.ports.input.service.UserApplicationService;
import com.food.ordering.system.user.service.domain.ports.output.message.publisher.UserMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class UserApplicationServiceImpl implements UserApplicationService {

    private final UserCreateCommandHandler customerCreateCommandHandler;

    private final UserDataMapper customerDataMapper;

    private final UserMessagePublisher customerMessagePublisher;

    public UserApplicationServiceImpl(UserCreateCommandHandler customerCreateCommandHandler,
                                      UserDataMapper customerDataMapper,
                                      UserMessagePublisher customerMessagePublisher) {
        this.customerCreateCommandHandler = customerCreateCommandHandler;
        this.customerDataMapper = customerDataMapper;
        this.customerMessagePublisher = customerMessagePublisher;
    }

    @Override
    public CreateUserResponse createUser(CreateUserCommand createCustomerCommand) {
        UserCreatedEvent customerCreatedEvent = customerCreateCommandHandler.createUser(createCustomerCommand);
        customerMessagePublisher.publish(customerCreatedEvent);
        return customerDataMapper
                .customerToCreateCustomerResponse(customerCreatedEvent.getUser(),
                        "Customer saved successfully!");
    }
}