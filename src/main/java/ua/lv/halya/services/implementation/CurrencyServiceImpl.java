package ua.lv.halya.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lv.halya.dao.CurrencyDao;
import ua.lv.halya.entity.Currency;
import ua.lv.halya.services.CurrencyService;

import java.util.List;


@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyDao currencyDao;

    @Override
    public void add(Currency currency) {
        if(!currency.getName().equalsIgnoreCase("") && currency.getName() != null && currency.getRate() >0){
            currencyDao.save(currency);
        }
    }

   @Override
    public void delete(int id) {
        if(currencyDao.findOne(id).getTransactionList().isEmpty()){
            currencyDao.delete(id);
        }
    }

    @Override
    public Currency findById(int id) {
        return currencyDao.findOne(id);
    }

    @Override
    public List<Currency> findAll() {
        return currencyDao.findAll();
    }

    @Override
    public void changeRate(int currencyId, double newRate) {
        if (newRate >0) {
            Currency currency = currencyDao.findOne(currencyId);
            currency.setRate(newRate);
            currencyDao.save(currency);
        }
    }

}
