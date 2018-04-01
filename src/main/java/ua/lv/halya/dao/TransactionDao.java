package ua.lv.halya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lv.halya.app.Status;
import ua.lv.halya.entity.Transaction;

import java.sql.Date;
import java.util.List;


public interface TransactionDao extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByUserEmailAndCategoryStatusAndDateBetweenOrderByDate(String email, Status status, Date dateAfter, Date dateBefore);

    List<Transaction> findByUserEmailOrderByDate(String email);

    List<Transaction> findByUserEmailAndCategoryNameAndDateBetween(String email, String name, Date dateAfter, Date dateBefore);
}
