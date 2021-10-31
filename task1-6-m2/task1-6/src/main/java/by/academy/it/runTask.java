package by.academy.it;

import java.sql.*;

public class runTask {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

            ConnectionManager connectionManager = new ConnectionManager();
            Connection myConnection = connectionManager.getConnection();
            DataSource dataSource = new DataSource(myConnection);
            dataSource.expensesToReceivers();
            dataSource.dayMaxPayment();
            dataSource.maxPaymentToDayMaxPayment();
            myConnection.close();
   }
}
