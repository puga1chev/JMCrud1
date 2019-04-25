package jmCrud.dao;

import jmCrud.exception.DBException;
import jmCrud.executor.Executor;
import jmCrud.model.User;
import jmCrud.util.JdbcConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class UsersDaoJdbc implements UsersDaoDB {

    private final Executor executor;
    private final Connection connection;
    private final String autoCommitErrorText = "Ошибка при попытке включить автокоммит";

    public UsersDaoJdbc() {

        connection = JdbcConnection.getInstance();
        executor = new Executor(connection);
    }

    @Override
    public void insert(User user) throws DBException {

        try {
            connection.setAutoCommit(false);
            executor.execUpdate("insert into users (user_id, username, login, pass) values (DEFAULT, '" + user.getUsername() + "', '" + user.getLogin() + "', '" + user.getPass() + "')");
            connection.commit();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            throw new DBException("Ошибка при попытке добавить запись в таблицу", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DBException(autoCommitErrorText, e);
            }
        }
    }

    @Override
    public List<User> getAll() throws DBException {
        try {
            executor.execQuery("select * from users", result -> {
                List<User> users = new ArrayList<>();
                while (result.next()) {
                    users.add(new User(
                            result.getLong("user_id"),
                            result.getString("username"),
                            result.getString("login"),
                            result.getString("pass")
                    ));
                }
                return users;
            });
        } catch (SQLException e) {
            throw new DBException("Ошибка при извлечении всех пользователей из БД", e);
        }

        return null;
    }

    @Override
    public User getById(Long user_id) throws DBException {
        try {
            executor.execQuery("select * from users where user_id=" + user_id, result -> {
                User users = null;
                if (result.next()) {

                    users = new User(
                            result.getLong("user_id"),
                            result.getString("username"),
                            result.getString("login"),
                            result.getString("pass")
                    );
                }
                return users;
            });
        } catch (SQLException e) {
            throw new DBException("Ошибка при получении пользователя из БД", e);
        }
        return null;
    }

    @Override
    public void update(User user) throws DBException  {

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
                throw new DBException("Ошибка при откате изменений", e);
            }

        } catch (SQLException e) {
            throw new DBException("Ошибка при попытке обность данные пользователя", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DBException(autoCommitErrorText, e);
            }
        }
    }

    @Override
    public void delete(Long user_id) throws DBException {
        try {
            connection.setAutoCommit(false);
            executor.execUpdate("DELETE FROM users WHERE user_id=" + user_id);
            connection.commit();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DBException(autoCommitErrorText, e);
            }
        }
    }
}
