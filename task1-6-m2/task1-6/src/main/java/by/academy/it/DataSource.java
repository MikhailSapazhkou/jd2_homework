package by.academy.it;

import java.sql.*;

public class DataSource {

    Connection connection;

    public DataSource(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void expensesToReceivers() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT receivers.name, SUM(value) FROM expenses,receivers WHERE receiver=receivers.num GROUP BY name");
        System.out.println("Получателем перечислено: ");
        System.out.println("========================");
        while (result.next()) {
            System.out.println(result.getString(1) + " " + result.getString(2));
        }
        System.out.println("========================");
        statement.close();
    }

    public void dayMaxPayment() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT expenses.paydate, MAX(value) FROM expenses");
        result.next();
        String dateMaxPayment = result.getString(1);
        result.close();
        result = statement.executeQuery("SELECT SUM(value) FROM expenses WHERE paydate=" + "'" + dateMaxPayment + "'");
        result.next();
        System.out.println();
        System.out.println("========================");
        System.out.println("Максимальный палтеж был зарегистрирован: "+dateMaxPayment+"\n"+
                           "Общая сумма платежей в этот день составляет: " +result.getString(1));
        System.out.println("========================");
        result.close();
        statement.close();
    }

    public void maxPaymentToDayMaxPayment() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT expenses.paydate, SUM(value) FROM expenses GROUP BY paydate");
        Statement st = result.getStatement();
        ResultSet rs = st.executeQuery("SELECT paydate, MAX(value) FROM expenses");
        rs.next();
        String dateMaxPayment = rs.getString(1);
        rs.close();

        result = statement.executeQuery("SELECT paydate, receivers.name, MAX(value) FROM expenses, receivers WHERE paydate='" + dateMaxPayment + "' AND receivers.num=expenses.receiver");
        result.next();
        System.out.println();
        System.out.println("Наибольший платеж в день ("+dateMaxPayment+") наибольших платежей");
        System.out.println("========================");
        System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3));
        System.out.println("========================");
        result.close();
        statement.close();
    }
}
