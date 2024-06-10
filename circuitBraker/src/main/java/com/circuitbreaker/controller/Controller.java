package com.circuitbreaker.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.circuitbreaker.service.Servicio;



@RestController
@RequestMapping("/sample")
public class Controller {

	public final Servicio sampleService;
	
	public Controller(Servicio sampleService) {
		
		this.sampleService = sampleService;
	}
	
	@GetMapping
	public int testCall() {
		return this.sampleService.runApiPostCall();
	}
	
}