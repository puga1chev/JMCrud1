package jmCrud.dao;

import jmCrud.util.ApplicationProperties;
import org.apache.log4j.Logger;

public class DaoFactoryHelper {

    final static Logger logger = Logger.getLogger(DaoFactoryHelper.class);
    ApplicationProperties properties = ApplicationProperties.getInstance();

    private static DaoFactoryHelper instance = new DaoFactoryHelper();
    public static DaoFactoryHelper getInstance() {
        return instance;
    }
    private DaoFactoryHelper() {
    }

    // chose into configuration file
    public DaoAbstractFactory getFactory() {

        return getFactory(properties.getChosenDbType());
    }

    public DaoAbstractFactory getFactory(DbType dbType) {

        if (dbType.equals(DbType.Hibernate)) {
            return DaoHibernateFactory.getInstance();
        } else if (dbType.equals(DbType.Jdbc)) {
            return new DaoJdbcFactory();
        }
        logger.error("Не найдена подходящая реализация для типа " + dbType);
        return null;//todo exeption
    }
}
