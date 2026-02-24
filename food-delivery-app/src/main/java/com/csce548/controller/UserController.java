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

import com.csce548.model.User;
import com.csce548.service.UserService;

@RestController
@RequestMapping("/users")
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
