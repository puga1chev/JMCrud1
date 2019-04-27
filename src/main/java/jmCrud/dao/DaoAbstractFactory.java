package jmCrud.dao;

public abstract class DaoAbstractFactory {

//todo property reader
//todo по дефолту вернуть что-нибудь

    abstract public BaseDaoOperations getUsersDao();
    abstract public BaseDaoOperations getRoleDao();
}
