package ua.lv.halya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lv.halya.entity.Currency;


public interface CurrencyDao extends JpaRepository<Currency, Integer> {

}
