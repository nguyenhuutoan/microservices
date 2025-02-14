package com.food.ordering.system.user.service.domain.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateUserResponse {
    @NotNull
    private final UUID userId;
    @NotNull
    private final String message;
}