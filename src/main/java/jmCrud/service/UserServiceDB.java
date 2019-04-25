package jmCrud.service;

import jmCrud.exception.DBException;
import jmCrud.model.User;
import java.util.List;

public interface UserServiceDB {

    public void add(User user) throws DBException;
    public void edit(User user) throws DBException;
    public void remove(Long id) throws DBException;
    public User getById(Long user_id) throws DBException;
    public List<User> getAll() throws DBException;
}
