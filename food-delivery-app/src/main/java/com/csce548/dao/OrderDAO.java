package com.csce548.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.csce548.config.DBConnection;
import com.csce548.model.Order;

public class OrderDAO {

    public void create(Order order) throws Exception {
        String sql = """
            INSERT INTO orders (user_id, restaurant_id, status, total_amount)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRestaurantId());
            ps.setString(3, order.getStatus());
            ps.setBigDecimal(4, order.getTotalAmount());
            ps.executeUpdate();
        }
    }

    public List<Order> getByUser(int userId) throws Exception {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id=?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getInt("restaurant_id"),
                        rs.getTimestamp("order_date"),
                        rs.getString("status"),
                        rs.getBigDecimal("total_amount")
                ));
            }
        }
        return orders;
    }

    public List<Order> getAll() throws Exception {
    List<Order> orders = new ArrayList<>();
    String sql = "SELECT * FROM orders";
    try (Connection conn = DBConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            orders.add(new Order(
                rs.getInt("order_id"),
                rs.getInt("user_id"),
                rs.getInt("restaurant_id"),
                rs.getTimestamp("order_date"),
                rs.getString("status"),
                rs.getBigDecimal("total_amount")
            ));
        }
    }
    return orders;
}

    public Order getById(int id) throws Exception {
        Order order = null;
        String sql = "SELECT * FROM orders WHERE order_id=?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getInt("restaurant_id"),
                        rs.getTimestamp("order_date"),
                        rs.getString("status"),
                        rs.getBigDecimal("total_amount")
                );
            }
        }
        return order;
    }

    public void updateStatus(int orderId, String status) throws Exception {
        String sql = "UPDATE orders SET status=? WHERE order_id=?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM orders WHERE order_id=?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
