package com.csce548.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csce548.model.Restaurant;
import com.csce548.service.RestaurantService;

/**
 * Service Layer for Food Delivery Application
 *
 * Hosting Context:
 * This service is managed by the Spring Boot application and runs as part
 * of the backend service hosted locally on the embedded Tomcat server.
 *
 * Platform:
 * - Spring Boot (Java)
 * - Hosted locally at: http://localhost:8080
 *
 * How the Service is Used:
 * - Controller layer receives HTTP requests.
 * - Controller calls methods in this Service class.
 * - Service contains business logic and calls the DAO layer.
 * - DAO layer performs CRUD operations on the MySQL database.
 *
 * Application Startup:
 * To start the hosted service:
 *   mvn spring-boot:run
 *   OR run FoodDeliveryAppApplication.java
 *
 * This service is automatically available once the Spring Boot application
 * is running.
 *
 */

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private RestaurantService service = new RestaurantService();

    @PostMapping
    public String create(@RequestBody Restaurant r) throws Exception {
        // Hosting: This endpoint listens for POST requests at:
        // http://localhost:8080/restaurants
        // Can be called from a console client, Postman, or frontend app.
        service.create(r);
        return "Restaurant created";
    }

    @GetMapping
    public List<Restaurant> getAll() throws Exception {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Restaurant r) throws Exception {
        // Endpoint: PUT http://localhost:8080/restaurants/{id}
        // Updates restaurant info in the database
        r.setRestaurantId(id);
        service.update(r);
        return "Restaurant updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) throws Exception {
        // Endpoint: DELETE http://localhost:8080/restaurants/{id}
        // Deletes the restaurant from database
        service.delete(id);
        return "Restaurant deleted";
    }
}
