package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

//        userDaoJDBC.createUsersTable();
//        userDaoJDBC.dropUsersTable();
//        userDaoJDBC.saveUser("Pavel", "Grakov", (byte) 24);
//        userDaoJDBC.saveUser("Dima", "Petrov", (byte) 32);
//        userDaoJDBC.saveUser("Iina", "Ivanova", (byte) 37);
//        userDaoJDBC.removeUserById(2);
//        userDaoJDBC.cleanUsersTable();
        System.out.println(userDaoJDBC.getAllUsers());
        UserService userService = new UserServiceImpl();
        System.out.println(userService.getAllUsers());
    }
}
