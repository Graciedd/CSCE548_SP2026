package com.csce548.service;

import java.util.List;

import com.csce548.dao.RestaurantDAO;
import com.csce548.model.Restaurant;

public class RestaurantService {

    private RestaurantDAO dao = new RestaurantDAO();

    public void create(Restaurant r) throws Exception {
        dao.create(r);
    }
 

    public List<Restaurant> getAll() throws Exception {
        return dao.getAll();
    }

     public Restaurant getById(int id) throws Exception {
        return dao.getById(id);
    }

    public void update(Restaurant r) throws Exception {
        dao.update(r);
    }

    public void delete(int id) throws Exception {
        dao.delete(id);
    }
}
