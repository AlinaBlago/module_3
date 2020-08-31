package dao.impl;

import dao.GenericDAO;
import entity.Balance;
import entity.Expense;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ExpenseDAOImpl implements GenericDAO {

    private final SessionFactory sessionFactory;

    public ExpenseDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Object expense) {
        Session session = this.sessionFactory.openSession();
        session.save(expense);
    }

    @Override
    public List<Expense> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Expense ").list();
    }

    @Override
    public Object findById(Integer id) {
        Session session = sessionFactory.openSession();
        return session.get(Expense.class, id);
    }

    @Override
    public void deleteAll() {
        Session session = this.sessionFactory.openSession();
        List<Expense> expenses = findAll();
        for (Expense expense : expenses) {
            session.delete(expense);
        }
    }
}
