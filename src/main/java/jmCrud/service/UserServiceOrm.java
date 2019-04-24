package jmCrud.service;

import jmCrud.dao.UsersDaoOrm;
import jmCrud.model.*;
import org.hibernate.*;

import java.util.List;

public class UserServiceOrm implements UserServiceOrmImpl {

    private UsersDaoOrm usersDaoOrm = new UsersDaoOrm();

    @Override
    public void add(User user) throws HibernateException {
        usersDaoOrm.insert(user);
    }

    @Override
    public void edit(User user) throws HibernateException {
        usersDaoOrm.update(user);
    }

    @Override
    public void remove(Long id) throws HibernateException {
        usersDaoOrm.delete(id);
    }

    @Override
    public User getById(Long user_id) throws HibernateException {
        return usersDaoOrm.getById(user_id);
    }

    @Override
    public List<User> getAll() throws HibernateException {
        return usersDaoOrm.getAll();
    }
}
