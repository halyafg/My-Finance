package ua.lv.halya.services;

import ua.lv.halya.entity.Currency;

import java.util.List;

public interface CurrencyService {

    void add(Currency currency);

    void delete(int id);

    Currency findById (int id);

    List<Currency> findAll();

    void changeRate(int currencyId, double newRate);
}
