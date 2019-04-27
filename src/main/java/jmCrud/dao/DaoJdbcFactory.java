package jmCrud.dao;

public class DaoJdbcFactory extends DaoAbstractFactory {
    @Override
    public BaseDaoOperations getUsersDao() {
        return new UserDaoJdbcImpl();
    }

    @Override
    public BaseDaoOperations getRoleDao() {
        return null;// todo exeption
    }
}
