package main;

import au.com.bytecode.opencsv.CSVWriter;
import entity.*;
import jdbc.impl.JDBCDataImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {

        JDBCDataImpl data = new JDBCDataImpl();

        ArrayList<Balance> balanceArrayList = data.getBalances();
        ArrayList<Expense> expenseArrayList = data.getExpenses();
        ArrayList<ExpenseOperation> expenseOperationArrayList = data.getExpenseOperation();
        ArrayList<Income> incomeArrayList = data.getIncomes();
        ArrayList<IncomeOperation> incomeOperationArrayList = data.getIncomeOperation();
        ArrayList<Operation> operationArrayList = data.getOperation();
        ArrayList<User> userArrayList = data.getUsers();
      /*  for (Balance balance: balanceArrayList){
            System.out.println(balance.nameOfBalance + " " + balance.userId);
        }

        for (Expense expense: expenseArrayList){
            System.out.println(expense.sumOfExpenses + " " + expense.typeOfExpenses);
        }

        for (ExpenseOperation expenseOperation: expenseOperationArrayList){
            System.out.println(expenseOperation.idOfExpense + " " + expenseOperation.idOfOperation);
        }

        for (Income income: incomeArrayList){
            System.out.println(income.sumOfIncome + " " + income.typeOfIncome);
        }

        for (IncomeOperation incomeOperation: incomeOperationArrayList){
            System.out.println(incomeOperation.idOfIncome + " " + incomeOperation.idOfOperation);
        }

        for (Operation operation: operationArrayList){
            System.out.println(operation.balanceId + " " + operation.date);
        }

        for (User user: userArrayList){
            System.out.println(user.login + " " + user.password);
        }
*/

        Scanner scn = new Scanner(System.in);

        System.out.println("Login : ");
        String login = scn.next();
        System.out.println("Password : ");
        String pass = scn.next();

        int userId = data.authenticateUser(login , pass);

        System.out.println("\n\nYour balances : ");
        for (Balance bal : data.getBalancesByUserId(userId)){
            System.out.println("Id = " + bal.id + "  -  Name = " + bal.nameOfBalance);
        }
        System.out.println("Please select balance id : ");
        int balanceId = scn.nextInt();


        System.out.println("Please enter date of start (Year) : ");
        int yearStart = scn.nextInt();
        System.out.println("Month : ");
        int monthStart = scn.nextInt();
        System.out.println("Day : ");
        int dayStart = scn.nextInt();

        System.out.println("Please enter date of end (Year) : ");
        int yearEnd = scn.nextInt();
        System.out.println("Month : ");
        int monthEnd = scn.nextInt();
        System.out.println("Day : ");
        int dayEnd = scn.nextInt();

        LocalDate dateStart = LocalDate.of(yearStart , monthStart , dayStart);
        LocalDate dateEnd = LocalDate.of(yearEnd , monthEnd , dayEnd);

        System.out.println("\n\nYour operations : ");

        String csv = "data.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));

        int Incomes = 0;
        int Expenses = 0;

        for(Operation oper : data.getOperationsByBalanceId(balanceId , dateStart , dateEnd)){
            int operationType = data.getTypeOfOperationByOperationId(oper.id);
            switch (operationType){
                case 1:{
                    //Incomes

                    for(Income inc : data.getIncomesByOperationId(oper.id)){
                        System.out.println(inc.toString());
                        Incomes += inc.sumOfIncome;
                        writer.writeNext(inc.toCSVFormat());
                    }

                    break;
                }
                case 2:{
                    //Expenses

                    for(Expense exp : data.getExpensesByOperationId(oper.id)){
                        System.out.println(exp.toString());
                        Expenses += exp.sumOfExpenses;
                        writer.writeNext(exp.toCSVFormat());
                    }

                    break;
                }

                case 0:
                default:{
                    System.out.println("No actions for this operation");
                }
            }


        }



        System.out.println("\n\nSum of incomes = " + Incomes);
        System.out.println("Sum of expenses = " + Expenses);
        System.out.println("Saldo = " + (Incomes - Expenses));

        String[] IncomesArr = new String[2];
        IncomesArr[0] = "IncomesSum";
        IncomesArr[1] = Integer.toString(Incomes);
        writer.writeNext(IncomesArr);

        String[] ExpensesArr = new String[2];
        ExpensesArr[0] = "ExpensesSum";
        ExpensesArr[1] = Integer.toString(Expenses);
        writer.writeNext(ExpensesArr);

        String[] SaldoArr = new String[2];
        SaldoArr[0] = "Saldo";
        SaldoArr[1] = Integer.toString(Incomes - Expenses);
        writer.writeNext(SaldoArr);

        writer.close();



        //System.out.println(data.authenticateUser("alinablago" , "1234"));
    }
}
