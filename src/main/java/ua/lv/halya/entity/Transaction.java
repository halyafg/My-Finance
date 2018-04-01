package ua.lv.halya.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idTransaction;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private Date date;

    @Column
    private String comment;

    @ManyToOne
    private Currency currency;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;

    public Transaction() {
    }

    public Transaction(double amount, Date date, String comment) {
        this.amount = amount;
        this.date = date;
        this.comment = comment;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getAmountWithCurrencyRate(){
        return this.amount*this.currency.getRate();
    }
}
