package jmCrud.exception;

import org.apache.log4j.Logger;

public class DBException extends Exception {

    final static Logger logger = Logger.getLogger(DBException.class);

    public DBException(String message, Throwable cause) {

        super(message, cause);
        logger.error(message, cause);
    }
}
