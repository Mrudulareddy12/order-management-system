package org.example.service;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;

@Configuration
public class ResilienceConfig {
    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
            .maxAttempts(1)
            .waitDuration(Duration.ofMillis(500))
            .build();

        return RetryRegistry.of(config);
    }
}
