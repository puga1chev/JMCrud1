package jmCrud.util;

import jmCrud.dao.DbType;
import jmCrud.model.*;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.sql.*;
import java.util.*;

public class DbHelper {

    private static SessionFactory hibernateSessionFactory;
    private static Connection postgresConnect;
    final static Logger logger = Logger.getLogger(DbHelper.class);

    private DbHelper(DbType type) {

    }

    public static SessionFactory getHibernateSessionFactory() {
        if (hibernateSessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://127.0.0.1:5432/jmcrud1");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "postgres");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
                settings.put(Environment.POOL_SIZE, 1);
                settings.put(Environment.HBM2DDL_AUTO, "validate"); // validate | update | create | create-drop
                settings.put(Environment.SHOW_SQL, true);
                settings.put(Environment.USE_SQL_COMMENTS, true);
                settings.put(Environment.FORMAT_SQL, true);

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Role.class);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                hibernateSessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                logger.error("Ошибка подключения к базе данных", e);
            }
        }
        return hibernateSessionFactory;
    }

    public static Connection getPostgresConnection() {

        if (postgresConnect == null) {
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


            } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                logger.error("Ошибка подключения к базе данных", e);
            }
        }
        return postgresConnect;
    }
}
