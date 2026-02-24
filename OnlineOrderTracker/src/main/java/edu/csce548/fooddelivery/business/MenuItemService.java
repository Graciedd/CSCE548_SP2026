package edu.csce548.fooddelivery.business;

import edu.csce548.fooddelivery.dao.MenuItemDAO;
import edu.csce548.fooddelivery.model.MenuItem;
import java.util.List;

public class MenuItemService {

    private final MenuItemDAO dao = new MenuItemDAO();

    public void create(MenuItem item) throws Exception {
        dao.create(item);
    }

    public List<MenuItem> getByRestaurant(int restaurantId) throws Exception {
        return dao.getByRestaurant(restaurantId);
    }

    public void update(MenuItem item) throws Exception {
        dao.update(item);
    }

    public void delete(int id) throws Exception {
        dao.delete(id);
    }
}

