package com.food.ordering.system.user.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.UserId;

public class User extends AggregateRoot<UserId> {
    private final String username;
    private final String firstName;
    private final String lastName;
    private final Integer userType;

    public User(UserId customerId, String username, String firstName, String lastName, Integer userType) {
        super.setId(customerId);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType =userType;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getUserType() {
        return userType;
    }
}