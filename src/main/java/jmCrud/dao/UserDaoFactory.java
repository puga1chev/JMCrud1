package jmCrud.dao;

import java.util.*;

public class UserDaoFactory extends UserDaoAbstractFactory {

    @Override
    public UsersDao createDaoImpl() {

        String dbType = getProperty("db.type");
        if (dbType.equals("hibernate")) {
            return new UserDaoHibernateImpl();
        } else if (dbType.equals("jdbc")) {
            return new UserDaoJdbcImpl();
        }
        return null;
    }
}
