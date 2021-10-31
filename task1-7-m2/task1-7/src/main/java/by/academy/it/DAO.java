package by.academy.it;


import java.util.ArrayList;

public interface DAO {

    Receiver getReceiver (int num);
    ArrayList<Receiver> getReceivers();
    Expense getExpense (int num);
    ArrayList<Expense> getExpenses();
    int addReceiver(Receiver receiver);
    int addExpense(Expense expense);

}
