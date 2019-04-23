package jmCrud.service;

import jmCrud.dao.UsersDAOJDBC;
import jmCrud.model.User;
import java.sql.SQLException;
import java.util.*;

public class UserServiceJDBC {

    public List<User> getAll() throws SQLException {
        return new UsersDAOJDBC().getAll();
    }
}
