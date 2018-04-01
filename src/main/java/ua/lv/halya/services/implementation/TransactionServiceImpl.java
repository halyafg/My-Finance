package ua.lv.halya.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lv.halya.app.Period;
import ua.lv.halya.app.Status;
import ua.lv.halya.dao.CategoryDao;
import ua.lv.halya.dao.TransactionDao;
import ua.lv.halya.dao.UserDao;
import ua.lv.halya.entity.Category;
import ua.lv.halya.entity.Currency;
import ua.lv.halya.entity.Transaction;
import ua.lv.halya.services.TransactionService;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionDao transactionDao;

    private CategoryDao categoryDao;

    private UserDao userDao;

    @Autowired
    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(String userLogin, Category category, double amount, Currency currency, Date date, String comment) {
        if (amount > 0){
            Transaction t = new Transaction(amount, date, comment);
            t.setCategory(category);
            t.setCurrency(currency);
            t.setUser(this.userDao.findByEmail(userLogin));
            transactionDao.save(t);
        }
    }

    @Override
    public void edit(int idTransaction, Date date,int categoryId,  double amount, Currency currency, String comment) {
        Transaction transaction = transactionDao.findOne(idTransaction);

        if(currency != null ){
            transaction.setCurrency(currency);
        }
        if(amount >0){
            transaction.setAmount(amount);
        }
        if(!date.toString().equalsIgnoreCase("") && date.toString() != null ){
            transaction.setDate(date);
        }
        if(!comment.equalsIgnoreCase("") ){
            transaction.setComment(comment);
        }
        transaction.setCategory(this.categoryDao.findOne(categoryId));
        transactionDao.save(transaction);
    }

    @Override
    public void delete(int id) {
       transactionDao.delete(id);
    }

    @Override
    public Transaction findById(int id) {
        return  transactionDao.findOne(id);
    }


    @Override
    public Date currentDate(){
        return new Date(System.currentTimeMillis());
    }

    @Override
    public List<Transaction> findUserTransactionsByName(String login, String name) {
        return transactionDao.findByUserEmailAndCategoryNameAndDateBetween(login, name, Period.dateFrom, Period.dateTo);
    }

    /**
     *
     * @param login - userLogin(email)
     * @param status- category's status (INCOME or OUTCOME)
     * @return  all user's funds  (by INCOME or OUTCOME)!!! with USING CURRENCY RATE >
     */
    @Override
    public double getUserCost(String login, Status status){
        List<Transaction> transactionList = transactionDao.findByUserEmailAndCategoryStatusAndDateBetweenOrderByDate(login, status, Period.dateFrom, Period.dateTo);
        double sum =0;
        for (Transaction tr:transactionList
             ) {
            sum += tr.getAmountWithCurrencyRate();
        }
        return sum;
    }

    /**
     *
     * @param login - userLogin(email)
     * @param status- category's status (INCOME or OUTCOME)
     * @return Map <category's name,  double category's amount !!! with USING CURRENCY RATE >
     */
    @Override
    public Map<String, Double> getUserFundsSeparateByCategory(String login, Status status){
        List<Transaction> transactionList = transactionDao.findByUserEmailAndCategoryStatusAndDateBetweenOrderByDate(login, status, Period.dateFrom, Period.dateTo);
        Map<String, Double> categoryMap = new HashMap<String, Double>();
        for (Transaction tr: transactionList) {
            if(!categoryMap.containsKey(tr.getCategory().getName())){
                categoryMap.put(tr.getCategory().getName(), tr.getAmountWithCurrencyRate());
            }else{
                for (Map.Entry<String, Double> entry : categoryMap.entrySet()) {
                    if (entry.getKey().equals(tr.getCategory().getName())){
                        entry.setValue(entry.getValue()+tr.getAmountWithCurrencyRate());
                        break;
                    }
                }
            }

        }
        return categoryMap;
    }

    @Override
    public Date findDateOfOldestTransaction(String email) {
        return transactionDao.findByUserEmailOrderByDate(email).get(0).getDate();
    }

    @Override
    public Date findDateOfNewestTransaction(String email) {
        return transactionDao.findByUserEmailOrderByDate(email).get(transactionDao.findByUserEmailOrderByDate(email).size()-1).getDate();
    }
}
