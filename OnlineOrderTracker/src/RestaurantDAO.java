import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
