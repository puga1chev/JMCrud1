package jmCrud.service;

import jmCrud.dao.*;
import jmCrud.model.User;
import java.sql.SQLException;
import java.util.*;

public class UserServiceJdbc implements UserServiceJdbcImpl {

    private UsersDaoJdbcImpl daoJdbc = new UsersDaoJdbc();

    @Override
    public List<User> getAll() throws SQLException {
        return daoJdbc.getAll();
    }

    @Override
    public User getById(Long user_id) throws SQLException {
        return daoJdbc.getById(user_id);
    }

    @Override
    public void add(User user) throws SQLException {
        daoJdbc.insert(user);
    }

    @Override
    public void edit(User user) throws SQLException {
        daoJdbc.update(user);
    }

    @Override
    public void remove(Long id) throws SQLException {
        daoJdbc.delete(id);
    }
}
