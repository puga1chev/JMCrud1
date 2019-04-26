package jmCrud.dao;

import jmCrud.model.User;
import jmCrud.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.*;

import javax.persistence.criteria.*;
import java.util.*;


public class UserDaoHibernateImpl implements UsersDao {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    final static Logger logger = Logger.getLogger(UserDaoHibernateImpl.class);

    @Override
    public void insert(User user) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Ошибка при попытке добавления пользователя в БД", e);
        }
    }

    @Override
    public void update(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Ошибка при обновлении пользователя в БД", e);
        }
    }

    @Override
    public void delete(Long user_id) {

        User userDel = getById(user_id);
        if (userDel == null) {
            logger.error("Пользователь с id " + user_id + " не найден!");
        }

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(userDel);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Ошибка при попытке удалить пользователя из БД", e);
        }
    }

    @Override
    public User getById(Long user_id) {

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
            logger.error("Ошибка при попытке получить пользователя из БД", e);
        }
        return user;
    }

    public List<User> getAll() {

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
            logger.error("Ошибка при попытке получить всех пользователей из БД", e);
        }
        return users;
    }
}
