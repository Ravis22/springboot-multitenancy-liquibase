package com.example.multitenantliquibase.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "tenants")
public class DataSourceProperties {
    private final Map<Object, Object> dataSources = new LinkedHashMap<>();
    private String defaultSchema;

    public Map<Object, Object> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Map<String, Map<String, String>> dataSources) {
        dataSources
                .forEach((key, value) -> this.dataSources.put(key, convert(value)));
    }

    public DataSource convert(Map<String, String> source) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(source.get("url"));
        config.setDriverClassName(source.get("driverClassName"));
        config.setUsername(source.get("username"));
        config.setPassword(source.get("password"));
        config.setMaximumPoolSize(Integer.parseInt(source.get("maximum-pool-size")));
        config.setPoolName(source.get("pool-name"));

        return new HikariDataSource(config);
    }

    public String getDefaultSchema() {
        return defaultSchema;
    }

    public void setDefaultSchema(String defaultSchema) {
        this.defaultSchema = defaultSchema;
    }
}
