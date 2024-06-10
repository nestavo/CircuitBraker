package com.Circuit.servicio;

import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class MyService {

    @CircuitBreaker(name = "myCircuitBreaker")
    public void myMethod() {
        // Lógica método protegido por el Circuit Breaker
    }
}

