package com.example.multitenantliquibase.controller;

import com.example.multitenantliquibase.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public String createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto.userName);
    }

    @Getter
    @Setter
    private static class UserDto {
        private String userName;
    }
}
