package com.csce548.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.csce548.config.DBConnection;
import com.csce548.model.MenuItem;

public class MenuItemDAO {

    public void create(MenuItem item) throws Exception {
        String sql = "INSERT INTO menu_items (restaurant_id, name, price) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, item.getRestaurantId());
            ps.setString(2, item.getName());
            ps.setBigDecimal(3, item.getPrice());
            ps.executeUpdate();
        }
    }

    public List<MenuItem> getAll() throws Exception {
    List<MenuItem> items = new ArrayList<>();
    String sql = "SELECT * FROM menu_items";
    try (Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            items.add(new MenuItem(
                rs.getInt("menu_item_id"),
                rs.getInt("restaurant_id"),
                rs.getString("name"),
                rs.getBigDecimal("price")
            ));
        }
    }
    return items;
}

    public List<MenuItem> getByRestaurant(int restaurantId) throws Exception {
        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items WHERE restaurant_id=?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                items.add(new MenuItem(
                        rs.getInt("menu_item_id"),
                        rs.getInt("restaurant_id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price")
                ));
            }
        }
        return items;
    }

     public MenuItem getById(int id) throws Exception {
        MenuItem item = null;
        String sql = "SELECT * FROM menu_items WHERE menu_item_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = new MenuItem(
                        rs.getInt("menu_item_id"),
                        rs.getInt("restaurant_id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price")
                );
            }
        }
        return item;
    }

    public void update(MenuItem item) throws Exception {
        String sql = "UPDATE menu_items SET name=?, price=? WHERE menu_item_id=?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setBigDecimal(2, item.getPrice());
            ps.setInt(3, item.getMenuItemId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM menu_items WHERE menu_item_id=?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}