package com.example.multitenantliquibase.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class TenantRequestInterceptor extends OncePerRequestFilter {
    private final DataSourceRouting multiDataSourceRouting;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String tenantHeader = request.getHeader("X-Tenant");
            log.info("Tenant in  request header {}", tenantHeader);
            multiDataSourceRouting.setCurrentTenant(tenantHeader);
            filterChain.doFilter(request, response);
        } finally {
            multiDataSourceRouting.clear();
        }
    }
}
