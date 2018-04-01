package ua.lv.halya.services;

import ua.lv.halya.app.Status;
import ua.lv.halya.entity.Category;
import ua.lv.halya.entity.Currency;
import ua.lv.halya.entity.Transaction;

import java.sql.Date;
import java.util.List;
import java.util.Map;


public interface TransactionService {

    void add(String userLogin, Category category, double amount, Currency currency, Date date, String comment);

    void edit(int idTransaction, Date date,int categoryId,  double amount, Currency currency, String comment);

    void delete(int id);

    Transaction findById(int id);

    Date currentDate();

    List<Transaction> findUserTransactionsByName(String login, String name);

    double getUserCost(String login, Status status);

    Map<String, Double> getUserFundsSeparateByCategory(String login, Status status);

    Date findDateOfOldestTransaction(String email);

    Date findDateOfNewestTransaction(String email);
}
