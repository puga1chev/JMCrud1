package jmCrud.service;

import jmCrud.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserServiceJdbcImpl {

    public void add(User user) throws SQLException;
    public void edit(User user) throws SQLException;
    public void remove(String id) throws SQLException;
    public User getById(String user_id) throws SQLException;
    public List<User> getAll() throws SQLException;
}
