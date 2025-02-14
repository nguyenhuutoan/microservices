package com.food.ordering.system.order.service.domain.ports.input.message.listener.user;

import com.food.ordering.system.order.service.domain.dto.message.UserModel;

public interface UserMessageListener {

    void userCreated(UserModel userModel);
}