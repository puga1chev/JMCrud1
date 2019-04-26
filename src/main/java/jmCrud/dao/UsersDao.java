package jmCrud.dao;

import jmCrud.model.User;
import java.util.List;

public interface UsersDao {

    public void insert(User user);
    public void update(User user);
    public void delete(Long user_id);
    public User getById(Long user_id);
    public List<User> getAll();

}
