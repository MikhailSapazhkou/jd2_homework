package by.academy.it;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public ConnectionManager() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }


    public Connection getConnection() throws SQLException {


        String baseName = VariableApp.BASE_NAME;
        String portBase = VariableApp.NUMBER_BASE_PORT;
        String serverName = VariableApp.SERVER_NAME;
        String userName = VariableApp.USER_NAME;
        String userPassword = VariableApp.USER_PASSWORD;
        String dbURL = "jdbc:mysql://" + serverName + ":" + portBase + "/" + baseName+"?";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbURL, userName, userPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
