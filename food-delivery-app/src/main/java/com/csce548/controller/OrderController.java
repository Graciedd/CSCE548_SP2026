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

import com.csce548.model.Order;
import com.csce548.service.OrderService;

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
@RequestMapping("/orders")
public class OrderController {

    private OrderService service = new OrderService();

    @PostMapping
    public String create(@RequestBody Order order) throws Exception {
        // Hosting: This endpoint listens for POST requests at:
        // http://localhost:8080/orders
        // Can be called from a console client, Postman, or frontend app.
        service.create(order);
        return "Order created";
    }

    @GetMapping("/user/{id}")
    public List<Order> getByUser(@PathVariable int id) throws Exception {
        return service.getByUser(id);
    }

    @PutMapping("/{id}/status")
    public String updateStatus(@PathVariable int id, @RequestBody String status) throws Exception {
        // Endpoint: PUT http://localhost:8080/orders/{id}
        // Updates order info in the database
        service.updateStatus(id, status);
        return "Order status updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) throws Exception {
        // Endpoint: DELETE http://localhost:8080/orders/{id}
        // Deletes the order from database
        service.delete(id);
        return "Order deleted";
    }

    @GetMapping
    public List<Order> getAll() throws Exception {
        // Endpoint: GET http://localhost:8080/orders
        // Returns all orders across all users
        return service.getAll();
    }
}
