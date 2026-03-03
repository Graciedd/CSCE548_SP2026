package edu.csce548.fooddelivery.business;

import java.util.List;

import com.csce548.dao.OrderDAO;
import com.csce548.model.Order;

public class OrderService {

    private final OrderDAO dao = new OrderDAO();

    public void create(Order order) throws Exception {
        dao.create(order);
    }

    public List<Order> getByUser(int userId) throws Exception {
        return dao.getByUser(userId);
    }

    public void updateStatus(int orderId, String status) throws Exception {
        dao.updateStatus(orderId, status);
    }

    public void delete(int id) throws Exception {
        dao.delete(id);
    }
}

