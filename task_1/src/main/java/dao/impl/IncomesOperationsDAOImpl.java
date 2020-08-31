package dao.impl;

import dao.GenericDAO;
import entity.Income;
import entity.IncomesOperations;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class IncomesOperationsDAOImpl implements GenericDAO {

    private final SessionFactory sessionFactory;

    public IncomesOperationsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Object incOp) {
        Session session = this.sessionFactory.openSession();
        session.save(incOp);
    }

    @Override
    public List<IncomesOperations> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from IncomesOperations ").list();
    }

    @Override
    public Object findById(Integer id) {
        Session session = sessionFactory.openSession();
        return session.get(IncomesOperations.class, id);
    }

    @Override
    public void deleteAll() {
        Session session = this.sessionFactory.openSession();
        List<IncomesOperations> incomes = findAll();
        for (IncomesOperations income : incomes) {
            session.delete(income);
        }
    }
}
