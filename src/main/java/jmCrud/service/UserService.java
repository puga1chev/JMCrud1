package jmCrud.service;

import jmCrud.exception.DBException;
import jmCrud.model.User;
import java.util.List;

public interface UserService {

    public void add(User user);
    public void edit(User user);
    public void remove(Long id);
    public User getById(Long user_id);
    public List<User> getAll();
}
