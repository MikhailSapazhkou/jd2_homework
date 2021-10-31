package by.academy.it;

import java.sql.*;

public class runTask {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        if (args.length > 0) {
            ConnectionManager connectionManager = new ConnectionManager();
            Connection myConnection = connectionManager.getConnection();
            String[][] arrayParam = ParserArgs.getParam(args);
            DataSource dataSource = new DataSource(myConnection,arrayParam);
            dataSource.writeDate();
            dataSource.printData();
            myConnection.close();
        } else {
            System.out.println("Необходимо передать один или более аргументов \n" +
                    "в формате \n" +
                    "ГГГГ-ММ-ДД;Получатель;000000");
        }
    }
}
