package jmCrud.service;

import jmCrud.dao.UsersDaoJdbc;
import jmCrud.model.User;
import java.sql.SQLException;
import java.util.*;

public class UserServiceJdbc {

    private UsersDaoJdbc daoJdbc = new UsersDaoJdbc();

    public List<User> getAll() throws SQLException {
        return daoJdbc.getAll();
    }

    public User getById(String user_id) throws SQLException {
        return daoJdbc.getById(user_id);
    }

    public void add(User user) throws SQLException {
        daoJdbc.insert(user);
    }

    public void edit(User user) throws SQLException {
        daoJdbc.update(user);
    }

    public void remove(String id) throws SQLException {
        daoJdbc.delete(id);
    }
}
