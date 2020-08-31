package dao.impl;

import dao.GenericDAO;
import entity.Expense;
import entity.Operation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OperationDAOImpl implements GenericDAO {


    private final SessionFactory sessionFactory;

    public OperationDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Object operation) {
        Session session = this.sessionFactory.openSession();
        session.save(operation);
    }

    @Override
    public List<Operation> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Operation ").list();
    }

    @Override
    public Object findById(Integer id) {
        Session session = sessionFactory.openSession();
        return session.get(Operation.class, id);
    }

    @Override
    public void deleteAll() {
        Session session = this.sessionFactory.openSession();
        List<Operation> operations = findAll();
        for (Operation operation : operations) {
            session.delete(operation);
        }
    }
}
