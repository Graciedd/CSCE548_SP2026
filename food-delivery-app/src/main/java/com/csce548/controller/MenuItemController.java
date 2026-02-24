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

@RestController
@RequestMapping("/menu")
public class MenuItemController {

    private MenuItemService service = new MenuItemService();

    @PostMapping
    public String create(@RequestBody MenuItem item) throws Exception {
        service.create(item);
        return "Menu item created";
    }

    @GetMapping("/restaurant/{id}")
    public List<MenuItem> getByRestaurant(@PathVariable int id) throws Exception {
        return service.getByRestaurant(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody MenuItem item) throws Exception {
        item.setMenuItemId(id);
        service.update(item);
        return "Menu item updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) throws Exception {
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
