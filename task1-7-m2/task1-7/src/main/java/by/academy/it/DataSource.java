package by.academy.it;

import java.sql.SQLException;
import java.util.ArrayList;

public class DataSource {

    MyDAO myDAO = new MyDAO();

    public DataSource() throws SQLException, ClassNotFoundException {
    }

    public void printExpenses(){

        ArrayList<Expense> expenses = myDAO.getExpenses();
        for (int i = 0; i < expenses.size(); i++) {
            Expense exp = expenses.get(i);
            System.out.println(exp.getNum()+" "+exp.getPaydate()+ " "+exp.getReceiver()+ " "+ exp.getValue());
        }
    }

    public void addNewReceiver(){
        Receiver r = new Receiver();
        r.setNum(10);
        r.setName("Гипермаркет Евороопт");
        myDAO.addReceiver(r);
    }

    public void closeConnection() throws SQLException {
        myDAO.closeConnection();
    }
}
