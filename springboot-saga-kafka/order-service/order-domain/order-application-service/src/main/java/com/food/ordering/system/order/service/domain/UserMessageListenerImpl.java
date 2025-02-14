package com.food.ordering.system.order.service.domain;

//import com.food.ordering.system.order.service.domain.dto.message.CustomerModel;
import com.food.ordering.system.order.service.domain.dto.message.UserModel;
//import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.entity.User;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
//import com.food.ordering.system.order.service.domain.ports.input.message.listener.customer.CustomerMessageListener;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.user.UserMessageListener;
//import com.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserMessageListenerImpl implements UserMessageListener {

    private final UserRepository userRepository;
    private final OrderDataMapper orderDataMapper;

    public UserMessageListenerImpl(UserRepository userRepository, OrderDataMapper orderDataMapper) {
        this.userRepository = userRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Override
    public void userCreated(UserModel userModel) {
        User user = userRepository.save(orderDataMapper.userModelToUser(userModel));
        if (user == null) {
            log.error("Customer could not be created in order database with id: {}", userModel.getId());
            throw new OrderDomainException("Customer could not be created in order database with id " +
                    userModel.getId());
        }
        log.info("Customer is created in order database with id: {}", user.getId());
    }
}
