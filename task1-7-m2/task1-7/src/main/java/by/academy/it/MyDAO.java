package by.academy.it;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import static java.lang.Integer.parseUnsignedInt;

public class MyDAO implements DAO {

    ConnectionManager connectionManager = new ConnectionManager();
    Connection connection = connectionManager.getConnection();

    public MyDAO() throws SQLException, ClassNotFoundException {
    }


    @Override
    public Receiver getReceiver(int num) {

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT name FROM receivers WHERE num='" + num + "'");
            result.next();
            Receiver receiver = new Receiver();
            receiver.setNum(num);
            receiver.setName(result.getString(1));
            result.close();
            statement.close();
            return receiver;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Receiver> getReceivers() {

        ArrayList<Receiver> receivers = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM receivers");
            while (result.next()) {
                Receiver receiver = new Receiver();
                receiver.setNum(result.getInt(1));
                receiver.setName(result.getString(2));
                receivers.add(receiver);
            }
            result.close();
            statement.close();
            return receivers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense getExpense(int num) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT paydate, receiver, value FROM expenses WHERE num='" + num + "'");
            result.next();
            Expense expense = new Expense();
            expense.setNum(num);
            expense.setPaydate(result.getDate(1));
            expense.setReceiver(result.getInt(2));
            expense.setValue(result.getBigDecimal(3));
            result.close();
            statement.close();
            return expense;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Expense> getExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM expenses");
            while (result.next()) {
                Expense expense = new Expense();
                expense.setNum(result.getInt(1));
                expense.setPaydate(result.getDate(2));
                expense.setReceiver(result.getInt(3));
                expense.setValue(result.getBigDecimal(4));
                expenses.add(expense);
            }
            result.close();
            statement.close();
            return expenses;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public int addReceiver(Receiver receiver) {
        try {
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM receivers WHERE name='" + receiver.getName() + "'");
            int numReceiver;
            if (result.next()) {
                numReceiver = result.getInt("num");
            } else {
                String sql = "INSERT INTO receivers VALUES (" + getNextRow("receivers") + "," + "'" + receiver.getName() + "')";
                numReceiver = getNextRow("receivers");
                statement.executeUpdate(sql);
            }
            result.close();
            statement.close();
            return numReceiver;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }

    }

    @Override
    public int addExpense(Expense expense) {

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO expenses VALUES (" + getNextRow("expenses") + "," + expense.getPaydate() + "," + expense.getReceiver() + "," + expense.getValue() + ")";
            int nextNum = getNextRow("expenses");
            statement.executeUpdate(sql);
            statement.close();
            return nextNum;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }

    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    private int getNextRow(String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM " + tableName);
        result.next();
        int a = result.getInt(1) + 1;
        result.close();
        statement.close();
        return a;
    }

}
