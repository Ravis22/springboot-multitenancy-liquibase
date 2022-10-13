package com.example.multitenantliquibase.repo;

import com.example.multitenantliquibase.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
}
