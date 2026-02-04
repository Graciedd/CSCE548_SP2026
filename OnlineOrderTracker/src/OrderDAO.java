import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
