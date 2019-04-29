package jmCrud.service;

import jmCrud.dao.*;
import jmCrud.model.*;
import java.util.*;

public class RoleServiceImpl implements ObjectService<Role> {

    private DaoAbstractFactory factory = DaoAbstractFactory.getFactory();
    private BaseDaoOperations<Role> dao = factory.getRoleDao();

    @Override
    public List<Role> getAll(String orderByField) {
        return dao.getAll(orderByField);
    }

    @Override
    public void insert(Role obj) {
        dao.insert(obj);
    }

    @Override
    public void update(Role obj) {
        dao.update(obj);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public Role getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public Role getByField(String fieldName, String value) {
        return dao.getByField(fieldName, value);
    }
}
