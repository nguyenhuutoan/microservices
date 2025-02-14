package com.food.ordering.system.user.service.dataaccess.user.adapter;

import com.food.ordering.system.user.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.food.ordering.system.user.service.dataaccess.user.repository.UserJpaRepository;
import com.food.ordering.system.user.service.domain.entity.User;
import com.food.ordering.system.user.service.domain.ports.output.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository customerJpaRepository;

    private final UserDataAccessMapper customerDataAccessMapper;

    public UserRepositoryImpl(UserJpaRepository customerJpaRepository,
                                  UserDataAccessMapper customerDataAccessMapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.customerDataAccessMapper = customerDataAccessMapper;
    }

    @Override
    public User createUser(User customer) {
        return customerDataAccessMapper.userEntityToUser(
                customerJpaRepository.save(customerDataAccessMapper.userToUserEntity(customer)));
    }
}