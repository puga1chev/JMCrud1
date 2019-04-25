package jmCrud.dao;

import jmCrud.exception.DBException;
import jmCrud.model.User;
import jmCrud.util.HibernateUtil;
import org.hibernate.*;

import javax.persistence.criteria.*;
import java.util.*;


public class UsersDaoOrm implements UsersDaoDB {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public UsersDaoOrm() {
    }

    @Override
    public void insert(User user) throws DBException {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DBException("Ошибка при попытке добавления пользователя в БД", e);
        }
    }

    @Override
    public void update(User user) throws DBException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DBException("Ошибка при обновлении пользователя в БД", e);
        }
    }

    @Override
    public void delete(Long user_id) throws DBException {

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
            throw new DBException("Ошибка при попытке удалить пользователя из БД", e);
        }
    }

    @Override
    public User getById(Long user_id) throws DBException {

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
            throw new DBException("Ошибка при попытке получить пользователя из БД", e);
        }
        return user;
    }

    public List<User> getAll() throws DBException {

        List<User> users = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root);
            criteria.orderBy(builder.asc(root.get("id")));
            users = session.createQuery(criteria).getResultList();
            transaction.commit();
        } catch (Exception e) {
            throw new DBException("Ошибка при попытке получить всех пользователей из БД", e);
        }
        return users;
    }
}
