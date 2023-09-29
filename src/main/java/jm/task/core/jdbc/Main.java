package jm.task.core.jdbc;

import com.mysql.cj.Session;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        //Через Hibernate
        UserServiceHibernateImpl userServiceHibernate = new UserServiceHibernateImpl();
//        userServiceHibernate.createUsersTable(); //cоздание таблицы
//        userServiceHibernate.dropUsersTable(); //Удаление таблицы
//        userServiceHibernate.saveUser("Igor", "Provkin", (byte)54);

//        userServiceHibernate.saveUser("Misha", "Petrov", (byte) 34); //добавление пользователя
//        userServiceHibernate.removeUserById(1); //удаляем пользователя по id
//        userServiceHibernate.cleanUsersTable();
//        System.out.println(userServiceHibernate.getAllUsers());


        // Через  JDBC
//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.createUsersTable();
//        userDaoJDBC.dropUsersTable();
//        userDaoJDBC.saveUser("Pavel", "Grakov", (byte) 24);
//        userDaoJDBC.saveUser("Dima", "Petrov", (byte) 32);
//        userDaoJDBC.saveUser("Iina", "Ivanova", (byte) 37);
//        userDaoJDBC.removeUserById(1);
//        userDaoJDBC.cleanUsersTable();
//        System.out.println(userDaoJDBC.getAllUsers());
//        UserService userService = new UserServiceImpl();
//        userService.createUsersTable();
//        userService.saveUser("Pavel", "Ivanov", (byte) 22);
//        System.out.println(userService.getAllUsers());
    }
}
