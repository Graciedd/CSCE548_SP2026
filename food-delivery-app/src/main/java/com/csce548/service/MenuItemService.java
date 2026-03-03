package com.csce548.service;

import java.util.List;

import com.csce548.dao.MenuItemDAO;
import com.csce548.model.MenuItem;

public class MenuItemService {

    private MenuItemDAO dao = new MenuItemDAO();

    public void create(MenuItem item) throws Exception {
        dao.create(item);
    }

    public List<MenuItem> getByRestaurant(int restaurantId) throws Exception {
        return dao.getByRestaurant(restaurantId);
    }

    public MenuItem getById(int id) throws Exception {
    return dao.getById(id); // assumes your DAO has a getById method
    }

    public void update(MenuItem item) throws Exception {
        dao.update(item);
    }

    public void delete(int id) throws Exception {
        dao.delete(id);
    }

    public List<MenuItem> getAll() throws Exception {
        // You can extend DAO to have a getAll() if needed
        return dao.getAll();
    }
}
