package com.food.ordering.system.user.service.application.rest;

import com.food.ordering.system.user.service.domain.create.CreateUserCommand;
import com.food.ordering.system.user.service.domain.create.CreateUserResponse;
import com.food.ordering.system.user.service.domain.ports.input.service.UserApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/users", produces = "application/vnd.api.v1+json")
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserCommand
                                                                         createUserCommand) {
        log.info("Creating user with username: {}", createUserCommand.getUsername());
        CreateUserResponse response = userApplicationService.createUser(createUserCommand);
        return ResponseEntity.ok(response);
    }

}