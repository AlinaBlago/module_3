package jdbc;

import entity.*;
import jdbc.impl.JDBCDataImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public interface JDBCData {
    Properties props = loadProperties();
    String url = props.getProperty("url");

    ArrayList<Balance> getBalances() throws SQLException;

    ArrayList<Expense> getExpenses() throws SQLException;

    ArrayList<Income> getIncomes() throws SQLException;

    ArrayList<Operation> getOperation() throws SQLException;

    ArrayList<IncomeOperation> getIncomeOperation() throws SQLException;

    ArrayList<ExpenseOperation> getExpenseOperation() throws SQLException;

    ArrayList<User> getUsers() throws SQLException;

    static Properties loadProperties() {
        Properties props = new Properties();

        try (InputStream input = JDBCDataImpl.class.getResourceAsStream("/jdbc.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return props;
    }

}
