package com.csce548.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.csce548.config.DBConnection;
import com.csce548.model.Restaurant;

public class RestaurantDAO {

    public void create(Restaurant r) throws Exception {
        String sql = "INSERT INTO restaurants (name, address, is_active) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getName());
            ps.setString(2, r.getAddress());
            ps.setBoolean(3, r.isActive());
            ps.executeUpdate();
        }
    }

    public List<Restaurant> getAll() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM restaurants";

        try (Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                restaurants.add(new Restaurant(
                        rs.getInt("restaurant_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getBoolean("is_active")
                ));
            }
        }
        return restaurants;
    }

    public Restaurant getById(int id) throws Exception {
        String sql = "SELECT * FROM restaurants WHERE restaurant_id = ?";
        Restaurant r = null;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                r = new Restaurant();
                r.setRestaurantId(rs.getInt("restaurant_id"));
                r.setName(rs.getString("name"));
                r.setAddress(rs.getString("address"));
                r.setActive(rs.getBoolean("is_active"));
            }
        }

        return r;
    }

    public void update(Restaurant r) throws Exception {
        String sql = "UPDATE restaurants SET name=?, address=?, is_active=? WHERE restaurant_id=?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getName());
            ps.setString(2, r.getAddress());
            ps.setBoolean(3, r.isActive());
            ps.setInt(4, r.getRestaurantId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM restaurants WHERE restaurant_id=?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}