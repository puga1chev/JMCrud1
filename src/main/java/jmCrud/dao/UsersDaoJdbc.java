package jmCrud.dao;

import jmCrud.executor.Executor;
import jmCrud.model.User;
import jmCrud.util.JdbcConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class UsersDaoJdbc implements UsersDaoJdbcImpl {

    private final Executor executor;
    private final Connection connection;

    public UsersDaoJdbc() {

        connection = JdbcConnection.getInstance();
        executor = new Executor(connection);
    }

    @Override
    public void insert(User user) throws SQLException {

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
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        return executor.execQuery("select * from users", result -> {
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
    }

    @Override
    public User getById(Long user_id) throws SQLException {
        return executor.execQuery("select * from users where user_id=" + user_id, result -> {
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
    }

    @Override
    public void update(User user) throws SQLException  {

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
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long user_id) throws SQLException {
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
                e.printStackTrace();
            }
        }
    }
}
