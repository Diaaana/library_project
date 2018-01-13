package by.radomskaya.project.pool;

import by.radomskaya.project.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private static AtomicBoolean flag = new AtomicBoolean();
    private static Lock lock = new ReentrantLock();
    private BlockingQueue<ProxyConnection> connections;
    private PoolManager manager;

    public ConnectionPool() throws DAOException {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            init();
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL, e + " DriverManager wasn't found.");
        }
    }

    private void init() throws DAOException {
        manager = new PoolManager();
        connections = new ArrayBlockingQueue<>(manager.poolSize);
        for (int i = 0; i < manager.poolSize; i++) {
            connections.add(manager.getConnection());
        }
        if (connections.isEmpty()) {
            LOGGER.log(Level.FATAL, "Can not create database connection by.radomskaya.project.pool");
        }

        int sizeDiff = manager.poolSize - connections.size();
        if (sizeDiff > 0) {
            for (int i = 0; i < sizeDiff; i++) {
                connections.add(manager.getConnection());
            }
        }
        sizeDiff = manager.poolSize - connections.size();
        if (sizeDiff > 0 && sizeDiff < manager.poolSize / 2) {
           LOGGER.log(Level.WARN, "Connection by.radomskaya.project.pool size is smaller than required; Attempt to continue working...");
        } else if (sizeDiff > manager.poolSize / 2) {
           LOGGER.log(Level.FATAL, "Connection by.radomskaya.project.pool size is too small: size - " + connections.size());
        }
    }


    public static ConnectionPool getInstance() {
        if (!flag.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    flag.set(true);
                }
            } catch (DAOException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Can not take connection from by.radomskaya.project.pool: " + e);
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        connections.offer(connection);
    }

    public void closePool() throws SQLException {
        for (ProxyConnection connection : connections) {
            connection.closeConnection();
        }
    }
}
