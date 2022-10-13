package com.example.multitenantliquibase.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataSourceRouting extends AbstractRoutingDataSource {
    private final DataSourceProperties dataSourceProperties;
    private final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public DataSourceRouting(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
        super.setTargetDataSources(dataSourceProperties.getDataSources());
        super.setDefaultTargetDataSource(dataSourceProperties.getDataSources().get(dataSourceProperties.getDefaultSchema()));
    }

    public void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String currentLookupKey = currentTenant.get();
        if (currentLookupKey == null) {
            currentLookupKey = dataSourceProperties.getDefaultSchema();
        }
        log.info("Database in scope for current request : {} ", currentLookupKey);
        return currentLookupKey;
    }

    public void clear() {
        currentTenant.remove();
    }

}
