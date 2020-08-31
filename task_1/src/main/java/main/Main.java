package main;

import dao.impl.*;
import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.Instant;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        Configuration cfg = new Configuration().configure();
        try (SessionFactory sessionFactory = cfg.buildSessionFactory();
        ) {

            BalanceDAOImpl balanceDAO = new BalanceDAOImpl(sessionFactory);

            UserDAOImpl userDAO = new UserDAOImpl(sessionFactory);

            System.out.println("Enter your login: ");
            String login = scn.next();
            System.out.println("Enter your password: ");
            String password = scn.next();

            if(!userDAO.isUserExists(login, password)) {
                throw new Exception("User doesnt exist.");
            };

            int userId = userDAO.getUserId(login, password);

            System.out.println("Enter type of operation :");

            OperationDAOImpl operationDAO = new OperationDAOImpl(sessionFactory);


            System.out.println("\nPlease select balance id to add operation");
            for(Balance item :  balanceDAO.getAllBalancesByUserId(userId)){
                System.out.println("Id : " + item.getId() + " ---- " +  item.getNameOfBalance());
            }

            int selectedBalanceId = scn.nextInt();

            System.out.println("\n\n\n\nSelect type of operation : \n1 - Income\n2 - Expenses");

            Operation operation = new Operation();
            operation.setBalanceId((Balance)balanceDAO.findById(selectedBalanceId));
            operation.setDateOfOperation(Instant.now());

            OperationDAOImpl opera = new OperationDAOImpl(sessionFactory);
            opera.create(operation);

            int selectedTypeOfOperation = scn.nextInt();

            if(selectedTypeOfOperation == 1){
                int answer = -1 ;
                IncomeDAOImpl incomeDAO = new IncomeDAOImpl(sessionFactory);
                IncomesOperationsDAOImpl incomesOperationsDAO = new IncomesOperationsDAOImpl(sessionFactory);
                do{
                    System.out.println("Enter income type : ");
                    String incomeType = scn.next();
                    System.out.println("Enter income sum : ");
                    int incomeSum = scn.nextInt();

                    Income income = new Income();
                    income.setSumOfIncomes(incomeSum);
                    income.setTypeOfIncomes(incomeType);

                    incomeDAO.create(income);


                    IncomesOperations incomesOperations = new IncomesOperations();
                    incomesOperations.setIncomeId(income);
                    incomesOperations.setOperationId(operation);

                    incomesOperationsDAO.create(incomesOperations);

                    System.out.println("Add another income ? \n1 - yes\n0 - no");
                    answer = scn.nextInt();

                }while (answer > 0);
                System.out.println("\n\n\n\n");
            } else {
                int answer = 0;
                ExpenseDAOImpl expenseDAO = new ExpenseDAOImpl(sessionFactory);
                ExpensesOperationsDAOImpl expensesOperationsDAO = new ExpensesOperationsDAOImpl(sessionFactory);
                do{
                    System.out.println("Enter expense type: ");
                    String expenseType =
                            scn.next();
                    System.out.println("Enter expense sum: ");
                    int expenseSum = scn.nextInt();

                    Expense expense = new Expense();
                    expense.setSumOfExpenses(expenseSum);
                    expense.setTypeOfExpenses(expenseType);

                    expenseDAO.create(expense);


                    ExpensesOperations expensesOperations = new ExpensesOperations();
                    expensesOperations.setIdExpense(expense);
                    expensesOperations.setIdOperation(operation);

                    expensesOperationsDAO.create(expensesOperations);
                    System.out.println("Add another expense? \n1 - yes\n0 - no");
                    answer = scn.nextInt();

                }while (answer > 0);
                System.out.println("\n\n\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
