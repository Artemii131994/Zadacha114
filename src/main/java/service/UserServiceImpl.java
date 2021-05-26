package service;

import dao.UserDaoHibernateImpl;
import model.User;
import org.hibernate.SessionFactory;
import util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {
    SessionFactory session = Util.getSessionFactory();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl(session);

    public void createUsersTable() {

        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {

        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {

        userDaoHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {

        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> users = userDaoHibernate.getAllUsers();
        return users;
    }

    public void cleanUsersTable() {

        userDaoHibernate.cleanUsersTable();
    }
}