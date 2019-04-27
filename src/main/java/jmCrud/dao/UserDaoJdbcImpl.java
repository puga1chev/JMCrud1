package jmCrud.dao;

import jmCrud.executor.Executor;
import jmCrud.model.*;
import jmCrud.util.DbHelper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class UserDaoJdbcImpl implements BaseDaoOperations<User> {

    private final Executor executor;
    private final Connection connection;
    private final String autoCommitErrorText = "Ошибка при попытке включить автокоммит";
    final static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    public UserDaoJdbcImpl() {

        connection = DbHelper.getPostgresConnection();
        executor = new Executor(connection);
    }

    @Override
    public void insert(User user) {

        try {
            connection.setAutoCommit(false);
            executor.execUpdate("insert into users (user_id, username, login, pass) values (DEFAULT, '" + user.getUsername() + "', '" + user.getLogin() + "', '" + user.getPass() + "')");
            connection.commit();
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error("Ошибка при попытке откатить запись в таблицу", e);
            }

        } catch (SQLException e) {
            logger.error("Ошибка при попытке добавить запись в таблицу", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error(autoCommitErrorText, e);
            }
        }
    }

    @Override
    public List<User> getAll(String orderByField) {
        try {
            executor.execQuery("select * from users", result -> {
                List<User> users = new ArrayList<>();
                while (result.next()) {
                    users.add(new User(
                            result.getLong("user_id"),
                            result.getString("username"),
                            result.getString("login"),
                            result.getString("pass"),
                            new Role()
                    ));
                }
                return users;
            });
        } catch (SQLException e) {
            logger.error("Ошибка при извлечении всех пользователей из БД", e);
        }

        return null;
    }

    @Override
    public User getById(Long user_id) {
        try {
            executor.execQuery("select * from users where user_id=" + user_id, result -> {
                User users = null;
                if (result.next()) {

                    users = new User(
                            result.getLong("user_id"),
                            result.getString("username"),
                            result.getString("login"),
                            result.getString("pass"),
                            new Role()
                    );
                }
                return users;
            });
        } catch (SQLException e) {
            logger.error("Ошибка при получении пользователя из БД", e);
        }
        return null;
    }

    @Override
    public User getByField(String fieldName, String value) {
        return null;
    }

    @Override
    public void update(User user)  {

        try {
            connection.setAutoCommit(false);
            executor.execUpdate("update users " +
                    "SET username='" + user.getUsername() + "', " +
                    "login='" + user.getLogin() + "'," +
                    "pass='" + user.getPass() + "' " +
                    "WHERE user_id=" + user.getId());
            connection.commit();
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error("Ошибка при откате изменений", e);
            }

        } catch (SQLException e) {
            logger.error("Ошибка при попытке обность данные пользователя", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error(autoCommitErrorText, e);
            }
        }
    }

    @Override
    public void delete(Long user_id) {
        try {
            connection.setAutoCommit(false);
            executor.execUpdate("DELETE FROM users WHERE user_id=" + user_id);
            connection.commit();
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error("Ошибка при попытке откатить транзакцию удаления пользователя", e);
            }

        } catch (SQLException e) {
            logger.error("Ошибка при попытке удалить пользователя", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error(autoCommitErrorText, e);
            }
        }
    }
}
