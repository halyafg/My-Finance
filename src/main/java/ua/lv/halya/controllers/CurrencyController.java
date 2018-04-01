package ua.lv.halya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lv.halya.entity.Currency;
import ua.lv.halya.services.CurrencyService;
import ua.lv.halya.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping(value = "/currency")
public class CurrencyController {

    private final String REDIRECT_CURRENCY_EDIT = "redirect:/currency/editPage";
    private final String EDIT_CURRENCY = "editCurrency";

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/editPage", method = RequestMethod.GET)
    private String openAddCurrencyPage(Model model, Principal principal){
        model.addAttribute("currencies", currencyService.findAll());
        model.addAttribute("principal", userService.findByLogin(principal.getName()));
        return EDIT_CURRENCY;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private String addCurrency(@RequestParam("name") String currencyName,
                               @RequestParam("rate") double rate){
        currencyService.add(new Currency(currencyName, rate));
        return REDIRECT_CURRENCY_EDIT;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private String deleteCurrency(@RequestParam("id") int id){
        currencyService.delete(id);
        return REDIRECT_CURRENCY_EDIT;
    }

    @RequestMapping(value = "/changeRate", method = RequestMethod.POST)
    private String changeRate(@RequestParam("currencyId") int currencyId,
                              @RequestParam("rate") double rate){
        currencyService.changeRate(currencyId, rate);
        return REDIRECT_CURRENCY_EDIT;
    }

}
