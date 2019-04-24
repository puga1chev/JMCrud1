package jmCrud.service;

import jmCrud.model.User;
import org.hibernate.HibernateException;
import java.util.*;

public interface UserServiceOrmImpl {

    public void add(User user) throws HibernateException;
    public void edit(User user) throws HibernateException;
    public void remove(Long id) throws HibernateException;
    public User getById(Long user_id) throws HibernateException;
    public List<User> getAll() throws HibernateException;
}
