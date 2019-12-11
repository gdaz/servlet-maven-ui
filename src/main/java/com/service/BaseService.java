package com.service;

import com.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;

public abstract class BaseService {

    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    public static final String SCHEMA_NAME = "";

    @Autowired
    private DataSource dataSource;

    public Connection conn;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws Exception {
        if (conn == null || conn.isClosed()) {
            conn = getDataSource().getConnection();
        }
        return conn;
    }

    public void setConnection(Connection connection) {
        this.conn = connection;
    }

    private void closeConnection(Connection connection) {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (Exception ex) {
            logger.error("There are problem in closeConnection()");
        }
    }

    public Object findBy_PK(Object object) throws Exception {
        logger.info("Service Name {} : Method : findByPK ", this.getClass().getName());
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            return (findByPK(object));

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException();
        } finally {
            closeConnection(conn);
        }
    }

    public Object find_All(Object object) throws Exception {
        logger.info("Service Name : {} : Method : findAll ", this.getClass().getName());
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            return (findAll(object));

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException();
        } finally {
            closeConnection(conn);
        }
    }

    public Object doAdd(Object object) throws Exception {
        logger.info("Service Name: {} : Method : Add", this.getClass().getName());
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            Object retObj = add(object);
            conn.commit();
            return (retObj);

        } catch (BaseException ex) {
            logger.error(ex.getMessage(), ex);
            conn.rollback();
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            conn.rollback();
            throw ex;
        } finally {
            closeConnection(conn);
        }
    }

    public Object doUpdate(Object object) throws Exception {
        logger.info("Service Name: {} : Method : Update", this.getClass().getName());
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            Object retObj = update(object);
            conn.commit();
            return (retObj);

        } catch (BaseException ex) {
            logger.error(ex.getMessage(), ex);
            conn.rollback();
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            conn.rollback();
            throw ex;
        } finally {
            closeConnection(conn);
        }
    }

    public Object doDelete(Object object) throws Exception {
        logger.info("Service Name: {} : Method : Delete", this.getClass().getName());
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            Object retObj = delete(object);
            conn.commit();
            return (retObj);

        } catch (BaseException ex) {
            logger.error(ex.getMessage(), ex);
            conn.rollback();
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            conn.rollback();
            throw ex;
        } finally {
            closeConnection(conn);
        }
    }

    public Object doView(Object object) throws Exception {
        logger.info("Service Name: {} : Method : View", this.getClass().getName());
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            Object retObj = view(object);
            return (retObj);
        } catch (BaseException ex) {
            logger.error(ex.getMessage(), ex);
            conn.rollback();
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            conn.rollback();
            throw ex;
        } finally {
            closeConnection(conn);
        }
    }

    public abstract Object findByPK(Object object) throws Exception;

    public abstract Object add(Object object) throws Exception;

    public abstract Object update(Object object) throws Exception;

    public abstract Object delete(Object object) throws Exception;

    public abstract Object view(Object object) throws Exception;

    public abstract Object findAll(Object object) throws Exception;

}
