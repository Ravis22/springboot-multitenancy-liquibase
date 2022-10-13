package com.example.multitenantliquibase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = {"com.example.multitenantliquibase"})
@EnableConfigurationProperties(LiquibaseProperties.class)
public class MultiTenantLiquibaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiTenantLiquibaseApplication.class, args);
    }

}
