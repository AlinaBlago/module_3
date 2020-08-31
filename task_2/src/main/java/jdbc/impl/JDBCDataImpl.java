package jdbc.impl;

import entity.*;
import jdbc.JDBCData;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JDBCDataImpl implements JDBCData {

    public ArrayList<Balance> getBalances() throws SQLException {
        ArrayList<Balance> balanceArrayList = new ArrayList<Balance>();
        try (Connection connection = DriverManager.getConnection(url, props)) {
            try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM balances;")) {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    balanceArrayList.add(
                            new Balance(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getInt(3)
                            )
                    );
                }
            }
        }
        return balanceArrayList;
    }

    public ArrayList<Expense> getExpenses() throws SQLException {
        ArrayList<Expense> expenseArrayList = new ArrayList<Expense>();
        try (Connection connection = DriverManager.getConnection(url, props)) {
            try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM expenses;")) {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    expenseArrayList.add(
                            new Expense(
                                    rs.getInt(1),
                                    rs.getInt(2),
                                    rs.getString(3)
                            )
                    );
                }
            }
        }
        return expenseArrayList;
    }

    @Override
    public ArrayList<Income> getIncomes() throws SQLException {
        ArrayList<Income> incomeArrayList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, props)) {
            try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM incomes;")) {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    incomeArrayList.add(
                            new Income(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getInt(3)
                            )
                    );
                }
            }
        }
        return incomeArrayList;
    }

    @Override
    public ArrayList<Operation> getOperation() throws SQLException {
        ArrayList<Operation> operationArrayList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, props)) {
            try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM operations;")) {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    operationArrayList.add(
                            new Operation(
                                    rs.getInt(1),
                                    ((Timestamp)rs.getObject(3)).toInstant(),
                                    rs.getInt(2)
                            )
                    );
                }
            }
        }
        return operationArrayList;
    }

    @Override
    public ArrayList<IncomeOperation> getIncomeOperation() throws SQLException {
        ArrayList<IncomeOperation> incomeOperationArrayList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, props)) {
            try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM incomes_operations;")) {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    incomeOperationArrayList.add(
                            new IncomeOperation(
                                    rs.getInt(2),
                                    rs.getInt(3)
                            )
                    );
                }
            }
        }
        return incomeOperationArrayList;
    }

    @Override
    public ArrayList<ExpenseOperation> getExpenseOperation() throws SQLException {
        ArrayList<ExpenseOperation> expenseOperationArrayList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, props)) {
            try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM expenses_operations;")) {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    expenseOperationArrayList.add(
                            new ExpenseOperation(
                                    rs.getInt(2),
                                    rs.getInt(3)
                            )
                    );
                }
            }
        }
        return expenseOperationArrayList;
    }

    @Override
    public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> userArrayList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, props)) {
            try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM users;")) {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    userArrayList.add(
                            new User(
                                    rs.getString(2),
                                    rs.getString(3)
                            )
                    );
                }
            }
        }
        return userArrayList;
    }


    public int authenticateUser(String login , String password) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, props)) {
            try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM users WHERE login = '"+login+"' and password = '" + password + "'")) {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }
    public ArrayList<Balance> getBalancesByUserId(int userId) throws SQLException {
        ArrayList<Balance> balances = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, props)) {
            try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM balances WHERE user_id = " + userId)) {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    balances.add(new Balance(rs.getInt(1) ,
                            rs.getString(2),
                            rs.getInt(3)));
                }
            }
        }

        return balances;

    }
    public ArrayList<Operation> getOperationsByBalanceId(int balanceId , LocalDate timeStart , LocalDate timeEnd) throws SQLException {
        ArrayList<Operation> operations = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, props)) {
            try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM operations WHERE balance_id = " + balanceId
            + " and date_of_operation > '" + timeStart.toString()
            + "' and date_of_operation < '" + timeEnd.toString() + "'"))
            {

                ResultSet rs = pst.executeQuery();

                while (rs.next()){
                    operations.add(
                            new Operation(rs.getInt(1),
                            ((Timestamp)  rs.getObject(3)).toInstant(),
                            rs.getInt(2)));
            }
        }
    }
        return operations;

    }
    public int getTypeOfOperationByOperationId(int operationId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, props)) {
            try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM incomes_operations where id_of_operation = " + operationId)) {

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    return 1;
                }
            }

            try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM expenses_operations where id_of_operation = " + operationId)) {

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    return 2;
                }
            }
        }
        return 0;
    }
    public ArrayList<Income> getIncomesByOperationId(int operationId) throws SQLException {
        ArrayList<Income> incomes = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, props)) {
            try (PreparedStatement pst = connection.prepareStatement("SELECT inc.type_of_incomes , inc.sum_of_incomes , inc.id " +
                    "from incomes inc " +
                    "inner join incomes_operations io on inc.id = io.id_of_income " +
                    "where io.id_of_operation = " + operationId)) {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                  incomes.add( new Income(rs.getInt(3) , rs.getString(1) , rs.getInt(2)));
                }
            }
        }

        return incomes;
    }
    public ArrayList<Expense> getExpensesByOperationId(int operationId) throws SQLException {
        ArrayList<Expense> expenses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, props)) {
            try (PreparedStatement pst = connection.prepareStatement("SELECT exp.type_of_expenses , exp.sum_of_expenses  , exp.id " +
                    "from expenses exp " +
                    "inner join expenses_operations io on exp.id = io.id_of_expense " +
                    "where io.id_of_operation = " + operationId)) {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    expenses.add( new Expense(rs.getInt(3) ,rs.getInt(2) , rs.getString(1)));
                }
            }
        }

        return expenses;
    }
}
