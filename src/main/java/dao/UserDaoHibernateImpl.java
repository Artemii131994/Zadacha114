package dao;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {

    }

    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user" +
                    "(id int not null auto_increment," +
                    " name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age int, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("User с именем " + name + " добавлен базу данных");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        Transaction transaction = null;
        List list = new ArrayList();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            list = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
            return list;
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE user").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}