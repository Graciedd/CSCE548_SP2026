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

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private RestaurantService service = new RestaurantService();

    @PostMapping
    public String create(@RequestBody Restaurant r) throws Exception {
        service.create(r);
        return "Restaurant created";
    }

    @GetMapping
    public List<Restaurant> getAll() throws Exception {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Restaurant r) throws Exception {
        r.setRestaurantId(id);
        service.update(r);
        return "Restaurant updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) throws Exception {
        service.delete(id);
        return "Restaurant deleted";
    }
}
