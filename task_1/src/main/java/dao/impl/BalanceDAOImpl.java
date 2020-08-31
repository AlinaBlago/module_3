package dao.impl;

import dao.GenericDAO;
import entity.Balance;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.w3c.dom.UserDataHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BalanceDAOImpl implements GenericDAO {

    private final SessionFactory sessionFactory;

    public BalanceDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Object balance) {
        Session session = this.sessionFactory.openSession();
        session.save(balance);
    }

    @Override
    public List<Balance> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Balance ").list();
    }

    @Override
    public Object findById(Integer id) {
        Session session = sessionFactory.openSession();
        return session.get(Balance.class, id);
    }

    @Override
    public void deleteAll() {
        Session session = this.sessionFactory.openSession();
        List<Balance> balances = findAll();
        for (Balance balance : balances) {
            session.delete(balance);
        }
    }
    public List<Balance> getAllBalancesByUserId(int userId){
        Session session = this.sessionFactory.openSession();

        Query query = session.createSQLQuery("Select * from balances where user_id = :id ");
        query.setParameter("id" , userId);

        List users = query.list();

        if(users.size() == 0){
            return new ArrayList<>();
        } else {
            Object[] curUs = (Object[]) users.get(0);
            List<Balance> balances = new ArrayList<Balance>();

            UserDAOImpl userDAO = new UserDAOImpl(sessionFactory);

            for (Object item : users) {
                Object[] curItem = (Object[])item;

                Balance newBalance = new Balance();
                newBalance.setId((int)curItem[0]);
                newBalance.setNameOfBalance((String)curItem[1]);
                newBalance.setUserId((User)userDAO.findById((int)curItem[2]));

                balances.add(newBalance);
            }

            return balances;
        }
    }
}
