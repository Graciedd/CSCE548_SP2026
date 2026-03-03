package com.csce548.service;

import java.util.List;

import com.csce548.dao.OrderDAO;
import com.csce548.model.Order;

public class OrderService {

    private OrderDAO dao = new OrderDAO();

    public void create(Order order) throws Exception {
        dao.create(order);
    }

    public List<Order> getByUser(int userId) throws Exception {
        return dao.getByUser(userId);
    }

    public Order getById(int id) throws Exception {
        return dao.getById(id); // assumes your DAO has a getById method
}

    public void updateStatus(int orderId, String status) throws Exception {
        dao.updateStatus(orderId, status);
    }

    public void delete(int id) throws Exception {
        dao.delete(id);
    }

      public List<Order> getAll() throws Exception {
        return dao.getAll();
    }
}
