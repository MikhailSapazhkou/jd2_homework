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


            String template = "SELECT * FROM receivers WHERE name=?";

            PreparedStatement pStatement = connection.prepareStatement(template);
            pStatement.setString(1, arrayParam[i][1]);
            ResultSet result;
            result = pStatement.executeQuery();
            int numReceiver;
            if (result.next()) {
                numReceiver = result.getInt("num");
            } else {
                int nextId = getNextRow("receivers");
                template = "INSERT INTO receivers VALUES (?,?)";
                pStatement = connection.prepareStatement(template);
                pStatement.setInt(1, nextId);
                pStatement.setString(2, arrayParam[i][1]);

                numReceiver = getNextRow("receivers");
                pStatement.executeUpdate();
            }
            result.close();
            pStatement.close();
            int nextId = getNextRow("expenses");
            template = "INSERT INTO expenses VALUES (?, ?, ?, ?)";
            pStatement = connection.prepareStatement(template);
            java.math.BigDecimal value = BigDecimal.valueOf(parseUnsignedInt(arrayParam[i][2]));
            java.sql.Date date = Date.valueOf(arrayParam[i][0]);
            pStatement.setInt(1, nextId);
            pStatement.setDate(2, date);
            pStatement.setInt(3, numReceiver);
            pStatement.setBigDecimal(4, value);
            pStatement.executeUpdate();
            pStatement.close();
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
