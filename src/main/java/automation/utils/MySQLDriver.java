package automation.utils;

import automation.Config;

import java.sql.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MySQLDriver
{
    final private Logger logger = Logger.getLogger(String.valueOf(this.getClass()));
    final private Connection _mysqlConn;

    public MySQLDriver() {
        String url = String.format(
                "jdbc:mysql://%s:%s/%s",
                Config.MYSQL_HOST.value,
                Config.MYSQL_PORT.value,
                Config.MYSQL_DATABASE.value
        );
        try {
            logger.info("Create mysql connection");
            this._mysqlConn = DriverManager.getConnection(url, Config.MYSQL_USERNAME.value, Config.MYSQL_PASSWORD.value);
            Runtime.getRuntime().addShutdownHook(new Thread(MySQLDriver.this::close));
        } catch (SQLException e) {
            String msg = "Could not connect to database";
            logger.info(msg + ": REASON: " + e.getMessage());
            throw new RuntimeException(msg, e);
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            return this._mysqlConn.createStatement().executeQuery(query);
        } catch (SQLException e) {
            String msg = "Could not execute query: \"" + query + "\"";
            logger.info(msg + ": REASON: " + e.getMessage());
            throw new RuntimeException(msg, e);
        }
    }

    public boolean execute(String query) {
        try {
            return this._mysqlConn.createStatement().execute(query);
        } catch (SQLException e) {
            String msg = "Could not execute query: \"" + query + "\"";
            logger.info(msg + ": REASON: " + e.getMessage());
            throw new RuntimeException(msg, e);
        }
    }

    public PreparedStatement preparedStatement(String query) {
        try {
            return this._mysqlConn.prepareStatement(query);
        } catch (SQLException e) {
            String msg = "Could not create prepared statement with query: \"" + query + "\"";
            logger.info(msg + ": REASON: " + e.getMessage());
            throw new RuntimeException(msg, e);
        }
    }


    final public void close() {
        try {
            this._mysqlConn.close();
            logger.info("Close mysql connection");
        } catch (SQLException e) { /* Ignore */ }
    }
}

