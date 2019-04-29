package jmCrud.dao;

import jmCrud.util.ApplicationProperties;
import org.apache.log4j.Logger;

public abstract class DaoAbstractFactory {

//todo property reader
//todo по дефолту вернуть что-нибудь

    final static Logger logger = Logger.getLogger(DaoAbstractFactory.class);
    final static ApplicationProperties properties = ApplicationProperties.getInstance();

    abstract public BaseDaoOperations getUsersDao();
    abstract public BaseDaoOperations getRoleDao();

    public static DaoAbstractFactory getFactory() {

        return getFactory(properties.getChosenDbType());
    }

    public static DaoAbstractFactory getFactory(DbType dbType) {

        if (dbType.equals(DbType.Hibernate)) {
            return DaoHibernateFactory.getInstance();
        } else if (dbType.equals(DbType.Jdbc)) {
            return new DaoJdbcFactory();
        }
        logger.error("Не найдена подходящая реализация для типа " + dbType);
        return null;//todo exeption
    }
}
