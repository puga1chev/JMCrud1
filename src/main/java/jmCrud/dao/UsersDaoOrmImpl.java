package jmCrud.dao;

import jmCrud.model.User;
import org.hibernate.HibernateException;
import java.util.*;

public interface UsersDaoOrmImpl {

    public void insert(User user) throws HibernateException;
    public void update(User user) throws HibernateException;
    public void delete(Long user_id) throws HibernateException;
    public User getById(Long user_id) throws HibernateException;
    public List<User> getAll() throws HibernateException;

}
