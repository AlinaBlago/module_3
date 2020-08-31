package dao.impl;

import dao.GenericDAO;
import entity.Expense;
import entity.Income;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class IncomeDAOImpl implements GenericDAO {

    private final SessionFactory sessionFactory;

    public IncomeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Object income) {
        Session session = this.sessionFactory.openSession();
        session.save(income);
    }

    @Override
    public List<Income> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Income ").list();
    }

    @Override
    public Object findById(Integer id) {
        Session session = sessionFactory.openSession();
        return session.get(Income.class, id);
    }

    @Override
    public void deleteAll() {
        Session session = this.sessionFactory.openSession();
        List<Income> incomes = findAll();
        for (Income income : incomes) {
            session.delete(income);
        }
    }
}
