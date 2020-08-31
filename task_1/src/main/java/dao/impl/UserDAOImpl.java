package dao.impl;

import dao.GenericDAO;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class UserDAOImpl implements GenericDAO {

    private final SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Object user) {
        Session session = this.sessionFactory.openSession();
        session.save(user);
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from User ").list();
    }

    @Override
    public Object findById(Integer id) {
        Session session = sessionFactory.openSession();
        return session.get(User.class, id);
    }

    @Override
    public void deleteAll() {
        Session session = this.sessionFactory.openSession();
        List<User> users = findAll();
        for (User user : users) {
            session.delete(user);
        }
    }

    public int getUserId(String login, String password) throws Exception {

        Session session = sessionFactory.openSession();

        Query query = session.createSQLQuery("Select * from users where login = :login and password = :password");
        query.setParameter("login" , login);
        query.setParameter("password" , password);
        List users = query.list();

        if(users.size() == 0){
            return 0;
        } else {
            Object[] curUs = (Object[]) users.get(0);

            return (int)curUs[0];
        }
    }

    public boolean isUserExists(String login, String password) throws Exception {
        int id = getUserId(login , password);
        return id == 0 ? false : true;
    }
}
