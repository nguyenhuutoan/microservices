package com.food.ordering.system.user.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

public class UserDomainException extends DomainException {

    public UserDomainException(String message) {
        super(message);
    }
}