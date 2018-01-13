package by.radomskaya.project.pool;

import by.radomskaya.project.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class PoolManager {
    private static final Logger LOGGER = LogManager.getLogger(PoolManager.class);
    private String url;
    int poolSize;
    private Properties properties;

    PoolManager() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("database");
            url = resource.getString("db.url");
            poolSize = Integer.parseInt(resource.getString("db.poolSize"));
            properties = new Properties();
            properties.put("user", resource.getString("db.user"));
            properties.put("password", resource.getString("db.password"));
            properties.put("autoReconnect", "true");
            properties.put("characterEncoding", resource.getString("db.encoding"));
            properties.put("useUnicode", resource.getString("db.useUnicode"));

        } catch (MissingResourceException e) {
            LOGGER.log(Level.FATAL, "Can not find properties file " + e.getMessage());
            throw new RuntimeException("Can not find properties file " + e.getMessage());
        }
    }

    ProxyConnection getConnection() throws DAOException {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = new ProxyConnection(DriverManager.getConnection(url, properties));
        } catch (SQLException e) {
            throw new DAOException("Can not create connection " + e.getMessage());
        }
        return proxyConnection;
    }
}
