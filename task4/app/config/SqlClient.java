package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlClient {
    private Connection connection;


    public SqlClient() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/productmanagement", "root", "password");

    }
    public Connection getConnection() {
        return connection;
    }
}
