package com.example.multitenantliquibase.config;

import liquibase.integration.spring.MultiTenantSpringLiquibase;
import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final LiquibaseProperties liquibaseProperties;
    private final DataSourceProperties dataSourceProperties;

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        String defaultSchema = dataSourceProperties.getDefaultSchema();
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(liquibaseProperties.getChangeLog());
        liquibase.setDefaultSchema(defaultSchema);
        return liquibase;
    }

    @Bean
    @DependsOn("springLiquibase")
    public MultiTenantSpringLiquibase multiTenantSpringLiquibase(SpringLiquibase springLiquibase) {
        List<Object> schemas = new ArrayList<>(dataSourceProperties.getDataSources().keySet());
        final List<String> schemaNames = new ArrayList<>();
        schemas.forEach(o -> schemaNames.add(o.toString()));
        MultiTenantSpringLiquibase liquibase = new MultiTenantSpringLiquibase();
        liquibase.setDataSource(springLiquibase.getDataSource());
        liquibase.setChangeLog(springLiquibase.getChangeLog());
        liquibase.setDefaultSchema(springLiquibase.getDefaultSchema());
        liquibase.setSchemas(schemaNames);
        liquibase.setShouldRun(liquibaseProperties.isEnabled());
        return liquibase;
    }
}
