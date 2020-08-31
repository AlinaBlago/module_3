package dao.impl;

import dao.GenericDAO;
import entity.ExpensesOperations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ExpensesOperationsDAOImpl implements GenericDAO {

    private final SessionFactory sessionFactory;

    public ExpensesOperationsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Object expOp) {
        Session session = this.sessionFactory.openSession();
        session.save(expOp);
    }

    @Override
    public List<ExpensesOperations> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from ExpensesOperations ").list();
    }

    @Override
    public Object findById(Integer id) {
        Session session = sessionFactory.openSession();
        return session.get(ExpensesOperations.class, id);
    }

    @Override
    public void deleteAll() {
        Session session = this.sessionFactory.openSession();
        List<ExpensesOperations> expensesOperations = findAll();
        for (ExpensesOperations expensesOperation : expensesOperations) {
            session.delete(expensesOperation);
        }
    }
}
