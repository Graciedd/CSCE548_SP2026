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

import com.csce548.model.MenuItem;
import com.csce548.service.MenuItemService;
 
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
@RequestMapping("/menu")
public class MenuItemController {

    private MenuItemService service = new MenuItemService();

    @PostMapping
    public String create(@RequestBody MenuItem item) throws Exception {
        // Hosting: This endpoint listens for POST requests at:
        // http://localhost:8080/menu
        // Can be called from a console client, Postman, or frontend app.
        service.create(item);
        return "Menu item created";
    }

    @GetMapping("/restaurant/{id}")
    public List<MenuItem> getByRestaurant(@PathVariable int id) throws Exception {
        return service.getByRestaurant(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody MenuItem item) throws Exception {
        // Endpoint: PUT http://localhost:8080/menu/{id}
        // Updates menu item info in the database
        item.setMenuItemId(id);
        service.update(item);
        return "Menu item updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) throws Exception {
        // Endpoint: DELETE http://localhost:8080/menu/{id}
        // Deletes the menu item from database
        service.delete(id);
        return "Menu item deleted";
    }

    @GetMapping
    public List<MenuItem> getAll() throws Exception {
        // Endpoint: GET http://localhost:8080/menu
        // Returns all menu items across all restaurants
        return service.getAll();
    }
}
