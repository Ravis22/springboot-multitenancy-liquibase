package com.example.multitenantliquibase.service;

import com.example.multitenantliquibase.config.DataSourceRouting;
import com.example.multitenantliquibase.model.User;
import com.example.multitenantliquibase.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final DataSourceRouting     dataSourceRouting;

    public String createUser(String username) {
        User user = new User();
        user.setUsername(username);
        userRepo.save(user);
        return user.getUsername();
    }
}
