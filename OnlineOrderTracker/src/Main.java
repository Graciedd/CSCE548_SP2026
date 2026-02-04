import java.math.BigDecimal;
import java.util.List;

public class Main {

    private static final UserDAO userDAO = new UserDAO();
    private static final RestaurantDAO restaurantDAO = new RestaurantDAO();
    private static final MenuItemDAO menuItemDAO = new MenuItemDAO();
    private static final OrderDAO orderDAO = new OrderDAO();

    public static void main(String[] args) {
        System.out.println("=== Automated Full CRUD Demo (DAO-Safe) ===");

        try {
            // -------------------- USERS --------------------
            System.out.println("\n--- Users ---");
            viewUsers();

            // Create a new user safely (skips if email exists)
            addUser("Alice Johnson", "alice@example.com", "123 Main St");

            // Update user info (assume ID 1 exists)
            updateUser(1, "Alice Smith", "alice.smith@example.com", "456 Elm St");

            // Delete user safely (assume ID 2 exists)
            deleteUser(2);

            viewUsers();

            // -------------------- RESTAURANTS --------------------
            System.out.println("\n--- Restaurants ---");

            // Create a new restaurant
            addRestaurant("Pasta Place", "789 Oak Ave");

            // Update restaurant info (assume ID 1 exists)
            updateRestaurant(1, "Pizza Palace", "321 Maple Rd");

            // NOTE: Cannot safely delete restaurants with orders yet

            viewRestaurants();

            // -------------------- MENU ITEMS --------------------
            System.out.println("\n--- Menu Items ---");

            // Create a new menu item for restaurant ID 1
            addMenuItem(1, "Spaghetti", new BigDecimal("12.50"));

            // Update menu item info (assume menu item ID 1 exists)
            updateMenuItem(1, "Fettuccine Alfredo", new BigDecimal("14.00"));

            viewMenuItems(1);

            // -------------------- ORDERS --------------------
            System.out.println("\n--- Orders ---");

            // Create an order for user 1, restaurant 1
            placeOrder(1, 1, "Pending", new BigDecimal("25.50"));

            // Update order status (assume order ID 1 exists)
            updateOrderStatus(1, "Delivered");

            // Delete an order (assume order ID 2 exists)
            deleteOrder(2);

            viewOrders(1);

            System.out.println("\n=== Full CRUD Demo Complete ===");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------- USERS --------------------
    private static void viewUsers() throws Exception {
        List<User> users = userDAO.getAll();
        System.out.println("--- Current Users ---");
        for (User u : users) {
            System.out.println(u.getUserId() + " | " + u.getName() + " | " + u.getEmail());
        }
    }

    // Updated addUser() with email check
    private static void addUser(String name, String email, String address) throws Exception {
        List<User> users = userDAO.getAll();
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                System.out.println("User with email " + email + " already exists. Skipping creation.");
                return;
            }
        }

        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setAddress(address);
        userDAO.create(u);
        System.out.println("Added user: " + name);
    }

    private static void updateUser(int userId, String name, String email, String address) throws Exception {
        User u = new User();
        u.setUserId(userId);
        u.setName(name);
        u.setEmail(email);
        u.setAddress(address);
        userDAO.update(u);
        System.out.println("Updated user ID " + userId);
    }

    private static void deleteUser(int userId) throws Exception {
        // Delete all orders for this user first
        List<Order> orders = orderDAO.getByUser(userId);
        for (Order o : orders) {
            orderDAO.delete(o.getOrderId());
            System.out.println("Deleted order ID " + o.getOrderId() + " for user ID " + userId);
        }

        // Then delete the user
        userDAO.delete(userId);
        System.out.println("Deleted user ID " + userId);
    }

    // -------------------- RESTAURANTS --------------------
    private static void viewRestaurants() throws Exception {
        List<Restaurant> restaurants = restaurantDAO.getAll();
        System.out.println("--- Current Restaurants ---");
        for (Restaurant r : restaurants) {
            System.out.println(r.getRestaurantId() + " | " + r.getName() + " | " + r.getAddress());
        }
    }

    private static void addRestaurant(String name, String address) throws Exception {
        Restaurant r = new Restaurant();
        r.setName(name);
        r.setAddress(address);
        restaurantDAO.create(r);
        System.out.println("Added restaurant: " + name);
    }

    private static void updateRestaurant(int restaurantId, String name, String address) throws Exception {
        Restaurant r = new Restaurant();
        r.setRestaurantId(restaurantId);
        r.setName(name);
        r.setAddress(address);
        restaurantDAO.update(r);
        System.out.println("Updated restaurant ID " + restaurantId);
    }

    // -------------------- MENU ITEMS --------------------
    private static void viewMenuItems(int restaurantId) throws Exception {
        List<MenuItem> items = menuItemDAO.getByRestaurant(restaurantId);
        System.out.println("--- Menu Items for Restaurant ID " + restaurantId + " ---");
        for (MenuItem item : items) {
            System.out.println(item.getMenuItemId() + " | " + item.getName() + " | $" + item.getPrice());
        }
    }

    private static void addMenuItem(int restaurantId, String name, BigDecimal price) throws Exception {
        MenuItem item = new MenuItem();
        item.setRestaurantId(restaurantId);
        item.setName(name);
        item.setPrice(price);
        menuItemDAO.create(item);
        System.out.println("Added menu item: " + name);
    }

    private static void updateMenuItem(int menuItemId, String name, BigDecimal price) throws Exception {
        MenuItem item = new MenuItem();
        item.setMenuItemId(menuItemId);
        item.setName(name);
        item.setPrice(price);
        menuItemDAO.update(item);
        System.out.println("Updated menu item ID " + menuItemId);
    }

    // -------------------- ORDERS --------------------
    private static void viewOrders(int userId) throws Exception {
        List<Order> orders = orderDAO.getByUser(userId);
        System.out.println("--- Orders for User ID " + userId + " ---");
        for (Order o : orders) {
            System.out.println("Order #" + o.getOrderId() +
                    " | Restaurant ID: " + o.getRestaurantId() +
                    " | Status: " + o.getStatus() +
                    " | Total: $" + o.getTotalAmount());
        }
    }

    private static void placeOrder(int userId, int restaurantId, String status, BigDecimal totalAmount) throws Exception {
        Order order = new Order();
        order.setUserId(userId);
        order.setRestaurantId(restaurantId);
        order.setStatus(status);
        order.setTotalAmount(totalAmount);
        orderDAO.create(order);
        System.out.println("Placed order for user ID " + userId);
    }

    private static void updateOrderStatus(int orderId, String status) throws Exception {
        orderDAO.updateStatus(orderId, status);
        System.out.println("Updated order ID " + orderId + " to status: " + status);
    }

    private static void deleteOrder(int orderId) throws Exception {
        orderDAO.delete(orderId);
        System.out.println("Deleted order ID " + orderId);
    }
}









