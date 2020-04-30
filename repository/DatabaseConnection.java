package repository;

import java.sql.*;


public class DatabaseConnection {
    private Connection connection;
    private Statement statement;
    private String host;
    private String userName;
    private String userPassword;

    public static Connection getConnection (DatabaseConnection getConnection){
        try {
            getConnection.host = "jdbc:mysql://localhost:3306/shop";
            getConnection.userName = "root";
            getConnection.userPassword = "dumyyc1997";
            getConnection.connection = DriverManager.getConnection(getConnection.host, getConnection.userName, getConnection.userPassword);
            getConnection.statement = getConnection.connection.createStatement();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getConnection.connection;
    }

}
