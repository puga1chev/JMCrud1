package jmCrud.dao;

import jmCrud.model.*;

public class DaoHibernateFactory extends DaoAbstractFactory {

    private static DaoHibernateFactory instance = null;
    public static DaoHibernateFactory getInstance() {
        if (instance == null) {
            instance = new DaoHibernateFactory();
        }
        return instance;
    }
    private DaoHibernateFactory() { }

    @Override
    public BaseDaoOperations getUsersDao() {
        return new ObjectDaoHibernateImpl<User>(User.class);
    }

    @Override
    public BaseDaoOperations getRoleDao() {
        return new ObjectDaoHibernateImpl<Role>(Role.class);
    }

}
