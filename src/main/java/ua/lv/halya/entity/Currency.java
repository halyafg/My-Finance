package ua.lv.halya.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private double rate;

    @OneToMany(mappedBy = "currency", fetch = FetchType.EAGER)
    private List<Transaction> transactionList;


    public Currency() {
    }

    public Currency(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
