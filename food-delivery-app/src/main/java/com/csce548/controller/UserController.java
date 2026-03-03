package com.csce548.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csce548.model.User;
import com.csce548.service.UserService;

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
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private UserService service = new UserService();

    @PostMapping
    public String create(@RequestBody User user) throws Exception {
        // Hosting: This endpoint listens for POST requests at:
        // http://localhost:8080/users
        // Can be called from a console client, Postman, or frontend app.
        service.createUser(user);
        return "User created";
    }

    @GetMapping
    public List<User> getAll() throws Exception {
         // Endpoint: GET http://localhost:8080/users
        return service.getAllUsers();
    }

   /*  @GetMapping("/{id}")
    public User getById(@PathVariable int id) throws Exception {
        return service.getUser(id);
    } */

    @GetMapping("/{id}")
    public User get(@PathVariable int id) throws Exception {
        // Endpoint: GET http://localhost:8080/users/{id}
        return service.getUser(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody User user) throws Exception {
        // Endpoint: PUT http://localhost:8080/users/{id}
        // Updates user info in the database
        user.setUserId(id);
        service.updateUser(user);
        return "User updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) throws Exception {
        // Endpoint: DELETE http://localhost:8080/users/{id}
        // Deletes the user from database
        service.deleteUser(id);
        return "User deleted";
    }
}
