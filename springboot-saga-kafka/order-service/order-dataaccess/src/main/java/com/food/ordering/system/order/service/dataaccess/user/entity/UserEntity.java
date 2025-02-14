package com.food.ordering.system.order.service.dataaccess.user.entity;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private Integer userType;
}