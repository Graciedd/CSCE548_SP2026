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

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService service = new OrderService();

    @PostMapping
    public String create(@RequestBody Order order) throws Exception {
        service.create(order);
        return "Order created";
    }

    @GetMapping("/user/{id}")
    public List<Order> getByUser(@PathVariable int id) throws Exception {
        return service.getByUser(id);
    }

    @PutMapping("/{id}/status")
    public String updateStatus(@PathVariable int id, @RequestBody String status) throws Exception {
        service.updateStatus(id, status);
        return "Order status updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) throws Exception {
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
