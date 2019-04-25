package jmCrud.service;

import jmCrud.exception.DBException;
import jmCrud.dao.UsersDaoOrm;
import jmCrud.model.*;

import java.util.List;

public class UserServiceOrm implements UserServiceDB {

    private UsersDaoOrm usersDaoOrm = new UsersDaoOrm();

    @Override
    public void add(User user) throws DBException {
        usersDaoOrm.insert(user);
    }

    @Override
    public void edit(User user) throws DBException {
        usersDaoOrm.update(user);
    }

    @Override
    public void remove(Long id) throws DBException {
        usersDaoOrm.delete(id);
    }

    @Override
    public User getById(Long user_id) throws DBException {
        return usersDaoOrm.getById(user_id);
    }

    @Override
    public List<User> getAll() throws DBException {
        return usersDaoOrm.getAll();
    }
}
