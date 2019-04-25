package jmCrud.dao;

import jmCrud.exception.DBException;
import jmCrud.model.User;

import java.util.List;

public interface UsersDaoDB {

    public void insert(User user) throws DBException;
    public void update(User user) throws DBException;
    public void delete(Long user_id) throws DBException;
    public User getById(Long user_id) throws DBException;
    public List<User> getAll() throws DBException;

}
