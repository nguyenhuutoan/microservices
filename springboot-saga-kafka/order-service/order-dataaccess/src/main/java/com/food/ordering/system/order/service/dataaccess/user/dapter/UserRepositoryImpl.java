package com.food.ordering.system.order.service.dataaccess.user.dapter;

import com.food.ordering.system.order.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.user.repository.UserJpaRepository;
import com.food.ordering.system.order.service.domain.entity.User;
import com.food.ordering.system.order.service.domain.ports.output.repository.UserRepository;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

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
    public Optional<User> findUser(UUID customerId) {
        return customerJpaRepository.findById(customerId).map(customerDataAccessMapper::userEntityToUser);
    }

    @Transactional
    @Override
    public User save(User customer) {
        return customerDataAccessMapper.userEntityToUser(
                customerJpaRepository.save(customerDataAccessMapper.userToUserEntity(customer)));
    }
}