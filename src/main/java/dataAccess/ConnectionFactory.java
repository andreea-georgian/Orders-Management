package dataAccess;

import java.sql.*;
import java.util.logging.Logger;

public class ConnectionFactory {
    private Logger logger = Logger.getLogger(ConnectionFactory.class.getName());
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String dburl = "jdbc:mysql://localhost:3306/ordersmanagement";
    private String user = "root";
    private String pass = "Andreea.59436782";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Constructor fara parametrii pentru crearea obiectului
     */
    private ConnectionFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda creeaza si returneaza conexiunea cu baza de date
     * @return
     */
    private Connection createConnection() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(dburl, user, pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
