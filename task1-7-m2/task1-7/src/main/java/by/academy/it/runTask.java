package by.academy.it;

import java.sql.*;

public class runTask {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DataSource dataSource = new DataSource();
        dataSource.printExpenses();
        dataSource.addNewReceiver();
        dataSource.closeConnection();
    }
}
