package edu.csce548.fooddelivery.business;
import fooddelivery.*;
import dao.RestaurantDAO;
import model.Restaurant;
import java.util.List;

public class RestaurantService {

    private final RestaurantDAO dao = new RestaurantDAO();

    public void create(Restaurant r) throws Exception {
        dao.create(r);
    }

    public List<Restaurant> getAll() throws Exception {
        return dao.getAll();
    }

    public void update(Restaurant r) throws Exception {
        dao.update(r);
    }

    public void delete(int id) throws Exception {
        dao.delete(id);
    }
}

