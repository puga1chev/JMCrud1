package jmCrud.util;

import java.sql.*;

public class JdbcConnection {

    private static Connection instastance = null;

    public static Connection getInstance() {
        if (instastance == null) {
            instastance = getPostgresConnection();
        }
        return instastance;
    }

    private JdbcConnection() {

    }
    private static Connection getPostgresConnection() {

        try {
            DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());

            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:postgresql://").
                    append("localhost:").
                    append("5432/").
                    append("jmcrud1?").
                    append("user=postgres&").
                    append("password=postgres");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;

        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
