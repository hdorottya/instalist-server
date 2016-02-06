package org.noorganization.instalist.server.support;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 *
 * Created by damihe on 30.01.16.
 */
public class DatabaseHelper {
    private static DatabaseHelper sInstance;
    private EntityManagerFactory  mFactory;


    /**
     * Retrieves a database-connection. The DatabaseHelper must get initialized once before this call works.
     * @return Either the connection or null, if connecting failed.
     */
    public EntityManager getManager() {
        if (mFactory == null)
            throw new IllegalStateException("DatabaseHelper was not initialized properly.");
        return mFactory.createEntityManager();
    }

    /**
     * Initializes the DatabaseHelper, including checking and upgrading of the internal structures.
     * @param _jpaInstance The JPA-Persistence-Instance.
     */
    public void initialize(String _jpaInstance) {
        mFactory = Persistence.createEntityManagerFactory(_jpaInstance);
    }

    public static DatabaseHelper getInstance() {
        if (sInstance == null) {
            sInstance = new DatabaseHelper();
        }
        return sInstance;
    }

    private DatabaseHelper() {
    }
}
