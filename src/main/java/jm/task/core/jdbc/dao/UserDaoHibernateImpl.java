package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class UserDaoHibernateImpl extends Util implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        //открываем сессию с помощью метода openSession
        try (Session session = hibernateUtilConnection().getSessionFactory().openSession()) {
//Начинаем новую транзакцию с помощью метода beginTransaction(). Транзакция позволяет
// группировать несколько операций в одну логическую единицу работы
            Transaction transaction = session.beginTransaction();
//создаем SQL-запрос с помощью метода `createSQLQuery()`, передавая ему строку SQL-запроса.
            String sql = "CREATE TABLE IF NOT EXISTS users" +
                    "(id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                    "user_name VARCHAR (30)," +
                    "user_lastname VARCHAR (30)," +
                    "user_age INT(3));";
// Затем мы вызываем метод `executeUpdate()`, чтобы выполнить этот запрос и создать таблицу
            session.createNativeQuery(sql).executeUpdate();
//фиксируем транзакцию. Это означает, что все изменения, сделанные в рамках транзакции, будут сохранены в базе данных
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Не удалось создать таблицу! ");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Session session = hibernateUtilConnection().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*    @Override
        public void saveUser(String name, String lastName, byte age) {
            try (Session session = hibernateUtilConnection().getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                User user = new User(name, lastName, age);
                session.persist(user);
                transaction.commit();
            } catch (Exception e) {
                System.out.println("Не удалось добавить пользователя в таблицу users");
                e.printStackTrace();
            }
        }*/

    //Так работает, но тут через SQL-запрос...
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = hibernateUtilConnection().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "INSERT INTO users (user_name, user_lastname, user_age) VALUES (:name, :lastName, :age)";
            Query query = session.createNativeQuery(sql);
            query.setParameter("name", name);
            query.setParameter("lastName", lastName);
            query.setParameter("age", age);
            int result = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*        @Override
        public void removeUserById(long id) {
            try (Session session = hibernateUtilConnection().getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                String sql = "DELETE FROM users WHERE id = :id";
                session.createQuery(sql).setParameter("id", id).executeUpdate();
                transaction.commit();

                System.out.println("Пользователь с id \" + id + \" успешно удален");

            } catch (Exception e) {
                System.out.println("Не удалось удалить пользователся с id" + id);
                e.printStackTrace();
            }
        }*/

/*    @Override
    public void removeUserById(long id) {
        try (Session session = hibernateUtilConnection().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id); //получаем пользователя по id
            if (user != null) { //Если пользователь найден
                session.delete(user); //удаляем его
                transaction.commit(); //подтверждаем
                System.out.println("Пользователь с id " + id + " успешно удален");
            } else {
                System.out.println("Пользователь с id " + id + " не найден");
            }
        } catch (Exception e) {
            System.out.println("Не удалось удалить пользователя с id " + id);
            e.printStackTrace();
        }
    }*/

    //Второй способ удаления пользователя по id
    public void removeUserById(long id) {
        try (Session session = hibernateUtilConnection().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = session.load(User.class, id);
            if (user != null) {
                session.delete(user);
                transaction.commit();
                System.out.println("Пользователь с id " + id + " успешно удален");
            } else {
                System.out.println("Пользователь с id " + id + " не найден");
            }
        } catch (Exception e) {
            System.out.println("Не удалось удалить пользователя с id " + id);
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = hibernateUtilConnection().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "SELECT * FROM users";
            Query<User> query = session.createQuery(sql);
            List<User> userList = query.getResultList();
            return userList;
        } catch (Exception e) {
            System.out.println("Не удалось получить список пользователей");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = hibernateUtilConnection().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DELETE FROM users";
            session.createQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Таблица пользователей успешно очищена.");
        } catch (Exception e) {
            System.out.println("Не удалось очистить таблицу users");
            e.printStackTrace();
        }
    }
}