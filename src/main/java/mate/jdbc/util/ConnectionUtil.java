package mate.jdbc.util;

import mate.jdbc.exception.DataProcessingException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't load JDBC driver for MySQL", e);
        }
    }

    public static Connection getConnection() {
        File file = new File("src/main/resources/database.properties");
        try {
            Properties dbProperties = new Properties();
            dbProperties.load(new FileReader(file));
            String host = dbProperties.getProperty("host");
            return DriverManager.getConnection(host, dbProperties);
        } catch (SQLException | IOException e) {
            throw new DataProcessingException("Can't create connection to DB", e);
        }
    }
}
