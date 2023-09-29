package jm.task.core.jdbc.dao;

import jakarta.persistence.criteria.*;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.JpaCriteriaInsertSelect;

import java.util.Collections;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    List<User> users;


    @Override
    public void createUsersTable() {
        try (Session session = Util.getHibernateConnection().openSession()) {

            Transaction transaction = session.beginTransaction(); // Начинаем транзакцию

            String sql = "CREATE TABLE IF NOT EXISTS users" +
                    "(id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR (30)," +
                    "lastname VARCHAR (30)," +
                    "age INT(3));";

            Query query = session.createNativeQuery(sql); //???????????точно ли createQuery
            query.executeUpdate(); //Выполняем запрос для создания таблицы
            transaction.commit(); //Фиксируем транзакцию
            System.out.println("Таблица users успешно создана");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getHibernateConnection().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getHibernateConnection().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getHibernateConnection().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null){
                session.remove(user);
                transaction.commit();
                System.out.println("Пользователь " + user.getName() + " успешно удален из таблицы!");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getHibernateConnection().openSession();){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);

            Query<User> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getHibernateConnection().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
            criteriaDelete.from(User.class);

            session.createQuery(criteriaDelete).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
