import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
