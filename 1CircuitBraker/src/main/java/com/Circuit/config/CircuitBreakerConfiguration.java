package com.Circuit.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

@Configuration
public class CircuitBreakerConfiguration {

    @Value("${resilience4j.circuitbreaker.configs.default.slidingWindowSize}")
    private int slidingWindowSize;

    @Value("${resilience4j.circuitbreaker.configs.default.minimumNumberOfCalls}")
    private int minimumNumberOfCalls;

    @Value("${resilience4j.circuitbreaker.configs.default.permittedNumberOfCallsInHalfOpenState}")
    private int permittedNumberOfCallsInHalfOpenState;

    @Value("${resilience4j.circuitbreaker.configs.default.waitDurationInOpenState}")
    private String waitDurationInOpenState;

    @Value("${resilience4j.circuitbreaker.configs.default.failureRateThreshold}")
    private float failureRateThreshold;

    @Value("${resilience4j.circuitbreaker.configs.default.eventConsumerBufferSize}")
    private int eventConsumerBufferSize;

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
            .slidingWindowSize(slidingWindowSize)
            .minimumNumberOfCalls(minimumNumberOfCalls)
            .permittedNumberOfCallsInHalfOpenState(permittedNumberOfCallsInHalfOpenState)
            .waitDurationInOpenState(Duration.ofSeconds(Long.parseLong(waitDurationInOpenState.substring(0, waitDurationInOpenState.length() - 1))))
            .failureRateThreshold(failureRateThreshold)
            //.eventConsumerBufferSize(eventConsumerBufferSize)
            .build();

        return CircuitBreakerRegistry.of(circuitBreakerConfig);
    }
}


