package com.Falloc.servicio;


import org.springframework.stereotype.Service;

@Service
public class FailingService {

    private int counter = 0;

    public String mightFail() {
        counter++;
        if (counter % 2 == 0) {
            throw new RuntimeException("Intentional Failure");
        }
        return "Success";
    }
}
