package jmCrud.service;

import jmCrud.dao.UsersDaoOrm;
import jmCrud.model.*;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class UserServiceOrm {

    private UsersDaoOrm usersDaoOrm = new UsersDaoOrm();

    public UserServiceOrm() {
    }

    public List<User> getAll() throws HibernateException {

        return usersDaoOrm.getAll();
    }

/*    public UsersDataSet getUser(long id) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UsersDaoJdbc dao = new UsersDaoJdbc(session);
            UsersDataSet dataSet = dao.get(id);
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }*/
/*

    public void UpdateDB(UsersDAO dao) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            dao.SetSession(session);
            long id = dao.insertUser(name);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
*/

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
