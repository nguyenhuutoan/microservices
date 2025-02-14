package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.UserId;

public class User extends AggregateRoot<UserId> {

    private String username;
    private String firstName;
    private String lastName;
    private Integer userType;

    public User(UserId userId, String username, String firstName, String lastName, Integer userType) {
        super.setId(userId);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }

    public User(UserId userId) {
        super.setId(userId);
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
