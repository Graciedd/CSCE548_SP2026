package com.csce548;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Run with:
mvn spring-boot:run

Service URL:
http://localhost:8080
*/
@SpringBootApplication
public class FoodDeliveryApplication {
    public static void main(String[] args) {
          // SpringApplication.run() starts the embedded Tomcat server
        // This hosts all REST controllers at localhost:8080 by default
        SpringApplication.run(FoodDeliveryApplication.class, args);
    }
}
