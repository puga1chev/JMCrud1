package jmCrud.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jmCrud.dao.DbType;
import org.apache.log4j.Logger;

public class ApplicationProperties {

    private static ApplicationProperties instance;

    private final static Logger logger = Logger.getLogger(ApplicationProperties.class);
    private final Map<String, String> properties = new HashMap<>();

    private ApplicationProperties() {
    }

    public static ApplicationProperties getInstance() {
        if (instance == null) {
            instance = new ApplicationProperties();
        }
        return instance;
    }

    protected Map<String, String> getProperties() {

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {

            Properties prop = new Properties();
            prop.load(input);

            prop.forEach((key, value) -> properties.put((String)key, (String)value));

        } catch (IOException e) {
            logger.error("Ошибка при чтении файла настроек", e);
        }
        return properties;
    }

    public String getProperty(String key) {
        return getProperties().get(key);
    }

    public DbType getChosenDbType()
    {
        DbType dbResult = null;
        switch (getProperty("db.type")) {
            case "hibernate":
                dbResult = DbType.Hibernate;
                break;
            case "jdbc":
                dbResult = DbType.Jdbc;
                break;
            default:
                logger.error("В файле выбора подключаемой БД, выбран неправильный тип: " + getProperty("db.type"));
                break;
        }
        return dbResult;
    }
}
