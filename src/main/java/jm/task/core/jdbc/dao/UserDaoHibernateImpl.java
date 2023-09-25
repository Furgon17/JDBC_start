package jm.task.core.jdbc.dao;

import jakarta.persistence.criteria.CriteriaDelete;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util; //Дабавил сам...
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;



public class UserDaoHibernateImpl extends Util implements UserDao {
    private Session session;
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override //????????????
    public List<User> getAllUsers() {

    }

    @Override
    public void cleanUsersTable() {

    }
}
