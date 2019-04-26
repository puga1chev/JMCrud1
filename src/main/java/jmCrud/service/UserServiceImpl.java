package jmCrud.service;

import jmCrud.dao.*;
import jmCrud.model.User;
import java.util.*;

public class UserServiceImpl implements UserService {

    private UsersDao dao = new UserDaoFactory().createDaoImpl();

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public User getById(Long user_id) {
        return dao.getById(user_id);
    }

    @Override
    public void add(User user) {
        dao.insert(user);
    }

    @Override
    public void edit(User user) {
        dao.update(user);
    }

    @Override
    public void remove(Long id) {
        dao.delete(id);
    }
}
