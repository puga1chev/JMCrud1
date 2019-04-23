package jmCrud.dao;

import jmCrud.model.User;
import jmCrud.util.HibernateSessionFactory;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.*;
import java.util.*;


public class UsersDaoOrm {

    private final SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    public UsersDaoOrm() {
    }

    public List<User> getAll() throws HibernateException {

        List<User> users = null;
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        users = session.createQuery(criteria).getResultList();

        return users;
    }


/*    public void SetSession(Session session) {
        this.session = session;
    }


    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public long getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }*/


 /*   public long insert(User user) throws HibernateException {
        return (Long) session.save(user);
    }*/
}
