package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.beans.BeanProperty;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String dbURL = "jdbc:mysql://localhost:3306/my_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    //Connection для Hibernate мой способ
/*    private static SessionFactory sessionFactory;
    public static SessionFactory hibernateUtilConnection() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, dbURL + "?useSSL=false");
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }*/

    public Session hibernateUtilConnection() {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", dbURL + "?serverTimezone=Europe/Minsk&useSSL=false")
                //Для чего добавляем временной пояс?
                .setProperty("hibernate.connection.username", USERNAME)
                .setProperty("hibernate.connection.password", PASSWORD)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.show_sql", "true") //для отображения SQL-запросов в консоли
                .setProperty("hibernate.package_to_scan", "jm.task.core.jdbc.model");


        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory.openSession();
    }


//Connection для Hibernate способ Саши
/*    public Session hibernateUtilConnection(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url",
                "jdbc:mysql://localhost:3306/myfirstdbtest?serverTimezone=Europe/Minsk&useSSL=false");
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.connection.password", PASSWORD);
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("show_sql", String.valueOf(true));
        SessionFactory sessionFactory = new Configuration().addProperties(properties)
                .addAnnotatedClass(User.class).buildSessionFactory();
        return sessionFactory.openSession();
    }*/

    //Connection для JDBC
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Class.forName отвечает за загрузку драйвера JDBC
            // для работы с базой данных MySQL.
            connection = DriverManager.getConnection(dbURL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}

