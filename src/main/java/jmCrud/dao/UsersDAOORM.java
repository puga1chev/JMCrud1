package jmCrud.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;


public class UsersDAOORM {

    private Session session;

    public UsersDAOORM() {

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
    }


    public long insert(String name) throws HibernateException {
        return (Long) session.save(new UsersDataSet(name));
    }*/
}
