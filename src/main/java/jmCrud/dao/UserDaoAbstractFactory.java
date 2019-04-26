package jmCrud.dao;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

public abstract class UserDaoAbstractFactory {

    final static Logger logger = Logger.getLogger(UserDaoAbstractFactory.class);

    public abstract UsersDao createDaoImpl();

    protected Map<String, String> getProperties() {

        Map<String, String> properties = new HashMap<>();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("user_dao.properties")) {

            Properties prop = new Properties();
            prop.load(input);

            System.out.println(prop.getProperty("db.url"));

            prop.forEach((key, value) -> properties.put((String)key, (String)value));

        } catch (IOException e) {
            logger.error("Ошибка при чтении файла настроек", e);
        }
        return properties;
    }

    protected String getProperty(String key) {
        return getProperties().get(key);
    }
}
