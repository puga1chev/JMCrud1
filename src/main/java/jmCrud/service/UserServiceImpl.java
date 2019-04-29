package jmCrud.service;

import jmCrud.dao.*;
import jmCrud.model.User;
import java.util.*;

public class UserServiceImpl implements ObjectService<User> {

    private DaoAbstractFactory factory = DaoAbstractFactory.getFactory();
    private BaseDaoOperations<User> dao = factory.getUsersDao();

    @Override
    public List<User> getAll(String orderByField) {
        return dao.getAll(orderByField);
    }

    @Override
    public void insert(User obj) {
        dao.insert(obj);
    }

    @Override
    public void update(User obj) {
        dao.update(obj);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public User getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public User getByField(String fieldName, String value) {
        return dao.getByField(fieldName, value);
    }
}
