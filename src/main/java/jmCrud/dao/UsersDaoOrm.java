package jmCrud.dao;

import jmCrud.model.User;
import jmCrud.util.HibernateSessionFactory;
import org.hibernate.*;

import javax.persistence.criteria.*;
import java.util.*;


public class UsersDaoOrm implements UsersDaoOrmImpl {

    private final SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    public UsersDaoOrm() {
    }

    @Override
    public void insert(User user) throws HibernateException {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) throws HibernateException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long user_id) throws HibernateException {

        User userDel = getById(user_id);
        if (userDel == null) {
            throw new IllegalArgumentException("Пользователь с id " + user_id + " не найден!");
        }

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(userDel);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public User getById(Long user_id) throws HibernateException {

        User user = null;
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root).where(builder.equal(root.get("id"), user_id));
            user = session.createQuery(criteria).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }

    public List<User> getAll() throws HibernateException {

        List<User> users = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root);
            users = session.createQuery(criteria).getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }
}
