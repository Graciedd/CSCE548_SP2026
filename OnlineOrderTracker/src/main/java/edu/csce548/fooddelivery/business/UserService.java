package edu.csce548.fooddelivery.business;


import java.util.List;

import com.csce548.dao.UserDAO;
import com.csce548.model.User;

public class UserService {

    private final UserDAO dao = new UserDAO();

    public void create(User user) throws Exception {
        dao.create(user);
    }

    public User getById(int id) throws Exception {
        return dao.getById(id);
    }

    public List<User> getAll() throws Exception {
        return dao.getAll();
    }

    public void update(User user) throws Exception {
        dao.update(user);
    }

    public void delete(int id) throws Exception {
        dao.delete(id);
    }
}

