package jm.task.core.jdbc.util;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String URL = "jdbc:mysql://localhost:3306/myfirstdbtest";
    private static String USERNAME = "root";
    private static String PASSWORD = "Pavel14121998";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Class.forName отвечает за загрузку драйвера JDBC для работы с базой данных MySQL.
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}

