package jmCrud.dao;

import jmCrud.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersDaoJdbcImpl {

    public void insert(User user) throws SQLException;
    public void update(User user) throws SQLException;
    public void delete(Long user_id) throws SQLException;
    public User getById(Long user_id) throws SQLException;
    public List<User> getAll() throws SQLException;

}
