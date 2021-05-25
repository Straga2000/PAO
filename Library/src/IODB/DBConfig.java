package IODB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

    private static final String DB_URL = "jdbc:mysql://localhost:3307/lab12";
    private static final String USER = "root";
    private static final String PASSWORD = "random-pass";

    private static Connection databaseConnection;

    private DBConfig() {
    }

    public static Connection getDBConnection()    {
        try {
            if(databaseConnection == null || databaseConnection.isClosed()) {
                databaseConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return databaseConnection;
    }

    public static void closeDBConnection()    {
        try {
            if(databaseConnection != null && !databaseConnection.isClosed()) {
                databaseConnection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
