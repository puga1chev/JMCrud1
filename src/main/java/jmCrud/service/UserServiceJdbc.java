package jmCrud.service;

import jmCrud.dao.*;
import jmCrud.exception.DBException;
import jmCrud.model.User;
import java.util.*;

public class UserServiceJdbc implements UserServiceDB {

    private UsersDaoDB daoJdbc = new UsersDaoJdbc();

    @Override
    public List<User> getAll() throws DBException {
        return daoJdbc.getAll();
    }

    @Override
    public User getById(Long user_id) throws DBException {
        return daoJdbc.getById(user_id);
    }

    @Override
    public void add(User user) throws DBException {
        daoJdbc.insert(user);
    }

    @Override
    public void edit(User user) throws DBException {
        daoJdbc.update(user);
    }

    @Override
    public void remove(Long id) throws DBException {
        daoJdbc.delete(id);
    }
}
