package by.academy.it;

import java.math.BigDecimal;
import java.sql.*;

import static java.lang.Integer.parseUnsignedInt;

public class DataSource {

    Connection connection;
    String[][] arrayParam;


    public DataSource(Connection connection, String[][] arrayParam) throws SQLException {
        this.connection = connection;
        this.arrayParam = arrayParam;
    }

    public void writeDate() throws SQLException {

        for (int i = 0; i < arrayParam.length; i++) {

            Statement statement = connection.createStatement();

            statement.executeUpdate("SET NAMES utf8 COLLATE utf8_unicode_ci");

            ResultSet result;

            result = statement.executeQuery("SELECT * FROM receivers WHERE name='" + arrayParam[i][1] + "'");
            int numReceiver;
            if (result.next()) {
                numReceiver = result.getInt("num");
            } else {
                String toReceivers = "INSERT INTO receivers VALUES (" + getNextRow("receivers") + "," + "'" + arrayParam[i][1] + "')";
                numReceiver = getNextRow("receivers");
                statement.executeUpdate(toReceivers);
             }

            java.math.BigDecimal value = BigDecimal.valueOf(parseUnsignedInt(arrayParam[i][2]));
            java.sql.Date date = Date.valueOf(arrayParam[i][0]);
            String toExpenses = "INSERT INTO expenses VALUES (" + getNextRow("expenses") + "," + "'" + date + "'" + "," + numReceiver + "," + value + ")";
            statement.executeUpdate(toExpenses);
            statement.close();
        }
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

    public void printData() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT expenses.num,paydate,name,value FROM expenses,receivers WHERE receiver=receivers.num");
        while (result.next()) {
            System.out.println(result.getString(1) + " " + result.getString(2) + " " + result.getString(3) + " " + result.getString(4));
        }
        statement.close();
    }
}
