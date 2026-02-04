public class Restaurant {

    private int restaurantId;
    private String name;
    private String address;
    private boolean isActive;

    public Restaurant() {
    }

    public Restaurant(int restaurantId, String name, String address, boolean isActive) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.isActive = isActive;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
