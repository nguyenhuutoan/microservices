package com.food.ordering.system.user.service.domain;

import com.food.ordering.system.user.service.domain.create.CreateUserCommand;
import com.food.ordering.system.user.service.domain.entity.User;
import com.food.ordering.system.user.service.domain.event.UserCreatedEvent;
import com.food.ordering.system.user.service.domain.exception.UserDomainException;
import com.food.ordering.system.user.service.domain.mapper.UserDataMapper;
import com.food.ordering.system.user.service.domain.ports.output.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
class UserCreateCommandHandler {

    private final UserDomainService customerDomainService;

    private final UserRepository customerRepository;

    private final UserDataMapper customerDataMapper;

    public UserCreateCommandHandler(UserDomainService customerDomainService,
                                    UserRepository customerRepository,
                                    UserDataMapper customerDataMapper) {
        this.customerDomainService = customerDomainService;
        this.customerRepository = customerRepository;
        this.customerDataMapper = customerDataMapper;
    }

    @Transactional
    public UserCreatedEvent createUser(CreateUserCommand createCustomerCommand) {
        User customer = customerDataMapper.createUserCommandToUser(createCustomerCommand);
        UserCreatedEvent customerCreatedEvent = customerDomainService.validateAndInitiateUser(customer);
        User savedCustomer = customerRepository.createUser(customer);
        if (savedCustomer == null) {
            log.error("Could not save customer with id: {}", createCustomerCommand.getUserId());
            throw new UserDomainException("Could not save customer with id " +
                    createCustomerCommand.getUserId());
        }
        log.info("Returning CustomerCreatedEvent for customer id: {}", createCustomerCommand.getUserId());
        return customerCreatedEvent;
    }
}