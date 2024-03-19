package com.wakanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@SpringBootApplication
public class HelpheDeskApplication {

	@GetMapping
	public String getHome() {
		return "Helphe Desk - API Home";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HelpheDeskApplication.class, args);
	}

}
