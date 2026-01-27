package com.ibet.userservice.security.config;

import java.time.Duration;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    private List<String> allowedOrigins = List.of("http://localhost:3000");
    private List<String> publicEndpoints = List.of(
        "/api/v1/auth/**",
        "/api/v1/public/**",
        "/actuator/health",
        "/swagger-ui/**",
        "/v3/api-docs/**"
    );
    private int maxLoginAttempts = 5;
    private Duration accountLockDuration = Duration.ofMinutes(30);
    private boolean requireSecureCookies = false;
}
