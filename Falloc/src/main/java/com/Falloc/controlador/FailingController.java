package com.Falloc.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Falloc.servicio.FailingService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api")
public class FailingController {

    @Autowired
    private FailingService failingService;

    @CircuitBreaker(name = "failingService", fallbackMethod = "fallback")
    @GetMapping("/test")
    public String testService() {
        return failingService.mightFail();
    }

    public String fallback(Throwable t) {
        return "Fallback response: " + t.getMessage();
    }
}
