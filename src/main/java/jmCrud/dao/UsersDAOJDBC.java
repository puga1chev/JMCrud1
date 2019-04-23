package jmCrud.dao;

import jmCrud.dao.executor.Executor;
import jmCrud.model.User;
import jmCrud.util.JDBCConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class UsersDAOJDBC {

    private final Executor executor;
    private final Connection connection;

    public UsersDAOJDBC() {

        connection = JDBCConnection.getInstance();
        executor = new Executor(connection);
    }

    public void insert(User user)  {

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

    public List<User> getAll() throws SQLException {
        return executor.execQuery("select * from users", result -> {
            List<User> users = new ArrayList<>();
            while (result.next()) {
//                users.add(new User());
                int a = 1;
            }
            return null;
        });
    }
/*

    public User get(long id) throws SQLException {
        return executor.execQuery("select * from users where id=" + id,);
    }

    public long getUserId(String name) throws SQLException {
        return executor.execQuery("select * from users where user_name='" + name + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public UsersDataSet get(long id) throws DBException {
        try {
            return (new UsersDAOJDBC(connection).get(id));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
*/

}
