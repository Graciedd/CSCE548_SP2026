package com.csce548.service;

import java.util.List;

import com.csce548.dao.UserDAO;
import com.csce548.model.User;

public class UserService {

    private UserDAO dao = new UserDAO();

    public void createUser(User user) throws Exception {
        dao.create(user);
    }

    public User getUser(int id) throws Exception {
        return dao.getById(id);
    }

    public List<User> getAllUsers() throws Exception {
        return dao.getAll();
    }

    public void updateUser(User user) throws Exception {
        dao.update(user);
    }

    public void deleteUser(int id) throws Exception {
        dao.delete(id);
    }
}
